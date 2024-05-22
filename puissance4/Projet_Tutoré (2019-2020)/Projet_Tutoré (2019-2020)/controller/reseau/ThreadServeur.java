package controller.reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import model.reseau.*;

public class ThreadServeur extends Thread {

    private Serveur serveur;
    private BufferedReader[] lecture;
    private PrintWriter[] ecriture;

    public ThreadServeur(Serveur s, BufferedReader[] l, PrintWriter[] e) {
        this.lecture = l;
        this.ecriture = e;
        this.serveur = s;
        Thread thread = new Thread();
        thread.start();
    }

    @Override
    public void run() {
        String str = "";
        Random r = new Random();
        int tourJoueur = r.nextInt(2);
        while (true) {

            BufferedReader buff;
            PrintWriter pw;
            pw = (PrintWriter) ecriture[tourJoueur];
            pw.println("A toi");
            pw.flush();
            pw = (PrintWriter) ecriture[changeTour(tourJoueur)];
            pw.println("pas a toi");
            pw.flush();
            buff = (BufferedReader) lecture[tourJoueur];
            try {
                str = buff.readLine();
                if(str.equals("fin")) {
                    PrintWriter tmp = (PrintWriter) ecriture[changeTour(tourJoueur)];
                    tmp.println(str);
                    tmp.flush();
                    break;
                }
            } catch (IOException e) {
                str = "fin";
            }
            

            tourJoueur = changeTour(tourJoueur);
            pw = (PrintWriter) ecriture[tourJoueur];
            pw.println(str);
            pw.flush();
        }
        
        try {
            serveur.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int changeTour(int tour) {
        if(tour==0) {
            return 1;
        } else {
            return 0;
        }
    }

}