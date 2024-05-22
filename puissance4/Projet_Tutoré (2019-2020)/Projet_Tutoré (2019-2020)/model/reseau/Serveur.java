package model.reseau;

import java.io.*;
import java.net.*;

public class Serveur {

    private ServerSocket serverSocket;
    private Socket socket;
    private PrintWriter pw;
    private BufferedReader buff;

    public Serveur(int port) throws IOException {
        serverSocket = new ServerSocket(port);

    }

    public String ecoute() throws IOException {
        socket = serverSocket.accept();
        InputStream entree = socket.getInputStream();
        OutputStream sortie = socket.getOutputStream();
        InputStreamReader lecture = new InputStreamReader(entree);
        buff = new BufferedReader(lecture);
        pw = new PrintWriter(sortie);
        return buff.readLine();
    }

    public PrintWriter getPrintWriter() {
        return this.pw;
    }

    public BufferedReader getBufferedReader() {
        return this.buff;
    }

    public void close() throws IOException {
        socket.close();
    }
}