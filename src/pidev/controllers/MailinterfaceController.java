/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import pidev.services.MailService;
import pidev.services.Upload;

/**
 * FXML Controller class
 *
 * @author Manel 
 */
public class MailinterfaceController implements Initializable {

    @FXML
    private JFXTextField destinataire;
    @FXML
    private JFXTextField objet;
    @FXML
    private JFXButton envoyermail;
    @FXML
    private TextArea corpdumail;
    @FXML
    private Label image ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void EnvoyerMail(ActionEvent event) {
        try {
            MailService ml = new MailService();
            String img ="C:\\wamp\\www\\pidev_v1\\web\\images\\" + image.getText();

            ml.envoyer( destinataire.getText(), objet.getText(),corpdumail.getText(),img);
            
            
            
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Mail envoyé aves succé! ");
        alert.show();
    
        } catch (IOException ex) {
            Logger.getLogger(MailinterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
      @FXML
      private void importerimage(ActionEvent event)  throws IOException {
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
    
}
