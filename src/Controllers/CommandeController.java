/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.CommandeCltController;
import Entity.Addresse;
import Entity.Commande;
import Entity.Panier;
import Entity.Paniervendu;
import Entity.Session;
import Services.AddresseService;
import Services.CommandeService;
import Services.PanierService;
import Services.PaniervenduService;
import Services.ProduitService;
import com.teknikindustries.bulksms.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import SMS.SMS;


/**
 * FXML Controller class
 *
 * @author asus
 */
public class CommandeController implements Initializable {
    
     @FXML
    private AnchorPane btnAjouter;
 
    @FXML
    private TextField numTel;
    @FXML
    private TextField email;
    @FXML
    private TextField pays;
    @FXML
    private TextField ville;
    @FXML
    private TextField codePostale;
    
     @FXML
    private Button btnAjout;
    
    
    public static int idAdresse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void passerCommande(ActionEvent event) throws IOException, SQLException {
        System.out.println("procedure de creation de commande");
        System.out.println("@ ok");
        idAdresse = 0;
        
        Addresse addresse = new Addresse();
        
        addresse.setNumTel(Integer.valueOf(numTel.getText()));
        addresse.setMail(email.getText());
        addresse.setPayer(pays.getText());
        addresse.setVille(ville.getText());
        addresse.setPincode(Integer.parseInt(codePostale.getText()));
        addresse.setId(addresse.hashCode());
        idAdresse =addresse.getId();
        System.out.println(addresse);
        
        AddresseService addresseService = new AddresseService();
        addresseService.Ajouteraddresse(addresse);
        System.out.println("###creation commande###");
        System.out.println("###calculer prix totale###");
        ProduitService produitService = new ProduitService();
        PanierService panierService = new PanierService();
        List<Panier> list = new ArrayList<>();
         try {
             list = panierService.getpanier(Session.getCurrentSession());
         } catch (Exception ex) {
             Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
         }
        int prixTotale = 0;
        for (Panier panier : list) 
            prixTotale += produitService.getprixproduit(panier.getProduit_id());
        
        Commande commande = new Commande();
        
         try {
             commande.setClient_id(Session.getCurrentSession());
         } catch (Exception ex) {
             Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
         }
        commande.setPrixtotal(prixTotale);
        commande.setEtat("en cours");
        commande.setAddresse_id(idAdresse);
        commande.setId(commande.hashCode());
        System.out.println(commande);
        
        CommandeService commandeService = new CommandeService();
        commandeService.Ajoutercommande(commande);
        
        System.out.println("###creation panier vendu###");
        PaniervenduService panierVenduService = new PaniervenduService();
        
        for (Panier panier : list) {
            Paniervendu panierVendu = new Paniervendu();
            panierVendu.setProduit_id(panier.getProduit_id());
            panierVendu.setClient_id(panier.getClient_id());
            panierVendu.setQuantite(panier.getQuantite());
            panierVendu.setCommande_id(commande.getId());
            panierVenduService.ajouterpaniervendu(panierVendu);
            panierService.supprimerprodfrompanier(panier.getId());
        }
        
        
     String nt= "+216"+numTel.getText();
       SMS sms=new SMS();
       sms.SMS(nt ,"Votre commande est validée");
        
     
        precedent();
    }
    
    private void precedent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/homeClt.fxml"));
            Parent root = loader.load();

            homeCltController panelClientController = loader.getController();
            email.getScene().setRoot(root);
        } catch (IOException ex) {

        }
    }
 
    
}
