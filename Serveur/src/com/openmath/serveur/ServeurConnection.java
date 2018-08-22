package com.openmath.serveur;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.json.*;

/**
*le serveur de OpenMath
*@author KHAN 
*/

public class ServeurConnection {

	private Connection monServeur = new Connection();

	/**
	* traiter le message reçu et ecrire la réponse dans pw
	*/
	void traiter(String message, PrintWriter pw){
		System.out.println("traiter called again");
		System.out.println(message);
		JSONObject jo = null;
		try {
			jo = new JSONObject(message);
			String messageType = jo.getString("messageType");
			String username = jo.getString("username");

			switch(messageType){
				case "LOGIN": {
					String password = jo.getString ("password");
					if(monServeur.verifId(username,password)){
						System.out.println("Login succeed");
						pw.println(true);
					}else{
						System.out.println("Login failed");
						pw.println(false);
						

					}
					break;
				}case "INSCRIPTION":{
					String password = jo.getString("password");
					String nom = jo.getString("nom");
					String prenom = jo.getString("prenom");
					String dn = jo.getString("dateDeNaissance");
					String courriel = jo.getString("courriel");
					if(monServeur.addId(username,password)){
						System.out.println("Inscription succeed");
						monServeur.storeInscription(username,password, nom, prenom, dn, courriel);
						pw.println(true);

					}else{
						System.out.println("Inscription failed");
						pw.println(false);
					}
					break;
				} case "SAVE_SCORE":{
					System.out.println("SAVE_SCORE tried");
					BufferedReader br = null;
					boolean trouve = false;

					
					System.out.println(jo.getInt("n"));
					boolean res  = NotesManager.addScoreForUser(username, jo.getString("qc"),jo.getInt("n"));
					
					if(res) pw.println("score saved");
					else pw.println("score not saved");
				} case "GET_SCORE": {
					System.out.println("GET_SCORE tried");
					JSONObject allNotesForUser = NotesManager.getScoresForUser(username);
					System.out.println(allNotesForUser);
					pw.println(allNotesForUser.toString());
				}

			}
		}catch(JSONException je){
			je.printStackTrace();
			pw.println("small problem");
		}
	}


	/**
	* Créer et executer un serveur en lancant un nouveu Thread 
	*/
	public void createAndRunServeur() {
		System.out.println("main");
		// ServerSocket serveurSocket;
		//final Socket clientSocket;
		//final BufferedReader in;
		//final PrintWriter out;
		try {
			ServerSocket serveurSocket = new ServerSocket(2009);

			while(true){
				final Socket clientSocket = serveurSocket.accept();
				//serveurSocket.close();
				final PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
				final BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				Thread recevoir = new Thread(new Runnable() {
					String login, mdp;

					@Override
					public void run() 
					{
						System.out.println("Serveur a demarre");
						try {
							String message = in.readLine();
							System.out.println(message);
							while (message!= null){
								traiter(message, out);
								out.flush();
								//login = in.readLine();
								message = in.readLine();
							
							}
							// sortir de la boucle si le client a déconecté
							System.out.println("Client déconecté");
							// fermer le flux et la session socket
							in.close();
							out.close();
							clientSocket.close();
							//serveurSocket.close();
						} catch (IOException e) {

							e.printStackTrace();
						}
					}
				});
				recevoir.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		ServeurConnection sc = new ServeurConnection();
		sc.createAndRunServeur();
	}
}

