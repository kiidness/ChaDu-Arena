/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.Entites;

import javafx.scene.image.Image;

/**
 * Monstre est une classe abstraite héritant d'Entite posant les bases pour la 
 * créations de nouveaux types de monstres attaquant au corps à corps avec un schéma 
 * déjà défini.
 *
 * @author Eliott
 */
public abstract class Monstre extends Entite {
    
    /**
    * La force du monstre (intervient lorsque celui-ci attaque).
    */
    protected int force;
    
    /**
     * Permet de récupérer la force du monstre qui lui permet d'attaquer d'autres entités.
     *
     * @return un int correspondant à la force du monstre
     */
    public int getForce(){
        return force;
    }
}
