/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

import java.util.Objects;

/**
 *
 * @author Manel
 */
public class Comment {
    private int id ;


    private String commentaire ; 
    private int user_id ;
    private String user_name ;
    private int event_id ; 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getEvent_id() {
        return this.event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }
    
    
    
    
        public Comment() {
    }

    public Comment(String commentaire, int user_id, int event_id) {
        this.commentaire = commentaire;
        this.user_id = user_id;
        this.event_id = event_id;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", commentaire=" + commentaire + ", user_id=" + user_id + ", event_id=" + event_id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.commentaire);
        hash = 53 * hash + this.user_id;
        hash = 53 * hash + this.event_id;
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
        final Comment other = (Comment) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        if (this.event_id != other.event_id) {
            return false;
        }
        if (!Objects.equals(this.commentaire, other.commentaire)) {
            return false;
        }
        return true;
    }
        
    
    
    
}
