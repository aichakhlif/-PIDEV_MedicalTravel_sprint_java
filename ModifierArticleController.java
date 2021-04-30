/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medica.reservation;

import Service.ServiceArticle;
import Service.ServiceCatégorieArticle;
import entities.Article;
import entities.CategorieArticle;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ZIED
 */
public class ModifierArticleController implements Initializable {

    @FXML
    private TextField Titre;
    @FXML
    private TextField Auteur;
    @FXML
    private TextField Image;
    @FXML
    private TextArea Contenu;
    @FXML
    private ComboBox<String> Cat;
    @FXML
    private Button Modifier_Art;
    @FXML
    private Button Supprimer_Art;
    @FXML
    private TextField ID;
    @FXML
    private Button home;
    @FXML
    private Label btnOrders1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceCatégorieArticle SCat = new  ServiceCatégorieArticle();

        ObservableList<String> listCat = SCat.getValuesArticle();
        Cat.setItems(listCat);
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
        
        ServiceArticle SAr = new ServiceArticle();
        ServiceCatégorieArticle SCat = new ServiceCatégorieArticle();

         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation modification");
        alert.setHeaderText("Etes vous sur de vouloir modifier cet Article ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
         Article cat = new Article();
         cat.setId(SAr.getIdParDesc(ID.getText()));
         System.out.println(SAr.getIdParDesc(ID.getText()));

        cat.setTitre(Titre.getText());
        
        System.out.println(Titre.getText());
        System.out.println("/n");
        cat.setAuteur(Auteur.getText());
        System.out.println(Auteur.getText());
        cat.setContenu(Contenu.getText());
        System.out.println(Contenu.getText());
        
        cat.setCategorie(SCat.load_data(Cat.getValue()));
        System.out.println(SCat.load_data(Cat.getValue()));
                
        

      
        SAr.modifierArticle(cat);
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/reservation/Article.fxml"));
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

public void selected_item(int id ,String titre, String auteur, String contenu) {
                ServiceArticle SArticle = new ServiceArticle();
                   ServiceCatégorieArticle SCat = new ServiceCatégorieArticle();
        

        System.out.println(id +titre + auteur + contenu   );
        ID.setText(""+id);
        Titre.setText(titre);
        Auteur.setText(auteur);
        Contenu.setText(contenu);
       
       

    }

@FXML
    private void supprimerArticle(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("Voulez vous supprimer Cet article?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        ServiceArticle SCat = new ServiceArticle();
            System.out.println(ID.getText());
             System.out.println(SCat.getIdParDesc(ID.getText()));
            SCat.supprimerArticle(SCat.getIdParDesc(ID.getText()));
            try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/reservation/Article.fxml"));
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
