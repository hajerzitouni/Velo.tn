/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Hajer
 *
 */

public class MyBdd {
    private static MyBdd instance;
    private Connection connexion;
    private String url = "JDBC:mysql://localhost:3306/pijava";
    private String user = "root";
    private String password = "";

    private MyBdd () {
        try {
            connexion = DriverManager.getConnection(url, user, password);
              System.out.println("succés");
        } catch (SQLException ex) {
            System.out.println("Probleme de connexion");
        }
    }

    public static MyBdd  getInstance() {
        if (MyBdd .instance == null) {
            MyBdd .instance = new MyBdd ();
        }
        return MyBdd .instance;
    }

    public Connection getConnexion() {
        return this.connexion;
    }
  public static void main(String[] args) {
        // TODO code application logic here
 new  MyBdd ();
    }
}


