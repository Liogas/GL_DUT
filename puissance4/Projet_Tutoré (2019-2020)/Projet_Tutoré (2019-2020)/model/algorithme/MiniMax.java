package model.algorithme;

import model.evaluation.*;
import model.grille.*;
import model.joueur.*;
import model.*;

public class MiniMax extends Algorithme {

	public MiniMax(Evaluation e, int profondeur){
		super(e,profondeur);
	}

	@Override
	public int[] caseAJouer(Grille grille, Joueur joueur){
		int[] caseJouer = new int[2];
		int noteDuJeu = Integer.MIN_VALUE;

		for(int profondeur = 0; profondeur < grille.getNbrProfondeur(); profondeur++){
			for(int colonne = 0; colonne < grille.getNbrColonne(); colonne++){
				if(!grille.verifColonne(colonne, profondeur)){
					Grille grilleCopie = grille.copier();
					grilleCopie.inserer(colonne, profondeur, joueur);

					int noteDuJeuActuel = miniMax(grilleCopie, joueur, profondeurRecherche);

					if(noteDuJeuActuel >= noteDuJeu){
						noteDuJeu = noteDuJeuActuel;
						caseJouer[0] = colonne;
						caseJouer[1] = profondeur;
					}
				}
			}
		}
		//System.out.println("L'IA a choisit la case : "+caseAJouer.getLigne()+" "+caseAJouer.getColonne()+" "+caseAJouer.getProfondeur());
		return caseJouer;
	}

	/* Fonction récursive miniMax qui calcule la note du jeu en essayant tous les coups possibles en alternant entre le joueur (MAX) et son opposant (MIN). */
	private int miniMax(Grille grille, Joueur joueur, int profondeur){
		//System.out.println("Lancement de MINIMAX");
		return this.min(grille, joueur, profondeur);
	}

	private int min(Grille grille, Joueur joueur, int prof){
		if(prof != 0){
			int noteDuJeu = Integer.MAX_VALUE;

			for(int profondeur = 0; profondeur < grille.getNbrProfondeur(); profondeur++){
				for(int colonne = 0; colonne < grille.getNbrColonne(); colonne++){
					if(!grille.verifColonne(colonne, profondeur)){
						Grille grilleCopie = grille.copier();
						grilleCopie.inserer(colonne, profondeur, grilleCopie.getPartie().joueurSuivant());

						noteDuJeu = Math.min(noteDuJeu, this.max(grilleCopie, joueur, prof-1));
					}
				}
			}
			return 0;
		} else {
			return grille.evaluer(evaluation, joueur);
		}
	}

	private int max(Grille grille, Joueur joueur, int prof){
		if(prof != 0){
			int noteDuJeu = Integer.MAX_VALUE;

			for(int profondeur = 0; profondeur < grille.getNbrProfondeur(); profondeur++){
				for(int colonne = 0; colonne < grille.getNbrColonne(); colonne++){
					if(!grille.verifColonne(colonne, profondeur)){
						Grille grilleCopie = grille.copier();
						grilleCopie.inserer(colonne, profondeur, joueur);

						noteDuJeu = Math.max(noteDuJeu, this.min(grilleCopie, joueur, prof-1));
					}
				}
			}
			return 0;

		} else {
			return grille.evaluer(evaluation, joueur);
		}
	}	
}