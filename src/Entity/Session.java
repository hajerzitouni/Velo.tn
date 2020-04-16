/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import pidev.pidev;

/**
 *
 * @author asus
 */
public class Session {
    
    private static int idUtilisateur;

    public Session() {
        
    }
   

    public static void start(int currentUserID) {
        idUtilisateur = currentUserID;
    }

    public static int getCurrentSession() throws Exception {
        
       // return idUtilisateur=2;

        if (idUtilisateur != -1) {
            return pidev.user_id;
        } else {
            throw new Exception("Session has not started yet!");
        }

    }

    public static void close() throws Exception {
        if (idUtilisateur != -1) {
            idUtilisateur = -1;
        } else {
            throw new Exception("Session has not started yet!");
        }
    }
    
}
