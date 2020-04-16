/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application1;

import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter; 
//import java.sql.Date;  
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 *
 * @author Hajer
 */
public class Application1 extends Application {

    public static fos_user fos_user;
    public static int user_id;
   
      @Override
      public void start(Stage primaryStage) {
        try {
           
            
        // Parent root = FXMLLoader.load(getClass().getResource("/gui/ajoutlocation.fxml"));
         //   Parent root = FXMLLoader.load(getClass().getResource("/gui/afficherlocation.fxml"));
           //Parent root = FXMLLoader.load(getClass().getResource("/gui/modifierlocation.fxml"));
      // Parent root = FXMLLoader.load(getClass().getResource("/gui/addvelo.fxml"));
      //Parent root = FXMLLoader.load(getClass().getResource("/gui/affichervelo.fxml"));
      //Parent root = FXMLLoader.load(getClass().getResource("/gui/ajoutreclamation.fxml"));
    // Parent root = FXMLLoader.load(getClass().getResource("/gui/showreclamation.fxml"));
    //Parent root = FXMLLoader.load(getClass().getResource("/gui/addassurance.fxml"));
  // Parent root = FXMLLoader.load(getClass().getResource("/gui/showassurance.fxml"));
 //Parent root = FXMLLoader.load(getClass().getResource("/gui/homev.fxml"));
 //Parent root = FXMLLoader.load(getClass().getResource("/gui/homev_1.fxml"));
 Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
 Scene scene = new Scene(root);
            
            primaryStage.setTitle("location");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
           Logger.getLogger(Application1.class.getName()).log(Level.SEVERE, null, ex);
        
}
      }
       /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
    
    
      launch(args);
      locationService ls = new locationService();
      //Date date1 = Date.valueOf("2020-03-30");
        //     Date date2 = Date.valueOf("2020-03-3");
       //location l = new location(date1,date2,"test");
  //ls.ajouter3(l, 5);
  velooService vs = new velooService();
// System.out.println(vs.getAllveloos());
///vs.etatvelo(49);
SimpleDateFormat formater = null;
Date aujourdhui = new Date();
 formater = new SimpleDateFormat("dd-MM-yy");
   System.out.println(formater.format(aujourdhui));
  //System.out.println(ls.getdate(28)); 

       
   
    
            
        
    
}
      
       
      
       
      
    }
    

