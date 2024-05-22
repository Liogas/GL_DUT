package model.evaluation;

import model.grille.*;
import model.joueur.*;
import model.joueur.JoueurHumain.*;
import model.*;

public class EvaluationPoint extends Evaluation {
    private Case[][][] tabCase;
    private int profondeur;
    private int ligne;
    private int colonne;
    private Partie jeu;
    private int scoreJ1 = 0;
    private int scoreJ2 = 0;
    private int score;
    private Joueur joueur;

    public EvaluationPoint(){
      score = 0;
      ligne = 5;
      colonne = 6;
      profondeur = 5;
      tabCase = new Case[ligne][colonne][profondeur];
    }

    @Override
    public int notation(Grille grille, Joueur j){
      jeu = grille.getPartie();
      scoreJ1 = 0;
      scoreJ2 = 0;
      score = 0;
      joueur = j;
      tabCase = grille.getTabCase();

        this.notationPosition(grille.getDernierCoup());
      return score;
    }

    public int scorePosition(int count, Couleur dc){
      if(dc == joueur.getCouleur()){
        if(count == 2){
          return 10;
        } else if(count == 3){
          return 100;
        } else if(count == 4){
          return 1000;
        }      
      } else {
        if(count == 2){
          return 5;
        } else if(count == 3){
          return 50;
        } else if(count == 4){
          return 500;
        }
      }
      return 0;
    }

    public int scoreBlocage(int count, Couleur dc){
      if(dc == joueur.getCouleur()){
        if(count == 1){
          return 5;
        } else if(count == 2){
          return 50;
        } else if(count == 3){
          return 500;
        }      
      } else {
        if(count == 1){
          return 10;
        } else if(count == 2){
          return 100;
        } else if(count == 3){
          return 1000;
        }
      }
      return 0;
    }

    public void notationPosition(Case dc){
      Case dernierCoup = dc;
      int dernierC = dernierCoup.getColonne();
      int dernierP = dernierCoup.getProfondeur();
      int count = 0;

      int l = dernierCoup.getLigne();
      do{
        count++;
        l++;
        if(l == ligne){
          break;
        }
      } while((tabCase[l][dernierC][dernierP].getEtat()) == (dernierCoup.getEtat()));
      
      if(dernierCoup.getEtat() == joueur.getCouleur()){
        if(count == 2){
          score -= 10;
        } else if(count == 3){
          score -= 100;
        } else if(count == 4){
          score -= 1000;
        }    
      } else {
        if(count == 2){
          score += 10;
        } else if(count == 3){
          score += 100;
        } else if(count == 4){
          score += 1000;
        }         
      }

      count = -1;
      l = dernierCoup.getLigne();
      do{
        count++;
        l++;
        if(l == ligne){
          break;
        }
      } while((tabCase[l][dernierC][dernierP].getEtat()) != (dernierCoup.getEtat()));

      if(dernierCoup.getEtat() == joueur.getCouleur()){
        if(count == 1){
          score -= 5;
        } else if(count == 2){
          score -= 50;
        } else if(count == 3){
          score -= 500;
        }     
      } else {
        if(count == 1){
          score += 5;
        } else if(count == 2){
          score += 50;
        } else if(count == 3){
          score += 500;
        }                
      }
    }

    public void notationHorizontal(){
      for(int k = 0; k < this.profondeur; k++){
        jeu.resetCompteur();
        for (int i = this.ligne-1; i >= 0; i--){
          jeu.resetCompteur();
          for (int j = 0; j < this.colonne; j++){
            if(tabCase[i][j][k].getEtat() == joueur.getCouleur()){
              jeu.resetCompteurJ2();
              jeu.addCompteurJ1();

              if((jeu.getCompteurJ1() == 2) && (tabCase[i][j+1][k].getEtat() == Couleur.BLANC)){
                score += 10;
              }
              if((jeu.getCompteurJ1() == 3) && (tabCase[i][j+1][k].getEtat() == Couleur.BLANC)){
                score += 100;
              }
              if((jeu.getCompteurJ1() == 4) && (tabCase[i][j+1][k].getEtat() == Couleur.BLANC)){
                score += 10000;
              }

            } else if(tabCase[i][j][k].getEtat() == Couleur.BLANC){
              jeu.resetCompteur();
            } else {
              jeu.resetCompteurJ1();
              jeu.addCompteurJ2();

              if((jeu.getCompteurJ2() == 2) && (tabCase[i][j+1][k].getEtat() == Couleur.BLANC)){
                score -= 10;
              }
              if((jeu.getCompteurJ2() == 3) && (tabCase[i][j+1][k].getEtat() == Couleur.BLANC)){
                score -= 100;
              }
              if((jeu.getCompteurJ2() == 4) && (tabCase[i][j+1][k].getEtat() == Couleur.BLANC)){
                score -= 10000;
              }              
            }
          }
        }
      }
    }

