/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.stripe.model.Token;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pidev.entities.PaiementStripe;
import pidev.entities.cours;
import pidev.entities.fos_user;
import pidev.entities.paiement;
import pidev.pidev;
import static pidev.pidev.fos_user;
import static pidev.pidev.user_id;
import pidev.services.coursService;
import pidev.services.fos_userService;
import pidev.services.paiementService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class payercoursController implements Initializable {

   @FXML
    private AnchorPane avr;
    @FXML
    private TableView<cours> table;
    @FXML
    private TableColumn<cours, Integer> C1;
    @FXML
    private TableColumn<cours, String> C2;
    @FXML
    private TableColumn<cours, String> C3;
    @FXML
    private TableColumn<cours, Integer> C4;
    @FXML
    private AnchorPane Fields;

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField age;
    @FXML
    private JFXButton Back;
    @FXML
    private JFXButton payer;
   
           

   coursService cs=new coursService();
   paiementService ps =new paiementService();
   fos_userService us =new fos_userService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<cours> cours = FXCollections.observableArrayList();
     try {
        
         for(cours c: cs.FindAllcours())
             cours.add(c);
         C1.setCellValueFactory(new PropertyValueFactory<>("niveau"));
         C2.setCellValueFactory(new PropertyValueFactory<>("date_cours"));
         C3.setCellValueFactory(new PropertyValueFactory<>("lieu_cours"));

         C4.setCellValueFactory(new PropertyValueFactory<>("prixcours"));
         //id.setCellValueFactory(new PropertyValueFactory<>("id")); 
  
       
     
     } catch (SQLException ex) {
         Logger.getLogger(coursService.class.getName()).log(Level.SEVERE, null, ex);
     } 
       
        table.setItems(cours);
        
    
    }    
      
    

   
      
   
    

    public static boolean isNotInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return true;
        }

        return false;
    }
       
    @FXML
    private void Back(ActionEvent event) {
    }

    @FXML
    private void payer(ActionEvent event) throws SQLException, IOException {
          if (validateInputs()) {
        
         cours selected = table.getSelectionModel().getSelectedItem();
         
        if (!table.getSelectionModel().getSelectedItems().isEmpty()) {
        
               
            int cour_id = table.getSelectionModel().getSelectedItem().getId();
            System.out.println(cour_id);
          
         
            user_id = pidev.user_id;
        
            String nomc = nom.getText();
               String pnom = prenom.getText();
            
          int ages = (Integer) Integer.parseInt(age.getText()) + 0;
          
           fos_userService us = new fos_userService();
           
           
 
           paiementService ps = new paiementService();
            paiement p = new paiement( nomc, pnom, ages,cour_id,user_id);
            coursService cs = new coursService();
          
         cs.increment_nb(cour_id);
           // int fos_user.setSolde(fos_user.getSolde())--(table.getSelectionModel().getSelectedItem().getPrixcours());
            ps.Addpaiement(p);
            
            System.out.println("Paiement effectué");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            
          
            table.setItems(cs.FindAllcours());
             try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("paiement.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
            cours c=new cours();
           
          
            System.out.println(c.getNb());
          
          
             
             
         
            
            
           
             
        
        }
             catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
         else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("No element selected");
            alert.showAndWait();

        }}}
        
    private boolean validateInputs() {
        if (nom.getText().length() == 0 || prenom.getText().length() == 0 || age.getText().length() == 0 ) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("tous les champs doivent etre remplis");
            alert.showAndWait();
            return false;
        } else if (isNotInteger(age.getText())) {

            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veuillez vérifier l'âge");
            alert1.setHeaderText(null);
            alert1.show();
            return false;

        
        }
       
        

        return true;
    }
    }
    

     
