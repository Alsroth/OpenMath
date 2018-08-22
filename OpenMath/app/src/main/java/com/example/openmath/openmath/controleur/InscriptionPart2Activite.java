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
import com.example.openmath.openmath.modele.Session;

import org.json.JSONException;
import org.json.JSONObject;

public class InscriptionPart2Activite extends OpenMathActivite
{
    private TextView texteInscription ;
    private TextView texteNom ;
    private EditText champNom ;
    private TextView textePrenom ;
    private EditText champPrenom ;
    private TextView texteDateDeNaissance ;
    private EditText champDateDeNaissance ;
    private Button boutonPrecedent ;
    private Button boutonInscrire ;
    private EditText champMail ;
    private boolean isTexteChampsSuffisant = false ;
    public static final int MINIMUM_CARACTERE = 3 ;
    private Context contexte = this ;
    private String username;
    private String password;


    /**
     * @author Oualid Benazzouz
     * Cette fonction permet de créer le menu déroulant présent dans l'activité InscriptionPart2Activite.
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
                Intent intent = new Intent(InscriptionPart2Activite.this, AProposActivite.class);
                startActivity(intent);
        }
        switch (item.getItemId()){
            case R.id.boutonPrecedent:
                this.finish();
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Cette méthode créer l'activité InscriptionPart2Activite à partir de son contenu XML
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_part2_activite);

        //On relie les widgets des activité au code java.
        texteInscription = findViewById(R.id.texteInscription) ;
        texteNom = findViewById(R.id.texteNom) ;
        champNom = findViewById(R.id.champNom) ;
        textePrenom = findViewById(R.id.textePrenom) ;
        champPrenom = findViewById(R.id.champPrenom);
        texteDateDeNaissance = findViewById(R.id.texteDateDeNaissance);
        champDateDeNaissance = findViewById(R.id.champDateDeNaissance) ;
        boutonPrecedent = findViewById(R.id.boutonPrecedent);
        boutonInscrire = findViewById(R.id.boutonInscrire) ;
        champMail = findViewById(R.id.champMail);

        Intent i = getIntent();
        username = i.getStringExtra("username");
        password = i.getStringExtra("password");
        Log.e("insc2",username);
        Log.e ("insc2", password);


         /* Le bouton d'inscription est désactive tant que les champs Nom et prenom
            ne font pas au moins la valeur de MINIMUM_CARACTERE. Et que la date de naissance
            ne soit pas égale à 10. Plus le champ mail >= à 10.*/
        champPrenom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

                boutonInscrire.setEnabled(champNom.getText().length() >= MINIMUM_CARACTERE
                        && champPrenom.getText().length() >= MINIMUM_CARACTERE
                        && champDateDeNaissance.getText().length()== 10
                        && champMail.getText().length() >= 10);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

         /* Le bouton d'inscription est désactive tant que les champs Nom et prenom
            ne font pas au moins la valeur de MINIMUM_CARACTERE. Et que la date de naissance
            ne soit pas égale à 10. Plus le champ mail >= à 10.*/
        champNom.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                // Le bouton d'inscription est désactive tant que les champs Nom et prenom
                // ne font pas au moins la valeur de MINIMUM_CARACTERE. Et que la date de naissance
                // ne soit pas égale à 10. Plus le champ mail >= à 10.
                boutonInscrire.setEnabled(champNom.getText().length() >= MINIMUM_CARACTERE
                        && champPrenom.getText().length() >= MINIMUM_CARACTERE
                        && champDateDeNaissance.getText().length()== 10
                        && champMail.getText().length() >= 10);
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });

         /* Le bouton d'inscription est désactive tant que les champs Nom et prenom
            ne font pas au moins la valeur de MINIMUM_CARACTERE. Et que la date de naissance
            ne soit pas égale à 10. Plus le champ mail >= à 10.*/
        champDateDeNaissance.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                // Le bouton d'inscription est désactive tant que les champs Nom et prenom
                // ne font pas au moins la valeur de MINIMUM_CARACTERE. Et que la date de naissance
                // ne soit pas égale à 10. Plus le champ mail >= à 10.
                if(champNom.getText().length() >= MINIMUM_CARACTERE
                        && champPrenom.getText().length() >= MINIMUM_CARACTERE
                        && champDateDeNaissance.getText().length()== 10
                        && champMail.getText().length() >= 10)
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

         /* Le bouton d'inscription est désactive tant que les champs Nom et prenom
            ne font pas au moins la valeur de MINIMUM_CARACTERE. Et que la date de naissance
            ne soit pas égale à 10. Plus le champ mail >= à 10.*/
        champMail.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                // Le bouton d'inscription est désactive tant que les champs Nom et prenom
                // ne font pas au moins la valeur de MINIMUM_CARACTERE. Et que la date de naissance
                // ne soit pas égale à 10. Plus le champ mail >= à 10.
                if(champNom.getText().length() >= MINIMUM_CARACTERE
                        && champPrenom.getText().length() >= MINIMUM_CARACTERE
                        && champDateDeNaissance.getText().length()== 10
                        && champMail.getText().length() >= 10)
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



        //On redéfini les listener des boutons pour pouvoir rediriger les utiliseurs vers une nouvelle interface.

        boutonPrecedent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inscriptionPart1ActiviteIntent = new Intent(InscriptionPart2Activite.this,InscriptionPart1Activite.class);
                startActivity(inscriptionPart1ActiviteIntent);
            }
        });

        boutonInscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!isTexteChampsSuffisant)
                {
                    Toast.makeText(contexte, "3 caractères minimum par champs et la date de naissance au format dd/mm/xxxx", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Session.resetScores();
                    JSONObject messageObject = null;
                    try{
                        messageObject = new JSONObject();
                        messageObject.put("username", username );
                        messageObject.put("password", password);
                        messageObject.put("messageType","INSCRIPTION");
                        messageObject.put("nom", champNom.getText().toString());
                        messageObject.put("prenom",champPrenom.getText().toString());
                        messageObject.put("dateDeNaissance", champDateDeNaissance.getText().toString());
                        messageObject.put("courriel",champMail.getText().toString());
                    }catch (JSONException je){
                        Log.e("identificationActivite", je.getMessage());
                    }
                    //ClientConnection client = new ClientConnection("Swabahadine","mdpswabah");
                    ClientConnection client = new ClientConnection(username,password);
                    client.threadSeConnecter(messageObject.toString());
                    Session.setCurrentUserName(username);
                    Intent menuIntent = new Intent(InscriptionPart2Activite.this,MenuActivite.class) ;
                    startActivity(menuIntent);
                }

            }
        });


    }
}
