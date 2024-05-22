package model.evaluation;

import model.joueur.*;
import model.grille.*;
import model.*;

import java.lang.Object;
import java.lang.Throwable;
import java.lang.Exception;

/**
 *
 * La classe EvaluationNbrCombinaisons herite de la classe Evaluation.
 * Elle possede un tableau representant la grille de jeu, chaque case est constituée du nombre de combinaisons possible.
 * 
 * @author Yanis DE SOUSA ALVES
 */

public class EvaluationNbrCombinaisons extends Evaluation {
	public final static int VIDE = 0;
    public final static int ROUGE = 1;
    public final static int JAUNE = 2;

    /**
     * La grille de jeu à noter.
     */
	private static int grille[][][] = new int[5][6][5];
	private int ligne; 
    private int colonne;
    private int profondeur;

    /**
     * La grille de la même taille que celle à noter comprenant les notes finales.
     */
    private int gri[][][];
	private int blacklist[][][];

	/**
     * Constructeur EvaluationNbrCombinaisons.
     * 
     */
	public EvaluationNbrCombinaisons(){
		this.ligne = grille.length;
		this.colonne = grille[1].length;
		this.profondeur = grille[1][1].length;
		this.gri = new int[this.ligne][this.colonne][this.profondeur];
		this.blacklist = new int[this.ligne][this.colonne][this.profondeur];

		//Crée le tableau avec les notes pour chaque case
		this.evaluationHorizontal();
		this.evaluationVertical();
		this.evaluationDiagonal1();
		this.evaluationDiagonal2();
		this.evaluationHorizontalProfondeur();
		this.evaluationDiagonalProfondeur1();
		this.evaluationDiagonalProfondeur2();

		for(int i=0; i<ligne; i++){
			for(int k=0; k<profondeur; k++){
				for(int j=0; j<colonne; j++){
					System.out.print(gri[i][j][k]+" ");
				}
				System.out.print("    ");
			}
			System.out.println();
		}
	}

	/**
     * 
     * @return le score total de la grille de jeu, sous la forme d'un int 
     *
     * @param gr
     *            La grille de jeu.
     * @param j
     *            Le joueur actuel.
     */
	@Override
	public int notation(Grille gr, Joueur j){
		Couleur couleurJoueur = j.getCouleur();
		int note = 0;

		for(int l = 0; l < this.ligne; l++){
			for(int c = 0; c < this.colonne; c++){
				for(int p = 0; p < this.profondeur; p++){
					Couleur couleurCase = gr.getEtatTabCase(l,c,p).getEtat();
					if(couleurCase == couleurJoueur){
						note += gri[l][c][p];
					} else if(couleurCase == Couleur.BLANC){
					} else if(couleurCase != couleurJoueur){
						note -= gri[l][c][p];
					}
				}
			}
		}
		return note;
	}

	/**
     * 
     * Calcule le nombre de combinaisons horizontales de chaque case.
     *
     */
	public void evaluationHorizontal(){
		int count = 1;
		int ll;
		int cc;
		int profondeur_limite;

		for(int p = 0; p < this.profondeur; p++){
	        for(int l = 0; l < this.ligne; l++){
	          	for(int c = 0; c < this.colonne; c++){
	          		for(int pblacklist = 0; pblacklist < this.profondeur; pblacklist++){
		          		for(int lblacklist = 0; lblacklist < this.ligne; lblacklist++){ //met le tab a 0
				          	for(int cblacklist = 0; cblacklist < this.colonne; cblacklist++){
				    			this.blacklist[lblacklist][cblacklist][pblacklist] = 0;
							}
						}
					}
					for(int z = 0; z<4; z++){
	        			for(int i = 1; i<4; i++){
		        			if(((c+i) < this.colonne) && ((this.blacklist[l][c+i][p] == 0)) && (count != 4)){
			    				count++;
					             
		    					if(count == 4){
		    						this.gri[l][c][p]++;
		    						count = 1;
		    						this.blacklist[l][c+i][p]= 1;
			    				}
		    				}else{
			        			try{
				        			if(((c+i) >= this.colonne) || (this.blacklist[l][c+i][p] == 1)){
				    					cc=(c+i-1);
					            		this.blacklist[l][cc][p]= 1;
					            		int difference;
					            		difference = (4-count);
					            		
					            		for(int y = 1; y<=difference; y++){
					            			if((c-y)>= 0){
					            				count++;
					            				if(count == 4){
					            					this.gri[l][c][p]++;
					            					count = 1;
						                			i=4;
					            				}
					            			}else{
					            				break;
					            			}
					            		}
					            		i=4;
					            		if(cc == c){
					            			z=4;
					            		}
						            }
					            }catch(IndexOutOfBoundsException e){
				            		int difference;
				            		difference = (4-count);
				            		
				            		for(int y = 1; y<=difference; y++){
				            			count++;
				            			if(count == 4){
				            				this.gri[l][c][p]++;
				            				count = 1;
				            			}
				            		}
			        			}
		        			}
	        			}
	        			count=1;
	        		}	        
	        	}
	        }
	    }
	}


