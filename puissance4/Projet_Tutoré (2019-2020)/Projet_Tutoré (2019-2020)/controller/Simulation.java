package controller;

import controller.jeu.*;
import model.algorithme.*;
import model.evaluation.*;
import model.grille.*;
import model.joueur.*;
import model.*;

public class Simulation {
	private static int nbrSimulationsEffectuees;
	private static int nbrSimulations;

	public Simulation(){
		nbrSimulationsEffectuees = 0;
	}

	public static void incrementNbrSimulations(){
		nbrSimulationsEffectuees++;
	}

	public static int getNbrSimulationsEffectuees(){
		return nbrSimulationsEffectuees;
	}

	public static int getNbrSimulations(){
		return nbrSimulations;
	}

	public static void main(String[] args) {
		nbrSimulations = 1000;
		int nbrVictoiresJ1 = 0;
		int nbrVictoiresJ2 = 0;
		int cores = Runtime.getRuntime().availableProcessors();
		int nbrSimulationsPerThread = (int)(nbrSimulations/cores);
		ThreadSimulation[] threads = new ThreadSimulation[cores];

		System.out.println("Progression : 0% ("+nbrSimulationsEffectuees+"/"+nbrSimulations+")");

		for(int core = 0; core < cores; core++){
			ThreadSimulation threadSim;
			/* On donne au premier thread le reste des simulations, par exemple : si on a 12 coeurs et que l'on souhaite faire 15 simulations, il restera 3 simulations à effectuer (qui ne peuvent être divisées par 12) */
			if(core == 0){
				threadSim = new ThreadSimulation(new JoueurIA(Couleur.ROUGE, new MonteCarlo(600)), new JoueurIA(Couleur.JAUNE, new AlphaBeta(new EvaluationNbrAlignement(), 3)), nbrSimulationsPerThread+(nbrSimulations%cores));
			} else {
				threadSim = new ThreadSimulation(new JoueurIA(Couleur.ROUGE, new MonteCarlo(600)), new JoueurIA(Couleur.JAUNE, new AlphaBeta(new EvaluationNbrAlignement(), 3)), nbrSimulationsPerThread);
			}
			
			threads[core] = threadSim;
			threadSim.start();
		}

		for(int t = 0; t < cores; t++){
			try {
				threads[t].join();
			} catch(InterruptedException e){}
		}
		for(int r = 0; r < cores; r++){
			nbrVictoiresJ1 += threads[r].getVictoiresJ1();
			nbrVictoiresJ2 += threads[r].getVictoiresJ2();
		}

		System.out.println("Nombre de parties simulées : "+nbrSimulations);
		System.out.println("Nombre de victoires du J1 ("+((double)nbrVictoiresJ1/(double)nbrSimulations*100)+"%) : "+nbrVictoiresJ1);
		System.out.println("Nombre de victoires du J2 ("+((double)nbrVictoiresJ2/(double)nbrSimulations*100)+"%) : "+nbrVictoiresJ2);
    }
}