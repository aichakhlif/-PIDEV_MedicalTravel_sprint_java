/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medica.dossier;

import entitiesLinda.dossier;
import entitiesLinda.rendez_vous;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import medica.dossier.DossierController;
import medica.dossier.ModifierRendezvousController;
import servicesLinda.serviceDossier;
import servicesLinda.serviceRendezvous;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class RendezvousController implements Initializable {

    @FXML
    private Button btn_ajout1;
    @FXML
    private TableView<rendez_vous> tabRendezvous;
    @FXML
    private TableColumn<rendez_vous, String> colmed;
    @FXML
    private TableColumn<rendez_vous, String> coldate;
    @FXML
    private TableColumn<rendez_vous, String> colheure;
    @FXML
    private Button modif_ren;
    @FXML
    private TableColumn<rendez_vous,Integer> colid;
    @FXML
    private ComboBox<String> cbtriObjPred;
    @FXML
    private Label btnOrders1;
    @FXML
    private Button home;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        serviceRendezvous sRen = new serviceRendezvous();
        ObservableList <rendez_vous> rendez_vous =sRen.afficherRendezvousList() ;
        afficherRendezvousList();
        ObservableList <String> listTriObjPred = FXCollections.observableArrayList("par date", "par heure");
        cbtriObjPred.setItems(listTriObjPred);
       

        btn_ajout1.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/medica/rendezvous/ajoutRendezvous.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(RendezvousController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
          home.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/medica/reservation/espaceClient.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(RendezvousController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    

    
    private void afficherRendezvousList() {
        serviceRendezvous sRen = new serviceRendezvous();
        ObservableList<rendez_vous> rendez_vous = sRen.afficherRendezvousList();
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colheure.setCellValueFactory(new PropertyValueFactory<>("heure"));
       
        colmed.setCellValueFactory(new PropertyValueFactory<>("med"));
        
        tabRendezvous.setItems(rendez_vous);

    }

    @FXML
    private void ajout(ActionEvent event) {
         
    }

    @FXML
    private void modifier_dos(ActionEvent event) throws IOException {
       rendez_vous ren= new rendez_vous();
       System.out.println("aaaaa");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/medica/rendezvous/modifierRendezvous.fxml"));
            
            Parent root = loader.load();
             
          ModifierRendezvousController HomeScene = loader.getController();
          System.out.println("aaaaa");
          serviceRendezvous sm= new serviceRendezvous();
         //String nomMed = sm.load_data2();
            HomeScene.selected_item(
                     
                  tabRendezvous.getSelectionModel().getSelectedItem().getId(),
                  tabRendezvous.getSelectionModel().getSelectedItem().getDate(),
                    tabRendezvous.getSelectionModel().getSelectedItem().getHeure(),
                        tabRendezvous.getSelectionModel().getSelectedItem().getMed()

            );
                       
                 System.out.println("bbbbbbbbbbbbb");
           
            Stage window = (Stage) modif_ren.getScene().getWindow();
            window.setScene(new Scene(root, 904, 581));
    }
    

    @FXML
    private void OnMouseObjectifPred(MouseEvent event) {
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
    private void selectTriObjPred(ActionEvent event) {
        serviceRendezvous sop = new serviceRendezvous();
        ObservableList <rendez_vous> rendez_vous = FXCollections.observableArrayList();
        if (cbtriObjPred.getValue().equals("par date")) {
           rendez_vous = sop.trierDate();
           
        } 
        else if (cbtriObjPred.getValue().equals("par heure")) {
          rendez_vous = sop.trierHeure();
        }
        tabRendezvous.setItems(rendez_vous);
    }

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void handleClicks(MouseEvent event) {
    }
        
}
