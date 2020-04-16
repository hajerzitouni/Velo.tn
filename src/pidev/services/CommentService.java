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
import java.sql.Timestamp;
import pidev.entities.Comment;
import pidev.entities.Event;
import pidev.util.connexionBD;

/**
 *
 * @author Admin
 */
public class CommentService {
   
            Connection c = connexionBD.getInstance().getCnx();
        
    public static CommentService su;

    public static CommentService getInstance() {
        if(su == null )
            su = new CommentService();
        return su;
            
    }
    
    
    public void ajouterComment(Comment cmt) {
        try {
            Statement st = c.createStatement();
            String req = "insert into comment (commentaire , user_id, event_id) values('"+cmt.getCommentaire()+"','"+cmt.getUser_id()+"','"+cmt.getEvent_id()+"')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
    }
    
    
     public void modifierComment(Comment cmt , int id) {
        try {
            PreparedStatement pt = c.prepareStatement("update comment set commentaire = ?  ,  user_id = ? , event_id= ? where id = ? ");

           
            pt.setString(1, cmt.getCommentaire());
             pt.setInt(2, cmt.getUser_id());
             pt.setInt(3, cmt.getEvent_id());
        
             pt.setInt(4, id);
             
             pt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
    }
    
       
    public void supprimerComment(int id) {
        try {
            PreparedStatement pt = c.prepareStatement("delete from comment where id = ?");
            pt.setInt(1, id);
            pt.execute();
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
    }
    
     public Comment afficherCommentparId(int id) {
         Comment  cmt= new Comment();
            try {
            //cette fonction return un seule commentaire 
           
            PreparedStatement pt = c.prepareStatement("select * from comment WHERE id = ? ");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                cmt.setId(rs.getInt(1));
                cmt.setCommentaire(rs.getString(2));
                cmt.setUser_id(rs.getInt(3));
                cmt.setEvent_id(rs.getInt(4));
           
                 

            }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        
        return cmt;
    }
    
     
     
     
     
      public ResultSet afficherComments() {
                ResultSet rs = null;
        try {
            PreparedStatement pt = c.prepareStatement("SELECT * FROM comment");
            rs = pt.executeQuery();
    
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
            return rs;
      }
      
      
      
      
      
       public ResultSet afficherCommentsParEvent(int eventId) {
                ResultSet rs = null;
        try {
            PreparedStatement pt = c.prepareStatement("SELECT * FROM comment WHERE event_id = ?  ");
            pt.setInt(1, eventId);
            rs = pt.executeQuery();

    
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
            return rs;
    }

    /**
     *
     */
    public void afficherCommentsConsole() {
        try {
            PreparedStatement pt = c.prepareStatement("SELECT * FROM comment");
            ResultSet result = pt.executeQuery();
            while (result.next()) {
        System.out.println(result.getInt(1)+"  "+result.getString(2)+"  "+result.getString(3)+" "+result.getString(4)); 
            }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
    }
    
    
     
}
