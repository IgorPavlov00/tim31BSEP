package com.example.Bezbednost;

import com.example.Bezbednost.model.Radnik;
import com.example.Bezbednost.repo.RadnikRepo;
import com.example.Bezbednost.servis.RadnikServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("")
public class RadnikResurs {

    private final RadnikServis radnikServis;
    @Autowired
    private RadnikRepo radnikRepo;

    public RadnikResurs(RadnikServis radnikServis) {
        this.radnikServis = radnikServis;
    }

    @GetMapping("/hello")
    public String pozdrav(){
        return "pozdrav!";
    }


    @GetMapping("/all")
    public ResponseEntity<List<Radnik>> prikaziSveRadnike(){
        List<Radnik> li=radnikServis.pronadjiSveRadnike();
        return new ResponseEntity<>(li, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Radnik> getRadnikById(@PathVariable("id") Long id){
        Radnik radnik=radnikServis.pronadjiPoId(id);
        return new ResponseEntity<>(radnik, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Radnik> dodajRadnik(@RequestBody Radnik radnik){
        Radnik radnik1=radnikServis.dodajRadnika(radnik);
        return new ResponseEntity<>(radnik1, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Radnik> updateRadnik(@RequestBody Radnik radnik){
        Radnik radnik1=radnikServis.izmeniRadnika(radnik);
        return new ResponseEntity<>(radnik1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        radnikServis.izbrisiRadnika(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody Radnik r){
        System.out.println(r);
        Radnik radnik=radnikRepo.findRadnikById(r.getId());
        if(radnik.getId()==(r.getId()) && radnik.getIme().equals(r.getIme()))
            return ResponseEntity.ok(radnik);

        return (ResponseEntity<?>)ResponseEntity.internalServerError();

    }
}
