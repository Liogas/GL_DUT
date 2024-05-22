    import java.util.*;
    import java.io.*;
    import javax.swing.*;
    import java.awt.*;

    /**
     * La classe <code>Algorithme</code> est utilisée pour l'exécution
     * d'un algorithme aléatoire ou déterministe avec ou non un affichage
     * afin de trouver la sortie d'un labyrinthe.
     *
     * @version 1.0
     * @author Fabien Le Bec & Gaston Lions
     */

    public class Algorithme {
        private int movCount,xpoint,ypoint;
        private boolean manuel;
        private Player thesee;
        private Point sortie,backtrack;
        private boolean[][] infogrille;
        private boolean[][] visited;
        private Fichier fichier;
        private long max;
        private Fenetre fenetre;
        private DessinGrille dessin;
        private Random rand = new Random();
        private Stack<Point> chemin = new Stack<Point>();
        private Object monitor = new Object();

        /**
         * Indique si un autre objet de type Point est égale à cet objet.
         *
         * @param o l'objet de référence avec qui comparer
         *
         * @return true si cet objet est de type Point et qu'il est égale à l'argument o; false si non.
         */
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point)) {
                return false;
            }
            return (this.xpoint == ((Point) o).x && this.ypoint == ((Point) o).y);
        }

        /**
         * Constructeur uniquement destiné à la création d'un algorithme ayant un affichage graphique.
         *
         * @param file fichier contenant les informations sur la grille
         * @param frame fenêtre sur laquelle dessiner
         * @param draw classe permettant de dessiner
         * @param joueur Thésée
         * @param ismanuel précise l'exécution est manuelle ou automatique
         */
        public Algorithme(Fichier file, Fenetre frame, DessinGrille draw, Player joueur, boolean ismanuel){
            this.fichier = file;
            this.thesee = joueur;
            this.sortie = new Point(this.fichier.getSortie());
            this.infogrille = this.fichier.getPosMur();
            this.fenetre = frame;
            this.dessin = draw;
            this.manuel = ismanuel;
            this.max = this.fichier.getTailleLaby()*1000000;
        }

        /**
         * Constructeur uniquement destiné à la création d'un algorithme n'ayant pas d'affichage.
         *
         * @param file fichier contenant les informations sur la grille
         * @param joueur Thésée
         * @param ismanuel précise l'exécution est manuelle ou automatique
         */
        public Algorithme(Fichier file, Player joueur, boolean ismanuel){
            this.fichier = file;
            this.thesee = joueur;
            this.sortie = new Point(this.fichier.getSortie());
            this.infogrille = this.fichier.getPosMur();
            this.visited = new boolean[fichier.getTailleLaby()][fichier.getTailleLaby()];
            this.manuel = ismanuel;
            this.max = this.fichier.getTailleLaby()*1000000;
        }

        /**
         * Reset le compteur de mouvements à 0.
         */
        public void resetmovcount(){
            this.movCount = 0;
        }

        /**
         * Utilisé avec l'algorithme aléatoire pour essayer d'aller dans une direction.
         * Si la prochaine case n'est ni un mur ni en dehors de la grille, on s'y déplace.
         * Dans tous les cas on incrémente le compteur de mouvements.
         *
         * @param mouvement direction vers laquelle on souhaite se déplacer
         *
         */ 
        public void tryMove(Dir mouvement){
            if(this.manuel){
                try {
                    synchronized(this.monitor){
                        this.monitor.wait();
                    }
                } catch(InterruptedException e){};
                this.dessin.updateMovcount(this.movCount);              
            }
            try {
                if(!this.infogrille[this.thesee.getProchaineLigne(mouvement.getX())][this.thesee.getProchaineColonne(mouvement.getY())]){
                    this.thesee.movePosition(mouvement.getX(),mouvement.getY());
                    if(this.manuel){
                        this.fenetre.repaint();
                    }
                    this.movCount++;
                } else {
                    this.movCount++;
                }
            } catch(ArrayIndexOutOfBoundsException e){
                this.movCount++;
            }
        }

        /**
         * Résoud le labyrinthe à l'aide de l'algorithme aléatoire.
         * Renvoie 0 si le nombre de mouvements dépasse la taille du labyrinthe*1 000 000 (labyrinthe sans solution); si non renvoie le nombre de mouvements.
         *
         * @return nombre de mouvements (0 si aucune solution)
         */
        public int resoudreAleatoire(){
            while(!(this.thesee.getPosition()).equals(sortie)){
                switch(this.rand.nextInt(4)){
                    case 0:
                        if(this.manuel){
                            this.dessin.updateNextmov("HAUT  ");
                        }
                        this.tryMove(Dir.HAUT);
                        break;
                    case 1:
                        if(this.manuel){
                            this.dessin.updateNextmov("BAS   ");
                        }               
                        this.tryMove(Dir.BAS);
                        break;
                    case 2:
                        if(this.manuel){
                            this.dessin.updateNextmov("DROITE");
                        }                
                        this.tryMove(Dir.DROITE);
                        break;
                    case 3:
                        if(this.manuel){
                            this.dessin.updateNextmov("GAUCHE");
                        }                
                        this.tryMove(Dir.GAUCHE);
                        break;
                }
                if(this.movCount>this.max){
                    return 0;
                }
            }
            return this.movCount;       
        }

        /**
         * Résoud le labyrinthe à l'aide de l'algorithme déterministe de façon récursive.
         * 
         * @param x coordonnée x à tester
         * @param y coordonnée y à tester
         * @param nextdir text contenant la prochaine direction
         * @param chemin Pile contenant le chemin emprunté
         * @param visited tableau contenant les cases visités
         *
         * @return true si la sortie a été trouvée; false si non (mur rencontrée, coordonnées en dehors de la grille, case déjà visitée)
         */
        public boolean resoudreDeterministe(int x, int y, String nextdir, Stack<Point> chemin, boolean[][] visited){
            if(this.thesee.getPosition().equals(sortie)){
                return true;
            }
            try{
                if(this.manuel){
                    if(this.dessin.isVisited(x,y)){
                        return false;
                    }
                    this.dessin.updateNextmov(nextdir);
                    try {
                        synchronized(this.monitor){
                            this.monitor.wait();
                        }
                    } catch(InterruptedException e){};
                    if(this.infogrille[x][y]){
                        this.dessin.setVisited(x,y);
                        this.movCount++;
                        this.dessin.updateMovcount(movCount);
                        return false;
                    }                 
                } else {
                    if(this.visited[x][y]){
                        return false;
                    }
                    if(this.infogrille[x][y]){
                        this.visited[x][y] = true;
                        this.movCount++;
                        return false;
                    }
                }
            } catch(ArrayIndexOutOfBoundsException e){        
                this.movCount++;
                if(this.manuel){
                    this.dessin.updateNextmov(nextdir);
                    try {
                        synchronized(this.monitor){
                            this.monitor.wait();
                        }
                    } catch(InterruptedException event){}; 
                    this.dessin.updateMovcount(movCount);
                }
                return false;
            }

            chemin.push(this.thesee.getPosition());

            this.thesee.setPosition(x,y);
            if(this.manuel){
                this.dessin.setVisited(x,y);
                this.fenetre.repaint();
                this.movCount++;
                this.dessin.updateMovcount(movCount);
            } else {
                this.visited[x][y] = true;
                this.movCount++;
            }

            if(resoudreDeterministe(this.thesee.getProchaineLigne(Dir.HAUT.getX()), this.thesee.getProchaineColonne(Dir.HAUT.getY()), "HAUT  ", chemin, this.visited)){ return true;}
            if(resoudreDeterministe(this.thesee.getProchaineLigne(Dir.BAS.getX()), this.thesee.getProchaineColonne(Dir.BAS.getY()), "BAS   ", chemin, this.visited)){ return true;}
            if(resoudreDeterministe(this.thesee.getProchaineLigne(Dir.GAUCHE.getX()), this.thesee.getProchaineColonne(Dir.GAUCHE.getY()), "GAUCHE", chemin, this.visited)){ return true;}
            if(resoudreDeterministe(this.thesee.getProchaineLigne(Dir.DROITE.getX()), this.thesee.getProchaineColonne(Dir.DROITE.getY()), "DROITE", chemin, this.visited)){ return true;}

            if(this.manuel){
                try {
                    synchronized(this.monitor){
                        this.monitor.wait();
                    }
                } catch(InterruptedException e){};                
            }
            this.backtrack = chemin.pop();
            this.thesee.setPosition((int) this.backtrack.getX(),(int) this.backtrack.getY());
            this.movCount++;

            if(manuel){
                this.fenetre.repaint();
                this.dessin.updateMovcount(movCount);
            }
            return false;
        }

        /**
         * Lance l'algorithme déterministe.
         *
         * @return 0 si le labyrinthe n'a pas de solution sinon le nombre de mouvements. -1 si une erreur StackOverflow est levée.
         */
        public int deterministe(){
            this.thesee.resetPosition();
            this.resetmovcount();

            if(this.manuel){
                this.fenetre.addKeyListener(new ActionManuelle(this.fenetre,this.monitor));
            }
            try{
                if(resoudreDeterministe(this.thesee.getLigne(), this.thesee.getColonne(), "", chemin, this.visited)){
                    if(this.manuel){
                        this.dessin.isFinish();
                        this.fenetre.repaint();
                    }
                    return movCount;
                } else {
                    if(this.manuel){
                        this.dessin.isFinish();
                        this.fenetre.repaint();
                    }                
                    return 0;
                }
            } catch(StackOverflowError e){
                JFrame error = new JFrame();
                JOptionPane.showMessageDialog(error, "Problème de mémoire, labyrinthe trop complexe pour être résolu dans cette configuration.", "Résultat", JOptionPane.ERROR_MESSAGE);
                return -1;
            }

        }

        /**
         * Exécute l'algorithme aléatoire 100 fois et renvoie la moyenne de mouvements.
         *
         * @return Renvoie 0 si le labyrinthe n'a pas de solution; sinon la moyenne des 100 exécutions.
         */
        public int aleatoire(){
            int moyenne = 0;
            int retour;
            this.thesee.resetPosition();
            this.resetmovcount();

            if(this.manuel){
                this.fenetre.addKeyListener(new ActionManuelle(this.fenetre,this.monitor));
                this.fenetre.repaint();
                moyenne = this.resoudreAleatoire();
                return moyenne;
            } else {
                for(int i=0;i<100;i++){
                    this.thesee.resetPosition();
                    this.resetmovcount();
                    retour = this.resoudreAleatoire();
                    if(retour==0){
                        return 0;
                    }
                    moyenne += retour;
                }
                return moyenne/100;                
            }
        }
    }
