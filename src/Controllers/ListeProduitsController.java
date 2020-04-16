/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Produit;
import Entity.Session;
import Services.PanierService;
import Services.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ListeProduitsController implements Initializable {

    @FXML
    private Label nomUtilisateur;
    @FXML
    private TableView<Produit> listeProduits;
    @FXML
   private TableColumn<Produit, String> nomProduit;
    @FXML
    private TableColumn<Produit, Integer> prixProduit;
    @FXML
    private TableColumn ajouterPanier;
    @FXML
    private ImageView imageview;
    private  Produit selectedid;
    
   
    private ObservableList<Produit> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("afficher list des produits");
        try {
            nomUtilisateur.setText("liste des produit");
        } catch (Exception ex) {
            Logger.getLogger(ListeProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        populateTableView();
    }    
    
    private void populateTableView() {
        
        ProduitService produitService = new ProduitService();
        list = produitService.getproduit();
        
        nomProduit.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prixProduit.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
        Callback<TableColumn<Produit, String>, TableCell<Produit, String>> cellFactory= (param) -> {

            final TableCell<Produit, String> cell = new TableCell<Produit, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button test = new Button("Ajouter le produit");
                        test.setOnAction(event -> {
                            Produit p = getTableView().getItems().get(getIndex());
                            PanierService panierService = new PanierService();
                                
                            try {
                                panierService.ajouterproductaupanier(Session.getCurrentSession(), p.getId());
                            } catch (Exception ex) {
                                Logger.getLogger(ListeProduitsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                
                            System.out.println(p);
                        });

                        setGraphic(test);
                        setText(null);

                    }
                }

            };
            return cell; //To change body of generated lambdas, choose Tools | Templates.
        };
        ajouterPanier.setCellFactory(cellFactory);
        listeProduits.setItems(list);
        
    }
    
    
@FXML
    private void afficherImage(MouseEvent event) {
        
      selectedid = (Produit) listeProduits.getSelectionModel().getSelectedItem();
       Image i = new Image( "http://localhost//pidev.java/image/"+selectedid.getImage());
       imageview.setImage(i);
       System.out.println(selectedid.getImage());
       
    }


    
}
