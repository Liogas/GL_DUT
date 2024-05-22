package model.reseau;

import java.io.*;
import java.net.*;


/**
 * Client
 */
public class Client {

    private int port;
    private String adresseIP;
    private Socket socket;
    private InputStream entree;
    private OutputStream sortie;
    private InputStreamReader lecture;
    private BufferedReader buff;
    private PrintWriter pw;
    private boolean isConnected = false;

    public Client(int p, String addIP)  throws IOException {
        this.port = p;
        this.adresseIP = addIP;
        this.socket = new Socket(addIP,p);
        this.entree = socket.getInputStream();
        this.sortie = socket.getOutputStream();
        this.lecture = new InputStreamReader(entree);
        this.buff = new BufferedReader(lecture);
        this.pw = new PrintWriter(sortie);
    }

    public void connexion() {
        pw.println("connexion");
        pw.flush();
        this.isConnected = true;
    }

    public void envoyer(String str) throws IOException {
        if(this.isConnected) {
            pw.println(str);
            pw.flush();
        } else {
            throw new IOException("Not conneted to server");
        }
    }

    
    public String recevoir() throws IOException {
        return buff.readLine();
    }

    public void close() throws IOException {
        this.socket.close();
    }
}