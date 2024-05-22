package view;

import model.*;
import model.grille.*;

import javax.swing.*;
import java.awt.*;

public class DessinGrille extends JComponent{

	public final static int VIDE = 0;
    public final static int ROUGE = 1;
    public final static int JAUNE = 2;  

	private Partie partie;

	private Fenetre fenetre;

	private int largeurGrille;
	private int hauteurGrille;

	private InfoGrille infoGrille;
    private int compteurL = 0;
    private int compteurC = 0;

	public DessinGrille(Fenetre f, Partie j){
		partie = j;
		fenetre = f;
		infoGrille = new InfoGrille(fenetre, partie);
	}	

	public void paintComponent(Graphics g){
	 	
		Graphics secondPinceau = g.create();
		int i,j;

		hauteurGrille = infoGrille.getHauteurGrille();
		largeurGrille = infoGrille.getLargeurGrille();

      	secondPinceau.setColor(Color.WHITE);
      	secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());

        if(infoGrille.getAffichage()==0){

            if(partie.getJoueurActuel().getCouleur() == Couleur.JAUNE){
                secondPinceau.setColor(new Color( 251, 251, 0 ));
            } else {
                secondPinceau.setColor(Color.RED);
            }
            secondPinceau.fillOval(10,10,infoGrille.getLargeurCercle(), infoGrille.getHauteurCercle());
            secondPinceau.setColor(Color.BLACK);
            secondPinceau.drawOval(10,10,infoGrille.getLargeurCercle(), infoGrille.getHauteurCercle());

        	for(i = 0 ; i < infoGrille.getNbrProfondeur(); i++){

        		infoGrille.initPos(i);
    	    	secondPinceau.setColor(new Color(72, 99, 207));    
        		secondPinceau.fillRect(infoGrille.getPosX(), infoGrille.getPosY(), largeurGrille, hauteurGrille);
        		secondPinceau.setColor(new Color(255,255,255));
        		secondPinceau.drawRect(infoGrille.getPosX(), infoGrille.getPosY(), largeurGrille, hauteurGrille);

        		secondPinceau.setColor(Color.BLACK);
                if(i == infoGrille.getNbrProfondeur()-1){
        		  secondPinceau.drawString("Profondeur n°"+(infoGrille.getNbrProfondeur()-i), infoGrille.getPosX() + infoGrille.getLargeurGrille() + infoGrille.getLargeurCercle()/2, infoGrille.getPosY() + hauteurGrille + 20);
                } else {
                  secondPinceau.drawString("Profondeur n°"+(infoGrille.getNbrProfondeur()-i), infoGrille.getPosX() + infoGrille.getLargeurGrille()/3, infoGrille.getPosY() + hauteurGrille + 20);
                }

        		// Affichage des cercles

        		infoGrille.initCercleX();
        		infoGrille.initCercleY();

        		for(j = 0; j < infoGrille.getNbrLigne(); j++){
        			for(int k = 0; k < infoGrille.getNbrColonne(); k++){
                        if(i == infoGrille.getNbrProfondeur()-1 && compteurC == 0){
                            // Affichage rectangle colonne
                            secondPinceau.setColor(Color.RED);
                            secondPinceau.fillRect(infoGrille.getCercleX()+infoGrille.getLargeurCercle()/2, infoGrille.getPosY()+infoGrille.getHauteurGrille()+ infoGrille.getLargeurCercle()/3, infoGrille.getLargeurCercle()/3, infoGrille.getLargeurCercle()/3);
                            compteurC++;
                        }

                        // Dessin du cercle provisoire
                        if(infoGrille.getCerclePB() && infoGrille.getCerclePX() == k && infoGrille.getCerclePZ() == i){
                            if(partie.getJoueurActuel().getCouleur() == Couleur.ROUGE){
                                secondPinceau.setColor(Color.RED);
                            } else {
                                secondPinceau.setColor(Color.YELLOW);
                            }
                            secondPinceau.fillOval(infoGrille.getDebutX()+largeurGrille*i+infoGrille.getLargeurCercle()*infoGrille.getCerclePX()+6, infoGrille.getDebutY()-infoGrille.getHauteurCercle()+hauteurGrille*(4-i), infoGrille.getLargeurCercle(), infoGrille.getHauteurCercle());
                        }

        				if(partie.getGrille().getEtatTabCase(j,k,4-i).getEtat() == Couleur.BLANC){
        					secondPinceau.setColor(new Color(255,255,255));
        				} else if(partie.getGrille().getEtatTabCase(j,k,4-i).getEtat() == Couleur.JAUNE){
        					secondPinceau.setColor(new Color( 251, 251, 0 ));
        				} else if(partie.getGrille().getEtatTabCase(j,k,4-i).getEtat() == Couleur.ROUGE){
        					secondPinceau.setColor(Color.RED);
        				} else {
                            secondPinceau.setColor(Color.GREEN);
                        }
        				secondPinceau.fillOval(infoGrille.getCercleX(), infoGrille.getCercleY(),infoGrille.getLargeurCercle(), infoGrille.getHauteurCercle());

                        if(partie.getGrille().getEtatTabCase(j,k,4-i) ==  partie.getGrille().getDernierCoup()){
                        secondPinceau.setColor(new Color(0,0,0));
                            secondPinceau.drawOval(infoGrille.getCercleX(), infoGrille.getCercleY(),infoGrille.getLargeurCercle(), infoGrille.getHauteurCercle());
                            secondPinceau.drawOval(infoGrille.getCercleX()-1, infoGrille.getCercleY()-1,infoGrille.getLargeurCercle()+2, infoGrille.getHauteurCercle()+2);
                            secondPinceau.drawOval(infoGrille.getCercleX()-2, infoGrille.getCercleY()-2,infoGrille.getLargeurCercle()+2, infoGrille.getHauteurCercle()+4);
                        } 
        				infoGrille.decalageCercleX();
                        compteurC = 0;
        			}
                    compteurL = 0;
        			infoGrille.decalageCercleY();
        			infoGrille.initCercleX();
        		}
        		infoGrille.initCercleY();
    		}

        } else if(infoGrille.getAffichage()==1){

            secondPinceau.setColor(new Color(72, 99, 207));    
            secondPinceau.fillRect(fenetre.getWidth()/2-largeurGrille, fenetre.getHeight()/2-hauteurGrille, largeurGrille*2 - infoGrille.getLargeurCercle()*2, hauteurGrille*2);
            secondPinceau.setColor(new Color(255,255,255));
            secondPinceau.drawRect(fenetre.getWidth()/2-largeurGrille, fenetre.getHeight()/2-hauteurGrille, largeurGrille*2 - infoGrille.getLargeurCercle()*2, hauteurGrille*2);

            secondPinceau.setColor(Color.BLACK);
            secondPinceau.drawString("Vue de la colonne n°"+(infoGrille.getColonneAAfficher()+1)+" (de coté)", infoGrille.getPosX() + 20, infoGrille.getPosY() + hauteurGrille + 20);

            // Affichage des cercles
            int posX = fenetre.getWidth()/2-largeurGrille;
            int posY = fenetre.getHeight()/2-hauteurGrille;

            for(j = 0; j < infoGrille.getNbrProfondeur(); j++){
                for(int k = 0; k < infoGrille.getNbrLigne(); k++){
                    if(partie.getGrille().getEtatTabCase(j,infoGrille.getColonneAAfficher(),k).getEtat() == Couleur.BLANC){
                        secondPinceau.setColor(new Color(255,255,255));
                    } else if(partie.getGrille().getEtatTabCase(j,infoGrille.getColonneAAfficher(),k).getEtat() == Couleur.JAUNE){
                        secondPinceau.setColor(new Color( 251, 251, 0 ));
                    } else if(partie.getGrille().getEtatTabCase(j,infoGrille.getColonneAAfficher(),k).getEtat() == Couleur.ROUGE){
                        secondPinceau.setColor(Color.RED);
                    } else {
                        secondPinceau.setColor(Color.GREEN);
                    }
                    secondPinceau.fillOval(posX+12, posY+12,infoGrille.getLargeurCercle()*2, infoGrille.getHauteurCercle()*2);

                    if(partie.getGrille().getEtatTabCase(j,infoGrille.getColonneAAfficher(),k) ==  partie.getGrille().getDernierCoup()){
                        secondPinceau.setColor(new Color(0,0,0));
                        secondPinceau.drawOval(posX+12, posY+12,infoGrille.getLargeurCercle()*2, infoGrille.getHauteurCercle()*2);
                        secondPinceau.drawOval(posX+11, posY+11,infoGrille.getLargeurCercle()*2+2, infoGrille.getHauteurCercle()*2+2);
                    } 

                    posX += infoGrille.getLargeurCercle()*2;
                }
                posY+=infoGrille.getHauteurCercle()*2;
                posX = fenetre.getWidth()/2-largeurGrille;
            }
            posY = fenetre.getHeight()/2-hauteurGrille;

            // ELEMENT PERMETTANT DE REVENIR LA VUE D'ENSEMBLE DES PROFONDEURS
            //secondPinceau.fillRect(10,10,100,100);
        }

