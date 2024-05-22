package model.joueur;

import model.algorithme.*;
import model.evaluation.*;
import model.grille.*;
import model.*;

public class JoueurIA extends Joueur{

	public JoueurIA(Couleur c, Algorithme a){
		super(c,a);
	}

	public Algorithme getAlgorithme(){
		return algorithme;
	}

	public int[] jouer(Grille g){
		return algorithme.caseAJouer(g, this);
	}
}