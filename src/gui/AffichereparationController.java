/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import pidev.entities.reparation;
import pidev.entities.velorepares;
import pidev.services.reparationService;
import pidev.services.veloreparesService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AffichereparationController implements Initializable {

    @FXML
    private TableView<velorepares> table;
    veloreparesService cs =new veloreparesService();
    @FXML
    private TableColumn<velorepares, String> namecomp;
    @FXML
    private TableColumn<velorepares, String> start;
    @FXML
    private TableColumn<velorepares, ?> id;
    @FXML
    private TableColumn<velorepares, String> end;
    @FXML
    private TableColumn<velorepares, String> nbrparticipant;
    @FXML
    private Button nouveau;
    @FXML
    private Button delete;
    @FXML
    private Button delete1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObservableList<velorepares> velorepares = FXCollections.observableArrayList();
     try {
        
         for(velorepares c: cs.FindAllvelo())
             velorepares.add(c);
         namecomp.setCellValueFactory(new PropertyValueFactory<>("descriptionprob"));
         start.setCellValueFactory(new PropertyValueFactory<>("type"));
         end.setCellValueFactory(new PropertyValueFactory<>("marque"));

         nbrparticipant.setCellValueFactory(new PropertyValueFactory<>("ville"));
         id.setCellValueFactory(new PropertyValueFactory<>("id")); 

     
     } catch (SQLException ex) {
         Logger.getLogger(veloreparesService.class.getName()).log(Level.SEVERE, null, ex);
     }

       
        table.setItems(velorepares);
        
    
    }    
      
    

    @FXML
    private void nouveau(ActionEvent event) {
   
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("addReparation.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
       @FXML
    private void update(ActionEvent event) {
   
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("updatevelorep.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

  
    @FXML
    private void Delete(ActionEvent event) throws SQLException {
        if(!table.getSelectionModel().getSelectedItems().isEmpty()) {
           
           cs.DeleteVelorepares(table.getSelectionModel().getSelectedItems().get(0).getId());
             try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("afficherreparation.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
            
            
        }
        
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
        
       else{
           
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("aucun élément 'a ètè séléctionné");
        alert.showAndWait();

           
        
       }
    }
   
    
    
}
