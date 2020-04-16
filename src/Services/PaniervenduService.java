/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Paniervendu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.MyDbConnection;

/**
 *
 * @author asus
 */
public class PaniervenduService {
    private Connection cnx;
    private Statement stm;
    private PreparedStatement pstm;
    private ResultSet rs;

    public PaniervenduService() {
        cnx = MyDbConnection.getInstance().getConnexion();
    }
    
    ///// Ajouter Panier vendu  //////

 public void ajouterpaniervendu (Paniervendu paniervendu){
        
        String req = "insert into PanierVendu (produit_id, client_id, commande_id, quantite) values(?, ?, ?, ?);";
        try {
                PreparedStatement pst = cnx.prepareStatement(req);
                pst.setInt(1, paniervendu.getProduit_id());
                pst.setInt(2, paniervendu.getClient_id());
                pst.setInt(3, paniervendu.getCommande_id());
                pst.setInt(4, paniervendu.getQuantite());
                pst.executeUpdate();
                System.out.println("panier vendu crée!");
            } catch (SQLException ex) {
                Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }    
}
