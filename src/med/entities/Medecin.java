/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package med.entities;

import med.entities.enumMedecin.SpecialiteMedicale;

/**
 *
 * @author ASUS
 */

public class Medecin {
    private int id ;
    private String nom ;
    private String prenom ; 
    private String adresse ;
    private int telephone ;
    private String email;
    private String mdp;
    private SpecialiteMedicale specialité;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public SpecialiteMedicale getSpecialité() {
        return specialité;
    }

    public void setSpecialité(SpecialiteMedicale specialité) {
        this.specialité = specialité;
    }

    public Medecin(String nom, String prenom, String adresse, int telephone, String email, String mdp, SpecialiteMedicale specialité) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.mdp = mdp;
        this.specialité = specialité;
    }

    public Medecin() {
    }

    @Override
    public String toString() {
        return "Medecin{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", telephone=" + telephone + ", email=" + email + ", mdp=" + mdp + ", specialit\u00e9=" + specialité + '}';
    }
    
}


