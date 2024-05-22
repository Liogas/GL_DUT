/**
 * La classe <code>Menu</code> est utilisée pour la gestion des 
 * actions venant de l'édition de grille.
 * 
 * @version 1.0
 * @author Gaston Lions & Fabien Le Bec
 */

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;

public class ActionMapEditor implements ActionListener{
	private JTextField zoneText;
	private int taille,nbrClick,indice;
	private Fenetre fenetre;
	private MapEditor editor;
	private int nbrCases=-1;
	private JPanel zoneGrille;
	private Bouton bouton;
	private String mode;
	private JButton valider;
	private Bouton[] groupeB;


	/**
	* Constructeur destiné à réagir à la taille indiquer.
	*
	* @param tf la zone de texte (JTexfield).
	* @param me l'édition de grille.
	* @param f la fenêtre.
	* @param p la zone où est dessiner la grille.
	*/
	public ActionMapEditor(JTextField tf, MapEditor me, Fenetre f, JPanel p){
		this.mode = "menu";
		this.zoneText = tf;
		this.taille = 0;
		this.editor = me;
		this.fenetre = f;
		this.zoneGrille = p;
	}

	/**
	* Constructeur destiné à réagir à un clique sur un bouton de la grille.
	*
	* @param i la position du bouton dans le tableau.
	* @param me l'édition de grille.
	* @param gb le tableau comportant les boutons. 
	*/
	public ActionMapEditor(int i, MapEditor me, Bouton[] gb){
		this.mode = "edition";
		this.indice = i;
		this.editor = me;
		this.groupeB = gb;
	}

	/**
	* Constructeur destiné à réagir au remplissage aléatoire.
	*
	* @param p la zone où est dessiner la grille.
	* @param me l'édition de grille.
	* @param f la fenêtre. 
	*/
	public ActionMapEditor(MapEditor me, JPanel p, Fenetre f){
		this.mode = "random";
		this.editor = me;
		this.zoneGrille = p;
		this.fenetre = f;
	}

	/**
	* Constructeur destiné à réagir au remplissage aléatoire.
	*
	* @param v la validation de la grille.
	* @param me l'édition de grille.
	* @param f la fenêtre. 
	* @param gb le tableau comportant les boutons.
	*/
	public ActionMapEditor(JButton v, MapEditor me, Fenetre f, Bouton[] gb){
		this.mode = "valider";
		this.valider = v;
		this.editor = me;
		this.fenetre = f;
	}


	/**
	* Gère tous les évènements.
	*
	*@param evenement l'évènement de l'élément.
	*/
	public void actionPerformed(ActionEvent evenement){

		if(this.mode.equals("edition")){
			this.groupeB[indice].setClique();
			if(this.groupeB[indice].getClique() == 1){
				this.groupeB[indice].setBackground(Color.BLACK);
			} else if(this.groupeB[indice].getClique() == 2){
				this.groupeB[indice].setBackground(Color.MAGENTA);
				this.editor.setEntree();
			} else if(this.groupeB[indice].getClique() == 3){
				this.groupeB[indice].setBackground(Color.BLUE);
				this.editor.setSortie();
			} else{
				this.groupeB[indice].setBackground(new Color(255,255,255));
			}


		} else if(this.mode.equals("menu")){
			Container c = this.fenetre.getContentPane();
			try{
				this.nbrCases = Integer.parseInt(evenement.getActionCommand());
				if(this.nbrCases < 2){
					this.zoneText.setText(" ent >= 2");
				} else if(this.nbrCases > 100){
					this.zoneText.setText(" ent <= 100");
				}else {
					c.removeAll();
					this.fenetre.repaint();
					this.editor.createGrille(this.nbrCases);
				}
			} catch(NumberFormatException e){
				JFrame errorint = new JFrame();
				JOptionPane.showMessageDialog(errorint, "Veuillez entrer un entier !", "Erreur", JOptionPane.ERROR_MESSAGE);
			} 

		} else if(this.mode.equals("random")){
			Container c = this.fenetre.getContentPane();
			c.remove(this.zoneGrille);
			this.fenetre.repaint();	
			this.editor.rempRand();
		
		} else if(this.mode.equals("valider")){
			if(this.editor.verifConfirm() == false){
				JFrame fenetre = new JFrame();
				JOptionPane.showMessageDialog(fenetre, "Vous devez mettre 1 sortie et 1 entrée !", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				Container c = this.fenetre.getContentPane();
				String nom = JOptionPane.showInputDialog("Nom de la grille :");
				if(nom == null){
					JOptionPane.showMessageDialog(fenetre, "Veuillez choisir un nom de grille !", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					this.editor.setVariable();
					boolean[][] mur = this.editor.getMur();

					Fichier newFichier = new Fichier(nom,this.editor.getTaille(),this.editor.getEntree(), this.editor.getSortie(), mur);
					newFichier.writeFile();
					c.removeAll();
					Menu menu = new Menu(this.fenetre);
					menu.setMenu();
					this.fenetre.validate();
				}
			}
		} 
	}

}