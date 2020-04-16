/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Panier;
import Entity.Session;
import Services.PanierService;
import java.awt.List;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class PanierController implements Initializable {

    @FXML
    private AnchorPane containerPanier;
    @FXML
    private AnchorPane btnAjouter;
    @FXML
    private Label nomUtilisateur;
    @FXML
    private TableView<Panier> listePanier;
   
    @FXML
    private TableColumn<Panier, Integer> quantiteProduit;
    @FXML
    private TableColumn incQt;
    @FXML
    private TableColumn decQt;
    @FXML
    private TableColumn  supprimerProduits;
    @FXML
    private Button validerCommande;
    
    private ObservableList<Panier> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("afficher panier");
        populateTableView();
    }    
    private void populateTableView() {
        list = FXCollections.observableArrayList();
        PanierService panierService = new PanierService();
        try {
            list = panierService.getpanier(Session.getCurrentSession());
        } catch (Exception ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       // numproduit.setCellValueFactory(new PropertyValueFactory<>("id_Produit"));
        quantiteProduit.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        
        Callback<TableColumn<Panier, String>, TableCell<Panier, String>> cellFactoryRemove = (param) -> {

            final TableCell<Panier, String> cell = new TableCell<Panier, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button test = new Button("Supprimer");
                        test.setOnAction(event -> {
                            Panier p = getTableView().getItems().get(getIndex());
                            PanierService panierService = new PanierService();

                            panierService.supprimerprodfrompanier(p.getId());
                            
                            try {
                                refresh();
                            } catch (IOException ex) {
                                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            //panierService.addProduct(SingninController.userIden, p.getId());
                            System.out.println(p);
                        });

                        setGraphic(test);
                        setText(null);

                    }
                }

            };
            return cell;
        };
        
        Callback<TableColumn<Panier, String>, TableCell<Panier, String>> cellFactoryInc = (param) -> {

            final TableCell<Panier, String> cell = new TableCell<Panier, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button test = new Button("+");
                        test.setOnAction(event -> {

                            Panier p = getTableView().getItems().get(getIndex());
                            System.out.println("+++:" + p);
                            PanierService panierService = new PanierService();

                            panierService.incquantite(p.getId());
                            try {
                                refresh();
                            } catch (IOException ex) {
                                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            //panierService.addProduct(SingninController.userIden, p.getId());
                            System.out.println(p);
                        });

                        setGraphic(test);
                        setText(null);

                    }
                }

            };
            return cell;
        };
        
        Callback<TableColumn<Panier, String>, TableCell<Panier, String>> cellFactoryDec = (param) -> {

            final TableCell<Panier, String> cell = new TableCell<Panier, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button test = new Button("-");
                        test.setOnAction(event -> {

                            Panier p = getTableView().getItems().get(getIndex());
                            System.out.println("+++:" + p);
                            PanierService panierService = new PanierService();

                            try {
                                panierService.decquantite(p.getId());
                            } catch (SQLException ex) {
                                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {
                                refresh();
                            } catch (IOException ex) {
                                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            //panierService.addProduct(SingninController.userIden, p.getId());
                            System.out.println(p);
                        });

                        setGraphic(test);
                        setText(null);

                    }
                }

            };
            return cell;
        };
        
        
        supprimerProduits.setCellFactory(cellFactoryRemove);
        incQt.setCellFactory(cellFactoryInc);
        decQt.setCellFactory(cellFactoryDec);
        listePanier.setItems(list);
    }
    
    public void refresh() throws IOException {
        System.out.println("afficher panier");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/panier.fxml"));
        Parent root = loader.load();
        containerPanier.getChildren().setAll(root);
    }

    @FXML
    private void validerCommande(ActionEvent event) throws IOException{
        System.out.println("valider la commande");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/commande.fxml"));
        Parent root = loader.load();
        containerPanier.getChildren().setAll(root);
    }

  
    
    
}
