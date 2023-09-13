package com.example.Bezbednost.repo;

import com.example.Bezbednost.model.CrtCertificate;
import com.example.Bezbednost.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrtRepo extends JpaRepository<CrtCertificate,Long> {

}
