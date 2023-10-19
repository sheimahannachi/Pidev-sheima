/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package med.gui;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;


import med.entities.publications;
import med.services.publicationsCrud;
import med.entities.enumMedecin;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PostsMedController implements Initializable {
    
  
    
    private String imagePath = null;
    
    private ObservableList<publications> publicationList;

    @FXML
    private TextField tfTitre;
  
    @FXML
    private ComboBox<enumMedecin.SpecialiteMedicale> cbSpecialite;
    @FXML
    private DatePicker dPub;
    @FXML
    private TextField tfTags;
   
   
    @FXML
    private Button bPublier;
    @FXML
    private Button bModifierPub;
    @FXML
    private Button bSupprimerPub;
    @FXML
    public TableView<publications> tablePub;
    @FXML
    private TextField tfTexte;
    
    @FXML
    private Button bimporter;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField imagePathTextField;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       ObservableList<enumMedecin.SpecialiteMedicale> specialiteList = FXCollections.observableArrayList(enumMedecin.SpecialiteMedicale.values());
        cbSpecialite.setItems(specialiteList);

      
        
       TableColumn<publications, String> titreColumn = new TableColumn<>("Titre");
        titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        
         
       TableColumn<publications, String> texteColumn = new TableColumn<>("Texte");
        texteColumn.setCellValueFactory(new PropertyValueFactory<>("texte"));
        
       

     
        TableColumn<publications, LocalDate> dateDePublicationColumn = new TableColumn<>("Date de Publication");
        dateDePublicationColumn.setCellValueFactory(new PropertyValueFactory<>("dateDePublication"));

        TableColumn<publications, enumMedecin.SpecialiteMedicale> specialitéAssociéColumn = new TableColumn<>("Spécialité Associée");
        specialitéAssociéColumn.setCellValueFactory(new PropertyValueFactory<>("specialitéAssocié"));

        TableColumn<publications, String> tagsColumn = new TableColumn<>("Tags");
        tagsColumn.setCellValueFactory(new PropertyValueFactory<>("tags"));
      
       TableColumn<publications, String> imagePathColumn = new TableColumn<>("image path");
        imagePathColumn.setCellValueFactory(new PropertyValueFactory<>("imagePath"));
       

        tablePub.getColumns().addAll(titreColumn, texteColumn, dateDePublicationColumn, specialitéAssociéColumn, tagsColumn,imagePathColumn);

           
        
     publicationList = FXCollections.observableArrayList();
        tablePub.setItems(publicationList);
        
        
    }    
