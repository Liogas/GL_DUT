import javax.swing.*;
import java.awt.*;

/**
 * La classe <code>DessinGrille</code> permet d'afficher une grille.
 * nbrClique.
 * @version 1.0
 * @author Gaston Lions et Fabien Le Bec
 */

public class DessinGrille extends JComponent{
    private int tailleCase,nbrCases,algo;
    private JFrame fenetre;
    private Fichier fichier;
    private Player thesee;
    private Point sortie;
    private boolean finish = false;
    private boolean[][] posMur;
    private boolean[][] visited;
    private JLabel movnumber,nextmov;


/**
* Constructeur uniquement destiné à charger des valeurs dans des variables.
*
* @param fe la fenêtre.
* @param fi le fichier.
* @param t thésée.
* @param algorithme 0 : déterministe, 1 : aléatoire.
* @param movnumb le nombre de déplacement.
* @param prochainmov le prochain déplacement donnée par l'algorithme.
*/
    public DessinGrille(JFrame fe, Fichier fi, Player t, int algorithme, JLabel movnumb, JLabel prochainmov){
        this.fenetre = fe;
        this.fichier = fi;
        this.nbrCases = fichier.getTailleLaby();
        this.tailleCase = this.fenetre.getHeight()/this.nbrCases;
        this.thesee = t;
        this.sortie = fichier.getSortie();
        this.posMur = fichier.getPosMur();
        this.visited = new boolean[fichier.getTailleLaby()][fichier.getTailleLaby()];
        this.algo = algorithme;
        this.movnumber = movnumb;
        this.nextmov = prochainmov;
    }


    /**
    * Permet de donner un nombre de cases.
    *
    * @param nbr le nombre de cases.
    */
    public void setCases(int nbr){
        this.nbrCases = nbr;
    }

    /**
    * Permet de rendre une cases dans l'état visitée (true).
    *
    * @param x ligne du tableau.
    * @param y colonne du tableau.
    */
    public void setVisited(int x, int y){
        this.visited[x][y] = true;
    }

    /**
    * retourne si une case est dans l'état visitée.
    *
    * @param x ligne du tableau.
    * @param y colonne du tableau.
    * @return retourne si une case est dans l'état visitée (true ou false).
    */
    public boolean isVisited(int x, int y){
        return this.visited[x][y];
    }

    /**
    * Met à jours la variable finish à true qui permet de dire que la sortie a été trouvé.
    */
    public void isFinish(){
        this.finish = true;
    }

    /**
    * Met à jours le texte indiquant le nombre de mouvement.
    *
    * @param mov le nombre de mouvement.
    */
    public void updateMovcount(int mov){
        this.movnumber.setText("Mouvement n°"+mov);
    }

    /**
    * Met à jours le texte indiquant le prochain mouvement.
    *
    * @param direction le prochain mouvement.
    */
    public void updateNextmov(String direction){
        this.nextmov.setText("Prochaine direction : "+direction);
    }

    /**
    * Permet de dessiner le labyrinthe ainsi que ses composants (murs, sortie , etc...).
    *
    * @param pinceau le Graphics.
    */
    @Override
    public void paintComponent(Graphics pinceau) {
        Graphics secondPinceau = pinceau.create();
        if (this.isOpaque()) {
            // on repeint toute la surface avec la couleur de fond
            secondPinceau.setColor(this.getBackground());
            secondPinceau.fillRect(50, 0, this.getWidth(), this.getHeight());
        }
        
        int x = this.fenetre.getWidth() / 2 - (tailleCase* this.nbrCases / 2);
        int y = this.fenetre.getHeight() / 2 - (tailleCase* this.nbrCases / 2);

        for(int i = 0; i < this.nbrCases; i++){
            for(int j = 0; j < this.nbrCases; j++){
                if(this.algo == 1){
                    if(this.posMur[i][j]){
                        secondPinceau.setColor(Color.BLACK);
                        secondPinceau.fillRect(x,y,tailleCase,tailleCase);
                    
                    } else if(i == this.thesee.getLigne() && j == this.thesee.getColonne()){
                        secondPinceau.setColor(Color.MAGENTA);
                        secondPinceau.fillRect(x,y,tailleCase,tailleCase);
                    } else if(i == (int) this.sortie.getX() && j == (int) this.sortie.getY()){
                        secondPinceau.setColor(Color.BLUE);
                        secondPinceau.fillRect(x,y,tailleCase,tailleCase);
                    }
                }
                if(this.algo == 2){
                    // Dévoile tous les murs quand la sortie a été trouvée
                    if(this.finish){
                        // Position thésée
                        if(i == this.thesee.getLigne() && j == this.thesee.getColonne()){
                            secondPinceau.setColor(Color.MAGENTA);
                            secondPinceau.fillRect(x,y,tailleCase,tailleCase);
                        // Mur connu
                        } else if(this.posMur[i][j] && this.visited[i][j]){
                            secondPinceau.setColor(Color.RED);
                            secondPinceau.fillRect(x,y,tailleCase,tailleCase);
                        // Case visitée
                        } else if(this.visited[i][j]){
                            secondPinceau.setColor(Color.GRAY);
                            secondPinceau.fillRect(x,y,tailleCase,tailleCase);
                        // Mur non connu
                        } else if(this.posMur[i][j]){
                            secondPinceau.setColor(Color.BLACK);
                            secondPinceau.fillRect(x,y,tailleCase,tailleCase);
                        }
                    
                    } else {
                        // Grille noire
                        if(!this.visited[i][j] && !this.finish){
                            secondPinceau.setColor(Color.BLACK);
                            secondPinceau.fillRect(x,y,tailleCase,tailleCase);

                        // Position Thesee
                        } else if(i == this.thesee.getLigne() && j == this.thesee.getColonne()){
                            secondPinceau.setColor(Color.MAGENTA);
                            secondPinceau.fillRect(x,y,tailleCase,tailleCase);

                        // Position sortie
                        } else if(i == (int) this.sortie.getX() && j == (int) this.sortie.getY()){
                            secondPinceau.setColor(Color.BLUE);
                            secondPinceau.fillRect(x,y,tailleCase,tailleCase);

                        // Mur découvert
                        } else if(this.visited[i][j] && this.posMur[i][j]){
                            secondPinceau.setColor(Color.RED);
                            secondPinceau.fillRect(x,y,tailleCase,tailleCase);

                        // Case visitée n'étant pas un mur
                        } else if(this.visited[i][j]){
                            secondPinceau.setColor(Color.GRAY);
                            secondPinceau.fillRect(x,y,tailleCase,tailleCase);
                        }
                    }
                }
                //Cadrillage
                secondPinceau.setColor(Color.BLACK);
                secondPinceau.drawRect(x,y,tailleCase,tailleCase);
                x += tailleCase;
            }
            x = this.fenetre.getWidth() / 2 - (tailleCase* this.nbrCases / 2);
            y += tailleCase;
        }
    }
}