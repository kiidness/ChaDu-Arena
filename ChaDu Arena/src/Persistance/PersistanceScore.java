/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistance;

import Modele.Score;
import java.util.LinkedList;

/**
 * PersistanceScore est une classe abstraite posant un schéma à respecter pour
 * la création de classes concrètes permettant la persistance du score.
 *
 * @author Eliott
 */
public abstract class PersistanceScore {

    /**
     * Permet de charger l'ensemble des scores enregistrée dans un fichier.
     *
     * @return la liste des scores chargée
     * @throws Exception lorsque le chargement du fichier à échoué
     */
    public abstract LinkedList<Score> chargerScores() throws Exception;

    /**
     * Permet de sauvegarder l'ensemble des scores dans un fichier.
     *
     * @param scores La liste des scores à sauvegarder
     * @throws Exception lorsque la sauvegarde du fichier à échoué
     */
    public abstract void sauvegarderScores(LinkedList<Score> scores) throws Exception;
}
