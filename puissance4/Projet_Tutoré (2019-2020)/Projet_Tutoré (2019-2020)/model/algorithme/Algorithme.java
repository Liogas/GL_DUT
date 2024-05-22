package model.algorithme;

import model.evaluation.*;
import model.grille.*;
import model.joueur.*;

public abstract class Algorithme {
	protected int profondeurRecherche;
	protected Evaluation evaluation;
	protected int thinkingTime;

	//Utilisé pour minmax et alphabeta
	public Algorithme(Evaluation e, int profondeur){
		this.evaluation = e;
		this.profondeurRecherche = profondeur;
	}

	//Utilisé pour MonteCarlo (pas de notions de profondeur ni d'evaluation d'une grille), temps en ms
	public Algorithme(int thinkingtime){
		this.thinkingTime = thinkingtime;
	}

	public abstract int[] caseAJouer(Grille grille, Joueur joueur);
}