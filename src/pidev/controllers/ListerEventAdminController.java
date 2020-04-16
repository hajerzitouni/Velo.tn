/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controllers;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import pidev.entities.Event;
import pidev.entities.User;
import pidev.services.EventService;
import pidev.services.MailService;
import pidev.services.RecuService;
import pidev.services.UserService;

/**
 * FXML Controller class
 *
 * @author Manel
 */
public class ListerEventAdminController implements Initializable {
        private ObservableList<Event> data;

        @FXML
    private TableView<Event> tableview;
    @FXML
    private TableColumn<Event, Integer> nomevent=new TableColumn<>();
    @FXML
    private TableColumn<Event, Integer> date_deb=new TableColumn<>();
    @FXML
    private TableColumn<Event, Integer> date_fin=new TableColumn<>();
    @FXML
    private TableColumn<Event, Integer> nb_participants=new TableColumn<>();
    @FXML
    private TableColumn<Event, Integer> prix=new TableColumn<>();
    @FXML
    private TableColumn<Event, Integer> creator_id=new TableColumn<>();
    @FXML
    private TableColumn<Event, Integer> nb_signal=new TableColumn<>();
    @FXML
    private Button ajouter_admin;
    @FXML
    private Button modifier_admin;
    @FXML
    private Button supprimer_admin;
    @FXML
    private Button Envoyer_mail;
    @FXML
    private Button Afficher_admin;
    
      @FXML
    private Button ListeRecu;
      
        @FXML
    private Button Listcomment;
    
    
        
    @FXML
    private AnchorPane holderPane;  
    
     @FXML 
     private JFXTextField rechercheBar;
     public static String  value ;
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          Event u = new Event();
        nomevent.setCellValueFactory(new PropertyValueFactory<Event, Integer>("nomevet"));
        prix.setCellValueFactory(new PropertyValueFactory<Event, Integer>("prixe"));
        date_deb.setCellValueFactory(new PropertyValueFactory<Event, Integer>("date_debe"));
        date_fin.setCellValueFactory(new PropertyValueFactory<Event, Integer>("date_fine"));
        nb_signal.setCellValueFactory(new PropertyValueFactory<Event, Integer>("nbsignal"));
        nb_participants.setCellValueFactory(new PropertyValueFactory<Event, Integer>("nbparticipent"));
        //creator_id.setCellValueFactory(new PropertyValueFactory<Event, Integer>("creator_id"));
        buildData();
         
        rechercheBar.textProperty().addListener((observable, oldValue, newValue) -> { 
        value = rechercheBar.getText();
        buildData();
        });
        
       
        // TODO
    }    

       private void buildData() {
         EventService su = EventService.getInstance();
        data=FXCollections.observableArrayList();
       
        try {
             ResultSet rs = su.afficherevents();
            while(rs.next()){
                
                Event evt = new Event();
                evt.setId(rs.getInt(1));
                evt.setNomevet(rs.getString(2));
                evt.setLieuevt(rs.getString(3));
                evt.setDate_debe(rs.getTimestamp(4)); 
                evt.setDate_fine(rs.getTimestamp(5)); 
                evt.setDescription(rs.getString(6));
                evt.setNbparticipent(rs.getInt(7));
                evt.setPrixe(rs.getInt(8));
                evt.setNom_image(rs.getString(9));
                //evt.setCreator_id(rs.getInt(10));
                evt.setNbsignal(rs.getInt(11));
                evt.setIsActive(rs.getBoolean(12)); 
             if(value == null ){
               
              data.add(evt);
              }
             ////////recherche ///////// 
             else if (evt.getNomevet().toLowerCase().contains(value.toLowerCase()) || evt.getDescription().toLowerCase().contains(value.toLowerCase())) {
                 data.add(evt);
             
             }
            }
            
            tableview.setItems(data);

        } catch (SQLException ex) {
            Logger.getLogger(ListerEventAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        
        
                AnchorPane page1 = FXMLLoader.load(getClass().getResource("/pidev/gui/ajouterevent.fxml"));
                               setNode(page1);

        
    }
            public void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1000));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    @FXML
    private void modifier(ActionEvent event) throws IOException {
        
     Event evt =(Event)tableview.getSelectionModel().getSelectedItem();  
     ModifiereventController.id = evt.getId();
     AnchorPane page1 = FXMLLoader.load(getClass().getResource("/pidev/gui/ModifierEvent.fxml"));
     setNode(page1);
        
    }

    @FXML
    private void supprimer(ActionEvent event) {
           Event evt =(Event)tableview.getSelectionModel().getSelectedItem();  
           EventService ev = new EventService().getInstance() ; 
           ev.supprimerevent(evt.getId());
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("evenement supprimé avec succé!");
        alert.show();
        buildData();
        
    }

    @FXML
    private void envoyerMail(ActionEvent event) throws IOException  {
        Event evt =(Event)tableview.getSelectionModel().getSelectedItem();  
        MailService ml = new MailService();
        UserService usr = UserService.getInstance(); 
        RecuService src = RecuService.getInstance();
        
        

            try {
                ResultSet rs = src.afficherRecuParEvent(evt.getId());
                while(rs.next()) {
                  User user = usr.afficheruserparid(rs.getInt(3));
                      String img ="C:\\wamp64\\www\\pidev_v1\\web\\images\\" + evt.getNom_image();
                   ml.envoyer( user.getEmail(),evt.getNomevet(),evt.getDescription(),img);

                    
                  
                }  
            } catch (SQLException ex) {
                Logger.getLogger(ListerEventAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
                           
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Mails envoyés aves succés ! ");
        alert.show();
        
        
    }

    @FXML
    private void afficher(ActionEvent event) throws IOException {
               
     Event evt =(Event)tableview.getSelectionModel().getSelectedItem();  
            DetailEventController.id = evt.getId();

           holderPane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/pidev/gui/DetailEvent.fxml"));
        holderPane.getChildren().add(parent);
        
    }
    
    
      @FXML
    private void ListCommentA(ActionEvent event) throws IOException {
         holderPane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/pidev/gui/ListCommentAdmin.fxml"));
        holderPane.getChildren().add(parent);

    }
    
    
      @FXML
    private void ListeReçuA(ActionEvent event) throws IOException {
        
       holderPane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/pidev/gui/ListRecuAdmin.fxml"));
        holderPane.getChildren().add(parent);

    }
    
    
}
