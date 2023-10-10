/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.Gestionnaires;

import Modele.Entites.Entite;
import Modele.Entites.Monstre;
import Modele.Projectiles.Projectile;
import java.util.LinkedList;

/**
 * Cette classe abstraite GestionMouvement gère les mouvements des entités et projectiles.
 *
 * @author Eliott
 */
public abstract class GestionMouvement {

    /**
     * Calcule les déplacements des entités et projectiles et les collisions qui en résultent.
     *
     * @param joueur l'Entité du joueur alors contrôlé par gestionTouches
     * @param monstres l'ensemble d'Entités instantiées correspondant aux monstres attaquants
     * @param projectiles l'ensemble des projectiles instantiés
     * @param gestionTouches le GestionTouches contenant la direction donnée par le joueur
     * pour l'Entité joueur.
     */
    public static void calculer (Entite joueur, LinkedList<Entite> monstres,LinkedList<Projectile> projectiles,GestionTouches gestionTouches) {
        Entite monstre; // = monstre calculé dans la boucle for
        Entite monstre2; // = pour les autres monstres autour du monstre calculé
        Projectile projectile; // projectile calculé dans la boucle for
        
        /****************** Gestion mouvement du joueur ******************/
        if (gestionTouches.getIsMoving()) {
            joueur.setDirection(gestionTouches.getDirection());
            joueur.setCanMove(true);
            
                for (int j = 0; j<monstres.size(); j++) {
                    monstre2 = monstres.get(j);
                    if (joueur.getPosition().distance(monstre2.getPosition()) < (joueur.getRayon()+monstre2.getRayon()-10) ) {
                        // Distance de collision diminuée de 10 pour que le joueur puisse se défaire un minimum lorsqu'à moitié entouré
                        if (joueur.getDirection().isUp() && joueur.getPosition().getY() < monstre2.getPosition().getY())
                           joueur.setCanMove(false);
                        else if (joueur.getDirection().isDown() && joueur.getPosition().getY() > monstre2.getPosition().getY())
                            joueur.setCanMove(false);
                        else if (joueur.getDirection().isLeft() && joueur.getPosition().getX() > monstre2.getPosition().getX())
                            joueur.setCanMove(false);
                        else if (joueur.getDirection().isRight() && joueur.getPosition().getX() < monstre2.getPosition().getX())
                            joueur.setCanMove(false);
                    }
                }
        } else {
            joueur.setCanMove(false);
        }
        
        
        /****************** Gestion mouvement des monstres ******************/
        for (int i = 0; i<monstres.size(); i++) {
            monstre = monstres.get(i);
                
            if (joueur.getPosition().distance(monstre.getPosition()) > (joueur.getRayon()+ monstre.getRayon())) {
                monstre.setDirection(monstre.getPosition().calculerDirection(joueur.getPosition()));
                monstre.setCanMove(true);
                    
                for (int j = 0; j<monstres.size(); j++) {
                    
                    if (j!=i) {
                        monstre2 = monstres.get(j);
                        
                        if (monstre.getPosition().distance(monstre2.getPosition()) < (monstre.getRayon()+monstre2.getRayon())) {
                            if (monstre.getDirection().isUp() && monstre.getPosition().getY() < monstre2.getPosition().getY())
                                monstre.setCanMove(false);
                            else if (monstre.getDirection().isDown() && monstre.getPosition().getY() > monstre2.getPosition().getY())
                                monstre.setCanMove(false);
                            else if (monstre.getDirection().isLeft() && monstre.getPosition().getX() > monstre2.getPosition().getX())
                                monstre.setCanMove(false);
                            else if (monstre.getDirection().isRight() && monstre.getPosition().getX() < monstre2.getPosition().getX())
                                monstre.setCanMove(false);
                        }
                    }
                }
            } else {
                joueur.subirAttaque(((Monstre)monstre).getForce());
                monstre.setCanMove(false);
            }
        }
        
        
        /****************** Gestion des colisions des projectiles ******************/
        for (int i = 0; i<projectiles.size(); i++) {
            projectile = projectiles.get(i);
            for (int j = 0; j<monstres.size(); j++) {
                monstre = monstres.get(j);
                if (projectile.getPosition().distance(monstre.getPosition()) < (projectile.getRayon()+ monstre.getRayon())) {
                    monstre.subirAttaque(projectile.getForce());
                    projectile.detruire();
                }
            }
        }
    }
    
    /**
     * Applique les déplacements des entités et projectiles calculés.
     *
     * @param joueur l'Entité du joueur alors contrôlé par gestionTouches
     * @param monstres l'ensemble d'Entités instantiées correspondant aux monstres attaquants
     * @param projectiles l'ensemble des projectiles instantiés
     */
    public static void deplacer (Entite joueur, LinkedList<Entite> monstres,LinkedList<Projectile> projectiles) {
        
        if (joueur.getCanMove()) {
            joueur.deplacer();
        }
        
        for (int i = 0; i<monstres.size(); i++) {
            Entite monstre = monstres.get(i);
            if (monstre.getCanMove()) {
                monstre.deplacer();
            }
        } 
        for (int i = 0; i<projectiles.size(); i++) {
            projectiles.get(i).deplacer();
        }
    }
}
