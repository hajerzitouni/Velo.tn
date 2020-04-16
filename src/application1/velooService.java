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
public class velooService {
    private Connection con;
  

    
    public velooService()
    {
        con = MyBdd.getInstance().getConnexion();//pour s'assurer que la connexion a la base se fait une seule fois

    }
    
  
    public void  ajouter(velo v) throws SQLException {   
        //boolean b = false;
        //try {
        //String req = "INSERT INTO velo (`user_id` ,`prix`,`marque`,`type`,`nomimage`, `age`, `etat`, `nomvelo`) VALUES ('"+v.getUser_id()+"','"+v.getPrix()+"','"+v.getMarque()+"','"+v.getType()
              //  +"','"+v.getNomimage()+"','"+v.getAge()+"','"+v.getEtat()+"','"+v.getNomvelo()+"')";
        String sql = "insert into veloo (nomvelo) values ('" + v.getNomvelo()+"')";
        System.out.println(sql);
        Statement stm = con.createStatement();
        stm.executeUpdate(sql);
        System.out.println("user added ...");
         //b = true;
    }
    //catch (SQLException ex) {
      //      Logger.getLogger(veloService.class.getName()).log(Level.SEVERE, null, ex);
        //}//
        //return b;
         

  public void ajouter2(velo v) throws SQLException {
        String req = "INSERT INTO `velo` (nomvelo,prix,age,marque,image,etat1) VALUES (?,?,?,?,?,?) ";
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setString(1, v.getNomvelo());
        pstm.setInt(2, v.getPrix());
        pstm.setInt(3, v.getAge());
         pstm.setString(4, v.getMarque());
         pstm.setString(5,v.getImage());
         pstm.setString(6,v.getEtat1());
        
        pstm.executeUpdate();
    }

public List<velo> getAllveloos()throws SQLException  {

        List<velo> veloos = new ArrayList<>();

            String req = "SELECT * FROM velo";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(req);
            while (rs.next()) {
         
            velo v = new velo (rs.getInt(1),
                     
                        
           
                                 rs.getString("nomvelo"),
                                 rs.getInt("prix"),
                                 rs.getInt("age"),
                                 rs.getString("marque"),
                                 
                                  rs.getString("Image"),
                                 rs.getInt("etat"),
                                rs.getString("etat1"));
             
             
                      veloos.add(v);
        }

        return veloos;
    }
 public void delete(int id) throws SQLException {
        PreparedStatement pre= con.prepareStatement("delete from velo where Id=?"); //executer une requette parametrer.
		pre.setInt(1, id);
		pre.executeUpdate();
                   System.out.println("User deleted ...");     
        
    }
       public void modifier(int Id,String Nom) {
     String sql = "UPDATE   velo SET `Nom`='"+Nom+ "'  WHERE Id='"+Id+"' ";

        PreparedStatement st;
        try {
           
            st = con.prepareStatement(sql);
       
        st.executeUpdate();
        System.out.println("update done!");
        
        } catch (SQLException ex) {
            Logger.getLogger(velooService.class.getName()).log(Level.SEVERE, null, ex);
        }
       }

       public ObservableList<velo> recherche(String nomvelo , Integer prix , Integer age , String marque) {
        
        String requete = "SELECT * FROM  velo where nomvelo = '"+nomvelo+"' " ;
        PreparedStatement pst;
        ObservableList<velo> list= FXCollections.observableArrayList();
            

        try {
            pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                
            int id=rs.getInt(1);
               String nom=rs.getString("nomvelo");
               
              
               velo  v =new velo (id, nomvelo,prix,age,marque);
            list.add(v);
            }
            
        
        } catch (SQLException ex) {
            Logger.getLogger(velooService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list;
          
    }
       public void accepter(){
       
           String sql = "select * from velo join location on velo.id = location.velo_id";
           
       
       
       }
       public ArrayList<String> getVeloloués(){
        String query = "select * from velo v join locationn l on v.id = l.velo_id";
             PreparedStatement ps;
        ArrayList<String> loués = new ArrayList<>();
        
        try {
           // com.mysql.jdbc.PreparedStatement ps = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
       ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                
                loués.add(rs.getString("nomvelo"));
                loués.add(rs.getString("prix"));
                loués.add(rs.getString("age"));
                loués.add(rs.getString("marque"));
                loués.add(rs.getString("image"));
                 loués.add(rs.getString("etat"));
                
                
            }
            System.out.println("liste des vélos loués ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return loués;
    }
        public ArrayList<String> etatvelo(int id )throws SQLException 
          {
               
              
              ArrayList<String> etat= new ArrayList<>();
             
                String req ="UPDATE velo v INNER JOIN locationn l ON v.id = l.velo_id SET etat1= 'non' WHERE l.id='"+id+"'";
              PreparedStatement ste = con.prepareStatement(req);
                    System.out.println(req);
               //ste.setInt(1, id);
             //  ResultSet rs = ste.executeQuery(); 
             // while(rs.next()){
              //a=rs.getInt("nb");
               // System.out.println(a);}
                            ste.executeUpdate();
               return etat ;}
          

    
}
