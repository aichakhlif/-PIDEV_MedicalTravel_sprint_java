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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import medica.reservation.ReservationController;
import servicesLinda.serviceDossier;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class BackController implements Initializable {

    @FXML
    private Label btnOrders1;
    @FXML
    private TableView<dossier> tabdossier;
     @FXML
    private TableColumn<dossier, String> colref;
    @FXML
    private TableColumn<dossier, ImageView> coldossier;
    @FXML
    private TableColumn<dossier, Integer> coliddos;
    @FXML
    private Button home;
    @FXML
    private Label btnOrders2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         serviceDossier sDos = new serviceDossier();
        ObservableList<dossier> dossier = sDos.afficherDossierList();
        afficherDossierList();
        
        
         home.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/medica/reservation/EspaceBack.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    
    
    private void afficherDossierList() {
        serviceDossier sDos = new serviceDossier();
        ObservableList<dossier> dossier = sDos.afficherDossierList();
         ObservableList<dossier> lista = FXCollections.observableArrayList();
          for (dossier aux : dossier){
              
                        File file = new File(aux.getFichier());
                Image image = new Image(file.toURI().toString());
                System.out.println(image.toString());
                ImageView imageView =new ImageView(image);
                imageView.setImage(image);
                imageView.setFitHeight(1000);
                imageView.setFitWidth(1000);

                aux.setImage(imageView);

            
          lista.add(aux);
          }

        colref.setCellValueFactory(new PropertyValueFactory<>("reference"));
        coldossier.setCellValueFactory(new PropertyValueFactory<>("Image"));
        coliddos.setCellValueFactory(new PropertyValueFactory<>("id"));

        
        tabdossier.setItems(lista);
        
    }

    

    private void handleClicks(ActionEvent event) {
        
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/rendezvous/rendezvous.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DossierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void handleClicks1(ActionEvent event) {
        
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/dossier/dossier.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DossierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleClicks4(ActionEvent event) {
        
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/rendezvous/espaceClient.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DossierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void OnMouseObjectifPred(MouseEvent event) {
    }

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void handleClicks(MouseEvent event) {
    }

}
