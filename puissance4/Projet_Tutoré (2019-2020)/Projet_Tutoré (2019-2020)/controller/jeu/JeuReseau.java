package controller.jeu;

import java.awt.BorderLayout;
import java.awt.Window;
import java.io.IOException;
import javax.swing.*;
import java.awt.event.*;

import model.*;
import view.*;
import model.grille.*;
import model.joueur.*;
import model.reseau.*;

/**
 * Jeu en reseau
 */
public class JeuReseau extends Jeu{

	private Partie jeu;
	private Joueur[] joueurs = new Joueur[2];
	private Grille grille;
	private Client client;
	private boolean jePeuxJouer = false;
	private JFrame frame;
	private PanneauAttenteJoueur panneau;

	public JeuReseau(AffichageGraphique a, Client c){
		super(a, c);

		frame = new JFrame("En attente d'un joueur");
		panneau = new PanneauAttenteJoueur("Recherche d'un joueur");
		frame.add(panneau, BorderLayout.CENTER);
		frame.setSize(300,200);
		frame.setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
		frame.setLocationRelativeTo(a.getFenetre());
		frame.setVisible(true);
		
		this.client = c;
	}

	public void lancerPartieIA(JoueurIA j1, JoueurIA j2){}

	@Override
	public void lancerPartie(boolean ia){
		aGraphique.getFenetre().addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try{
					client.envoyer("fin");
				} catch(IOException exc) {
					exc.printStackTrace();
				}
				
			}
		});
		this.client.connexion();
		JoueurHumain j1,j2;
		j1 = new JoueurHumain(Couleur.ROUGE);
		j2 = new JoueurHumain(Couleur.JAUNE);	
		joueurs[0] = j1;
		joueurs[1] = j2;
		jeu = new Partie(joueurs);
		grille = new Grille(jeu);
		jeu.setGrille(grille);
		jeu.nouveauTour();
		aGraphique.afficherInterfaceJeu();
	}

	@Override
	public int jouer(int c, int p){
		try {
			if(jePeuxJouer) {
				int i;
				Information info;
				info = new Information(c,p);
				this.client.envoyer(info.getEncodage());
				if((i = grille.inserer(c,p, jeu.getJoueurActuel())) == 0) {
					return 0;
				} else {
					aGraphique.getFenetre().repaint();
					aGraphique.getFenetre().revalidate();
					Couleur etatPartie = grille.qqunAgagne();
					if(etatPartie == null){
						jeu.nouveauTour();
						return 1;
					} else {
						return 2;
					}
				}
			} else {
				return -1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int afficherJeton(int c, int p) {
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

	public void jePeuxJouer(boolean b) {
		this.jePeuxJouer = b;
	}

	public Partie getPartie(){
		return jeu;
	}
	public void fermerFenetreAttente() {
		this.frame.dispose();
	}
}