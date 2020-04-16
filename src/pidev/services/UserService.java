/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pidev.entities.User;
import pidev.util.connexionBD;

/**
 *
 * @author Admin
 */
public class UserService {
     Connection c = connexionBD.getInstance().getCnx();
        
    public static UserService su;

    public static UserService getInstance() {
        if(su == null )
            su = new UserService();
        return su;
            
    }
    

    
    public User afficheruserparid(int id) {
         User  usr= new User();
            try {
                
             PreparedStatement pt = c.prepareStatement("select * from fos_user WHERE id = ?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            
            while (rs.next()) {
                usr.setId(rs.getInt(1));
                usr.setUsername(rs.getString(2));
                usr.setEmail(rs.getString(4));
             
            }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        
        return usr;
    }
    
    
    
    
    
    
}
