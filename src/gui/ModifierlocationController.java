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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hajer
 */
public class ModifierlocationController implements Initializable {
 @FXML
    private TableView<locationn> table;
     locationService ls = new locationService ();
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
    @FXML
    private Button modifier;
    @FXML
    private Button retour;
    @FXML
    private TextField nom;
    @FXML
    private TextField velo;
   
    @FXML
    private TableColumn<?, ?> nomi;
    @FXML
    private TableColumn<?, ?> start;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> end;
    @FXML
    private TableColumn<?, ?> veloi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       ObservableList<locationn> location = FXCollections.observableArrayList();
     try {
         
        
         for(locationn l: ls.getAlllocations())
             location.add(l);
         nomi.setCellValueFactory(new PropertyValueFactory<>("nomlocation"));
         start.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
         end.setCellValueFactory(new PropertyValueFactory<>("date_fin"));

         
         
     
     } catch (SQLException ex) {
         Logger.getLogger(locationService.class.getName()).log(Level.SEVERE, null, ex);
     }

       
        table.setItems(location);
        
    
    }      
     

 
     @FXML
    void select() {
     
        locationn selected = table.getSelectionModel().getSelectedItem();
          if (!table.getSelectionModel().getSelectedItems().isEmpty()) {
        
         selected.setVelo_id(Integer.parseInt(velo.getText()));
         Date start = Date.valueOf(datedebut.getValue());
        Date end = Date.valueOf(datefin.getValue());
              selected.setDate_debut(start);
              selected.setDate_fin(end);
              selected.setNomlocation(nom.getText());
        
          } else {
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("aucun élément 'a ètè séléctionné");
            alert.showAndWait();
          }
    }
     @FXML
    private void modifier(ActionEvent event) throws IOException, SQLException {
        

         //if(!table.getSelectionModel().getSelectedItems().isEmpty()){
            
            locationService ls = new locationService ();
        Date start = Date.valueOf(datedebut.getValue());
        Date end = Date.valueOf(datefin.getValue());
      
            
           locationn l = new locationn();
            //ls.Edit2(table.getSelectionModel().getSelectedItems().get(0).getId());
            
            //String tit = "Opération réussie";
            //String message = "L'évenement a été ajoutée avec succée";
            
            locationn l2 = table.getSelectionModel().getSelectedItem();
               int id_ev = l2.getId() ;
               locationn l3 = new locationn(id_ev,Integer.parseInt(velo.getText()),start,end,nom.getText());
               
               ls.Editl(l3);
                       //l.setVelo_id(Integer.parseInt(velo.getText()));
             // l.setDate_debut(start);
              //l.setDate_fin(end);
              //l.setNomlocation(nom.getText());
        
       //locationService ls = new locationService ();
       //ls.Edit(l, ls.getIdbynom(nom.getText()));
       // sc.modifierClient(c, sc.getIdbymail(email.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("!!! Modification avec success !!!");
        alert.showAndWait();
    }
            
        @FXML
    private void retour(ActionEvent event) throws IOException {
         
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/gui/Afficherlocation.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }
    
        
        
    

     


   
    
   