    public void notationVertical(){
      for(int k = 0; k < this.profondeur; k++){
        jeu.resetCompteur();
        for (int i = 0; i < this.colonne; i++){
          jeu.resetCompteur();
          for (int j = this.ligne-1; j >= 0; j--){
            if(tabCase[j][i][k].getEtat() == joueur.getCouleur()){
              jeu.resetCompteurJ2();
              jeu.addCompteurJ1();

              if((jeu.getCompteurJ1() == 2) && (tabCase[j-1][i][k].getEtat() == Couleur.BLANC)){
                score += 10;
              }
              if((jeu.getCompteurJ1() == 3) && (tabCase[j-1][i][k].getEtat() == Couleur.BLANC)){
                score += 100;
              }
              if((jeu.getCompteurJ1() == 4) && (tabCase[j-1][i][k].getEtat() == Couleur.BLANC)){
                score += 10000;
              }

            } else if(tabCase[j][i][k].getEtat() == Couleur.BLANC){
              jeu.resetCompteur();
            } else {
              jeu.resetCompteurJ1();
              jeu.addCompteurJ2();

              if((jeu.getCompteurJ2() == 2) && (tabCase[j-1][i][k].getEtat() == Couleur.BLANC)){
                score -= 10;
              }
              if((jeu.getCompteurJ2() == 3) && (tabCase[j-1][i][k].getEtat() == Couleur.BLANC)){
                score -= 100;
              }
              if((jeu.getCompteurJ2() == 4) && (tabCase[j-1][i][k].getEtat() == Couleur.BLANC)){
                score -= 10000;
              }              
            }
          }
        }
      }
    }    

    public void verifHorizontal(){
      for(int k = 0; k < this.profondeur; k++){
        jeu.resetCompteur();
        for (int i = 0; i < this.ligne; i++){
          jeu.resetCompteur();
          for (int j = 0; j < this.colonne; j++) {
            if(tabCase[i][j][k].getEtat() == Couleur.ROUGE){
              jeu.resetCompteurJ2();
              jeu.addCompteurJ1();


              if((jeu.getCompteurJ1() == 2) && (tabCase[i][j+1][k].getEtat() == Couleur.BLANC)){
                  if(joueur.getCouleur() == Couleur.ROUGE){
                    score += 10;
                  }
              }
              if((jeu.getCompteurJ1() == 3) && (tabCase[i][j+1][k].getEtat() == Couleur.BLANC)){
                if(joueur.getCouleur() == Couleur.ROUGE){
                    score += 100;
                }
              }
              if(jeu.getCompteurJ1() == 4){
                if(joueur.getCouleur() == Couleur.ROUGE){
                    score += 10000;
                }
              }
            }
            if(tabCase[i][j][k].getEtat() == Couleur.JAUNE){
              jeu.resetCompteurJ1();
              jeu.addCompteurJ2();
              if((jeu.getCompteurJ2() == 2) && (tabCase[i][j+1][k].getEtat() == Couleur.BLANC)){
                if(joueur.getCouleur() == Couleur.JAUNE){
                    score += 10;
                }
              }
              if((jeu.getCompteurJ2() == 3) && (tabCase[i][j+1][k].getEtat() == Couleur.BLANC)){
                if(joueur.getCouleur() == Couleur.JAUNE){
                    score += 100;
                }
              }
              if(jeu.getCompteurJ2() == 4){
                if(joueur.getCouleur() == Couleur.JAUNE){
                    score += 10000;
                }
              }
            }
            if(tabCase[i][j][k].getEtat() == Couleur.BLANC){
              jeu.resetCompteur();
            }
          }
        }
      }
    }

