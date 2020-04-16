/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import application1.assuranceService;
import application1.asurance;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hajer
 */
public class ModifierassuranceController implements Initializable {

    @FXML
    private Button modifier;
    @FXML
    private TextField nom;
    @FXML
    private TextField montant;
    @FXML
    private Button retour;
    @FXML
    private TableView<asurance> table;
    @FXML
    private TableColumn<?, ?> veloi;
    @FXML
    private TableColumn<?, ?> montan;

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
        veloi.setCellValueFactory(new PropertyValueFactory<>("velo_id"));
         montan.setCellValueFactory(new PropertyValueFactory<>("montant"));
         
     } catch (SQLException ex) {
         Logger.getLogger(assuranceService.class.getName()).log(Level.SEVERE, null, ex);
     }

       
        table.setItems(assurance);
        
    
    }    
     @FXML
    void select() {
     
        asurance selected = table.getSelectionModel().getSelectedItem();
          if (!table.getSelectionModel().getSelectedItems().isEmpty()) {
        
      
           selected.setVelo_id(Integer.parseInt(nom.getText()));
                
              selected.setMontant(Integer.parseInt(montant.getText()));
        
          } else {
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("aucun élément 'a ètè séléctionné");
            alert.showAndWait();
          }
    }  

    @FXML
    private void modifier(ActionEvent event) {
        asurance a = new asurance ();
        assuranceService as = new assuranceService();
        asurance a2 = table.getSelectionModel().getSelectedItem();
        int id_ev= a2.getId();
        asurance a3 = new asurance (id_ev,Integer.parseInt(nom.getText()),Integer.parseInt(montant.getText()));
        as.modifier(id_ev, a3);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("!!! Modification avec success !!!");
        alert.showAndWait();
        
        
    }

    @FXML
    private void retour(ActionEvent event){
         
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/gui/showassurance.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
}
