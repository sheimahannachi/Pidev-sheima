/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package med.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import med.entities.Medecin;

import med.entities.enumMedecin;
import med.entities.publications;

import med.utiles.connexionPublications;




/**
 *
 * @author ASUS
 */
public class publicationsCrud {
    connexionPublications myc =connexionPublications.getInstance();
        Connection cnx  = myc.getCnx();
        
        
        public void ajouterpublications(publications P){
        String requete1 ="INSERT INTO publications(titre,texte,dateDePublication,specialitéAssocié,tags, imagePath)"
                +"VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete1);
            pst.setString(1, P.getTitre());
             pst.setString(2, P.getTexte());
               pst.setDate(3, Date.valueOf(P.getDateDePublication()));
               pst.setString(4, P.getSpecialitéAssocié().name());
               pst.setString(5, P.getTags());
                pst.setString(6, P.getImagePath());
               System.out.println( P.getImagePath());
               pst.executeUpdate();
               System.out.println("Votre publication est ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
            
        }}

        public void supprimerpublications(publications p){
        String requete2 ="DELETE FROM publications WHERE id=?";
               
        try {
            PreparedStatement pst = cnx.prepareStatement(requete2);
               pst.setInt(1, p.getId());
               pst.executeUpdate();
               System.out.println("Votre publication est supprimée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }}
public void modifierpublications(publications p,int id){
        String requete3 ="UPDATE publications SET titre=?,texte=?,dateDePublication=?,specialitéAssocié=?,tags=?, imagePath=? WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete3);
            
           pst.setString(1, p.getTitre());
             pst.setString(2, p.getTexte());
              pst.setDate(3, Date.valueOf(p.getDateDePublication()));
               pst.setString(4, p.getSpecialitéAssocié().name());
               pst.setString(5, p.getTags());
                pst.setString(6, p.getImagePath());
              
               pst.setInt(7, id);
               pst.executeUpdate();
               System.out.println("Votre publication est modifiée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }}

         public List<publications>afficherpublications(){
         List<publications> myList=new ArrayList<>();
        try {
           
            String requete4="SELECT * FROM publications";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete4);
            while(rs.next()){
            publications P = new publications ();
            
            P.setTitre(rs.getString("titre"));
             P.setTexte(rs.getString("texte"));
           
           P.setDateDePublication(rs.getDate("dateDePublication").toLocalDate());

            P.setTags(rs.getString("tags"));
            P.setImagePath(rs.getString("imagePath"));
            
              String specialiteString = rs.getString("specialitéAssocié");
            P.setSpecialitéAssocié(enumMedecin.SpecialiteMedicale.valueOf(specialiteString));
            myList.add(P);  
        }
              
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
        return myList;  
    }
    








}










        










