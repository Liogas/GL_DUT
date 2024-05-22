package controller.jeu;

import model.grille.*;
import model.joueur.*;
import model.algorithme.*;
import model.evaluation.*;
import model.*;
import view.*;

import javax.swing.*;

/**
 * Jeu simulation utilisé pour faire des statistiques
 */
public class JeuSimulation extends Jeu{

	private Partie jeu;
	private Joueur[] joueurs = new Joueur[2];
	private Grille grille;

	public JeuSimulation(){
		super();
	}

	@Override
	public void lancerPartie(boolean ia){}

	@Override
	public void lancerPartieIA(JoueurIA j1, JoueurIA j2){
		joueurs[0] = j1;
		joueurs[1] = j2;
		jeu = new Partie(joueurs);
		jeu.setController(this);
		grille = new Grille(jeu);
		jeu.setGrille(grille);
		jeu.nouveauTour();
	}

	@Override
	public int jouer(int c, int p){
		int i;
		if((i = grille.inserer(c,p,jeu.getJoueurActuel())) == 0){
			return 0;
		} else {
			Couleur etatPartie = grille.qqunAgagne();
			
			if(etatPartie == null){
				jeu.nouveauTour();			
				return 1;
			} else {
				return 2;
			}
		}
	}

	public Partie getPartie(){
		return jeu;	
	}
}