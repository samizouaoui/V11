package com.example.admin.v1;

/**
 * Created by Admin on 03/12/2018.
 */

public class user {
    String prenom;
    String nom;
    String email;
    String mp;

    public user() {

    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public String getMp() {
        return mp;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMp(String mp) {
        this.mp = mp;
    }
}
