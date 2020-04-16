/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pidev.entities.reparation;
import pidev.entities.velorepares;
import pidev.services.reparationService;
import pidev.services.veloreparesService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class reparationsController implements Initializable {

    @FXML
    private AnchorPane btnAjouter;
    @FXML
    private TextField tfnamecast;
    @FXML
    private Button btnAjout;
    @FXML
    private Button consulter;
    @FXML
    private TextField tfnamecast1;
    @FXML
    private DatePicker tfstartcomp;
    @FXML
    private DatePicker tfstartcomp1;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter2(ActionEvent event)  {
         if (validateInputs()) {
        String namecast = tfnamecast.getText();
        int namecast1 = (Integer) Integer.parseInt(tfnamecast1.getText()) + 0;
        String startcomp = tfstartcomp.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String startcomp1 = tfstartcomp1.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        reparationService rs = new reparationService();
        reparation r = new reparation(namecast,startcomp,startcomp1,namecast1);
        rs.Addreparation(r);
        System.out.println("Saha");
        try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("Afficherreparations.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
            
            
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }}

    @FXML
    private void consulter1(ActionEvent event) {
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("Afficherreparations.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
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
        if (tfnamecast.getText().length() == 0 || tfnamecast1.getText().length() == 0  ) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("tous les champs doivent etre remplis");
            alert.showAndWait();
            return false;
        } else if (isNotInteger(tfnamecast1.getText())) {

            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("type text invalide ");
            alert1.setHeaderText(null);
            alert1.show();
            return false;

        
        }
        return false;
       
      
   
       
    
}}
