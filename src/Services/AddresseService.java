/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Addresse;
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
import pidev.MyDbConnection;

/**
 *
 * @author asus
 */
public class AddresseService {
    
    private Connection cnx;
    private Statement stm;
    private PreparedStatement pstm;
    private ResultSet rs;

    public AddresseService() {
        cnx = MyDbConnection.getInstance().getConnexion();
    }
    
    
      /////////////Ajouter///////////////////////
    // @Override
    public void Ajouteraddresse(Addresse a) throws SQLException {
     
                    
              String req = "insert into addresse (id, numTel, mail, pays, ville, pinCode) values(?, ?, ?, ?, ?, ?);";
        try {
                PreparedStatement pst = cnx.prepareStatement(req);
                pst.setInt(1, a.getId());
                pst.setInt(2, a.getNumTel());
                pst.setString(3, a.getMail());
                pst.setString(4, a.getPayer());
                pst.setString(5, a.getVille());
                pst.setInt(6, a.getPincode());
                pst.executeUpdate();
                System.out.println("addresse crée!");
                
   
        } catch (SQLException ex) {
            Logger.getLogger(AddresseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
