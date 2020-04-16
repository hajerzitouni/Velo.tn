/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import application1.asurance;
import application1.locationn;
import application1.locationService;
import application1.velooService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hajer
 */
public class ShowlocationController implements Initializable {

    @FXML
    private TableView<locationn> table;
    locationService ls = new locationService ();
    @FXML
    private TableColumn<?, ?> namecomp;
    @FXML
    private TableColumn<?, ?> start;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> end;
    @FXML
    private TableColumn<?, ?> velo_id;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private Button delete;
    @FXML
    private Button calculprix;
    @FXML
    private TableView<asurance> tablea;
    @FXML
    private TableColumn<?, ?> asuranceloc;
    @FXML
    private Button confirmer;
    @FXML
    private Button retour;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void delete(ActionEvent event) throws SQLException {
    

        if(!table.getSelectionModel().getSelectedItems().isEmpty()){
               locationService ls = new locationService ();
               ObservableList<asurance> assurancel= FXCollections.observableArrayList();
           assurancel=ls.afficherassurance(table.getSelectionModel().getSelectedItems().get(0).getId());
            
   // assurance.setText("100");
        // for(String a : assurancel){
           // assurance.setText(a + "\n");
            // output += s.toString() + "\n";
             
                              // }//
                 asuranceloc.setCellValueFactory(new PropertyValueFactory<>("montant"));
                 
               
 tablea.setItems(assurancel);
        
       
        }
       else{
           
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("aucun élément 'a ètè séléctionné");
        alert.showAndWait();

           
        
       
    }
    }

    
     public void load(Integer id) throws SQLException {
         ObservableList<locationn> location = FXCollections.observableArrayList();
     
         
        
          location.add(ls.FindById(id));
             
         namecomp.setCellValueFactory(new PropertyValueFactory<>("nomlocation"));
         start.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
         end.setCellValueFactory(new PropertyValueFactory<>("date_fin"));

        velo_id.setCellValueFactory(new PropertyValueFactory<>("velo_id"));
         prix.setCellValueFactory(new PropertyValueFactory<>("prixloc")); 
       
     
   
       
        table.setItems(location);
        
    
    }  
     
    
    public void load() throws SQLException {
         ObservableList<locationn> location = FXCollections.observableArrayList();
     
         
        
          location.add(ls.FindById(table.getSelectionModel().getSelectedItems().get(0).getId()));
             
         namecomp.setCellValueFactory(new PropertyValueFactory<>("nomlocation"));
         start.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
         end.setCellValueFactory(new PropertyValueFactory<>("date_fin"));

        velo_id.setCellValueFactory(new PropertyValueFactory<>("velo_id"));
         prix.setCellValueFactory(new PropertyValueFactory<>("prixloc")); 
       
     
   
       
        table.setItems(location);
        
    
    }  
     
     @FXML
    private void calculprix(ActionEvent event) throws SQLException, IOException, IOException, IOException {
   if(!table.getSelectionModel().getSelectedItems().isEmpty()){
               locationService ls = new locationService ();
           ls.calculPrix(table.getSelectionModel().getSelectedItems().get(0).getId());
         load ();
         
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
    private void confirmer(ActionEvent event) throws SQLException {
      if(!table.getSelectionModel().getSelectedItems().isEmpty()){
             
         velooService vs = new velooService ();
         vs.etatvelo(table.getSelectionModel().getSelectedItems().get(0).getId());
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
    private void retour(ActionEvent event) throws IOException {
         
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/gui/Affichervelo.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }
    

