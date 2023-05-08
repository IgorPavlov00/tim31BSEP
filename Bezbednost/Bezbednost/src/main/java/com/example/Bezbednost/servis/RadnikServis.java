package com.example.Bezbednost.servis;

import com.example.Bezbednost.exception.RadnikNotFoundException;
import com.example.Bezbednost.model.Radnik;
import com.example.Bezbednost.repo.RadnikRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.catalina.security.SecurityUtil.remove;
@Transactional
@Service
public class RadnikServis {
    private final RadnikRepo radnikRepo;
    @Autowired
    public RadnikServis(RadnikRepo radnikRepo) {
        this.radnikRepo = radnikRepo;
    }
    public Radnik dodajRadnika(Radnik radnik){
        return radnikRepo.save(radnik);
    }
    public List<Radnik> pronadjiSveRadnike(){
        return radnikRepo.findAll();
    }

    public Radnik izmeniRadnika(Radnik radnik){
        return radnikRepo.save(radnik);
    }

    public Radnik pronadjiPoId(Long id){
        return radnikRepo.findRadnikById(id);

    }
    public void izbrisiRadnika(Long id){
        radnikRepo.deleteRadnikById(id);

    }


}