    //Vérifie si il y a un puissance 4 en vertical, renvoie la couleur du joueur sinon renvoie null
    public void verifVertical(){
      for(int k = 0; k < this.profondeur; k++){
        jeu.resetCompteur();
        for (int i = 0; i < this.colonne; i++){
          jeu.resetCompteur();
          for (int j = 0; j < this.ligne; j++) {
            if(tabCase[j][i][k].getEtat() == Couleur.ROUGE){
              jeu.resetCompteurJ2();
              jeu.addCompteurJ1();
              if((jeu.getCompteurJ1() == 2) && (tabCase[j+1][i][k].getEtat() == Couleur.BLANC)){
                if(joueur.getCouleur() == Couleur.ROUGE){
                    score += 10;
                }
              }
              if((jeu.getCompteurJ1() == 3) && (tabCase[j+1][i][k].getEtat() == Couleur.BLANC)){
                if(joueur.getCouleur() == Couleur.ROUGE){
                    score += 100;
                  }
              }
              if(jeu.getCompteurJ1() == 4){
                if(joueur.getCouleur() == Couleur.ROUGE){
                    score += 10000;
                  }
              }
            }
            if(tabCase[j][i][k].getEtat() == Couleur.JAUNE){
              jeu.resetCompteurJ1();
              jeu.addCompteurJ2();
              if((jeu.getCompteurJ2() == 2) && (tabCase[j+1][i][k].getEtat() == Couleur.BLANC)){
                if(joueur.getCouleur() == Couleur.JAUNE){
                    score += 10;
                  }
              }
              if((jeu.getCompteurJ2() == 3) && (tabCase[j+1][i][k].getEtat() == Couleur.BLANC)){
                if(joueur.getCouleur() == Couleur.JAUNE){
                    score += 100;
                  }
              }
              if(jeu.getCompteurJ2() == 4){
                if(joueur.getCouleur() == Couleur.JAUNE){
                    score += 10000;
                  }
              }
            }
            if(tabCase[j][i][k].getEtat() == Couleur.BLANC){
              jeu.resetCompteur();
            }
          }
        }
      }
    }

    //Vérifie si il y a un puissance 4 en profondeur, renvoie la couleur du joueur sinon renvoie null
    public void verifProfondeur(){
      for(int i = 0; i < this.ligne; i++){
        jeu.resetCompteur();
        for (int j = 0; j < this.colonne; j++){
          jeu.resetCompteur();
          for (int k = 0; k < this.profondeur; k++) {
            if(tabCase[i][j][k].getEtat() == Couleur.ROUGE){
              jeu.resetCompteurJ2();
              jeu.addCompteurJ1();
              if((jeu.getCompteurJ1() == 2) && (tabCase[i][j][k+1].getEtat() == Couleur.BLANC)){
                scoreJ1 += 10;
                scoreJ2 -= 10;
              }
              if((jeu.getCompteurJ1() == 3) && (tabCase[i][j][k+1].getEtat() == Couleur.BLANC)){
                scoreJ1 += 100;
                scoreJ2 -= 100;
              }
              if(jeu.getCompteurJ1() == 4){
                scoreJ1 += 1000;
                scoreJ2 -= 1000;
              }
            }
            if(tabCase[i][j][k].getEtat() == Couleur.JAUNE){
              jeu.resetCompteurJ1();
              jeu.addCompteurJ2();
              if((jeu.getCompteurJ2() == 2) && (tabCase[i][j][k+1].getEtat() == Couleur.BLANC)){
                scoreJ2 += 10;
                scoreJ1 -= 10;
              }
              if((jeu.getCompteurJ2() == 3) && (tabCase[i][j][k+1].getEtat() == Couleur.BLANC)){
                scoreJ2 += 100;
                scoreJ1 -= 100;
              }
              if(jeu.getCompteurJ2() == 4){
                scoreJ2 += 10000;
                scoreJ1 -= 10000;
              }
            }
            if(tabCase[i][j][k].getEtat() == Couleur.BLANC){
              jeu.resetCompteur();
            }
          }
        }
      }
    }

