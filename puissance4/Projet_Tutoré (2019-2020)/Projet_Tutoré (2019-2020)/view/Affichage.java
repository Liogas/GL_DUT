package view;

import model.*;
import model.joueur.*;
import java.util.Scanner;
/**
 * Affichage
 */
public class Affichage {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public Affichage(){}

    public void affichGrille(int[][][] grille) {
        int ligne = grille.length;
        int colonne = grille[0].length;
        int profondeur = grille[0][0].length;

        for(int i=0; i<ligne; i++){
            for(int k=0; k<profondeur; k++){
                for(int j=0; j<colonne; j++){
                    if(grille[i][j][k] == 0){
                        System.out.print(grille[i][j][k]);
                    } else if(grille[i][j][k] == 1){
                        System.out.print(ANSI_RED + grille[i][j][k] + ANSI_RESET);
                    } else if(grille[i][j][k] == 2){
                        System.out.print(ANSI_YELLOW + grille[i][j][k] + ANSI_RESET);    
                    }
                }
                System.out.print("    ");
            }
            System.out.println();
        }
    }

    public int[] choixJoueur(Joueur player){ //methode pour renvoyer le choix du joueur sous forme d'un tableau colonne/profondeur
        int choix[] = new int[2];
        Scanner sc = new Scanner(System.in);

        System.out.print(player.getCouleur() + " dans quelle colonne souhaitez-vous placer votre piece ? ");
        choix[0] = sc.nextInt();

        System.out.print("A quelle profondeur souhaitez-vous placer votre piece ? ");
        choix[1] = sc.nextInt();
        System.out.println();

        return choix;
    }

    public void colonnePleine(Joueur player, int colonne){
        System.out.println(player.getCouleur() + " la colonne "+colonne+" est déjà pleine.");
    }

    public void victoire(Joueur player){
        if(player == null){
            System.out.println("La grille est pleine, aucun vainquer !");
        } else {
            System.out.println(player.getCouleur() + " a gagné !");
        }
    }
}