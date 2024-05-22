package controller.listener;

import view.*;
import model.*;
import model.joueur.*;
import controller.*;

import java.awt.event.*;


public class ActionSourisEnJeu implements MouseListener, MouseMotionListener{

	private DessinGrille dessin;
	private Fenetre fenetre;
	private Partie partie;
	private AffichageGraphique affichage;
	private boolean exit = false;

	public ActionSourisEnJeu(Fenetre f, DessinGrille d, Partie p, AffichageGraphique a){
		fenetre = f;
		dessin = d;
		partie = p;
		affichage = a;
	}

	public void mouseClicked(MouseEvent e){

		if(!(dessin.getModelGrille().getEtatTransition()) && fenetre.getCliquable() && (partie.getJoueurActuel() instanceof JoueurHumain) && partie.getVainqueur() == null){

			int largeurFenetre = fenetre.getWidth();

			int debutX = dessin.getModelGrille().getDebutX();
			int debutY = dessin.getModelGrille().getDebutY();

			int largeurGrille = dessin.getModelGrille().getLargeurGrille();
			int hauteurGrille = dessin.getModelGrille().getHauteurGrille();

			int posXCercle;

			int nbrColonne = partie.getGrille().getNbrColonne();
			int nbrProfondeur = partie.getGrille().getNbrProfondeur();
			int nbrLigne = partie.getGrille().getNbrLigne();

			int largeurCercle = dessin.getModelGrille().getLargeurCercle();

			if(dessin.getModelGrille().getAffichage()==0){
				for(int i = 0; i < nbrProfondeur ; i++){

					dessin.getModelGrille().initPos(i);
					dessin.getModelGrille().initCercleX();
					posXCercle = dessin.getModelGrille().getCercleX();

					// GESTION D'UN CLIQUE DANS LES RECTANGLES PERMETTANT DE CHANGER LA VUE
					if(i == nbrProfondeur-1){
						for(int j = 0; j < nbrColonne; j++){
							if(e.getX() >= dessin.getModelGrille().getCercleX()+dessin.getModelGrille().getLargeurCercle()/2+dessin.getModelGrille().getLargeurCercle()*j && e.getX() <= dessin.getModelGrille().getCercleX()+dessin.getModelGrille().getLargeurCercle()/2+dessin.getModelGrille().getLargeurCercle()*j + dessin.getModelGrille().getLargeurCercle()/3){
								if(e.getY()>=dessin.getModelGrille().getPosY()+dessin.getModelGrille().getHauteurGrille()+ dessin.getModelGrille().getLargeurCercle()/3 && e.getY()<=(dessin.getModelGrille().getPosY()+dessin.getModelGrille().getHauteurGrille()+ dessin.getModelGrille().getLargeurCercle()/3)+dessin.getModelGrille().getLargeurCercle()/3){
									dessin.getModelGrille().setColonneAAfficher(j);
									dessin.getModelGrille().setAffichage(1);
									break;
								}
							}
						}
					}


					// GESTION D'UN CLIQUE DANS L'AFFICHAGE AVEC LES 5 PROFONDEURS
					if((e.getX() > largeurFenetre - largeurGrille * (i+1) - debutX && e.getX() < largeurFenetre - largeurGrille * (i+1) - debutX + largeurGrille) && (e.getY() > debutY + hauteurGrille * i   && e.getY() < debutY + hauteurGrille * i + hauteurGrille)){
						for(int j = 0; j < nbrColonne; j++){
							if(e.getX() > posXCercle + largeurCercle * j && e.getX() < posXCercle + largeurCercle * (j+1)){
								int tmp = affichage.getControllerJeu().jouer(j,4-i);
								if(tmp == 2){
									partie.setVainqueur(partie.getJoueurActuel());
								}
								dessin.getModelGrille().verifEtatColonne(4-i,j);
								Thread t = new Thread(new ThreadAnimation(affichage));
								t.start();
							}
						}
					}
				}

				// PERMET DE REVENIR A LA VUE D'ENSEMBLE DES GRILLES
			} else if(dessin.getModelGrille().getAffichage()==1){
					dessin.getModelGrille().setAffichage(0);
			}	
		// PERMET DE REVENIR À L'ÉCRAN PRINCIPAL LORS DE L'AFFICHAGE D'UNE FIN DE PARTIE
		} else if(partie.getVainqueur() != null){
			if((e.getX() > fenetre.getWidth()-fenetre.getWidth()/3 && e.getX() < (fenetre.getWidth()-fenetre.getWidth()/3) + fenetre.getWidth()/2-fenetre.getWidth()/3) && (e.getY() > fenetre.getHeight()/2+fenetre.getHeight()/15 && e.getY() < (fenetre.getHeight()/2+fenetre.getHeight()/15) + fenetre.getHeight()/20 )){
				affichage.afficherMenuPrincipal();
				try {
					affichage.getAttenteReseau().arreter();
				} catch (Exception ex) {
					//TODO: handle exception
				}
			}
		}
	}	

	public void mousePressed(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseDragged(MouseEvent e){}
	public void mouseMoved(MouseEvent e){
		exit = false;
		if(!(dessin.getModelGrille().getEtatTransition()) && fenetre.getCliquable()){

			int largeurFenetre = fenetre.getWidth();

			int debutX = dessin.getModelGrille().getDebutX();
			int debutY = dessin.getModelGrille().getDebutY();

			int largeurGrille = dessin.getModelGrille().getLargeurGrille();
			int hauteurGrille = dessin.getModelGrille().getHauteurGrille();

			int posXCercle;
			int nbrColonne = dessin.getModelGrille().getNbrColonne();
			int largeurCercle = dessin.getModelGrille().getLargeurCercle();

			for(int i = 0; i < 5 ; i++){

				dessin.getModelGrille().initPos(i);
				dessin.getModelGrille().initCercleX();
				posXCercle = dessin.getModelGrille().getCercleX();
				
				if((e.getX() > largeurFenetre - largeurGrille * (i+1) - debutX && e.getX() < largeurFenetre - largeurGrille * (i+1) - debutX + largeurGrille) && (e.getY() > debutY + hauteurGrille * i   && e.getY() < debutY + hauteurGrille * i + hauteurGrille)){
					for(int j = 0; j < nbrColonne; j++){
						if(e.getX() > posXCercle + largeurCercle * j && e.getX() < posXCercle + largeurCercle * (j+1)){
							dessin.getModelGrille().initPosCercleProvisoire(4-i, j);
							exit = true;
							break;
						}
					}
				} 
				if(exit){
					break;
				} else {
					dessin.getModelGrille().setCerclePB();

				}
			}
			fenetre.repaint();
			fenetre.revalidate();
		} 
	}
}