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
public class asurance {

   
    private Integer id ;
    private Integer location_id;
    private Integer user_id;

    public asurance(Integer montant) {
        this.montant = montant;
    }
    private Integer montant ;
    private Integer velo_id ;
    
    public asurance() {
    }

    public asurance(Integer id, Integer location_id, Integer user_id, Integer montant, Integer velo_id) {
        this.id = id;
        this.location_id = location_id;
        this.user_id = user_id;
        this.montant = montant;
        this.velo_id = velo_id;
    }

    public asurance(Integer id, Integer montant, Integer velo_id) {
        this.id = id;
        this.montant = montant;
        this.velo_id = velo_id;
    }

    public asurance(Integer montant, Integer velo_id) {
        this.velo_id = velo_id;
        this.montant = montant;
      
    }

    
    
    
     public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getMontant() {
        return montant;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }

    public Integer getVelo_id() {
        return velo_id;
    }

    public void setVelo_id(Integer velo_id) {
        this.velo_id = velo_id;
    }
    
    @Override
    public String toString() {
        return "assurance{" + "id=" + id + ", montant=" + montant + ", velo_id=" + velo_id + '}';
    }
    
}
