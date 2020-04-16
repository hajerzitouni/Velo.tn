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
import pidev.entities.Event;
import pidev.util.connexionBD;

/**
 *
 * @author Admin
 */
public class EventService {
            Connection c = connexionBD.getInstance().getCnx();
        
    public static EventService su;

    public static EventService getInstance() {
        if(su == null )
            su = new EventService();
        return su;
            
    }
    
    
    public void ajouter(Event ev) {
        try {
            Statement st = c.createStatement();
            String req = "insert into event (nomevet,lieuevt,description,nbparticipent,date_debe,date_fine,prixe,nbsignal,nom_image,creator_id) values('"+ev.getNomevet()+"','"+ev.getLieuevt()+"','"+ev.getDescription()+"','"+ev.getNbparticipent()+"','" + new Timestamp(ev.getDate_debe().getTime()) +"','"+new Timestamp(ev.getDate_fine().getTime())+ "','"+ev.getPrixe()+"','"+ev.getNbsignal()+"','"+ev.getNom_image()+"','"+ev.getCreator_id()+"')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
    }
    
     public void modifier(Event ev , int id) {
        try {
            PreparedStatement pt = c.prepareStatement("update event set nomevet = ?  ,  lieuevt = ? , description = ? , nbparticipent = ? , date_debe = ? , date_fine = ? , prixe = ?, isActive = ? , nbsignal = ?  where id = ? ");

           
            pt.setString(1, ev.getNomevet());
             pt.setString(2, ev.getLieuevt());
             pt.setString(3, ev.getDescription());
             pt.setInt(4, ev.getNbparticipent());           
             pt.setTimestamp(5,new Timestamp(ev.getDate_debe().getTime()));
             pt.setTimestamp(6,new Timestamp(ev.getDate_fine().getTime()));
             pt.setInt(7, ev.getPrixe());
             pt.setInt(8, ev.getNbsignal());
             pt.setBoolean(9, ev.getIsActive());
             pt.setInt(10, id);
             
             pt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
    }
    
    
     public void afficher() {
        try {
            PreparedStatement pt = c.prepareStatement("SELECT * FROM event");
            ResultSet result = pt.executeQuery();
            while (result.next()) {
        System.out.println(result.getInt(1)+"  "+result.getString(2)+"  "+result.getString(3)+" "+result.getString(4)); 
            }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
    }
    
    public ResultSet afficherevents() {
                ResultSet rs = null;
        try {
            PreparedStatement pt = c.prepareStatement("SELECT * FROM event");
            rs = pt.executeQuery();
    
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
            return rs;
    }
        public ResultSet afficherevents(int creatorId) {
                ResultSet rs = null;
        try {
            PreparedStatement pt = c.prepareStatement("SELECT * FROM event WHERE creator_id = ?");
                        pt.setInt(1, creatorId);

            rs = pt.executeQuery();
    
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
            return rs;
    }
    
      public Event affichereventparid(int id) {
         Event  evt= new Event();
            try {
            
           
            PreparedStatement pt = c.prepareStatement("select * from event WHERE id = ?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
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
           
                 

            }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        
        return evt;
    }
    
    
       
    public void supprimerevent(int id) {
        try {
            PreparedStatement pt = c.prepareStatement("delete from event where id = ?");
            pt.setInt(1, id);
            pt.execute();
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
    }
    
      public void Signaler(Event evt) {
       try {
            PreparedStatement pt = c.prepareStatement("update event set nbsignal = ? , isActive = ?  where id = ? ");
         if(evt.getNbsignal() >= 5){
            pt.setBoolean(2, false);
            
          }
         else{
          pt.setBoolean(2, true);
         }
        pt.setInt(1 , evt.getNbsignal());
        // le 2 position de l'id dans la requette

        pt.setInt(3 , evt.getId());
          pt.executeUpdate();
        
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
       }
        public void Participer(Event evt) {
       try {
            PreparedStatement pt = c.prepareStatement("update event set nbparticipent = ?  where id = ? ");

        pt.setInt(1 , evt.getNbparticipent());
        // le 2 position de l'id dans la requette

        pt.setInt(2 , evt.getId());
          pt.executeUpdate();
        
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
       }
          
          
          
      
    
}
