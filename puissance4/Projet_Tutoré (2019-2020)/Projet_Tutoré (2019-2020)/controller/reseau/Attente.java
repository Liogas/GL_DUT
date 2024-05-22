
package controller.reseau;
import controller.jeu.JeuReseau;
import model.reseau.Client;
import model.reseau.Information;
import view.AffichageGraphique;
import javax.swing.*;
import java.awt.event.*;

/**
 * Attente
 */
public class Attente extends Thread {

    private JeuReseau jeu;
    private Client client;
    private AffichageGraphique affichGraphique;
    private boolean continuer = true;

    public Attente(JeuReseau j, Client c, AffichageGraphique a) {
        this.jeu = j;
        this.client = c;
        this.affichGraphique = a;
    }

    public void arreter() {
        continuer = false;
    }
    @Override
    public void run() {
        while(continuer) {
            try {
                int i;
                Information info;
                String str;
                int etatJeu;
                str = this.client.recevoir();
                this.jeu.fermerFenetreAttente();
                System.out.println(str);
                if(continuer==false) {
                    break;
                }
                if(str.equals("pas a toi")) {
                    this.jeu.jePeuxJouer(false);
                    str = this.client.recevoir();
                    if(continuer==false) {
                        break;
                    }
                    if(str.equals("fin")) {
                        if(continuer==false) {
                            break;
                        }
                        JOptionPane jop = new JOptionPane();      
                        int option = jop.showConfirmDialog(null, "Le joueur a quitté la partie, revenir à l'écran d'accueil ?", "Fin", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);

                        if(option == JOptionPane.OK_OPTION){
                            affichGraphique.afficherMenuPrincipal();
                            break;
                        }
                    }
                    info = new Information(str);
                    etatJeu = this.jeu.afficherJeton(info.getColonne(), info.getProfondeur());
                    if(etatJeu == 2){
                        this.jeu.getPartie().setVainqueur(this.jeu.getPartie().getJoueurActuel());
                    }
                    affichGraphique.getFenetre().repaint();
                    affichGraphique.getFenetre().revalidate();
                } else {
                    this.jeu.jePeuxJouer(true);
                    if(continuer==false) {
                        break;
                    }
                }
                str = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}