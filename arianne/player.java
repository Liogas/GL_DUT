    import java.awt.*;

    /**
     * La classe <code>Player</code> est utilisée pour représenter la position
     * d'un personnage sur une grille selon la classe Point.
     *
     * @version 1.0
     * @author Fabien Le Bec et Gaston Lions
     */
    public class Player extends Point{
    	private Point initialThesee;
    	
    	/**
    	 * Constructeur permettant la création d'un personnage ayant comme caractéristiques des coordonnées.
    	 * 
    	 * @param posX position en X du personnage
    	 * @param posY position en Y du personnage
    	 */
    	public Player(int posX, int posY){
    		super();
    		this.initialThesee = new Point(posX,posY);
    	}

    	/**
    	 * Remet le personnage à sa position initiale correspondant aux coordonnées fournis dans le constructeur.
    	 */
    	public void resetPosition(){
    		this.setLocation(this.initialThesee);
    	}

    	/**
    	 * Retourne la ligne sur laquelle se trouve le personnage.
    	 * 
    	 * @return ligne du personnage
    	 */
    	public int getLigne(){
    		return (int) this.getX();
    	}

    	/**
    	 * Retourne la colonne sur laquelle se trouve le personnage.
    	 * 
    	 * @return colonne du personnage
    	 */
    	public int getColonne(){
    		return (int) this.getY();
    	}

    	/**
    	 * Retourne la position d'un personnage sous la forme d'un objet Point.
    	 * 
    	 * @return position du personnage
    	 */
    	public Point getPosition() {
    		return this.getLocation();
    	}

    	/**
    	 * Retourne la prochaine ligne selon un nombre de déplacements fournis.
    	 * 
    	 * @param x nombre de déplacements sur la ligne
    	 *
    	 * @return prochaine ligne
    	 */
    	public int getProchaineLigne(int x){
    		return this.getLigne()+x;
    	}

    	/**
    	 * Retourne la prochaine colonne selon un nombre de déplacements fournis.
    	 * 
    	 * @param y nombre de déplacements sur la colonne
    	 *
    	 * @return prochaine colonne
    	 */
    	public int getProchaineColonne(int y){
    		return this.getColonne()+y;
    	}

    	/**
    	 * Définit la position du personnage.
    	 * 
    	 * @param x numéro de la ligne
    	 * @param y numéro de la colonne
    	 */
    	public void setPosition(int x, int y){
    		this.move(x,y);
    	}

    	/**
    	 * Déplace le personnage depuis la position actuelle.
    	 * 
    	 * @param x déplacement sur la ligne
    	 * @param y déplacement sur la colonne
    	 */
    	public void movePosition(int x, int y){
    		this.move(this.getLigne()+x, this.getColonne()+y);
    	}

    }
