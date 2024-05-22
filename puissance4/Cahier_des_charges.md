## Cahier des charges ([lien vers le Git](https://dwarves.iut-fbleau.fr/git/desousaa/Projet_Tuteure))

### Sommaire
1. Présentation du projet
2. Structure de données
3. Spécifications fonctionnelles
4. Spécifications techniques
5. Diagrammes 


-----
### Présentation du projet:
-----

Dans le cadre de notre DUT Informatique nous sommes ammenés à réaliser un projet informatique dont l'échéance est février 2020. Notre groupe est constitué de quatre personnes :
* Fabien LE BEC
* Yanis DE SOUSA ALVES,
* Mark SOUS, 
* Gaston LIONS.

Notre tuteur de projet est l'enseignant-chercheur Pierre VALARCHER. Il consiste en l'élaboration d'un jeu "puissance 4" en trois dimensions ainsi qu'une intelligence artificielle contre laquelle l'utilisateur pourra jouer. Optionnellement (si nous avons assez de temps), l'utilisateur pourra jouer en réseau contre un autre utilisateur.



-----
### Structure de données
-----

Pour réaliser un puissance 4 en trois dimensions nous avons décidé d'utiliser un tableau de trois dimensions pour représenter les données. La première dimension représentera les lignes, la deuxième les colonnes et la troisième la profondeur. Chaque case d'un tableau comprendra une donnée qui sera un int compris entre 0 et 2 tel que :
* 0 : représente une case qui ne contient aucun jeton
* 1 : représente une case qui contient un jeton rouge
* 2 : représente une case qui contient un jeton jaune

Par exemple, pour un plateau de jeu de 5 lignes, 6 colonnes et 5 de profondeurs : `plateau[5][6][5]` on aurait une représentation comme suivant :

```
         Profondeur 0    Profondeur 1    Profondeur 2    Profondeur 3    Profondeur 4

            0 0 0 0 0 0    0 0 0 0 0 0    0 1 0 0 0 0    0 0 0 0 0 0    0 0 0 0 0 0			
            0 0 0 0 0 0    0 0 0 0 0 0    0 2 0 0 0 0    0 0 0 0 0 0    0 0 0 0 0 0			
            0 0 0 0 0 0    0 0 0 0 0 1    0 2 0 0 0 0    0 0 0 0 0 0    0 0 0 0 0 0			
            0 0 2 0 0 0    0 0 0 0 0 1    0 1 0 0 0 0    0 0 0 2 2 0    0 0 0 1 0 0			
            0 1 2 0 0 0    2 0 0 0 0 1    0 2 0 0 0 0    0 0 2 1 1 1    0 2 0 1 0 2	
              ^                                                                   ^
              |                                                                   |
              |                                                                   |
              |                                                                   |
  
   plateau[0][1][0] = 1                                                plateau[0][4][4] = 2
 
```

-----
### Spécifications fonctionnelles
-----

#### Fonctionnalités :

Nous avons décidé d'organiser nos fonctionnalités par ordre de priorité. Pour cela nous utilisons la methode MoSCoW. MoSCoW est l'acronyme de Must, Should, Could, Would.

Pour les fonctionnalités minimales attendues, représentées par le Must, nous avons la réalisation du jeu puissance 4 et de différents argorithmes heuristiques permettant de jouer contre la machine.

Pour les fonctionnalités importantes, représentées par le Should, nous avons la réalisation d'une simple interface graphique en deux dimensions ainsi qu'un algorithme de détection de victoire optimisé c'est-à-dire qui ne teste pas bêtement toutes les cases.

Pour les fonctionnalités additionnelles, représentées par le Could, nous avons la réalisation d'un mode réseau via internet (un joueur fera le serveur, l'autre le client) permettant à 2 joueurs de s'affronter sur deux machines différentes.

Pour les fonctionnalités futures, représentées par le Would, nous avons la réalisation d'une interface graphique en trois dimensions.

#### Interfaces graphiques :

Comme énoncé dans les fonctionnalités, nous réaliserons deux interfaces. La première, très minimaliste, consiste à représenter le plateau sous forme de 0, 1 et 2 dans le terminal. Les différentes profondeurs du plateau seront représentées l'une à côté de l'autre comme illustré ci-dessous :

![Interface minimaliste](http://dwarves.iut-fbleau.fr/~lebec/ProjetTut/img/interfacebasique.png)


La seconde, plus graphique, essayera de représenter l'effet de profondeur de la troisième dimension dans une interface graphique en deux dimensions comme suivant :


![Interface simple](http://dwarves.iut-fbleau.fr/~desousaa/projet_tutor%C3%A9_interface/Interfacepuissance4.png)



-----
### Spécifications techniques
-----

#### Outils utilisées:

La réalisation de notre jeu est effectuée avec le langage Java et nous utiliserons la bibliothèque Swing pour l'interface graphique. Pour le développement de nos intelligences artificielles, nous serons probablement amenés à utiliser d'autres bibliothèques.

Pour la création de nos intelligences artificielles nous élaborerons dans un premier temps divers algorithmes et techniques :
* L'algorithme Minimax
* La technique d'élagage Alpha/Beta qui correspond à une optimisation de l'algorithme précédent


-----
### Diagrammes
-----

#### Diagramme de cas d'usage :

![Interface simple](http://dwarves.iut-fbleau.fr/~desousaa/projet_tutor%C3%A9_interface/UseCaseDiagram.svg)

#### Diagramme de Gantt (fichier accessible sur le git) :

![Interface simple](http://dwarves.iut-fbleau.fr/~desousaa/projet_tutor%C3%A9_interface/Diagramme_Gantt.png)