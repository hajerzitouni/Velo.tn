/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import static java.awt.PageAttributes.MediaType.C;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import static javafx.print.Paper.C;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.C;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static jdk.nashorn.internal.runtime.Debug.id;
import pidev.entities.cours;
import pidev.services.coursService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class UpdatcoursController implements Initializable {
  @FXML
    private TextField recherche;
    @FXML
    private AnchorPane avr;
    @FXML
    private JFXButton show;
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
    private TableColumn<cours,? > id;
    @FXML
    private AnchorPane Fields;
    @FXML
    private TextField niveau;
    @FXML
    private TextField date;
    @FXML
    private TextField lieu;
    @FXML
    private TextField prix;
    @FXML
    private JFXButton Back;
    @FXML
    private JFXButton edit;
coursService cs=new coursService();
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
        @FXML
    private void recherche(KeyEvent event) {
        ObservableList<cours> cours= FXCollections.observableArrayList();
            cours= cs.recherche(recherche.getText());
     
         C1.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        C2.setCellValueFactory(new PropertyValueFactory<>("date_cours"));
         C3.setCellValueFactory(new PropertyValueFactory<>("lieu_cours"));

        C4.setCellValueFactory(new PropertyValueFactory<>("prixcours"));
      
  
         
   
       
        table.setItems(cours);
        
    
    }
    

   
        
   
    

   @FXML
    void select() {
     
        cours selected = table.getSelectionModel().getSelectedItem();
          if (!table.getSelectionModel().getSelectedItems().isEmpty()) {
        
        
        date.setText(selected.getDate_cours());
        lieu.setText(selected.getLieu_cours());  
        niveau.setText(String.valueOf(selected.getNiveau()));
        prix.setText(String.valueOf(selected.getPrixcours()));
        
          } else {
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("aucun élément 'a ètè séléctionné");
            alert.showAndWait();
          }
    }

       
    

    @FXML 
    void edit(){
        
     coursService cs = new coursService();
        
       
            
            
            cours c  = new cours();
          cours c2 = table.getSelectionModel().getSelectedItem();
            int id_ev = c2.getId();
            
            cours c3 = new cours(id_ev, Integer.parseInt(niveau.getText()), date.getText(), lieu.getText(), Integer.parseInt(prix.getText()));
            cs.EditCours(c3);
           
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("cours modifié");
            alert.showAndWait();
            
        }
      

    
   


    

      
    @FXML
    private void Back(ActionEvent event) {
         {
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/gui/Affichercours.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }}
    
