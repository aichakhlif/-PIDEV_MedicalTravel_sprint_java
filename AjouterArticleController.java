/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medica.reservation;

import Service.ServiceArticle;
import Service.ServiceCatégorieArticle;
import Service.ServiceClinique;
import Service.ServiceIntervention;
import Service.ServiceMedecin;
import Service.ServiceReservation;
import entities.Article;
import entities.reservation;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author ZIED
 */
public class AjouterArticleController implements Initializable {

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
    private Button UploadBtn;
    @FXML
    private ImageView catCheck;
    @FXML
    private ImageView TitreCheck;
    @FXML
    private ImageView AuteurCheck;
    @FXML
    private ImageView ContenuCheck;
    @FXML
    private ImageView Imagecheck;
    @FXML
    private Button home;
    @FXML
    private Label btnOrders1;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServiceCatégorieArticle ArCat = new ServiceCatégorieArticle();
        

        ObservableList<String> listArt = ArCat.getValuesArticle();
        Cat.setItems(listArt);
        
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
    private void AjouterArticle(ActionEvent event) {
        ServiceCatégorieArticle ArCat = new ServiceCatégorieArticle();
        
        ServiceArticle Sar = new ServiceArticle();
        if(testSaisie()){ 

        Article Ar = new Article();
        

        Ar.setTitre(Titre.getText());
        Ar.setAuteur(Auteur.getText());
        Ar.setContenu(Contenu.getText());
        Ar.setImage(Image.getText());
       
        Ar.setCategorie(ArCat.load_data(Cat.getValue()));
        

        Sar.addArticle(Ar);

        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/reservation/Article.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }}

    }

    @FXML
    private void UploadImage(ActionEvent event){
       
        
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if(file !=null)
        {
            Image.setText(file.getAbsolutePath()) ;
        }
        
            
        
        
    }
    
    private Boolean testTitre() {
        int nbNonChar = 0;
        for (int i = 1; i < Titre.getText().trim().length(); i++) {
            char ch = Titre.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && Titre.getText().trim().length() >= 3) {
            TitreCheck.setImage(new javafx.scene.image.Image("images/checkMark.png"));
            return true;
        } else {
            TitreCheck.setImage(new javafx.scene.image.Image("images/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }
    }
    
    private Boolean testAuteur() {
        int nbNonChar = 0;
        for (int i = 1; i < Auteur.getText().trim().length(); i++) {
            char ch = Auteur.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && Auteur.getText().trim().length() >= 3) {
            AuteurCheck.setImage(new javafx.scene.image.Image("images/checkMark.png"));
            return true;
        } else {
            AuteurCheck.setImage(new javafx.scene.image.Image("images/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }
    }
    
     private Boolean testContenu() {
        int nbNonChar = 0;
        for (int i = 1; i < Contenu.getText().trim().length(); i++) {
            char ch = Contenu.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && Contenu.getText().trim().length() >= 3) {
            ContenuCheck.setImage(new javafx.scene.image.Image("images/checkMark.png"));
            return true;
        } else {
            ContenuCheck.setImage(new javafx.scene.image.Image("images/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }
    }
     private Boolean testImage() {
        int nbNonChar = 0;
        for (int i = 1; i < Image.getText().trim().length(); i++) {
            char ch = Image.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && Image.getText().trim().length() >= 3) {
            Imagecheck.setImage(new javafx.scene.image.Image("images/checkMark.png"));
            return true;
        } else {
            Imagecheck.setImage(new javafx.scene.image.Image("images/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }
    }
     private Boolean testCat() {
        
        if (Cat.getValue() != null) {
                catCheck.setImage(new javafx.scene.image.Image("images/checkMark.png"));
                return true;
            } else {
                catCheck.setImage(new javafx.scene.image.Image("images/erreurCheckMark.png"));
            }
                return false;
    }
     
     
     private Boolean testSaisie() {
        String erreur = "";
      
        if (!testTitre()) {
            erreur = erreur + ("Veuillez verifier votre Nom \n");
        }
       
        
          if (!testCat()) {
            erreur = erreur + ("Veuillez choisir le medecin  \n");}
       
        return  testTitre() && testCat()&& testAuteur()&& testContenu();
          }

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void handleClicks(MouseEvent event) {
    }







}
    
     
    
    
    

