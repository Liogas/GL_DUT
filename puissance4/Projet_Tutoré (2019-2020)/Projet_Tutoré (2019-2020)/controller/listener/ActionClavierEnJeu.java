package controller.listener;
import view.*;

import java.awt.event.*;

public class ActionClavierEnJeu implements KeyListener{


	public ActionClavierEnJeu(){
	
	}

	public void keyPressed(KeyEvent evt){}

 	public void keyReleased(KeyEvent evt){} 

 	public void keyTyped(KeyEvent evt){
 		if(evt.getKeyChar() == 'i'){
 			
 		} else if(evt.getKeyChar() == 'p'){
 			System.out.println("p");
 		}
 	}


}
