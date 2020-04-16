/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import application1.locationn;
import application1.locationService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Hajer
 */
public class AfficherlocationController implements Initializable {


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
    private TableColumn<?, ?> velo_id;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private Button delete;
      @FXML
    private Button edit;
    @FXML
    private Button update;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<locationn> location = FXCollections.observableArrayList();
     try {
         
        
         for(locationn l: ls.getAlllocations())
             location.add(l);
         namecomp.setCellValueFactory(new PropertyValueFactory<>("nomlocation"));
         start.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
         end.setCellValueFactory(new PropertyValueFactory<>("date_fin"));

         prix.setCellValueFactory(new PropertyValueFactory<>("prixloc")); 
           id.setCellValueFactory(new PropertyValueFactory<>("id")); 
     
     } catch (SQLException ex) {
         Logger.getLogger(locationService.class.getName()).log(Level.SEVERE, null, ex);
     }

       
        table.setItems(location);
        
    
    }    
    
    public void load() {
         ObservableList<locationn> location = FXCollections.observableArrayList();
     try {
         
        
         for(locationn l: ls.getAlllocations())
             location.add(l);
         namecomp.setCellValueFactory(new PropertyValueFactory<>("nomlocation"));
         start.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
         end.setCellValueFactory(new PropertyValueFactory<>("date_fin"));

        velo_id.setCellValueFactory(new PropertyValueFactory<>("velo_id"));
         prix.setCellValueFactory(new PropertyValueFactory<>("prixloc")); 
           id.setCellValueFactory(new PropertyValueFactory<>("id")); 
     
     } catch (SQLException ex) {
         Logger.getLogger(locationService.class.getName()).log(Level.SEVERE, null, ex);
     }

       
        table.setItems(location);
        
    
    }    
       
    
   
    @FXML
    private void delete(ActionEvent event) throws SQLException, IOException, IOException, IOException {
   if(!table.getSelectionModel().getSelectedItems().isEmpty()){
               locationService ls = new locationService ();
           ls.delete(table.getSelectionModel().getSelectedItems().get(0).getId());
          
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
    private void edit(ActionEvent event) throws IOException {
        try {
  javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("modifierlocation.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
           
  // if(!table.getSelectionModel().getSelectedItems().isEmpty()){
             //  locationService ls = new locationService ();
              
         //  ls.Edit2(table.getSelectionModel().getSelectedItems().get(0).getId());
         
        }
       //else{
           
        //Alert alert = new Alert(Alert.AlertType.ERROR);
       // alert.setTitle("Information Dialog");
       // alert.setHeaderText(null);
      //  alert.setContentText("aucun élément 'a ètè séléctionné");
       // alert.showAndWait();
            
        
        
      catch (IOException ex) {
        System.out.println(ex.getMessage());
        }
    

}

    @FXML
    private void update(ActionEvent event) throws SQLException {
        
        Date a = table.getSelectionModel().getSelectedItems().get(0).getDate_fin();
         java.util.Date now = new java.util.Date();
          if (now.compareTo(a) >0) {
            ls.delete(table.getSelectionModel().getSelectedItems().get(0).getId());
         load();
          }
    }

}
   



