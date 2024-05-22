package model.grille;

import model.evaluation.*;
import model.joueur.*;
import model.*;

import java.util.ArrayList;
import java.util.Random; 

public class Grille{

    private Case[][][] tabCase;
    private int profondeur;
    private int ligne;
    private int colonne;
    private Partie jeu;
    private Case dernierCoup;
    private Couleur vainqueur;

    public Grille(Partie j){
        ligne = 5;
        colonne = 6;
        profondeur = 5;
        tabCase = new Case[ligne][colonne][profondeur];
        jeu = j;
        dernierCoup = null;
        vainqueur = null;
        initGrille();
    }

    public void initGrille(){
        for(int i = 0; i < ligne; i++){
            for(int j = 0; j<colonne;j++){
                for(int k=0;k<profondeur;k++){
                    createCase(i,j,k,Couleur.BLANC);
                }
            }
        }
    }

    public int getNbrLigne(){
        return ligne;
      }
  
      public int getNbrColonne(){
        return colonne;
      }
  
    public int getNbrProfondeur(){
      return profondeur;
    }

    public Case[][][] getTabCase(){
      return tabCase;
    }    

    public Case getCase(int c, int p){
      for(int l = this.ligne-1; l >= 0; l--){
        if(l == 0){
          return tabCase[l][c][p];
        }
        if(tabCase[l-1][c][p].getEtat() == Couleur.BLANC){
          return tabCase[l][c][p];
        }
      }
      return null;
    }

    private void createCase(int l, int c, int p, Couleur color){
      tabCase[l][c][p] = new Case(l,c,p);
      tabCase[l][c][p].changerEtat(color);
    }

      //Vérifie si il y a un puissance 4 en horizontal, renvoie la couleur du joueur sinon renvoie null
    public Couleur verifHorizontal(){
        for(int k = 0; k < this.profondeur; k++){
          jeu.resetCompteur();
          for (int i = 0; i < this.ligne; i++){
            jeu.resetCompteur();
            for (int j = 0; j < this.colonne; j++) {


              Couleur c = tabCase[i][j][k].getEtat();

              if(c == Couleur.ROUGE) {
                jeu.resetCompteurJ2();
                jeu.addCompteurJ1();
              }
              if(c == Couleur.JAUNE) {
                jeu.resetCompteurJ1();
                jeu.addCompteurJ2();
              }
              if(c == Couleur.BLANC) {
                jeu.resetCompteur();
              }

              if(jeu.getCompteurJ1() == 4 || jeu.getCompteurJ2() == 4) {
                tabCase[i][j][k].changerEtat(Couleur.VERT);
                tabCase[i][j-1][k].changerEtat(Couleur.VERT);
                tabCase[i][j-2][k].changerEtat(Couleur.VERT);
                tabCase[i][j-3][k].changerEtat(Couleur.VERT);
                return c;
              }

              /*if(tabCase[i][j][k].getEtat() == Couleur.ROUGE){
                jeu.resetCompteurJ2();
                jeu.addCompteurJ1();
                if(jeu.getCompteurJ1() == 4){
                  tabCase[i][j][k].changerEtat(Couleur.VERT);
                  tabCase[i][j-1][k].changerEtat(Couleur.VERT);
                  tabCase[i][j-2][k].changerEtat(Couleur.VERT);
                  tabCase[i][j-3][k].changerEtat(Couleur.VERT);
                  return Couleur.ROUGE;
                }
              }
              if(tabCase[i][j][k].getEtat() == Couleur.JAUNE){
                jeu.resetCompteurJ1();
                jeu.addCompteurJ2();
                if(jeu.getCompteurJ2() == 4){
                  tabCase[i][j][k].changerEtat(Couleur.VERT);
                  tabCase[i][j-1][k].changerEtat(Couleur.VERT);
                  tabCase[i][j-2][k].changerEtat(Couleur.VERT);
                  tabCase[i][j-3][k].changerEtat(Couleur.VERT);
                  return Couleur.JAUNE;
                }
              }
              if(tabCase[i][j][k].getEtat() == Couleur.BLANC){
                jeu.resetCompteur();
              }*/
            }
          }
        }
        return null;
      }
  
