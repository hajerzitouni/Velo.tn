/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import application1.asurance;
import application1.assuranceService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Hajer
 */
public class AjouterassuranceController implements Initializable {

   
    @FXML
    private JFXTextField velo_id;
    @FXML
    private JFXTextField txt;
    @FXML
    private Button ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        
   
         try {

    
    assuranceService as = new assuranceService ();
         if(validateInputs()){
        int nbrcomp = (Integer) Integer.parseInt(velo_id.getText()) + 0;
        int nbr = (Integer) Integer.parseInt(txt.getText()) + 0;
        
       
   
         asurance a = new asurance(nbrcomp,nbr);
         as.ajouter(a);
                
        System.out.println("assurance ajouté"); 
           
            
            
        }
           
          }catch (SQLException ex) {
            Logger.getLogger(assuranceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
     public static boolean isNotInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return true;
        }

        return false;
    }
    
   private boolean validateInputs() {
        if (txt.getText().length() == 0 ||velo_id.getText().length() == 0
           ) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("tous les champs doivent etre remplis");
            alert.showAndWait();
            return false;
        } else if (isNotInteger(txt.getText())|| isNotInteger(velo_id.getText()) ){

            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText(" doivent etre des nombres");
            alert1.setHeaderText(null);
            alert1.show();
            return false;

        }
        return false;
   }
}
