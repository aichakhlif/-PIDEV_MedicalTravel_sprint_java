/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zerobug69;

import entitesyassin.MyListener;
import entitesyassin.clinique;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class FrontfxmlController implements Initializable {

    @FXML
    private Label namecl;
    @FXML
    private Label clqn;
    @FXML
    private ImageView clqimg;
    @FXML
    private Label namecl2;
    @FXML
    private Label clqem;
    @FXML
    private Label namecl21;
    @FXML
    private Label clqad;
    @FXML
    private Label namecl211;
    @FXML
    private Label clqtel;
    @FXML
    private Label namecl2111;
private clinique clinique;
private MyListener mylistener;
    @FXML
    private Label clqspec;
    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
  
        
    

    void setData(clinique clinique, MyListener mylistener) {
        this.clinique=clinique;
        this.mylistener =mylistener;
        clqn.setText(clinique.getNom());
        clqem.setText(clinique.getEmail());
        clqad.setText(clinique.getAdresse());
        clqtel.setText(""+clinique.getTel());
       clqspec.setText(clinique.getSpecialite());
       File file = new File(clinique.getImg().replace('/' , '\\'));
        System.out.println(file);
       
        Image im = null;
        if(file.exists()){
                 im = new Image(file.toURI().toString());
        }else{
            //im = new Image("resources/default-article.jpg"); // this is the defualt photo of the product
        }
         clqimg.setImage(im);
       
    }

    @FXML
    private void click(MouseEvent event) {
          mylistener.onClickListener(clinique);
    }

    
    
}
