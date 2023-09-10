package com.example.Bezbednost;

import com.example.Bezbednost.model.Korisnik;

import com.example.Bezbednost.repo.KorisnikRepo;

import com.example.Bezbednost.servis.KorisnikServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
//@RequestMapping("")
public class RadnikResurs {

    private static final int MAX_FAILED_ATTEMPTS = 2;
    private static final int MAX_LOGIN_ATTEMPTS = 3;
    private static final long LOCKOUT_TIME_MINUTES = 5;
    private final KorisnikServis radnikServis;
    @Autowired
    private KorisnikRepo radnikRepo;

    @Autowired
    PasswordEncoder encoder;

    public RadnikResurs(KorisnikServis radnikServis) {
        this.radnikServis = radnikServis;
    }
    @Autowired
    public JavaMailSender emailSender;



    @GetMapping("/hello")
    public String pozdrav(){
        return "pozdrav!";
    }


    @GetMapping("/all")
    public ResponseEntity<List<Korisnik>> prikaziSveRadnike(){
        List<Korisnik> li=radnikServis.pronadjiSveRadnike();
        return new ResponseEntity<>(li, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Korisnik> getRadnikById(@PathVariable("id") Long id){
        Korisnik radnik=radnikServis.pronadjiPoId(id);
        return new ResponseEntity<>(radnik, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Korisnik> dodajRadnik(@RequestBody Korisnik radnik){
        String encoded=encoder.encode(radnik.getLozinka());
        radnik.setLozinka(encoded);
        Korisnik radnik1=radnikServis.dodajRadnika(radnik);
        return new ResponseEntity<>(radnik1, HttpStatus.CREATED);

    }

    @PutMapping("/update")
    public ResponseEntity<Korisnik> updateRadnik(@RequestBody Korisnik radnik){
        Korisnik radnik1=radnikServis.izmeniRadnika(radnik);
        return new ResponseEntity<>(radnik1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        radnikServis.izbrisiRadnika(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody Korisnik r){
     System.out.println(r);
        Korisnik radnik=radnikRepo.getKorisnikByEmail(r.getEmail());
        System.out.println(radnik);
        if(radnik.getEmail().equals(r.getEmail()) && radnik.getIme().equals(r.getIme()))

            return ResponseEntity.ok(radnik);

        return (ResponseEntity<?>)ResponseEntity.internalServerError();

    }

//    @Override
//    public void register(CSRRequestDTO requestDTO, MultipartFile document, HttpServletRequest request) throws IOException {
//        CSRRequest csrRequest = new CSRRequest();
//        csrRequest.setEmail(requestDTO.getEmail());
//        csrRequest.setFirstName(requestDTO.getFirstName());
//        csrRequest.setLastName(requestDTO.getLastName());
//        csrRequest.setTimestamp(LocalDateTime.now());
//
//        String filePath = "src/main/resources/data/csr/"+requestDTO.getEmail()+".csr";
//        File path = new File(filePath);
//        path.createNewFile();
//        FileOutputStream output = new FileOutputStream(path);
//        output.write(document.getBytes());
//        output.close();
//
//        logService.addInfo(new LogDTO(LogAction.CREATING_NEW_CSR_REQUEST, CLS_NAME, "Creating new csr request for user: " + requestDTO.getEmail(), HttpUtils.getRequestIP(request)));
//        csrRequestRepository.save(csrRequest);
//    }

//    @PostMapping("/login")
//    public ResponseEntity<?>login(@RequestBody Korisnik r){
//
//        if(r.getEmail().equals(r.getEmail()) && r.getIme().equals(r.getIme())) {
//            r.setFailedAttempts(0); // reset failed attempts if login is successful
//            return ResponseEntity.ok(r);
//        } else {
//            long failedAttempts = r.getFailedAttempts() + 1;
//            r.setFailedAttempts(failedAttempts);
//            radnikRepo.save(r);
//            if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
//                // block the account
//
//                radnikRepo.save(r);
//                return ResponseEntity.badRequest().body("Account is locked.");
//            }
//            return (ResponseEntity<?>)ResponseEntity.internalServerError();
//        }
//    }





}