      //Vérifie si il y a un puissance 4 en vertical, renvoie la couleur du joueur sinon renvoie null
      public Couleur verifVertical(){
        for(int k = 0; k < this.profondeur; k++){
          jeu.resetCompteur();
          for (int i = 0; i < this.colonne; i++){
            jeu.resetCompteur();
            for (int j = 0; j < this.ligne; j++) {

              Couleur c = tabCase[j][i][k].getEtat();

              if(c == Couleur.ROUGE) {
                jeu.resetCompteurJ2();
                jeu.addCompteurJ1();
              }
              if(c == Couleur.JAUNE) {
                jeu.resetCompteurJ1();
                jeu.addCompteurJ2();
              }
              if(c == Couleur.BLANC) {
                jeu.resetCompteur();
              }

              if(jeu.getCompteurJ1() == 4 || jeu.getCompteurJ2() == 4) {
                tabCase[j][i][k].changerEtat(Couleur.VERT);
                tabCase[j-1][i][k].changerEtat(Couleur.VERT);
                tabCase[j-2][i][k].changerEtat(Couleur.VERT);
                tabCase[j-3][i][k].changerEtat(Couleur.VERT);
                return c;
              }/*


              if(tabCase[j][i][k].getEtat() == Couleur.ROUGE){
                jeu.resetCompteurJ2();
                jeu.addCompteurJ1();
                if(jeu.getCompteurJ1() == 4){
                  tabCase[j][i][k].changerEtat(Couleur.VERT);
                  tabCase[j-1][i][k].changerEtat(Couleur.VERT);
                  tabCase[j-2][i][k].changerEtat(Couleur.VERT);
                  tabCase[j-3][i][k].changerEtat(Couleur.VERT);
                  return Couleur.ROUGE;
                }
              }
              if(tabCase[j][i][k].getEtat() == Couleur.JAUNE){
                jeu.resetCompteurJ1();
                jeu.addCompteurJ2();
                if(jeu.getCompteurJ2() == 4){
                  tabCase[j][i][k].changerEtat(Couleur.VERT);
                  tabCase[j-1][i][k].changerEtat(Couleur.VERT);
                  tabCase[j-2][i][k].changerEtat(Couleur.VERT);
                  tabCase[j-3][i][k].changerEtat(Couleur.VERT);
                  return Couleur.JAUNE;
                }
              }
              if(tabCase[j][i][k].getEtat() == Couleur.BLANC){
                jeu.resetCompteur();
              }*/
            }
          }
        }
        return null;
      }
  
      //Vérifie si il y a un puissance 4 en profondeur, renvoie la couleur du joueur sinon renvoie null
      public Couleur verifProfondeur(){
        for(int i = 0; i < this.ligne; i++){
          jeu.resetCompteur();
          for (int j = 0; j < this.colonne; j++){
            jeu.resetCompteur();
            for (int k = 0; k < this.profondeur; k++) {


              Couleur c = tabCase[i][j][k].getEtat();

              if(c == Couleur.ROUGE) {
                jeu.resetCompteurJ2();
                jeu.addCompteurJ1();
              }
              if(c == Couleur.JAUNE) {
                jeu.resetCompteurJ1();
                jeu.addCompteurJ2();
              }
              if(c == Couleur.BLANC) {
                jeu.resetCompteur();
              }

              if(jeu.getCompteurJ1() == 4 || jeu.getCompteurJ2() == 4) {
                tabCase[i][j][k].changerEtat(Couleur.VERT);
                tabCase[i][j][k-1].changerEtat(Couleur.VERT);
                tabCase[i][j][k-2].changerEtat(Couleur.VERT);
                tabCase[i][j][k-3].changerEtat(Couleur.VERT);
                return c;
              }/*
              if(tabCase[i][j][k].getEtat() == Couleur.ROUGE){
                jeu.resetCompteurJ2();
                jeu.addCompteurJ1();
                if(jeu.getCompteurJ1() == 4){
                  tabCase[i][j][k].changerEtat(Couleur.VERT);
                  tabCase[i][j][k-1].changerEtat(Couleur.VERT);
                  tabCase[i][j][k-2].changerEtat(Couleur.VERT);
                  tabCase[i][j][k-3].changerEtat(Couleur.VERT);
                  return Couleur.ROUGE;
                }
              }
              if(tabCase[i][j][k].getEtat() == Couleur.JAUNE){
                jeu.resetCompteurJ1();
                jeu.addCompteurJ2();
                if(jeu.getCompteurJ2() == 4){
                  tabCase[i][j][k].changerEtat(Couleur.VERT);
                  tabCase[i][j][k-1].changerEtat(Couleur.VERT);
                  tabCase[i][j][k-2].changerEtat(Couleur.VERT);
                  tabCase[i][j][k-3].changerEtat(Couleur.VERT);
                  return Couleur.JAUNE;
                }
              }
              if(tabCase[i][j][k].getEtat() == Couleur.BLANC){
                jeu.resetCompteur();
              }*/
            }
          }
        }
        return null;      
      }
  
