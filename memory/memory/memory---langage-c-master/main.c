    /* Auteur : Gaston Lions */

    #include <stdio.h>
    #include <stdlib.h>
    #include <graph.h>
    #include <string.h>
    #include <time.h>

    #include "memory.h"

    int main(int argc, char *argv[]){

    	int choix, ligne, colonne, diff, nbrCases, i;
    	int tailleFX, tailleFY;
    	int partie = 1;
    	int choixF = 3;
    	int **memoireX, **memoireY;

    	Tableau **imageFind;
    	Tableau **image;

    	couleur ecran;
    	



    /* ------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    	InitialiserGraphique();
    	CreerFenetre(0, 0, RESOLUTIONX, RESOLUTIONY);
    	
    	
    	ecran = CouleurParNom("black");







    	choix = menu();
    	
    	switch(choix){
    		case 1 : 
    			
    			EffacerEcran(ecran);
    			ChargerImageFond("img/fondmenu.jpeg");
    			diff = choixDiff();
    			
    			switch(diff){
    				case 1 : 
    					FermerGraphique();
    					ligne = 4;
    					colonne = 4;
    					nbrCases = ligne * colonne;

    					image = malloc(nbrCases * sizeof(*image));
        				for(i = 0; i < ligne; i++){
        					image[i]=malloc((i+1) *sizeof(**image));
        				}

      
    					memoireY = malloc(nbrCases * sizeof(*memoireY));
        				for(i = 0; i < ligne; i++){
        					memoireY[i]=malloc((i+1) *sizeof(**memoireY));
        				}

    					memoireX = malloc(nbrCases * sizeof(*memoireX));
        				for(i = 0; i < ligne; i++){
        					memoireX[i]=malloc((i+1) *sizeof(**memoireX));
        				}	

    					imageFind = malloc(nbrCases * sizeof(*imageFind));
        				for(i = 0; i < ligne; i++){
        					imageFind[i]=malloc((i+1) *sizeof(**imageFind));
        				}


    					partie = grille(ligne, colonne, image, imageFind, memoireX, memoireY);
    					if(partie == 0){
    						tailleFX = colonne;
    						tailleFY = ligne;
    						choixF = Fin(ligne, colonne, ecran, tailleFX, tailleFY);	
    							free(image);
    								free(imageFind);
    								free(memoireY);
    								free(memoireX);
    						switch(choixF){
    							
    							case 1:
    								
    								return EXIT_SUCCESS;
    							break;
    						}
    					}
    					break;
    				case 2: 
    					FermerGraphique();
    					ligne = 5;
    					colonne = 4;
    					nbrCases = ligne * colonne;

    					image = malloc(nbrCases * sizeof(*image));
        				for(i = 0; i < ligne; i++){
        					image[i]=malloc((i+1) *sizeof(**image));
        				}

      
    					memoireY = malloc(nbrCases * sizeof(*memoireY));
        				for(i = 0; i < ligne; i++){
        					memoireY[i]=malloc((i+1) *sizeof(**memoireY));
        				}

    					memoireX = malloc(nbrCases * sizeof(*memoireX));
        				for(i = 0; i < ligne; i++){
        					memoireX[i]=malloc((i+1) *sizeof(**memoireX));
        				}	

    					imageFind = malloc(nbrCases * sizeof(*imageFind));
        				for(i = 0; i < ligne; i++){
        					imageFind[i]=malloc((i+1) *sizeof(**imageFind));
        				}



    					partie = grille(ligne, colonne, image, imageFind, memoireX, memoireY);
    					if(partie == 0){
    						tailleFX = colonne;
    						tailleFY = ligne;
    						choixF = Fin(ligne, colonne, ecran, tailleFX, tailleFY);
    						free(image);
    								free(imageFind);
    								free(memoireY);
    								free(memoireX);
    						switch(choixF){
    							case 1:
    								return EXIT_SUCCESS;
    							break;
    						}
    					}
    					break;
    				case 3 : 
    					FermerGraphique();
    					ligne = 4;
    					colonne = 3;
    					nbrCases = ligne * colonne;

    					image = malloc(nbrCases * sizeof(*image));
        				for(i = 0; i < ligne; i++){
        					image[i]=malloc((i+1) *sizeof(**image));
        				}

      
    					memoireY = malloc(nbrCases * sizeof(*memoireY));
        				for(i = 0; i < ligne; i++){
        					memoireY[i]=malloc((i+1) *sizeof(**memoireY));
        				}

    					memoireX = malloc(nbrCases * sizeof(*memoireX));
        				for(i = 0; i < ligne; i++){
        					memoireX[i]=malloc((i+1) *sizeof(**memoireX));
        				}	

    					imageFind = malloc(nbrCases * sizeof(*imageFind));
        				for(i = 0; i < ligne; i++){
        					imageFind[i]=malloc((i+1) *sizeof(**imageFind));
        				}


    					partie = grille(ligne, colonne, image, imageFind, memoireX, memoireY);
    					if(partie == 0){
    						tailleFX = colonne;
    						tailleFY = ligne;
    						choixF = Fin(ligne, colonne, ecran, tailleFX, tailleFY);
    							free(image);
    								free(imageFind);
    								free(memoireY);
    								free(memoireX);	
    						switch(choixF){
    							case 1:
    								
    								return EXIT_SUCCESS;
    							break;
    						}
    					}
    					break;
    			}	
    		break;



    			case 2: 
    			break;
    	}




    	FermerGraphique();

    	return EXIT_SUCCESS;
    }
