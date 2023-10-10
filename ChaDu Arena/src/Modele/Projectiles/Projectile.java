/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.Projectiles;

import Modele.Sprite;
import Modele.Vector2;
import javafx.scene.image.ImageView;

/**
 * Projectile est une classe abstraite posant les bases pour la créations de nouveaux
 * types de projectiles avec un schéma déjà codé.
 *
 * @author Eliott
 */
public abstract class Projectile {
    
    /**
    * la direction du projectile
    */
    protected Vector2 direction;
    
    /**
    * la position du projectile
    */
    protected Vector2 position;
    
    /**
    * le sprite du projectile
    */
    protected Sprite sprite;
    
    /**
    * la vitesse du projectile
    */
    protected int vitesse;
    
    /**
    * la force du projectile
    */
    protected int force;
    
    /**
    * la duree de vie du projectile
    */
    protected int dureeVie;
    
    /**
    * le rayon du projectile (= largeur/2)
    */
    protected int rayon;
    
    /**
     * Permet de récupérer la direction du projectile.
     *
     * @return un Vector2 correspondant à la direction du projectile
     */
    public Vector2 getDirection() {
        return this.direction;
    }
    
    /**
     * Déplace le projectile :   met à jour son affichage (via le sprite)
     *                          met à jour sa position en fonction de sa direction
     * et de sa vitesse.
     *
     */
    public void deplacer () {
        if (!direction.isZero()) {
            sprite.nextStep(position,direction.getRotation());
            position.deplacer(this.direction, vitesse);
        } else {
            sprite.setIdle(direction.getRotation());
        }
        dureeVie--;
        if (dureeVie < 0)
            dureeVie = 0;
    }
    
    /**
     * Permet de récupérer l'ImageView correspondant au projectile contenue dans
     * son sprite.
     *
     * @return l'ImageView associée au projectile
     */
    public ImageView getImageView() {
        return sprite.getImageView();
    }
    
    /**
     * Permet de récupérer la position du projectile.
     *
     * @return un Vector2 correspondant à sa position.
     */
    public Vector2 getPosition() {
        return position;
    }
    
    /**
     * Permet de récupérer le rayon du projectile.
     *
     * @return un int correspondant au rayon du projectile.
     */
    public int getRayon() {
        return rayon;
    }
    
    /**
     * Permet de récupérer la force du projectile.
     *
     * @return un int correspondant à la force du projectile.
     */
    public int getForce() {
        return force;
    }
    
    /**
     * Permet de récupérer la durée de vie restante du projectile.
     *
     * @return un int correspondant à la durée de vie restante du projectile.
     */
    public int getDureeVie() {
        return dureeVie;
    }
    
    /**
     * Met la durée de vie du projectile à zéro. Forçant ainsi sa destruction
     * immédiate.
     */
    public void detruire() {
        dureeVie = 0;
    }
}
