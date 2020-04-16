/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import application1.MyBdd;
import application1.reclamation;
import application1.reclamationService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author Hajer
 */
public class ShowreclamationController implements Initializable {

    @FXML
    private TableColumn<?, ?> txtTypeType;
    @FXML
    private TableColumn<?, ?> text;
    @FXML
    private TextField recherche;
    @FXML
    private TableView<reclamation> table;
       reclamationService rs = new reclamationService ();
    @FXML
    private Button delete;
    @FXML
    private Button imprimer;
     private Connection con;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
 ObservableList<reclamation> reclamation = FXCollections.observableArrayList();
     try {
         
        
         for(reclamation r: rs.getAllreclam())
            reclamation.add(r);
         txtTypeType.setCellValueFactory(new PropertyValueFactory<>("titrereclam"));
         text.setCellValueFactory(new PropertyValueFactory<>("probleme"));
         
     } catch (SQLException ex) {
         Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
     }

       
        table.setItems(reclamation);
        
    
    }    

      

    @FXML

 private void delete(ActionEvent event) throws SQLException, IOException{
   if(!table.getSelectionModel().getSelectedItems().isEmpty()){
           reclamationService rs = new reclamationService ();
           rs.delete(table.getSelectionModel().getSelectedItems().get(0).getId());
       
        }
       else{
           
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("aucun élément 'a ètè séléctionné");
        alert.showAndWait();

           
        
       
    }
    }

    @FXML
    private void recherche(ActionEvent event) {
        ObservableList<reclamation> reclamation= FXCollections.observableArrayList();
            reclamation= rs.recherche(recherche.getText());
     
          txtTypeType.setCellValueFactory(new PropertyValueFactory<>("titrereclam"));
         text.setCellValueFactory(new PropertyValueFactory<>("probleme"));
         
   
       
        table.setItems(reclamation);
        
    
    }    
 private static Font orangeFont = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.NORMAL, BaseColor.ORANGE);
   private static Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0)); 

    @FXML
    private void imprimer(ActionEvent event) throws FileNotFoundException, ClassNotFoundException, SQLException {
     try {
           Class.forName("com.mysql.jdbc.Driver");
              con = MyBdd.getInstance().getConnexion();
           
  Statement stmt = con.createStatement();
                    /* Define the SQL query */
                    ResultSet query_set = stmt.executeQuery("SELECT *From reclamation");
                    /* Step-2: Initialize PDF documents - logical objects */
                    Document my_pdf_report = new Document();
                    PdfWriter.getInstance(my_pdf_report, new FileOutputStream("pdf_report_from_sql_using_java.pdf"));
                    my_pdf_report.open();            
                    //we have four columns in our table
            Paragraph intro = new Paragraph("La liste des reclamations", orangeFont);
              Paragraph space = new Paragraph("  ") ;
              
                    PdfPTable my_report_table = new PdfPTable(2);
                   
 
                    //create a cell object
                    PdfPCell table_cell;
PdfPCell c1 = new PdfPCell ( new Paragraph("Titre",bfBold12));
 my_report_table.addCell(c1);
PdfPCell c2 = new PdfPCell ( new Paragraph("Description",bfBold12));
 my_report_table.addCell(c2);
                    while (query_set.next()) {                
                                    String dept_id = query_set.getString("titrereclam");
                                    table_cell=new PdfPCell(new Phrase(dept_id));
                                    my_report_table.addCell(table_cell);
                                    String dept_name=query_set.getString("probleme");
                                    table_cell=new PdfPCell(new Phrase(dept_name));
                                    my_report_table.addCell(table_cell);
                                    
                                    }
                    /* Attach report table to PDF */
                    my_pdf_report.add(intro);  
                    my_pdf_report.add(space);  
                    my_pdf_report.add(space);  
                    my_pdf_report.add(space);  
                       my_pdf_report.add(c1);
                        my_pdf_report.add(c2);  
                    my_pdf_report.add(my_report_table);                       
                    my_pdf_report.close();

                    /* Close all DB related objects */
                    query_set.close();
                    stmt.close(); 
                    con.close();               



    } catch (FileNotFoundException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    } catch (DocumentException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    }
    
    }
}
