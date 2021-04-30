/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medica.dossier;

import entitiesLinda.dossier;
import entitiesLinda.medecin;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import medica.reservation.ReservationController;
import servicesLinda.serviceDossier;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ModifierDossierController implements Initializable {

    @FXML
    private TextField Ref;
    @FXML
    private TableView<dossier> tabDossier;
    @FXML
    private TableColumn<dossier, String> colref;
    @FXML
    private TableColumn<dossier, String> colfichier;
    
    @FXML
    private TextField fichier;
    @FXML
    private Button ok;
    @FXML
    private Button ok1;
    @FXML
    private TextField id_dos;
    @FXML
    private Button home;
    @FXML
    private Label btnOrders2;
    @FXML
    private Label btnOrders1;

    /**
     * Initializes the controller class.
     */
    @Override
  
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        serviceDossier dos = new serviceDossier();
        ObservableList<dossier> dossier = dos.afficherDossierList();
afficherDossierList();


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
    
     public void selected_item(int id,String reference, String Fichier) {
                
        serviceDossier Dossier = new serviceDossier();

        System.out.println(id +reference + Fichier  );
         id_dos.setText(""+id);
        Ref.setText(reference);
        fichier.setText(Fichier);
        
        
        
        

    }

    @FXML
    private void modifierdossier(ActionEvent event) {
        serviceDossier dos = new serviceDossier();
        

         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation modification");
        alert.setHeaderText("Etes vous sur de modifier cet reservation?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            dossier doss = new dossier();
        doss.setId(dos.getIdParDesc(id_dos.getText()));
           
                
         System.out.println(dos.getIdParDesc(id_dos.getText()));

        doss.setReference(Ref.getText());
        System.out.println(Ref.getText());

        doss.setFichier(fichier.getText());
        System.out.println(fichier.getText());

        
            System.out.println("yyyyyyyyyy");
        
        dos.modifierDossier(doss);
         try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/dossier/dossier.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DossierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }else {
            System.out.println("Cancel");
        }
    }

   private void afficherDossierList() {
        serviceDossier sDos = new serviceDossier();
        ObservableList<dossier> dossier = sDos.afficherDossierList();
        colref.setCellValueFactory(new PropertyValueFactory<>("reference"));
        colfichier.setCellValueFactory(new PropertyValueFactory<>("fichier"));
        
     //   tabDossier.setItems(dossier);

    }

    @FXML
    private void OnMouseObjectifPred(MouseEvent event) {
    }

    @FXML
    private void suppdossier(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("Etes vous sur de supprimer cet reservation?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        serviceDossier sDos = new serviceDossier();
            System.out.println(id_dos.getText());
             System.out.println(sDos.getIdParDesc(id_dos.getText()));
            sDos.supprimerDossier(sDos.getIdParDesc(id_dos.getText()));
try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/dossier/dossier.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DossierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        } else {
            System.out.println("Cancel");
        }
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
    void selected_item(int id, medecin med, String date, String heure) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void handleClicks(MouseEvent event) {
    }
    

    
    
}
