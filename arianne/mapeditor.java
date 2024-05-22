import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

/**
 * La classe <code>MapEditor</code> est utilisé pour créer une grille.
 * 
 * @version 1.0
 * @author Gaston Lions & Fabien Le Bec
 */

public class MapEditor{
	private Fenetre fenetre;
	private int taille,nbrSortie,nbrEntree;
	private Bouton[] tableauB;
	private Random rand = new Random();
	private boolean[][] mur;
	private Point posEntree,posSortie;

/**
/* Constructeur permettant de créer une fenêtre préparer pour la l'édition d'une grille.
*/
	public MapEditor(){ 
		this.fenetre = new Fenetre("Edition");
		this.taille = 0;
		this.createGrille(this.taille);
		this.tableauB = new Bouton[this.taille * this.taille];
		this.mur = new boolean[this.taille][this.taille];
		this.nbrEntree = 0;
		this.nbrSortie = 0;
	}

/**
* Créer tous les éléments de la fenêtre.
*
* @param t la taille souhaité pour le labyrinthe (2 à 100).
*/
	public void createGrille(int t){

		GridLayout gestionGestion = new GridLayout(1,2);
		JPanel zoneGestion = new JPanel();
		zoneGestion.setLayout(gestionGestion);
		zoneGestion.setBounds(0,0,this.fenetre.getWidth(), 50);
		JLabel textTaille = new JLabel("Taille : ");
		JTextField taille = new JTextField();
		zoneGestion.add(textTaille);
		zoneGestion.add(taille);

		this.fenetre.add(zoneGestion);

		JPanel zoneLegende = new JPanel();
		GridLayout gestionLegende = new GridLayout(5,1);
		zoneLegende.setLayout(gestionLegende);
		zoneLegende.setBounds(0, 110, 100, 200);
		JLabel click = new JLabel("CLIQUE : ");
		zoneLegende.add(click);
		JLabel click1 = new JLabel("1 : MUR");
		zoneLegende.add(click1);
		JLabel click2 = new JLabel("2 : ENTREE");
		zoneLegende.add(click2);
		JLabel click3 = new JLabel("3 : SORTIE");
		zoneLegende.add(click3);
		JLabel click4 = new JLabel("4 : VIDE");
		zoneLegende.add(click4);
		this.fenetre.add(zoneLegende);


		this.taille = t;
		JPanel zoneGrille;
		zoneGrille = this.refreshGrille(this.taille);



		JButton valider = new JButton("Enregistrer");
		valider.setSize(this.fenetre.getWidth()/4, 50);
		valider.setLocation(this.fenetre.getWidth()/2 - valider.getWidth(), this.fenetre.getHeight()- 90);
		JButton random = new JButton("Aléatoire");
		random.setSize(this.fenetre.getWidth()/4, 50);
		random.setLocation((this.fenetre.getWidth()/2 - valider.getWidth()) + valider.getWidth()+10, this.fenetre.getHeight()- 90);
		this.fenetre.add(valider);
		this.fenetre.add(random);


		this.fenetre.setVisible(true);

		taille.addActionListener(new ActionMapEditor(taille, this, this.fenetre, zoneGrille));
		random.addActionListener(new ActionMapEditor(this, zoneGrille, this.fenetre));
		valider.addActionListener(new ActionMapEditor(valider, this, this.fenetre, this.tableauB));
	}

/**
* Créer la grille du labyrinthe selon la taille donnée.
*
* @param t la taille souhaité du labyrinthe (2 à 100).
* @return la JPanel où le labyrinthe à été créer.
*/
	public JPanel refreshGrille(int t){

		this.taille = t;

		JPanel panneau = new JPanel();

		if(this.taille <= 0){
		} else{

		GridLayout gestionGrille = new GridLayout(this.taille,this.taille);
		panneau.setBounds(this.fenetre.getWidth()/2,this.fenetre.getHeight()/2, this.fenetre.getWidth()-200, this.fenetre.getHeight()-200);
		panneau.setLocation(this.fenetre.getWidth()/2 - panneau.getWidth() / 2,this.fenetre.getHeight()/2 - panneau.getHeight() / 2);
		panneau.setLayout(gestionGrille);

		this.tableauB = new Bouton[this.taille * this.taille];
		for(int i = 1; i <= tableauB.length; i++){
			Bouton bouton = new Bouton();
			bouton.setBackground(new Color(255,255,255));
			panneau.add(bouton);
			this.tableauB[i-1] = bouton;
			this.tableauB[i-1].addActionListener(new ActionMapEditor(i-1, this, this.tableauB));
		}
		this.fenetre.add(panneau);
		this.fenetre.validate();
		}

		return panneau;
	}

/**
* Retourne le tableau regroupant chaque cases du labyrinthe.
*
* @return b Le tableau.
* @param b le tableau comportant les boutons.
*/
	public Bouton[] getGroupeBouton(Bouton[] b){
		return b;
	}

/**
* Remplit aléatoirement le labyrinthe.
*/
	public void rempRand(){
		int sortie = 1;
		int mur = (this.taille*this.taille)/3;
		int entree = 1;

		if(this.taille <= 0){
			JFrame erreur = new JFrame();
			JOptionPane.showMessageDialog(erreur, "Veuillez entrer une taille avant de générer une grille aléatoire !", "Erreur", JOptionPane.ERROR_MESSAGE);
		} else {
			while(sortie != 0 && entree !=0){
				JPanel panneau = new JPanel();
				GridLayout gestionGrille = new GridLayout(this.taille,this.taille);
				panneau.setBounds(this.fenetre.getWidth()/2,this.fenetre.getHeight()/2, this.fenetre.getWidth()-200, this.fenetre.getHeight()-200);
				panneau.setLocation(this.fenetre.getWidth()/2 - panneau.getWidth() / 2,this.fenetre.getHeight()/2 - panneau.getHeight() / 2);
				panneau.setLayout(gestionGrille);

				sortie = 1;
			 	mur = (this.taille*this.taille)/3;
			 	entree = 1;
				this.tableauB = new Bouton[this.taille * this.taille];
				for(int i = 1; i < tableauB.length + 1; i++){
					Bouton bouton = new Bouton();
					int alea = this.rand.nextInt(9 - 0 + 1);
					if(alea == 0 || alea == 1 || alea == 2){
						bouton.setBackground(Color.BLACK);
						panneau.add(bouton);
						bouton.setClique(1);
						this.tableauB[i-1] = bouton;
						this.tableauB[i-1].addActionListener(new ActionMapEditor(i-1, this, this.tableauB));
						mur--;
					} else if(alea == 7 && entree != 0){
						bouton.setBackground(Color.MAGENTA);
						bouton.setClique(2);
						panneau.add(bouton);
						this.tableauB[i-1] = bouton;
						this.tableauB[i-1].addActionListener(new ActionMapEditor(i-1, this, this.tableauB));
						entree--;
					} else if(alea == 8 && sortie != 0){
						bouton.setBackground(Color.BLUE);
						panneau.add(bouton);
						bouton.setClique(3);
						this.tableauB[i-1] = bouton;
						this.tableauB[i-1].addActionListener(new ActionMapEditor(i-1, this, this.tableauB));
						sortie--;
					} else {
						bouton.setBackground(new Color(255,255,255));
						panneau.add(bouton);
						bouton.setClique(0);
						this.tableauB[i-1] = bouton;
						this.tableauB[i-1].addActionListener(new ActionMapEditor(i-1, this, this.tableauB));
					}
				}
				this.fenetre.add(panneau);
				this.fenetre.validate();
			}
		}
	}

/**
* Met à jour les variables utilisées pour écrire dans le fichier.
*/ 
	public void setVariable(){
		int j = 0;
		int k = -1;
		mur = new boolean[this.taille][this.taille];

		for(int i = 0; i < this.taille * this.taille; i++){
			if(k < this.taille - 1){
				k++;
			} else {
				k = 0;
			} 
			if(i == this.taille * (j+1)){
				j++;
			}
			if(this.tableauB[i].getClique() == 2){
				this.posEntree = new Point(j,k);
				this.mur[j][k] = false;
			} else if(this.tableauB[i].getClique() == 3){
				this.posSortie = new Point(j,k);
				this.mur[j][k] = false;
			} else if(this.tableauB[i].getClique() == 1){
				this.mur[j][k] = true;
			} else{
				this.mur[j][k] = false;
			}
		}
	}

/**
* Retourne si le labyrinthe contient une seule sortie et entrée.
*
* @return si le labyrinthe contient une seule sortie et entrée (true ou false).
*/
	public boolean verifConfirm(){
		int sortie = 0;
		int entree = 0;
		for(int i = 0; i < this.taille*this.taille; i++){
			if(this.tableauB[i].getClique() == 3){
				sortie++;
			} else if(this.tableauB[i].getClique() == 2){
				entree++;
			}
		}

		if(sortie != 1 || entree != 1){
			return false;
		} else {
			return true;
		}
	}

/**
* Retourne la position des murs.
*
* @return la position des murs.
*/
	public boolean[][] getMur(){
		return this.mur;
	}

/**
* Retourne la position de l'entrée.
*
* @return la position de l'entrée.
*/
	public Point getEntree(){
		return this.posEntree;
	}

/**
* Retourne la position de la sortie.
*
* @return la position de la sortie.
*/
	public Point getSortie(){
		return this.posSortie;
	}

/**
* Retourne la taille.
*
* @return la taille.
*/
	public int getTaille(){
		return this.taille;
	}

/**
* Initialise la position de l'entrée.
*/
	public void setEntree(){
		this.nbrEntree++;
	}

/**
* Initialise la position de la sortie.
*/
	public void setSortie(){
		this.nbrSortie++;
	}

/**
* retourne le nombre de sortie.
*
* @return le nombre de sortie.
*/
	public int verifSortie(){
		return this.nbrSortie;
	}

/**
* retourne le nombre d'entrée.
*
* @return le nombre d'entrée.
*/
	public int verifEntree(){
		return this.nbrEntree;
	}
}