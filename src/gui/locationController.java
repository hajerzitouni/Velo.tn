/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import application1.Application1;
import application1.locationn;
import application1.locationService;
import application1.velo;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import application1.fos_user;
import application1.fos_userService;
import static application1.Application1.fos_user;
import static application1.Application1.user_id;
import java.text.SimpleDateFormat;


/**
 * FXML Controller class
 *
 * @author Hajer
 */
public class locationController implements Initializable {

    @FXML
    private AnchorPane btnAjouter;
    @FXML
    private TextField tfnamecomp;
    @FXML
    private TextField tfnbrcomp;
    @FXML
    private Button btnAjout;
    @FXML
    private Button consulter;
    @FXML
    private DatePicker tfstartcomp;
    @FXML
    private DatePicker tfendcomp;
    int  lastid =0 ;
  
    public static int vdt;
   
    locationService ls = new locationService ();

    fos_userService us = new fos_userService();
           
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    public void ajouter(int vdt) {
        btnAjout.setOnAction((event) -> {
          try {
        
         

         Date startcomp = Date.valueOf(tfstartcomp.getValue());
        Date endcomp = Date.valueOf(tfendcomp.getValue());
        locationService lss = new locationService ();
         if(validateInputs() ){
        if (ls.getdate(vdt).compareTo(startcomp) >0) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("date non val");
            alert.setHeaderText(null);
            alert.setContentText("date non correcte");
            alert.showAndWait(); }
          //  else {
         
            
          String namecomp= tfnamecomp.getText();
//      int nbrcomp  = (Integer) Integer.parseInt(tfnbrcomp.getText()) + 0;
    //String nbrcomp= tfnbrcomp.getText();
          
     //   String startcomp = tfstartcomp.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
     
      
      //  String endcomp = tfendcomp.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    
           fos_userService us = new fos_userService();
           
           
        user_id = Application1.user_id;
        locationService ls = new locationService ();
            locationn l = new locationn (user_id,startcomp,endcomp,namecomp);
            
               ls.ajouter3(l, vdt);
             
              
        System.out.println("location ajouté"); 
        lastid = ls.getIdbynom(namecomp);
        System.out.println(lastid);
           
          
            
        }
         
           
          }catch (SQLException ex) {
            Logger.getLogger(locationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    }
   
     public static boolean isNotInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return true;
        }

        return false;
    }
    
     private boolean validateInputs() throws SQLException {
         SimpleDateFormat formater = null;
          String d;
          Date v ;
         
         v= ls.getdate(vdt);
         System.out.println( "v="+ v);
                 java.util.Date now = new java.util.Date();
 formater = new SimpleDateFormat("dd-MM-yy");
   d =formater.format(now);
        Date startcomp = Date.valueOf(tfstartcomp.getValue());
        Date endcomp = Date.valueOf(tfendcomp.getValue());
//        if (tfnbrcomp.getText().length() == 0 || tfnbrcomp.getText().length() == 0
                if( tfnamecomp.getText().length() == 0) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("tous les champs doivent etre remplis");
            alert.showAndWait();
            return false;
        }// else if (isNotInteger(tfnbrcomp.getText()) ){

           // Alert alert1 = new Alert(Alert.AlertType.WARNING);
            //alert1.setTitle("Erreur");
            //alert1.setContentText(" doivent etre des nombres");
          //  alert1.setHeaderText(null);
           // alert1.show();
         //   return false;

      //  }
        else if (endcomp.compareTo(startcomp) <0) {
         Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("date eronné");
            alert.setHeaderText(null);
            alert.setContentText("date eronnée");
            alert.showAndWait();
            return false;
        
        }
                else if (now.compareTo(startcomp) >0) {
         Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("date eronné");
            alert.setHeaderText(null);
            alert.setContentText("date eronnée");
            alert.showAndWait();
            return false;
                
                }
               /*else if (v.compareTo(startcomp) <0) {
         Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("date non val");
            alert.setHeaderText(null);
            alert.setContentText("date non correcte");
            alert.showAndWait();
            return false;
                }*/
        return true;
    }

    @FXML
    private void consulter(ActionEvent event) throws IOException, SQLException {
        try {
          FXMLLoader consulterLoader = new FXMLLoader(getClass().getResource("/gui/showlocation.fxml"));
                Parent root = (Parent)consulterLoader.load();
                ShowlocationController slController =  consulterLoader.getController();
                
//      t3ayet lel fonction showCommande ta3 controller le5er          
               slController.load(lastid);
                
//      bch thezzek lel interface lo5ra                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Store");
                
                        
        
        } catch (IOException ex) {
            Logger.getLogger(locationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
   
}
