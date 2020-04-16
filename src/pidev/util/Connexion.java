/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ouertani
 */
public class Connexion {
    
    private static Connexion instance;
    private Connection connexion;
    private String url = "JDBC:mysql://localhost:3306/pivelo";
    private String user = "root";
    private String password = "";

    private Connexion() {
        try {
            connexion = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println("Probleme de connexion");
        }
    }

    public static Connexion getInstance() {
        if (Connexion.instance == null) {
            Connexion.instance = new Connexion();
        }
        return Connexion.instance;
    }

     public Connection getConnexion() {
        return this.connexion;
    }

    }
  