	/**
     * 
     * Calcule le nombre de combinaisons verticales de chaque case.
     *
     */
	public void evaluationVertical(){
		int count = 1;
		int ll;
		int cc;
		int profondeur_limite;

		for(int p = 0; p < this.profondeur; p++){
			for(int l = 0; l < this.ligne; l++){
	            for(int c = 0; c < this.colonne; c++){
	            	for(int pblacklist = 0; pblacklist < this.profondeur; pblacklist++){
		              	for(int lblacklist = 0; lblacklist < this.ligne; lblacklist++){ //met le tab a 0
		                	for(int cblacklist = 0; cblacklist < this.colonne; cblacklist++){
		              			this.blacklist[lblacklist][cblacklist][pblacklist] = 0;
		          			}
		        		}
		        	}

		        	for(int z = 0; z<4; z++){
		              	for(int i = 1; i<4; i++){
			              	if(((l+i) < this.ligne) && ((this.blacklist[l+i][c][p] == 0)) && (count != 4)){
			                	count++;
			                   
				                if(count == 4){
				                  	this.gri[l][c][p]++;
				                  	count = 1;
				                  	this.blacklist[l+i][c][p]= 1;
				                }
			                }else{
			                  	try{
			                    	if(((l+i) >= this.ligne) || (this.blacklist[l+i][c][p] == 1)){
					                    ll=(l+i-1);
				                        this.blacklist[ll][c][p]= 1;
				                        int difference;
				                        difference = (4-count);
				                        
				                        for(int y = 1; y<=difference; y++){
				                          	if((l-y)>= 0){
				                            	count++;
				                            	if(count == 4){
				                              		this.gri[l][c][p]++;
				                              		count = 1;
				                                	i=4;
				                            	}
				                          	}else{
				                            	break;
				                          	}
				                        }
				                        i=4;
				                        if(ll == l){
				                         	z=4;
				                        }
				                    }
			                    }catch(IndexOutOfBoundsException e){
			                      	int difference;
			                      	difference = (4-count);
			                      
			                      	for(int y = 1; y<=difference; y++){
			                        	count++;
			                        	if(count == 4){
			                          		this.gri[l][c][p]++;
			                          		count = 1;
			                        	}
			                      	}
			                  	}
			                }
		            	}
		            	count=1;
		            }         
	        	}
	        }
	    }
	}

