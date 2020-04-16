/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import application1.locationn;
import application1.locationService;
import application1.velo;
import application1.velooService;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hajer
 */
public class AfficherveloController implements Initializable {

    @FXML
    private TableView<velo> table;
    velooService vs = new velooService ();
    private TableColumn<?, ?> namecomp;
    @FXML
    private TableColumn<?, ?> start;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> end;
    private TableColumn<?, ?> etat;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private ImageView imageview;
     private  velo selectedid;
    @FXML
    private Button louer;
    Integer lastid=0;
    private TableView<velo> table1;
    private TableColumn<?, ?> namecomp1;
    private TableColumn<?, ?> start1;
    private TableColumn<?, ?> end1;
    @FXML
    private TableColumn<?, ?> etat1;
    private TableColumn<?, ?> prix1;
    @FXML
    private TableColumn<?, ?> name;
   
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<velo> veloo = FXCollections.observableArrayList();
     try {
         
        
         for(velo  v : vs.getAllveloos())
             veloo.add(v);
         
            
         name.setCellValueFactory(new PropertyValueFactory<>("nomvelo"));
         start.setCellValueFactory(new PropertyValueFactory<>("marque"));
         end.setCellValueFactory(new PropertyValueFactory<>("age"));
        etat1.setCellValueFactory(new PropertyValueFactory<>("etat1"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix")); 
      
         
         
           
     
     } catch (SQLException ex) {
         Logger.getLogger(velooService.class.getName()).log(Level.SEVERE, null, ex);
     }

       
        table.setItems(veloo);
        
    
    }    
    

    @FXML
    private void afficherImage(MouseEvent event) {
        
      selectedid = (velo) table.getSelectionModel().getSelectedItem();
       Image i = new Image( "http://localhost//pidev.java/"+selectedid.getImage() );
       imageview.setImage(i);
       System.out.println(selectedid.getImage());
       
    }

    

    private void delete(ActionEvent event) throws SQLException {
 if(!table.getSelectionModel().getSelectedItems().isEmpty()){
               
           vs.delete(table.getSelectionModel().getSelectedItems().get(0).getId());
         
        }
       else{
           
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("aucun élément 'a ètè séléctionné");
        alert.showAndWait();

           
        
       
    }
  
    
    }

    private void edit(ActionEvent event) {
        try {
  javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("modifierlocation.fxml"));
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
    private void louer(ActionEvent event) {
   try {
            
                
                if(table.getSelectionModel().getSelectedItem() != null){
                    
//             bch        
                int vdt= table.getSelectionModel().getSelectedItems().get(0).getId();
                
//      na3mel f instance ta3 controller e5er li bch yji fih affichage ta3 ka3ba wa7da w bch tet7at fih methode showCommande() 
                FXMLLoader louerLoader = new FXMLLoader(getClass().getResource("/gui/ajoutlocation.fxml"));
                Parent root = (Parent)louerLoader.load();
                locationController ccController =  louerLoader.getController();
                
//      t3ayet lel fonction showCommande ta3 controller le5er          
                ccController.ajouter( vdt);
                
                
//      bch thezzek lel interface lo5ra                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Store");
                }else{
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Alert");
                    alert1.setContentText("Selectionner une commande");
                    alert1.setHeaderText(null);
                    alert1.show();
                        }
        
        } catch (IOException ex) {
            Logger.getLogger(AfficherveloController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     public void reresh() {
      ObservableList<velo> loués = FXCollections.observableArrayList();
     try {
         
        
         for(velo  v : vs.getAllveloos())
             loués.add(v);
            
         namecomp1.setCellValueFactory(new PropertyValueFactory<>("nomvelo"));
         start1.setCellValueFactory(new PropertyValueFactory<>("marque"));
         end1.setCellValueFactory(new PropertyValueFactory<>("age"));
        etat1.setCellValueFactory(new PropertyValueFactory<>("etat"));
         prix1.setCellValueFactory(new PropertyValueFactory<>("prix")); 
         
           
     
     } catch (SQLException ex) {
         Logger.getLogger(velooService.class.getName()).log(Level.SEVERE, null, ex);
     }

       
        table1.setItems(loués);
        
    
    }    
}
     
  
