package model.joueur;

import model.*;
import model.algorithme.*;
import model.evaluation.*;
/**
 * Joueur
 */
public abstract class Joueur {

	protected Couleur couleur;
	protected Algorithme algorithme;

	public Joueur(Couleur color){
		couleur = color;
	}

	public Joueur(Couleur color, Algorithme alg){
		couleur = color;
		algorithme = alg;
	}

	public Couleur getCouleur(){
		return couleur;
	}
}