	/**
     * 
     * Calcule le nombre de combinaisons en diagonales de gauche a droite de chaque case.
     *
     */
	public void evaluationDiagonal1(){
		int count = 1;
		int ll;
		int cc;
		int profondeur_limite;

		for(int p = 0; p < this.profondeur; p++){
	        for(int l = 0; l < this.ligne; l++){
	            for(int c = 0; c < this.colonne; c++){
	            	for(int pblacklist = 0; pblacklist < this.profondeur; pblacklist++){
		              	for(int lblacklist = 0; lblacklist < this.ligne; lblacklist++){ //met le tab a 0
		                	for(int cblacklist = 0; cblacklist < this.colonne; cblacklist++){
		              			this.blacklist[lblacklist][cblacklist][pblacklist] = 0;
		          			}
		        		}
		        	}
			        for(int z = 0; z<4; z++){
			            for(int i = 1; i<4; i++){
			            	if(((l+i) < this.ligne) && ((c+i) < this.colonne) && ((this.blacklist[l+i][c+i][p] == 0)) && (count != 4)){
			                	count++;
			                   
				                if(count == 4){
				                	this.gri[l][c][p]++;
				                	count = 1;
				                	this.blacklist[l+i][c+i][p]= 1;
				                }
			            	}else{
			                  	try{
			                    	if((((l+i) >= this.ligne) && ((c+i) >= this.colonne)) || (this.blacklist[l+i][c+i][p] == 1)){
			                    		ll=(l+i-1);
			                    		cc=(c+i-1);
			                        	this.blacklist[ll][cc][p]= 1;
			                        	int difference;
			                        	difference = (4-count);
			                        
				                		for(int y = 1; y<=difference; y++){
					                        if(((l-y) >= 0) && ((c-y) >= 0)){
					                            count++;
					                            if(count == 4){
					                            	this.gri[l][c][p]++;
					                            	count = 1;
					                                i=4;
					                            }
					                        }else{
					                            break;
					                        }
				           			    }
				           			    count=1;
				                        i=4;
				                        if((ll == l) && (cc == c)){
				                        	z=4;
				                        }
				                    }
			                    }catch(IndexOutOfBoundsException e){
			                        ll=(l+i-1);
									cc=(c+i-1);
									this.blacklist[ll][cc][p]= 1;
									int difference;
									difference = (4-count);
									for(int y = 1; y<=difference; y++){
			                          	if(((l-y) >= 0) && ((c-y) >= 0)){
				                            count++;
				                            if(count == 4){
				                              	this.gri[l][c][p]++;
				                              	count = 1;
				                                i=4;
				                            }
			                            }else{
			                            	break;
			                          	}
			                        }
			            			i=4;
			            			if((ll == l) && (cc == c)){
			                          	z=4;
			                        }
			                    count =1;
			                	}
			                }
			            }
			        }
	    		}
			}
		}
	}

