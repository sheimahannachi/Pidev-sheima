/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package med.gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import med.entities.commentaire;
import med.entities.enumMedecin;
import med.entities.publications;
import med.services.commentaireCrud;
import med.services.publicationsCrud;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class UserPostsController implements Initializable {
    private ObservableList<commentaire> commentaireList;
private CARDController cardController;
     private commentaireCrud commCrud;
     public void setCARDController(CARDController cardController) {
        this.cardController = cardController;
    }
    
    @FXML
    private TableView<commentaire> tableComm;
    
    @FXML
    private VBox postContainer;
    public void setPostContainer(VBox postContainer) {
        this.postContainer = postContainer;
    
    }
    public void updateCommentInTableView(commentaire updatedCommentaire) {
        // Find the comment in the table view and update it
        int index = commentaireList.indexOf(updatedCommentaire);
        if (index >= 0) {
            commentaireList.set(index, updatedCommentaire);
            commCrud.modifierCommentaire(updatedCommentaire, index);
        }
    }
    public void removeCommentFromTableView(commentaire deletedCommentaire) {
        commentaireList.remove(deletedCommentaire);
        commCrud.supprimerCommentaire(deletedCommentaire);
    }

   
       public void setTableView(TableView<commentaire> tableComm) {
        this.tableComm = tableComm;}
    // Make sure this is correctly associated with the FXML

  public void ajouterCommentaireATable(commentaire c) {
    commentaireList.add(c);
    commCrud.ajouterCommentaire(c);
}  
    
    
  

    /**
     * Initializes the controller class.
     */
       
public void displayCards() {
    // You can use FXMLLoader to load the CARD.fxml and create a new scene
    // Then, add the scene to a Stage to display the cards
    try {
        publicationsCrud publicationCRUDInstance = new publicationsCrud();
        
        List<publications> publications = publicationCRUDInstance.afficherpublications();
        for (publications publication : publications) {
            if (publication != null && publication.getDateDePublication() != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("CARD.fxml"));
                    Node postCard = loader.load();
                    CARDController cardController = loader.getController();
                    cardController.setPublication(publication); // Fixed the method name here
                    cardController.setTableComm(tableComm);
                    postContainer.getChildren().add(postCard);
                     cardController.setUserPostsController(this);
                } catch (IOException e) {
                    e.printStackTrace();
                    // Handle the exception as needed
                }
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        // Handle any exceptions that might occur while displaying cards
    }
}





    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

         TableColumn<commentaire, String> contenuCommentaireColumn = new TableColumn<>("contenu commentaire");
        contenuCommentaireColumn.setCellValueFactory(new PropertyValueFactory<>("contenuCommentaire"));
        
        
        TableColumn<commentaire, LocalDate> dateCommentaireColumn = new TableColumn<>("Date de commentaire");
        dateCommentaireColumn.setCellValueFactory(new PropertyValueFactory<>("dateCommentaire"));
        
        tableComm.getColumns().addAll(contenuCommentaireColumn, dateCommentaireColumn);
        
        commentaireList = FXCollections.observableArrayList();
        tableComm.setItems(commentaireList);
        
         commCrud = new commentaireCrud();
         
         
        loadDataIntoTable();
          }  
        
        void loadDataIntoTable() {
        if (commentaireList != null) {
            commentaireList.clear();
            commentaireList.addAll(commCrud.afficherCommentaires());
        }
   displayCards(); }  }  
