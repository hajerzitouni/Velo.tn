/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hajer
 */
public class assuranceService {
    private Connection con;
  

    
    public assuranceService()
    {
        con = MyBdd.getInstance().getConnexion();//pour s'assurer que la connexion a la base se fait une seule fois

    }
    public void ajouter(asurance a) throws SQLException {
        String req = "INSERT INTO asurance (velo_id,montant) VALUES (?,?)";
         try {
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setInt(1, a.getVelo_id());
        pstm.setInt(2,a.getMontant());
       
        pstm.executeUpdate();
        System.out.println("assurance added ...");
    } catch (SQLException ex) {
            Logger.getLogger(assuranceService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
      public List<asurance> getAllassurance()throws SQLException  {

        List<asurance> assurances = new ArrayList<>();

            String req = "SELECT * FROM asurance";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(req);
            while (rs.next()) {
             asurance a = new asurance(rs.getInt(1),
                     rs.getInt("montant"),
                     rs.getInt("velo_id"));
                      assurances.add(a);
        }

        return assurances;
    }
      
  public void delete(int id) throws SQLException {
        PreparedStatement pre= con.prepareStatement("delete from asurance where Id=?"); //executer une requette parametrer.
		pre.setInt(1, id);
		pre.executeUpdate();
                   System.out.println("assurance deleted ...");     
        
    }
   public void modifier(int Id,asurance a) {
     String sql = "UPDATE asurance SET montant=?,velo_id=? WHERE Id='"+Id+"' ";

        PreparedStatement st;
        try {
           
            st = con.prepareStatement(sql);
            st.setInt(1,a.getMontant());
            st.setInt(2,a.getVelo_id());
            
           st.executeUpdate();
        System.out.println("update done!");
        
        } catch (SQLException ex) {
            Logger.getLogger(assuranceService.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
  
}
