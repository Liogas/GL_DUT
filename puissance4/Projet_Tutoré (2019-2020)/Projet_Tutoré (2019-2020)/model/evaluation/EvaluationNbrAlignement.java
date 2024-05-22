package model.evaluation;

import model.grille.*;
import model.joueur.*;
import model.*;

/**
 *
 * La classe EvaluationNbrAlignement herite de la classe Evaluation.
 * 
 * 
 * @author Yanis DE SOUSA ALVES
 */

public class EvaluationNbrAlignement extends Evaluation {

	/**
     * Constructeur EvaluationNbrAlignement.
     * Pour une grille de jeu donne, elle attribue des points en fonctions des differents alignements de jetons.
     *
     */
	public EvaluationNbrAlignement(){

	}

	/**
     * 
     * @return le score total de la grille de jeu, sous la forme d'un int 
     *
     * @param gr
     *            La grille de jeu.
     * @param j
     *            Le joueur actuel.
     */
	@Override
	public int notation(Grille gr, Joueur j){
		Couleur gagnant = null;

		if((gagnant = gr.qqunAgagne()) != null){
			if(gagnant == j.getCouleur()){
				return Integer.MAX_VALUE;
			} else {
				return Integer.MIN_VALUE;
			}
		}

		int score2alignement = gr.nombreAlignementPion(2, j.getCouleur());
		int score3alignement = 10 * gr.nombreAlignementPion(3, j.getCouleur());
		int score4alignement = 1000 * gr.nombreAlignementPion(4, j.getCouleur());

		return score2alignement+score3alignement+score4alignement;
	}
}