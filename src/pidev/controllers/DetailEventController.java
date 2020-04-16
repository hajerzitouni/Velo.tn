/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controllers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import pidev.entities.Comment;
import pidev.entities.Event;
import pidev.entities.Recu;
import pidev.entities.User;
import pidev.services.CommentService;
import pidev.services.EventService;
import pidev.services.MailService;
import pidev.services.ProfanityFilter;
import pidev.services.RecuService;
import pidev.services.UserService;
import pidev.pidev;
/**
 * FXML Controller class
 *
 * @author Manel
 */
public class DetailEventController implements Initializable {
private ObservableList<Comment> data;
    @FXML
    private Label nomevent;
    @FXML
    private Label lieuxevent;
    @FXML
    private Label datedebe;
    @FXML
    private Text description;
    @FXML
     private TextArea commentaires;
    @FXML
    private Label prix;
    @FXML
    private JFXButton modifierevent;
    @FXML
    private JFXButton save;
    @FXML
    private JFXButton supprimerevent;
    @FXML
    private TableView<Comment> listCommentaires;
    @FXML
    private TableColumn<Comment , Integer> user;
    @FXML
    private TableColumn<Comment, Integer> commentaire;
    @FXML
    private JFXButton supprimercomment;
    @FXML
    private JFXButton modifiercommet;
    @FXML
    private JFXButton annulerparticipation;
    @FXML
    private JFXButton participerevent;
    @FXML
    private JFXButton signalerevent;
    @FXML
    private JFXButton ajoutercomment;
    @FXML
    private ImageView image;
    @FXML
    private AnchorPane holderPane;  
      @FXML
    private Label prixevent;
    @FXML
    private Label nb;
    public static int  id;
    private static final String QR_CODE_IMAGE_PATH = "./MyQRCode.png";
    
      @FXML
    private Button btnretour;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
      modifiercommet.setVisible(false);
        supprimercomment.setVisible(false);
         save.setVisible(false);
        EventService evt = new EventService();
        Event e = evt.affichereventparid(id);
        nomevent.setText(e.getNomevet());
        lieuxevent.setText(e.getLieuevt());
        prix.setText(Integer.toString(e.getPrixe()));
        description.setText(e.getDescription());
        nb.setText(Integer.toString(e.getNbparticipent()));
        datedebe.setText(formatter.format(e.getDate_debe()));
        //date_fine.setValue(e.getDate_fine().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        String img ="file:///C://wamp/www//pidev_v1//web//images//" + e.getNom_image();
        image.setImage(new Image(img));
        
        
        //////lister commentaires : 
      
        user.setCellValueFactory(new PropertyValueFactory<Comment, Integer>("user_name"));
        commentaire.setCellValueFactory(new PropertyValueFactory<Comment, Integer>("commentaire"));
     
        buildData();
        if(e.getNbparticipent() == 0 || e.getDate_debe().compareTo(new Date()) < 0 ){
        participerevent.setVisible(false);
        }
        if(e.getCreator_id() == pidev.user_id){
        participerevent.setVisible(false);
        signalerevent.setVisible(false);
        annulerparticipation.setVisible(false);
        modifierevent.setVisible(true);
        supprimerevent.setVisible(true);   
        }
        else  {
        modifierevent.setVisible(false);
        supprimerevent.setVisible(false);  
        }
        
