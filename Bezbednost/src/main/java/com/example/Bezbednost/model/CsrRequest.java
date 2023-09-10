package com.example.Bezbednost.model;



import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="csr_request")
@Data

public class CsrRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Use AUTO or SEQUENCE
    @Column(nullable = false, updatable = false)
    private String email;
    private String firstName;
    private String lastName;
    private LocalDateTime timestamp;

    public CsrRequest() {

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public CsrRequest(String email, String firstName, String lastName, LocalDateTime timestamp) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.timestamp = timestamp;
    }
}