      //Vérifie si il y a un puissance 4 en diagonale (qui commence en bas à gauche et se finit en haut à droite), renvoie la couleur du joueur sinon renvoie null
      public Couleur verifDiagonal1(){
        for(int k = 0; k < this.profondeur; k++){
          jeu.resetCompteur();
          for (int j = 0; j < this.colonne; j++){
            jeu.resetCompteur();
            for (int i = 0; i < this.ligne; i++){
              jeu.resetCompteur();
              
              for(int l = i, m = j; (l >= 0) && (m < this.colonne); l--, m++){
                
                Couleur c = tabCase[l][m][k].getEtat();

                if(c == Couleur.ROUGE) {
                  jeu.resetCompteurJ2();
                  jeu.addCompteurJ1();
                }
                if(c == Couleur.JAUNE) {
                  jeu.resetCompteurJ1();
                  jeu.addCompteurJ2();
                }
                if(c == Couleur.BLANC) {
                  jeu.resetCompteur();
                }

                if(jeu.getCompteurJ1() == 4 || jeu.getCompteurJ2() == 4) {
                  tabCase[l][m][k].changerEtat(Couleur.VERT);
                  tabCase[l+1][m-1][k].changerEtat(Couleur.VERT);
                  tabCase[l+2][m-2][k].changerEtat(Couleur.VERT);
                  tabCase[l+3][m-3][k].changerEtat(Couleur.VERT);
                  return c;
                }

/*                if(jeu.getCompteurJ1() == 4) {
                  c = Couleur.ROUGE;
                } else if(jeu.getCompteurJ2() == 4){
                  c = Couleur.JAUNE;
                }

                if(tabCase[l][m][k].getEtat() == Couleur.ROUGE){
                  jeu.resetCompteurJ2();
                  jeu.addCompteurJ1();
                  if(jeu.getCompteurJ1() == 4){
                    tabCase[l][m][k].changerEtat(Couleur.VERT);
                    tabCase[l+1][m-1][k].changerEtat(Couleur.VERT);
                    tabCase[l+2][m-2][k].changerEtat(Couleur.VERT);
                    tabCase[l+3][m-3][k].changerEtat(Couleur.VERT);
                    return Couleur.ROUGE;
                  }
                }
                if(tabCase[l][m][k].getEtat() == Couleur.JAUNE){
                  jeu.resetCompteurJ1();
                  jeu.addCompteurJ2();
                  if(jeu.getCompteurJ2() == 4){
                    tabCase[l][m][k].changerEtat(Couleur.VERT);
                    tabCase[l+1][m-1][k].changerEtat(Couleur.VERT);
                    tabCase[l+2][m-2][k].changerEtat(Couleur.VERT);
                    tabCase[l+3][m-3][k].changerEtat(Couleur.VERT);
                    return Couleur.JAUNE;
                  }
                }
                if(tabCase[l][m][k].getEtat() == Couleur.BLANC){
                  jeu.resetCompteur();
                }*/
              }
            }
          }
        }
        return null;          
      }
  