    //Vérifie si il y a un puissance 4 en diagonale (qui commence en bas à gauche et se finit en haut à droite), renvoie la couleur du joueur sinon renvoie null
    public void verifDiagonal1(){
      for(int k = 0; k < this.profondeur; k++){
        jeu.resetCompteur();
        for (int j = 0; j < this.colonne; j++){
          jeu.resetCompteur();
          for (int i = 0; i < this.ligne; i++){
            jeu.resetCompteur();
            
            for(int l = i, m = j; (l < this.ligne) && (m < this.colonne); l++, m++){
              if(tabCase[l][m][k].getEtat() == Couleur.ROUGE){
                jeu.resetCompteurJ2();
                jeu.addCompteurJ1();
                if((jeu.getCompteurJ1() == 2) && (tabCase[l+1][m+1][k].getEtat() == Couleur.BLANC)){
                  scoreJ1 += 10;
                  scoreJ2 -= 10;
                }
                if((jeu.getCompteurJ1() == 3) && (tabCase[l+1][m+1][k].getEtat() == Couleur.BLANC)){
                  scoreJ1 += 100;
                  scoreJ2 -= 100;
                }
                if(jeu.getCompteurJ1() == 4){
                  scoreJ1 += 10000;
                  scoreJ2 -= 10000;
                }
              }
              if(tabCase[l][m][k].getEtat() == Couleur.JAUNE){
                jeu.resetCompteurJ1();
                jeu.addCompteurJ2();
                if((jeu.getCompteurJ2() == 2) && (tabCase[l+1][m+1][k].getEtat() == Couleur.BLANC)){
                  scoreJ2 += 10;
                  scoreJ1 -= 10;
                }
                if((jeu.getCompteurJ2() == 3) && (tabCase[l+1][m+1][k].getEtat() == Couleur.BLANC)){
                  scoreJ2 += 100;
                  scoreJ1 -= 100;
                }
                if(jeu.getCompteurJ2() == 4){
                  scoreJ2 += 10000;
                  scoreJ1 -= 10000;
                }
              }
              if(tabCase[l][m][k].getEtat() == Couleur.BLANC){
                jeu.resetCompteur();
              }
            }
          }
        }
      }
    }

    //Vérifie si il y a un puissance 4 en diagonale (qui commence en bas à droite et se finit en haut à gauche), renvoie la couleur du joueur sinon renvoie null
    public void verifDiagonal2(){
      for(int k = 0; k < this.profondeur; k++){
        jeu.resetCompteur();
        for (int j = 0; j < this.colonne; j++){
          jeu.resetCompteur();
          for (int i = 0; i < this.ligne; i++){
            jeu.resetCompteur();
            
            for(int l = i, m = j; (l < this.ligne) && (m >= 0); l++, m--){
              if(tabCase[l][m][k].getEtat() == Couleur.ROUGE){
                jeu.resetCompteurJ2();
                jeu.addCompteurJ1();
                try{
                  if((jeu.getCompteurJ1() == 2) && (tabCase[l+1][m-1][k].getEtat() == Couleur.BLANC)){
                    scoreJ1 += 10;
                    scoreJ2 -= 10;
                  }
                }catch(IndexOutOfBoundsException e){
                  scoreJ1 += 0;
                  scoreJ2 -= 0;
                }
                if((jeu.getCompteurJ1() == 3) && (tabCase[l+1][m-1][k].getEtat() == Couleur.BLANC)){
                  scoreJ1 += 100;
                  scoreJ2 -= 100;
                }
                if(jeu.getCompteurJ1() == 4){
                  scoreJ1 += 10000;
                  scoreJ2 -= 10000;
                }
              }
              if(tabCase[l][m][k].getEtat() == Couleur.JAUNE){
                jeu.resetCompteurJ1();
                jeu.addCompteurJ2();
                try{
                  if((jeu.getCompteurJ2() == 2) && (tabCase[l+1][m-1][k].getEtat() == Couleur.BLANC)){
                    scoreJ2 += 10;
                    scoreJ1 -= 10;
                  }
                }catch(IndexOutOfBoundsException exception){
                  scoreJ2 += 0;
                  scoreJ1 -= 0;
                }
                try{
                  if((jeu.getCompteurJ2() == 3) && (tabCase[l+1][m-1][k].getEtat() == Couleur.BLANC)){
                    scoreJ2 += 100;
                    scoreJ1 -= 100;
                  }
                }catch(IndexOutOfBoundsException except){
                  scoreJ2 += 0;
                  scoreJ1 -= 0;
                }
                if(jeu.getCompteurJ2() == 4){
                  scoreJ2 += 10000;
                  scoreJ1 -= 10000;
                }
              }
              if(tabCase[l][m][k].getEtat() == Couleur.BLANC){
                jeu.resetCompteur();
              }
            }
          }
        }
      }
    }
  
