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
import med.entities.commentaire;
import med.utiles.connexionPublications;

/**
 *
 * @author ASUS
 */
public class commentaireCrud {
    
    connexionPublications myc =connexionPublications.getInstance();
        Connection cnx  = myc.getCnx();
        
        
        public void ajouterCommentaire(commentaire c){
        String requete1 ="INSERT INTO commentaire(contenuCommentaire,dateCommentaire)"
                +"VALUES(?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete1);
            pst.setString(1, c.getContenuCommentaire());
             
               pst.setDate(2, Date.valueOf(c.getDateCommentaire()));
              
               pst.executeUpdate();
               System.out.println("Votre commentaire est ajouté");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
            
        }}

        public void supprimerCommentaire(commentaire c ){
        String requete2 ="DELETE FROM commentaire WHERE id=?";
               
        try {
            PreparedStatement pst = cnx.prepareStatement(requete2);
               pst.setInt(1, c.getId());
               pst.executeUpdate();
               System.out.println("Votre commentaire est supprimé");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }}
public void modifierCommentaire(commentaire c,int id){
        String requete3 ="UPDATE commentaire SET contenuCommentaire=?,dateCommentaire=? WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete3);
            
           pst.setString(1, c.getContenuCommentaire());
             
              pst.setDate(2, Date.valueOf(c.getDateCommentaire()));
             
               pst.setInt(3, id);
               pst.executeUpdate();
               System.out.println("Votre commentaire est modifié");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }}

         public List<commentaire>afficherCommentaires(){
         List<commentaire> myList=new ArrayList<>();
        try {
           
            String requete4="SELECT * FROM commentaire";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete4);
            while(rs.next()){
            commentaire c  = new commentaire ();
            
            c.setContenuCommentaire(rs.getString("contenuCommentaire"));
             
           
           c.setDateCommentaire(rs.getDate("dateCommentaire").toLocalDate());

           
            myList.add(c);  
        }
              
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
        return myList;  
    }
    

}

    

