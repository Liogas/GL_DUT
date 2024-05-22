package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
 
public class PanneauAttenteJoueur extends JPanel implements Runnable {

  JLabel label;
  String str;
  Thread th;

  public PanneauAttenteJoueur(String texte) {
    super();
    str=texte;
    label = new JLabel(texte);
    label.setFont(new java.awt.Font(Font.SANS_SERIF, Font.BOLD, 14));
    label.setForeground(Color.MAGENTA);
    add(label);
    th = new Thread(this);
    th.start();
  }
 
  public void run(){
    int i=0;
    char c='.';
    int taille = str.length();
    while(true){
      if(i==4) {
        str = str.substring(0,taille);
        i=0;
      }
      try{
        Thread.sleep(750);
      }catch(InterruptedException e){ e.printStackTrace();}
      str += c;
      label.setText(str);
      i++;
    }
  }
}