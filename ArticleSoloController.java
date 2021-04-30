/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medica.reservation;

import Service.ServiceArticle;
import Service.ServiceCatégorieArticle;
import entities.Article;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ZIED
 */
public class ArticleSoloController implements Initializable {
    
    ObservableList Article = FXCollections.observableArrayList();
    @FXML
    private Button Ajouterlike;
    @FXML
    private Button SupprimerLike;
    @FXML
    private Button Retour;
    @FXML
    private Label Titre;
    @FXML
    private Button home;
    @FXML
    private Label btnOrders1;
    @FXML
    private Label Auteur;
    @FXML
    private Label Contenu;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

        // TODO
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
    
    
    
    public void selected_item1(String titre) {
                
                   
                   
                   
        
           
        
        ServiceArticle SAr = new ServiceArticle();
        
        
        Titre.setText(titre);
        Contenu.setText(SAr.GetContenu(titre));
        Auteur.setText(SAr.GetAuteur(titre));
        
        
           
            
            
            System.out.println(SAr.GetAuteur(titre));
            
            //Image imageURL= new Image("file:///C:/xampp/htdocs/MedicaTravel-main/MedicaTravel-main/public/Uploads/Image/" + SAr.GetAuteur(titre));
          
            //Image. setImage(new javafx.scene.image.Image("file:///C:/xampp/htdocs/MedicaTravel-main/MedicaTravel-main/public/Uploads/Image/"+ SAr.GetAuteur(titre)));
        
        
        /*ID.setText(""+id);
        Titre.setText(titre);
        Auteur.setText(auteur);
        Contenu.setText(contenu);*/
       
       

    }

    

    @FXML
    private void AjouterLike(ActionEvent event) {
        ServiceArticle SAr = new ServiceArticle();
        
        SAr.likerArticle(7, 9);
        System.out.println("nnnnn");
    }

    @FXML
    private void SupprimerLike(ActionEvent event) {
        ServiceArticle SAr = new ServiceArticle();
        
        SAr.DislikerArticle(7, 9);
        System.out.println("nnnnn");
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/reservation/BlogFront.fxml"));
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
