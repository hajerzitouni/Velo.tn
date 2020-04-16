/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

/**
 *
 * @author Manel
 */
public class Recu {
    private int id ;
    private int prixrecu ; 
    private int event_id ; 
    private int user_id; 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrixrecu() {
        return prixrecu;
    }

    public void setPrixrecu(int prixrecu) {
        this.prixrecu = prixrecu;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Recu{" + "id=" + id + ", prixrecu=" + prixrecu + ", event_id=" + event_id + ", user_id=" + user_id + '}';
    }

    public Recu() {
    }

    public Recu(int prixrecu, int event_id, int user_id) {
        this.prixrecu = prixrecu;
        this.event_id = event_id;
        this.user_id = user_id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.id;
        hash = 71 * hash + this.prixrecu;
        hash = 71 * hash + this.event_id;
        hash = 71 * hash + this.user_id;
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
        final Recu other = (Recu) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.prixrecu != other.prixrecu) {
            return false;
        }
        if (this.event_id != other.event_id) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        return true;
    }
 
    
    
    
    
}
