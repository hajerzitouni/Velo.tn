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
public class station {
	
  
    
    private String ville;
     
  
   
   
   
    
   
  

    public station() {
    }
      public station(String ville) {
        
        
        this.ville = ville;
        

       
    }

    public station(String string, boolean add) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
    
    

    
    
     @Override
    public String toString() {
        return "station{" + "ville=" + ville +'}';
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
        final station other = (station) obj;
         if (!Objects.equals(this.ville, other.ville)) {
            return false;
        }
        
       
        return true;
    }

   
    
}
