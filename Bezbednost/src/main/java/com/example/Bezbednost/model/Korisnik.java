package com.example.Bezbednost.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="korisnik")
@Data
@NoArgsConstructor
public class Korisnik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;
    private String ime;
    private String prezime;
    private String posao;
    private String uloga;
    private String lozinka;
    private String email;







    public Korisnik(long id, String ime, String prezime, String posao,String uloga,String lozinka,String email) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.posao = posao;
        this.uloga=uloga;
        this.lozinka=lozinka;
        this.email=email;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getPosao() {
        return posao;
    }

    public void setPosao(String posao) {
        this.posao = posao;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }

    public String getUloga() {
        return uloga;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    @Override
    public String toString() {
        return "Korisnik{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", posao='" + posao + '\'' +
                ", uloga='" + uloga + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", email='" + email + '\'' +

                '}';
    }
}
