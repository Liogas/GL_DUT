import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

/**
 * La classe <code>Bouton</code> est une classe dérivée de JButton possédant l'attribut
 * nbrClique.
 * @version 1.0
 * @author Gaston Lions & Fabien Le Bec
 */

public class Bouton extends JButton{
	private int nbrClique;

/**
* Constructeur qui permet de créer un bouton.
*/
	public Bouton(){
		super();
		this.nbrClique = 0;
	}

/**
* Constructeur qui permet de créer un bouton à partir d'une image.
*
* @param icon l'image.
*/

	public Bouton(Icon icon){
		super();
		this.nbrClique = 0;
	}

/**
* Incrémente de 1 le nombre de clique.
*
*/
	public void setClique(){
		this.nbrClique++;
		if(this.nbrClique > 3){
			this.nbrClique = 0;
		}
	}

/**
* Change la valeur du nombre de clique selon x.
*
* @param x le nombre de cliques.
*/
	public void setClique(int x){
		this.nbrClique = x;
	}

/**
* Retourne le nombre de clique.
* 
* @return le nombre de clique.
*/

	public int getClique(){
		return this.nbrClique;
	}
}