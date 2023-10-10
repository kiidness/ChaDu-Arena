/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import launcher.Main;


/**
 * La classe Erreur correspond à la vue pour la fenêtre des erreurs (Controller).
 *
 * @author Eliott
 */
public class Erreur implements Initializable {
    private Stage stage;
    private Exception exception;
    
    @FXML
    private Label labelMessage;
    
    @FXML
    private ListView listView;
    
    /**
     * Constructeur de la classe Erreur.
     * Fournit à la classe les infos nécessaires à son fonctionnement.
     *
     * @param exception l'exception à afficher.
     * @param stage la fenêtre d'erreur. Permet de la fermer depuis celle-ci.
     */
    public Erreur (Exception exception, Stage stage) {
        this.stage = stage;
        this.exception = exception;
    }
    
    // Bouton fermant la fenêtre d'erreur.
    @FXML
    private void quitBouton (Event e) {
        stage.close();
    }

    // Initialisation de l'affichage des infos sur l'exception.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelMessage.textProperty().set(exception.toString());
        ObservableList observableList = FXCollections.observableArrayList();
        observableList.setAll(exception.getStackTrace());
        listView.setItems(observableList);
    }
}
