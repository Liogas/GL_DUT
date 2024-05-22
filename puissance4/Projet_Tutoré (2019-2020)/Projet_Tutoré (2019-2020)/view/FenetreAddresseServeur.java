package view;

import controller.listener.*;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

/**
 * FenetreAddresseServeur
 */
public class FenetreAddresseServeur extends JFrame {

    private JTextField champs;
    private JButton btn;

    public FenetreAddresseServeur() {
        super("Adresse IP du serveur");
        JLabel label = new JLabel("Inserez l'adresse IP et le port du serveur :");
        JLabel label2 = new JLabel("Format : <adresse_ip>:<port>");
        btn = new JButton("OK");
        //btn.addActionListener((ActionListener) new ActionBoutonMenu(affichageGraphique));
        champs = new JTextField();
        label.setHorizontalAlignment(JLabel.CENTER);
        label2.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new GridLayout(5,0));
        this.add(label);
        this.add(label2);
        this.add(champs, BorderLayout.CENTER);
        this.add(new JPanel());
        this.add(btn, BorderLayout.EAST);
        this.setResizable(false);
    }

    public void setActionListener(ActionListener a) {
        btn.addActionListener(a);
    }

    public JTextField getChamps() {
        return champs;
    }
}