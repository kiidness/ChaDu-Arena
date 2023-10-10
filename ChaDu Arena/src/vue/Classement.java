/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import Modele.Score;
import Persistance.PersistanceScore;
import Persistance.PersistanceScoreSerialization;
import Persistance.PersistanceScoreXml;
import java.net.URL;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import launcher.Main;

/**
 * La classe Classement correspond à la vue du classement (Controller).
 *
 * @author Eliott
 */
public class Classement implements Initializable {
    
    @FXML
    private ImageView ivBO;
    
    private LinkedList<Score> scores;
    private final PersistanceScore serializeur = new PersistanceScoreSerialization();
    private BooleanProperty easterEggBool = new SimpleBooleanProperty();
    
    @FXML
    private ListView listView;
    
    private ObservableList observableList = FXCollections.observableArrayList();
    
    /**
     * Charge les scores déjà enregistré.
     * 
     * Si le fichier n'existe pas, sa valeur est initialisée.
     * 
     */
    public Classement () {
        try {
            scores = serializeur.chargerScores();
        } catch (Exception e) {
            scores = new LinkedList();
        }
    }
    
    /**
     * Constructeur de Classement.
     * Charge les scores depuis le serializeur, ajoute le nouveau score, trie la liste
     * pour ensuite l'enregistrer.
     * 
     * La variable scores est initialisée ici.
     *
     * @param score de type Score correspond au nouveau score à enregistrer.
     */
    public Classement (Score score) {
        try {
            scores = serializeur.chargerScores();
        } catch (Exception e) {
            scores = new LinkedList();
        }
        scores.add(score);
        Comparator<Score> c = (Score s1, Score s2) -> s2.getScore() - s1.getScore();
        scores.sort(c);
        try {
            serializeur.sauvegarderScores(scores);
        } catch (Exception ex) {
            Main.fenetreErreur(ex);
        }
    }
      
    @FXML
    private void retourBouton(Event e) throws Exception {
        GridPane menuGPane = (GridPane)FXMLLoader.load(getClass().getResource("/sources/fxml/Menu.fxml"));
        Main.changeScene(menuGPane);
    }

    // Initialise l'affichage des scores dans la ListView.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        easterEggBool.set(false);
        ivBO.visibleProperty().bind(easterEggBool);
        
        observableList.setAll(scores);
        
        listView.setItems(observableList);
        
        listView.getSelectionModel().setSelectionMode(null);
        
        listView.setCellFactory(param -> 
            new ListCell<Score>(){
                @Override
                protected void updateItem(Score item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        StringExpression output = Bindings.format("%d. %s",(scores.indexOf(item)+1), item.toString());
                        setText(output.get());
                        if (item.getScore()>=100) {
                            setStyle("-fx-text-fill:green");
                        }
                        if (item.getScore()<10) {
                            setStyle("-fx-text-fill:red");
                        }
                        if (item.getPseudo().toLowerCase().contains("bouhours")) {
                            easterEggBool.set(true);
                        }
                    } else {
                        setText("");
                    }
                }
            }
        );
    }
}
