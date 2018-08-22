package com.example.openmath.openmath.controleur;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.openmath.openmath.R;
import com.example.openmath.openmath.modele.ClientConnection;
import com.example.openmath.openmath.modele.MessageType;
import com.example.openmath.openmath.modele.Session;

import org.json.*;

import java.util.Iterator;

public class IdentificationActivite extends OpenMathActivite
{

    private TextView phraseDeBienvenue;
    private TextView textLogin;
    private TextView textMdp;
    private EditText champLogin;
    private EditText champMDP;
    private boolean isTexteChampsSuffisant = false ;
    private Button boutonConnexion;
    private Context contexte = this ;
    public static final int MINIMUM_CARACTERE = 3;

    /**
     * @author Oualid Benazzouz
     * Cette fonction permet de créer le menu déroulant présent dans l'activité identificationActivite.
     * doc: https://developer.android.com/guide/topics/ui/menus.html
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bienvenue,menu);
        return true;
    }

    /**
     * @atuhor Oualid BENAZZOUZ
     * Cette méthodes liste les choix atteignable depuis le menu déroulant dans l'activité apropro.
     * doc: https://developer.android.com/guide/topics/ui/menus.html
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.aproposBienvenueActivite:
                Intent intent = new Intent(IdentificationActivite.this, AProposActivite.class);
                startActivity(intent);
        }
        switch (item.getItemId()){
            case R.id.boutonPrecedent:
                this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Cette méthode créer l'activité apropros à partir de son contenu XML
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identification_activite);

        //On relie les widgets des activité au code java.
        phraseDeBienvenue = findViewById(R.id.phraseDeBienvenue);
        textLogin = findViewById(R.id.textLogin);
        textMdp = findViewById(R.id.textMDP);
        champLogin = findViewById(R.id.champLogin);
        champMDP = findViewById(R.id.champMDP);
        boutonConnexion = findViewById(R.id.boutonConnexion);
        /* Le bouton de connexion est désactivé tant que les champs login et mdp
         ne font pas au moins la valeur de MINIMUM_CARACTERE. (Le double pour le mot de passe) */
        champLogin.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

                if(champLogin.getText().length() >= MINIMUM_CARACTERE
                        && champMDP.getText().length() >= (MINIMUM_CARACTERE * 2) )
                {
                    isTexteChampsSuffisant = true ;
                }
                else
                {
                    isTexteChampsSuffisant = false ;
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });

        /* Le bouton de connexion est désactivé tant que les champs login et mdp
        ne font pas au moins la valeur de MINIMUM_CARACTERE. (Le double pour le mot de passe) */
        champMDP.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

                if(champLogin.getText().length() >= MINIMUM_CARACTERE
                        && champMDP.getText().length() >= (MINIMUM_CARACTERE * 2) )
                {
                    isTexteChampsSuffisant = true ;
                }
                else
                {
                    isTexteChampsSuffisant = false ;
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });

        /*
         * On redéfini les listener des boutons pour pouvoir rediriger les utiliseurs vers une nouvelle interface.
         * Et on fait appel au serveur pour vérifier la validité des informtations données.
         */
        boutonConnexion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if(!isTexteChampsSuffisant)
                {
                    Toast.makeText(contexte, "3 caractères minimum pour le login et 6 pour le mdp", Toast.LENGTH_SHORT).show();
                }
                else
                {


                Log.e("Identification", "cliquer");
                JSONObject messageObject = null;
                try{
                    messageObject = new JSONObject();
                    messageObject.put("username", champLogin.getText().toString());
                    messageObject.put("password", champMDP.getText().toString());
                    messageObject.put("messageType","LOGIN");
                }catch (JSONException je){
                    Log.e("identificationActivite", je.getMessage());
                }
                //ClientConnection client = new ClientConnection("Swabahadine","mdpswabah");
                ClientConnection client = new ClientConnection(champLogin.getText().toString(),champMDP.getText().toString());
                System.out.println(champLogin.getText().toString() + "  "+ champMDP.getText().toString());
                System.out.println(client.isConnection());

                client.threadSeConnecter(ClientConnection.ADDRESS, ClientConnection.PORT, messageObject.toString());

                try {
                    Thread.sleep(100);

                    boolean b = client.isConnection();
                    if (b) {
                        JSONObject scoreGetterMessage = null;
                        try {
                            scoreGetterMessage = new JSONObject();
                            scoreGetterMessage.put("username", champLogin.getText().toString());
                            scoreGetterMessage.put("messageType", "GET_SCORE");
                        }catch(JSONException je){
                            Log.e("identicationActivite", je.getMessage());
                        }

                        // récuperer les scores d'un utilisateur connecté depuis le serveur
                        client.threadSeConnecter(ClientConnection.ADDRESS, ClientConnection.PORT, scoreGetterMessage.toString(), MessageType.GET_SCORE);

                        try{
                            Thread.sleep(200);
                        }catch(InterruptedException ie){
                            Log.e("identificationActivite",ie.getMessage());
                        }

                        Session.resetScores();
                        String resultatScore = client.getResultMessage();
                        try {
                            JSONObject jo = new JSONObject(resultatScore);

                            // Boucler sous un Iterator sur les clefs de JSON reponse
                            Iterator<String> keyIter = jo.keys();
                            while(keyIter.hasNext()) {
                                String key = keyIter.next();
                                if(!key.equals("username")) {
                                    Session.addScore(key, jo.getInt(key));
                                    Log.e("IdentficationActivite",jo.getInt(key) + " " + key);
                                }

                               Log.e("IdentificationActivite", Session.getScores().toString());

                            }

                        } catch (JSONException je) {
                            Log.e("IdentificationActivite", je.getMessage());
                            je.printStackTrace();
                        }
                        Intent menuActiviteIntent = new Intent(IdentificationActivite.this, MenuActivite.class);
                        //Utilisateur utilisateurConnecte = new Utilisateur();
                        //utilisateurConnecte.setPseudo(champLogin.getText().toString());
                        //utilisateurConnecte.setPseudo(champMDP.getText().toString());
                        setUtilisateurConnecte(champLogin.getText().toString());
                        menuActiviteIntent.putExtra(IntentKeys.CURRENT_USER,champLogin.getText().toString());
                        Session.setCurrentUserName(champLogin.getText().toString());
                        startActivity(menuActiviteIntent);

                    }
                    else {
                        phraseDeBienvenue.setText("Identifiants incorrects");
                        phraseDeBienvenue.setTextColor(-65536);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



            }

            }
        });

    }
}
