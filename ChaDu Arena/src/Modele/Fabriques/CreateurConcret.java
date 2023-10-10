/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.Fabriques;

import Modele.Entites.Joueur;
import Modele.Entites.ChaTank;
import Modele.Entites.Chaton;
import Modele.Entites.Entite;
import Modele.Projectiles.Pique;
import Modele.Projectiles.Projectile;
import Modele.Vector2;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import launcher.Main;

/**
 * Cette classe CreateurConcret héritant de Createur permet d'instancier des entités
 * et projectiles.
 *
 * @author Eliott
 */
public class CreateurConcret extends Createur {

    //Cf Createur.java
    @Override
    public LinkedList<Entite> fabriqueEntitesMonstres(int nbVague, StackPane arenePane) {
        LinkedList<Entite> listeEntites = new LinkedList();
        Entite monstre;
        int nb;
        
        if (nbVague < monstresParVague.size())
            nb = nbVague;
        else
            nb = monstresParVague.size()-1;
        
        Iterator iterateur = monstresParVague.get(nb).entrySet().iterator();
        
        while (iterateur.hasNext()) {
                
            Map.Entry couple = (Map.Entry)iterateur.next();
            for (int i = 0; i < (Integer)couple.getValue(); i++) {
                try {
                        
                    if (!((Entite.class).isAssignableFrom((Class)couple.getKey()))) {
                        throw new Exception("CreateurConcret, fabriqueEntiteMonstres : la classe indiquée n'est pas une fille de la classe Entite");
                    }
                        
                    Image img = imagesEntites.get((Class)couple.getKey());
                        
                    // Constructeur d'entités monstres & informations sur le type de ses paramètres
                    Class[] parameterTypes = new Class[]{Vector2.class,Vector2.class,Image.class};
                    Constructor constructeur;
                    constructeur = ((Class)couple.getKey()).getConstructor(parameterTypes);
                        
                    Vector2 position;
                        
                    if (random.nextInt(2)==0) { // Si sur X
                        if (random.nextInt(2)==0) { // si sur X en haut
                            position = new Vector2(random.nextInt(1280)-640,360);
                        } else { // si sur X en bas
                            position = new Vector2(random.nextInt(1280)-640,-360);
                        }
                    } else { // si sur Y
                        if (random.nextInt(2)==0) { // si sur Y à gauche
                            position = new Vector2(-640,random.nextInt(720)-360);
                        } else { // Si sur Y à droite
                            position = new Vector2(640,random.nextInt(720)-360);
                        }
                    }
                        
                    monstre = (Entite) constructeur.newInstance(position,new Vector2(0,0),img);
                        
                    listeEntites.add(monstre);
                    arenePane.getChildren().add(monstre.getImageView());
                    
                } catch (Exception e) {
                    Main.fenetreErreur(e);
                }
                    
            }
        }
        
        return listeEntites;
    }

    //Cf Createur.java
    @Override
    public Entite fabriquePerso(StackPane arenePane) {
        Image img = new Image("sources/img/joueur/SpritePerso.png");     
        Entite joueur = new Joueur(new Vector2(0,0),new Vector2(1,0),img);
        arenePane.getChildren().add(joueur.getImageView());
        return joueur;
    }
    
    
    //Cf Createur.java
    @Override
    public Projectile fabriqueProjectile(Class type, Entite lanceur, StackPane arenePane) {
        try {
            if (!((Projectile.class).isAssignableFrom(type))) {
                throw new Exception("CreateurConcret, fabriqueEntiteMonstres : la classe indiquée n'est pas une fille de la classe Entite");
            }
            
            Image img = imagesEntites.get(type);
            
            Projectile projectile;
            Class[] parameterTypes = new Class[]{Vector2.class,Vector2.class,Image.class};
            Constructor constructeur;
            constructeur = type.getConstructor(parameterTypes);

            projectile =(Projectile)constructeur.newInstance(lanceur.getPosition(),lanceur.getDirection(),img);
            arenePane.getChildren().add(projectile.getImageView());
            
            return projectile;
            
        } catch (Exception e) {
            Main.fenetreErreur(e);
        }
        return null;
    }
    
    /**
     * Constructeur de la classe CreateurConcret.
     * 
     * Initialise les variables nécessaires à son fonctionnement ainsi que le
     * contenu des différentes vagues.
     * 
     */
    public CreateurConcret(){
        imagesEntites = new HashMap();
        monstresParVague = new LinkedList();
        random = new Random();
        //images (uniques & réutilisées lors de la création des monstres)
        imagesEntites.put(Chaton.class,new Image("sources/img/monstres/SpriteChaton.png"));
        imagesEntites.put(ChaTank.class,new Image("sources/img/monstres/SpriteChatonBrun.png"));
        
        
        imagesEntites.put(Pique.class,new Image("sources/img/projectiles/fleche.png"));
        
        // Création du contenu des vagues
        HashMap<Class,Integer> map = new HashMap();
        map.put(Chaton.class, 1);
        monstresParVague.add(map);
        
        map = new HashMap();
        map.put(Chaton.class, 2);
        monstresParVague.add(map);
        
        map = new HashMap();
        map.put(ChaTank.class, 1);
        map.put(Chaton.class, 1);
        monstresParVague.add(map);
        
        map = new HashMap();
        map.put(Chaton.class, 3);
        monstresParVague.add(map);
        
        map = new HashMap();
        map.put(ChaTank.class, 2);
        monstresParVague.add(map);
        
        map = new HashMap();
        map.put(Chaton.class, 3);
        monstresParVague.add(map);
        
        map = new HashMap();
        map.put(ChaTank.class, 2);
        map.put(Chaton.class, 2);
        monstresParVague.add(map);
        
        map = new HashMap();
        map.put(Chaton.class, 1);
        monstresParVague.add(map);
        
        map = new HashMap();
        map.put(Chaton.class, 3);
        monstresParVague.add(map);
        
    }
    
}
