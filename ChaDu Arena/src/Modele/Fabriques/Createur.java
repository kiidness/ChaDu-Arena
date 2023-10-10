/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.Fabriques;

import Modele.Entites.Entite;
import Modele.Projectiles.Projectile;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

/**
 * Createur est une classe abstraite posant le schéma de base pour la création
 * de créateurs concrets.
 *
 * @author Eliott
 */
public abstract class Createur {
    /**
    * Les images des entités liées à une classe donnée, permet d'utiliser plusieurs fois
    * une même Image pour générer plusieurs entités d'un même type.
    */
    protected Map<Class,Image> imagesEntites;
    
    /**
    * Le nombre d'entités de classes données par vague.
    */
    protected LinkedList<Map<Class,Integer>> monstresParVague;
    
    /**
    * Le générateur de nombres randoms pour le spawn des monstres.
    */
    protected Random random;
    
    /**
     * Schéma de la méthode permettant de fabriquer un ensemble d'Entités monstres 
     * (attaquant le joueur) le résultat est différent selon l'avancement du jeu (nbVague).
     *
     * @param nbVague un int correspondant à l'avancement du jeu.
     * @param arenePane le StackPane ou l'ImageView de chaque Entité monstre créé
     * est ajoutée afin de l'afficher.
     * @return l'ensemble d'entités monstres créée.
     */
    public abstract LinkedList<Entite> fabriqueEntitesMonstres(int nbVague, StackPane arenePane);

    /**
     * Schéma de la méthode permettant de créer l'entité correspondant au joueur / personnage joué.
     * 
     * @param arenePane le StackPane ou l'ImageView de l'Entité créée est ajoutée 
     * afin de l'afficher.
     * @return l'Entite créée.
     */
    public abstract Entite fabriquePerso(StackPane arenePane);

    /**
     * Schéma de la méthode permettant d'instantier un Projectile de type donné.
     *
     * @param type le type / la Classe du projectile à créer
     * @param origine l'Entite correspondant au lanceur du projectile. 
     * @param arenePane le StackPane ou l'ImageView du Projectile créé est ajoutée 
     * afin de l'afficher.
     * @return le Projectile créé.
     */
    public abstract Projectile fabriqueProjectile(Class type, Entite origine, StackPane arenePane);
}
