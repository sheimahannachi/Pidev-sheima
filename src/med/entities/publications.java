/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package med.entities;

import java.sql.Date;
import java.time.LocalDate;

import med.entities.enumMedecin.SpecialiteMedicale;

/**
 *
 * @author ASUS
 */
public class publications {
    
    private int id ;
    private String titre;
    private String texte;
    private LocalDate dateDePublication ;
    private SpecialiteMedicale specialitéAssocié ;
    private String tags ;
    private String imagePath;
   

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

       public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

  

    public LocalDate getDateDePublication() {
        return dateDePublication;
    }

    public void setDateDePublication(LocalDate dateDePublication) {
        this.dateDePublication = dateDePublication;
    }

    public SpecialiteMedicale getSpecialitéAssocié() {
        return specialitéAssocié;
    }

    public void setSpecialitéAssocié(SpecialiteMedicale specialitéAssocié) {
        this.specialitéAssocié = specialitéAssocié;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public publications(String titre, String texte, LocalDate dateDePublication, SpecialiteMedicale specialitéAssocié, String tags, String imagePath) {
        this.titre = titre;
        this.texte = texte;
        this.dateDePublication = dateDePublication;
        this.specialitéAssocié = specialitéAssocié;
        this.tags = tags;
        this.imagePath = imagePath;
    }

    public publications() {
    }

    public publications(String titre, String texte, LocalDate dateDePublication, SpecialiteMedicale specialitéAssocié, String tags) {
        this.titre = titre;
        this.texte = texte;
        this.dateDePublication = dateDePublication;
        this.specialitéAssocié = specialitéAssocié;
        this.tags = tags;
    }

    
    
    

 
   



    public publications(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "publications{" + "titre=" + titre + ", texte=" + texte + ", dateDePublication=" + dateDePublication + ", specialit\u00e9Associ\u00e9=" + specialitéAssocié + ", tags=" + tags + ", imagePath=" + imagePath + '}';
    }

    

 
    
    

    
    
    
}
