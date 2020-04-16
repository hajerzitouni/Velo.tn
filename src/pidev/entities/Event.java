/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Manel
 */
public class Event {
    private int id;
    private String nomevet;
    private Date date_debe ; 
    private Date date_fine ; 
    private String lieuevt;
    private String description;
    private int nbparticipent;
    private int prixe ;
    private String nom_image;
    private int creator_id;
    private int nbsignal;
    private boolean  isActive;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomevet() {
        return nomevet;
    }

    public void setNomevet(String nomevet) {
        this.nomevet = nomevet;
    }

    public Date getDate_debe() {
        return date_debe;
    }

    public void setDate_debe(Date date_debe) {
        this.date_debe = date_debe;
    }

    public Date getDate_fine() {
        return date_fine;
    }

    public void setDate_fine(Date date_fine) {
        this.date_fine = date_fine;
    }

    public String getLieuevt() {
        return lieuevt;
    }

    public void setLieuevt(String lieuevet) {
        this.lieuevt = lieuevet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbparticipent() {
        return nbparticipent;
    }

    public void setNbparticipent(int nbparticipent) {
        this.nbparticipent = nbparticipent;
    }

    public int getPrixe() {
        return prixe;
    }

    public void setPrixe(int prixe) {
        this.prixe = prixe;
    }

    public String getNom_image() {
        return nom_image;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }

    public int getNbsignal() {
        return nbsignal;
    }

    public void setNbsignal(int nbsignal) {
        this.nbsignal = nbsignal;
    }

    /**
     *
     * @return
     */
    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Event() {
    }

    public Event(int id, String nomevet, Date date_debe, Date date_fine, String lieuevet, String description, int nbparticipent, int prixe, String nom_image, int creator_id, int nbsignal, boolean isActive) {
        this.id = id;
        this.nomevet = nomevet;
        this.date_debe = date_debe;
        this.date_fine = date_fine;
        this.lieuevt = lieuevet;
        this.description = description;
        this.nbparticipent = nbparticipent;
        this.prixe = prixe;
        this.nom_image = nom_image;
        this.creator_id = creator_id;
        this.nbsignal = nbsignal;
        this.isActive = isActive;
    }

    public Event(String nomevet, Date date_debe, Date date_fine, String lieuevet, String description, int nbparticipent, int prixe, String nom_image, int creator_id, int nbsignal, boolean isActive) {
        this.nomevet = nomevet;
        this.date_debe = date_debe;
        this.date_fine = date_fine;
        this.lieuevt = lieuevet;
        this.description = description;
        this.nbparticipent = nbparticipent;
        this.prixe = prixe;
        this.nom_image = nom_image;
        this.creator_id = creator_id;
        this.nbsignal = nbsignal;
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.id;
        hash = 11 * hash + Objects.hashCode(this.nomevet);
        hash = 11 * hash + Objects.hashCode(this.date_debe);
        hash = 11 * hash + Objects.hashCode(this.date_fine);
        hash = 11 * hash + Objects.hashCode(this.lieuevt);
        hash = 11 * hash + Objects.hashCode(this.description);
        hash = 11 * hash + this.nbparticipent;
        hash = 11 * hash + Objects.hashCode(this.prixe);
        hash = 11 * hash + Objects.hashCode(this.nom_image);
        hash = 11 * hash + this.creator_id;
        hash = 11 * hash + this.nbsignal;
        hash = 11 * hash + (this.isActive ? 1 : 0);
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
        final Event other = (Event) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.nbparticipent != other.nbparticipent) {
            return false;
        }
        if (this.creator_id != other.creator_id) {
            return false;
        }
        if (this.nbsignal != other.nbsignal) {
            return false;
        }
        if (this.isActive != other.isActive) {
            return false;
        }
        if (!Objects.equals(this.nomevet, other.nomevet)) {
            return false;
        }
        if (!Objects.equals(this.lieuevt, other.lieuevt)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.prixe, other.prixe)) {
            return false;
        }
        if (!Objects.equals(this.nom_image, other.nom_image)) {
            return false;
        }
        if (!Objects.equals(this.date_debe, other.date_debe)) {
            return false;
        }
        if (!Objects.equals(this.date_fine, other.date_fine)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nomevet + "  "+ this.lieuevt + "  "+  this.date_debe +" "+  this.date_fine + " "+ this.prixe; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}