	/**
     * 
     * Calcule le nombre de combinaisons en diagonales de droite a gauche de chaque case.
     *
     */
	public void evaluationDiagonal2(){
		int count = 1;
		int ll;
		int cc;
		int profondeur_limite;

		for(int p = 0; p < this.profondeur; p++){
			for(int l = this.ligne; l >= 0; l--){
	            for(int c = 0; c < this.colonne; c++){
	            	for(int pblacklist = 0; pblacklist < this.profondeur; pblacklist++){
		            	for(int lblacklist = 0; lblacklist < this.ligne; lblacklist++){
		                	for(int cblacklist = 0; cblacklist < this.colonne; cblacklist++){
		              			this.blacklist[lblacklist][cblacklist][pblacklist] = 0;
		         			}
		        		}
		        	}
			        for(int z = 0; z<4; z++){
			            for(int i = 1; i<4; i++){
			              	if(((l-i) >= 0) && ((c+i) < this.colonne) && ((this.blacklist[l-i][c+i][p] == 0)) && (count != 4)){
			                	count++;
			                          
			                    if(count == 4){
			                    	try{
				              			this.gri[l][c][p]++;
				              			count = 1;
				             			this.blacklist[l-i][c+i][p]= 1;
				                    }catch(IndexOutOfBoundsException ex){
				                    	//gri[this.ligne-1][c]++;
				              			count = 1;
				             			this.blacklist[l-i][c+i][p]= 1;
				                    }
			            		}
			        		}else{
		                  		try{
		                    		if((((l-i) < 0) && ((c+i) >= this.colonne)) || (this.blacklist[l-i][c+i][p] == 1)){
		                          		try{
			                    			ll=(l-i+1);
			                    			cc=(c+i-1);
			                        		this.blacklist[ll][cc][p]= 1;
			                    		}catch(IndexOutOfBoundsException e){
			                    			ll=0;
			                    			cc=(c+i-1);
			                    			this.blacklist[ll][cc][p]= 1;
			                    		}
			                    		
			                        	int difference;
			                        	difference = (4-count);
			                        
			                 			for(int y = 1; y<=difference; y++){
			                          		if(((l+y) < (this.colonne-1)) && ((c-y) >= 0)){
			                            		count++;
			                            		if(count == 4){
			                              			this.gri[l][c][p]++;
			                              			count = 1;
			                                		i=4;
			                            		}
			                          		}else{
			                            		break;
			                          		}
			           			    	}
			                        	i=4;
			                       	 	if((ll == l) && (cc == c)){
			                          		z=4;
			                        	}
			                    	}
		                    	}catch(IndexOutOfBoundsException e){
	                        		try{
			                    			ll=(l-i+1);
			                    			cc=(c+i-1);
			                        		this.blacklist[ll][cc][p]= 1;
			                    		}catch(IndexOutOfBoundsException exception){
			                    			ll=0;
			                    			cc=(c+i-1);
			                    			this.blacklist[ll][cc][p]= 1;
			                    		}
			                    		
			                        	int difference;
			                        	difference = (4-count);
			                        
			                 			for(int y = 1; y<=difference; y++){
			                          		if(((l+y) < this.colonne-1) && ((c-y) >= 0)){
			                            		count++;
			                            		try{
				                            		if(count == 4){
				                              			this.gri[l][c][p]++;
				                              			count = 1;
				                                		i=4;
				                            		}
				                            	}catch(IndexOutOfBoundsException excep){
				                            		if(count == 4){
				                              			this.gri[l][c][p]++;
				                              			count = 1;
				                                		i=4;
				                            		}
				                            	}
			                          		}else{
			                            		break;
			                          		}
			           			    	}
			                        	i=4;
			                       	 	if((ll == l) && (cc == c)){
			                          		z=4;
			                        	}
									count =1;
		                    	}
	                    	}
	         	        }
	           		count=1;     
					}
				}
			}
		}
	}

	/**
     * 
     * Calcule le nombre de combinaisons horizontales en profondeur de chaque case.
     *
     */
	public void evaluationHorizontalProfondeur(){
		int count = 1;
		int ll;
		int cc;
		int profondeur_limite;

		for(int p = 0; p < this.profondeur; p++){
	        for(int l = 0; l < this.ligne; l++){
	          	for(int c = 0; c < this.colonne; c++){
	          		for(int pblacklist = 0; pblacklist < this.profondeur; pblacklist++){
		          		for(int lblacklist = 0; lblacklist < this.ligne; lblacklist++){ //met le tab a 0
				          	for(int cblacklist = 0; cblacklist < this.colonne; cblacklist++){
				    			this.blacklist[lblacklist][cblacklist][pblacklist] = 0;
							}
						}
					}
					for(int z = 0; z<4; z++){
	        			for(int i = 1; i<4; i++){
		        			if(((p+i) < this.profondeur) && ((this.blacklist[l][c][p+i] == 0)) && (count != 4)){
			    				count++;
					             
		    					if(count == 4){
		    						this.gri[l][c][p]++;
		    						count = 1;
		    						this.blacklist[l][c][p+i]= 1;
			    				}
		    				}else{
			        			try{
				        			if(((p+i) >= this.profondeur) || (this.blacklist[l][c][p+i] == 1)){
	                    				profondeur_limite = (p+i-1);
					            		this.blacklist[l][c][profondeur_limite]= 1;
					            		int difference;
					            		difference = (4-count);
					            		
					            		for(int y = 1; y<=difference; y++){
					            			if((p-y)>= 0){
					            				count++;
					            				if(count == 4){
					            					this.gri[l][c][p]++;
					            					count = 1;
						                			i=4;
					            				}
					            			}else{
					            				break;
					            			}
					            		}
					            		i=4;
					            		if(profondeur_limite == p){
					            			z=4;
					            		}
						            }
					            }catch(IndexOutOfBoundsException e){
				            		int difference;
				            		difference = (4-count);
				            		
				            		for(int y = 1; y<=difference; y++){
				            			count++;
				            			if(count == 4){
				            				this.gri[l][c][p]++;
				            				count = 1;
				            			}
				            		}
			        			}
		        			}
	        			}
	        			count=1;
	        		}	        
	        	}
	        }
	    }
	}

