package com.example.entities;

import javax.persistence.*;

/**
 * Created by hoseasandstrom on 6/27/16.
 */
@Entity
@Table(name = "files")
public class AnonFile {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String originalFilename;

    @Column(nullable = false)
    String realFilename;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    String comment;

    public AnonFile() {
    }

    public AnonFile(String originalFilename, String realFilename) {
        this.originalFilename = originalFilename;
        this.realFilename = realFilename;
    }

    public AnonFile(String originalFilename, String realFilename, String password) {
        this.originalFilename = originalFilename;
        this.realFilename = realFilename;
        this.password = password;
    }

    public AnonFile(int id, String originalFilename, String realFilename, String password, String comment) {
        this.id = id;
        this.originalFilename = originalFilename;
        this.realFilename = realFilename;
        this.password = password;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getRealFilename() {
        return realFilename;
    }

    public void setRealFilename(String realFilename) {
        this.realFilename = realFilename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
