import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

/**
 * La classe <code>Fichier</code> est utilisée pour intéragir avec un fichier ".lab".
 *  
 * @version 1.1
 * @author Gaston Lions & Fabien Le Bec
 */

public class Fichier{
    private String nom;
    private int tailleLaby;
    private Point entree,sortie;
    private Player thesee;
    private boolean[][] posMur;


    /**
    * Constructeur destiné à la lecture d'un fichier.
    *
    * @param f le nom du fichier.
    */    
    public Fichier(String f){
        this.nom = f;
    }

    /**
    *Constructeur destiné à l'écriture dans un fichier.
    *
    * @param filename le nom du fichier.
    * @param taille la taille du labyrinthe.
    * @param posEntree la position de l'entrée.
    * @param posSortie la position de la sortie.
    * @param mur le tableau comportant la position des murs.
    */
    public Fichier(String filename, int taille, Point posEntree, Point posSortie, boolean[][] mur){
        this.nom = filename;
        this.tailleLaby = taille;
        this.entree = new Point(posEntree);
        this.sortie = new Point(posSortie);
        this.posMur = mur;
    }


    /**
    * Retourne le nom du fichier.
    *
    * @return le nom du fichier
    */
    public String getFile(){
        return this.nom;
    }

    /**
    * Retourne la taille du labyrinthe.
    *
    * @return la taille du labyrinthe.
    */
    public int getTailleLaby(){
        return this.tailleLaby;
    }

    /**
    * Retourne la position de l'entrée.
    *
    * @return la position de l'entrée.
    */
    public Player getThesee(){
        return this.thesee;
    }

    /**
    * Retourne la position de la sortie.
    *
    * @return la position de la sortie.
    */
    public Point getSortie(){
        return this.sortie;
    }

    /**
    * Retourne la position des murs.
    *
    * @return la position des murs.
    */
    public boolean[][] getPosMur(){
        return this.posMur;
    }


    /**
    * Permet d'écrire dans un fichier les données d'un nouveau labyrinthe.
    */
    public void writeFile(){
        String octetmur="";
        try {
            FileOutputStream fichier = new FileOutputStream("./Map/"+this.nom+".lab");

            try{
                fichier.write(this.tailleLaby);
                fichier.write((int) this.entree.getX());
                fichier.write((int) this.entree.getY());
                fichier.write((int) this.sortie.getX());
                fichier.write((int) this.sortie.getY());

                for(int i=0; i<this.tailleLaby; i++){
                    for(int j=0; j<this.tailleLaby; j++){
                        if(this.posMur[j][i]){
                            octetmur += "1";
                        } else {
                            octetmur += "0";
                        }
                        if(octetmur.length()==8){
                            fichier.write(Integer.parseInt(octetmur,2));
                            octetmur = "";
                        }
                    }
                }
            } catch(IOException ev){
                System.err.println("Ecriture impossible");
            }
            try{
                fichier.close();
            } catch(IOException ev){
                System.err.println("Erreur de fermeture");
            }
        } catch(FileNotFoundException e){
            System.err.println("Impossible de créer un nouveau fichier");
        }
    }

    /**
    * Permet de lire les données d'un labyrinthe dans un fichier.
    */
    public void openFile(){
        int bitvalue;
        int bitcount=7;
        byte byteread;

        try{
            FileInputStream fichier = new FileInputStream(this.nom);
            try{
                this.tailleLaby = (byte) fichier.read();
                this.thesee = new Player((byte) fichier.read(),(byte) fichier.read());
                this.sortie = new Point((byte) fichier.read(),(byte) fichier.read());
                this.posMur = new boolean[this.tailleLaby][this.tailleLaby];
                byteread = (byte) fichier.read();

                for(int i = 0; i < tailleLaby; i++){
                    for(int j = 0; j < tailleLaby; j++){
                        if(bitcount==-1){
                            bitcount = 7;
                            byteread = (byte) fichier.read();
                        }
                        bitvalue = (byteread >> bitcount) & 1;
                        if(bitvalue==0){
                            posMur[j][i] = false;
                        } else {
                            posMur[j][i] = true;
                        }
                        bitcount--;
                    }
                }
            } catch(IOException e){
                System.err.println("Erreur de lecture");
            }
            try{
                fichier.close();
            } catch(IOException e){
                System.err.println("Erreur de fermeture");
            }
        } catch(FileNotFoundException e){
            System.err.println("Erreur d'ouverture");
        }
    } 
}