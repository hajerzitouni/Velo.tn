/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import pidev.entities.reparation;
import pidev.services.reparationService;
import pidev.services.veloreparesService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class updatereparationController implements Initializable {

    @FXML
    private AnchorPane avr;
    @FXML
    private JFXButton show;
    @FXML
    private AnchorPane btnAjouter;
    @FXML
    private TextField nom;
    @FXML
    private Button btnAjout;
    @FXML
    private Button consulter;
    @FXML
    private TextField prix;
    @FXML
    private TextField debut;
    @FXML
    private TextField fin;
    reparationService cs =new reparationService();
    @FXML
    private TableView<reparation> table;
    @FXML
    private TableColumn<reparation, String> C1;
    @FXML
    private TableColumn<reparation, String> C2;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<reparation, String> C3;
    @FXML
    private TableColumn<reparation, Integer> C4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<reparation> reparation = FXCollections.observableArrayList();
       try {
       for(reparation r: cs.FindAllreparation())
           reparation.add(r);
       C1.setCellValueFactory(new PropertyValueFactory<>("nom"));
       C2.setCellValueFactory(new PropertyValueFactory<>("date_debr"));
       C3.setCellValueFactory(new PropertyValueFactory<>("date_finr"));
       C4.setCellValueFactory(new PropertyValueFactory<>("prixr"));
       //id.setCellValueFactory(new PropertyValueFactory<>("id"));
        } catch (SQLException ex) {
         Logger.getLogger(reparationService.class.getName()).log(Level.SEVERE, null, ex);
     } 
       
       
        table.setItems(reparation);
        
    
    }    
    

    @FXML
    private void showData(ActionEvent event) {
    }
    @FXML
private void select() {
        reparation selected = table.getSelectionModel().getSelectedItem();
          if (!table.getSelectionModel().getSelectedItems().isEmpty()) {
        
        
        nom.setText(selected.getNom());
       debut.setText(selected.getDate_debr());
      
        fin.setText(String.valueOf(selected.getDate_finr()));
        prix.setText(String.valueOf(selected.getPrixr()));
                    
          
            
          
         
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
        reparationService cs = new reparationService();
        
       
            
            
           reparation r  = new reparation();
          reparation c2 = table.getSelectionModel().getSelectedItem();
            int id_ev = c2.getId();
            
            reparation c3 = new reparation(id_ev, (nom.getText()), debut.getText(), fin.getText(),Integer.parseInt(prix.getText()));
           cs.EditReparation(c3);
           
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("velo modifiÃ©");
            alert.showAndWait();
    }
@FXML
    private void back(ActionEvent event) {
        {
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/gui/Afficherreparations.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }
    
}
