/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import application1.asurance;
import application1.assuranceService;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hajer
 */
public class ShowassuranceController implements Initializable {

    @FXML
    private TableView<asurance> table;
    @FXML
    private TableColumn<?, ?> velo;
    @FXML
    private TableColumn<?, ?> montant;
    @FXML
    private Button Add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<asurance> assurance = FXCollections.observableArrayList();
     try {
         assuranceService as = new assuranceService();
        
         for(asurance  a: as.getAllassurance())
         assurance.add(a);
        velo.setCellValueFactory(new PropertyValueFactory<>("velo_id"));
         montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
         
     } catch (SQLException ex) {
         Logger.getLogger(assuranceService.class.getName()).log(Level.SEVERE, null, ex);
     }

       
        table.setItems(assurance);
        
    
    }    
     public void load() {
        ObservableList<asurance> assurance = FXCollections.observableArrayList();
     try {
         assuranceService as = new assuranceService();
        
         for(asurance  a: as.getAllassurance())
         assurance.add(a);
        velo.setCellValueFactory(new PropertyValueFactory<>("velo_id"));
         montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
         
     } catch (SQLException ex) {
         Logger.getLogger(assuranceService.class.getName()).log(Level.SEVERE, null, ex);
     }

       
        table.setItems(assurance);
        
    
    }    
       

    @FXML
    private void ajouter(ActionEvent event) {
try {
  javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("addassurance.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();}
           catch (IOException ex) {
        System.out.println(ex.getMessage());
        }
    
    }

    @FXML
    private void update(ActionEvent event) {
        try {
  javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("modifierassurance.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
        }
         
      catch (IOException ex) {
        System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
         if(!table.getSelectionModel().getSelectedItems().isEmpty()){
           assuranceService as = new assuranceService();
           as.delete(table.getSelectionModel().getSelectedItems().get(0).getId());
           load();
       
        }
       else{
           
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("aucun élément 'a ètè séléctionné");
        alert.showAndWait();

           
        
       
    }
    }

    @FXML
    private void retour(ActionEvent event){
         
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/gui/homev.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
    
