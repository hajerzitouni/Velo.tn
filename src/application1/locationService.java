/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application1;

import static application1.Application1.user_id;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import static java.time.Instant.now;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author Hajer
 */
public class locationService {
    
    private Connection con;
  

    
    public locationService()
    {
        con = MyBdd.getInstance().getConnexion();//pour s'assurer que la connexion a la base se fait une seule fois

    }
    
     public void ajouter(locationn l) throws SQLException {
        String req = "INSERT INTO `locationn` (`velo_id`,`date_debut`,`date_fin`,`nomlocation`) VALUES (?,?,?,?)";
         try {
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setInt(1,l.getVelo_id());
        pstm.setDate(2,l.getDate_debut());
       // pstm.setDate(3,new java.sql.Date(l.getDate_debut()));
        pstm.setDate(3,l.getDate_fin());
        pstm.setString(4,l.getNomlocation());
       
        
        pstm.executeUpdate();
        System.out.println("location added ...");
    } catch (SQLException ex) {
            Logger.getLogger(locationService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     public void  ajouter2(locationn l) throws SQLException {   
         String sql = "insert into locationn (user_id,velo_id,date_debut,date_fin,nomlocation,prixloc) values ('" + l.getUser_id()+"','"+l.getVelo_id()+"','"+l.getDate_debut()+"','"+l.getDate_fin()+"','"+l.getNomlocation()+"','"+l.getPrixloc()+"')";
        System.out.println(sql);
        Statement stm = con.createStatement();
        stm.executeUpdate(sql);
        System.out.println("loc added ...");
         //b = true;
    }
      public void modifier(int Id,int user_id , int velo_id ,Date date_debut , Date date_fin ,String nomlocation,int prixloc) {
     //String sql = "UPDATE   veloo SET `Nom`='"+Nom+ "'  WHERE Id='"+Id+"' ";
     String sql = "UPDATE   locationn SET `User_id`='"+user_id+ "',`velo_id`='"+velo_id+ "',`date_debut`='"+date_debut+ "',`date_fin`='"+date_fin+ "',`nomlocation`='"+nomlocation+ "' WHERE Id='"+Id+"' ";

        PreparedStatement st;
        try {
           
            st = con.prepareStatement(sql);
       
        st.executeUpdate();
        System.out.println("update done!");
        
        } catch (SQLException ex) {
            Logger.getLogger(locationService.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       public static String valueOf(Object obj) {  
       return (obj == null) ? "null" : obj.toString();  
   }
         public void Edit(locationn l, int id) throws SQLException {
           SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String req = "UPDATE locationn SET user_id=?,velo_id=?,date_debut=?,date_fin=?,nomlocation=?,prixloc=? WHERE id='"+id+"' ";
        PreparedStatement st = con.prepareStatement(req);

            st.setInt(2,l.getVelo_id());
           // Date Date1 = Date.valueOf("2020-03-30");
             //Date Date2 = Date.valueOf("2020-03-30");
            st.setDate(3,l.getDate_debut());
            st.setDate(4,l.getDate_fin());
            //st.setDate(4, new java.sql.Date(15/02/2018));
            st.setString(5, l.getNomlocation());
     
           
           
     
        //st.setInt(7, id);
        st.executeUpdate();

    }
          public void Edit2( int id) throws SQLException {
              try {
           SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String req = "UPDATE locationn SET velo_id=?,date_debut=?,date_fin=?,nomlocation=? WHERE id='"+id+"' ";
        PreparedStatement st = con.prepareStatement(req);
            st.setInt(1, 6);
       
            Date Date1 = Date.valueOf("2020-03-30");
             Date Date2 = Date.valueOf("2020-03-3");
            st.setDate(2, Date1);
            st.setDate(3, Date2);
            //st.setDate(4, new java.sql.Date(15/02/2018));
            st.setString(4, "haj");
         
           
     
        //st.setInt(7, id);
        st.executeUpdate();
         } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        public void  Editl(locationn l)
        {
            Statement st;
       
        try {
             st = con.createStatement();
             st.executeUpdate("UPDATE locationn SET velo_id='"+l.getVelo_id()+"',date_debut='"+l.getDate_debut()+"',date_fin='"+l.getDate_debut()+"',nomlocation='"+l.getNomlocation()+
                        "' WHERE id= "+l.getId());
                    }   
        catch (SQLException ex) {
            Logger.getLogger(locationService.class.getName()).log(Level.SEVERE, null, ex);
        }}
        
        
        
         public List<locationn> getAlllocations()throws SQLException  {

        List<locationn> locations = new ArrayList<>();

            String req = "SELECT * FROM locationn";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(req);
            while (rs.next()) {
             locationn l = new locationn(rs.getInt(1),
                                        rs.getInt("user_id"),
                                        rs.getInt("velo_id"),
                                        rs.getDate("date_debut"),
                                        rs.getDate("date_fin"),
                                        rs.getString("nomlocation"),
                                        rs.getInt("prixloc"));
                      locations.add(l);
        }
          return locations;
         
     }
          public void delete(int id) throws SQLException {
        PreparedStatement pre= con.prepareStatement("delete from locationn where Id=?"); //executer une requette parametrer.
		pre.setInt(1, id);
		pre.executeUpdate();
                   System.out.println("location deleted ...");     
        
    }
           public ArrayList<String> calculdiff(int id )throws SQLException 
          {
               int a=0;
               int b=0;
              ArrayList<String> calcul= new ArrayList<>();
              String req = "SELECT DATEDIFF(date_fin,date_debut) as nb from locationn  WHERE id='"+id+"' ";
              PreparedStatement ste = con.prepareStatement(req);
               //ste.setInt(1, id);
               ResultSet rs = ste.executeQuery(); 
              while(rs.next()){
              a=rs.getInt("nb");
                System.out.println(a);}
               return calcul ;
             
                      
          }
           
           
        public ArrayList<String> calculPrix(int id )throws SQLException 
          {
               int a=0;
              
              ArrayList<String> prixl= new ArrayList<>();
              String req = "update locationn l set prixloc =( select * from (SELECT v.prix *DATEDIFF(date_fin,date_debut) as nb  from locationn l join velo v on v.id= l.velo_id WHERE l.id='"+id+"') as x) WHERE l.id='"+id+"'"; 
              PreparedStatement ste = con.prepareStatement(req);
               //ste.setInt(1, id);
             //  ResultSet rs = ste.executeQuery(); 
             // while(rs.next()){
              //a=rs.getInt("nb");
               // System.out.println(a);}
                            ste.executeUpdate();
               return prixl ;}
          
        

 public int getIdbynom(String nomlocation) {
        try {
            PreparedStatement st = con.prepareStatement("select id from locationn where nomlocation=?");
            st.setString(1, nomlocation);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(locationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }
 
  public ObservableList<asurance> afficherassurance(int id )throws SQLException 
          {
               
              
             // ArrayList<String> assurancel= new ArrayList<>();
              ObservableList<asurance> assurancel= FXCollections.observableArrayList();
              String req = "SELECT montant as nb from asurance a join locationn l on l.velo_id=a.velo_id WHERE l.id='"+id+"' "; 
              PreparedStatement ste = con.prepareStatement(req);
               //ste.setInt(1, id);
               ResultSet rs = ste.executeQuery(); 
              while(rs.next()){
                  
            int montant =rs.getInt("nb");
              asurance a = new asurance (montant);
             // a=rs.getInt("nb");
                //System.out.println(a);}
              
                         assurancel.add(a);   }    
               return assurancel ;}
          
  
  


    
 
 public locationn FindById(int id) throws SQLException {
        locationn l = new locationn();

        String req = "SELECT * FROM locationn WHERE id = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {

            l.setId(rs.getInt("id"));
            l.setUser_id(rs.getInt("user_id"));
            l.setVelo_id(rs.getInt("velo_id"));
            l.setDate_debut (rs.getDate("date_debut"));
            l.setDate_fin (rs.getDate("date_fin"));
            l.setNomlocation(rs.getString("nomlocation"));
            l.setPrixloc(rs.getInt("prixloc"));
                        
        }

        return l;
    }
 
   public void ajouter4 (locationn l ,int velo_id) throws SQLException {
        String req = "INSERT INTO `locationn` ('velo_id','date_debut`,`date_fin`,`nomlocation`) VALUES (?,?,?,?)";
         
        try {
          
        PreparedStatement pstm = con.prepareStatement(req);
        //pstm.setInt(1,l.setVelo_id);
        pstm.setDate(2,l.getDate_debut());
       // pstm.setDate(3,new java.sql.Date(l.getDate_debut()));
        pstm.setDate(3,l.getDate_fin());
        pstm.setString(4,l.getNomlocation());
       
        
        pstm.executeUpdate();
        System.out.println("location added ...");
    } catch (SQLException ex) {
            Logger.getLogger(locationService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
       
 public void  ajouter3(locationn l, int velo_id) throws SQLException {   
         String sql = "insert into locationn (user_id,velo_id,date_debut,date_fin,nomlocation) values ('"+user_id+"','"+velo_id+"','"+l.getDate_debut()+"','"+l.getDate_fin()+"','"+l.getNomlocation()+"')";
        System.out.println(sql);
        Statement stm = con.createStatement();
        stm.executeUpdate(sql);
        System.out.println("loc added ...");
         //b = true;
    }
 
  public   Date getdate(int id) throws SQLException {
        //locationn l = new locationn();
         Date a = null ;
        String req = "SELECT date_fin FROM locationn l join  velo v  on  l.velo_id= v.id WHERE v.id = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        while (rs.next()){
            
            a=rs.getDate("date_fin");
           
                        
        }
   return a ;
        
  }
}