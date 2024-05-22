package controller.jeu;

import controller.*;
import model.grille.*;
import model.joueur.*;
import model.*;

public class ThreadSimulation extends Thread{
	JoueurIA joueur1, joueur2;
	int nbrSimulations, victoiresJ1, victoiresJ2;

	public ThreadSimulation(JoueurIA j1, JoueurIA j2, int nbrSim){
		joueur1 = j1;
		joueur2 = j2;
		nbrSimulations = nbrSim;
	}

	public void run(){
		for(int simulationEffectuee = 1; simulationEffectuee <= nbrSimulations; simulationEffectuee++){
			if(Simulation.getNbrSimulationsEffectuees() != 0){
				System.out.println("Progression : "+((int)((double)Simulation.getNbrSimulationsEffectuees()/(double)Simulation.getNbrSimulations()*100))+"% ("+Simulation.getNbrSimulationsEffectuees()+"/"+Simulation.getNbrSimulations()+")");
			}

			JeuSimulation simulation = new JeuSimulation();

			simulation.lancerPartieIA(joueur1, joueur2);

			if(simulation.getPartie().getGrille().getVainqueur() == Couleur.ROUGE){
				victoiresJ1++;
			} else if(simulation.getPartie().getGrille().getVainqueur() == Couleur.JAUNE){
				victoiresJ2++;
			}
			Simulation.incrementNbrSimulations();
		}
	}

	public int getVictoiresJ1(){
		return victoiresJ1;
	}
	
	public int getVictoiresJ2(){
		return victoiresJ2;
	}
}