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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pidev.entities.reparation;
import pidev.services.reparationService;
import pidev.services.veloreparesService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AffichereparationsController implements Initializable {

    @FXML
    private TableView<reparation> table;
     reparationService rs =new reparationService();
    @FXML
    private TableColumn<reparation, String> namecomp;
    @FXML
    private TableColumn<reparation, String> start;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<reparation, String> end;
    @FXML
    private TableColumn<reparation, Integer> nbrparticipant;
    @FXML
    private Button nouveau;
    @FXML
    private Button nouveau2;
    @FXML
    private Button delete;
    @FXML
    private TextField recherche;

    /**
     * Initializes the controller class.
     */
    private final ObservableList<reparation> dataList = FXCollections.observableArrayList();
    @FXML
    private AnchorPane avr;
    @FXML
    private Button nouveau1;
    @FXML
    private TextField filterField;
    @FXML
    private Button delete1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObservableList<reparation> reparation = FXCollections.observableArrayList();
     try {
        
         for(reparation r: rs.FindAllreparation())
             reparation.add(r);
         namecomp.setCellValueFactory(new PropertyValueFactory<>("nom"));
         start.setCellValueFactory(new PropertyValueFactory<>("date_debr"));
         end.setCellValueFactory(new PropertyValueFactory<>("date_finr"));

         nbrparticipant.setCellValueFactory(new PropertyValueFactory<>("prixr"));
         id.setCellValueFactory(new PropertyValueFactory<>("id")); 

         
     
     } catch (SQLException ex) {
         Logger.getLogger(veloreparesService.class.getName()).log(Level.SEVERE, null, ex);
     }

       
        table.setItems(reparation);
        
    
    }    
     

    @FXML
    private void nouveau(ActionEvent event) {
   try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("addReparations.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    private void update1(ActionEvent event) {
   
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("updatereparation.fxml"));
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
           
           rs.DeleteReparation(table.getSelectionModel().getSelectedItems().get(0).getId());
             try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("afficherreparations.fxml"));
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
    @FXML
    private void stat(ActionEvent event) {
   try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/gui/barechart.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    private void recherche(ActionEvent event) {
        ObservableList<reparation> reparation= FXCollections.observableArrayList();
            reparation= rs.recherche(recherche.getText());
     
         namecomp.setCellValueFactory(new PropertyValueFactory<>("nom"));
         start.setCellValueFactory(new PropertyValueFactory<>("date_debr"));
         end.setCellValueFactory(new PropertyValueFactory<>("date_finr"));

         nbrparticipant.setCellValueFactory(new PropertyValueFactory<>("prixr"));
        id.setCellValueFactory(new PropertyValueFactory<>("id")); 
  
         
   
       
        table.setItems(reparation);
        
    
    }
    
    
    
    
    
    
    
    
    
    
    
   /* @FXML
    private void stat(ActionEvent event) throws IOException {
        avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/barechart.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
    }*/
}

