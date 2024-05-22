package model.algorithme;

import model.evaluation.*;
import model.grille.*;
import model.joueur.*;
import model.*;

import java.util.ArrayList;

public class AlphaBeta extends Algorithme {

	public AlphaBeta(Evaluation e, int profondeur){
		super(e,profondeur);
	}

	@Override
	public int[] caseAJouer(Grille grille, Joueur joueur){
		int[] caseJouer = new int[2];
		int noteDuJeu = Integer.MIN_VALUE;
		ArrayList<int[]> casesJouables = new ArrayList<int[]>();

		for(int profondeur = 0; profondeur < grille.getNbrProfondeur(); profondeur++){
			for(int colonne = 0; colonne < grille.getNbrColonne(); colonne++){
				if(!grille.verifColonne(colonne, profondeur)){
					Grille grilleCopie = grille.copier();
					grilleCopie.inserer(colonne, profondeur, joueur);

					int noteDuJeuActuel = alphaBeta(grilleCopie, joueur, profondeurRecherche);

					if(noteDuJeuActuel > noteDuJeu){
						casesJouables.clear();
						noteDuJeu = noteDuJeuActuel;
						caseJouer[0] = colonne;
						caseJouer[1] = profondeur;
					}
					if(noteDuJeuActuel == noteDuJeu){
						int[] c = {colonne, profondeur};
						casesJouables.add(c);
					}
				}
			}
		}
		if(casesJouables.size() != 0){
			int random = (int)(Math.random()*casesJouables.size());
			return casesJouables.get(random);
		} else {
			return caseJouer;
		}
	}

	private int alphaBeta(Grille grille, Joueur joueur, int prof){
		int alpha = Integer.MIN_VALUE;
		int beta= Integer.MAX_VALUE;

		return this.min(grille, joueur, prof, alpha, beta);
	}

	private int min(Grille grille, Joueur joueur,  int prof, int alpha, int beta){
		if(prof != 0){
			int noteDuJeu = Integer.MAX_VALUE;

			for(int profondeur = 0; profondeur < grille.getNbrProfondeur(); profondeur++){
				for(int colonne = 0; colonne < grille.getNbrColonne(); colonne++){
					if(!grille.verifColonne(colonne, profondeur)){
						Grille grilleCopie = grille.copier();
						grilleCopie.inserer(colonne, profondeur, grilleCopie.getPartie().joueurSuivant());

						noteDuJeu = Math.min(noteDuJeu, this.max(grilleCopie, joueur, prof-1, alpha, beta));
						
						if(alpha >= noteDuJeu){
							return noteDuJeu;
						}
		               	beta = Math.min(beta, noteDuJeu);
					}
				}
			}
			return noteDuJeu;
		} else {
			return grille.evaluer(evaluation, joueur);
		}
	}

	private int max(Grille grille, Joueur joueur,  int prof, int alpha, int beta){
		if(prof != 0){
			int noteDuJeu = Integer.MIN_VALUE;

			for(int profondeur = 0; profondeur < grille.getNbrProfondeur(); profondeur++){
				for(int colonne = 0; colonne < grille.getNbrColonne(); colonne++){
					if(!grille.verifColonne(colonne, profondeur)){
						Grille grilleCopie = grille.copier();
						grilleCopie.inserer(colonne, profondeur, joueur);

						noteDuJeu = Math.max(noteDuJeu, this.min(grilleCopie, joueur, prof-1, alpha, beta));
						
						if(noteDuJeu >= beta){
							return noteDuJeu;
						}
		               	alpha = Math.max(alpha, noteDuJeu);
					}
				}
			}
			return noteDuJeu;
		} else {
			return grille.evaluer(evaluation, joueur);
		}
	}
}