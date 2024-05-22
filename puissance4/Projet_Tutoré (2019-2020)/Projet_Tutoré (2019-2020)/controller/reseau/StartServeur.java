package controller.reseau;

import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Scanner;

import model.reseau.*;


/**
 * StartServeur
 */
public class StartServeur {

    public static void main(String[] args) {
        int nbrConnexion = 0;
        try{
            String str;
            BufferedReader[] buff = new BufferedReader[2];
            PrintWriter[] pw = new PrintWriter[2];
            Scanner sc = new Scanner(System.in);
            System.out.println("Entrez le port : ");
            String port = sc.nextLine();
            String ip = InetAddress.getLocalHost().getHostAddress();
            System.out.println("L'adresse IP est " +ip+" et le port est "+port);
            System.out.println("Entrez ceci chez le client ->  "+ip+":"+port);
            Serveur s = new Serveur(Integer.parseInt(port));
            System.out.println("En attente de clients");
            while(true) {
                str = s.ecoute();
                if(str.equals("connexion")) {
                    pw[nbrConnexion] = s.getPrintWriter();
                    buff[nbrConnexion] = s.getBufferedReader();
                    nbrConnexion++;
                    System.out.println(nbrConnexion);
                }
                if(nbrConnexion % 2 == 0) {
                    ThreadServeur j = new ThreadServeur(s,buff, pw);
                    j.start();
                    nbrConnexion = 0;
                    buff = new BufferedReader[2];
                    pw = new PrintWriter[2];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}