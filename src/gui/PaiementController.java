/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.stripe.model.Charge;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import pidev.entities.PaiementStripe;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import pidev.controllers.LoginController;


/**
 * FXML Controller class
 *
 * @author abder
 */
public class PaiementController implements Initializable {

    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField numeroCarte;
    @FXML
    private JFXTextField MoisValidite;
    @FXML
    private JFXTextField AnneeValidite;
    @FXML
    private JFXPasswordField ccvTextField;
    @FXML
    private JFXButton btnValider;
    @FXML
    private JFXButton btnAnnuler;
    @FXML
    private JFXTextField montant;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void validerFunction(ActionEvent event) throws IOException {
        
        
            int mois = Integer.parseInt(MoisValidite.getText());
        int annee = Integer.parseInt(AnneeValidite.getText());
        
        
        
        
        com.stripe.model.Token token = PaiementStripe.getToken("pk_test_AuAMdXwE57NnBcd4Xld65Ez4", numeroCarte.getText(), mois, annee, ccvTextField.getText(), email.getText());
      
            if(token !=null){
                
                 Charge ch= PaiementStripe.ChargePayement("rk_test_oGfrFNOjpnRPklUVzjelPHgf", "usd", "tok_visa",montant.getText().charAt(annee) ,"sk_test_yIqEVjLUzA1vwKhr1PjhnS9I", numeroCarte.getText(), mois, annee, ccvTextField.getText(), email.getText());
                 
                 
                 
                 
            String tit = "Paiement réussi";
            String message = "Votre paiement a été traité avec succès";
            NotificationType notification = NotificationType.SUCCESS;
    
            TrayNotification tray = new TrayNotification(tit, message, notification);          
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(javafx.util.Duration.seconds(2));
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Home.fxml"));
        AnchorPane root = (AnchorPane) loader.load();
      
         sendEmail();
        email.getScene().setRoot(root);
            }
    }
            

    @FXML
    private void AnnulerFunction(ActionEvent event) throws IOException {
        
     
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/affichercoursclient.fxml"));
        AnchorPane root = (AnchorPane) loader.load();
        email.getScene().setRoot(root);
    }
    
        
     public void sendEmail(){
     try{
            String host ="smtp.gmail.com" ;
            String user = "ahmedissiou1@gmail.com ";
            String pass = "unique50942";
            String to = " ";
            String from = "ahmedissiou1@gmail.com";
            String subject = "Cupcake - Paiement effectué";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
           
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
                
            
            MimeBodyPart messageBodyPart = new MimeBodyPart();
        Multipart multipart = new MimeMultipart();
        
       
    messageBodyPart = new MimeBodyPart();   
  
    messageBodyPart.setText("Bonjour "+ ", votre paiement a été traité avec succès");
    multipart.addBodyPart(messageBodyPart);
    
        
     msg.setContent(multipart);

       
        
        
        
           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
                   System.out.println("Email envoyé");

        }catch(Exception ex)
        {
            System.out.println(ex);
        }
}
}
