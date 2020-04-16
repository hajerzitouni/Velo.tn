package pidev.services;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.entities.reparation;
import pidev.util.Connexion;


/**
 *
 * @author ahmed
 */


       
        public class reparationService {
Connection connexion;
public reparationService() {
       connexion=Connexion.getInstance().getConnexion();
        
}
    
    public void Addreparation(reparation r) {

        String requete = "INSERT INTO reparation(nom,date_debr,date_finr,prixr) VALUES (?,?,?,?)";

        try {

            PreparedStatement st = connexion.prepareStatement(requete);
            st.setString(1, r.getNom());
            st.setString(2, r.getDate_debr());
            st.setString(3, r.getDate_finr());
            st.setInt(4, r.getPrixr());
            st.executeUpdate();
            System.out.println("demande de reparation recu");

        } catch (SQLException ex) {
            Logger.getLogger(reparationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void EditReparation(reparation r ) throws SQLException {

    

   
        Statement st;
       
        try {
             st = connexion.createStatement();
             st.executeUpdate("UPDATE reparation SET nom='"+r.getNom()+"',date_debr='"+r.getDate_debr()+"',date_finr='"+r.getDate_finr()+"',prixr='"+r.getPrixr()+
                        "' WHERE id= "+r.getId());
                    }   
        catch (SQLException ex) {
            Logger.getLogger(reparationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    public void DeleteReparation(int id) throws SQLException {
        String req = "DELETE FROM reparation WHERE id=?";
        PreparedStatement pre = connexion.prepareStatement(req);
        pre.setInt(1, id);
        pre.executeUpdate();

    }

    public reparation FindById(int id) throws SQLException {
        reparation r = new reparation();

        String req = "SELECT * FROM `reparation` WHERE id = ?";
        PreparedStatement pre = connexion.prepareStatement(req);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {

            
            r.setNom(rs.getString("nom"));
            r.setDate_debr(rs.getString("date_debr"));
            r.setDate_finr(rs.getString("date_finr"));
            r.setPrixr(rs.getInt("Prixr"));
            
      

        }

        return r;
    }

    public List<reparation> FindAllreparation() throws SQLException {

        List<reparation> reparation = new ArrayList<>();

        

            String req = "SELECT * FROM reparation";
            Statement st = connexion.createStatement();
            ResultSet result = st.executeQuery(req);
            while (result.next()) {
                reparation r =new reparation(
               result.getInt("id"),
                result.getString("nom"),
                result.getString("date_debr"),
                result.getString("date_finr"),
                result.getInt("prixr"));
                reparation.add(r);
              
            }

         

        
        

        return reparation;
    }
    public ObservableList<reparation>recherche(String nom) {
        
        String requete = "SELECT * FROM  reparation where nom = '"+nom+"' " ;
        PreparedStatement pst;
        ObservableList<reparation> reparation= FXCollections.observableArrayList();
            

        try {
            pst = connexion.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                
            int id=rs.getInt(1);
               String date_debr =rs.getString("date_debr");
               String date_finr =rs.getString("date_finr");
              Integer prixr =rs.getInt("prixr");
              
               reparation c =new reparation (nom ,date_debr,date_finr,prixr);
           reparation.add(c);
            }
            
        
        } catch (SQLException ex) {
            Logger.getLogger(reparationService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return reparation;
          
    }
    
        }
        
     

       
  

