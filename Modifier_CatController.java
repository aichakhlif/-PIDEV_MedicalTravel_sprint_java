/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medica.reservation;

import Service.ServiceCatégorieArticle;
import entities.CategorieArticle;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ZIED
 */
public class Modifier_CatController implements Initializable {

    @FXML
    private TextField NomCat;
    @FXML
    private Button Modifier;
    @FXML
    private Button Supprimer;
    @FXML
    private TextField IdCat;
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
    
    @FXML
    private void Mod(ActionEvent event)  {
        
        ServiceCatégorieArticle SCat = new ServiceCatégorieArticle();

         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation modification");
        alert.setHeaderText("Etes vous sur de vouloir modifier cette catégorie ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
         CategorieArticle Cat = new CategorieArticle();
         Cat.setId(SCat.getIdParDesc(IdCat.getText()));
         System.out.println(SCat.getIdParDesc(IdCat.getText()));

        Cat.setNom(NomCat.getText());
        System.out.println(NomCat.getText());

      
        SCat.modifierCat(Cat);
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/reservation/categorie.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }

        }else {
            System.out.println("Cancel");
        }
        
     
        

    }
    
public void selected_item(int id ,String nom) {
        ServiceCatégorieArticle Cat = new ServiceCatégorieArticle();
                   

        System.out.println(id + nom  );
        IdCat.setText(""+id);
        NomCat.setText(nom);
        

    }

@FXML
    private void supprimerCat(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("Voulez vous supprimer Cette Catégorie?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        ServiceCatégorieArticle SCat = new ServiceCatégorieArticle();
            System.out.println(IdCat.getText());
             System.out.println(SCat.getIdParDesc(IdCat.getText()));
            SCat.supprimerCat(SCat.getIdParDesc(IdCat.getText()));
            try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/reservation/categorie.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
      
        } else {
            System.out.println("Cancel");
        }
    }

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void handleClicks(MouseEvent event) {
    }


    
    
    
}
