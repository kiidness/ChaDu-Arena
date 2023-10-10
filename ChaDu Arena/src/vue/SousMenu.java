
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import launcher.Main;
/**
 * La classe SousMenu correspond à la vue des sous menus simples (Controller).
 * 
 * @author elchaput
 */
public class SousMenu {
    @FXML
    private void retourBouton(Event e) throws Exception {
        GridPane menuGPane = (GridPane)FXMLLoader.load(getClass().getResource("/sources/fxml/Menu.fxml"));
        Main.changeScene(menuGPane);
    }
    
}