package com.example.Bezbednost.repo;

import com.example.Bezbednost.model.Korisnik;
import com.example.Bezbednost.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRepo extends JpaRepository<Korisnik,Long> {
    Korisnik getKorisnikByEmail(String email);
    void deleteRadnikById(Long id);

   Korisnik findRadnikById(Long id);



//    Radnik findRadnikByIme(String ime);
}
