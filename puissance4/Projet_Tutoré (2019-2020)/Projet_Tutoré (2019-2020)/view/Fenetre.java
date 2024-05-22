package view;

import javax.swing.*;
import java.awt.*;


public class Fenetre extends JFrame{

	private AffichageGraphique graphique;
	private boolean cliquable = false;

	public Fenetre(){
		graphique = new AffichageGraphique(this);
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

		int fenetrew = (int)(((double)gd.getDisplayMode().getWidth())/1.75);
		int fenetreh = (int)(((double)gd.getDisplayMode().getHeight())/1.08);

		setTitle("Puissance 4 PT");
		setSize(fenetrew,fenetreh);
		setLocation((gd.getDisplayMode().getWidth()/2)-(fenetrew/2),(gd.getDisplayMode().getHeight()/2)-(fenetreh/2));
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(500,500));		

		graphique.afficherMenuPrincipal();
	}

	public void setCliquable(boolean b){
		cliquable = b;
	}

	public boolean getCliquable(){
		return cliquable;
	}
}