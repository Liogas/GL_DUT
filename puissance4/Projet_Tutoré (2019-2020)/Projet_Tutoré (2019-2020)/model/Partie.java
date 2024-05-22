package model;

import view.*;
import model.grille.*;
import model.joueur.*;
import controller.jeu.*;

/**
 * Partie
 */
public class Partie {
  
    private int compteurj1;
    private int compteurj2;
    private Joueur[] joueurs;
    private Joueur joueurActuel;
    private int tourDeJeu;
    private Grille grille;
    private Joueur vainqueur;
    private Jeu jeu;

    public Partie(Joueur[] j){
      this.joueurs = j;
      this.tourDeJeu = 0;
      grille = null;
      vainqueur = null;
    }

    public void nouveauTour(){
      tourDeJeu++;
      joueurActuel = joueurSuivant();
      if(joueurActuel instanceof JoueurIA){
        int[] caseJouer = ((JoueurIA)joueurActuel).jouer(this.grille);
        jeu.jouer(caseJouer[0],caseJouer[1]);
      }
    }

    public int getTourDeJeu(){
      return tourDeJeu;
    }

    public Joueur getJoueurActuel(){
      return joueurActuel;
    }

    public Joueur joueurSuivant(){
      if(joueurActuel == joueurs[0]){
        return joueurs[1];
      } else {
        return joueurs[0];
      }      
    }
 
    public Joueur[] getJoueurs(){
      return joueurs;
    }


    public void resetCompteur(){
      compteurj1 = 0;
      compteurj2 = 0;
    }

    public void resetCompteurJ1(){
      compteurj1 = 0;
    }

    public void resetCompteurJ2(){
      compteurj2 = 0;
    }

    public void addCompteurJ2(){
      compteurj2++;
    }

    public void addCompteurJ1(){
      compteurj1++;
    }

    public int getCompteurJ1(){
      return compteurj1;
    }

    public int getCompteurJ2(){
      return compteurj2;
    }

    public void setGrille(Grille g){
      grille = g;
    }

    public Grille getGrille(){
      return grille;
    }

    public Joueur getVainqueur(){
      return vainqueur;
    }

    public void setVainqueur(Joueur j){
      vainqueur = j;
    }

    public void setController(Jeu j){
      this.jeu = j;
    }    

}