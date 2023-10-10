/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.Entites;

import Modele.Sprite;
import Modele.Vector2;
import static java.lang.Math.abs;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

/**
 * Joueur est une classe héritant d'Entite désignant un type de joueur concret.
 * Une image lui est associée dans le créateur (/spawner).
 *
 * @author Eliott
 */
public class Joueur extends Entite {
    
    /**
    * La propriété permettant l'affichage de la vie.
    */
    private StringProperty strVie = new SimpleStringProperty();

    /**
     * Permet de récupérer la Property afin de la binder et de l'afficher.
     *
     * @return un StringProperty correspondant à la property liée à la vie du joueur.
     */
    public StringProperty vieProperty() { return strVie; }
    
    
    /**
     * Permet de décrémenter la vie du joueur lorsqu'il subit une attaque, tout en
     * modifiant la valeur du StringProperty, qui indique à ses observateurs son changement.
     */
    @Override
    public void subirAttaque(int force){
        super.subirAttaque(force);
        strVie.set("Vie : "+vie);
    }
    
    /**
     * Le constructeur de la classe Joueur en spécifiant les valeurs nécessaires
     * à sa création.
     * 
     * @param position la position initiale du joueur (Vector2).
     * @param direction la direction initiale du joueur (Vector2).
     * @param img l'image associée au monstre (permet de générer son sprite)
     */
    public Joueur(Vector2 position, Vector2 direction, Image img){
        this.position=position;
        this.direction=direction;
        this.vie=100;
        this.vitesse=7;
        this.rayon=25;
        
        this.sprite=new Sprite(img,this.rayon,this.position, this.direction);
        
        this.canMove=true;
        strVie.set("Vie : "+vie);
    }
    
}
