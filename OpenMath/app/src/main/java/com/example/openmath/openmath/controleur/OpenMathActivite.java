package com.example.openmath.openmath.controleur;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.util.Log;

import com.example.openmath.openmath.modele.ClientConnection;
import com.example.openmath.openmath.modele.Session;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Classe mere de toutes les Activity de l'application OpenMath
 * @author KHAN
 */
public class OpenMathActivite extends AppCompatActivity {

    private String utilisateurConnecte = null;
    public static final String ANONYME = "Anonyme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent!= null && intent.hasExtra("userConnecte")){
            utilisateurConnecte = intent.getStringExtra("userConnecte");
        }else{
            utilisateurConnecte = null;
        }

    }

    /**
     * Enregistrer le score dans le serveur
     * Est appele a chaque bonne reponse
     */
    public void saveCurrentScores(){
        Log.e("saveCurrentScores","saveCurrentScores appele");
        Intent intent = getIntent();
        JSONObject message = new JSONObject();
        Log.d("saveScore", "called");
        if(isConnecte()){

            Log.e("saveCurrentScores","nous sommes connecte appele");
            try {
                //String login = intent.getStringExtra("userConnecte");
                String login = Session.getCurrentUserName();
                if(intent.getExtras() != null) {
                    for (String key : intent.getExtras().keySet()) {
                        Log.e("saveCurrentScores", "s" + key);
                        // rajouter (questionCategory, note) pour chaque cle /valeur trouve dans l'Intent recu
                        if (key.startsWith("qc")) {
                            key = key.substring(2);
                            Log.e("saveCurrentScores", "trouve un qc");

                            message.put("qc", key);
                            Log.e("OpenMathActivite", Session.getScores().toString());
                            message.put("n", Session.getScores().get(key));
                        }

                    }

                    message.put("username", login);
                    message.put("messageType", "SAVE_SCORE");
                    Log.e("saveCurrentScores", message.toString());
                    ClientConnection client = new ClientConnection();
                    client.setLogin(Session.getCurrentUserName());
                    client.threadSeConnecter(ClientConnection.ADDRESS, ClientConnection.PORT, message.toString());
                }

            }catch (JSONException je) {
                Log.e("SAVE_SCORE", je.getMessage());
            }
        }else{
            Log.e("saveScore", "notLoggedIn");
        }

    }

    public boolean isConnecte(){
        return !Session.getCurrentUserName().equals("Anonyme");
    }

    public String getUtilisateurConnecte(){
        return utilisateurConnecte ;
    }

    public void setUtilisateurConnecte(String username){
        utilisateurConnecte = username;
    }

    public static void  deconnecter() {
        Session.setCurrentUserName("Anonyme");
        Session.setUser(null);
    }

}
