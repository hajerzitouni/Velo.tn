/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import pidev.pidev;
import pidev.entities.Event;
import pidev.services.EventService;
import pidev.services.Upload;

/**
 * FXML Controller class
 *
 * @author Manel
 */
public class ModifiereventController implements Initializable {

    @FXML
    private Button annulerevent;
    @FXML
    private Button enregistrerevent;
    @FXML
    private JFXDatePicker date_debe;
    @FXML
    private JFXDatePicker date_fine;
    @FXML
    private JFXTextArea lieu;
    @FXML
    private JFXTextArea nom;
    @FXML
    private JFXTextArea nbparticipents;
    @FXML
    private JFXTextArea prix;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXButton importer_image;
         
    @FXML
    private AnchorPane holderPane;  
    @FXML
    private Label image;
    public static int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EventService evt = new EventService();
       Event e = evt.affichereventparid(id);
        nom.setText(e.getNomevet());
        lieu.setText(e.getLieuevt());
        prix.setText(Integer.toString(e.getPrixe()));
        description.setText(e.getDescription());
        nbparticipents.setText(Integer.toString(e.getNbparticipent()));
        date_debe.setValue(e.getDate_debe().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        date_fine.setValue(e.getDate_fine().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        image.setText(e.getNom_image());
    }    

    @FXML
    private void annuler_new_event(ActionEvent event) throws IOException {
              if(pidev.fos_user.getRoles().contains("ROLE_ADMIN")){
                AnchorPane page1 = FXMLLoader.load(getClass().getResource("/pidev/gui/ListerEventAdmin.fxml"));
                        setNode(page1);

        }
        else {
            AnchorPane page1 = FXMLLoader.load(getClass().getResource("/pidev/gui/AllEvents.fxml"));
        setNode(page1);

        }

    }
            public void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1000));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

            
            
            
            
    @FXML
    private void importerimage(ActionEvent event) throws IOException {
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            Upload u = new Upload();
            u.upload(selectedFile);
            image.setText(selectedFile.getName());

        }
    }

    @FXML
    private void enregistrerModification(ActionEvent event) {
           if (validerChamps() ){
           
             LocalDate local_fine = date_fine.getValue();
       Instant instant_fine = Instant.from(local_fine.atStartOfDay(ZoneId.systemDefault()));
       Date d_fine = Date.from(instant_fine);
       
       LocalDate local_debe = date_debe.getValue();
       Instant instant_debe = Instant.from(local_debe.atStartOfDay(ZoneId.systemDefault()));
       Date d_debe = Date.from(instant_debe);
       
                Event u = new Event (
                nom.getText(),
                d_debe,
                d_fine,
                lieu.getText(),
                description.getText(),
                Integer.parseInt(nbparticipents.getText()),  
                Integer.parseInt(prix.getText()),
                image.getText(),
                        pidev.user_id,
                        0,
                        true
                        
               
                 );
                
                
    EventService su = new EventService();
        su.modifier(u,id);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("evennement modifié avec succé!");
        alert.show();
    
        }
    
    }
    
    
    private boolean validerChamps() 
        {    
        Alert alert = new Alert(Alert.AlertType.WARNING);
       
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Erreur de saisie");
         if (nom.getText().isEmpty()) {
            alert.setContentText("Veuillez Saisir le  nom de l'evennement");
            alert.showAndWait();
            return false;
            
        } 
         else if (lieu.getText().isEmpty()) {
            alert.setContentText("Veuillez Saisir le lieu de l'eenement ");
            alert.showAndWait();
            
            return false;
        } 
            else if (description.getText().isEmpty()) {
            alert.setContentText("Veuillez Saisir la description de l'evenement ");
            alert.showAndWait();
            
            return false;
        } 
            else if (image.getText().isEmpty()) {
            alert.setContentText("Veuillez choisir une image  ");
            alert.showAndWait();
            
            return false;
        }
            else if (nbparticipents.getText().isEmpty()) {
            alert.setContentText("Veuillez saisir le nombre de participants  ");
            alert.showAndWait();
            
            return false;
        }   else if (Integer.parseInt(nbparticipents.getText()) <= 0) {
            alert.setContentText("Veuillez saisir le nombre de participants  ");
            alert.showAndWait();
            
            return false;
        }
               else if (prix.getText().isEmpty()) {
            alert.setContentText("Veuillez saisir un  prix positive   ");
            alert.showAndWait();
            
            return false;
        }
       else if (Integer.parseInt(prix.getText()) < 0) {
            alert.setContentText("Veuillez saisir un  prix positive   ");
            alert.showAndWait();
            
            return false;
        }
            
        else if (date_debe.getValue().getDayOfMonth() > date_fine.getValue().getDayOfMonth() && date_debe.getValue().getMonthValue() == date_fine.getValue().getMonthValue() && date_debe.getValue().getYear() == date_fine.getValue().getYear()) {
            alert.setContentText("Veuillez verifier le jour  ");
            alert.showAndWait();
            
            return false;
        }
                else if ( date_debe.getValue().getMonthValue() > date_fine.getValue().getMonthValue() && date_debe.getValue().getYear() == date_fine.getValue().getYear()) {
            alert.setContentText("Veuillez verfier le mois ");
            alert.showAndWait();
            
            return false;
        }
                         else if ( date_debe.getValue().getYear() > date_fine.getValue().getYear()) {
            alert.setContentText("Veuillez verifier l'annee  ");
            alert.showAndWait();
            
            return false;
        }
                else {
            return true;
        }
        
        
        }
        
    
}
