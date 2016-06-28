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

    //@Column(nullable = false) added if user wants to add it
    String password;

    @Column(nullable = false)
    Boolean forever;

    //@Column(nullable = false) this can be left out
    String comment;

    public AnonFile() {
    }

    public AnonFile(String originalFilename, String realFilename, String password, Boolean forever, String comment) {
        this.originalFilename = originalFilename;
        this.realFilename = realFilename;
        this.password = password;
        this.forever = forever;
    }

    public AnonFile(String originalFilename, String realFilename, Boolean forever, String comment) {
        this.originalFilename = originalFilename;
        this.realFilename = realFilename;
        this.password = password;
        this.forever = forever;
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

    public Boolean getForever() {
        return forever;
    }

    public void setForever(Boolean forever) {
        this.forever = forever;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
