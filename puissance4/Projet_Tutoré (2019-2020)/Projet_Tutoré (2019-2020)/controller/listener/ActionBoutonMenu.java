package controller.listener;

import view.*;
import controller.jeu.*;
import controller.reseau.*;
import model.reseau.Client;

import java.awt.Color;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;


public class ActionBoutonMenu implements ActionListener{

	private AffichageGraphique aGraphique;
	private FenetreAddresseServeur fenetreAddresseServeur;
	private Attente attente;

	public ActionBoutonMenu(AffichageGraphique a){
		aGraphique = a;
	}

	public void actionPerformed(ActionEvent e){
		if(aGraphique.getAccesMenu()) {
			switch(e.getActionCommand()){

				case "Jouer" : 			aGraphique.afficherMenuChoixMode();

				break;
				case "Aide" : 			FenetreAide f = new FenetreAide();
										f.setVisible(true);

				break;
				case "Informations" : 	

				break;
				case "IA" : 			
											aGraphique.afficherMenuIA();
										
				break;
				case "Local" : 			aGraphique.createControllerJeu((Jeu) new JeuLocal(aGraphique));
										aGraphique.getControllerJeu().lancerPartie(false);
										
										
				break;
				case "Réseau" :			aGraphique.setAccesMenu(false);
										fenetreAddresseServeur = new FenetreAddresseServeur();
										fenetreAddresseServeur.setActionListener(this);
										fenetreAddresseServeur.setSize(300,300);
										fenetreAddresseServeur.setLocationRelativeTo(aGraphique.getFenetre());
										fenetreAddresseServeur.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
										fenetreAddresseServeur.setVisible(true);
										fenetreAddresseServeur.addWindowListener(new WindowAdapter() {
											public void windowClosing(WindowEvent e) {
												aGraphique.setAccesMenu(true);
												fenetreAddresseServeur.dispose();
											}
										});
				break;

				case "Alpha Beta" :	
									aGraphique.setAlgo("alpha");
									aGraphique.afficherMenuChoixProfondeur();
				break;

				case "Min Max" :
									aGraphique.setAlgo("min");
									aGraphique.afficherMenuChoixProfondeur();
				break;

				case "Monte-Carlo" :
									aGraphique.setAlgo("montecarlo");
									aGraphique.createControllerJeu((Jeu) new JeuLocal(aGraphique));
									aGraphique.getControllerJeu().lancerPartie(true);
				break;			

				case "Niveau 1" :
									aGraphique.setProf(1);
									aGraphique.createControllerJeu((Jeu) new JeuLocal(aGraphique));
									aGraphique.getControllerJeu().lancerPartie(true);
				break;

				case "Niveau 2"  :
									aGraphique.setProf(2);
									aGraphique.createControllerJeu((Jeu) new JeuLocal(aGraphique));
									aGraphique.getControllerJeu().lancerPartie(true);
				break;

				case "Niveau 3" : 
									aGraphique.setProf(3);
									aGraphique.createControllerJeu((Jeu) new JeuLocal(aGraphique));
									aGraphique.getControllerJeu().lancerPartie(true);
				break;

				case "Retour" : 			aGraphique.afficherMenuPrincipal();
											aGraphique.setAlgo(null);
				break;
				case "Quitter" : 		System.exit(0);

				break;
				case "Menu principal" : 	aGraphique.afficherMenuPrincipal();
				break;


			};
		} else {
			if(e.getActionCommand().equals("OK")) {
				JTextField champs = fenetreAddresseServeur.getChamps();
				String[] text = champs.getText().split(":");
				try {
					if(text[0] == null || text[1] == null) {
						champs.setForeground(Color.RED);
						fenetreAddresseServeur.repaint();
					} else {
						aGraphique.setAccesMenu(true);
						startGameReseau(Integer.parseInt(text[1]), text[0]);
						fenetreAddresseServeur.dispose();
					}
				} catch (Exception ex) {
					champs.setForeground(Color.RED);
					fenetreAddresseServeur.repaint();
				}
			}
		}
	}
		
	private void startGameReseau(int port, String adresseIp) {
		try {
			Client client = new Client(port, adresseIp);
			aGraphique.createControllerJeu((Jeu) new JeuReseau(aGraphique, client));
			aGraphique.getControllerJeu().lancerPartie(false);
			Thread th = new Attente((JeuReseau) aGraphique.getControllerJeu(), client, aGraphique);
			th.start();
		} catch(IOException err) {
			FenetreServeurInexistant fenetre = new FenetreServeurInexistant();
			fenetre.setSize(300,200);
			fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			fenetre.setLocationRelativeTo(aGraphique.getFenetre());
			fenetre.setVisible(true);
		}
	}

	public Attente getAttenteReseau() {
		return this.attente;
	} 
}



