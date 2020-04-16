/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author asus
 */
public class Paniervendu {
    
    private int id;
    private int produit_id;
    private int client_id;
    private int commande_id;
    private int quantite;

    public Paniervendu(int id, int produit_id, int client_id, int commande_id, int quantite) {
        this.id = id;
        this.produit_id = produit_id;
        this.client_id = client_id;
        this.commande_id = commande_id;
        this.quantite = quantite;
    }

    public Paniervendu() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getCommande_id() {
        return commande_id;
    }

    public void setCommande_id(int commande_id) {
        this.commande_id = commande_id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
      @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", Panier de client =" + client_id + 
          ", id de produit  =" + produit_id + ", Commande numero  =" + commande_id +
                ", Quantite de produit=" + quantite +"}";
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
        final Paniervendu other = (Paniervendu) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    } 
    
}
