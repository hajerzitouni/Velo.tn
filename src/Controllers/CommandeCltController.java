/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Commande;
import Entity.Session;
import Services.CommandeService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class CommandeCltController implements Initializable {

    @FXML
    private AnchorPane containerCommande;
    @FXML
    private TableView listeCommandeAdmin;
   
   // private TableColumn<Commande, Integer> idCommande;
    @FXML
    private TableColumn<Commande, Integer> prixTotale;
    @FXML
    private TableColumn<Commande, String> etatCommande;
    
    @FXML
    private TableColumn supprimerCommande;
   
    @FXML
    private TextField mot;
    
    private ObservableList<Commande> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        populateTableView();
    }    
    
    private void populateTableView() {
        
        CommandeService commandeService = new CommandeService();
        try {
            list = commandeService.getcommandeclient(Session.getCurrentSession());
        } catch (Exception ex) {
            Logger.getLogger(CommandeCltController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        //idCommande.setCellValueFactory(new PropertyValueFactory<>("id"));
        etatCommande.setCellValueFactory(new PropertyValueFactory<>("etat"));
        prixTotale.setCellValueFactory(new PropertyValueFactory<>("prixtotal"));
        
        Callback<TableColumn<Commande, String>, TableCell<Commande, String>> cellFactoryRemove = (param) -> {

            final TableCell<Commande, String> cell = new TableCell<Commande, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {//if(4>9){
                        final Button test = new Button("Supprimer");
                        test.setOnAction(event -> {
                            Commande p = getTableView().getItems().get(getIndex());
                            CommandeService commandeService1 = new CommandeService();

                            commandeService.supprimercommande(p.getId());
                            try {
                                refresh();
                            } catch (IOException ex) {
                                Logger.getLogger(CommandeCltController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            //panierService.addProduct(SingninController.userIden, p.getId());
                            System.out.println(p);
                        });

                        setGraphic(test);
                        setText(null);
                        //}

                    }
                }

            };
            return cell;
        };
        
      /*  Callback<TableColumn<Commande, String>, TableCell<Commande, String>> cellFactoryTraiter = (param) -> {

            final TableCell<Commande, String> cell = new TableCell<Commande, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {//if(4>9){
                        final Button test = new Button("Traiter");
                        test.setOnAction(event -> {
                            Commande p = getTableView().getItems().get(getIndex());
                            CommandeService commandeService1 = new CommandeService();

                            commandeService.modifiercommande(p.getId());
                            try {
                                refresh();
                            } catch (IOException ex) {
                                Logger.getLogger(CommandeCltController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            //panierService.addProduct(SingninController.userIden, p.getId());
                            System.out.println(p);
                        });

                        setGraphic(test);
                        setText(null);
                        //}

                    }
                }

            };
            return cell;
        };
*/
        
        supprimerCommande.setCellFactory(cellFactoryRemove);
       // modifierCommande.setCellFactory(cellFactoryTraiter);
        listeCommandeAdmin.setItems(list);
        
    }
    
    public void refresh() throws IOException {
        System.out.println("afficher mes commandes");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/commandeClt.fxml"));
        Parent root = loader.load();
        containerCommande.getChildren().setAll(root);
    }
    
 

    @FXML
    private void chercher(KeyEvent event) {
        System.out.println(mot.getText());
        CommandeService commandeService = new CommandeService();
        list = commandeService.cherchercmd(mot.getText());
        for (Commande commande : list) {
            System.out.println(commande);
        }
        
       // idCommande.setCellValueFactory(new PropertyValueFactory<>("id"));
        etatCommande.setCellValueFactory(new PropertyValueFactory<>("etat"));
        prixTotale.setCellValueFactory(new PropertyValueFactory<>("prixTotale"));
        
        listeCommandeAdmin.setItems(list);
    }
    

    
}
