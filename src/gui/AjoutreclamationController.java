/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import application1.reclamation;
import application1.reclamationService;
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
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Hajer
 */
public class AjoutreclamationController implements Initializable {

    @FXML
    private TextArea description;
    @FXML
    private TextArea titre;
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

    
       reclamationService rs = new reclamationService ();
         if(validateInputs()){
          String namecomp= description.getText();
             String namecomp1= titre.getText();
       
   
           reclamation r = new reclamation (namecomp,namecomp1);
            
           rs.ajouter(r);
              
        System.out.println("reclamation ajouté"); 
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Merci pour votre reclamation cher client !");
            alert.showAndWait();
            
            
        }
           
          }catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
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
        if (titre.getText().length() == 0 || titre.getText().length() == 0
                || description.getText().length() == 0) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("tous les champs doivent etre remplis");
            alert.showAndWait();
            return false;
        } 

        
       
        

        return true;
    }
   
}

    
    

