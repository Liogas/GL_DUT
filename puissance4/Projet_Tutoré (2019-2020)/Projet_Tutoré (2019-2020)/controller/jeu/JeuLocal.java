package controller.jeu;


import model.grille.*;
import model.joueur.*;
import model.algorithme.*;
import model.evaluation.*;
import model.*;
import view.*;

import javax.swing.*;

/**
 * Jeu en Local
 */
public class JeuLocal extends Jeu{

	private Partie jeu;
	private Joueur[] joueurs = new Joueur[2];
	private Grille grille;

	public JeuLocal(AffichageGraphique a){
		super(a);
	}

	public void lancerPartieIA(JoueurIA j1, JoueurIA j2){}

	@Override
	public void lancerPartie(boolean ia){
		Joueur j1;
		Joueur j2;

		j1 = new JoueurHumain(Couleur.ROUGE);
		if(ia){
			if(aGraphique.getAlgo().equals("alpha")){
				j2 = new JoueurIA(Couleur.JAUNE, new AlphaBeta(new EvaluationNbrAlignement(), aGraphique.getProf()));
			} else if(aGraphique.getAlgo().equals("min")){
				j2 = new JoueurIA(Couleur.JAUNE, new MiniMax(new EvaluationNbrAlignement(), aGraphique.getProf()));
			} else if(aGraphique.getAlgo().equals("montecarlo")){
				j2 = new JoueurIA(Couleur.JAUNE, new MonteCarlo(2000));
			} else {
				j2 = null;
			}
		} else {
			j2 = new JoueurHumain(Couleur.JAUNE);
		}

		joueurs[0] = j1;
		joueurs[1] = j2;
		jeu = new Partie(joueurs);
		jeu.setController(this);
		grille = new Grille(jeu);
		jeu.setGrille(grille);
		jeu.nouveauTour();
		aGraphique.afficherInterfaceJeu();
	}

	@Override
	public int jouer(int c, int p){
		int i;
		if((i = grille.inserer(c,p,jeu.getJoueurActuel())) == 0){
			return 0;
		} else {
			aGraphique.getFenetre().repaint();
			aGraphique.getFenetre().revalidate();
			Couleur etatPartie = grille.qqunAgagne();

			//System.out.println("Nbr de coups possibles : "+grille.coupsPossibles().size());
			
			if(etatPartie == null){
				String audioFilePath = "Son/mvtJeton.wav";
				AudioPlayer player = new AudioPlayer(audioFilePath);
				SwingUtilities.invokeLater(new Runnable() {
				    public void run() {
				        jeu.nouveauTour();
				    }
				});				
				return 1;
			} else {
				String audioFilePath = "Son/mmmmhhhhh.wav";
				AudioPlayer player = new AudioPlayer(audioFilePath);
				return 2;
			}
		}
	}

	public Partie getPartie(){
		return jeu;	
	}
}