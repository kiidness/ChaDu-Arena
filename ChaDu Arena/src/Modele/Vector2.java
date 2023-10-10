/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.atan2;
import static java.lang.Math.sqrt;

/**
 * Vector2 est la classe utilisée pour gérer les 2 dimensions utilisées dans le jeu.
 * Elle intervient autant en tant que position qu'en tant que direction (qui peut être
 * transformée en rotation).
 *
 * @author Eliott
 */
public class Vector2 {
    /**
    * La coordonnée x
    */
    private int x;
    
    /**
    * La coordonnée y
    */
    private int y;
    
    /**
     * Constructeur de la classe Vector2 en spécifiant les valeurs de départ.
     *
     * @param x la valeur x
     * @param y la valeur y
     */
    public Vector2 (int x, int y) {
        this.x=x;
        this.y=y;
    }
    
    /**
     * Calcule la distance entre ce Vector2 et un autre passé en argument.
     * ces Vector2 correspondent ici à des positions dans un plan 2D.
     *
     * @param position la position du seconde objet 2D
     * @return un int correspondant à la distance entre les deux objets 2D
     */
    public int distance (Vector2 position) {
        double x = position.getX()-this.x;
        double y = position.getY()-this.y;
        return (int) sqrt(x*x+y*y);
    }
    
    /**
     * Crée une copie du Vector2.
     *
     * @return un nouveau Vector2 copie du Vecteur2
     */
    public Vector2 copy () {
        return new Vector2(this.getX(),this.getY());
    }
    
    /**
     * Déplace un objet 2D en modifiant sa position
     *
     * @param direction la direction dans laquelle l'objet 2D se déplace
     * @param vitesse la vitesse de déplacement ajoutée dans la direction donnée
     */
    public void deplacer (Vector2 direction, int vitesse) {
        this.x += direction.x * vitesse;
        this.y += direction.y * vitesse;
    }
    
    /**
     * Modifie les valeurs x et y du Vector2.
     *
     * @param x la nouvelle valeur de x
     * @param y la nouvelle valeur de y
     */
    public void setXY (int x, int y) {
        this.x=x;
        this.y=y;
    }
    
    /**
     * Modifie la valeur de x du Vector2
     *
     * @param x la nouvelle valeur de x
     */
    public void setX (int x) {
        this.x=x;
    }
    
    /**
     * Renvoie la valeur de x.
     *
     * @return la valeur de x
     */
    public int getX () {
        return x;
    }
    
    /**
     * Modifie la valeur de y du Vector2.
     *
     * @param y la nouvelle valeur de y
     */
    public void setY (int y) {
        this.y=y;
    }
    
    /**
     * Renvoie la valeur de y
     *
     * @return la valeur de y
     */
    public int getY () {
        return y;
    }
    
    /**
     * Calcule la rotation à partir d'une direction (Vector2)
     *
     * @return la rotation en degrés
     */
    public int getRotation () {
        return (int) (atan2(x,y)*180/PI);
    }
    
    /**
     * Calcule une direction dans le but d'atteindre la position indiquée en paramètre.
     *
     * @param objectif position de l'objet 2D à atteindre
     * @return un Vector2 correspondant à la direction calculée
     */
    public Vector2 calculerDirection (Vector2 objectif) {
        int x = objectif.getX()-this.x;
        int y = objectif.getY()-this.y;
        
        if (x > 0 && x >= abs(y)) {
            return new Vector2(1,0);
        } else if (x < 0 && abs(x) >= abs(y)) {
            return new Vector2(-1,0);
        } else if (y > 0 && abs(x) < abs(y)) {
            return new Vector2(0,1);
        }
        return new Vector2(0,-1);
    }
    
    /**
     * Permet de savoir si le Vector2 est égal au vecteur zéro.
     *
     * @return un booléen. Vrai si le Vector2 est égal au vecteur zéro faux sinon.
     */
    public boolean isZero () {
        return y==0 && x==0;
    }
   
    /**
     * Permet de savoir si la direction est vers le haut.
     *
     * @return un booléen. Vrai si le vecteur correspond à la direction "haut" faux sinon.
     */
    public boolean isUp () {
        return x==0 && y==1;
    }
    
    /**
     * Permet de savoir si la direction est vers la gauche.
     *
     * @return un booléen. Vrai si le vecteur correspond à la direction "gauche" faux sinon.
     */
    public boolean isLeft () {
        return x==-1 && y==0;
    }
    
    /**
     * Permet de savoir si la direction est vers la droite.
     *
     * @return un booléen. Vrai si le vecteur correspond à la direction "droite" faux sinon.
     */
    public boolean isRight () {
        return x==1 && y==0;
    }
    
    /**
     * Permet de savoir si la direction est vers le bas.
     *
     * @return un booléen. Vrai si le vecteur correspond à la direction "bas" faux sinon.
     */
    public boolean isDown () {
        return x==0 && y==-1;
    }
    
}
