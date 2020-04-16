/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Produit;
import Services.ProduitService;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author asus
 */
public class modifierproduitController implements Initializable {

    @FXML
    private AnchorPane btnAjouter;
    @FXML
    private TextField tfnbrcomp111;
    @FXML
    private TextField tfnbrcomp11;
    @FXML
    private TextField tfnbrcomp1;
    @FXML
    private Button btnAjout;
  
    @FXML
    private TableColumn<Produit , String> nomProduit;
    @FXML
    private TableColumn<Produit , Integer> prixProduit;
    @FXML
    private TableColumn<Produit , Integer> quantite;
   
    @FXML
    private TableView <Produit> listeProduits;
    ProduitService ps = new ProduitService();
    @FXML
    private ImageView imageview;
    private  Produit selectedid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<Produit> produit = FXCollections.observableArrayList();
        for(Produit p: ps.getproduit())
            produit.add(p);
        nomProduit.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prixProduit.setCellValueFactory(new PropertyValueFactory<>("prix"));
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        listeProduits.setItems(produit);
    }    

void select() throws Exception {
     
          Produit selected = listeProduits.getSelectionModel().getSelectedItem();
          if (!listeProduits.getSelectionModel().getSelectedItems().isEmpty()) {
              
            selected.setPrix(Integer.parseInt(prixProduit.getText()));
            selected.setPrix(Integer.parseInt(quantite.getText()));
            selected.setNom(nomProduit.getText());

          } else {
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("aucun élément 'a ètè séléctionné");
            alert.showAndWait();
          }
    }

@FXML
private void modifier(ActionEvent event) throws IOException, SQLException {
        

        ProduitService ps = new ProduitService();

           Produit p = new Produit();
           Produit p1 = listeProduits.getSelectionModel().getSelectedItem();
            int id = p1.getId();
            int qte=Integer.parseInt(tfnbrcomp11.getText());
            int pp=Integer.parseInt(tfnbrcomp1.getText());
            Produit p2 = new Produit(id, tfnbrcomp111.getText() , qte, pp);
            ps.modifierproduit(p2);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("!!! Modification avec success !!!");
        alert.showAndWait();
        refresh();
    }
@FXML
    private void afficherImage(MouseEvent event) {
        
      selectedid = (Produit) listeProduits.getSelectionModel().getSelectedItem();
       Image i = new Image( "http://localhost//pidev.java/image/" + selectedid.getImage());
       imageview.setImage(i);
       System.out.println(selectedid.getImage());
       
    }

@FXML
    private void supprimer(ActionEvent event) {
                if(!listeProduits.getSelectionModel().getSelectedItems().isEmpty()){     
           ps.supprimerproduit(listeProduits.getSelectionModel().getSelectedItems().get(0).getId());
                    try {
                        refresh();
                    } catch (IOException ex) {
                        Logger.getLogger(modifierproduitController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }
        else{
           
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("aucun élément 'a ètè séléctionné");
        alert.showAndWait();
       }
   }
 
      public void refresh() throws IOException {
        System.out.println("afficher panier");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/modifierproduit.fxml"));
        Parent root = loader.load();
        btnAjouter.getChildren().setAll(root);
    }

}
