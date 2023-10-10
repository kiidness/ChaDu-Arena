/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import vue.Erreur;
/**
 * La classe Main gère la base de l'application.
 * (Lancement / changement de scènes / ouverture de la fenêtre d'Erreur)
 *
 * @author Chaput et Dumonet
 */
public class Main extends Application {
    private static Stage stage;
    private static FXMLLoader leLoader;

    /**
     * Constructeur de Main. Initialise la valeur du loader pour la fenêtre d'erreur.
     *
     * @throws IOException si le FXMLLoader a eu un problème
     */
    public Main() throws IOException {
        leLoader = new FXMLLoader(getClass().getResource("/sources/fxml/Erreur.fxml"));
    }
    
    // Lancement de l'application et initialisation de son affichage
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        /* Handler attaché à la fenêtre, ferme le thread 
        correspondant à la boucle de jeu lorsque la fenêtre est fermée */
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });
        
        // Initialisation de la fenêtre
        this.stage = primaryStage;
        primaryStage.setTitle("ChaDu Arena");
        GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("/sources/fxml/Menu.fxml"));
        primaryStage.minHeightProperty().set(720);
        primaryStage.minWidthProperty().set(1280);
        
        changeScene(root);

        primaryStage.show();
    }


    /**
     * Permet de changer de scène à partir d'une autre classe
     *
     * @param root est un Parent correspondant au contenu de la scène à créer.
     */
    public static void changeScene(Parent root)
    {
        stage.setScene(new Scene(root,stage.getWidth(),stage.getHeight()));
        stage.centerOnScreen();
        
    }
    
    /**
     * Permet d'afficher la fenêtre d'erreur affichant les détails de l'exception
     * catchée (affichage géré par son controlleur associé).
     *
     * @param e est l'exception catchée à afficher par la fenêtre d'erreur.
     */
    public static void fenetreErreur(Exception e) {
        Stage s = new Stage();
        s.setTitle("Erreur");
        
        leLoader.setController(new Erreur(e,s));
        
        try {
            Scene scene = new Scene(leLoader.load(),600,400);
            s.setScene(scene);
        } catch (Exception ex) {
            System.err.append(ex.getMessage());
            Platform.exit();
        }
        
        s.setAlwaysOnTop(true);
        s.setMinHeight(400);
        s.setMinWidth(600);
        s.centerOnScreen();
        s.show();
        
    }
    
    public static void main (String[] args) {
        launch(args);
    }
    
}
