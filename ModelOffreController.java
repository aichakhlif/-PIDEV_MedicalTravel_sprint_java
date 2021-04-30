/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zerobug69;

import entitesyassin.MyListener;
import entitesyassin.offre;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ModelOffreController implements Initializable {
 @FXML
    private Label namecl;

    @FXML
    private Label namecl2;

    @FXML
    private Label clq;

    @FXML
    private Label namecl21;

    @FXML
    private Label med;

    @FXML
    private Label namecl211;

    @FXML
    private Label inter;

    @FXML
    private Label namecl2111;

    @FXML
    private Label pr;
private offre offre;
private MyListener mylistener;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  
       
    }    
    void setData(offre offre, MyListener mylistener) {
        this.offre=offre;
        this.mylistener =mylistener;
        clq.setText(offre.getClinique_id());
        med.setText(offre.getMedecin_id());
        inter.setText(offre.getIntervention_id());
        pr.setText(""+offre.getPrix());
    }


@FXML
    void click(MouseEvent event) {
 mylistener.onClickListener(offre);
    }


    
}
