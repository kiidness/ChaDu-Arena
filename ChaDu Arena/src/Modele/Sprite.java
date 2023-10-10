/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Sprite est la classe utilisée pour gérer l'animation de l'ImageView.
 * L'ImageView est générée dans le constructeur à partir d'une image comportant 
 * les différentes étapes de l'animation et d'un offsetX calculé (= décalage / avancement).
 * 
 * @author Eliott
 */
public class Sprite {
    
    /**
    * L'ImageView gérée par la classe et affichée dans le StackPane en parallèle.
    */
    private ImageView iv;
    
    /**
    * La taille de l'ImageView (hauteur et largeur car elle est toujours carrée)
    */
    private int taille;
    
    /**
    * La largeur totale de l'image utilisée et utilisée par l'ImageView.
    * Permet de gérer son décalage dans l'ImageView.
    */
    private int largeurTotale;
    
    /**
    * L'offsetX, ou autrement dis le décalage de l'image qui n'est affiché qu'en partie. (principe d'un sprite)
    */
    private int offsetX;
    
    /**
     * Constructeur de la classe Sprite en spécifiant les valeurs nécessaires à
     * son initialisation et à son bon fonctionnement.
     *
     * @param image l'image utilisée pour créer l'ImageView qui sera animée
     * @param rayon le rayon de l'entité, multiplié par 2 il est égal à la largeur
     * ainsi qu'à la hauteur de l'ImageView créée et gérée.
     * @param position la position initiale de l'ImageView créée
     * @param direction la direction initiale de l'ImageView créée (ce Vector2 est
     * utilisé afin de calculer sa rotation qui est ainsi initialisée)
     */
    public Sprite (Image image, int rayon, Vector2 position, Vector2 direction) {
        offsetX=0;
        
        taille=(int) image.getHeight();
        largeurTotale=(int) image.getWidth();
        
        int nbImages = largeurTotale/taille;
        
        iv = new ImageView();
        
        iv.setImage(image);     
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        iv.setCache(true);
        iv.setFitWidth(rayon*2);
        iv.setViewport(new Rectangle2D(offsetX, 0, taille, taille));
        iv.setRotate(direction.getRotation());
        
        iv.setTranslateX((float)position.getX());
        iv.setTranslateY((float)-position.getY());
    }
    
    /**
     * Retourne la valeur de l'ImageView créée et gérée par la classe
     * permet notamment de l'afficher en l'ajoutant à une GridPane par exemple.
     *
     * @return la valeur de l'ImageView gérée
     */
    public ImageView getImageView() {
        return iv;
    }
    
    /**
     * Actualise les valeurs de l'ImageView correspondant à un déplacement.
     * son offset est aussi décalé afin de créer l'illusion d'une animation.
     *
     * @param position la nouvelle position de l'ImageView
     * @param rotation la nouvelle rotation de l'ImageView (en degrés)
     */
    public void nextStep(Vector2 position, int rotation) {
        offsetX=(offsetX+taille)%largeurTotale;
        iv.setViewport(new Rectangle2D(offsetX, 0, taille, taille));
        iv.setTranslateX(position.getX());
        iv.setTranslateY(-position.getY());
        iv.setRotate(rotation);
    }
    
    /**
     * Actualise l'offsetX de l'ImageView en la remettant à zéro.
     * cette méthode est associée à l'idée d'arrêt de mouvement de l'objet
     * représenté par l'ImageView
     *
     * @param rotation la nouvelle rotation de l'ImageView (en degrés)
     */
    public void setIdle(int rotation) {
        offsetX=0;
        iv.setViewport(new Rectangle2D(offsetX, 0, taille, taille));
        iv.setRotate(rotation);
    }
   
}
