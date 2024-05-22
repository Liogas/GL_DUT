import javax.swing.*;
import java.awt.*;

/**
 * La classe <code>SecondThread</code> permet l'exécution simultané de l'affichage et de l'algorithme.
 *
 * @version 1.0
 * @author Gaston Lions et Fabien Le Bec
 */

public class SecondThread extends Thread {
	private Fichier choixGrille;
	private int choixAlgo;

    /**
    * Constructeur destiné à créer le SecondThread.
    *
    * @param grille le fichier du labyrinthe.
    * @param algo l'algorithme (1 : aléatoire, 0 : deterministe).
    */
	public SecondThread(Fichier grille, int algo){
    	super();
    	this.choixGrille = grille;
    	this.choixAlgo = algo;
    	this.choixGrille.openFile();
  	}

    /**
    * Lance les instructions.
    */
  	public void run(){
        int resultat;
    	Fenetre labyrinthe = new Fenetre();
        JPanel info = new JPanel();
        JPanel legendes = new JPanel();
        GridLayout affichagelegendes = new GridLayout(6,1);
        legendes.setLayout(affichagelegendes);

        JLabel movcount = new JLabel("Mouvement n°0");
        JLabel nextmov = new JLabel("Prochain mouvement :");
        JLabel titre = new JLabel("Légendes :");
        JLabel magenta = new JLabel("        Magenta = Thésée");
        JLabel noir = new JLabel("        Noir = Mur/Case non-découvert");
        JLabel rouge = new JLabel("        Rouge = Mur découvert");
        JLabel gris = new JLabel("        Gris = Case découverte");
        JLabel blanc = new JLabel("        Blanc = Case non-découverte");

        legendes.add(titre);
        legendes.add(magenta);
        legendes.add(noir);
        legendes.add(rouge);
        legendes.add(gris);
        legendes.add(blanc);

        GridLayout affichage = new GridLayout(3,1);
        info.setLayout(affichage);

        info.add(movcount);
        info.add(nextmov);
        info.add(legendes);
        info.setPreferredSize(new Dimension(300,200));
        labyrinthe.add(info, BorderLayout.WEST);

    	DessinGrille dessin = new DessinGrille(labyrinthe, this.choixGrille,this.choixGrille.getThesee(), this.choixAlgo, movcount, nextmov);
    	labyrinthe.add(dessin, BorderLayout.CENTER);

    	Algorithme algorithme = new Algorithme(this.choixGrille, labyrinthe, dessin, this.choixGrille.getThesee(), true);

    	if(this.choixAlgo==1){
    		resultat = algorithme.aleatoire();
    	} else {
    		resultat = algorithme.deterministe();
    	}

        if(resultat == 0){
            JFrame nosol = new JFrame();
            JOptionPane.showMessageDialog(nosol, "Il n'existe aucune solution pour ce labyrinthe.", "Résultat", JOptionPane.WARNING_MESSAGE);
        } 
        if(resultat>0){
            JFrame popup = new JFrame();
            if(this.choixAlgo == 1){
                JOptionPane.showMessageDialog(popup, "L'algorithme a été résolu en "+resultat+" mouvements avec l'algorithme aléatoire.", "Résultat", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(popup, "Le nombre de mouvements nécessaire pour résoudre ce labyrinthe avec l'algorithme déterministe est de "+resultat+".", "Résultat", JOptionPane.INFORMATION_MESSAGE);
            }
        }
  	}       
}