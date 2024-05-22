package model.grille;

import model.*;

public class Case{

	private int idLigne, idColonne, idProfondeur;
	private Couleur etat;

	public Case(int l, int c, int p){
		idLigne = l;
		idColonne = c;
		idProfondeur = p;
		etat = Couleur.BLANC;
		//addMouseListener();
	}

	public Couleur getEtat(){
		return etat;
	}

	public int getLigne(){
		return idLigne;
	}

	public int getColonne(){
		return idColonne;
	}

	public int getProfondeur(){
		return idProfondeur;
	}	

	public void changerEtat(Couleur c){
		etat = c;
	}

}