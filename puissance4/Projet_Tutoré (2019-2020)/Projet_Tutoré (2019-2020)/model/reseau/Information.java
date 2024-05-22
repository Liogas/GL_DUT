package model.reseau;

import java.io.*;

/**
 * Information
 */
public class Information {

    private String encodage;
    private int colonne;
    private int profondeur;

    public Information(String str) {
        this.encodage = str;
        String[] info = str.split("/");
        this.colonne = Integer.valueOf(info[0]);
        this.profondeur = Integer.valueOf(info[1]);
    }

    public Information(int c, int p) {
        this.colonne = c;
        this.profondeur = p;
        this.encodage = c+"/"+p;
    }

    public String getEncodage() {
        return this.encodage;
    }
    
    public int getColonne() {
        return this.colonne;
    }

    public int getProfondeur() {
        return this.profondeur;
    }


}