      //Vérifie si il y a un puissance 4 en diagonale (qui commence en bas à droite et se finit en haut à gauche), renvoie la couleur du joueur sinon renvoie null
      public Couleur verifDiagonal2(){
        for(int k = 0; k < this.profondeur; k++){
          jeu.resetCompteur();
          for (int j = 0; j < this.colonne; j++){
            jeu.resetCompteur();
            for (int i = 0; i < this.ligne; i++){
              jeu.resetCompteur();
              
              for(int l = i, m = j; (l >= 0) && (m >= 0); l--, m--){

                Couleur c = tabCase[l][m][k].getEtat();

                if(c == Couleur.ROUGE) {
                  jeu.resetCompteurJ2();
                  jeu.addCompteurJ1();
                }
                if(c == Couleur.JAUNE) {
                  jeu.resetCompteurJ1();
                  jeu.addCompteurJ2();
                }
                if(c == Couleur.BLANC) {
                  jeu.resetCompteur();
                }

                if(jeu.getCompteurJ1() == 4 || jeu.getCompteurJ2() == 4) {
                  tabCase[l][m][k].changerEtat(Couleur.VERT);
                  tabCase[l+1][m+1][k].changerEtat(Couleur.VERT);
                  tabCase[l+2][m+2][k].changerEtat(Couleur.VERT);
                  tabCase[l+3][m+3][k].changerEtat(Couleur.VERT);
                  return c;
                }/*
                if(tabCase[l][m][k].getEtat() == Couleur.ROUGE){
                  jeu.resetCompteurJ2();
                  jeu.addCompteurJ1();
                  if(jeu.getCompteurJ1() == 4){
                    /*tabCase[l][m][k].changerEtat(Couleur.VERT);
                    tabCase[l-1][m+1][k].changerEtat(Couleur.VERT);
                    tabCase[l-2][m+2][k].changerEtat(Couleur.VERT);
                    tabCase[l-3][m+3][k].changerEtat(Couleur.VERT);
                    return Couleur.ROUGE;
                  }
                }
                if(tabCase[l][m][k].getEtat() == Couleur.JAUNE){
                  jeu.resetCompteurJ1();
                  jeu.addCompteurJ2();
                  if(jeu.getCompteurJ2() == 4){
                    tabCase[l][m][k].changerEtat(Couleur.VERT);
                    tabCase[l-1][m+1][k].changerEtat(Couleur.VERT);
                    tabCase[l-2][m+2][k].changerEtat(Couleur.VERT);
                    tabCase[l-3][m+3][k].changerEtat(Couleur.VERT);
                    return Couleur.JAUNE;
                  }
                }
                if(tabCase[l][m][k].getEtat() == Couleur.BLANC){
                  jeu.resetCompteur();
                }*/
              }
            }
          }
        }
        return null;          
      }
  
