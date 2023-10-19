/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package med.utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class connexionPublications {
       public String url="jdbc:mysql://localhost:3306/publicationsbd";
    public String login="root";
    public String pwd="";
    Connection cnx ;
    public static connexionPublications instance; 
    
    private connexionPublications(){
        try {
        cnx = DriverManager.getConnection(url, login, pwd);
        System.out.println("Connexion etablie !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     public Connection getCnx(){
         return cnx;
         
     }
     public static connexionPublications getInstance() {
         if (instance==null){
             instance=new connexionPublications();
     }
     return instance;
     } 
    
}
