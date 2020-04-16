/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static application1.Application1.fos_user;
import application1.fos_userService;
import application1.fos_user;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import utils.BCrypt;

/**
 * FXML Controller class
 *
 * @author ouertani
 */
public class RecoverController implements Initializable {

    @FXML
    private Button covbutton;
    @FXML
    private VBox VBoxMdp;
    @FXML
    private TextField txtemail;
    @FXML
    private Label txtetat;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void recovermdp(ActionEvent event) throws SQLException {
            if (!txtemail.getText().isEmpty() && txtemail.getText().contains("@")) {
            fos_userService usr = new fos_userService();

            fos_user user = new fos_user();
            user = usr.getUserByEmail(txtemail.getText());
            if (user != null) {
                String plainpassword = getSaltString();
                String password = BCrypt.hashpw(plainpassword, BCrypt.gensalt());
                System.out.println("Le nouveau mot de passe de " + user.getEmail() + "est " + plainpassword);

                usr.changePassword(password, txtemail.getText());
                sendEmail(user, plainpassword);
                txtemail.setVisible(false);
                txtetat.setText("Mot de passe envoyé par email.");
            } else {
                txtetat.setText("Utilisateur introuvable");
            }

        }
    }

    @FXML
    private void Back(ActionEvent event) {
    }
    
        protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
            public void sendEmail(fos_user compte, String plainpassword) {
                try {
            String host = "smtp.gmail.com";
            String user = "aymen.ouertani@esprit.tn";
            String pass = "193JMT2693";
            String to = compte.getEmail();
            String from = "aymen.ouertani@esprit.tn";
            String subject = " Nouveau mot de passe";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};

            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            //msg.setSentDate(new Date());

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();

            messageBodyPart = new MimeBodyPart();

            messageBodyPart.setText("Bonjour " + compte.getUsername() + ", votre nouveau mot de passe est : " + plainpassword);
            multipart.addBodyPart(messageBodyPart);

            msg.setContent(multipart);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("Email envoyé");

        } catch (Exception ex) {
            System.out.println(ex);
        }
            }
    
}
