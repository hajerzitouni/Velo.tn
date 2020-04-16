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
public class reclamation {

    public reclamation(Integer id, String titrereclam) {
        this.id = id;
        this.titrereclam = titrereclam;
    }
    private Integer id ;
    private Integer user_id;
    private String probleme ;

    public reclamation(String probleme, String titrereclam) {
        this.probleme = probleme;
        this.titrereclam = titrereclam;
    }
    private String titrereclam;
      
    
    public reclamation(Integer user_id, String probleme, String titrereclam) {
        this.user_id = user_id;
        this.probleme = probleme;
        this.titrereclam = titrereclam;
    }

    public reclamation(Integer id, Integer user_id, String probleme, String titrereclam) {
        this.id = id;
        this.user_id = user_id;
        this.probleme = probleme;
        this.titrereclam = titrereclam;
    }

    public reclamation() {
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

    public String getProbleme() {
        return probleme;
    }

    public void setProbleme(String probleme) {
        this.probleme = probleme;
    }

    public String getTitrereclam() {
        return titrereclam;
    }

    public void setTitrereclam(String titrereclam) {
        this.titrereclam = titrereclam;
    }
    
    
    
    @Override
    public String toString() {
        return "reclamation{" + "id=" + id + ", user_id=" + user_id + ", probleme=" + probleme + ", titrereclam=" + titrereclam + '}';
    }
    
}
