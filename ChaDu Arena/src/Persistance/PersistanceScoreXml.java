/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistance;

import Modele.Score;
import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedList;

/**
  PersistanceScoreXml est une classe héritant de PersitanceScore.
 * Elle permet de gérer le chargement/la sauvegarde d'un ensemble
 * de scores en fichier XML (nomFichier).
 *
 * @author Eliott
 */
public class PersistanceScoreXml extends PersistanceScore {
    /**
    * le nom du fichier de sauvegarde
    */
    private static final String NOM_FICHIER = "save.xml";
    
    // Cf PersistanceScore.java
    @Override
    public LinkedList<Score> chargerScores() throws Exception {
        LinkedList<Score> liste;
        
        FileInputStream fis = new FileInputStream(NOM_FICHIER);
        BufferedInputStream bis = new BufferedInputStream(fis);
        XMLDecoder decodeur = new XMLDecoder(bis);
        liste = (LinkedList<Score>)decodeur.readObject();
        decodeur.close();
        bis.close();
        fis.close();
        return liste;
    }

    // Cf PersistanceScore.java
    @Override
    public void sauvegarderScores(LinkedList<Score> scores) throws Exception {
        FileOutputStream fos = new FileOutputStream(NOM_FICHIER);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        XMLEncoder encodeur = new XMLEncoder(bos);
        encodeur.writeObject(scores);
        encodeur.close();
        bos.close();
        fos.close();
    }
    
}
