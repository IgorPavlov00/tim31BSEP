package com.example.Bezbednost.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name="radnik")
@Data
@NoArgsConstructor
public class Radnik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;
    private String ime;
    private String prezime;
    private String posao;
    private String uloga;


    public Radnik(long id, String ime, String prezime, String posao,String uloga) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.posao = posao;
        this.uloga=uloga;
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

    @Override
    public String toString() {
        return "Radnik{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", posao='" + posao + '\'' +
                ", uloga='" + uloga + '\'' +
                '}';
    }
}
