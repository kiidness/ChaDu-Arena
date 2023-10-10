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
 * ChaTank est une classe héritant de Monstre désignant un type de monstre concret.
 * Une image lui est associée dans le créateur (/spawner).
 *
 * @author Eliott
 */
public class ChaTank extends Monstre {

    /**
     * Le constructeur de la classe ChaTank en spécifiant les valeurs nécessaires
     * à sa création.
     *
     * @param position la position initiale du monstre (Vector2)
     * @param direction la direction initiale du monstre (Vector2)
     * @param img l'image associée au monstre (permet de générer son sprite)
     */
    public ChaTank(Vector2 position, Vector2 direction, Image img){
        this.position=position;
        this.direction=direction;
        this.vie=200;
        this.vitesse=2;
        this.rayon=40;
        this.sprite=new Sprite(img,this.rayon,position, this.direction);
        
        this.force=15;
        this.canMove=true;
    }
}
