package com.openmath.serveur;

import java.io.*;
import org.json.*;
import java.util.*;

/**
* Cette Classe permet de enregistrer et récuperer les notes des utilisateurs dans un format JSON dans le fichier 
* files/notes.txt
* @author KHAN
*/

public class NotesManager {



	/** 
	* Recuperer les score d'un utilisateur donnée en paramètre 
	*/
	public static JSONObject getScoresForUser(String username) { 
		System.out.println("getScoresForUser " + username);

		try {
			JSONObject grosObjet = getTotalNotes();
			JSONArray touteArray = grosObjet.getJSONArray("notes");
			for(int i = 0; i<touteArray.length(); i++) {
				JSONObject userNotes = (JSONObject)touteArray.get(i);
				if(username.equals(userNotes.getString("username"))) {
					System.out.println(userNotes);
					return userNotes;
				}
			}

		} catch(JSONException je) {
			je.printStackTrace();
		}
		JSONObject res  = new JSONObject();
		res.put("username",username);
		return res;
	}

	/**
	* Mettre à jour les notes d'un utilisateur existant (dans le fichier file/notes.txt)
	* @return tout le JSON entier si le mise à jour a réusssi
	*/
	public static JSONObject setScoresForUser(String username, JSONObject userNotes) {
		System.out.println("setScoresForUser : " + username);
		try {
			JSONObject grosObjet = getTotalNotes();
			JSONArray touteArray = grosObjet.getJSONArray("notes");
			for(int i = 0; i<touteArray.length(); i++) {
				JSONObject un = (JSONObject)touteArray.get(i);
			
				if(username.equals(un.getString("username"))) {
					System.out.println("putting in "+ i + " " + userNotes);
					touteArray.put(i, userNotes);
					return grosObjet;
				}
			}

		} catch(JSONException je) {
			je.printStackTrace();
		}
		return null;
	}

	/**
	* Renvoie tout le JSON correspondant aux tous les notes de tous les utilisateurs 
	*/
	public static JSONObject getTotalNotes() {
		String grosTexte = "";

		

			try {
				BufferedReader br = new BufferedReader(new FileReader("files/notes.txt"));
				for(String ligne = br.readLine(); ligne != null; ligne = br.readLine()) {
					grosTexte += ligne;
				} 
				br.close();
			} catch(FileNotFoundException fnfe) {
				fnfe.printStackTrace();
			} catch(IOException ioe) {
				ioe.printStackTrace();
			}
			try {
				return new JSONObject(grosTexte);
			} catch(JSONException je) {
				je.printStackTrace();
			}
		
		return null;
	}

	/**
	* Ecrire tout le JSON entier de nouveau dans files/notes.txt
	*/

	public static boolean ecrireToutesLesNotes(JSONObject grosObjet) {
		if(grosObjet == null) return false;
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("files/notes.txt"));
			pw.println(grosObjet.toString());
			pw.close();
			return true;
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return false;
	}

	/**
	* Rajouter /mise à jour de note de questionCategory pour username
	*/

	public static boolean addScoreForUser(String username, String questionCategory, int note) {
		try {
			JSONObject grosObjet = getTotalNotes();
			JSONObject userNotes = getScoresForUser(username);
			System.out.println("userNotes 97 : " + userNotes);
			// utilisateur n'existe pas encore
			if(userNotes.names().length() == 1) {
				JSONObject newUserNote = new JSONObject();
				newUserNote.put("username",username);
				
				newUserNote.put(questionCategory, note);
				System.out.println("controverse : " +newUserNote);
				grosObjet.getJSONArray("notes").put(newUserNote);

			//l'utilisateur existe déjà

			} else {
				userNotes.put(questionCategory, note);
				System.out.println("before putting : " + grosObjet);
				JSONObject go  = setScoresForUser(username, userNotes);
				if(go != null) { grosObjet = go;}
				System.out.println("very suspicios " +userNotes);
				System.out.println(grosObjet);

			}

			return ecrireToutesLesNotes(grosObjet);

		} catch(JSONException je) {
			je.printStackTrace();
		}
		return false;		
	}
}