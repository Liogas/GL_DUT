package controller.listener;

import javax.swing.*;
import java.awt.event.*;

public class ActionFenetre implements WindowListener{

	private JFrame fenetre;

	public ActionFenetre(JFrame f){
		fenetre = f;
	}


	public void windowActivated(WindowEvent evenement){

	}      // premier plan
	public void windowClosed(WindowEvent evenement){

	}         // après fermeture
	public void windowClosing(WindowEvent evenement){
		fenetre.dispose();
	}        // avant fermeture
	public void windowDeactivated(WindowEvent evenement){}    	// arrière-plan
	public void windowDeiconified(WindowEvent evenement){} 		// restauration
	public void windowIconified(WindowEvent evenement){}      	// minimisation
	public void windowOpened(WindowEvent evenement){}         	// après ouverture
}