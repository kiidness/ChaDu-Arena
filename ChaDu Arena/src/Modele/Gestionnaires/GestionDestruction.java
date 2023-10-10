/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.Gestionnaires;

import Modele.Entites.Entite;
import Modele.Projectiles.Projectile;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.layout.StackPane;

/**
 * Cette classe abstraite GestionDestruction gère la destruction des Entités et Projectiles sans vie.
 *
 * @author Eliott
 */
public abstract class GestionDestruction {

    /**
     * Enlève de la liste associé chaque entité ou projectile considéré comme mort.
     * Leur ImageView est également retirée du StackPane afin de les désafficher.
     *
     * @param monstres la liste des monstres à gérer
     * @param projectiles la liste des projectiles à gérer
     * @param arenePane le conteneur des ImagesView permettant l'affichage de celles-ci
     */
    public static void detruire (LinkedList<Entite> monstres,LinkedList<Projectile> projectiles, StackPane arenePane) {
        Entite monstre;
        Projectile projectile;
        
        for (int i = 0; i<monstres.size(); i++) {
            monstre = monstres.get(i);
            if (monstre.getVie() == 0) {
                arenePane.getChildren().remove(monstre.getImageView());
                monstres.remove(monstre);
            }
        }
        
        for (int i = 0; i<projectiles.size(); i++) {
            projectile = projectiles.get(i);
            if (projectile.getDureeVie() == 0) {
                arenePane.getChildren().remove(projectile.getImageView());
                projectiles.remove(projectile);
            }
        }
    }
}
