/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Score est la classe utilisée pour gérer et enregistrer le couple score / pseudo joueur,
 * elle peut être sauvegardée via sérialization ou XML dans des linkedList grâce
 * aux classes héritant de PersistanceScore.java.
 * 
 * 
 * @author Eliott
 */
public class Score implements Serializable {
        
    /**
    * La valeur du score (= nombre de secondes survécues).
    */
    private int score;
    /**
    * Le pseudo du détenteur du score.
    */
    private String pseudo;

    /**
     * Renvoie l'int correspondant à la valeur du score
     * 
     * @return  le score (= nombre de secondes survécues enregistré)
     */
    public int getScore() {
            return score;
        }
        
    /**
     * Modifie la valeur du score
     * 
     * @param score la nouvelle valeur de this.score
     */
    public void setScore(int score) {
            this.score = score;
        }

    /**
     * Renvoie le String correspondant au pseudo du détenteur du score
     *
     * @return la valeur de pseudo
     */
    public String getPseudo() {
            return pseudo;
        }

    /**
     * Modifie la valeur de pseudo
     *
     * @param pseudo la nouvelle valeur de this.pseudo
     */
    public void setPseudo(String pseudo) {
            this.pseudo = pseudo;
        }

    /**
     * constructeur de la Classe
     * est nécessaire pour la persistance en XML
     *
     */
    public Score(){
        }
        
    /**
     * Constructeur de la Classe spécifiant la valeur du score et le pseudo du
     * détenteur de celui-ci
     *
     * @param pseudo le pseudo du détenteur du score
     * @param score la valeur du score (= secondes survécues)
     */
    public Score(String pseudo, int score) {
		this.pseudo=pseudo;
                this.score=score;
	}
    
    @Override
    public String toString() {
        return pseudo+" ( "+score+" seconde"+ (score > 1 ? "s" : "") +" )";
    }

}
