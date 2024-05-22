package controller.jeu;

import model.*;
import model.reseau.*;
import model.joueur.*;
import view.*;

public abstract class Jeu{

	protected AffichageGraphique aGraphique;
	protected Client client;
	protected Partie partie;

	public Jeu(){}

	public Jeu(AffichageGraphique a){
		aGraphique = a;
	}

	public Jeu(AffichageGraphique a, Client c){
		aGraphique = a;
		client = c;
	}

	public abstract void lancerPartie(boolean ia);
	public abstract void lancerPartieIA(JoueurIA j1, JoueurIA j2);

	public abstract int jouer(int c, int p);

	public Partie getPartie(){
		return partie;
	}
}