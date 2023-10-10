/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.Gestionnaires;

import Modele.Vector2;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;

/**
 * Gère les touches préssées et relachées par l'utilisateur.
 *
 * @author Eliott
 */
public class GestionTouches {
    
    private boolean tirer;
    
    private boolean isMoving;
    private Vector2 direction;
    
    /**
     * Constructeur de la classe GestionTouches initialisant ses valeurs et les
     * Event permettant de changer ses valeurs en fonction des touches pressées.
     *
     * @param btn le bouton auquel sont attachées les EventHandler
     */
    public GestionTouches(Button btn) {
        
        /**
        * L'état du joueur (est-ce qu'il bouge ?)
        */
        isMoving = false;
        
        /**
        * L'état du joueur voulu par l'utilisateur (est-ce qu'il veut tirer ?)
        */
        tirer = false;
        
        /**
        * La direction du joueur indiqué par l'utilisateur.
        */
        direction = new Vector2(0,0);
        
        
        btn.setOnKeyPressed((KeyEvent event) -> {
            switch(event.getCode()){
                case NUMPAD0:
                    tirer = true;
                    break;
                case LEFT:
                    isMoving = true;
                    direction.setXY(-1,0);
                    break;
                case RIGHT:
                    isMoving = true;
                    direction.setXY(1,0);
                    break;
                case UP:
                    isMoving = true;
                    direction.setXY(0,1);
                    break;
                case DOWN:
                    isMoving = true;
                    direction.setXY(0,-1);
                    break;
            }	
        });
        
        btn.setOnKeyReleased((KeyEvent event) -> {
            switch(event.getCode()){
                case NUMPAD0:
                    tirer = false;
                    break;
                case LEFT:
                    if(direction.isLeft())
                        isMoving = false;
                    break;
                case RIGHT:
                    if(direction.isRight())
                        isMoving = false;
                    break;
                    
                case UP:
                    if(direction.isUp())
                        isMoving = false;
                    break;
                case DOWN:
                    if(direction.isDown())
                        isMoving = false;
                    break;
            }	
        });
    }
    
    /**
     * Permet de récupérer la valeur de la direction du joueur.
     *
     * @return le Vector2 correspondant à la direction
     */
    public Vector2 getDirection () {
        return direction;
    }
    
    /**
     * Permet de récupérer l'état du joueur (si il se déplace ou non).
     *
     * @return le booléen correspondant à l'état du joueur. Vrai si il se déplace,
     * faux sinon.
     */
    public boolean getIsMoving () {
        return isMoving;
    }
    
    /**
     * Permet de récupérer l'état du joueur (si il tire ou non).
     *
     * @return un booléen correspondant à l'action de tirer (souhait de la réaliser si true).
     */
    public boolean getTirer () {
        return tirer;
    }
}
