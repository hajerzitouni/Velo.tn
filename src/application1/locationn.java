/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application1;
import java.sql.Date;

/**
 *
 * @author Hajer
 */
public class locationn {

    

    
    
    private Integer id ;
    private Integer user_id ;
    private Integer velo_id ;
    private Date date_debut ;
    private Date date_fin ;

    public locationn(Date date_debut, Date date_fin, String nomlocation) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nomlocation = nomlocation;
    }

    
    private String nomlocation ;

    
    private Integer prixloc ;
    
     public locationn() {
    }
    
     public locationn(Integer id, Integer user_id, Integer velo_id, Date date_debut, Date date_fin, String nomlocation, Integer prixloc) {
        this.id = id;
        this.user_id = user_id;
        this.velo_id = velo_id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nomlocation = nomlocation;
        this.prixloc = prixloc;
    }
     public locationn(Integer user_id, Integer velo_id, Date date_debut, Date date_fin, String nomlocation, Integer prixloc) {
        this.user_id = user_id;
        this.velo_id = velo_id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nomlocation = nomlocation;
        this.prixloc = prixloc;
    }
     public locationn(Integer velo_id, Date date_debut, Date date_fin, String nomlocation) {
        this.velo_id = velo_id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nomlocation = nomlocation;
    }
     
    public locationn(Integer id, Integer velo_id, Date date_debut, Date date_fin, String nomlocation) {
        this.id = id;
        this.velo_id = velo_id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nomlocation = nomlocation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getVelo_id() {
        return velo_id;
    }

    public void setVelo_id(Integer velo_id) {
        this.velo_id = velo_id;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getNomlocation() {
        return nomlocation;
    }

    public void setNomlocation(String nomlocation) {
        this.nomlocation = nomlocation;
    }

    public Integer getPrixloc() {
        return prixloc;
    }

    public void setPrixloc(Integer prixloc) {
        this.prixloc = prixloc;
    }
    
    
    
    @Override
    public String toString() {
        return "location{" + "id=" + id + ", user_id=" + user_id + ", velo_id=" + velo_id + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", nomlocation=" + nomlocation + ", prixloc=" + prixloc + '}';
    }
}
