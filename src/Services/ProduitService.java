/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Produit;
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
public class ProduitService implements Services {
    
    private Connection cnx;
    private Statement stm;
    private PreparedStatement pstm;
    private ResultSet rs;

    public ProduitService() {
        cnx = MyDbConnection.getInstance().getConnexion();
    }
     
    /////////////Ajouter///////////////////////
    // @Override
    public void Ajouterproduit(Produit a) throws SQLException {
 
        
                      
              String req = "insert into Produit (id,nom,Categorie_id,quantite,prix,image) values(?, ?, ?, ?, ?, ?)";
        try {
                pstm = cnx.prepareStatement(req);
                pstm.setInt(1, a.getId());
                pstm.setString(2, a.getNom());
                pstm.setInt(3, a.getCategorie_id());
                pstm.setInt(4, a.getQuantite());
                pstm.setDouble(5, a.getPrix());
                pstm.setString(6, a.getImage());
                pstm.executeUpdate();
                System.out.println("Produit ajouté!");
                
   
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    /////// supprimer //////
    
     public void supprimerproduit(int id) {

        try {
            String req = "delete from Produit where id = " + id;

           
            try {
                stm = cnx.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            }
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     /////Modifier///////
     public void  modifierproduit(Produit p) {

        try {
             stm = cnx.createStatement();
             stm.executeUpdate("UPDATE Produit SET nom='"+p.getNom()+"',quantite='"+p.getQuantite()+"',prix='"+p.getPrix()+
                        "' WHERE id= "+p.getId());
                    }   
        catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }}
     
      public void modifierproduit2( int id ) {
        try {
            String req = "update Produit where id=" + id + "'";
         
            try {
                stm = cnx.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            }
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ///// Afficher tt les produits////
    
      public ObservableList<Produit> getproduit(){
      ObservableList<Produit> Produit = FXCollections.observableArrayList();       
        String req = "select * from produit ";
    try {
        stm = cnx.createStatement();
        rs =  stm.executeQuery(req);
        
        while(rs.next()){
            Produit p =new Produit(rs.getInt("id"),
                           rs.getString("nom"),
                           rs.getInt("categorie_id"),
                           rs.getInt("quantite"),
                           rs.getDouble("prix"),
                           rs.getString("image"));      
               Produit.add(p);                
        }
    }
    catch (SQLException ex) {
         Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
    }        
        return Produit;
    }
      
  
      /////// Afficher les velos /////
      
  public List<Produit> getvelos() throws SQLException {
       List<Produit> Produit = new ArrayList<>();
        
        String req = "select * from produit where categorie_id = 1";
        try {
        stm = cnx.createStatement();
        rs =  stm.executeQuery(req);
        
        while(rs.next()){
              Produit p =new Produit(rs.getInt("id"),
                           rs.getString("nom"),
                           rs.getInt("categorie_id"),
                           rs.getInt("quantite"),
                           rs.getDouble("prix"),
                           rs.getString("image")); 
            Produit.add(p);
        }
    }

    catch (SQLException ex) {
         Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        return Produit;
    }
  
  /////// Afficher les accessoires /////
  
  public List<Produit> getAccessoires() throws SQLException {
       List<Produit> Produit = new ArrayList<>();
        
        String req = "select * from produit where categorie_id = 2 ";
          try {
        stm = cnx.createStatement();
        rs =  stm.executeQuery(req);
        
        while(rs.next()){
               Produit p =new Produit(rs.getInt("id"),
                           rs.getString("nom"),
                           rs.getInt("categorie_id"),
                           rs.getInt("quantite"),
                           rs.getDouble("prix"),
                           rs.getString("image")); 
            Produit.add(p);
        }
    }

    catch (SQLException ex) {
         Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        return Produit;
    }
  ////////// get prix prod //////
  
   public int getprixproduit(int id) {

        String req = "select prix from Produit where id = ? ";
        int price = 0;
        try {
            pstm = cnx.prepareStatement(req);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                price = rs.getInt(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return price;
    }


    
  ///////// Recherche /////////

 public List<Produit> chercherproduit(String nom) {
        List<Produit> Produit = new ArrayList();
        String req = "select * from Produit where nom like " + nom;
        try {
            stm = cnx.createStatement();
            rs = stm.executeQuery(req);
            while (rs.next()) {    
                   Produit p =new Produit(rs.getInt("id"),
                           rs.getString("nom"),
                           rs.getInt("categorie_id"),
                           rs.getInt("quantite"),
                           rs.getDouble("prix"),
                           rs.getString("image")); 
                   Produit.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Produit;
    }  
  
}
