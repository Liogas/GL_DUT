package view;

import model.*;
import model.joueur.*;
import controller.listener.*;
import controller.jeu.*;
import controller.reseau.Attente;

import javax.swing.*;
import java.awt.*;


public class AffichageGraphique{

	private Fenetre fenetre;
	private DessinGrille dessin;

	private Jeu cJeu;
	private ActionSourisEnJeu action;

	private final int screenW, screenH, verticalPadding, horizontalPadding, verticalPaddingMenuJouer;

	private String algo;
	private int prof;

	private boolean accesMenu=true;
	private ActionBoutonMenu actionBoutonMenuReseau;

	public AffichageGraphique(Fenetre f){
		fenetre = f;

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice(); //récupère les informations sur l'écran principal
		screenW = gd.getDisplayMode().getWidth();
		screenH = gd.getDisplayMode().getHeight();

		verticalPadding = screenH/14;
		horizontalPadding = screenW/6;
		verticalPaddingMenuJouer = (int)((double)verticalPadding/1.5);
	}


	public void afficherInterfaceJeu(){
		fenetre.setCliquable(true);
		fenetre.getContentPane().removeAll();

		dessin = new DessinGrille(fenetre, cJeu.getPartie());
		fenetre.add(dessin);

		action = new ActionSourisEnJeu(fenetre, dessin, cJeu.getPartie(), this); 
		dessin.addMouseListener(action);
		dessin.addMouseMotionListener(action);

		fenetre.repaint();
		fenetre.revalidate();	
	}

	public void afficherMenuPrincipal(){
		String audioFilePath = "Son/go.wav";
		AudioPlayer player = new AudioPlayer(audioFilePath);
		
		fenetre.setCliquable(false);
		fenetre.getContentPane().removeAll();

		JPanel fond = new JPanel();
		GridBagLayout repartiteur = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		fond.setLayout(repartiteur);

		JButton jouer = new JButton("Jouer");
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(verticalPadding,horizontalPadding,verticalPadding,horizontalPadding);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 100f;
		c.weighty = 25f;
		fond.add(jouer, c);
		jouer.setBackground(Color.BLACK);
		jouer.setForeground(Color.WHITE);
		jouer.addActionListener(new ActionBoutonMenu(this));
		
		JButton aide = new JButton("Aide");
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(verticalPadding,horizontalPadding,verticalPadding,horizontalPadding);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 100f;
		c.weighty = 25f;
		fond.add(aide, c);
		aide.setBackground(Color.BLACK);
		aide.setForeground(Color.WHITE);
		aide.addActionListener(new ActionBoutonMenu(this));

		JButton info = new JButton("Informations");
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(verticalPadding,horizontalPadding,verticalPadding,horizontalPadding);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 100f;
		c.weighty = 25f;
		fond.add(info, c);
		info.setBackground(Color.BLACK);
		info.setForeground(Color.WHITE);
		info.addActionListener(new ActionBoutonMenu(this));

		JButton quitter = new JButton("Quitter");
		c.gridx = 0;
		c.gridy = 6;
		c.insets = new Insets(verticalPadding,horizontalPadding,verticalPadding,horizontalPadding);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 100f;
		c.weighty = 25f;
		fond.add(quitter, c);
		quitter.setBackground(Color.BLACK);
		quitter.setForeground(Color.WHITE);
		quitter.addActionListener(new ActionBoutonMenu(this));

		fenetre.add(fond);
		fenetre.repaint();
		fenetre.revalidate();	
	}

	public void afficherMenuChoixMode(){
		fenetre.getContentPane().removeAll();

		JPanel fond = new JPanel();
		GridBagLayout repartiteur = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		fond.setLayout(repartiteur);

		JLabel label = new JLabel("Choix de l'adversaire :");
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(verticalPaddingMenuJouer,horizontalPadding+horizontalPadding/10,verticalPaddingMenuJouer,horizontalPadding);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 100f;
		c.weighty = 25f;
		fond.add(label, c);

		JButton ia = new JButton("IA");
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(verticalPaddingMenuJouer,horizontalPadding,verticalPaddingMenuJouer,horizontalPadding);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 100f;
		c.weighty = 25f;
		fond.add(ia, c);
		ia.setBackground(Color.BLACK);
		ia.setForeground(Color.WHITE);
		ia.addActionListener(new ActionBoutonMenu(this));
		
		JButton local = new JButton("Local");
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(verticalPaddingMenuJouer,horizontalPadding,verticalPaddingMenuJouer,horizontalPadding);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 100f;
		c.weighty = 25f;
		fond.add(local, c);
		local.setBackground(Color.BLACK);
		local.setForeground(Color.WHITE);
		local.addActionListener(new ActionBoutonMenu(this));

		JButton reseau = new JButton("Réseau");
		c.gridx = 0;
		c.gridy = 6;
		c.insets = new Insets(verticalPaddingMenuJouer,horizontalPadding,verticalPaddingMenuJouer,horizontalPadding);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 100f;
		c.weighty = 25f;
		fond.add(reseau, c);
		reseau.setBackground(Color.BLACK);
		reseau.setForeground(Color.WHITE);
		actionBoutonMenuReseau = new ActionBoutonMenu(this);
		reseau.addActionListener(actionBoutonMenuReseau);

		JButton retour = new JButton("Retour");
		c.gridx = 0;
		c.gridy = 8;
		c.insets = new Insets(verticalPaddingMenuJouer,horizontalPadding,verticalPaddingMenuJouer,horizontalPadding);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 100f;
		c.weighty = 25f;
		fond.add(retour, c);
		retour.setBackground(Color.BLACK);
		retour.setForeground(Color.WHITE);
		retour.addActionListener(new ActionBoutonMenu(this));

		fenetre.add(fond);
		fenetre.repaint();
		fenetre.revalidate();
	}