       //listener 
        listCommentaires.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                   Comment cmt =(Comment)listCommentaires.getSelectionModel().getSelectedItem();  
if(newSelection != null){
                   if(cmt.getUser_id() == pidev.user_id){
      modifiercommet.setVisible(true);
        supprimercomment.setVisible(true);
                    }
                    else
                    {
                              modifiercommet.setVisible(false);
        supprimercomment.setVisible(false);
                    }
}
});
    }  
    
     private void buildData() {
         CommentService su = CommentService.getInstance();
        data=FXCollections.observableArrayList();
               UserService usr = UserService.getInstance(); 
        try {
             ResultSet rs = su.afficherCommentsParEvent(id);
            while(rs.next()){
                User user = usr.afficheruserparid(rs.getInt(3));
                Comment cmt = new Comment();
                cmt.setId(rs.getInt(1));
                cmt.setEvent_id(rs.getInt(4));
                cmt.setUser_id(rs.getInt(3));
                cmt.setUser_name(user.getUsername());
                cmt.setCommentaire(rs.getString(2));
             
                System.out.println(cmt.getEvent_id());
               
              data.add(cmt);
            }
            
            listCommentaires.setItems(data);

        } catch (SQLException ex) {
            Logger.getLogger(ListCommentAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifierevent(ActionEvent event) throws IOException {
       
        ModifiereventController.id = id;
           AnchorPane page1 = FXMLLoader.load(getClass().getResource("/pidev/gui/ModifierEvent.fxml"));
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
    private void supprimerevent(ActionEvent event) {
           EventService evt = new EventService();
           evt.supprimerevent(id);
    }

    @FXML
    private void supprimercommet(ActionEvent event) {
        CommentService cmt = new CommentService();
       Comment cmts =(Comment)listCommentaires.getSelectionModel().getSelectedItem();  
        cmt.supprimerComment(cmts.getId());
         buildData();
    }

    @FXML
    private void modifiercomment(ActionEvent event) {
        save.setVisible(true);
       Comment cmts =(Comment)listCommentaires.getSelectionModel().getSelectedItem();  
       commentaires.setText(cmts.getCommentaire());
 ajoutercomment.setVisible(false);
        
        
    }

    @FXML
    private void annulerparticipation(ActionEvent event) {
        
 EventService  evt = new EventService();
 RecuService rc = new RecuService() ; 
 Event e = evt.affichereventparid(id);
   rc.supprimerRecu(id, pidev.user_id);
   e.setNbparticipent(e.getNbparticipent()+1);
   annulerparticipation.setVisible(false);
   participerevent.setVisible(true);
   
    }
    
    
    
    

    @FXML
    private void participerevent(ActionEvent event) throws IOException, ParseException {
          MailService ml = new MailService();
          EventService evt = new EventService();
          RecuService rc = RecuService.getInstance();
          Event e = evt.affichereventparid(id);
          UserService usr = UserService.getInstance(); 
          User user = usr.afficheruserparid(pidev.user_id);
          DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
          Recu recu = new Recu(e.getPrixe(), id, pidev.user_id);
          rc.ajouterRecu(recu);
             participerevent.setVisible(false);
             annulerparticipation.setVisible(true);
      

    try {
        e.setNbparticipent(e.getNbparticipent()-1);
        evt.Participer(e);
        String contenu = "vous avez participez a l'evenement " + e.getNomevet()+ " qui aura lieu le " + formatter.format(e.getDate_debe()) + " à "+e.getLieuevt();
        generateQRCodeImage(contenu, 350, 350, QR_CODE_IMAGE_PATH);
        
    } catch (WriterException ex) {
        Logger.getLogger(DetailEventController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(DetailEventController.class.getName()).log(Level.SEVERE, null, ex);
    }
    ml.envoyer(user.getEmail() ,e.getNomevet(),"ceci est votre ticket ",QR_CODE_IMAGE_PATH);

    }
    
    private static void generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
    
    
    
    
    
    @FXML
    private void signalerevent(ActionEvent event) throws IOException {
      EventService evt =  EventService.getInstance();
        Event e = evt.affichereventparid(id);
        e.setNbsignal(e.getNbsignal()+1);
        evt.Signaler(e);
      signalerevent.setVisible(false);
        if(e.getNbsignal()== 5){
         MailService ml = new MailService();
        UserService usr = UserService.getInstance(); 
        RecuService src = RecuService.getInstance();
        
        

            try {
                ResultSet rs = src.afficherRecuParEvent(e.getId());
                while(rs.next()) {
                  User user = usr.afficheruserparid(rs.getInt(3));
                      String img ="C:\\\\wamp\\\\www\\\\pidev.java" + e.getNom_image();
                   ml.envoyer( user.getEmail(),e.getNomevet(),"cet evenement a été annulé ",img);

                    
               
                }  
            } catch (SQLException ex) {
                Logger.getLogger(ListerEventAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
   }
     @FXML
    private void saveHandler(ActionEvent event) {
        
        
                        
        CommentService cmt = new CommentService();
       Comment cmts =(Comment)listCommentaires.getSelectionModel().getSelectedItem();  
       ProfanityFilter filter = new ProfanityFilter();
       filter.buildDictionaryTree("badwords.txt");
       cmts.setCommentaire(filter.filterBadWords(commentaires.getText()));
        cmt.modifierComment(cmts,cmts.getId());
         buildData();
          commentaires.setText("");
          save.setVisible(false);
                    ajoutercomment.setVisible(true);

   }
    @FXML
    private void ajoutercommet(ActionEvent event) {
        
                Comment u = new Comment(
     
                commentaires.getText(),
              pidev.user_id,id
                ) ;
                  ProfanityFilter filter = new ProfanityFilter();
       filter.buildDictionaryTree("badwords.txt");
       u.setCommentaire(filter.filterBadWords(commentaires.getText()));
                  
                  CommentService cm =  CommentService.getInstance();
                  cm.ajouterComment(u);
                  
                  buildData(); 
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("commentaire ajouté avec succé!");
        alert.show();
    }
 
    
    
    
    
      @FXML
    private void retourner(ActionEvent event) throws IOException {
           if(pidev.fos_user.getRoles().contains("ROLE_ADMIN")){
                AnchorPane page1 = FXMLLoader.load(getClass().getResource("/pidev/gui/ListerEventAdmin.fxml"));
                        setNode(page1);

        }
        else {
            AnchorPane page1 = FXMLLoader.load(getClass().getResource("/pidev/gui/AllEvents.fxml"));
        setNode(page1);

        }


    }
    
    
    
}
