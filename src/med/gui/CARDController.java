/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package med.gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import med.entities.commentaire;
import med.entities.publications;
import med.services.commentaireCrud;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class CARDController implements Initializable {
    
     private UserPostsController userPostsController; // Ajoutez cette référence
         public void setUserPostsController(UserPostsController userPostsController) {
        this.userPostsController = userPostsController;
    }
         public void setTableComm(TableView<commentaire> tableComm) {
    this.tableComm = tableComm;
}
         
     @FXML
    private Button ajoutercomm1;

    @FXML
    private DatePicker dComm1;

    @FXML
    private ImageView imageView1;

    @FXML
    private Label labeltexte1;

    @FXML
    private Button modifiercomm1;

    @FXML
    private Button supprimercomm1;

    @FXML
    private Label tags1;

    @FXML
    private TextField tfcomm1;
    private publications publication;
    
   
    private ObservableList<commentaire> commentaireList;
    
    public void setObservableList(ObservableList<commentaire> commentaireList) {
        this.commentaireList = commentaireList;
    }
    
    private TableView<commentaire> tableComm;

    public void setTableView(TableView<commentaire> tableComm) {
        this.tableComm = tableComm;
    }
    public void setPublication(publications publication) {
        this.publication = publication;
        String texte = publication.getTexte();
        String tags = publication.getTags();

        System.out.println("Texte: " + texte);
        System.out.println("Tags: " + tags);
        
        StringBuilder text = new StringBuilder("- ");
        text.append(texte != null ? texte : "N/A");
        text.append("_\n");
        text.append(tags != null ? tags : "N/A");

        System.out.println("Text to be set in labeltexte1: " + text.toString());

        labeltexte1.setText(text.toString());

        String imageUrl = publication.getImagePath();

        System.out.println("Image Path: " + imageUrl);

        if (imageUrl != null) {
            Image image = new Image("file:" + imageUrl);
            imageView1.setImage(image);
        }
    }

    

        /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
          commentaireList = FXCollections.observableArrayList();
          commentaireCrud commCrud = new commentaireCrud();
        commCrud = new commentaireCrud();
       

}


    
    
private void showAlert(String title, String headerText, String contentText) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);
    alert.showAndWait();
}



    @FXML
    void ajout(ActionEvent event) {
         String contenuCommentaire1 = tfcomm1.getText();
        
          
          LocalDate dateCommentaire1 = dComm1.getValue();
            if (contenuCommentaire1.isEmpty() || dateCommentaire1 == null) {
        showAlert("Erreur", "Champ commentaire Vide", "Le champ commentaire ne peut pas être vide.");
         // Arrête la méthode
    }else {
          
         commentaire c  = new commentaire(contenuCommentaire1, dateCommentaire1);
         commentaireCrud commCrud = new commentaireCrud();
          commCrud.ajouterCommentaire(c);
          
         
           userPostsController.ajouterCommentaireATable(c);
            commentaireList.add(c);
            System.out.println("Commentaire ajouté : " + c);

           
           
    tfcomm1.clear();
     dComm1.setValue(null);
    }}

 @FXML
    void modify(ActionEvent event) {
        
     commentaire selectedCommentaire = tableComm.getSelectionModel().getSelectedItem();

    if (selectedCommentaire != null) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation Dialog");
        confirmationAlert.setHeaderText("Modify comment");
        confirmationAlert.setContentText("Are you sure you want to modify this comment?");

        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Retrieve the modified data from the input fields
            TextField contentField = new TextField(selectedCommentaire.getContenuCommentaire());
            DatePicker datePicker = new DatePicker(selectedCommentaire.getDateCommentaire());

            // Create a custom dialog for input
            DialogPane customDialog = new DialogPane();
            customDialog.setContent(new VBox(10, contentField, datePicker));

            Alert modifyAlert = new Alert(Alert.AlertType.CONFIRMATION);
            modifyAlert.setDialogPane(customDialog);
            modifyAlert.setTitle("Modify Comment");
            modifyAlert.setHeaderText("Modify the comment");
            modifyAlert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

            Optional<ButtonType> modifyResult = modifyAlert.showAndWait();

            if (modifyResult.isPresent() && modifyResult.get() == ButtonType.OK) {
                // Get the modified data from the input fields
                String newContent = contentField.getText();
                LocalDate newDate = datePicker.getValue();

                // Check if the new content is not empty
                if (!newContent.isEmpty()) {
                    selectedCommentaire.setContenuCommentaire(newContent);
                    selectedCommentaire.setDateCommentaire(newDate);
                    commentaireCrud commCrud = new commentaireCrud();
                    commCrud.modifierCommentaire(selectedCommentaire, selectedCommentaire.getId());
            // Update your table view
                    // Call the method in UserPostsController to update the table view
                    userPostsController.updateCommentInTableView(selectedCommentaire);
                } else {
                    showAlert("Erreur", "Champ commentaire Vide", "Le champ commentaire ne peut pas être vide.");
                }
            }
        }
    }
}


    @FXML
    void supprimer(ActionEvent event) {
        
    commentaire selectedCommentaire = tableComm.getSelectionModel().getSelectedItem(); // Get the selected comment
        
        if (selectedCommentaire != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Delete Publication");
            alert.setContentText("Are you sure you want to delete this publication?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
                // User confirmed deletion, call your CRUD method to delete the publication
                commentaireCrud commCrud = new commentaireCrud();
                  commCrud.supprimerCommentaire(selectedCommentaire);

                commentaireList.remove(selectedCommentaire);
                
                // Now you can remove the selected item from the TableView
               userPostsController.removeCommentFromTableView(selectedCommentaire);
        }
    }

    }}

        
        
        
        
        


