/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pidev.entities.fos_user;


/**
 *
 * @author gogo-
 */
public class pidev extends Application {
        public static fos_user fos_user;
    public static int user_id;
    
    @Override
    public void start(Stage primaryStage) {
        try {
            /*   Button btn = new Button();
            btn.setText("Say 'Hello World'");
            btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
            System.out.println("Hello World!");
            }
            });
            
            StackPane root = new StackPane();
            root.getChildren().add(btn);
            */
               Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
             // Parent root = FXMLLoader.load(getClass().getResource("/gui/homeclient.fxml"));
               // Parent root = FXMLLoader.load(getClass().getResource("/gui/Paiement.fxml"));
           
          //Parent root = FXMLLoader.load(getClass().getResource("/gui/homev.fxml"));
            
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("cours");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(pidev.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
 public static void main(String[] args) {
        launch(args);
      
      
   
        System.out.println(fos_user);
        System.out.println(user_id);
        System.out.println(fos_user.getRoles());
        
    }

    
}
