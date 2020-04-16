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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Hajer
 */
public class reclamationService {
      private Connection con;
  

    
    public reclamationService()
    {
        con = MyBdd.getInstance().getConnexion();//pour s'assurer que la connexion a la base se fait une seule fois

    }
    
    
  public void ajouter(reclamation r) throws SQLException {
        String req = "INSERT INTO reclamation(titrereclam,probleme) VALUES ( ?,?) ";
        PreparedStatement pstm = con.prepareStatement(req);
    
        pstm.setString(1, r.getTitrereclam());
        pstm.setString(2, r.getProbleme());
        pstm.executeUpdate();
    }
  
  public List<reclamation> getAllreclam()throws SQLException  {

        List<reclamation> reclamations = new ArrayList<>();

            String req = "SELECT * FROM reclamation";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(req);
            while (rs.next()) {
             reclamation r = new reclamation (rs.getInt(1),
                     rs.getInt("user_id"),
                     rs.getString("probleme"),
                                 rs.getString("titrereclam"));
                      reclamations.add(r);
        }

        return reclamations;
    }
  
  public void delete(int id) throws SQLException {
        PreparedStatement pre= con.prepareStatement("delete from reclamation where Id=?"); //executer une requette parametrer.
		pre.setInt(1, id);
		pre.executeUpdate();
                   System.out.println("reclamation deleted ...");     
        
    }
  
   public void modifier(int Id,reclamation r) {
     String sql = "UPDATE   reclamation SET user_id=?,probleme=?,titrereclam=? WHERE Id='"+Id+"' ";

        PreparedStatement st;
        try {
           
            st = con.prepareStatement(sql);
            st.setInt(1,r.getUser_id());
            st.setString(2,r.getProbleme());
            st.setString(3,r.getTitrereclam());
       
        st.executeUpdate();
        System.out.println("update done!");
        
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
   public ObservableList<reclamation>recherche(String Titrereclam) {
        
        String requete = "SELECT * FROM  reclamation where titrereclam = '"+Titrereclam+"' " ;
        PreparedStatement pst;
        ObservableList<reclamation> list= FXCollections.observableArrayList();
            

        try {
            pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                
            int id=rs.getInt(1);
               String titrereclam =rs.getString("titrereclam");
              String probleme =rs.getString("probleme");
               reclamation r =new reclamation (id, titrereclam,probleme);
            list.add(r);
            }
            
        
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list;
          
    }
       
}
