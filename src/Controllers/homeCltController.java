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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class homeCltController implements Initializable {

    @FXML
    private MenuButton vente;
    @FXML
    private Button locationB;
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
    private void afficherProduit(ActionEvent event) throws IOException{
        System.out.println("afficher la liste des produits");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/listeproduits.fxml"));
        Parent root = loader.load();
        content.getChildren().setAll(root);
    }

    @FXML
    private void afficherPanier(ActionEvent event)throws IOException {
        System.out.println("afficher panier");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/panier.fxml"));
        Parent root = loader.load();
        content.getChildren().setAll(root);
    }

    @FXML
    private void afficherCommandes(ActionEvent event)throws IOException {
         System.out.println("afficher commandes");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/commandeClt.fxml"));
        Parent root = loader.load();
        content.getChildren().setAll(root);
    }

    @FXML
    private void location(ActionEvent event) throws IOException {
     content.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/affichervelo.fxml"));
        content.getChildren().add(parent);
        content.toFront();
    }

    @FXML
    private void reparation(ActionEvent event) throws IOException {
         content.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/Afficherreparation.fxml"));
        content.getChildren().add(parent);
        content.toFront();
    
    }
@FXML
   private void event(ActionEvent event) throws IOException {
        content.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/pidev/gui/AllEvents.fxml"));
        content.getChildren().add(parent);
        content.toFront();
    }

    @FXML
    private void cours(ActionEvent event) throws IOException {
          content.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/Affichercoursclient.fxml"));
        content.getChildren().add(parent);
        content.toFront();
    }

    @FXML
    private void statistiques(ActionEvent event) {
    }

   
        @FXML
    private void reclamations(ActionEvent event) throws IOException {
         content.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/ajoutreclamation.fxml"));
        content.getChildren().add(parent);
        content.toFront();
    }
    

    @FXML
    private void getpanier(MouseEvent event)throws IOException {
        System.out.println("afficher panier");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/panier.fxml"));
        Parent root = loader.load();
        content.getChildren().setAll(root);
    }

    
    
}
