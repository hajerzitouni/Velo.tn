/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;


import java.sql.Date;
import java.util.Objects;
/**
 *
 * @author asus
 */
public class reparation {
	
    private int    id;
    
    private String nom;
     
    private String date_debr;
	
    private String date_finr;
   
    private Integer prixr;
   
   
   
    
   
  

    public reparation() {
    }
      public reparation(String nom,String date_debr,String date_finr, Integer prixr) {
        
        
        this.nom = nom;
        this.date_debr = date_debr;
        this.date_finr = date_finr;
        this.prixr = prixr;

       
    }
    
    public reparation(Integer id,String nom,String date_debr,String date_finr, Integer prixr) {
        
        this.id = id;
        this.nom = nom;
        this.date_debr = date_debr;
        this.date_finr = date_finr;
        this.prixr = prixr;

       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDate_debr() {
        return date_debr;
    }

    public void setDate_debr(String date_debr) {
        this.date_debr = date_debr;
    }

    public String getDate_finr() {
        return date_finr;
    }

    public void setDate_finr(String date_finr) {
        this.date_finr = date_finr;
    }

   

    
    public Integer getPrixr() {
        return prixr;
    }

    public void setPrixr(Integer prixr) {
        this.prixr = prixr;
    }
    
     @Override
    public String toString() {
        return "reparation{" + "id=" + id +  "nom=" + nom + ", date_debr=" + date_debr + ", date_finr=" + date_finr + ", prixr=" + prixr + '}';
    }

    @Override
    public int hashCode() {
        int hash = 8;
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final reparation other = (reparation) obj;
         if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        
        if (!Objects.equals(this.date_debr, other.date_debr)) {
            return false;
        }
        if (!Objects.equals(this.date_finr, other.date_finr)) {
            return false;
        }
       
        if (this.prixr != other.prixr) {
            return false;
        }
         if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

   
    
}
