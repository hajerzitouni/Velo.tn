/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application1;

/**
 *
 * @author Hajer
 */
public class veloo {

   

    Integer id ;
    String nom ;
    Integer prix ;

    
    
    public veloo ()
    {}
    public veloo(Integer id, String nom, Integer prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }
    public veloo(String nom) {
       
        this.nom = nom;
    }
      public veloo(String nom, Integer prix) {
        this.nom = nom;
        this.prix = prix;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }
   

 
    @Override
    public String toString() {
        return "veloo{" + "id=" + id + ", nom=" + nom + ", prix=" + prix + '}';
    }


}