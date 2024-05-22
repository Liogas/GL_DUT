package view;

import model.*;
import controller.*;

import javax.swing.*;
import java.awt.*;

public class DessinAide extends JComponent{

	private FenetreAide fenetre;
	private ModelAide model;

	public DessinAide(){}

	public DessinAide(FenetreAide f){
		fenetre = f;
		model = new ModelAide(fenetre);
	}	


	public void paintComponent(Graphics g){

		int posXBloc1 = model.getPosXBloc(1);
		int posYBloc1 = model.getPosYBloc(1);

		int posXBloc2 = model.getPosXBloc(2);
		int posYBloc2 = model.getPosYBloc(2);

		int posXBloc4 = model.getPosXBloc(4);
		int posYBloc4 = model.getPosYBloc(4);

		int posXBloc5 = model.getPosXBloc(5);
		int posYBloc5 = model.getPosYBloc(5);

		int posXBloc6 = model.getPosXBloc(6);
		int posYBloc6 = model.getPosYBloc(6);

		int posXBloc7 = model.getPosXBloc(7);
		int posYBloc7 = model.getPosYBloc(7);

	 	
		Graphics secondPinceau = g.create();

		secondPinceau.setColor(Color.WHITE);
		secondPinceau.fillRect(0,0,fenetre.getWidth(), fenetre.getHeight());

		secondPinceau.setColor(Color.BLACK);
			
		secondPinceau.drawString(model.getTexte(1), posXBloc1, posYBloc1);

			secondPinceau.drawString(model.getTexte(2), posXBloc2, posYBloc2);

		// DESSIN DES GRILLES
			for(int i = 0; i < 5; i++){
				secondPinceau.drawRect(model.getPosXGrille(i), model.getPosYGrille(i), model.getLargeurGrille(), model.getHauteurGrille());

					secondPinceau.fillOval(model.getPosXCerclePlein(), model.getPosYCerclePlein(i), model.getLargeurCercle(), model.getHauteurCercle());

					secondPinceau.drawOval(model.getPosXCercleVide(), model.getPosYCercleVide(), model.getLargeurCercle(), model.getHauteurCercle());
			}

			secondPinceau.drawString(model.getTexte(3), posXBloc4, posYBloc4);

			secondPinceau.drawString(model.getTexte(4), posXBloc5, posYBloc5);
			secondPinceau.drawString(model.getTexte(5), posXBloc6, posYBloc6);
	
			secondPinceau.drawString(model.getTexte(6), posXBloc7, posYBloc7);
	}


	public ModelAide getModel(){
		return model;
	}

}