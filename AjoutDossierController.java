/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medica.dossier;

import entitiesLinda.dossier;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import medica.reservation.EspaceClientController;
import medica.reservation.ReservationController;
import servicesLinda.serviceDossier;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AjoutDossierController implements Initializable {

    @FXML
    private TextField reference;
    @FXML
    private TextField fichier;
    @FXML
    private Button ok;
    @FXML
    private Button uploadbutton;
    @FXML
    private Button home;
    @FXML
    private Label btnOrders1;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        serviceDossier dos = new serviceDossier();
        
         home.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/medica/reservation/espaceClient.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       
        

    }

    @FXML
    private void ajoutDossier(ActionEvent event) throws IOException {
        serviceDossier dos = new serviceDossier();
        
          File f = new File(fichier.getText());

        //dossier doss = new dossier();
        dossier doss = new dossier(reference.getText(),
        "C:\\Users\\admin\\Downloads\\medicatravel - aicha\\medicatravel\\src\\images\\"+f.getName());
        Files.copy(Paths.get(fichier.getText()),Paths.get("C:\\Users\\admin\\Downloads\\medicatravel - aicha\\medicatravel\\src\\images\\"+f.getName()),REPLACE_EXISTING);
      
        
       
        

        dos.addDossier(doss);
         try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/dossier/dossier.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DossierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("hello");

       
    }

    @FXML
    private void Uploadfile(ActionEvent event) {
       FileChooser fc = new FileChooser();
        String path = fc.showOpenDialog(uploadbutton.getScene().getWindow()).getPath();
        fichier.setText(path);
    }

    private void handleClicks(ActionEvent event) {
         try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/dossier/dossier.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleClicks1(ActionEvent event) {
         try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/rendezvous/rendezvous.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleClicks2(ActionEvent event) {
         try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/rendezvous/espaceClient.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void handleClicks(MouseEvent event) {
    }

    }    
    