private void showAlert(String title, String headerText, String contentText) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);
    alert.showAndWait();
}

    @FXML
    private void ajoutPub(ActionEvent event) {
            
        String titre = tfTitre.getText();
         String texte = tfTexte.getText();
        enumMedecin.SpecialiteMedicale specialitéAssocié = cbSpecialite.getValue();
        
        String tags = tfTags.getText();
      
       
        LocalDate dateDePublication = dPub.getValue();
          // Vérification du champ titre
    if (titre.isEmpty()) {
        showAlert("Erreur", "Champ Titre Vide", "Le champ titre ne peut pas être vide.");
        return; // Arrête la méthode
    }

    // Vérification du champ texte
    if (texte.isEmpty()) {
        showAlert("Erreur", "Champ Texte Vide", "Le champ texte ne peut pas être vide.");
        return; // Arrête la méthode
    }

    // Vérification du champ tags
    if (!tags.matches("^#\\S+$")) {
        showAlert("Erreur", "Format de Tags Incorrect", "Les tags doivent commencer par '#' et ne pas contenir d'espaces.");
        return; // Arrête la méthode
    }
       
   
    publications P = new publications(titre, texte, dateDePublication, specialitéAssocié, tags);
    String imagePath = imagePathTextField.getText();
    P.setImagePath(imagePath);
    
    publicationsCrud pub = new publicationsCrud();
    pub.ajouterpublications(P);

    publicationList.add(P);
    
    tfTitre.clear();
    tfTexte.clear();
    dPub.setValue(null);
    cbSpecialite.setValue(null);
    tfTags.clear();
    imageView.setImage(null);
    imagePathTextField.clear();
}      
    

    @FXML
    private void ModifierPub(ActionEvent event) {
        publications selectedPublication = tablePub.getSelectionModel().getSelectedItem();
    
    if (selectedPublication != null) {
        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation Dialog");
        confirmationAlert.setHeaderText("Modify Publication");
        confirmationAlert.setContentText("Are you sure you want to modify this publication?");
        
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        
        if (result.get() == ButtonType.OK) {
            // Retrieve the modified data from the input fields
            String titre = tfTitre.getText();
            String texte = tfTexte.getText();
            LocalDate dateDePublication = dPub.getValue();
            enumMedecin.SpecialiteMedicale specialitéAssocié = cbSpecialite.getValue();
            String tags = tfTags.getText();
            

            // Update the selected publication directly in the TableView
            selectedPublication.setTitre(titre);
            selectedPublication.setTexte(texte);
            selectedPublication.setDateDePublication(dateDePublication);
            selectedPublication.setSpecialitéAssocié(specialitéAssocié);
            selectedPublication.setTags(tags);
            
            
             if (tfTitre.getText().isEmpty()) {
        showAlert("Erreur", "Champ Titre Vide", "Le champ titre ne peut pas être vide.");
        return; // Arrête la méthode
    }

    // Vérification du champ texte
    if (tfTexte.getText().isEmpty()) {
        showAlert("Erreur", "Champ Texte Vide", "Le champ texte ne peut pas être vide.");
        return; // Arrête la méthode
    }

    // Vérification du champ tags
    if (!tfTags.getText().matches("^#\\S+$")) {
        showAlert("Erreur", "Format de Tags Incorrect", "Les tags doivent commencer par '#' et ne pas contenir d'espaces.");
        return; // Arrête la méthode
    }

            // Update the database with the modified data using your CRUD method
            publicationsCrud pub = new publicationsCrud();
            pub.modifierpublications(selectedPublication, selectedPublication.getId());

            // Clear the input fields after the modification
            tfTitre.clear();
            tfTexte.clear();
            dPub.setValue(null);
            cbSpecialite.setValue(null);
            tfTags.clear();
            

            // Refresh the TableView to see the modifications
            tablePub.refresh();
        }
   
            
            
      
    }
        
        
        
        
    }

    @FXML
    private void SupprimerPub(ActionEvent event) {
        publications selectedPublication = tablePub.getSelectionModel().getSelectedItem();
        
        if (selectedPublication != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Delete Publication");
            alert.setContentText("Are you sure you want to delete this publication?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                // User confirmed deletion, call your CRUD method to delete the publication
                publicationsCrud publicationCrud = new publicationsCrud();
                publicationCrud.supprimerpublications(selectedPublication);
                // Now you can remove the selected item from the TableView
                tablePub.getItems().remove(selectedPublication);
            }
        }
    }
    

    @FXML
    private void parcourirImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
    
    // Show the file chooser dialog
    File selectedFile = fileChooser.showOpenDialog(null);

    if (selectedFile != null) {
        // Load the selected image and set it in the ImageView
        Image image = new Image(selectedFile.toURI().toString());
        imageView.setImage(image);
        
        String imagePath = selectedFile.getAbsolutePath();
        imagePathTextField.setText(imagePath);
        
        
        publicationsCrud pub = new publicationsCrud();
        publications selectedPublication = tablePub.getSelectionModel().getSelectedItem();
        if (selectedPublication != null) {
            imagePathTextField.setText(selectedFile.getAbsolutePath());
            pub.modifierpublications(selectedPublication, selectedPublication.getId());
        }
    } else {
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Annulation de la sélection");
        alert.setHeaderText(null);
        alert.setContentText("Vous avez annulé la sélection du fichier. Continuer ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User chose to continue
            // You can add additional handling if needed
        } else {
            // User canceled the action
            // You can add additional handling or do nothing
        }
       
    }
    }

    
}