      //Vérifie si il y a un puissance 4 en diagonale et en profondeur (qui commence en bas d'une profondeur et se finit en haut 4 profondeurs plus loin), renvoie la couleur du joueur sinon renvoie null
      public Couleur verifDiagonalProfondeur1(){
        for(int k = 0; k < this.profondeur; k++){
          jeu.resetCompteur();
          for (int j = 0; j < this.colonne; j++){
            jeu.resetCompteur();
            for (int i = 0; i < this.ligne; i++){
              jeu.resetCompteur();
              
              for(int l = i, m = k; (l >= 0) && (m < this.profondeur); l--, m++){

                Couleur c = tabCase[l][j][m].getEtat();

                if(c == Couleur.ROUGE) {
                  jeu.resetCompteurJ2();
                  jeu.addCompteurJ1();
                }
                if(c == Couleur.JAUNE) {
                  jeu.resetCompteurJ1();
                  jeu.addCompteurJ2();
                }
                if(c == Couleur.BLANC) {
                  jeu.resetCompteur();
                }

                if(jeu.getCompteurJ1() == 4 || jeu.getCompteurJ2() == 4) {
                  tabCase[l][j][m].changerEtat(Couleur.VERT);
                  tabCase[l+1][j][m-1].changerEtat(Couleur.VERT);
                  tabCase[l+2][j][m-2].changerEtat(Couleur.VERT);
                  tabCase[l+3][j][m-3].changerEtat(Couleur.VERT);
                  return c;
                }/*
                if(tabCase[l][j][m].getEtat() == Couleur.ROUGE){
                  jeu.resetCompteurJ2();
                  jeu.addCompteurJ1();
                  if(jeu.getCompteurJ1() == 4){
                    tabCase[l][j][m].changerEtat(Couleur.VERT);
                    tabCase[l-1][j-1][m-1].changerEtat(Couleur.VERT);
                    tabCase[l-2][j-2][m-2].changerEtat(Couleur.VERT);
                    tabCase[l-3][j-3][m-3].changerEtat(Couleur.VERT);
                    return Couleur.ROUGE;
                  }
                }
                if(tabCase[l][j][m].getEtat() == Couleur.JAUNE){
                  jeu.resetCompteurJ1();
                  jeu.addCompteurJ2();
                  if(jeu.getCompteurJ2() == 4){
                    tabCase[l][j][m].changerEtat(Couleur.VERT);
                    tabCase[l-1][j-1][m-1].changerEtat(Couleur.VERT);
                    tabCase[l-2][j-2][m-2].changerEtat(Couleur.VERT);
                    tabCase[l-3][j-3][m-3].changerEtat(Couleur.VERT);
                    return Couleur.JAUNE;
                  }
                }
                if(tabCase[l][j][m].getEtat() == Couleur.BLANC){
                  jeu.resetCompteur();
                }*/              
              }
            }
          }
        }
        return null;          
      }
  
      //Vérifie si il y a un puissance 4 en diagonale et en profondeur (qui commence en haut d'une profondeur et se finit en bas 4 profondeurs plus loin), renvoie la couleur du joueur sinon renvoie null
      public Couleur verifDiagonalProfondeur2(){
        for(int k = 0; k < this.profondeur; k++){
          jeu.resetCompteur();
          for (int j = 0; j < this.colonne; j++){
            jeu.resetCompteur();
            for (int i = 0; i < this.ligne; i++){
              jeu.resetCompteur();
              
              for(int l = i, m = k; (l < this.ligne) && (m < this.profondeur); l++, m++){

                Couleur c = tabCase[l][j][m].getEtat();

                if(c == Couleur.ROUGE) {
                  jeu.resetCompteurJ2();
                  jeu.addCompteurJ1();
                }
                if(c == Couleur.JAUNE) {
                  jeu.resetCompteurJ1();
                  jeu.addCompteurJ2();
                }
                if(c == Couleur.BLANC) {
                  jeu.resetCompteur();
                }

                if(jeu.getCompteurJ1() == 4 || jeu.getCompteurJ2() == 4) {
                  tabCase[l][j][m].changerEtat(Couleur.VERT);
                  tabCase[l-1][j][m-1].changerEtat(Couleur.VERT);
                  tabCase[l-2][j][m-2].changerEtat(Couleur.VERT);
                  tabCase[l-3][j][m-3].changerEtat(Couleur.VERT);
                  return c;
                }/*

                if(tabCase[l][j][m].getEtat() == Couleur.ROUGE){
                  jeu.resetCompteurJ2();
                  jeu.addCompteurJ1();
                  if(jeu.getCompteurJ1() == 4){
                    tabCase[l][j][m].changerEtat(Couleur.VERT);
                    tabCase[l-1][j+1][m-1].changerEtat(Couleur.VERT);
                    tabCase[l-2][j+2][m-2].changerEtat(Couleur.VERT);
                    tabCase[l-3][j+3][m-3].changerEtat(Couleur.VERT);
                    return Couleur.ROUGE;
                  }
                }
                if(tabCase[l][j][m].getEtat() == Couleur.JAUNE){
                  jeu.resetCompteurJ1();
                  jeu.addCompteurJ2();
                  if(jeu.getCompteurJ2() == 4){
                    /*tabCase[l][j][m].changerEtat(Couleur.VERT);
                    tabCase[l-1][j+1][m-1].changerEtat(Couleur.VERT);
                    tabCase[l-2][j+2][m-2].changerEtat(Couleur.VERT);
                    tabCase[l-3][j+3][m-3].changerEtat(Couleur.VERT);
                    return Couleur.JAUNE;
                  }
                }
                if(tabCase[l][j][m].getEtat() == Couleur.BLANC){
                  jeu.resetCompteur();
                }*/
              }
            }
          }
        }
        return null;          
      }