	/**
     * 
     * Calcule le nombre de combinaisons en diagonales profondeur de gauche a droite de chaque case.
     *
     */
	public void evaluationDiagonalProfondeur1(){
		int count = 1;
		int ll;
		int cc;
		int profondeur_limite;

		for(int p = 0; p < this.profondeur; p++){
	        for(int l = 0; l < this.ligne; l++){
	            for(int c = 0; c < this.colonne; c++){
	            	for(int pblacklist = 0; pblacklist < this.profondeur; pblacklist++){
		              	for(int lblacklist = 0; lblacklist < this.ligne; lblacklist++){ //met le tab a 0
		                	for(int cblacklist = 0; cblacklist < this.colonne; cblacklist++){
		              			this.blacklist[lblacklist][cblacklist][pblacklist] = 0;
		          			}
		        		}
		        	}
			        for(int z = 0; z<4; z++){
			            for(int i = 1; i<4; i++){
			            	if(((l+i) < this.ligne) && ((p+i) < this.profondeur) && ((this.blacklist[l+i][c][p+i] == 0)) && (count != 4)){
			                	count++;
			                   
				                if(count == 4){
				                	this.gri[l][c][p]++;
				                	count = 1;
				                	this.blacklist[l+i][c][p+i]= 1;
				                }
			            	}else{
			                  	try{
			                    	if((((l+i) >= this.ligne) && ((c+i) >= this.colonne) && ((p+i) >= this.profondeur)) || (this.blacklist[l+i][c+i][p+i] == 1)){
			                    		ll=(l+i-1);
			                    		cc=(c+i-1);
			                    		profondeur_limite=(p+i-1);
			                        	this.blacklist[ll][cc][profondeur_limite]= 1;
			                        	int difference;
			                        	difference = (4-count);
			                        
				                		for(int y = 1; y<=difference; y++){
					                        if(((l-y) >= 0) && ((c-y) >= 0) && ((p-y) >= 0)){
					                            count++;
					                            if(count == 4){
					                            	this.gri[l][c][p]++;
					                            	count = 1;
					                                i=4;
					                            }
					                        }else{
					                            break;
					                        }
				           			    }
				           			    count=1;
				                        i=4;
				                        if((ll == l) && (cc == c) && (profondeur_limite == p)){
				                        	z=4;
				                        }
				                    }
			                    }catch(IndexOutOfBoundsException e){
			                        ll=(l+i-1);
									cc=(c+i-1);
									profondeur_limite=(p+i-1);
									this.blacklist[ll][cc][profondeur_limite]= 1;
									int difference;
									difference = (4-count);
									for(int y = 1; y<=difference; y++){
			                          	if(((l-y) >= 0) && ((c-y) >= 0) && ((p-y) >= 0)){
				                            count++;
				                            if(count == 4){
				                              	this.gri[l][c][p]++;
				                              	count = 1;
				                                i=4;
				                            }
			                            }else{
			                            	break;
			                          	}
			                        }
			            			i=4;
			            			if((ll == l) && (cc == c) && (profondeur_limite == p)){
			                          	z=4;
			                        }
			                    count =1;
			                	}
			                }
			            }
			        }
	    		}
			}
		}
	}

