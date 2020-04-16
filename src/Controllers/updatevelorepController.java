/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import static java.awt.PageAttributes.MediaType.C;
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
import static javafx.print.Paper.C;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pidev.entities.velorepares;
import pidev.services.veloreparesService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class updatevelorepController implements Initializable {

    @FXML
    private AnchorPane avr;
    @FXML
    private JFXButton edit;
    @FXML
    private TableView<velorepares> table;
    @FXML
    private TableColumn<velorepares, String> C1;
    @FXML
    private TableColumn<velorepares, String> C2;
    @FXML
    private TableColumn<velorepares, String> C3;
    @FXML
    private TableColumn<velorepares, String> C4;
    @FXML
    private TextField type;
    @FXML
    private TextField marque;
    @FXML
    private TextField desc;
    @FXML
    private TextField ville;
veloreparesService cs =new veloreparesService();
    @FXML
    private AnchorPane btnAjouter;
  
    @FXML
    private TableColumn<?, ?> id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObservableList<velorepares> velorepares = FXCollections.observableArrayList();
       try {
       for(velorepares c: cs.FindAllvelo())
           velorepares.add(c);
       C1.setCellValueFactory(new PropertyValueFactory<>("descriptionprob"));
       C2.setCellValueFactory(new PropertyValueFactory<>("type"));
       C3.setCellValueFactory(new PropertyValueFactory<>("marque"));
       C4.setCellValueFactory(new PropertyValueFactory<>("ville"));
       //id.setCellValueFactory(new PropertyValueFactory<>("id"));
        } catch (SQLException ex) {
         Logger.getLogger(veloreparesService.class.getName()).log(Level.SEVERE, null, ex);
     } 
       
       
        table.setItems(velorepares);
        
    
    }    
      
    
     

    @FXML
    private void showData(ActionEvent event) {
    }
@FXML 
    private void select() {
        velorepares selected = table.getSelectionModel().getSelectedItem();
          if (!table.getSelectionModel().getSelectedItems().isEmpty()) {
        
        
        desc.setText(selected.getDescriptionprob());
        marque.setText(selected.getMarque());  
        type.setText(String.valueOf(selected.getType()));
        ville.setText(String.valueOf(selected.getVille()));
        
          } else {
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("aucun Ã©lÃ©ment 'a Ã¨tÃ¨ sÃ©lÃ©ctionnÃ©");
            alert.showAndWait();
          }
    }
@FXML 
    private void edit(ActionEvent event) throws SQLException {
        veloreparesService cs = new veloreparesService();
        
       
            
            
            velorepares c  = new velorepares();
          velorepares c2 = table.getSelectionModel().getSelectedItem();
            int id_ev = c2.getId();
            
            velorepares c3 = new velorepares(id_ev, (desc.getText()), marque.getText(), type.getText(), (ville.getText()));
            cs.EditVelorepares(c3);
           
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("velo modifiÃ©");
            alert.showAndWait();
    }
@FXML
    private void Back(ActionEvent event) {
        {
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/gui/Afficherreparation.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }

    @FXML
    private void ajouter2(ActionEvent event) {
    }

    @FXML
    private void consulter1(ActionEvent event) {
    }
    }
    

