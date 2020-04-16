/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import pidev.entities.PaiementStripe;
import pidev.services.coursService;
import pidev.services.fos_userService;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

 

/**
 * FXML Controller class
 *
 * @author berrahal
 */
public class CommandePaiementController implements Initializable {

    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField numeroCarte;
    @FXML
    private JFXTextField MoisValidite;
    @FXML
    private JFXTextField AnneeValidite;
    @FXML
    private JFXPasswordField ccvTextField;
    @FXML
    private JFXButton btnValider;
    @FXML
    private JFXButton btnAnnuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void validerFunction(ActionEvent event) throws SQLException, IOException {
        
        int mois = Integer.parseInt(MoisValidite.getText());
        int annee = Integer.parseInt(AnneeValidite.getText());
        
        
        
   
           
        
        
        Token token = PaiementStripe.getToken("pk_test_AuAMdXwE57NnBcd4Xld65Ez4", numeroCarte.getText(), mois, annee, ccvTextField.getText(), email.getText());
      
            if(token !=null){
                
               
             
                
                 
                 
             

               
                 
                 
                 
            String tit = "Paiement réussi";
            String message = "Votre paiement a été traité avec succès";
          
       
      
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/cupcake/gui/homev.fxml"));
        AnchorPane root = (AnchorPane) loader.load();
       sendEmail();
        email.getScene().setRoot(root);
        
      
    }
        
    }

    @FXML
    private void AnnulerFunction(ActionEvent event) throws IOException {
       
    }
    
    public void sendEmail(){
    }
    
}
