/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import java.sql.SQLException;
import pidev.entities.cours;
import pidev.entities.paiement;
import pidev.services.coursService;
import pidev.services.paiementService;

/**
 *
 * @author gogo-
 */
public class test{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
       // paiement p = new paiement("selim","aouini",25,3,4);
        paiementService ps = new paiementService();
        coursService cs = new coursService();
        
    //   System.out.println(ps.FindAll());
      
     //  ps.Addpaiement(p);
       cs.increment_nb(4);
//s.DeleteCours(7);
       
        
    }

}