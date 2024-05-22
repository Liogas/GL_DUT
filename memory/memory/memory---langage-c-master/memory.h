#ifndef NIVEAU_H

#define NIVEAU_H

#define RESOLUTIONX 1280
#define RESOLUTIONY 720

#define ECART 130 /* Ecart entre les cases */

#define TAILLE_CARRE 100 /* Taille de la case */
#define POSITION_X 400
#define POSITION_Y 90
#define MIN 1
#define SECONDE 1000000;


/* Struct utilisé pour initialisé les grilles */
typedef struct tab Tableau;
struct tab{
	int id;
	int etat;
};


/* Struct utilisé lors des cliques */
typedef struct image Image;
struct image
{
	int id;
	int x;
	int y;
	int c;
	int l;
	int etat;
};


int menu();
/* Fonction utilisé pour afficher le menu puis renvois le choix fait*/
int choixDiff();
/* Fonction qui affiche le menu des difficultés et renvois la difficulté */

int grille(int ligne, int colonne, Tableau **image, Tableau **imageFind, int **memoireX, int **memoireY);
/* Fonction qui initialise les tableaux (grilles) puis les affiche à l'écran (tableau face cachée par dessus celui avec les images retournées), revois 0 quand la partie est terminée */

int DeroulementPartie(int ligne, int colonne, int positionX, int positionY, Tableau **image, Tableau **imageFind, int **memoireX, int **memoireY, int tailleFenetreX, int tailleFenetreY);
/* Fonction qui contient les fonctionnabilité de la partie (mode triche, clique de la souris, timer), revoie 0 à la fonction grille lorsque la partie est terminée */ 

int Fin(int colonne, int ligne, couleur ecran, int taileX, int tailleY);
/* Fonction qui affiche l'écran de fin lorsque l'on a trouvé toutes les cartes du jeux */


int chrono(int seconde, int nettoyage);
/* Fonction permettant de récupérer le temps écoulé depuis le démarage du programme */


#endif 