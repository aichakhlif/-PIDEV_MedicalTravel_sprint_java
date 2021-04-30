/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zerobug69;

import entitesyassin.JavaMailUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class FXMLMailController implements Initializable {

    @FXML
    private TextField sendTO;
    @FXML
    private TextField Subject;
    @FXML
    private TextField text;
    @FXML
    private Button envoyer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoyeraction(ActionEvent event) throws MessagingException {
        JavaMailUtil.sendmail(sendTO.getText(),Subject.getText(),text.getText());
    }
    
}
