/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package med.gui;

import java.io.IOException;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import med.entities.publications;
import med.services.commentaireCrud;
import med.services.publicationsCrud;
import med.utiles.connexionPublications;

/**
 *
 * @author ASUS
 */
public class userMedWindow extends Application {
    
  
    
    @Override
    public void start(Stage primaryStage) {
        try {
            connexionPublications.getInstance();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userPosts.fxml"));
            Parent root = loader.load();
            UserPostsController userPostsController = loader.getController();

            // Set the postContainer for UserPostsController
            VBox postContainer = new VBox(); // Create a VBox
            userPostsController.setPostContainer(postContainer);
             userPostsController.displayCards();
        
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("HELLO");
        primaryStage.setScene(scene);
        primaryStage.show();
     } catch (IOException ex) {
            System.out.println(ex.getMessage());
            
        
        }
    }
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
