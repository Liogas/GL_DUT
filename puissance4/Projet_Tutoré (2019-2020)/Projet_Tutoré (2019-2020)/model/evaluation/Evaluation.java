package model.evaluation;

import model.joueur.*;
import model.grille.*;

/**
 *
 * La classe Evaluation est une classe abstraite permettant aux differentes classes d'evaluation d'heriter de la methode notation.
 * 
 * @author Yanis DE SOUSA ALVES
 */

public abstract class Evaluation {

     /**
     * Constructeur Evaluation.
     * 
     */
     public Evaluation(){}

     /**
     * Methode abstraite permettant de noter la grille de jeu d'un joueur. 
     *
     * @param gr
     *            La grille de jeu.
     * @param j
     *            Le joueur actuel.
     */
     public abstract int notation(Grille gr, Joueur j);
}