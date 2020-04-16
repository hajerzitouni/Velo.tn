/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static gui.payercoursController.isNotInteger;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import pidev.entities.cours;
import pidev.services.Upload;
import pidev.services.coursService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class CoursController implements Initializable {

    @FXML
    private AnchorPane btnAjouter;
    @FXML
    private TextField tfnamecomp;
    @FXML
    private TextField tfnbrcomp;
    @FXML
    private Button consulter;
    @FXML
    private DatePicker tfstartcomp;
    @FXML
    private TextField tfnbrcomp1;
    @FXML 
    private Button image;
    String path = "";
     @FXML
     
    private ImageView imageViewAdd;
    @FXML
    private Button btnAjout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 @FXML
    private void ajouter(ActionEvent event) {  
         
        if (validateInputs()) {
        String nbrcomp1 = tfnbrcomp1.getText();
          int nbrcomp = (Integer) Integer.parseInt(tfnbrcomp.getText()) + 0;
          int namecomp = (Integer) Integer.parseInt(tfnamecomp.getText()) + 0;
        String startcomp = tfstartcomp.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      
       
        coursService cs = new coursService();
        cours c = new cours (namecomp,startcomp,nbrcomp1,nbrcomp,path);
        cs.AddCours(c);
        System.out.println("cours ajouté"); 
        try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("gui/Affichercours.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
            
            
        }
        
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }}
    
        
    @FXML
    private void consulter(ActionEvent event) {
         {
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AfficherCours.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }

     @FXML
    private void image(ActionEvent event) throws MalformedURLException {
        
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
            imageViewAdd.setImage(image);

        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("NoData");
        }
        
                } catch (IOException ex) {
            Logger.getLogger(CoursController.class.getName()).log(Level.SEVERE, null, ex);}
        

}


    private boolean validateInputs() {
        if (tfnbrcomp1.getText().length() == 0 || tfnbrcomp.getText().length() == 0
                ||  tfnamecomp.getText().length() == 0) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("tous les champs doivent etre remplis");
            alert.showAndWait();
            return false;
        } else if (isNotInteger(tfnamecomp.getText()) || isNotInteger(tfnbrcomp.getText())) {

            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("niveau,prix doivent etre des nombres");
            alert1.setHeaderText(null);
            alert1.show();
            return false;

        }
       
        

        return true;
    }

}

    
    


        
     

   


   
    

