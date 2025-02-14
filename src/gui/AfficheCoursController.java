/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pidev.entities.cours;
import pidev.services.coursService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficheCoursController implements Initializable {
    
    @FXML
    private Button delete;
    @FXML
    private TableView<cours> table;
       @FXML
    private TextField recherche;
       
    @FXML
    private TableColumn<cours, Integer> namecomp;
    @FXML
    private TableColumn<cours, String> start;
    @FXML
    private TableColumn<cours,? > id;
    @FXML
    private TableColumn<cours, String> end;
    @FXML
    private TableColumn<cours, Integer> nbrparticipant;
     @FXML
    private ImageView imageViewAdd;
    @FXML
    private Button nouveau;
     @FXML
    private TableView<cours> TableId;
      coursService cs=new coursService();
   ObservableList<cours> data;
     private cours selectedid;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        

      
     
                   
   ObservableList<cours> cours = FXCollections.observableArrayList();
     try {
        
         for(cours c: cs.FindAllcours())
             cours.add(c);
         namecomp.setCellValueFactory(new PropertyValueFactory<>("niveau"));
         start.setCellValueFactory(new PropertyValueFactory<>("date_cours"));
         end.setCellValueFactory(new PropertyValueFactory<>("lieu_cours"));

         nbrparticipant.setCellValueFactory(new PropertyValueFactory<>("prixcours"));
        id.setCellValueFactory(new PropertyValueFactory<>("id")); 
  
       
      table.setItems(cours);
     } catch (SQLException ex) {
          System.out.println(ex);
     } 
       
       
        
    
    }   
      

    @FXML
    private void nouveau(ActionEvent event) {
       
         
         try {
           
        javafx.scene.Parent view = FXMLLoader.load(getClass().getResource("AddCours.fxml"));
        Scene sceneview = new Scene(view);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    private void afficherImage(MouseEvent event) {
        
      selectedid = (cours) table.getSelectionModel().getSelectedItem();
       Image image= new Image( "http://localhost//pidev.java/"+selectedid.getImage() );
       imageViewAdd.setImage(image);
       
       
    }
   @FXML
    private void recherche(KeyEvent event) {
        ObservableList<cours> cours= FXCollections.observableArrayList();
            cours= cs.recherche(recherche.getText());
     
         namecomp.setCellValueFactory(new PropertyValueFactory<>("niveau"));
         start.setCellValueFactory(new PropertyValueFactory<>("date_cours"));
         end.setCellValueFactory(new PropertyValueFactory<>("lieu_cours"));

         nbrparticipant.setCellValueFactory(new PropertyValueFactory<>("prixcours"));
        id.setCellValueFactory(new PropertyValueFactory<>("id")); 
  
         
   
       
        table.setItems(cours);
        
    
    }
        
       @FXML
    private void deletecours(ActionEvent event) throws SQLException {
        
        if(table.getSelectionModel().getSelectedItems().size()!=0) {
           
           cs.DeleteCours(table.getSelectionModel().getSelectedItems().get(0).getId());
             try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("affichercours.fxml"));
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
    private void edit(ActionEvent event) {
         {
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("update.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }}}
    
    
      
    

   

