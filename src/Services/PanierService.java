/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.MyDbConnection;

/**
 *
 * @author asus
 */
public class PanierService {
    
    private Connection cnx;
    private Statement stm;
    private PreparedStatement pstm;
    private ResultSet rs;
    

    public PanierService() {
        cnx = MyDbConnection.getInstance().getConnexion();
    }
    
    
       /////////////Ajouter///////////////////////
    
    
     public void ajouterproductaupanier(int client_id, int produit_id) throws SQLException {
        System.out.println(testduplication(client_id, produit_id));
        System.out.println(getquantiteproduit(client_id, produit_id));
        if (testduplication(client_id, produit_id) == true) {
            String req = "insert into Panier (produit_id, client_id, quantite) values(?, ?, ?);";
            try {
                pstm = cnx.prepareStatement(req);
                pstm.setInt(1, produit_id);
                pstm.setInt(2, client_id);
                pstm.setInt(3, 1);
                pstm.executeUpdate();
                
                System.out.println("produit ajoutée au panier!");
            } catch (SQLException ex) {
            }
        } else {
            String req = "update Panier set quantite = ? where client_id = ? and produit_id = ?";
            try {
                pstm = cnx.prepareStatement(req);
                pstm.setInt(1, getquantiteproduit(client_id, produit_id) + 1);
                pstm.setInt(2, client_id);
                pstm.setInt(3, produit_id);
                pstm.executeUpdate();
                System.out.println("qt mise up");
            } catch (SQLException ex) {
               
            }
        }

    }
      ///////// teste/////
     
        public boolean testduplication(int client_id, int produit_id) throws SQLException {
        String req = "select * from Panier where client_id = ? and produit_id = ?";
        pstm = cnx.prepareStatement(req);
        pstm.setInt(1, client_id);
        pstm.setInt(2, produit_id);
        rs = pstm.executeQuery();
        System.out.println("*******");
        if (rs.next()) {
            return false;
        }
        return true;
    }
////// get quantité de prd //////
        
    public int getquantiteproduit (int client_id, int produit_id) throws SQLException {
        int qt = 0;
        String req = "select quantite from panier where client_id = ? and produit_id = ?";
        pstm = cnx.prepareStatement(req);
        pstm.setInt(1, client_id);
        pstm.setInt(2, produit_id);
        rs = pstm.executeQuery();
        while (rs.next()) {
          qt = rs.getInt(1);

        }
        return qt;
    }

    
    //////////// Modifier  ///////
      public void modifierpanier(int id, int quantite ) {
 try {
            String req = "update Panier set quantite='" + quantite + "' where id='" + id + "'";
         
            try {
                stm = cnx.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
            }
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
      ////// supprimer produit  from panier ////
      
     public void supprimerprodfrompanier(int id) {
         
        String req = "delete from panier where id = ?;";
        try {
            pstm = cnx.prepareStatement(req);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            System.out.println("produit supprimée du panier!");
        } catch (SQLException ex) {
        }
    }
      
    ///// Afficher panier ////
    
      public   ObservableList getpanier(int client_id){
    
            ObservableList<Panier> list = FXCollections.observableArrayList();

        String req = "select * from panier where client_id =?" ;

        try {
            pstm = cnx.prepareStatement(req);
            pstm.setInt(1, client_id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Panier p = new Panier(rs.getInt(1), rs.getInt(2), rs.getInt(4), rs.getInt(3));
               
                list.add(p);
            }
        } catch (SQLException ex) {
        }
        System.out.println("liste panier recupere");
        return list;
    }
      
     ///// Afficher panier ////
    
      public   List<Panier> getpanier2(int id){

        List<Panier> list = new ArrayList<>();

        String req = "select * from panier where client_id = ?";

        try {
            pstm = cnx.prepareStatement(req);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Panier p = new Panier(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
                list.add(p);
            }
        } catch (SQLException ex) {
        }
        System.out.println("liste panier recupere");
        return list;
    }
      
      
      
      ////// incrementer la quantité ////
      
      
        public void incquantite(int id) {

        String req = "update panier set quantite = ? where id = ?;";
        try {
            pstm = cnx.prepareStatement(req);
            pstm.setInt(1, getquantiteproduit2(id) + 1);
            pstm.setInt(2, id);
            pstm.executeUpdate();
        } catch (SQLException ex) {
        }
    }
        
         public void decquantite(int id) throws SQLException {
        if (getquantiteproduit2(id) == 1) {
            supprimerprodfrompanier(id);
        } else {
            String req = "update panier set quantite = ? where id = ?;";
            try {
                pstm = cnx.prepareStatement(req);
                pstm.setInt(1, getquantiteproduit2(id) - 1);
                pstm.setInt(2, id);
                pstm.executeUpdate();
            } catch (SQLException ex) {
            }
        }

    }

    public int getquantiteproduit2(int id) throws SQLException {
      int qt = 0;
        String req = "select quantite from panier where id = ?";
        pstm = cnx.prepareStatement(req);
        pstm.setInt(1, id);
        rs = pstm.executeQuery();
        while (rs.next()) {
            qt = rs.getInt(1);
        }
        return qt;
    
    }
    
    
    
      /*
       public void supprimerpanier(int id) {

        try {
            String req = "delete from Panier where id = " + id;

           
            try {
                stm = cnx.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
            }
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
*/
       
    
}
