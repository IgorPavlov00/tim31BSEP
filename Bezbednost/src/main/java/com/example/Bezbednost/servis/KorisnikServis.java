package com.example.Bezbednost.servis;

import com.example.Bezbednost.model.Korisnik;
import com.example.Bezbednost.model.Korisnik;
import com.example.Bezbednost.repo.KorisnikRepo;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class KorisnikServis {
    private final KorisnikRepo korisnikRepo;
    @Autowired
    public KorisnikServis(KorisnikRepo radnikRepo) {
        this.korisnikRepo = radnikRepo;
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


    public Korisnik getKorisnikByEmail(String email) {
        System.out.println(email);
        return this.korisnikRepo.getKorisnikByEmail(email);
    }
}
