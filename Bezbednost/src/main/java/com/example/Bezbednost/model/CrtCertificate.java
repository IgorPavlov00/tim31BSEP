package com.example.Bezbednost.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="crt_certificate")
@Data
public class CrtCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;
    private String commonName;
    private String organizationUnit;
    private String organizationName;
    private String localityName;
    private String stateName;
    private String countryName;
    private String filepath;

    private Boolean valid;


    public CrtCertificate(String commonName, String organizationUnit, String organizationName, String localityName, String stateName, String countryName, String filepath,Boolean valid) {

        this.commonName = commonName;
        this.organizationUnit = organizationUnit;
        this.organizationName = organizationName;
        this.localityName = localityName;
        this.stateName = stateName;
        this.countryName = countryName;
        this.filepath = filepath;
        this.valid=valid;
    }

    public CrtCertificate() {

    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getOrganizationUnit() {
        return organizationUnit;
    }

    public void setOrganizationUnit(String organizationUnit) {
        this.organizationUnit = organizationUnit;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getLocalityName() {
        return localityName;
    }

    public void setLocalityName(String localityName) {
        this.localityName = localityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public String toString() {
        return "CrtCertificate{" +
                "id=" + id +
                ", commonName='" + commonName + '\'' +
                ", organizationUnit='" + organizationUnit + '\'' +
                ", organizationName='" + organizationName + '\'' +
                ", localityName='" + localityName + '\'' +
                ", stateName='" + stateName + '\'' +
                ", countryName='" + countryName + '\'' +
                ", filepath='" + filepath + '\'' +
                '}';
    }
}
