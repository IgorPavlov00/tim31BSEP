package com.example.Bezbednost;

import com.example.Bezbednost.model.Korisnik;
import com.example.Bezbednost.model.LoginDTO;
import com.example.Bezbednost.model.LoginResponseDTO;

import com.example.Bezbednost.model.RegisterDTO;
import com.example.Bezbednost.repo.KorisnikRepo;

import com.example.Bezbednost.servis.KorisnikServis;
import com.example.Bezbednost.servis.KorisnikServis;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
