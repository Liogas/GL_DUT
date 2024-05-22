package controller;

import view.*;

public class ThreadAnimation implements Runnable{

	private AffichageGraphique aGraphique;


	public ThreadAnimation(AffichageGraphique a){
		aGraphique = a;
	}

	public void run(){
		aGraphique.animationTomberJeton();
	}


}