/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Commande;
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
public class CommandeService {
    
    private Connection cnx;
    private Statement stm;
    private PreparedStatement pstm;
    private ResultSet rs;

    public CommandeService() {
        cnx = MyDbConnection.getInstance().getConnexion();
    }
    
   /////////////Ajouter///////////////////////
    // @Override
    public void Ajoutercommande(Commande a) throws SQLException {
   String req = "insert into commande (id, client_id, prixtotal, etat, addresse_id) values(?, ?, ?, ?, ?);";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, a.getId());
            pst.setInt(2, a.getClient_id());
            pst.setDouble(3, a.getPrixtotal());
            pst.setString(4, a.getEtat());
            pst.setInt(5, a.getAddresse_id());
            pst.executeUpdate();
            System.out.println("commande crée!");
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
 //////////// Modifier  ///////
 
      public void modifiercommande(int id ) {
        
        String req = "update commande set etat = ? where id = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, "traite");
            pst.setInt(2, id);
            pst.executeUpdate();
            System.out.println("commade traite!");
        } catch (SQLException ex) {
        }

    }

      
      
    ///// Afficher tt les cmd////
 
      public ObservableList<Commande> getcommande(){
       ObservableList<Commande> Commande = FXCollections.observableArrayList();       
        String req = "select * from Commande ";
    try {
        stm = cnx.createStatement();
        rs =  stm.executeQuery(req);
 
        while(rs.next()){
            Commande p =new Commande (rs.getInt("id"),
                           rs.getInt("addresse_id"),
                           rs.getInt("client_id"),
                           rs.getString("etat"),
                           rs.getDouble("prixtotal"));                   
               Commande.add(p);                
        }
    }
    catch (SQLException ex) {
         Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
    }        
        System.out.println("liste des commandes recupere");
        return Commande;
    }
      
    ///// Afficher tt les cmd de chaque client ////
 
      public ObservableList<Commande> getcommandeclient(int client_id){
       ObservableList<Commande> Commande = FXCollections.observableArrayList();       
        String req = "select * from Commande where client_id =" + client_id ;
    try {
        stm = cnx.createStatement();
        rs =  stm.executeQuery(req);
        
        
        while(rs.next()){
            Commande p =new Commande (rs.getInt("id"),
                           rs.getInt("addresse_id"),
                           rs.getInt("client_id"),
                           rs.getString("etat"),
                           rs.getDouble("prixtotal"));
                           
                                
               Commande.add(p);                
        }
    }
    catch (SQLException ex) {
         Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
    }        
        System.out.println("liste des commandes recupere");
        return Commande;
    }
      
      
      
      
      ////////// supprimer cmd ///////
       public void supprimercommande(int id) {
        String req = "delete from commande where id = ?;";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("commade supprimée!");
        } catch (SQLException ex) {
        }
    }
       
       
       
         ///////// Recherche /////////

 public ObservableList<Commande> cherchercmd(String mot){
        ObservableList<Commande> list = FXCollections.observableArrayList();
        String req = "select * from commande where etat like '"+mot+"';";
        try {
           pstm = cnx.prepareStatement(req);
            
           rs = pstm.executeQuery();
            while (rs.next()) {
                Commande p = new Commande(rs.getInt(1), rs.getInt(2),rs.getInt(3), rs.getString(4), rs.getDouble(5));
               
                list.add(p);
            }
        } catch (SQLException ex) {
             Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("liste commandes admin recherche recupere");
        return list;
    }
 
 
    
}
