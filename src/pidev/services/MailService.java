/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Manel 
 */






public class MailService {
           private static final String host ="smtp.gmail.com";
           private static  final String user = "projet.pidev1@gmail.com";
           private static final String pass = "pidev1234";
         
           private static final String from = "projet.pidev1@gmail.com";   
     public static void envoyer(String to,  String  subject , String messageText , String image ) throws IOException 
{
        try{

          
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
            msg.setText(messageText);

            ////////////////////envoie image
       
            ////1
            File file = new File(image);
         FileDataSource datasource1 = new FileDataSource(file);
         DataHandler handler1 = new DataHandler(datasource1);
           MimeBodyPart autruche = new MimeBodyPart();
       try {
    autruche.setDataHandler(handler1);
    autruche.setFileName(datasource1.getName());
          } catch (MessagingException e) {
        }
       //////2
       MimeBodyPart content = new MimeBodyPart();
try {
    
    content.setContent(messageText, "text/plain");
} catch (MessagingException e) {
}
 ////////3      
       
       
       MimeMultipart mimeMultipart = new MimeMultipart();
         try {
    mimeMultipart.addBodyPart(content);
    mimeMultipart.addBodyPart(autruche);
   
    } catch (MessagingException e) {
    e.printStackTrace();
}
 //////////////////////ajouter img au msg////////////////////////////////////////
        msg.setContent(mimeMultipart);
            
           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }
        catch(MessagingException ex)
        {
            
         System.out.println(ex);
        
        }

}   
        
    
}