	public void afficherMenuIA(){
		fenetre.getContentPane().removeAll();

		JPanel fond = new JPanel();
		GridBagLayout repartiteur = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		fond.setLayout(repartiteur);

		JButton alpha = new JButton("Alpha Beta");
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(verticalPadding,horizontalPadding,verticalPadding,horizontalPadding);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 100f;
		c.weighty = 25f;
		fond.add(alpha, c);
		alpha.setBackground(Color.BLACK);
		alpha.setForeground(Color.WHITE);
		alpha.addActionListener(new ActionBoutonMenu(this));
		
		JButton min = new JButton("Min Max");
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(verticalPadding,horizontalPadding,verticalPadding,horizontalPadding);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 100f;
		c.weighty = 25f;
		fond.add(min, c);
		min.setBackground(Color.BLACK);
		min.setForeground(Color.WHITE);
		min.addActionListener(new ActionBoutonMenu(this));

		JButton autre = new JButton("Monte-Carlo");
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(verticalPadding,horizontalPadding,verticalPadding,horizontalPadding);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 100f;
		c.weighty = 25f;
		fond.add(autre, c);
		autre.setBackground(Color.BLACK);
		autre.setForeground(Color.WHITE);
		autre.addActionListener(new ActionBoutonMenu(this));

		JButton retour = new JButton("Retour");
		c.gridx = 0;
		c.gridy = 6;
		c.insets = new Insets(verticalPadding,horizontalPadding,verticalPadding,horizontalPadding);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 100f;
		c.weighty = 25f;
		fond.add(retour, c);
		retour.setBackground(Color.BLACK);
		retour.setForeground(Color.WHITE);
		retour.addActionListener(new ActionBoutonMenu(this));

		fenetre.add(fond);
		fenetre.repaint();
		fenetre.revalidate();
	}

	public void afficherMenuChoixProfondeur(){
		fenetre.getContentPane().removeAll();

		JPanel fond = new JPanel();
		GridBagLayout repartiteur = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		fond.setLayout(repartiteur);

		JButton une = new JButton("Niveau 1");
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(verticalPadding,horizontalPadding,verticalPadding,horizontalPadding);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 100f;
		c.weighty = 25f;
		fond.add(une, c);
		une.setBackground(Color.BLACK);
		une.setForeground(Color.WHITE);
		une.addActionListener(new ActionBoutonMenu(this));
		
		JButton deux = new JButton("Niveau 2");
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(verticalPadding,horizontalPadding,verticalPadding,horizontalPadding);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 100f;
		c.weighty = 25f;
		fond.add(deux, c);
		deux.setBackground(Color.BLACK);
		deux.setForeground(Color.WHITE);
		deux.addActionListener(new ActionBoutonMenu(this));

		JButton trois = new JButton("Niveau 3");
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(verticalPadding,horizontalPadding,verticalPadding,horizontalPadding);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 100f;
		c.weighty = 25f;
		fond.add(trois, c);
		trois.setBackground(Color.BLACK);
		trois.setForeground(Color.WHITE);
		trois.addActionListener(new ActionBoutonMenu(this));

		JButton retour = new JButton("Retour");
		c.gridx = 0;
		c.gridy = 6;
		c.insets = new Insets(verticalPadding,horizontalPadding,verticalPadding,horizontalPadding);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 100f;
		c.weighty = 25f;
		fond.add(retour, c);
		retour.setBackground(Color.BLACK);
		retour.setForeground(Color.WHITE);
		retour.addActionListener(new ActionBoutonMenu(this));

		fenetre.add(fond);
		fenetre.repaint();
		fenetre.revalidate();
	}

	public void animationTomberJeton(){

    	dessin.getModelGrille().runTransition();

    	while(dessin.getModelGrille().getEtatTransition()){

	      	fenetre.repaint();

	      	try {
	        	Thread.sleep(2);
	     	} catch (InterruptedException e) {
	        	e.printStackTrace();
	      	}

	    	dessin.getModelGrille().verifTransition();
	    	fenetre.repaint();
    	}
  	}

  	public void createControllerJeu(Jeu c){
  		cJeu = c;
  	}


  	public Jeu getControllerJeu(){
  		return cJeu;
  	}

  	public Fenetre getFenetre(){
  		return fenetre;
  	}

  	public String getAlgo(){
  		return algo;
  	}

  	public void setAlgo(String s){
  		algo = s;
  	}

  	public int getProf(){
  		return prof;
  	}

  	public void setProf(int i) {
  		prof = i;
	}
	  
	public void setAccesMenu(boolean b) {
		this.accesMenu = b;
	}

	public Attente getAttenteReseau() {
		return this.actionBoutonMenuReseau.getAttenteReseau();
	}

	public boolean getAccesMenu() {
		return this.accesMenu;
	}
}