      public int inserer(int c, int p, Joueur j){ //renvoie 0 si colonne pleine
        if(verifColonne(c, p)){
          return 0;
        } else {
          for(int i = ligne; i > 0; i--){
            if(tabCase[i-1][c][p].getEtat() == Couleur.BLANC){
              tabCase[i-1][c][p].changerEtat(j.getCouleur());
              dernierCoup = tabCase[i-1][c][p];
              break;
            }
          }
        }
        return 1;
      }

    public Partie getPartie(){
      return jeu;
    }

    //Convertie la Grille qui est un tableau de case en un tableau de int pour les méthodes d'évaluation
    public int[][][] toInt(){
      int[][][] grilleInt = new int[this.ligne][this.colonne][this.profondeur];

      for(int l = 0; l < this.ligne; l++){
        for(int c = 0; c < this.colonne; c++){
          for(int p = 0; p < this.profondeur; p++){
            Couleur caseC = getEtatTabCase(l,c,p).getEtat();
            if(caseC == Couleur.BLANC){
              grilleInt[l][c][p] = 0;
            } else if(caseC == Couleur.ROUGE){
              grilleInt[l][c][p] = 1;
            } else if(caseC == Couleur.JAUNE){
              grilleInt[l][c][p] = 2;
            }
          }
        }
      }
      return grilleInt;
    }

    public Case getEtatTabCase(int l, int c, int p){
        return tabCase[l][c][p];
    }

    public boolean verifColonne(int c, int p){
      if(tabCase[0][c][p].getEtat() == Couleur.BLANC){
        return false;
      } else {
        return true;
      }
    }

    public boolean isFull(){
      for(int prof = 0; prof < this.profondeur; prof++){
        for(int col = 0; col < this.colonne; col++){
          for(int line = 0; line < this.ligne; line++){
            if(this.tabCase[line][col][prof].getEtat() == Couleur.BLANC){
              return false;
            }
          }
        }
      }
      return true;
    }

    public Couleur qqunAgagne(){
      if(this.isFull()){
        vainqueur = Couleur.BLANC;
        return vainqueur; //retourne blanc si match nul
      }
      if((vainqueur = this.verifVertical()) != null){
        return vainqueur;
      } else if((vainqueur = this.verifHorizontal()) != null){
        return vainqueur;
      } else if((vainqueur = this.verifProfondeur()) != null){
        return vainqueur;
      } else if((vainqueur = this.verifDiagonal1()) != null){
        return vainqueur;
      } else if((vainqueur = this.verifDiagonal2()) != null){
        return vainqueur;
      } else if((vainqueur = this.verifDiagonalProfondeur1()) != null){
        return vainqueur;
      } else if((vainqueur = this.verifDiagonalProfondeur2()) != null){
        return vainqueur;
      }
      return null;
    }

