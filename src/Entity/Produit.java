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
public class Produit {
    
    private int id;
    private String nom;
    private int categorie_id;
    private String image;
    private int quantite;
    private double prix;

    public Produit(int id, String nom, int catégorie_id, int quantite, double prix, String image){
	
        this.id = id;
        this.nom = nom;
        this.categorie_id = catégorie_id;
        if(quantite<0) {
		 System.out.println("Quantité est negatif");	
	}else {
        this.quantite = quantite;
        }
        if(prix<0) {
		 System.out.println("prix est negatif");
		
	}else {
        this.prix = prix;
        }
         this.image = image;
    }

    public Produit(int id, String nom, int quantite, double prix) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
    }

   
    

    public Produit(String nom, int quantite, double prix , String image){
        this.nom = nom;
         if(quantite<0) {
	      System.out.println("Quantité est negatif");	
	}else {
        this.quantite = quantite;
        }
        if(prix<0) {
		 System.out.println("prix est negatif");
		
	}else {
        this.prix = prix;
        }
        this.image = image;
    }

    public Produit(String nom) {
        this.nom = nom;
    }

    public Produit(String nom, int categorie_id, int quantite, double prix, String image) {
        this.nom = nom;
        this.categorie_id = categorie_id;
        this.quantite = quantite;
        this.prix = prix;
        this.image = image;
    }
    
    

    public Produit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int catégorie_id) {
        this.categorie_id = catégorie_id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) throws Exception{
	if(prix<0) {
		throw new Exception("Qantoté est negatif");
		
	}else {
        this.quantite = quantite;
        }
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) throws Exception{
	if(prix<0) {
		throw new Exception("Prix est negatif");
		
	}else {
        this.prix = prix;
        }
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", Nom=" + nom + ", Catégorie=" + categorie_id+
          ", Quantite=" + quantite + ", Prix=" + prix +"}";
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
        final Produit other = (Produit) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

 
    

    
}
  