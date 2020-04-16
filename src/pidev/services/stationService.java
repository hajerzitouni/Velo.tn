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
import pidev.entities.station;
import pidev.util.Connexion;


/**
 *
 * @author Selim
 */


       
        public class stationService {
Connection connexion;
public stationService() {
       connexion=Connexion.getInstance().getConnexion();
        
}
    
    public void AddStation(station s) {

        String requete = "INSERT INTO station(ville) VALUES (?)";

        try {

            PreparedStatement st = connexion.prepareStatement(requete);
            st.setString(1, s.getVille());
           
            st.executeUpdate();
            System.out.println("la station la plus proche est");

        } catch (SQLException ex) {
            Logger.getLogger(stationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void EditStation(station s) throws SQLException {

        String req = "UPDATE station SET ville=?";
        PreparedStatement st = connexion.prepareStatement(req);
            st.setString(1, s.getVille());
            
        
        st.executeUpdate();

    }

    public void DeleteStation(int id) throws SQLException {
        String req = "DELETE FROM station WHERE id=?";
        PreparedStatement pre = connexion.prepareStatement(req);
        pre.setInt(1, id);
        pre.executeUpdate();

    }

    public station FindById(int id) throws SQLException {
        station s = new station();

        String req = "SELECT * FROM `station` WHERE id = ?";
        PreparedStatement pre = connexion.prepareStatement(req);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {

            
            s.setVille(rs.getString("ville"));
            
            
      

        }

        return s;
    }

    public List<station> FindAllstation() throws SQLException {

        List<station> station = new ArrayList<>();

        

            String req = "SELECT * FROM station";
            Statement st = connexion.createStatement();
            ResultSet result = st.executeQuery(req);
            while (result.next()) {
                station s =new station(
               
                result.getString("ville"));
                
              station.add(s);
              
                        }

         

        
        

        return station;
    }
        }
        
     

       
  

