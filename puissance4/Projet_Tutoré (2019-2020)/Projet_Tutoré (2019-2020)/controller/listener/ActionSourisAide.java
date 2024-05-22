package controller.listener;
import view.*;

import java.awt.event.*;


public class ActionSourisAide implements MouseListener{

	private DessinAide dessin;
	private FenetreAide fenetre;


	public ActionSourisAide(FenetreAide f, DessinAide d){
		fenetre = f;
		dessin = d;
	}

	public void mouseClicked(MouseEvent e){
		if(dessin.getModel().getCompteurClique() < 6){
			dessin.getModel().addClique();
			fenetre.repaint();
		}
	}

	public void mousePressed(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseExited(MouseEvent e){}

}