		// Transition
    	if(infoGrille.getEtatTransition() == true){
    		if(partie.getJoueurActuel().getCouleur() == Couleur.JAUNE){
    			secondPinceau.setColor(Color.RED);
    		} else {
	    		secondPinceau.setColor(Color.YELLOW);
    		}
    		secondPinceau.fillOval(infoGrille.getPosXTransition(), infoGrille.getPosYTransition(),infoGrille.getLargeurCercle(), infoGrille.getHauteurCercle());
    	}

        // Affichage gagnant de la partie si il existe
        if(partie.getVainqueur() != null){
            secondPinceau.setColor(Color.BLUE);
            secondPinceau.drawString(partie.getVainqueur().getCouleur()+" a gagné la partie en "+partie.getTourDeJeu()+" tours !", fenetre.getWidth()-fenetre.getWidth()/3, fenetre.getHeight()/2);
            secondPinceau.drawString("Combinaison gagnante affichée en verte", fenetre.getWidth()-fenetre.getWidth()/3, fenetre.getHeight()/2+fenetre.getHeight()/18);
            secondPinceau.setColor(Color.BLACK);
            secondPinceau.fillRect(fenetre.getWidth()-fenetre.getWidth()/3, fenetre.getHeight()/2+fenetre.getHeight()/15, fenetre.getWidth()/2-fenetre.getWidth()/3,fenetre.getHeight()/20);
            secondPinceau.setColor(Color.WHITE);
            secondPinceau.drawString("Menu principal", fenetre.getWidth()-fenetre.getWidth()/3 + ((fenetre.getWidth()/2-fenetre.getWidth()/3)/4), fenetre.getHeight()/2+fenetre.getHeight()/15 + (fenetre.getHeight()/40));
        }

	}



	public InfoGrille getModelGrille(){
		return infoGrille;
	}
 
}