      //Vérifie si il y a un puissance 4 en diagonale et en profondeur (qui commence en bas d'une profondeur et se finit en haut 4 profondeurs plus loin), renvoie la couleur du joueur sinon renvoie null
      public void verifDiagonalProfondeur1(){
        for(int k = 0; k < this.profondeur; k++){
          jeu.resetCompteur();
          for (int j = 0; j < this.colonne; j++){
            jeu.resetCompteur();
            for (int i = 0; i < this.ligne; i++){
              jeu.resetCompteur();
              
              for(int l = i, m = k; (l >= 0) && (m < this.profondeur); l--, m++){
                if(tabCase[l][j][m].getEtat() == Couleur.ROUGE){
                  jeu.resetCompteurJ2();
                  jeu.addCompteurJ1();
                  try{
                    if((jeu.getCompteurJ1() == 2) && (tabCase[l-1][j][m+1].getEtat() == Couleur.BLANC)){
                      scoreJ1 += 10;
                      scoreJ2 -= 10;
                    }
                  }catch(IndexOutOfBoundsException exception){
                    scoreJ1 += 0;
                    scoreJ2 -= 0;
                  }
                  try{
                    if((jeu.getCompteurJ1() == 3) && (tabCase[l-1][j][m+1].getEtat() == Couleur.BLANC)){
                      scoreJ1 += 100;
                      scoreJ2 -= 100;
                    }
                  }catch(IndexOutOfBoundsException e){
                    scoreJ1 += 0;
                    scoreJ2 -= 0;
                  }
                  if(jeu.getCompteurJ1() == 4){
                    scoreJ1 += 10000;
                    scoreJ2 -= 10000;
                  }
                }
                if(tabCase[l][j][m].getEtat() == Couleur.JAUNE){
                  jeu.resetCompteurJ1();
                  jeu.addCompteurJ2();
                  try{
                    if((jeu.getCompteurJ2() == 2) && (tabCase[l-1][j][m+1].getEtat() == Couleur.BLANC)){
                      scoreJ2 += 10;
                      scoreJ1 -= 10;
                    }
                  }catch(IndexOutOfBoundsException excep){
                    scoreJ2 += 0;
                    scoreJ1 -= 0;
                  }
                  try{
                    if((jeu.getCompteurJ2() == 3) && (tabCase[l-1][j][m+1].getEtat() == Couleur.BLANC)){
                      scoreJ2 += 100;
                      scoreJ1 -= 100;
                    }
                  }catch(IndexOutOfBoundsException ex){
                    scoreJ2 += 0;
                    scoreJ1 -= 0;
                  }
                  if(jeu.getCompteurJ2() == 4){
                    scoreJ2 += 10000;
                    scoreJ1 -= 10000;
                  }
                }
                if(tabCase[l][j][m].getEtat() == Couleur.BLANC){
                  jeu.resetCompteur();
                }              
              }
            }
          }
        }
      }
  
      //Vérifie si il y a un puissance 4 en diagonale et en profondeur (qui commence en haut d'une profondeur et se finit en bas 4 profondeurs plus loin), renvoie la couleur du joueur sinon renvoie null
      public void verifDiagonalProfondeur2(){
        for(int k = 0; k < this.profondeur; k++){
          jeu.resetCompteur();
          for (int j = 0; j < this.colonne; j++){
            jeu.resetCompteur();
            for (int i = 0; i < this.ligne; i++){
              jeu.resetCompteur();
              
              for(int l = i, m = k; (l < this.ligne) && (m < this.profondeur); l++, m++){
                if(tabCase[l][j][m].getEtat() == Couleur.ROUGE){
                  jeu.resetCompteurJ2();
                  jeu.addCompteurJ1();
                  if((jeu.getCompteurJ1() == 2) && (tabCase[l+1][j][m+1].getEtat() == Couleur.BLANC)){
                    scoreJ1 += 10;
                    scoreJ2 -= 10;
                  }
                  if((jeu.getCompteurJ1() == 3) && (tabCase[l+1][j][m+1].getEtat() == Couleur.BLANC)){
                    scoreJ1 += 100;
                    scoreJ2 -= 100;
                  }
                  if(jeu.getCompteurJ1() == 4){
                    scoreJ1 += 10000;
                    scoreJ2 -= 10000;
                  }
                }
                if(tabCase[l][j][m].getEtat() == Couleur.JAUNE){
                  jeu.resetCompteurJ1();
                  jeu.addCompteurJ2();
                  if((jeu.getCompteurJ2() == 2) && (tabCase[l+1][j][m+1].getEtat() == Couleur.BLANC)){
                    scoreJ2 += 10;
                    scoreJ1 -= 10;
                  }
                  if((jeu.getCompteurJ2() == 3) && (tabCase[l+1][j][m+1].getEtat() == Couleur.BLANC)){
                    scoreJ2 += 100;
                    scoreJ1 -= 100;
                  }
                  if(jeu.getCompteurJ2() == 4){
                    scoreJ2 += 10000;
                    scoreJ1 -= 10000;
                  }
                }
                if(tabCase[l][j][m].getEtat() == Couleur.BLANC){
                  jeu.resetCompteur();
                }
              }
            }
          }
        }
      }
}