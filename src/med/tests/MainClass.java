/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package med.tests;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import med.entities.Medecin;
import med.entities.commentaire;

import static med.entities.enumMedecin.SpecialiteMedicale.NEUROLOGUE;
import static med.entities.enumMedecin.SpecialiteMedicale.PSYCHIATRE;
import med.entities.publications;
import med.services.commentaireCrud;

import med.services.publicationsCrud;
import med.utiles.connexionPublications;


/**
 *
 * @author ASUS
 */
public class MainClass {
    public static void main (String[] args){
       // MyConnexion mc = new MyConnexion();
        Connection preparedStatement2 = connexionPublications.getInstance().getCnx();
        
       //System.out.println(md.hashCode()+"_"+md2.hashCode());
        Medecin M2 = new Medecin("hammeda", "amine", "tunis", 55711196,"amine.hammeda@esprit.tn","shamchoum",PSYCHIATRE);
        //Medecin M4 = new Medecin(5);
       
      //  System.out.println(medecinCrud.afficherMedecins());
        // medecinCrud.supprimerMedecin(M4);
          //medecinCrud.modifierMedecin(M2,11);
       publicationsCrud publication = new publicationsCrud();
       commentaireCrud commentaire = new commentaireCrud();
         LocalDate date = LocalDate.parse("2023-12-10"); 
       // publications p1 = new publications ("neurologie","hello!", date, NEUROLOGUE,"neurologie","\"C:\\Users\\ASUS\\Desktop\\téléchargement (1).jpg\"");
        commentaire c1= new commentaire ("genial!",date);
        commentaire c2 =new commentaire (2);
        commentaire.supprimerCommentaire(c2);
       // commentaire.ajouterCommentaire(c1);
       // publication.ajouterpublications(p1);
        //commentaire.modifierCommentaire(c1, 1);
       //publications p1 = new publications (2);
      // publication.supprimerpublications(p1);
        //publication.modifierpublications(p1, 2);
      

        
        
    }

   
         
           
      
    
}
