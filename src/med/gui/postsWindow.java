/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package med.gui;

import java.io.IOException;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import med.entities.enumMedecin;
import med.entities.publications;
import med.services.publicationsCrud;

/**
 *
 * @author ASUS
 */
public class postsWindow extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root =FXMLLoader.load(getClass().getResource("postsMed.fxml"));
            
            
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("posts!");
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