	/**
     * 
     * Calcule le nombre de combinaisons en diagonales profondeur de droite a gauche de chaque case.
     *
     */
	public void evaluationDiagonalProfondeur2(){
		int count = 1;
		int ll;
		int cc;
		int profondeur_limite;

		for(int p = 0; p < this.profondeur; p++){
			for(int l = this.ligne; l >= 0; l--){
	            for(int c = 0; c < this.colonne; c++){
	            	for(int pblacklist = 0; pblacklist < this.profondeur; pblacklist++){
		            	for(int lblacklist = 0; lblacklist < this.ligne; lblacklist++){
		                	for(int cblacklist = 0; cblacklist < this.colonne; cblacklist++){
		              			this.blacklist[lblacklist][cblacklist][pblacklist] = 0;
		         			}
		        		}
		        	}
			        for(int z = 0; z<4; z++){
			            for(int i = 1; i<4; i++){
			              	if(((l-i) >= 0) && ((c+i) < this.colonne) && ((p+i) < this.profondeur) && ((this.blacklist[l-i][c+i][p+i] == 0)) && (count != 4)){
			                	count++;                          
			                    if(count == 4){
			                    	try{
				              			this.gri[l][c][p]++;
				              			count = 1;
				             			this.blacklist[l-i][c+i][p+i]= 1;
				                    }catch(IndexOutOfBoundsException ex){
				              			count = 1;
				             			this.blacklist[l-i][c+i][p+i]= 1;
				                    }
			            		}
			        		}else{
		                  		try{
		                    		if((((l-i) < 0) && ((c+i) >= this.colonne) && ((p+i) >= this.profondeur)) || (this.blacklist[l-i][c+i][p+i] == 1)){
		                          		try{
			                    			ll=(l-i+1);
			                    			cc=(c+i-1);
			                    			profondeur_limite=(p+i-1);
			                        		this.blacklist[ll][cc][profondeur_limite]= 1;
			                    		}catch(IndexOutOfBoundsException e){
			                    			ll=0;
			                    			cc=(c+i-1);
			                    			profondeur_limite=(p+i-1);
			                    			this.blacklist[ll][cc][profondeur_limite]= 1;
			                    		}
			                    		
			                        	int difference;
			                        	difference = (4-count);
			                        
			                 			for(int y = 1; y<=difference; y++){
			                          		if(((l+y) < (this.colonne-1)) && ((c-y) >= 0) && ((p-y) >= 0)){
			                            		count++;
			                            		if(count == 4){
			                              			this.gri[l][c][p]++;
			                              			count = 1;
			                                		i=4;
			                            		}
			                          		}else{
			                            		break;
			                          		}
			           			    	}
			                        	i=4;
			                       	 	if((ll == l) && (cc == c) && (profondeur_limite == p)){
			                          		z=4;
			                        	}
			                    	}
		                    	}catch(IndexOutOfBoundsException e){
	                        		try{
			                    			ll=(l-i+1);
			                    			cc=(c+i-1);
			                    			profondeur_limite=(p+i-1);
			                        		this.blacklist[ll][cc][profondeur_limite]= 1;
			                    		}catch(IndexOutOfBoundsException exception){
			                    			ll=0;
			                    			cc=(c+i-1);
			                    			try{
				                    			profondeur_limite=(p+i-1);
				                    			this.blacklist[ll][cc][profondeur_limite]= 1;
				                    		}catch(IndexOutOfBoundsException except){
				                    			profondeur_limite=(this.profondeur-1);
				                    			this.blacklist[ll][cc][profondeur_limite]= 1;
				                    		}
			                    		}
			                    		
			                        	int difference;
			                        	difference = (4-count);
			                        
			                 			for(int y = 1; y<=difference; y++){
			                          		if(((l+y) < this.colonne-1) && ((c-y) >= 0) && ((p-y) >= 0)){
			                            		count++;
			                            		try{
				                            		if(count == 4){
				                              			this.gri[l][c][p]++;
				                              			count = 1;
				                                		i=4;
				                            		}
				                            	}catch(IndexOutOfBoundsException excep){
				                            		if(count == 4){
				                              			this.gri[l][c][p]++;
				                              			count = 1;
				                                		i=4;
				                            		}
				                            	}
			                          		}else{
			                            		break;
			                          		}
			           			    	}
			                        	i=4;
			                       	 	if((ll == l) && (cc == c) && (profondeur_limite == p)){
			                          		z=4;
			                        	}
									count =1;
		                    	}
	                    	}
	         	        }
	           		count=1;     
					}
				}
			}
		}
	}
}