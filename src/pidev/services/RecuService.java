/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pidev.entities.Comment;
import pidev.entities.Recu;
import pidev.util.connexionBD;

/**
 *
 * @author Manel
 */
public class RecuService {
     Connection c = connexionBD.getInstance().getCnx();
        
    public static RecuService su;

    public static RecuService getInstance() {
        if(su == null )
            su = new RecuService();
        return su;
            
    }
    
    
    public void ajouterRecu(Recu rc ) {
        try {
            Statement st = c.createStatement();
          
            String req = "insert into recu (prixrecu , user_id, event_id) values('"+rc.getPrixrecu()+"','"+rc.getUser_id()+"','"+rc.getEvent_id()+"')";
           String req2 = "insert into user_event (user_id, event_id) values('"+rc.getUser_id()+"','"+rc.getEvent_id()+"')";

            st.executeUpdate(req);
            st.executeUpdate(req2);
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
    }

     public void modifierRecu(Recu rc  , int id) {
        try {
            PreparedStatement pt = c.prepareStatement("update recu  set prixrecu = ?  ,  user_id = ? , event_id= ? where id = ? ");

           
            pt.setInt(1, rc.getPrixrecu());
             pt.setInt(2, rc.getUser_id());
             pt.setInt(3, rc.getEvent_id());
        
             pt.setInt(4, id);
             
             pt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
    }
    
     public void supprimerRecu(int eventId ,int userId) {
        try {
            PreparedStatement pt = c.prepareStatement("delete from recu where event_id = ? AND user_id = ?");
            pt.setInt(1, eventId);
            pt.setInt(2, userId);
            pt.execute();
            PreparedStatement pt2 = c.prepareStatement("delete from user_event where event_id = ? AND user_id = ?");
            pt2.setInt(1, eventId);
            pt2.setInt(2, userId);
            pt2.execute();
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
    }
    
     
     
       public ResultSet afficherRecuParEvent(int eventId) {
                ResultSet rs = null;
        try {
            PreparedStatement pt = c.prepareStatement("SELECT * FROM recu WHERE event_id = ?  ");
            pt.setInt(1, eventId);
            rs = pt.executeQuery();
     
    
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
            return rs;
    }
       
       
       
       
        public ResultSet afficherAllRecu() {
                ResultSet rs = null;
        try {
            PreparedStatement pt = c.prepareStatement("SELECT * FROM recu");
            rs = pt.executeQuery();
    
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
            return rs;
    }
     
        
        
        
      public void afficherRecuConsole() {
        try {
            PreparedStatement pt = c.prepareStatement("SELECT * FROM recu");
            ResultSet result = pt.executeQuery();
            while (result.next()) {
        System.out.println(result.getInt(1)+"  "+result.getString(2)+"  "+result.getString(3)+" "+result.getString(4)); 
            }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
    }
    
}
