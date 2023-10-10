/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.util.LinkedList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import launcher.Main;
/**
 * La classe Menu correspond à la vue du menu (Controller).
 * 
 * Elle contient toutes les méthodes liées aux boutons pour charger d'autres scènes.
 * 
 * @author elchaput
 */
public class Menu {

    @FXML
    private void aideBouton(Event e) throws Exception {
        GridPane aideGPane = (GridPane)FXMLLoader.load(getClass().getResource("/sources/fxml/Aide.fxml"));
        Main.changeScene(aideGPane);
    }
    
    @FXML
    private void classementBouton(Event e) throws Exception {
        FXMLLoader leLoader = new FXMLLoader(getClass().getResource("/sources/fxml/Classement.fxml"));	
        leLoader.setController(new Classement());	
        Main.changeScene((GridPane)leLoader.load());
    }
    
    @FXML
    private void jouerBouton(Event e) throws Exception {
        GridPane jeuGPane = (GridPane)FXMLLoader.load(getClass().getResource("/sources/fxml/Jeu.fxml"));
        Main.changeScene(jeuGPane);
    }
    
}