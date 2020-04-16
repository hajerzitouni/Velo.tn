/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import application1.Upload;
import application1.velo;
import application1.velooService;
import java.awt.Desktop;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author Hajer
 */
public class VeloController implements Initializable {

    @FXML
    private AnchorPane btnAjouter;
    @FXML
    private TextField tfnamecomp;
    @FXML
    private Button btnAjout;
    @FXML
    private TextField tfnbrcomp1;
    @FXML
    private TextField tfnbrcomp11;
    @FXML
    private TextField tfnbrcomp111;
    @FXML
    private Button btnImportImage;
    String path = "";
     @FXML
     
    private ImageView imageView;
   
   
    @FXML
    private TextArea textArea;
    @FXML
    private Button retour;
    @FXML
    private TextField tfnbrcomp12;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       

    }    


     @FXML
    private void ajouter(ActionEvent event) {
          try {

    
        
     velooService vs = new velooService ();
         if(validateInputs()){
          String namecomp= tfnamecomp.getText();
          String nbrcomp11 = tfnbrcomp111.getText();
          String nbrcomp12 = tfnbrcomp12.getText();
       int nbrcomp = (Integer) Integer.parseInt(tfnbrcomp1.getText()) + 0;
        int nbrcomp1 = (Integer) Integer.parseInt(tfnbrcomp11.getText()) + 0;
          
  
      velo v = new velo(namecomp,nbrcomp,nbrcomp1,nbrcomp11,path,nbrcomp12 );
      
         
    vs.ajouter2(v);
        System.out.println("cours"); 
        
           
            
            
        
         }
          }
         catch (SQLException ex) {
            Logger.getLogger(velooService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
     public static boolean isNotInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return true;
        }

        return false;
    }
    
     private boolean validateInputs() {
        if (tfnbrcomp111.getText().length() == 0 || tfnbrcomp111.getText().length() == 0
                ||  tfnamecomp.getText().length() == 0) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("tous les champs doivent etre remplis");
            alert.showAndWait();
            return false;
        } else if (isNotInteger(tfnbrcomp1.getText()) ){

            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText(" doivent etre des nombres");
            alert1.setHeaderText(null);
            alert1.show();
            return false;

        }
       
        

        return true;
    }
     @FXML
    private void importer(ActionEvent event) throws MalformedURLException {
        
         BufferedOutputStream stream = null;
	String globalPath="C:\\\\wamp\\\\www\\\\pidev.java";
        
        
        try {
        
        JFileChooser fileChooser = new JFileChooser(); 
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "gif", "png");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {         
            
            File selectedFile = fileChooser.getSelectedFile();
            path = selectedFile.getName();
            
            Path p = selectedFile.toPath();      
            byte[] bytes = Files.readAllBytes(p); 
            File dir = new File(globalPath);
            
            File serverFile = new File(dir.getAbsolutePath()+File.separator + path);
            stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            
            
            String path2 = selectedFile.toURI().toURL().toString();
            Image image = new Image(path2);
            imageView.setImage(image);

        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("NoData");
        }
        
                } catch (IOException ex) {
            Logger.getLogger(VeloController.class.getName()).log(Level.SEVERE, null, ex);}
        

}
 @FXML
    private void retour(ActionEvent event) throws IOException {
         
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/gui/Affichervelo.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

    

    
