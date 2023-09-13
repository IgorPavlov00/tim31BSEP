package com.example.Bezbednost.servis;

import com.example.Bezbednost.model.CrtCertificate;
import com.example.Bezbednost.model.FormData;
import com.example.Bezbednost.model.Korisnik;
import com.example.Bezbednost.repo.CrtRepo;
import com.example.Bezbednost.repo.KorisnikRepo;


import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.cert.CertificateEncodingException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.concurrent.TimeUnit;

@Transactional
@Service
public class KorisnikServis {
    private final KorisnikRepo korisnikRepo;

    private final CrtRepo crtRepo;

    @Autowired
    public KorisnikServis(KorisnikRepo radnikRepo,CrtRepo crt) {
        this.korisnikRepo = radnikRepo;
        this.crtRepo=crt;

    }
    public Korisnik dodajRadnika(Korisnik radnik){
        return korisnikRepo.save(radnik);
    }
    public List<Korisnik> pronadjiSveRadnike(){
        return korisnikRepo.findAll();
    }

    public Korisnik izmeniRadnika(Korisnik radnik){
        return korisnikRepo.save(radnik);
    }

    public Korisnik pronadjiPoId(Long id){
        return korisnikRepo.findRadnikById(id);

    }
    public void izbrisiRadnika(Long id){
        korisnikRepo.deleteRadnikById(id);

    }
    public void generateCertificate(FormData certificateDTO) throws Exception {
        // Generate a key pair (public and private key)
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // You can change the key size as needed
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        // Prepare the X.500 distinguished name for the certificate
        // Create a certificate builder
        X500Name issuer = new X500Name("CN=Igor, O=Bezbednost, C=Serbia");
        X500Name subject = issuer; // Self-signed certificate
        BigInteger serialNumber = BigInteger.valueOf(System.currentTimeMillis()); // Use a unique serial number

        X509v3CertificateBuilder certBuilder = new X509v3CertificateBuilder(
                issuer,
                serialNumber,
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(365)), // Validity period
                subject,
                SubjectPublicKeyInfo.getInstance(publicKey.getEncoded())); // Use the public key info
        // Self-sign the certificate
        ContentSigner signer = new JcaContentSignerBuilder("SHA256WithRSA").build(privateKey);
        X509CertificateHolder certificateHolder = certBuilder.build(signer);
        X509Certificate x509Certificate = new JcaX509CertificateConverter().getCertificate(certificateHolder);

        // Save the certificate to a .crt file
        saveCertificateToFile(x509Certificate, "./src/main/resources/data/crt/" + "igorpavlov106@gmail.com" + ".crt");
        crtRepo.save(new CrtCertificate(certificateDTO.getCommonName(),certificateDTO.getOrganizationUnit(),certificateDTO.getOrganizationName(),certificateDTO.getLocalityName(),certificateDTO.getStateName(),certificateDTO.getCountryName(),"./src/main/resources/data/crt/" + "igorpavlov106@gmail.com" + ".crt",true));
        System.out.println("Certificate generated and saved successfully.");
    }
    public static void saveCertificateToFile(X509Certificate certificate, String fileName) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            byte[] certificateData = certificate.getEncoded();
            fos.write("-----BEGIN CERTIFICATE-----\n".getBytes());
            fos.write(Base64.getEncoder().encode(certificateData));
            fos.write("\n-----END CERTIFICATE-----\n".getBytes());
        } catch (CertificateEncodingException ex) {
            throw new CertificateException("Error encoding the certificate: " + ex.getMessage());
        }
    }
    public void issueNewCertificate(FormData certificateDTO, HttpServletRequest request) throws Exception {






    }

    public Korisnik getKorisnikByEmail(String email) {
        System.out.println(email);
        return this.korisnikRepo.getKorisnikByEmail(email);
    }

    public List<CrtCertificate> getCertificates() {
       return crtRepo.findAll();
    }

    public void revoke(Long id) {
        CrtCertificate  certificate=crtRepo.findById(id).orElse(null);
        certificate.setValid(false);
        crtRepo.save(certificate);

    }
}
