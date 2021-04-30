/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zerobug69;

import com.mysql.jdbc.Connection;
import entitesyassin.MyListener;
import entitesyassin.clinique;
import javafx.geometry.Insets;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import database.ConnexionSingleton;
import java.io.File;
import java.sql.DriverManager;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import medica.reservation.ReservationController;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class FXMLmController implements Initializable {

    @FXML
    private VBox chosenclinique;
    @FXML
    private Label clqname;
    @FXML
    private ImageView clqimg;
    @FXML
    private ScrollPane scroll;
    @FXML
    private Label clqemail;
    @FXML
    private Label clqadr;
    @FXML
    private Label clqtel;
    @FXML
    private Label clqscep;
    @FXML
    private GridPane grid;
    private MyListener mylistener;
    @FXML
    private Button off1;
    @FXML
    private Button home;

    /**
     * Initializes the controller class.
     */
    private void setchosenclq(clinique clinique) {
        clqname.setText(clinique.getNom());
        clqemail.setText(clinique.getEmail());
        clqadr.setText(clinique.getAdresse());
        clqtel.setText("" + clinique.getTel());
        clqscep.setText(clinique.getSpecialite());
        File file = new File(clinique.getImg().replace('/', '\\'));
        System.out.println(file);

        Image im = null;
        if (file.exists()) {
            im = new Image(file.toURI().toString());
        } else {
            //im = new Image("resources/default-article.jpg"); // this is the defualt photo of the product
        }
        this.clqimg.setImage(im);
        //clqscep.setText(clinique.getSpec());
        chosenclinique.setStyle("-fx-background-color: #171717;\n"
                + "-fx-background-radius: 30;");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        clq.addAll(getData());
        if (clq.size() > 0) {
            setchosenclq(clq.get(0));
            mylistener = new MyListener() {
                @Override
                public void onClickListener(clinique clinique) {
                    setchosenclq(clinique);
                }
            };
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < clq.size(); i++) {
                    FXMLLoader fxmlloader = new FXMLLoader();
                    fxmlloader.setLocation(getClass().getResource("frontfxml.fxml"));

                    AnchorPane anchorpane = fxmlloader.load();

                    FrontfxmlController frontfxmlController = fxmlloader.getController();
                    frontfxmlController.setData(clq.get(i), mylistener);
                    if (column == 3) {
                        column = 0;
                        row++;
                    }

                    grid.add(anchorpane, column++, row);
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);

                    GridPane.setMargin(anchorpane, new Insets(10));
                }
            } catch (IOException ex) {
                Logger.getLogger(FXMLmController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

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

    public Connection getConnection() {
        Connection conn;
        try {
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/medicatravel3", "root", "");
            return conn;
        } catch (Exception ex) {
            System.out.println("Eroor de connexion");
            return null;
        }
    }

    public ObservableList<clinique> getCliniqueList() {
        ObservableList<clinique> cliniques = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT *From clinique";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            clinique clinique;
            while (rs.next()) {
                clinique = new clinique(rs.getInt("id"), rs.getString("adresse"), rs.getInt("tel"), rs.getString("nom"), rs.getString("image"), rs.getString("specialite"), rs.getString("email"));
                cliniques.add(clinique);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cliniques;
    }

    private List<clinique> clq = getCliniqueList();

    private List<clinique> getData() {
        List<clinique> clq = new ArrayList<>();
        clinique clinique;
        return clq;
    }

    @FXML
    public void changeScreenView(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("offrefront.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();

    }

    @FXML
    private void home(ActionEvent event) {
    }

}
