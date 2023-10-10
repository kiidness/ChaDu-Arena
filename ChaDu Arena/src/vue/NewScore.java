/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import Modele.Score;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import launcher.Main;

/**
 * La classe NewScore correspond à la vue du game over (Controller).
 * 
 * Elle s'affiche lorsque la personne meurs et permet de sauvegarder son score si
 * on le désire.
 *
 * @author Eliott
 */

public class NewScore implements Initializable {
    private int secondes;
    
    
    private BooleanProperty afficherbtn = new SimpleBooleanProperty();
    private StringProperty textePseudo = new SimpleStringProperty();
    
    @FXML
    private Label textScore;
    
    @FXML
    private Button scoreBtn;
    
    @FXML
    private TextField textField;
    
    /**
     * Constructeur de NewScore récupérant le score effectué par le joueur.
     *
     * @param secondes int correspondant au nombre de secondes survécues
     */
    public NewScore (int secondes) {
        this.secondes=secondes;
        afficherbtn.set(false);
    }

    // Initialise l'affichage et les bindings des Property.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (secondes < 11)
            textScore.textProperty().set("Vous avez survécu "+secondes+" secondes. Vous êtes pas très bon :/");
        else
            textScore.textProperty().set("Vous avez survécu "+secondes+" secondes.");
        textField.focusTraversableProperty().set(false);
        
        // Bind bidirectionel de la valeur du pseudo donnée par l'utilisateur
        textField.textProperty().bindBidirectional(textePseudo);
        
        // Bind de la visibilité du bouton avec la BooleanProperty
        scoreBtn.visibleProperty().bind(afficherbtn);

        ChangeListener listener;
        
        afficherbtn.set(false);
        
        listener = (ChangeListener) (ObservableValue observable, Object oldValue, Object newValue) -> {
            if (textePseudo.getValue().length() >= 3 && textePseudo.getValue().length() <= 42)
                afficherbtn.set(true);
            else
                afficherbtn.set(false);
        };
        textField.textProperty().addListener(listener);
    }
    
    @FXML
    private void retourBouton(Event e) throws Exception {
        GridPane menuGPane = (GridPane)FXMLLoader.load(getClass().getResource("/sources/fxml/Menu.fxml"));
        Main.changeScene(menuGPane);
    }
    
    @FXML
    private void scoreBouton(Event e) throws Exception {
        FXMLLoader leLoader = new FXMLLoader(getClass().getResource("/sources/fxml/Classement.fxml"));
        leLoader.setController(new Classement(new Score(textePseudo.getValue(), secondes)));	
        Main.changeScene((GridPane)leLoader.load());
    }
    
    
}
