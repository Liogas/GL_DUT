package controller.reseau;

import view.*;
import model.reseau.*;

import java.io.IOException;
import java.util.Scanner;

/**
 * StartClient
 *//*
public class StartClient {

    public static void main(String[] args) {
        try {
            Client j = new Client(28092, "localhost");
            j.connexion();
            String str = "";
            while(!str.equals("gagne")) {
                Scanner scanner = new Scanner( System.in );
                str = j.recevoir();
                System.out.println(str);
                if(str.equals("A toi")) {
                    System.out.print( "Veuillez saisir un entier : " );
                    j.envoyer(scanner.nextInt());
                } else {
                    System.out.println(j.recevoir());
                }
            }
            j.close();
        } catch(IOException e) {
            System.out.println("Erreur : "+e);
        }
    }
}*/