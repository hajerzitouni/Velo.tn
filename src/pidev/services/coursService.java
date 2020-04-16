package pidev.services;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static javax.swing.UIManager.getString;
import pidev.entities.cours;
import pidev.utils.Connexion;


/**
 *
 * @author ahmed
 */
public class coursService {
Connection connexion;
public coursService() {
       connexion=Connexion.getInstance().getConnexion();
    }

       

    
    public void AddCours(cours c) {

        String requete = "INSERT INTO cours(niveau,date_cours,lieu_cours,prixcours,Image,nb) VALUES (?,?,?,?,?,0)";

        try {

            PreparedStatement st = connexion.prepareStatement(requete);
            
            st.setInt(1, c.getNiveau());
            st.setString(2, c.getDate_cours());
            st.setString(3, c.getLieu_cours());
            st.setInt(4, c.getPrixcours());
            st.setString(5, c.getImage());
          

            st.executeUpdate();
            System.out.println("cours ajoutée");

        } catch (SQLException ex) {
            Logger.getLogger(coursService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    public void EditCours(cours c) {
   
     
        Statement st;
       
        try {
             st = connexion.createStatement();
             st.executeUpdate("UPDATE cours SET niveau='"+c.getNiveau()+"',date_cours='"+c.getDate_cours()+"',lieu_cours='"+c.getLieu_cours()+"',prixcours='"+c.getPrixcours()+"',nb='"+c.getNb()+
                        "' WHERE id= "+c.getId());
                    }   
        catch (SQLException ex) {
            Logger.getLogger(coursService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
  public void increment_nb(int id) throws SQLException{
        PreparedStatement preparedStatement = null ;
        String query="update cours c   set c.nb =c.nb+1 WHERE id="+id;
        Statement pstm = connexion.createStatement();
       pstm.executeUpdate(query);
    }

    public void DeleteCours(int id) throws SQLException {
        String req = "DELETE FROM cours WHERE id=?";
        PreparedStatement pre = connexion.prepareStatement(req);
        pre.setInt(1, id);
        pre.executeUpdate();

    }
   
  

    public cours FindById(int id) throws SQLException {
        cours c = new cours();

        String req = "SELECT * FROM `cours` WHERE id = ?";
        PreparedStatement pre = connexion.prepareStatement(req);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
             c.setNiveau(rs.getInt("id"));
            
            c.setNiveau(rs.getInt("niveau"));
            c.setDate_cours(rs.getString("date_cours"));
            c.setLieu_cours(rs.getString("lieu_cours"));
            c.setPrixcours(rs.getInt("prixcours"));
            c.setImage(rs.getString("Image"));
            c.setNb(rs.getInt("nb"));
           
          
      

        }

        return c;
    }
    public ObservableList<cours>recherche(String lieu_cours) {
        
        String requete = "SELECT * FROM  cours where lieu_cours = '"+lieu_cours+"' " ;
        PreparedStatement pst;
        ObservableList<cours> cours= FXCollections.observableArrayList();
            

        try {
            pst = connexion.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                
            int id=rs.getInt(1);
               String date_cours =rs.getString("date_cours");
              Integer prixcours =rs.getInt("prixcours");
              Integer niveau =rs.getInt("niveau");
               cours c =new cours (niveau ,date_cours,lieu_cours,prixcours);
           cours.add(c);
            }
            
        
        } catch (SQLException ex) {
            Logger.getLogger(coursService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return cours;
          
    }

     public ObservableList<cours> FindAllcours() throws SQLException {
         

        ObservableList<cours> cours = FXCollections.observableArrayList();

       

            String req = "SELECT * FROM cours";
            Statement st = connexion.createStatement();
            ResultSet result = st.executeQuery(req);
            while (result.next()) {
                cours c =new cours(
                result.getInt("id"),
                result.getInt("niveau"),
                result.getString("date_cours"),
                result.getString("lieu_cours"),
                result.getInt("prixcours"),
                result.getString("Image")
               );
                
              
                cours.add(c);

            }


        return cours;
        }}

   
        
     

       
  



