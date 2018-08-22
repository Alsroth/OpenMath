package com.example.openmath.openmath.modele;

/**
 * Created by swabahadine & KHAN on 08/03/2018.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import android.util.Log;

public class ClientConnection {
    private String login;
    private String password;
    private static boolean connection;
    /**
     *  champ stocker le resultat de cet appel
     */
    private String resultMessage = "";

    /**
     * l'addresse Ip du serveur (a changer chaque fois qu'on change d'wifi doit être identique au celui de serveur )
     */
    public static final String ADDRESS =  "10.188.185.94."; //"172.20.10.3";
    public static final int PORT = 2009;

    public ClientConnection(String login, String password) {
        this.login = login;
        this.password = password;
        //this.seConnecter();

    }

    public ClientConnection(){

    }

    // Getters & setters
    public static boolean isConnection() {
        return connection;
    }

    public void setConnection(boolean b) {
        connection = b;
    }

    public String getLogin() {
        return login;
    }


    public void setLogin(String login) {
        this.login = login;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getResultMessage(){return resultMessage;}

    /*
     * Méthode qui communique avec le serveur port 2009
     */
    public void threadSeConnecter(final String adresse , final int port, final String message, MessageType mt ) {
        boolean b;
        Thread connect = new Thread(new Runnable() {
            @Override
            public void run() {
                setConnection(seConnecter(adresse,port, message));

            }
        });
        connect.start();
        // attendre jusqu'à que le thread de connexion est terminé
        try {
            connect.join();
        } catch (InterruptedException ie){
            Log.e("ClientConnection", ie.getMessage());
        }
    }

    public void threadSeConnecter(final String adresse, final int port, final String message) {
        threadSeConnecter(adresse, port, message, MessageType.LOGIN);
    }

    /** se connecter au serveur avec l'addresse et le port par defaut */
    public void threadSeConnecter(String message){
        threadSeConnecter(ADDRESS, PORT, message);
    }

    //public void threadSeConnecter(String message, MessageType mt) { threadSeConnecter(ADDRESS, PORT,);}

    public boolean seConnecter(final String adresse, final int port, final String message){
        return seConnecter(adresse,port, message, MessageType.LOGIN);
    }

    /**
     * Renvoie un message au serveur qui est à addresse:port
     * @param adresse
     * @param port
     * @param message
     * @param mt le type de message
     * @return
     */
    public boolean seConnecter(final String adresse ,final int port, final String message, final MessageType mt) {
        Log.d("ClientConnection", message);
        final Socket clientSocket;
        final BufferedReader in;
        final PrintWriter out;

        try {

             /*
	         * les informations du serveur ( port et adresse IP ou nom d'hote
	         * 127.0.0.1 est l'adresse local de la machine
	         * 10.0.2.2 est l'adresse local de l'émulateur
	         */

            clientSocket = new Socket(adresse, port);



            //flux pour envoyer
            out = new PrintWriter(clientSocket.getOutputStream());
            //flux pour recevoir
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));



            // Pour envoyer

           // out.println(getLogin());
            //out.println(getPassword());

            out.println(message);

            //Log.i("ClientConnection",getLogin());
            //Log.i("ClientConnection",getPassword());
            out.flush();
            String msg;

            resultMessage = "";

            try {
                Log.d("ClientConnection","interieure");
                msg = in.readLine();
                Log.e("Connection","msg:"+msg);
                while (msg != null) {
                    if(mt == MessageType.LOGIN || mt == MessageType.INSCRIPTION ){
                        setConnection(Boolean.valueOf(msg));
                    }
                    resultMessage += msg;


                    msg = null;
                }
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                Log.e("Connectioninside",e.getMessage());
                e.printStackTrace();
            }

        } catch (IOException e) {
            Log.e("Connection", e.getMessage());
            e.printStackTrace();
        }
        Log.d("ClientConnection", "Result: " + isConnection());
        return isConnection();
    }

}