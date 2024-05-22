package model.algorithme;

import model.grille.*;
import model.joueur.*;
import model.*;

import java.util.ArrayList;

public class MonteCarlo extends Algorithme {

	public MonteCarlo(int thinkingt){
		super(thinkingt);
	}

	@Override
	public int[] caseAJouer(Grille grille, Joueur joueur){
		ArrayList<int[]> colonnesJouables = grille.coupsPossibles();
		double thinkingTimePerMove = thinkingTime/colonnesJouables.size(); //Correspond au temps de recherche qu'on laissera à chaque case jouables (ici il est le même pour chaque case)
		double meilleurRatio = -1.0f;
		int[] caseAJouer = new int[2];

		for(int[] colonneJouable : colonnesJouables){
			double victoire = 0;
			double défaite = 0;
			int début = (int)System.currentTimeMillis();
			int tempsEcoulé = 0;
			int nbrSimulations = 0;

			while(tempsEcoulé < thinkingTimePerMove){
				Grille copieGrille = grille.copier();
				copieGrille.inserer(colonneJouable[0], colonneJouable[1], joueur);

				if(copieGrille.qqunAgagne() == joueur.getCouleur()){
					return colonneJouable;
				}

				Couleur vainqueur = copieGrille.simuler(grille.getPartie().joueurSuivant());

				if(vainqueur == Couleur.ROUGE || vainqueur == Couleur.JAUNE){ //si la partie n'est pas une égalité
					if(vainqueur == joueur.getCouleur()){
						victoire++;
					} else {
						défaite++;
					}
				}

				tempsEcoulé = (int)System.currentTimeMillis()-début;
				nbrSimulations++;
			}

			double ratio = victoire/(victoire+défaite);

			//System.out.println("Ratio de victoires pour ("+nbrSimulations+" simulations) sur "+colonneJouable[0]+","+colonneJouable[1]+" : "+ratio+" %");
			if(ratio >= meilleurRatio){
				meilleurRatio = ratio;
				caseAJouer = colonneJouable;
			}
		}
		return caseAJouer;
	}
}