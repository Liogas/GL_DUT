import javax.swing.*;
import java.awt.*;

/**
 * La classe <code>Menu</code> est utilisée pour la gestion du menu ainsi
 * que les paramètre nécessaîre pour le lancement d'une grille.
 *  
 * @version 1.0
 * @author Gaston Lions & Fabien Le Bec
 */

public class Menu{
	private Fenetre fenetre;
	private Menu gestMenu;
	private int choixVue,choixAlgo;
	private Fichier choixGrille;
	private JButton boutonGrille,boutonVue,boutonAlgo;

	/**
	* Constante qui correspond à la vue aléatoire.
	*/
	final int AUTOMATIQUE = 1;
    /**
	* Constante qui correspond à la vue manuel.
	*/
	final int MANUEL = 2;
    /**
	* Constante qui correspond à l'algorithme aléatoire.
	*/
	final int ALEATOIRE = 1;

 	/**
	* Constante qui correspond à l'algorithme deterministe.
	*/
	final int DETERMINISTE = 2;
 
	/**
	* Constructeur qui permet de stocker les choix de grilles
	* d'algorithme et de vue.
	*
	* @param f fenetre
	*/

	public Menu(Fenetre f){
		this.gestMenu = this;
		this.fenetre = f;
		this.choixVue = 0;
		this.choixAlgo = 0;
		this.choixGrille = null;
		this.setMenu();
	}	


	/**
	* Renvoi la vue selectionner.
	* Initialise la vue selectionné (1 ou 2)
	*
	* @return la vue selectionné (1 ou 2).
	* @param x Vue (1 ou 2).
	*/
	public int setChoixVue(int x){
		this.choixVue = x;
		return choixVue;
	}

	/**
	* Renvoi l'algorithme selectionné.
	* Initialise l'algorithme selectionné (1 ou 2)
	*
	* @return l'algorithme selectionné (1 ou 2).
	* @param x Algorithme (1 ou 2).
	*/
	public int setChoixAlgo(int x){
		this.choixAlgo = x;
		return choixAlgo;
	}

	/**
	* Initialise la grille selectionnée (nom fichier)
	* @param x Grille (nom fichier).
	*/
	public void setChoixGrille(String x){
		this.choixGrille = new Fichier(x);
	}

	/**
	* Affiche le menu principal.
	*/ 
	public void setMenu(){
		JPanel menu = new JPanel();
		menu.setLayout(null);
		menu.setBounds(fenetre.getWidth()/2 - fenetre.getWidth()/8, 0, fenetre.getWidth()/4, fenetre.getHeight());

		this.boutonGrille = new JButton("Grille");
		this.boutonGrille.setBounds(0 ,0, menu.getWidth(), 50);
		this.boutonVue = new JButton("Vue");
		this.boutonVue.setBounds(0,100, menu.getWidth(), 50);
		this.boutonAlgo = new JButton("Algorithme");
		this.boutonAlgo.setBounds(0,200, menu.getWidth(), 50);
		JButton choixMapEdit = new JButton("Création grille");
		choixMapEdit.setBounds(0,300, menu.getWidth(), 50);
		JButton choixLancer = new JButton("Lancer");
		choixLancer.setBounds(0,400, menu.getWidth(), 50);
		JButton choixQuitt = new JButton("Quitter");
		choixQuitt.setBounds(0,500, menu.getWidth(), 50);
	
		menu.add(this.boutonGrille);
		menu.add(this.boutonVue);
		menu.add(this.boutonAlgo);
		menu.add(choixMapEdit);
		menu.add(choixLancer);
		menu.add(choixQuitt);
		this.fenetre.add(menu, BorderLayout.CENTER);

		boutonGrille.addActionListener(new ActionMenu(this.gestMenu, this.fenetre, menu, this.boutonGrille));
		boutonVue.addActionListener(new ActionMenu(this.gestMenu, this.fenetre, menu, this.boutonVue));
		boutonAlgo.addActionListener(new ActionMenu(this.gestMenu, this.fenetre, menu, this.boutonAlgo));
		choixMapEdit.addActionListener(new ActionMenu(this.gestMenu, this.fenetre, menu, choixMapEdit));
		choixLancer.addActionListener(new ActionMenu(this.gestMenu, this.fenetre, menu, choixLancer));
		choixQuitt.addActionListener(new ActionMenu(this.gestMenu, this.fenetre, menu, choixQuitt));

		this.fenetre.setVisible(true);
	}


