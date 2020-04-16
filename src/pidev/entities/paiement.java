/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author DELL
 */
public class paiement{
       private String nom;
     private String prenom;
     private Integer age;
    
    private Integer id;
    
  
  
   
    private Integer note;
    private Integer user_id;
    
    private Integer cour_id;

   
      public paiement() {
    }

    public paiement(String nom, String prenom, Integer age) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    public paiement(String nom, String prenom, Integer age, Integer cour_id) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.cour_id = cour_id;
    }

   

    public paiement(String nom, String prenom, Integer age, Integer cour_id,Integer user_id) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.user_id = user_id;
        this.cour_id = cour_id;
    }
      
    
     
  

   
    
 
    public paiement(Integer id, Integer num_carte, Integer cvc, String type_car, String nom, String prenom, Integer age, Integer note, Integer user_id, Integer cour_id) {
        this.id = id;
     
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.note = note;
        this.user_id = user_id;
        this.cour_id = cour_id;
    }

    public paiement(Integer id, String nom, String prenom, Integer age, Integer user_id, Integer cour_id) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.user_id = user_id;
        this.cour_id = cour_id;
    }
      
         
    
  
    
 public paiement( Integer note) {
        
        this.note = note;}
    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getCour_id() {
        return cour_id;
    }

    public void setCour_id(Integer cour_id) {
        this.cour_id = cour_id;
    }

 
  
 
  


    


   

   

 

    @Override
    public String toString() {
        return "paiement{" + " nom=" + nom +",prenom=" + prenom + ",age=" + age +  ", note=" + note +  '}';
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
        final paiement other = (paiement) obj;
       
        if (this.age != other.age) {
            return false;
        }
        if (this.note != other.note) {
            return false;
        }
           if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
              if (!Objects.equals(this.prenom, other.nom)) {
            return false;
        }
              
        
       
       if (!Objects.equals(this.cour_id, other.cour_id)) {
            return false;
        }
       
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
      
        return true;
    }
    
    
    
    
    
}
