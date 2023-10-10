/*	
 * To change this license header, choose License Headers in Project Properties.	
 * To change this template file, choose Tools | Templates	
 * and open the template in the editor.	
 */	
package vue;	
import Modele.Fabriques.Createur;
import Modele.Fabriques.CreateurConcret;
import Modele.Entites.Entite;
import Modele.Gestionnaires.GestionDestruction;
import Modele.Gestionnaires.GestionMouvement;
import Modele.Gestionnaires.GestionTouches;
import Modele.Entites.Joueur;
import Modele.Projectiles.Pique;
import Modele.Projectiles.Projectile;
import Modele.Gestionnaires.Timer;
import java.io.IOException;	
import static java.lang.Thread.interrupted;
import static java.lang.Thread.sleep;
import java.net.URL;	
import java.util.LinkedList;
import java.util.ResourceBundle;	
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.Event;	
	
import javafx.fxml.FXML;	
import javafx.fxml.FXMLLoader;	
import javafx.fxml.Initializable;	
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;	
import javafx.scene.control.Button;	
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;	
import javafx.scene.layout.StackPane;
import launcher.Main;	
/**
 * La classe Jeu correspond à la vue du jeu (Controller).
 *
 * Elle contient l'ensemble des Objets nécessaires au fonctionnement du jeu ainsi que
 * la boucle de jeu (Thread boucle).
 * 
 * @author elchaput	
 */	
public class Jeu implements Initializable {	
    
    private Timer timer;
    
    private boolean jouer;
    private BooleanProperty pause = new SimpleBooleanProperty();
    
    //FXML
    @FXML
    private Label affichageVie;
    @FXML
    private Label affichageTimer;
    @FXML
    private Label affichageVagues;
    @FXML
    private StackPane arenePane;
    @FXML
    private Button btn;	
    @FXML
    private ImageView ivPause;
    
    //private Sprite spriteJoueur;
    
    private Entite joueur;
    private LinkedList<Entite> monstres;
    private LinkedList<Projectile> projectiles;
    
    
    // Gestionnaires & spawner
    private Createur createurConcret;
    private GestionTouches gestionTouches;
    
    	
    @FXML	
    private void pauseBouton (Event e) throws Exception {	
        pause.set(!pause.getValue());
    }
	
    	
    private void gameOver () throws IOException {
        jouer=false;
        FXMLLoader leLoader = new FXMLLoader(getClass().getResource("/sources/fxml/NewScore.fxml"));	
        leLoader.setController(new NewScore(timer.getNbSecondes()));	
        Main.changeScene((GridPane)leLoader.load());
    }
	
    /**
     * Constructeur de la classe Jeu.
     * Initialise toutes les variables nécessaires à son fonctionnement.
     *
     */
    public Jeu () {	
        jouer = true;
        createurConcret = new CreateurConcret();
        timer = new Timer();
        
    }	
    
    /** 
     * Initialise l'affichage des objets, les bindings des property ainsi que la
     * boucle de jeu.
     */
    @Override	
    public void initialize(URL location, ResourceBundle resources) {
        ivPause.fitWidthProperty().bind(arenePane.widthProperty());
        ivPause.fitHeightProperty().bind(arenePane.heightProperty());
        ivPause.visibleProperty().bind(pause);
        // initialisation des conteneurs des entités & projectiles
        monstres = createurConcret.fabriqueEntitesMonstres(0, arenePane);
        projectiles = new LinkedList();
        joueur=createurConcret.fabriquePerso(arenePane);
        
        //initialisation des bindings
        affichageTimer.textProperty().bind(timer.secondesProperty());
        affichageVagues.textProperty().bind(timer.vaguesProperty());
        affichageVie.textProperty().bind(((Joueur)joueur).vieProperty());
        
        gestionTouches = new GestionTouches(btn);
        
        
        // ----------  BOUCLE DE JEU  -------------
        
        Thread boucle = new Thread(()-> {
            while (!interrupted() && jouer) {
                if (!pause.getValue()) {
                    try {
                        sleep(83); // Durée d'attente pour du ~12fps
                    } catch (Exception e) {
                        Main.fenetreErreur(e);
                    }

                    /* Compte du nombre de boucles depuis le début du jeu et
                    calcul du score (= nombre de secondes passées en vie) */
                    Platform.runLater(()->{
                        GestionMouvement.calculer(joueur,monstres,projectiles,gestionTouches);
                        GestionMouvement.deplacer(joueur,monstres,projectiles);
                        GestionDestruction.detruire(monstres, projectiles, arenePane);
                        
                        if (timer.isNewVague()) {
                            monstres.addAll(createurConcret.fabriqueEntitesMonstres(timer.getNbVague(), arenePane));
                        }
                    
                        if (gestionTouches.getTirer() && timer.canTir()) {
                            projectiles.add(createurConcret.fabriqueProjectile(Pique.class, joueur, arenePane));
                            timer.setCompteurTir(0.5f);
                        }
                        
                        timer.addTic();

                    if (joueur.getVie()==0) {
                        try {
                            gameOver();
                        } catch (Exception e) {
                            Main.fenetreErreur(e); // Ouverture de la fenêtre d'erreur.
                        }
                    }
                    });
                    
                }
            }
        });
        
        // Lancement de la boucle de jeu
        boucle.start();
        
    }	
}