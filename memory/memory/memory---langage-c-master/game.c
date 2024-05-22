    #include <stdio.h>
    #include <stdlib.h>
    #include <graph.h>
    #include <string.h>
    #include <time.h>

    #include "memory.h"


    int grille(int ligne, int colonne, Tableau **image, Tableau **imageFind, int **memoireX, int **memoireY){

    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    	int i, j;
    	int positionY = 40;
    	int positionX = ECART;
    	int tailleFenetreX = colonne * ECART;
    	int tailleFenetreY = ligne * ECART;
    	int nbrCases = ligne * colonne;
    	int ent, max, y, z, find;
    	char cheminPhoto[10];
    	int partie = 1;

    	max = nbrCases / 2;

    	InitialiserGraphique();
    	CreerFenetre(RESOLUTIONX / 2, RESOLUTIONY / 2, tailleFenetreX + ECART * 2, tailleFenetreY+ ECART * 2);
    	ChargerImageFond("img/fondmenu.jpeg");
    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

        srand(time(NULL));

    	for(i = 0; i < ligne; i++){
    		for(j = 0; j < colonne; j++){
    			ent = (rand() % (max - MIN + 1)) + MIN;
    			do{
        		find = 0;
        		ent = (rand() % (max - MIN + 1)) + MIN;
        			for(y = 0; y < ligne; y++){
        				for(z = 0; z < colonne; z++){
        					if(image[y][z].id == ent){
        						find++;
        					}
        				}
        			}
        		}
        		while(find == 2);
        		image[i][j].id = ent;
        	}
        }

    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

      	for(i = 0; i < ligne; i++){
        	for(j = 0; j < colonne; j++){


        		sprintf(cheminPhoto,"img/%d.png", image[i][j].id);	
        		 

        		ChargerImage(cheminPhoto, positionX, positionY, 0, 0,TAILLE_CARRE,TAILLE_CARRE);
        		positionX += ECART;

        		memoireX[i][j] = positionX - ECART;
        		memoireY[i][j] = positionY;
        	}
        	positionY += ECART;
        	positionX = ECART;
        }
        positionY = 40;


    	for(i = 0; i < ligne; i++){
    		for(j = 0; j < colonne; j++){
    			ChargerImage("img/dos.png", positionX, positionY, 0, 0, TAILLE_CARRE, TAILLE_CARRE);
    			imageFind[i][j].etat = 0;
    			positionX += ECART;
    		}
    		positionX = ECART;
    		positionY += ECART;
    	}
    	positionY = 40;

    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    while(partie == 1){
    	
    	partie = DeroulementPartie(ligne, colonne, positionX, positionY, image, imageFind, memoireX, memoireY, tailleFenetreX, tailleFenetreY);

    }

    return partie;
    }

    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    int DeroulementPartie(int ligne, int colonne, int positionX, int positionY, Tableau **image, Tableau **imageFind, int **memoireX, int **memoireY, int tailleFenetreX, int tailleFenetreY){

    int i, j;
    int triche = 0;
    char cheminPhoto[100];
    int carteR = 0;
    int trouve = 0;
    int partie = 1;

    char chronometre[1000];
    int seconde;
    int nettoyage = Microsecondes();
    int timer = 0;
    int minute = 0;
    int sup = 1;
    couleur colorie;
    int t;



    Image carte1, carte2;	


    while(1){



    	if(triche == 0 && partie == 1){
    	seconde = chrono(seconde, nettoyage);

    		if(seconde >= sup){
    			timer++;
    			nettoyage = Microsecondes();
    			if(timer == 60){
    				minute++;
    				timer = 0;
    			}
    			sprintf(chronometre, "TIMER : %02d:%02d", minute, timer);
    			colorie = CouleurParNom("black");
        		ChoisirCouleurDessin(colorie);
        		RemplirRectangle( 0, 0 , 130, 50);
    			colorie = CouleurParNom("red");
    			ChoisirCouleurDessin(colorie);
    			EcrireTexte( 20, 15, chronometre, 1);
    		}
    	}



    	if(ToucheEnAttente()){
        	if(Touche() == XK_t){
        		if(triche == 0){
        			triche = 1;

        			for(i = 0; i < ligne; i++){
        				for(j = 0; j < colonne; j++){
        					sprintf(cheminPhoto, "img/%d.png", image[i][j].id);
        					ChargerImage(cheminPhoto, positionX, positionY, 0, 0,TAILLE_CARRE,TAILLE_CARRE); 
        					positionX += ECART;
        				}
        				positionX = ECART;
        				positionY += ECART;
        			}
        			positionY = 40;

        		} else {
        			triche = 0;
        			for(i = 0; i < ligne; i++){
        				for(j = 0; j < colonne; j++){
        					if(imageFind[i][j].etat == 1){
        						sprintf(cheminPhoto, "img/%d.png", image[i][j].id);
        						ChargerImage(cheminPhoto, positionX, positionY, 0, 0,TAILLE_CARRE,TAILLE_CARRE);
        					} else {
        						ChargerImage("img/dos.png", positionX, positionY, 0, 0, TAILLE_CARRE, TAILLE_CARRE);
        					}
        					positionX += ECART;
        				}
        				positionX = ECART;
        				positionY += ECART;
        			}
        			positionY = 40;
        		}
    		}
    	}


    	/*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    	if(SourisCliquee() == 1 && triche == 0){
    		for(j = 0; j < colonne; j++){
    			if(_X >= positionX + ECART * j && _X <= positionX + TAILLE_CARRE + ECART  * j){
    				for(i = 0; i < ligne; i++){
    					if(_Y >= positionY + ECART * i && _Y <= positionY + TAILLE_CARRE + ECART  * i && imageFind[i][j].etat == 0){
    						carteR++;
    						sprintf(cheminPhoto, "img/%d.png", image[i][j].id);
    						ChargerImage(cheminPhoto, memoireX[i][j], memoireY[i][j], 0, 0, TAILLE_CARRE, TAILLE_CARRE);

    						
    						if(carteR == 1){
    							carte1.id = image[i][j].id;
    							carte1.x = memoireX[i][j];
    							carte1.y = memoireY[i][j];
    							carte1.c = j;
    							carte1.l = i;
    							imageFind[i][j].etat = 1;
    						} else {

    							if(carteR == 2){
    								carte2.id = image[i][j].id;
    								carte2.x = memoireX[i][j];
    								carte2.y = memoireY[i][j];
    								carte2.c = j;
    								carte2.l = i;
    								imageFind[i][j].etat = 1;
    							}
    						}

    							if(carteR == 2){
    								if(carte2.id == carte1.id && (carte1.x != carte2.x || carte1.y != carte2.y)){
    									printf("dedd\n");
    									for(i = 0; i < ligne; i++){
    										for(j = 0; j < colonne; j++){
    											if(imageFind[i][j].etat == 1){
    												trouve++;
    												if(trouve == ligne * colonne){
    													partie = 0;
    													return partie;
    												}
    											}
    										}
    									}
    									
    								} else {
    									int a = Microsecondes();
    									t = Microsecondes() + SECONDE;
    									while(t > a){
    										a = Microsecondes();
    										seconde = chrono(seconde, nettoyage);
    										if(seconde >= sup){
    											timer++;
    											nettoyage = Microsecondes();
    											if(timer == 60){
    												minute++;
    												timer = 0;
    												}
    												sprintf(chronometre, "TIMER : %02d:%02d", minute, timer);
    												colorie = CouleurParNom("black");
        											ChoisirCouleurDessin(colorie);
        											RemplirRectangle( 0, 0 , 130, 50);
    												colorie = CouleurParNom("red");
    												ChoisirCouleurDessin(colorie);
    												EcrireTexte( 20, 15, chronometre, 1);
    											
    										}
    									}
    									ChargerImage("img/dos.png", carte1.x, carte1.y, 0, 0,TAILLE_CARRE,TAILLE_CARRE);
        								ChargerImage("img/dos.png", carte2.x, carte2.y, 0, 0,TAILLE_CARRE,TAILLE_CARRE);
        								imageFind[carte1.l][carte1.c].etat = 0;
        								imageFind[carte2.l][carte2.c].etat = 0;
    								}
    								carteR = 0;
    								trouve = 0;
    							}
    						}
    					}
    				}
    			}
    		}



    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    	}
    }



    int chrono(int seconde, int nettoyage){

    	seconde = Microsecondes() - nettoyage;
        seconde = seconde / SECONDE;

    	return seconde;
    }



    int Fin(int colonne, int ligne, couleur ecran, int tailleX, int tailleY){
    	couleur colorie;
    	int choix = 0;

    	colorie = CouleurParNom("white");
    	ChoisirCouleurDessin(colorie);

    	EffacerEcran(ecran);

    	ChargerImage("img/quitterf.png", tailleX / 4 + 500 / 2,  tailleY / 2 + 250 / 2 , 0, 0, 500, 250);

    while(choix == 0){
    	if(SourisCliquee()){
    		if(_X >= tailleX / 4 + 500 / 2  && _X <= tailleX / 4 + 500 / 2 + 500 && _Y >= tailleY / 2 + 250 / 2 && _Y <= tailleY / 2  + 250 / 2 + 250){
    			choix = 1;
    		}
    	}
    }

    return choix;
    }
