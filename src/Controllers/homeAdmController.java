/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class homeAdmController implements Initializable {

    @FXML
    private MenuButton vente;
   @FXML
    private MenuItem locationB;
    @FXML
    private MenuItem velo;
    @FXML
    private MenuItem assurance;
    @FXML
    private Button rep;
    @FXML
    private Button event;
    @FXML
    private Button course;
    @FXML
    private Button stat;
    @FXML
    private Button recl;
    @FXML
    private AnchorPane content;
    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   @FXML
    private void afficherCommandes(ActionEvent event) throws IOException{
        System.out.println("afficher commandes admin");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/commandeAdm.fxml"));
        Parent root = loader.load();
        content.getChildren().setAll(root);
            
    }
    @FXML
    private void ajouterproduit(ActionEvent event) throws IOException{
        System.out.println("Ajouter produit");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ajouterproduit.fxml"));
        Parent root = loader.load();
        content.getChildren().setAll(root);
            
    }
    
    @FXML
    private void modifierproduit(ActionEvent event) throws IOException{
        System.out.println("Ajouter produit");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/modifierproduit.fxml"));
        Parent root = loader.load();
        content.getChildren().setAll(root);
            
    }

    @FXML
    private void location(ActionEvent event) throws IOException {
          content.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/Afficherlocation.fxml"));
        content.getChildren().add(parent);
        content.toFront();
    }

    @FXML
    private void reparation(ActionEvent event) throws IOException {
             
        

       content.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/Afficherreparations.fxml"));
        content.getChildren().add(parent);
        content.toFront();
    
    }

    @FXML
    private void event(ActionEvent event) throws IOException {
         content.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/pidev/gui/ListerEventAdmin.fxml"));
        content.getChildren().add(parent);
        content.toFront();
        
    }

   @FXML
    private void cours(ActionEvent event) throws IOException {
        

       content.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/AfficherCours.fxml"));
        content.getChildren().add(parent);
        content.toFront();
    }

    @FXML
    private void statistiques(ActionEvent event) {
    }

    @FXML
    private void reclamations(ActionEvent event) throws IOException {
         content.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/showreclamation.fxml"));
        content.getChildren().add(parent);
        content.toFront();
    }

    @FXML
    private void getsms(MouseEvent event) throws IOException{
        System.out.println("Ajouter produit");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SMS/sms.fxml"));
        Parent root = loader.load();
        content.getChildren().setAll(root);
    }
       @FXML
    private void Email(ActionEvent event) throws IOException {
               content.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/pidev/gui/Mailinterface.fxml"));
        content.getChildren().add(parent);
        content.toFront();
    }
     @FXML
    private void notif(ActionEvent event) {
        Image img = new Image ("/gui/stick.png");
     Notifications notificationBuilder = Notifications.create()
             .title("Notifications")
             .text("vous avez des nouvelles reclamations ..!")
             .graphic(new ImageView(img))
             .hideAfter(Duration.seconds(5))
             .position(Pos.TOP_RIGHT)
             .onAction(new EventHandler<ActionEvent>() {
               
                 public void hanle (ActionEvent event){
               System.out.println("Clicked on");
                 
                  }

         @Override
         public void handle(ActionEvent event) {
             throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         }
             });
     notificationBuilder.darkStyle();
     
     notificationBuilder.show();
             }
    
    @FXML
    private void velo(ActionEvent event) throws IOException {
         content.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/affichervelo_1.fxml"));
        content.getChildren().add(parent);
        content.toFront();
    }

    @FXML
    private void assurance(ActionEvent event) throws IOException {
         content.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/showassurance.fxml"));
        content.getChildren().add(parent);
        content.toFront();
    }
         
    
    
}
