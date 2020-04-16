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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import pidev.entities.Event;
import pidev.services.EventService;

/**
 * FXML Controller class
 *
 * @author Manel 
 */
public class AllEventsController implements Initializable {

    @FXML
    private AnchorPane holderPane;
    @FXML
    private VBox vbox2;
    @FXML
    private ImageView image2;
    @FXML
    private Label label2;
    @FXML
    private Button btn2_event;
    @FXML
    private VBox vbox1;
    @FXML
    private ImageView image1;
    @FXML
    private Label label1;
    @FXML
    private Button btn1_event;
    @FXML
    private VBox vbox3;
    @FXML
    private ImageView image3;
    @FXML
    private Label label3;
    @FXML
    private Button btn3_event;
    @FXML
    private VBox vbox4;
    @FXML
    private ImageView image4;
    @FXML
    private Label label4;
    @FXML
    private Button btn4_event;
    @FXML
    private ImageView eventsuivant;
    @FXML
    private ImageView eventprecedent;
    private static int a,b,c,d,i,idevent1,idevent2,idevent3,idevent4;  
    private ObservableList<Event> data;
    public static String  value ;
     @FXML 
     private JFXTextField rechercheBar;
     
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        a=0;b=1;c=2;d=3;
                     eventprecedent.setVisible(false);
           buildData();
        rechercheBar.textProperty().addListener((observable, oldValue, newValue) -> { 
        value = rechercheBar.getText();
        buildData();
        });  
           

    }    
    private void buildData()
    {
        data=FXCollections.observableArrayList();
        EventService su = EventService.getInstance();
        btn1_event.setVisible(false);
        vbox1.setVisible(false);
        label1.setVisible(false);
        image1.setVisible(false);
       btn2_event.setVisible(false);
        vbox2.setVisible(false);
        label2.setVisible(false);
        image2.setVisible(false);
        btn3_event.setVisible(false);
        vbox3.setVisible(false);
        label3.setVisible(false);
        image3.setVisible(false);
        btn4_event.setVisible(false);
        vbox4.setVisible(false);
        label4.setVisible(false);
        image4.setVisible(false);
           i = 0;
            int j = 0;
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
                evt.setCreator_id(rs.getInt(10));
                evt.setNbsignal(rs.getInt(11));
                evt.setIsActive(rs.getBoolean(12)); 
                System.out.println(evt.getIsActive());
             if(value == null){
               if(evt.getIsActive()){
              data.add(evt);
              j++;
               }
              }
             ////////recherche ///////// 
             else if (evt.getNomevet().toLowerCase().contains(value.toLowerCase()) || evt.getDescription().toLowerCase().contains(value.toLowerCase())) {
                 data.add(evt);
                 j++;
             
             }
             
                         while(i<j)
            {
                              if (i == a) {
                        btn1_event.setVisible(true);
                        vbox1.setVisible(true);
                        label1.setVisible(true);
                        image1.setVisible(true);
                        label1.setText(data.get(i).getNomevet());
                        String img ="file:///C://wamp//www//pidev_v1//web//images//" + data.get(i).getNom_image();
                        idevent1 = data.get(i).getId();
                        image1.setImage(new Image(img));
                }
                else if(i == b){
                         btn2_event.setVisible(true);
                        vbox2.setVisible(true);
                        label2.setVisible(true);
                        image2.setVisible(true);
                        label2.setText(data.get(i).getNomevet());
                        String img ="file:///C://wamp//www//pidev_v1//web//images//" + data.get(i).getNom_image();
                        idevent2 = data.get(i).getId();
                        image2.setImage(new Image(img));
                }
                    else if(i == c){
                        btn3_event.setVisible(true);
                        vbox3.setVisible(true);
                        label3.setVisible(true);
                        image3.setVisible(true);                        
                        label3.setText(data.get(i).getNomevet());
                        String img ="file:///C://wamp//www//pidev_v1//web//images//" + data.get(i).getNom_image();
                        idevent3 = data.get(i).getId();
                        image3.setImage(new Image(img));
                         }
                      else if(i == d){
                        btn4_event.setVisible(true);
                        vbox4.setVisible(true);
                        label4.setVisible(true);
                        image4.setVisible(true);                        
                        label4.setText(data.get(i).getNomevet());
                        String img ="file:///C://wamp//www//pidev_v1//web//images//" + data.get(i).getNom_image();
                        idevent4 = data.get(i).getId();
                        image4.setImage(new Image(img));
                         }

            i++;
            }
             
             
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(ListerEventAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void affichage_2(ActionEvent event) throws IOException {
    DetailEventController.id = idevent2;
                    AnchorPane page1 = FXMLLoader.load(getClass().getResource("/pidev/gui/DetailEvent.fxml"));
                               setNode(page1);
    
    }

    @FXML
    private void affichage_1(ActionEvent event) throws IOException {
            DetailEventController.id = idevent1;
                    AnchorPane page1 = FXMLLoader.load(getClass().getResource("/pidev/gui/DetailEvent.fxml"));
                               setNode(page1);
    }

    @FXML
    private void affichage_3(ActionEvent event) throws IOException {
            DetailEventController.id = idevent3;
                    AnchorPane page1 = FXMLLoader.load(getClass().getResource("/pidev/gui/DetailEvent.fxml"));
                               setNode(page1);
    }

    @FXML
    private void affichage_4(ActionEvent event) throws IOException {
            DetailEventController.id = idevent4;
                    AnchorPane page1 = FXMLLoader.load(getClass().getResource("/pidev/gui/DetailEvent.fxml"));
                               setNode(page1);
    }

    @FXML
    private void suivant_event(MouseEvent event) {
         a=a+6;b=b+6;c=c+6;d=d+6;
         eventprecedent.setVisible(true);
            buildData();
                 if (b == i || a == i || c == i || d == i)
        {
            eventsuivant.setVisible(false);
        }
    }
    
    
    
   
  
    
    

    @FXML
    private void event_precedent(MouseEvent event) {
         a=a-6;b=b-6;c=c-6;d=d-6;
 eventsuivant.setVisible(true);
       
 if (a == 0)
        {
            eventprecedent.setVisible(false);
        }
            buildData();
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
    private void ajouter(ActionEvent event) throws IOException {
        
        
                AnchorPane page1 = FXMLLoader.load(getClass().getResource("/pidev/gui/ajouterevent.fxml"));
                               setNode(page1);

        
    }
         
              
              
              
}
