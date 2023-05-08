package com.example.Bezbednost.repo;

import com.example.Bezbednost.model.Radnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RadnikRepo extends JpaRepository<Radnik,Long> {

    void deleteRadnikById(Long id);

   Radnik findRadnikById(Long id);



//    Radnik findRadnikByIme(String ime);
}
