package model.algorithme;

import java.util.*;
import model.algorithme.*;

public class Arbre {
 
	private NoeudArbre racine;

	public Arbre(NoeudArbre racine){
		this.racine = racine;
	}

	public NoeudArbre getRacine(){
		return racine;
	}

	public static int profondeurArbre(NoeudArbre noeud){
		if(noeud == null){
			return 0;
		}

		int profondeurmax = 0;
		for(NoeudArbre child : noeud.getChilds()){
			if(profondeurmax == 1){
				//child.addChild(new NoeudArbre(0,0,0));
			}
			profondeurmax = Math.max(profondeurmax, profondeurArbre(child));
		}

		return profondeurmax+1;
	}

	/*public static void afficherArbre(int profmax, int niveau, NoeudArbre noeud){
		int tabulation = (int) Math.pow(2.0,(double)(profmax-niveau))-1;
		int espace = (int) Math.pow(2.0,(double)(profmax-niveau+1))-1;

		for(int i = 0; i < tabulation; i++){
			System.out.print(" ");
		}

		for(NoeudArbre child : noeud.getChilds()){
			System.out.print("("+noeud.toString()+")");

			for(int s = 0; s < espace; s++){
				System.out.print("_");
			}
		}

		System.out.println();
	}*/

	public static void afficherArbre(int niveau, NoeudArbre noeud){
	    for (int i = 1; i <= niveau; i++) {
	        System.out.print("\t");
	    }
	    System.out.println("("+noeud.toString()+")");
	    for (NoeudArbre child : noeud.getChilds()) {
	        afficherArbre(niveau + 1, child);
	    }
	}	

	public static void main(String[] args){
		int grille[][] = new int[3][3];
		//Arbre a = new Arbre(new NoeudArbre(0,0,0));

		for(int l = 0; l < grille.length; l++){
			for(int c = 0; c < grille[l].length; c++){
				//NoeudArbre n = new NoeudArbre(l,c,0);
				//a.getRacine().addChild(n);
				if(l == 2){
					//n.addChild(new NoeudArbre(l,c,0));
				}
				if(c == 1){
					//n.addChild(new NoeudArbre(l,c,0));
				}
			}
		}

		//afficherArbre(0,a.getRacine());
	}
}