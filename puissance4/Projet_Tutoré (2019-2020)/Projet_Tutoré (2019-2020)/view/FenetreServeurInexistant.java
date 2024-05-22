package view;

import javax.swing.*;

/**
 * FenetreServeurInexistant
 */
public class FenetreServeurInexistant extends JFrame {

    public FenetreServeurInexistant() {
        super("Serveur inexistant");
        JLabel label = new JLabel("Vérifiez votre connexion internet");
        this.add(label);
    }

    
}