/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.Projectiles;

import Modele.Sprite;
import Modele.Vector2;
import javafx.scene.image.Image;

/**
 *
 * @author Eliott
 */
public class Pique extends Projectile {
    public Pique (Vector2 position, Vector2 direction, Image img) {
        this.direction = direction.copy();
        this.position = position.copy();
        this.rayon=20;
        this.position.deplacer(direction,rayon*2);
        this.vitesse = 15;
        this.force = 50;
        this.dureeVie = 100;
        this.sprite = new Sprite(img, this.rayon, this.position, this.direction);
    }
}
