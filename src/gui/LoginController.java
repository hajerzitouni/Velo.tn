/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import application1.Application1;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import application1.fos_user;
import application1.fos_userService;

import utils.BCrypt;


/**
 * FXML Controller class
 *
 * @author ouertani
 */
public class LoginController implements Initializable {

    @FXML
    private Button connectbutton;
    @FXML
    private VBox VBoxMdp;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label txtetat;
    @FXML
    private Button registerbutton;
    @FXML
    private Button mdpoubliébutton;
    @FXML
    private Button connectbutton1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
    }   
        private boolean validateInputs() throws SQLException {

        if (txtUsername.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veillez saisir votre nom d'utilisateur");
            alert1.setHeaderText("Controle de saisie");
            alert1.show();
            return false;
        }
        if (txtPassword.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veillez saisir votre mot de passe");
            alert1.setHeaderText("Controle de saisie");
            alert1.show();
            return false;
        }
        return true;
    }
        

    @FXML
    private void SeConnecter(ActionEvent event) throws SQLException, IOException{
            if (validateInputs()) {
            fos_userService us = new fos_userService();
            String pseudo = txtUsername.getText();
            String password = txtPassword.getText();

            fos_user u = us.searchByPseudoPass(pseudo, password);
            System.out.println(u);
            if (u != null && u.getEnabled() == 1 && BCrypt.checkpw(txtPassword.getText(), u.getPassword())) {
                Application1.fos_user = u;
                      Application1.user_id = u.getId();
                /*System.out.println("sooooo nice");*/
                txtetat.setText("");

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Succès");
                alert.setHeaderText("Authentifié");
                alert.setContentText("Vous êtes connecté en tant que :" + u.getUsername());
                

                alert.showAndWait();
                
                
                
                AnchorPane root = getRole(u);
                txtPassword.getScene().setRoot(root);

            } else {
                txtetat.setText("Identifiants incorrects.");
            }

        }
    }

    @FXML
    private void goRegister(ActionEvent event){
         Parent root;
            try{
            root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/Registration.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Register");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
        }
                catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void recover(ActionEvent event) {
                 Parent root;
            try{
            root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/recover.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Recouvrer votre mot de passe");
            stage.setScene(new Scene(root, 601, 438));
            stage.show();
        }
                catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @FXML
    private void facebookcnct(ActionEvent event) throws SQLException, IOException {
        String domain = "https://localhost";
        String appId = "344309509416204";
        String appSecret = "ce2ad147b8ac69cb16df8c90c8c62990";

        String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appId + "&redirect_uri=" + domain + "&scope=public_profile,user_birthday,email";

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ouertani\\Documents\\NetBeansProjects\\piTry\\src\\API\\chromedriver_win32\\\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get(authUrl);

        System.out.println(driver.getCurrentUrl());
        String accessToken;

        boolean b = true;
        while (b) {
            if (!driver.getCurrentUrl().contains("facebook.com")) {

                String url = driver.getCurrentUrl();
                accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
                System.out.println("test");

                driver.quit();
                b = false;
                FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                String fields = "name,first_name,last_name,age_range,birthday,email,gender,address";
                fos_user user = fbClient.fetchObject("me", fos_user.class, Parameter.with("fields", fields));
                System.out.println(user.getUsername());
                System.out.println(user.toString());

                fos_userService usr = new fos_userService();

                if (usr.getUserByEmail(user.getEmail()) != null) {
                    fos_user u = usr.getUserByEmail(user.getEmail());
                       Application1.fos_user = u;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/homev.fxml"));
                    AnchorPane root = (AnchorPane) loader.load();
                    connectbutton.getScene().setRoot(root);

                } 
                else {
                        fos_user u = new fos_user(user.getUsername().toLowerCase(), user.getEmail(), "ROLE_USER",0);
                           Application1.fos_user = u;
                        usr.ajouterClient(u);
                    }
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/homev.fxml"));
                    AnchorPane root = (AnchorPane) loader.load();
                    connectbutton.getScene().setRoot(root);

                }

            }
        }
    
        public AnchorPane getRole(fos_user user) throws IOException{
        AnchorPane root=null;
        if (user.getRoles().contains("ROLE_ADMIN")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/homev.fxml"));
            root = (AnchorPane) loader.load();
        }else if(user.getRoles().contains("ROLE_USER")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/homev_1.fxml"));
            root = (AnchorPane) loader.load();
        }
        return root;
    }
        
}
    
    
