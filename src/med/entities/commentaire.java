/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package med.entities;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author ASUS
 */
public class commentaire {
    private int id ;
    private String contenuCommentaire ;
    private LocalDate dateCommentaire ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenuCommentaire() {
        return contenuCommentaire;
    }

    public void setContenuCommentaire(String contenuCommentaire) {
        this.contenuCommentaire = contenuCommentaire;
    }

    public LocalDate getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(LocalDate dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }

    public commentaire( String contenuCommentaire, LocalDate dateCommentaire) {
        
        this.contenuCommentaire = contenuCommentaire;
        this.dateCommentaire = dateCommentaire;
    }

    public commentaire() {
    }

    public commentaire(int id) {
        this.id = id;
    }
    
    

    @Override
    public String toString() {
        return "commentaire{" + "id=" + id + ", contenuCommentaire=" + contenuCommentaire + ", dateCommentaire=" + dateCommentaire + '}';
    }
    
    
    
    
}
