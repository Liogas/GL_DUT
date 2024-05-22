import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * La classe <code>Fenetre</code> est utilisée pour créer une fenêtre.
 *  
 * @version 1.0
 * @author Gaston Lions & Fabien Le Bec
 */

public class Fenetre extends JFrame{

/**
* Constructeur destiné à donner les options de la fenêtre.
*/
	public Fenetre(){
		super("Algorithme manuel");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(500,500));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

/**
* Constructeur destiné à donner les options de la fenêtre ainsi que son nom.
*
* @param s Le nom donnée à la fenêtre.
*/
	public Fenetre(String s){
		super(s);
		this.setSize(800,800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
	}
}