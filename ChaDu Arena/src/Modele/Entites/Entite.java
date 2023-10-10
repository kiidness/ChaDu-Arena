/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.Entites;

import Modele.Sprite;
import Modele.Vector2;
import static java.lang.Math.abs;
import javafx.scene.image.ImageView;

/**
 * Entite est une classe abstraite posant les bases pour la créations de nouveaux
 * types d'entités avec un schéma déjà codé.
 *
 * @author Eliott
 */
public abstract class Entite {
    
    /**
    * La vie de l'entite
    */
    protected int vie;
    
    /**
    * La position de l'entite
    */
    protected Vector2 position;
    
    /**
    * La direction de l'entite
    */
    protected Vector2 direction;
    
    /**
    * Le rayon de l'entite (= largeur/2)
    */
    protected int rayon;
    
    /**
    * La vitesse de l'entite
    */
    protected int vitesse;
    
    /**
    * Le sprite de l'entite qui gère son ImageView affichée.
    */
    protected Sprite sprite;
    
    /**
    * La possibilité pour l'entité de bouger. (= collisions)
    */
    protected boolean canMove;
    
    /**
     * Déplace l'entité :   met à jour son affichage (via le sprite)
     *                      met à jour sa position en fonction de sa direction
     * et de sa vitesse.
     *
     */
    public void deplacer() {
        sprite.nextStep(position,direction.getRotation());
        position.deplacer(this.direction, vitesse);
    }
    
    /**
     * Permet de récupérer l'ImageView associée à l'entité et gérée par son sprite.
     *
     * @return l'ImageView associée à l'entité
     */
    public ImageView getImageView() {
        return sprite.getImageView();
    }
    
    /**
     * Permet de récupérer la valeur de la vitesse de l'entité.
     *
     * @return un int correspondant à sa vitesse.
     */
    public int getVitesse() {
        return vitesse;
    }
    
    /**
     * Permet de récupérer la valeur de la position de l'entité.
     *
     * @return un Vector2 correspondant à sa position.
     */
    public Vector2 getPosition() {
        return position;
    }
    
    /**
     * Décrémente la vie de l'entité lorsqu'elle est attaquée et veille à ce que la
     * valeur de sa vie soit toujours positive ou égale à zéro.
     *
     * @param force la force (int) de l'entité ou du projectile attaquant l'entité.
     */
    public void subirAttaque(int force){
        vie=vie-abs(force);
        if (vie < 0) {
            vie = 0;
        }
    }
    
    /**
     * Permet de récupérer la valeur de la vie de l'entité.
     *
     * @return un int correspondant à la valeur de la vie de l'entité.
     */
    public int getVie() {
        return vie;
    }
    
    /**
     * Permet de savoir si l'entité peut se déplacer ou non.
     *
     * @return un booléen correspondant à la possibilité de l'entité de bouger.
     */
    public boolean getCanMove() {
        return canMove;
    }
    
    /**
     * Permet d'indiquer à l'entité si elle peut bouger ou non en changeant son état.
     *
     * @param canMove la valeur à affecter à canMove.
     */
    public void setCanMove(boolean canMove) {
        this.canMove=canMove;
        if (!canMove) {
            this.sprite.setIdle(direction.getRotation());
        }
    }
    
    /**
     * Permet de récupérer la direction de l'entité.
     *
     * @return un Vector2 correspondant à la direction de l'entité.
     */
    public Vector2 getDirection() {
        return this.direction;
    }
    
    /**
     * Permet de récupérer la valeur du rayon de l'entité.
     *
     * @return un int correspondant à la valeur du rayon de l'entité (= largeur/2)
     */
    public int getRayon() {
        return rayon;
    }

    /**
     * Permet de modifier la direction de l'entité.
     *
     * @param direction la nouvelle direction de l'entité (Vector2).
     */
    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }
    
    
    
}
