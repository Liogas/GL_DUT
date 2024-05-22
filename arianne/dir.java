/**
 * La classe <code>Dir</code> est utilisée pour signifier une orientation possible
 * parmi les quatre points cardinaux.
 *  
 * @version 1.1
 * @author Luc Hernandez
 */
public class Dir {

  /**
   * Constante pointant vers le nord (c'est à dire vers le haut de l'écran).
   */
  public static final Dir HAUT = new Dir(-1, +0);
  
  /**
   * Constante pointant vers le sud (c'est à dire vers le bas de l'écran).
   */
  public static final Dir BAS = new Dir(+1, +0);
  
  /**
   * Constante pointant vers l'est (c'est à dire vers la droite de l'écran).
   */
  public static final Dir DROITE = new Dir(+0, +1);
  
  /**
   * Constante pointant vers l'ouest (c'est à dire vers la gauche de l'écran).
   */
  public static final Dir GAUCHE = new Dir(+0, -1);
  
  /**
   * Composante horizontale de la Dir (-1, 0 ou 1).
   */
  private int decalageX;
  
  /**
   * Composante verticale de la Dir (-1, 0 ou 1).
   */
  private int decalageY;
  
  /**
   * Constructeur uniquement destiné à la création des constantes publiques.
   *
   * @param x l'abcisse (-1, 0 ou 1)
   * @param y l'ordonnée (-1, 0 ou 1)
   */
  private Dir(int x, int y) {
    this.decalageX = x;
    this.decalageY = y;
  }
  
  /**
   * Renvoie la composante horizontale de la Dir.
   *
   * @return la composante horizontale de la Dir (-1, 0 ou 1)
   */
  public int getX() {
    return this.decalageX;
  }
  
  /**
   * Renvoie la composante verticale de la Dir.
   *
   * @return la composante verticale de la Dir (-1, 0 ou 1)
   */
  public int getY() {
    return this.decalageY;
  }
}
