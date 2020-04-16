/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Objects;

/**
 *
 * @author asus
 */
public class Addresse {
    private int id;
    private int numTel;
    private String mail;
    private String payer;
    private String ville;
    private int pincode;

    public Addresse(int id, int numTel, String mail, String payer, String ville, int pincode) {
        this.id = id;
        this.numTel = numTel;
        this.mail = mail;
        this.payer = payer;
        this.ville = ville;
        this.pincode = pincode;
    }

    public Addresse() {
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id;
        hash = 79 * hash + this.numTel;
        hash = 79 * hash + Objects.hashCode(this.mail);
        hash = 79 * hash + Objects.hashCode(this.payer);
        hash = 79 * hash + Objects.hashCode(this.ville);
        hash = 79 * hash + this.pincode;
        return hash;
    }
    
  


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }
    
     @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", numero de telephone=" + numTel + 
          ",Email =" + mail + ",Ville =" + ville + ",Code postal =" + pincode + "}";
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
        final Addresse other = (Addresse) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }  
    
}
