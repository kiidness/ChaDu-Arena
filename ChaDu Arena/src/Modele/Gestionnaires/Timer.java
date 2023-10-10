/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.Gestionnaires;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Timer est une classe qui permet de gérer le temps / les temporisations.
 *
 * @author Eliott
 */
public class Timer {
    
    /**
    * Tic par Seconde
    */
    private final double tParS = (1000/83);
    
    /**
    * Tic par Vague
    */
    private final double tParV = (10000/83);
    
    private int nbTics;
    private int nbVague;
    private int compteurTir;
    
    private StringProperty vagues = new SimpleStringProperty();
    private StringProperty secondes = new SimpleStringProperty();
    
    /**
     * Permet de récupérer la StringProperty liée à l'affichage du nombre de vagues.
     *
     * @return la StringProperty vagues
     */
    public StringProperty vaguesProperty() { return vagues; }

    /**
     * Permet de récupérer la StringProperty liée à l'affichage des secondes écoulées.
     *
     * @return la StringProperty secondes
     */
    public StringProperty secondesProperty() { return secondes; }
    
    /**
     * Permet de savoir si le joueur peut tirer.
     *
     * @return un booléen correspondant à la possibilité pour le joueur de tirer ou non.
     */
    public boolean canTir() {
        return compteurTir == 0;
    }
    
    /**
     * Initialise la valeur de compteurTir correspondant à la durée en tic avant
     * que le joueur puisse tirer de nouveau.
     *
     * @param duree un float correspondant à la durée en secondes
     */
    public void setCompteurTir(float duree){
        // transforme la durée de secondes à tic
        compteurTir = (int)(duree*tParS);
    }
    
    /**
     * Permet de récupérer la valeur du nombre de la vague actuelle.
     *
     * @return un int correspondant au nombre de la vague actuelle.
     */
    public int getNbVague() {
        return nbVague;
    }
    
    /**
     * Ajoute un tic à nbTics et modifie toutes les valeurs reliées à sa valeur.
     */
    public void addTic() {
        nbTics++;
        if (compteurTir > 0) {
            compteurTir--;
        }
        if (nbTics%tParS == 0) {
            secondes.set("Secondes : "+(int)(nbTics/tParS));
            if (nbTics%tParV == 0) {
                nbVague = (int)(nbTics/tParV);
                vagues.set("Vague n°"+nbVague);
            }
        }
    }
    
    /**
     * Permet de savoir si on vient de passer à une nouvelle vague pendant ce tic.
     *
     * @return un booléen indiquant s'il s'agit du début d'une nouvelle vague.
     */
    public boolean isNewVague() {
        return nbTics%tParV == 0;
    }
    
    /**
     * Permet de récupérer la valeur du nombre de secondes de jeu.
     *
     * @return un int correspondant au nombre de secondes survécues.
     */
    public int getNbSecondes() {
        return (int)(nbTics/tParS);
    }
    
    /**
     * Constructeur de la classe Timer qui initialise ses variable pour son bon fonctionnement.
     * 
     */
    public Timer(){
        compteurTir=0;
        nbTics=0;
        nbVague=0;
        secondes.set("Secondes : 0");
        vagues.set("Vague n°"+nbVague);
    }
}
