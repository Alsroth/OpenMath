package com.example.openmath.openmath.controleur;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.openmath.openmath.R;
import com.example.openmath.openmath.modele.ClientConnection;
import com.example.openmath.openmath.modele.ConfigurationTest;
import com.example.openmath.openmath.modele.Session;

public class ModificationActivite extends AppCompatActivity {

    private TextView modificationCompte ;
    private TextView ancienpseudo ;
    private EditText champApseudo ;
    private TextView nouveaupseudo ;
    private EditText champNpseudo ;
    private TextView ancienmotdepasse ;
    private EditText champAmotdepasse ;
    private TextView nouveaumotdepasse ;
    private EditText champNmotdepasse ;
    private Button btnconfirmation ;
    private boolean isTexteChampsSuffisant = false;
    private Context contexte = this;
    public static final int MINIMUM_CARACTERE = 3 ;

    /**
     * @author Oualid Benazzouz
     * Cette fonction permet de créer le menu déroulant présent dans l'activité ModificationActivite.
     * doc: https://developer.android.com/guide/topics/ui/menus.html
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(Session.getUser()!= null || ConfigurationTest.fakeConnecter){
            getMenuInflater().inflate(R.menu.menu_main,menu); }
        else {
            getMenuInflater().inflate(R.menu.menu_cnx,menu);
        }
        return true;
    }
    /** Oualid BENAZZOUZ
     * Cette méthodes liste les choix atteignable depuis le menu déroulant dans l'activité ModificationActivity.
     * doc: https://developer.android.com/guide/topics/ui/menus.html
     * @param item
     * @return
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;

        switch (item.getItemId()) {
            case R.id.acceuil:
                intent = new Intent(ModificationActivite.this, MenuActivite.class);
                break ;


            case R.id.signalement:
                intent = new Intent(ModificationActivite.this, SignalementActivite.class);
                break ;


            case R.id.scoreCourant:
                intent = new Intent(ModificationActivite.this, ScoreActivite.class);
                break;


            case R.id.settings:
                intent = new Intent(ModificationActivite.this, SettingsActivite.class);
                break ;


            case R.id.aproposBienvenueActivite:
                intent = new Intent(ModificationActivite.this, AProposActivite.class);
                break ;

            case R.id.menufeedback:
                intent = new Intent(ModificationActivite.this, FeedbackActivite.class);
                break ;


            case R.id.deconnection:
                intent = new Intent(ModificationActivite.this, BienvenueActivite.class);
                OpenMathActivite.deconnecter();
                break ;


            case R.id.boutonPrecedent:
                this.finish();
                break ;


            case R.id.seconnecter:
                intent = new Intent(ModificationActivite.this, IdentificationActivite.class);
                break ;

             /*   if(ClientConnection.isConnection()){
                    findViewById(R.id.connexion).setVisibility(View.GONE);
                }
                else{
                    findViewById(R.id.groupmenu).setVisibility(View.GONE);
                } */
        }
        if (intent != null)                 startActivity(intent);


        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modification_activite);


        modificationCompte = findViewById(R.id.texteModificationCompte) ;
        ancienpseudo = findViewById(R.id.texteAncienPseudo) ;
        champApseudo = findViewById(R.id.champancienpseudo) ;
        nouveaupseudo = findViewById(R.id.texteNouveauPseudo) ;
        champNpseudo = findViewById(R.id.champNouveaupseudo) ;
        ancienmotdepasse = findViewById(R.id.texteAncienMotdepasse) ;
        champAmotdepasse = findViewById(R.id.champAncienMotdepasse) ;
        nouveaumotdepasse = findViewById(R.id.texteNouveauMotdePasse) ;
        champNmotdepasse = findViewById(R.id.champNouveauMotdePasse) ;
        btnconfirmation = findViewById(R.id.boutonConfirmation) ;


        /*  @author Oualid BENAZZOUZ
            Le bouton de Confirmation est désactive tant que les champs Ancien Pseudo et nouveau
            pseudo et ancien Mot de passe et nouveau Mot de passe
            ne font pas au moins la valeur de MINIMUM_CARACTERE.
            MINIMUM_CARACTERE = 3   */
        champApseudo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(champApseudo.getText().length() >= MINIMUM_CARACTERE
                        && champNpseudo.getText().length() >= MINIMUM_CARACTERE
                        && champAmotdepasse.getText().length() >= MINIMUM_CARACTERE * 2
                        && champNmotdepasse.getText().length() >= MINIMUM_CARACTERE * 2)
                {
                    isTexteChampsSuffisant = true ;
                }
                else
                {
                    isTexteChampsSuffisant = false;
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        /*  @author Oualid BENAZZOUZ
            Le bouton de Confirmation est désactive tant que les champs Ancien Pseudo et nouveau
            pseudo et ancien Mot de passe et nouveau Mot de passe
            ne font pas au moins la valeur de MINIMUM_CARACTERE.
            MINIMUM_CARACTERE = 3   */
        champNpseudo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                /* Le bouton de Confirmation est désactive tant que les champs Ancien Pseudo et nouveau
                    pseudo et ancien Mot de passe et nouveau Mot de passe
                    ne font pas au moins la valeur de MINIMUM_CARACTERE.
                    MINIMUM_CARACTERE = 3   */
                if(champApseudo.getText().length() >= MINIMUM_CARACTERE
                        && champNpseudo.getText().length() >= MINIMUM_CARACTERE
                        && champAmotdepasse.getText().length() >= MINIMUM_CARACTERE * 2
                        && champNmotdepasse.getText().length() >= MINIMUM_CARACTERE * 2)
                {
                    isTexteChampsSuffisant = true ;
                }
                else
                {
                    isTexteChampsSuffisant = false;
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        /* Le bouton de Confirmation est désactive tant que les champs Ancien Pseudo et nouveau
            pseudo et ancien Mot de passe et nouveau Mot de passe
            ne font pas au moins la valeur de MINIMUM_CARACTERE.
            MINIMUM_CARACTERE = 3   */
        champAmotdepasse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                /* Le bouton de Confirmation est désactive tant que les champs Ancien Pseudo et nouveau
                    pseudo et ancien Mot de passe et nouveau Mot de passe
                    ne font pas au moins la valeur de MINIMUM_CARACTERE.
                    MINIMUM_CARACTERE = 3   */
                if(champApseudo.getText().length() >= MINIMUM_CARACTERE
                        && champNpseudo.getText().length() >= MINIMUM_CARACTERE
                        && champAmotdepasse.getText().length() >= MINIMUM_CARACTERE * 2
                        && champNmotdepasse.getText().length() >= MINIMUM_CARACTERE * 2)
                {
                    isTexteChampsSuffisant = true ;
                }
                else
                {
                    isTexteChampsSuffisant = false;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        /* Le bouton de Confirmation est désactive tant que les champs Ancien Pseudo et nouveau
            pseudo et ancien Mot de passe et nouveau Mot de passe
            ne font pas au moins la valeur de MINIMUM_CARACTERE.
            MINIMUM_CARACTERE = 3   */
        champNmotdepasse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                /* Le bouton de Confirmation est désactive tant que les champs Ancien Pseudo et nouveau
                    pseudo et ancien Mot de passe et nouveau Mot de passe
                    ne font pas au moins la valeur de MINIMUM_CARACTERE.
                    MINIMUM_CARACTERE = 3   */
                if(champApseudo.getText().length() >= MINIMUM_CARACTERE
                        && champNpseudo.getText().length() >= MINIMUM_CARACTERE
                        && champAmotdepasse.getText().length() >= MINIMUM_CARACTERE * 2
                        && champNmotdepasse.getText().length() >= MINIMUM_CARACTERE * 2)
                {
                    isTexteChampsSuffisant = true ;
                }
                else
                {
                    isTexteChampsSuffisant = false;
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnconfirmation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(!isTexteChampsSuffisant)
                {
                    Toast.makeText(contexte, "3 caractères minimum par champs et 6 pour le mdp", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    //Valider modification
                }
            }
        });

    }
}
