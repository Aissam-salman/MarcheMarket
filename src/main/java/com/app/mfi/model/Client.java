package com.app.mfi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
public class Client {
    private @Id
    @GeneratedValue Long id;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @Email
    private String email;
    private String passwordHash;


    public Client(String firstname, String lastname, String email, String passwordHash) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.passwordHash = passwordHash;
    }
    public Client() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public @NotBlank String getFirstname() {
        return firstname;
    }

    public void setFirstname(@NotBlank String firstname) {
        this.firstname = firstname;
    }

    public @NotBlank String getLastname() {
        return lastname;
    }

    public void setLastname(@NotBlank String lastname) {
        this.lastname = lastname;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstname, this.lastname, this.email, this.passwordHash);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(!(obj instanceof Client)) return false;
        Client cli = (Client) obj;
        return Objects.equals(id, cli.id) && Objects.equals(firstname, cli.firstname) && Objects.equals(lastname,
                cli.lastname) && Objects.equals(email, cli.email) && Objects.equals(passwordHash, cli.passwordHash);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}
