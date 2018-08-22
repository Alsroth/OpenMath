package com.openmath.serveur;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import java.io.PrintWriter;

/**
*Cette classe permet de gérer l'enregisrement d'un nouvel utilisateur 
*/

public class Connection {
	private String login = "Test";
	private String password = "Testmdp";

	private static final String IDENTIFIANTS_PATH = "./files/identifiants.txt";
	private static final String INSCRIPTIONS_PATH = "./files/inscriptions.txt";

	// Getters & setters
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

	/**
	 * @autor swabahadine abdallah/ khan
	 * @param login,
	 *            mdp -> les identifiants
	 * @return true login et mdp font parti de identifiants.txt
	 */
	public boolean verifId(String login, String mdp) {
		Path path = Paths.get(IDENTIFIANTS_PATH);
		List<String> listId = null;
		try {
			listId = Files.readAllLines(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String ligne : listId) {
			String[] id = ligne.split("/");
			String lg = id[0];
			String mdps = id[1];
			if (lg.equals(login) && mdps.equals(mdp)) {
				return true;
			}
		}

		return false;
	}

	/**
	* Engregistrer le login, mdp, nom, prenom, date de naissance et courriel
	*/

	public boolean storeInscription(String login, String mdp, String nom, String prenom, String dn, String courriel) {
		Path path = Paths.get(INSCRIPTIONS_PATH);
		try(final PrintWriter pw = new PrintWriter(Files.newOutputStream(path))){
			pw.println(login + "/" + nom + "/" + prenom + "/" + dn + "/" + courriel);
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}	
	
		return true;
	}

	/**
	* Rajouter de login et mot de passe d'un IDENTIFIANTS_PATH
	*/

	public boolean addId(String login, String mdp){
		Path path = Paths.get(IDENTIFIANTS_PATH);
		try{
			String ligne = login+"/"+mdp;
			List<CharSequence> lignes = new ArrayList<CharSequence>();
			lignes.add(ligne);
			Files.write(path, lignes, StandardCharsets.UTF_8,StandardOpenOption.APPEND, StandardOpenOption.WRITE);
		}catch (IOException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}


}