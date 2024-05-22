package model;

import view.*;

import javax.swing.*;
import java.awt.*;



public class ModelAide{

	private int compteurClique;
	private FenetreAide fenetre;


	// VARIABLE UTILE POUR LA POSITIONS DES DIFFÉRENTS TEXTES
	private int posXBloc1 = 150;
	private int posYBloc1 = 20;

	private int posXBloc2 = 20;
	private int posYBloc2 = 50;

	private int posXBloc4 = 20;
	private int posYBloc4 = 500;

	private int posXBloc5 = 20;
	private int posYBloc5 = 550;

	private int posXBloc6 = 80;
	private int posYBloc6 = 600;

	private int posXBloc7 = 80;
	private int posYBloc7 = 650;

	private String[] texte = new String[6];


	// VARIABLES UTILES POUR LE DESSIN DES GRILLES SERVANT D'EXEMPLES	

	private int largeurGrille;
	private int hauteurGrille;

	private int largeurCercle;
	private int hauteurCercle;

	private int posXGrille;
	private int posYGrille;

	public ModelAide(FenetreAide f){
		compteurClique = 0;
		fenetre = f;

		texte[0] = "Informations sur le fonctionement et disposition des grilles : ";
		texte[1] = "Pour commencer il faut imaginer 5 grilles de puissance 4 les unes derrieres les autres.";
		texte[2] = "P2 se situe derriere P1, P3 derriere P2 et ainsi de suite.";
		texte[3] = "L\'alignement de toutes ces grilles va donc permettre d'ajouter beaucoup de possibilites de gagner :";
		texte[4] = "- En diagonale entre les profondeurs (cercles noirs remplis sur dessin)";
		texte[5] = "- En ligne entre les profondeurs (cercles noirs sur dessin)";

		largeurGrille = fenetre.getWidth()/10;
		hauteurGrille = fenetre.getHeight()/10;

		largeurCercle = largeurGrille / 6;
		hauteurCercle = hauteurGrille / 5;
	}




	public int getLargeurCercle(){
		return largeurCercle;
	}

	public int getHauteurCercle(){
		return hauteurCercle;
	}

	public int getPosXCercleVide(){
		return posXGrille + 2;
	}

	public int getPosYCercleVide(){
		return posYGrille + hauteurGrille - hauteurCercle;
	}

	public int getPosXCerclePlein(){
		return posXGrille + largeurCercle * 5;
	}

	public int getPosYCerclePlein(int i){
		return posYGrille + hauteurCercle * (i);
	}



	public int getPosXGrille(int i){
		posXGrille = largeurGrille * (6-i);
		return posXGrille;
	}

	public int getPosYGrille(int i){
		posYGrille = 80 + hauteurGrille*(i);
		return posYGrille;
	}

	public int getLargeurGrille(){
		return largeurGrille;
	}

	public int getHauteurGrille(){
		return hauteurGrille;
	}



	public void addClique(){
		compteurClique++;
	}

	public int getCompteurClique(){
		return compteurClique;
	}

	public String getTexte(int i){
		return texte[i-1];
	}

	public int getPosXBloc(int i){
		int retour = 0;
		switch(i){
			case 1 : retour = posXBloc1;
			break;
			case 2 :  retour = posXBloc2;
			break;
			case 4 : retour = posXBloc4;
			break;
			case 5 : retour = posXBloc5;
			break;
			case 6 : retour = posXBloc6;
			break;
			case 7 : retour = posXBloc7;
			break;
		};
		return retour;
	}

	public int getPosYBloc(int i){
		int retour = 0;
		switch(i){
			case 1 : retour = posYBloc1;
			break;
			case 2 :  retour = posYBloc2;
			break;
			case 4 : retour = posYBloc4;
			break;
			case 5 : retour = posYBloc5;
			break;
			case 6 : retour = posYBloc6;
			break;
			case 7 : retour = posYBloc7;
			break;
		};
		return retour;
	}
}