	/**
	* Affiche le menu du choix de la vue.
	*/
	public void setMenuVue(){
		JPanel menu = new JPanel();
		menu.setLayout(null);
		menu.setBounds(fenetre.getWidth()/2 - fenetre.getWidth()/8, 0, fenetre.getWidth()/4, fenetre.getHeight());

		JButton choixManuel = new JButton("Manuel");
		choixManuel.setBounds(0,100, menu.getWidth(), 50);
		JButton choixAuto = new JButton("Automatique");
		choixAuto.setBounds(0,200, menu.getWidth(), 50);
		JButton choixRetour = new JButton("Retour");
		choixRetour.setBounds(0,300, menu.getWidth(), 50);
	
		menu.add(choixManuel);
		menu.add(choixAuto);
		menu.add(choixRetour);
		this.fenetre.add(menu);

		choixManuel.addActionListener(new ActionMenu(this.gestMenu, this.fenetre, menu, choixManuel));
		choixAuto.addActionListener(new ActionMenu(this.gestMenu, this.fenetre, menu, choixAuto));
		choixRetour.addActionListener(new ActionMenu(this.gestMenu, this.fenetre, menu, choixRetour));
	}

	/**
	* Affiche le menu du choix de l'algorithme.
	*/
	public void setMenuAlgo(){
		JPanel menu = new JPanel();
		menu.setLayout(null);
		menu.setBounds(fenetre.getWidth()/2 - fenetre.getWidth()/8, 0, fenetre.getWidth()/4, fenetre.getHeight());

		JButton choixAlea = new JButton("Aléatoire");
		choixAlea.setBounds(0,100, menu.getWidth(), 50);
		JButton choixDeter = new JButton("Déterministe");
		choixDeter.setBounds(0,200, menu.getWidth(), 50);
		JButton choixRetour = new JButton("Retour");
		choixRetour.setBounds(0,300, menu.getWidth(), 50);
	
		menu.add(choixAlea);
		menu.add(choixDeter);
		menu.add(choixRetour);
		this.fenetre.add(menu);

		choixAlea.addActionListener(new ActionMenu(this.gestMenu, this.fenetre, menu, choixAlea));
		choixDeter.addActionListener(new ActionMenu(this.gestMenu, this.fenetre, menu, choixDeter));
		choixRetour.addActionListener(new ActionMenu(this.gestMenu, this.fenetre, menu, choixRetour));
	}

	/**
	* Renvoie si une grille, une vue et un algorithme ont bien été choisi.
	*
	* @return true ou false.
	*/
	public boolean verifLancement(){
		if(this.choixGrille == null && this.choixAlgo == 0 && this.choixVue == 0){
			this.boutonGrille.setBackground(new Color(255,0,0));
			this.boutonAlgo.setBackground(new Color(255,0,0));
			this.boutonVue.setBackground(new Color(255,0,0));
		 	return false;
		} else if(this.choixGrille == null && this.choixAlgo == 0){
			this.boutonGrille.setBackground(new Color(255,0,0));
			this.boutonAlgo.setBackground(new Color(255,0,0));
			return false;
		} else if(this.choixGrille == null && this.choixVue == 0){
			this.boutonGrille.setBackground(new Color(255,0,0));
			this.boutonVue.setBackground(new Color(255,0,0));
			return false;
		} else if(this.choixAlgo == 0 && this.choixVue == 0){
			this.boutonAlgo.setBackground(new Color(255,0,0));
			this.boutonVue.setBackground(new Color(255,0,0));
			return false;
		} else if(this.choixVue == 0){
			this.boutonVue.setBackground(new Color(255,0,0));
			return false;
		} else if(this.choixAlgo == 0){
			this.boutonAlgo.setBackground(new Color(255,0,0));
			return false;
		}else{
			return true;
		}
	}


	/**
	* Lance l'algorithme.
	*/
	public void lancement(){		
		if(this.choixVue == AUTOMATIQUE){
			int resultat=0;

			this.choixGrille.openFile();

    		Algorithme algorithme = new Algorithme(this.choixGrille, this.choixGrille.getThesee(), false);

    		if(this.choixAlgo == ALEATOIRE){
    			resultat = algorithme.aleatoire();
    		}
    		if(this.choixAlgo == DETERMINISTE){
    			resultat = algorithme.deterministe();
    		}

    		if(resultat == 0){
    			JFrame popup = new JFrame();
    			JOptionPane.showMessageDialog(popup, "Il n'existe aucune solution pour ce labyrinthe.", "Résultat", JOptionPane.INFORMATION_MESSAGE);
    		} else {
    			JFrame popup = new JFrame();
	    		if(this.choixAlgo == ALEATOIRE){
	    			JOptionPane.showMessageDialog(popup, "La moyenne de mouvements sur 100 exécutions est de "+resultat+".", "Résultat", JOptionPane.INFORMATION_MESSAGE);
	    		}
	    		if(this.choixAlgo == DETERMINISTE){
	    			JOptionPane.showMessageDialog(popup, "Le nombre de mouvements nécessaire pour résoudre ce labyrinthe avec l'algorithme déterministe est de "+resultat+".", "Résultat", JOptionPane.INFORMATION_MESSAGE);
	    		}
    		}

		} else if(this.choixVue == MANUEL){
			this.fenetre.dispose();
			SecondThread algo = new SecondThread(this.choixGrille, this.choixAlgo);
		    algo.start();
		}
	}
}