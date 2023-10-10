/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.Entites;

import Modele.Sprite;
import Modele.Vector2;
import javafx.scene.image.Image;

/**
 * Chaton est une classe héritant de Monstre désignant un type de monstre concret.
 * Une image lui est associée dans le créateur (/spawner).
 *
 * @author Eliott
 */
public class Chaton extends Monstre {
    /**
     * Le constructeur de la classe Chaton en spécifiant les valeurs nécessaires
     * à sa création.
     *
     * @param position la position initiale du monstre (Vector2)
     * @param direction la direction initiale du monstre (Vector2)
     * @param img l'image associée au monstre (permet de générer son sprite)
     */
    public Chaton(Vector2 position, Vector2 direction, Image img){
        this.position=position;
        this.direction=direction;
        this.vie=100;
        this.vitesse=4;
        this.rayon=25;
        this.sprite=new Sprite(img,this.rayon,this.position, this.direction);
        this.force=5;
        this.canMove=true;
    }
}
