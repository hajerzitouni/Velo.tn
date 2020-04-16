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
import pidev.entities.velorepares;
import pidev.util.Connexion;


/**
 *
 * @author ahmed
 */


       
        public class veloreparesService {
Connection connexion;
public veloreparesService() {
       connexion=Connexion.getInstance().getConnexion();
        
}
    
    public void AddVelorepares(velorepares c) {

        String requete = "INSERT INTO velorepares(descriptionprob,type,marque,ville) VALUES (?,?,?,?)";

        try {

            PreparedStatement st = connexion.prepareStatement(requete);
            st.setString(1, c.getDescriptionprob());
            st.setString(2, c.getType());
            st.setString(3, c.getMarque());
            st.setString(4, c.getVille());
            st.executeUpdate();
            System.out.println("demande de reparation envoyé");

        } catch (SQLException ex) {
            Logger.getLogger(veloreparesService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void EditVelorepares(velorepares c ) throws SQLException {
        Statement st;
       
        try {
             st = connexion.createStatement();
             st.executeUpdate("UPDATE velorepares SET descriptionprob='"+c.getDescriptionprob()+"',type='"+c.getType()+"',marque='"+c.getMarque()+"',ville='"+c.getVille()+
                        "' WHERE id= "+c.getId());
                    }   
        catch (SQLException ex) {
            Logger.getLogger(veloreparesService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void DeleteVelorepares(int id) throws SQLException {
        String req = "DELETE FROM velorepares WHERE id=?";
        PreparedStatement pre = connexion.prepareStatement(req);
        pre.setInt(1, id);
        pre.executeUpdate();

    }

    public velorepares FindById(int id) throws SQLException {
        velorepares c = new velorepares();

        String req = "SELECT * FROM `velorepares` WHERE id = ?";
        PreparedStatement pre = connexion.prepareStatement(req);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {

            
            c.setDescriptionprob(rs.getString("Description de probléme"));
            c.setType(rs.getString("Type de probléme"));
            c.setMarque(rs.getString("Marque de velo"));
            c.setVille(rs.getString("Ville Proche"));
            
      

        }

        return c;
    }

    public ObservableList<velorepares> FindAllvelo() throws SQLException {

       ObservableList<velorepares> velorepares = FXCollections.observableArrayList();

        

            String req = "SELECT * FROM velorepares";
            Statement st = connexion.createStatement();
            ResultSet result = st.executeQuery(req);
            while (result.next()) {
                velorepares c =new velorepares(
               result.getInt("id"),
                result.getString("descriptionprob"),
                result.getString("type"),
                result.getString("marque"),
                result.getString("ville"));
                velorepares.add(c);
              
            }

         

        
        

        return velorepares;
    }
        }
        
     

       
  

