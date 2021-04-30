/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medica.reservation;

import Service.ServiceOffre;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import com.stripe.net.RequestOptions;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class PaiementController implements Initializable {

    @FXML
    private TextField carte;
    @FXML
    private TextField year;
    @FXML
    private TextField prix;
    @FXML
    private TextField month;
    @FXML
    private TextField cvc;
    @FXML
    private Button valider;
    @FXML
    private Button Annuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        

        valider.setOnAction((ActionEvent event) -> {
//          ServiceOffre of= new ServiceOffre();
//            System.out.println(of.load_data(parseInt(txt_offre.getText())));
//          prix.setText(of.load_data2(of.load_data(parseInt(txt_offre.getText()))));
            
            try {
               
             if (controleDeSaisi()) {
              
            if (carte.getText().isEmpty()) {
                carte.setText("");
            }
            if (month.getText().isEmpty()) {
                month.setText("");
            }
            if (year.getText().isEmpty()) {
                year.setText("");
            }
            if (cvc.getText().isEmpty()) {
                cvc.setText("");
            }   
            
               }
                
                 //Stripe.apiKey="sk_test_flb9vUYyiwC85Wx2ONpANeYf";
        Stripe.apiKey="sk_test_51IkUcHL6vEk69F2g0Fu0Z4JPnbo6jVO2MRozBhN0VY8h7mQts5ss4hDffHPv5T8HaujYDEcoZfpF4klrP4ZDoF3x001PNPYlh2";
        
        Customer a =Customer.retrieve("cus_JNPicFSsHd5d43");
       Map<String, Object> cardParams = new HashMap<String, Object>();
        cardParams.put("number", Long.parseLong(carte.getText()));
        cardParams.put("exp_month", Integer.parseInt(month.getText()));
        cardParams.put("exp_year", Integer.parseInt(year.getText()));
        cardParams.put("cvc",  Integer.parseInt(cvc.getText()));
                System.out.println("hello");
        
        Map<String, Object> tokenParams = new HashMap<String, Object>();
        tokenParams.put("card", cardParams);
                        System.out.println("hello2");

                System.out.println(tokenParams);
                
       // Token token = Token.create(tokenParams);
         
        

      //  Map<String, Object> source = new HashMap<String, Object>();
     //   source.put("source", token.getId());

      //  a.getSources().create(source);

        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", Integer.parseInt(prix.getText()));
        chargeParams.put("currency", "usd");
       // chargeParams.put("customer", a.getId());
        chargeParams.put("source", "tok_mastercard");
                System.out.println(chargeParams);
               System.out.print("aichoucha");

        
        Charge.create(chargeParams);
        System.out.print("transaction reussie");
        showAlert(Alert.AlertType.CONFIRMATION, "Données Valide", "Success", "Payment avec succes!");

       
        
                
            } catch (StripeException ex) {
                System.out.println("no paiement");
            }
                     
            
        
        });
        
        
       Annuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                     Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.close();

            }

        });
        
    }
    
  
    
    
     private boolean controleDeSaisi() {  

        if (carte.getText().isEmpty() || month.getText().isEmpty() || year.getText().isEmpty()
                || cvc.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]", carte.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez la reference ! ");
                carte.requestFocus();
                carte.selectEnd();
                return false;
            }

           if (!Pattern.matches("[0-9]*", month.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le Mois ! ");
                month.requestFocus();
                month.selectEnd();
                return false;
            }if (!Pattern.matches("[0-9]*", year.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez l'année ! ");
                year.requestFocus();
                year.selectEnd();
                return false;
            }if (!Pattern.matches("[0-9]*", cvc.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le cvc ! ");
                cvc.requestFocus();
                cvc.selectEnd();
                return false;
            }
           
        }
        return true;
    }
    
    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
    public void selected_item(Integer id) {
    
       ServiceOffre off = new ServiceOffre();
                 
       Integer liso= off.load_data2(id).getIdoffre();
        System.out.println("ooooooooo"+liso);
        prix.setText(""+liso);
        
    
    }
       
    
    
}
