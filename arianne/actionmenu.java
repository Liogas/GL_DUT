import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

/**
 * La classe <code>ActionMenu</code> est utilisée pour la gestion des 
 * actions venant du menu.
 * 
 * @version 1.0
 * @author Gaston Lions & Fabien Le Bec
 */


public class ActionMenu implements ActionListener{
    private JFrame fenetre;
    private JPanel element;
    private JButton bouton;
    private Menu gestionMenu;

    /**
    * Constante qui correspond à la vue aléatoire.
    */
    final int AUTOMATIQUE = 1;

    /**
    * Constante qui correspond à la vue manuel.
    */
    final int MANUEL = 2;

    /**
    * Constante qui correspond à l'algorithme aléatoire.
    */
    final int ALEATOIRE = 1;

    /**
    * Constante qui correspond à l'algorithme deterministe.
    */
    final int DETERMINISTE = 2;


    /**
    * Constructeur destiné à réagir au évènement de la classe Menu/
    *
    * @param f la fenetre.
    * @param m le menu.
    * @param e la zone où le menu est affiché.
    * @param b le bouton où il y eu le clique.
    */
    public ActionMenu(Menu m, JFrame f, JPanel e, JButton b){
        this.fenetre = f;
        this.element = e;
        this.bouton = b;
        this.gestionMenu = m;
    }

    /**
    * Gère tous les évènements.
    *
    *@param evenement l'évènement de l'élément.
    */
    public void actionPerformed(ActionEvent evenement){
        Container c = this.fenetre.getContentPane();
        String action = this.bouton.getActionCommand();

        if(action.equals("Vue")){
            c.removeAll();
            this.fenetre.repaint();
            gestionMenu.setMenuVue();
        
        } else if(action.equals("Algorithme")){
            c.removeAll();
            this.fenetre.repaint();
            gestionMenu.setMenuAlgo();
        
        } else if(action.equals("Grille")){
            JFileChooser choix = new JFileChooser("./Map");
            int retour=choix.showOpenDialog(null);
            if(retour==JFileChooser.APPROVE_OPTION){
             // un fichier a été choisi (sortie par OK)
            // nom du fichier  choisi 
            gestionMenu.setChoixGrille(choix.getSelectedFile().getAbsolutePath());
            // chemin absolu du fichier choisi
            }

        } else if(action.equals("Lancer")){
            boolean test = gestionMenu.verifLancement();
            if(test == true){
                gestionMenu.lancement();
            }
        
        } else if(action.equals("Création grille")){
            this.fenetre.dispose();
            MapEditor creation = new MapEditor();
            
        }else if(action.equals("Quitter")){
            this.fenetre.dispose();
        
        } else if(action.equals("Retour")){
            c.remove(element);
            this.fenetre.repaint();
            gestionMenu.setMenu();
        
        } else if(action.equals("Automatique")){
            gestionMenu.setChoixVue(AUTOMATIQUE);
            c.remove(element);
            this.fenetre.repaint();
            gestionMenu.setMenu();
        
        } else if(action.equals("Manuel")){
            gestionMenu.setChoixVue(MANUEL);
            c.remove(element);
            this.fenetre.repaint();
            gestionMenu.setMenu();
        
        } else if(action.equals("Aléatoire")){
            gestionMenu.setChoixAlgo(ALEATOIRE);
            c.remove(element);
            this.fenetre.repaint();
            gestionMenu.setMenu();
        
        } else if(action.equals("Déterministe")){
            gestionMenu.setChoixAlgo(DETERMINISTE);
            c.remove(element);
            this.fenetre.repaint();
            gestionMenu.setMenu();
        
        }

    }
}