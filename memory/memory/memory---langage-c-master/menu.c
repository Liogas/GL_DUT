#include <stdio.h>
#include <stdlib.h>
#include <graph.h>
#include <string.h>
#include <time.h>

#include "memory.h"


int menu(){

	int tailleX, tailleY;
	int positionX, positionY;
	int ecart, choix;

	tailleX = 250;
	tailleY = 125;

	positionX = RESOLUTIONX - 500;
	positionY = RESOLUTIONY - 640;

	

	ecart = 200;


	ChargerImageFond("img/fondmenu.jpeg");
	ChargerImage("img/jouer.png", positionX, positionY, 0, 0, tailleX, tailleY);
	ChargerImage("img/quitter.png", positionX, positionY + ecart, 0, 0, tailleX, tailleY);


	while(1){
		if(SourisCliquee()){
			if(_X >= positionX && _Y >= positionY && _X <= positionX + tailleX && _Y <= positionY + tailleY){
				printf("Vous avez cliquer\n");
				choix = 1;
				break;
			}

			if(_X >= positionX && _Y >= positionY + ecart && _X <= positionX + tailleX && _Y <= positionY + tailleY + ecart){
				printf("Vous avez cliquer\n");
				choix = 2;
				break;
			}
		}
	}
	return choix;
}
/*---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

/*---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
/*---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

/*---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
int choixDiff(){
	
	int positionX = RESOLUTIONX - 400;
	int positionY = 100;
	int tailleX = 100;
	int tailleY = 50;
	int diff;

	ChargerImage("img/4x4.png", positionX, positionY, 0, 0, 100, 50);
	ChargerImage("img/5x4.png", positionX, positionY + 100, 0, 0, 100, 50);
	ChargerImage("img/3x4.png", positionX, positionY + 200, 0, 0, 100, 50);

	while(1){

		if(SourisCliquee()){

			if(_X >= positionX && _Y >= positionY && _X <= positionX + tailleX && _Y <= positionY + tailleY){
				diff = 1;
				break;
			}

			if(_X >= positionX && _Y >= positionY + 100 && _X <= positionX + tailleX && _Y <= positionY + 100 + tailleY){
				diff = 2;
				break;
			}

			if(_X >= positionX && _Y >= positionY + 200 && _X <= positionX + tailleX && _Y <= positionY + 200 + tailleY){
				diff = 3;
				break;
			}
		}
	}
	return diff;
}
