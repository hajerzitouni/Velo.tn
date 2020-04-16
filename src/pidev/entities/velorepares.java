/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;


import java.util.Objects;
/**
 *
 * @author asus
 */
public class velorepares {
	
    private int    id;
    
    private String descriptionprob;
	
    private String type;
   
    private String marque;
   
    private String ville;
   
    
   
  

    public velorepares() {
    }
      public velorepares(String descriptionprob,String type,String marque, String ville) {
        
        
        this.descriptionprob = descriptionprob;
        this.type = type;
        this.marque = marque;
        this.ville = ville;

       
    }
    
    public velorepares(Integer id,String descriptionprob,String type,String marque, String ville) {
        
        this.id = id;
        this.descriptionprob = descriptionprob;
        this.type = type;
        this.marque = marque;
        this.ville = ville;

       
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescriptionprob() {
        return descriptionprob;
    }

    public void setDescriptionprob(String descriptionprob) {
        this.descriptionprob = descriptionprob;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
     @Override
    public String toString() {
        return "velorepares{" + "id=" + id +  "descriptionprob=" + descriptionprob + ", type=" + type + ", marque=" + marque + ", ville=" + ville + '}';
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
        final velorepares other = (velorepares) obj;
         if (!Objects.equals(this.descriptionprob, other.descriptionprob)) {
            return false;
        }
        
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.marque, other.marque)) {
            return false;
        }
       
        if (!Objects.equals(this.ville, other.ville)) {
            return false;
        }
         if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

   
    
}
