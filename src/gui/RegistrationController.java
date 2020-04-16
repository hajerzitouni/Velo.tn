/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import application1.fos_user;
import application1.fos_userService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


/**
 * FXML Controller class
 *
 * @author ouertani
 */
public class RegistrationController implements Initializable {

    @FXML
    private VBox VBoxMdp;
    @FXML
    private TextField txtPseudo;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtCfPassword;
    @FXML
    private Button back;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtnumcarte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Back(ActionEvent event) {
    }

    @FXML
    private void ajouterClient(ActionEvent event) throws SQLException {
        
        fos_userService sr = new fos_userService();
        //String image = "";
        String valueRadio = null;

        if (validateInputs()) {
            fos_user fos_user = new fos_user(txtPseudo.getText(), txtEmail.getText(), txtPassword.getText(),Integer.parseInt(txtnumcarte.getText()));
            fos_userService us = new fos_userService();

            sr.ajouterClient(fos_user);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.setContentText("Operation effectuée avec succée !");
            alert.show();
            alert.setOnHidden(e -> {
                if (alert.getResult() == ButtonType.YES) {
                    System.out.println("good");
                } else {
                    System.out.println("canceled");
                }
            });

        }
    }
        
        private boolean validateInputs() {
        if ((txtPseudo.getText().isEmpty()) || (txtEmail.getText().isEmpty()) || 
                (txtPassword.getText().isEmpty())) 
        {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veillez remplir tout les champs");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
        } else if (!(txtCfPassword.getText().equals(txtPassword.getText()))) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veillez vérifier votre mot de passe");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } else if (!(validate(txtEmail.getText()))) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veillez vérifier votre email");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } else if ((txtnumcarte.getText().trim().length() > 8) || ((txtnumcarte.getText().trim().length() < 8))) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veillez vérifier votre numéro de votre carte boncaire");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        }
        return true;
    }
        
        
        public static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

}
    