    public int nombreAlignementPion(int tailleAlignement, Couleur couleur){
        int nbrAlignement =0;

        for(int p = 0; p < profondeur; p++){
          for(int l = 0; l < ligne; l++){
            for(int c = 0; c < colonne; c++){
              if(alignementPion(tailleAlignement, couleur, l, c, p, 0, 1, 0) ){nbrAlignement++;}
              if(alignementPion(tailleAlignement, couleur, l, c, p, 1, 1, 0) ){nbrAlignement++;}
              if(alignementPion(tailleAlignement, couleur, l, c, p, 1, 0, 0) ){nbrAlignement++;}
              if(alignementPion(tailleAlignement, couleur, l, c, p, -1, 1, 0) ){nbrAlignement++;}
              if(alignementPion(tailleAlignement, couleur, l, c, p, 0, 0, 1) ){nbrAlignement++;}
              if(alignementPion(tailleAlignement, couleur, l, c, p, 0, 1, 1) ){nbrAlignement++;}
            }
          }
        }

        return nbrAlignement;
    }    

    public boolean alignementPion(int tailleAlignement, Couleur couleur, int l, int c, int p, int permutationX, int permutationY, int permutationZ){
        boolean alignement = true;
        while(tailleAlignement != 0 && alignement){
          Case pion = null;

          try{
            pion = getEtatTabCase(l,c,p);
          } catch (ArrayIndexOutOfBoundsException e){
            alignement = false;
          }

          if(pion == null || !pion.getEtat().equals(couleur)){
            alignement = false;
          }

          l += permutationY;
          c += permutationX;
          p += permutationZ;

          tailleAlignement--;
        }
        return alignement;
    }

    //Retourne un ArrayList contenant chaque coups possibles représentés par un tableau d'int de taille 2 (colonne et profondeur)
    public ArrayList<int[]> coupsPossibles(){
      ArrayList<int[]> coupsPossibles = new ArrayList<int[]>();

      for(int c = 0; c < colonne; c++){
        for(int p = 0; p < profondeur; p++){
          if(!this.verifColonne(c,p)){
            int[] cp = {c,p};
            coupsPossibles.add(cp);
          }
        }
      }
      return coupsPossibles;
    }

    public int evaluer(Evaluation e, Joueur j){
      return e.notation(this,j);
    }

    public Grille copier(){
      Grille copie = new Grille(jeu);

      for(int i = 0; i < ligne; i++){
          for(int j = 0; j<colonne;j++){
              for(int k=0;k<profondeur;k++){
                copie.createCase(i,j,k,this.getEtatTabCase(i,j,k).getEtat());
              }
          }
      }
      return copie;
    }

    //Affiche dans la console la grille (principalement pour débugger)
    public void toText(){
      String ANSI_RESET = "\u001B[0m";
      String ANSI_RED = "\u001B[31m";
      String ANSI_YELLOW = "\u001B[33m";

      for(int i=0; i<ligne; i++){
          for(int k=0; k<profondeur; k++){
              for(int j=0; j<colonne; j++){
                  if(tabCase[i][j][k].getEtat() == Couleur.BLANC){
                      System.out.print("V");
                  } else if(tabCase[i][j][k].getEtat() == Couleur.ROUGE){
                      System.out.print(ANSI_RED +"X"+ ANSI_RESET);
                  } else if(tabCase[i][j][k].getEtat() == Couleur.JAUNE){
                      System.out.print(ANSI_YELLOW +"O"+ ANSI_RESET);    
                  }
              }
              System.out.print("    ");
          }
          System.out.println();
      }     
    }

    public Case getDernierCoup(){
      return dernierCoup;
    }

    public Couleur simuler(Joueur joueur){
      Couleur resultat = null;
      Random rand = new Random();
      Joueur j1 = jeu.getJoueurs()[0];
      Joueur j2 = jeu.getJoueurs()[1];
      Joueur jActuel = joueur;

      while(resultat == null){
        this.inserer(rand.nextInt(colonne),rand.nextInt(profondeur), jActuel);
        resultat = this.qqunAgagne();

        if(jActuel == j1){
          jActuel = j2;
        } else {
          jActuel = j1;
        }
      }

      return resultat;
    }

    //utilisé pour simulations, retourne null si partie pas fini, blanc si match nul, sinon retourne couleur vainqueur
    public Couleur getVainqueur(){
      return vainqueur;
    }
}