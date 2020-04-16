/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Hajer
 */
public class homeuController implements Initializable {

    @FXML
    private Button vente;
    @FXML
    private Button locationB;
    @FXML
    private Button rep;
    @FXML
    private ImageView background;
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
    private void vente(ActionEvent event) {
    }

    @FXML
    private void location(ActionEvent event) throws IOException {
     content.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/affichervelo.fxml"));
        content.getChildren().add(parent);
        content.toFront();
    }
    
    

    @FXML
    private void reparation(ActionEvent event) {
    }

    @FXML
    private void event(ActionEvent event) {
    }

    @FXML
    private void cours(ActionEvent event) {
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
    
    }
    

