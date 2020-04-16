/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import java.io.IOException;
import pidev.entities.velorepares;
import pidev.services.veloreparesService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AddCastingController implements Initializable {
//ObservableList<String> tfnamecast1List = FXCollections.observableArrayList("Bizerte","Tunis","Beja");
    @FXML
    private AnchorPane btnAjouter;
    @FXML
    private TextField tfnamecast;
    @FXML
    private TextField tfstartcast;
    @FXML
    private TextArea tfendcast;
    @FXML
    private Button btnAjout;
    @FXML
     private Button MAP;
    @FXML
    private Button consulter;
    @FXML
    private TextField tfendcast1;
    @FXML
    private TextField tfnamecast1;
    
   // private ChoiceBox tfnamecast1;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //tfnamecast1.setItems(tfnamecast1List);
        // TODO
    }    

   @FXML
    private void ajouter2(ActionEvent event) {
        
        String namecast = tfnamecast.getText();
        String startcast = tfstartcast.getText();
        String endcast = tfendcast.getText();
        String endcast1 = tfendcast1.getText();
        //String namecast1 = tfnamecast1.getText();
        veloreparesService cs = new veloreparesService();
        velorepares c = new velorepares(namecast,startcast,endcast,endcast1);
        cs.AddVelorepares(c);
        System.out.println("Saha");
        try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("Afficherreparation.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
            
            
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
       private void consulter1(ActionEvent event) {
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("Afficherreparation.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
 @FXML
    private void MAP(ActionEvent event) {
   try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/gui/map.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
    
    
}
