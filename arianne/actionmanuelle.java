import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

/**
 * La classe <code>ActionManuelle</code> permet de faire défiler étape par étape 
 * l'évolution d'un algorithme à l'aide d'un affichage graphique.
 *
 * @version 1.0
 * @author Gaston Lions et Fabien Le Bec
 */


public class ActionManuelle implements KeyListener {
	private Fenetre fenetre;
	private Object monitor;

	/**
	* Constructeur destiné à la création d'une action.
	*
	* @param window la fenêtre.
	* @param pause au monitor.
	*/
	public ActionManuelle(Fenetre window, Object pause){
		this.fenetre = window;
		this.monitor = pause;
	}

	/**
	* Gère les évènements lorsqu'une touche est appuyé.
	*
	* @param e l'évènement de l'élément.
	*/
	public void keyPressed(KeyEvent e){
		try {
			synchronized(this.monitor){
				this.monitor.notify();
                this.fenetre.repaint();
			}
		} catch(IllegalMonitorStateException event){};
	}

	/**
	* Gère les évènements lorsqu'une touche est relaché.
	*
	* @param e l'évènement de l'élément.
	*/
	public void keyReleased(KeyEvent e){}

	/**
	* Gère les évènements lorsqu'une touche est tapé.
	*
	* @param e l'évènement de l'élément.
	*/
	public void keyTyped(KeyEvent e){}
}
