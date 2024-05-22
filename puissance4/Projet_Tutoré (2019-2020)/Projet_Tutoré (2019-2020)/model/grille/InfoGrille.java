package model.grille;

import view.*;
import model.*;

// Classe étant le model pour dessinGrille, cette classe regroupe tous les calculs et données nécéssaire pour le dessin des 5 profondeurs etc
public class InfoGrille{

	// Variable permettant de savoir quelle affichage affiché
	private int affichage;
	private int colAfficher;
	private int ligAfficher;

	// Variable contenant les données de la partie en cours
	private Partie partie;

	// Caractéristique de la grille
	private int nbrColonne;
	private int nbrLigne;
	private int nbrProfondeur;

	private int largeurGrille;
	private int hauteurGrille;

	private int decalagePosX;
	private int decalagePosY;

	private int posX;
	private int posY;

	private int debutY;
	private int debutX;


	// grille de la partie
	private Grille grille;


	// Variable concernant les cercles

	private int xCercle;
	private int yCercle;

	private int largeurCercle;
	private int hauteurCercle;

	// variable utile pour la transition
	private boolean transitionEtat = false;

	private int transitionX;
	private int transitionY;
	private int transitionYInit;
	private int transitionYLimite;

	private int transitionLigne;
	private int transitionColonne;
	private int transitionProfondeur;

	// Utile pour calculer les différentes paramètres
	private Fenetre fenetre;


	// Variable utile pour le cercle de prévisualisation
	private int cerclePX;
	private int cerclePZ;
	private boolean cerclePB;


	public InfoGrille(Fenetre f, Partie j){
		partie = j;

		

		// Correspond aux nombres de profondeurs, utile lors du calcul de la hauteur de la grille
		grille = partie.getGrille();

		nbrColonne = grille.getNbrColonne();
		nbrLigne = grille.getNbrLigne();
		nbrProfondeur = grille.getNbrProfondeur();

		fenetre = f;

		debutY = 5;
		debutX = fenetre.getWidth() / 25;

		largeurGrille = fenetre.getWidth() / nbrColonne + fenetre.getWidth() / (nbrColonne*9);
		hauteurGrille = fenetre.getHeight() / nbrLigne - fenetre.getHeight() ;

		transitionYLimite = 500;

		largeurCercle = largeurGrille / nbrColonne;
		hauteurCercle = hauteurGrille / nbrLigne;

		decalagePosX = largeurGrille + 15;
		decalagePosY = hauteurGrille - 15;

		affichage = 0;
		colAfficher = 0;
	}

	// Méthode concernant la vue affichée

	public void setAffichage(int i){
		affichage = i;
		fenetre.repaint();
		fenetre.revalidate();
	}

	public int getAffichage(){
		return affichage;
	}

	public int getColonneAAfficher(){
		return colAfficher;
	}

	public void setColonneAAfficher(int i){
		colAfficher = i;
	}

	// Méthode pour le cercle de prévisualisation
	public void initPosCercleProvisoire(int i, int j){
		cerclePB = true;
		cerclePX = j;
		cerclePZ = i;
	} 

	public int getCerclePX(){
		return cerclePX;
	}

	public int getCerclePZ(){
		return cerclePZ;
	}

	public boolean getCerclePB(){
		return cerclePB;
	}

	public void setCerclePB(){
		cerclePB = false;
	}

	// Méthode pour une grille

	public int getNbrColonne(){
		return nbrColonne;
	}

	public int getNbrLigne(){
		return nbrLigne;
	}

	public int getNbrProfondeur(){
		return nbrProfondeur;
	}

	public int getLargeurGrille(){
		largeurGrille = fenetre.getWidth() / nbrColonne + fenetre.getWidth() / (nbrColonne*9);
		return largeurGrille;
	}

	public int getHauteurGrille(){
		hauteurGrille = fenetre.getHeight() / nbrLigne - fenetre.getHeight() / (nbrLigne*9);
		return hauteurGrille;
	}


	// Méthodes permettant de récuperer ou changer les valeurs de la postion de la grille

	public int getPosX(){
		return posX;
	}

	public int getPosY(){
		return posY;
	}

	public void decalageGrille(){
		posX -= decalagePosX;
		posY += decalagePosY;
	}


	public int getDebutX(){
		return debutX;
	}

	public int getDebutY(){
		return debutY;
	}

	// Initialise la position de la grille selon sa profondeur

	public void initPos(int i){
		if(i == 0){	
			posX = fenetre.getWidth() - largeurGrille - debutX;
			posY = debutY;
		} else {
			posX = fenetre.getWidth() - largeurGrille * (i+1) - debutX;
			posY = debutY + hauteurGrille * i;
		}
	}


	// Methode utile lors du dessin des cercles

	public void decalageCercleX(){
		xCercle += largeurCercle;
	}

	public void decalageCercleY(){
		yCercle += hauteurCercle;
	}

	public void initCercleX(){
		xCercle = posX;
	}

	public void initCercleY(){
		yCercle = posY;
	}

	public int getCercleX(){
		return xCercle + 6;
	}

	public int getCercleY(){
		return yCercle + 6;
	}

	public int getLargeurCercle(){
		largeurCercle = largeurGrille / nbrColonne - 2;
		return largeurCercle;
	}

	public int getHauteurCercle(){
		hauteurCercle = hauteurGrille / nbrLigne - 2;
		return hauteurCercle;
	}


	// Méthode utile pour la transition

	public void runTransition(){
		transitionEtat = true;
	}

	public boolean getEtatTransition(){
		return transitionEtat;
	}

	public void setTransitionYLimite(){
		transitionYLimite = 0;
	}

	public void verifTransition(){
		if(transitionY == transitionYLimite){
			transitionEtat = false;
		} else {
			transitionY++;

		}
	}

	public void setPosXTransition(int x){
		transitionX = x;
	}

	public void setPosYTransition(int y){
		transitionY = y;
		transitionYInit = y;
	}

	public void setTransitionYLimite(int l){
		transitionYInit = l;
	}

	public int getPosXTransition(){
		return transitionX;
	}

	public int getPosYTransition(){
		return transitionY;
	}

	public int getTransitionLimite(){
		return transitionYLimite;
	}


	// Methode concernant la manipulation des cases 

	public Grille getGrille(){
		return grille;
	}

	public void verifEtatColonne(int p, int c){

		transitionProfondeur = p;
		transitionColonne = c;
		
		initPos(4-p);
		transitionY = posY - getHauteurCercle();
		transitionX = posX + getLargeurCercle() * c + 6;

		transitionYLimite = transitionY + getHauteurGrille();

		for(int i = nbrLigne-1; i >= 0; i--){
			if(grille.getEtatTabCase(i,c,p).getEtat() == Couleur.BLANC){
				break;
			} else {
				transitionYLimite -= getHauteurCercle() - 6;
			}
		}
	}
}