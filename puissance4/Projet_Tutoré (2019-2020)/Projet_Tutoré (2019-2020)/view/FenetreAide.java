package view;

import controller.listener.*;

import javax.swing.*;
import java.awt.*;

public class FenetreAide extends JFrame{

	private DessinAide dessin;

	public FenetreAide(){
		setTitle("Aide");
		setLocation(0,0);
		setSize(700,700);
		setMinimumSize(new Dimension(700,700));
		setMaximumSize(new Dimension(700,700));

		dessin = new DessinAide(this);
		add(dessin);
		addWindowListener(new ActionFenetre(this));
	}

	public static void main(String[] args){
		FenetreAide f = new FenetreAide();
		f.setVisible(true);
	}
}