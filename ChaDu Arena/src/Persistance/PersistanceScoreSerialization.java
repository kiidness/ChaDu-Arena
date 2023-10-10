/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistance;

import Modele.Score;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

/**
 * PersistanceScoreSerialization est une classe héritant de PersitanceScore.
 * Elle permet de gérer le chargement/la sauvegarde en déserializant/serializant 
 * un ensemble de scores en fichier binaire (nomFichier).
 *
 * @author Eliott
 */
public class PersistanceScoreSerialization extends PersistanceScore {
    /**
    * le nom du fichier de sauvegarde
    */
    private static final String NOM_FICHIER = "save.data";
    
    // Cf PersistanceScore.java
    @Override
    public LinkedList<Score> chargerScores() throws Exception {
        File f = new File(NOM_FICHIER);
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        LinkedList<Score> liste = (LinkedList<Score>)ois.readObject();
        ois.close();
        fis.close();
        return liste;
    }

    // Cf PersistanceScore.java
    @Override
    public void sauvegarderScores(LinkedList<Score> scores) throws Exception {
        File f = new File(NOM_FICHIER);
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos); 
        oos.writeObject(scores);
        oos.close();
        fos.close();
    }
    
}
