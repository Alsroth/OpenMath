package com.example.openmath.openmath.controleur;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.openmath.openmath.R;
import com.example.openmath.openmath.modele.ClientConnection;
import com.example.openmath.openmath.modele.ConfigurationTest;
import com.example.openmath.openmath.modele.LecteurAudio;
import com.example.openmath.openmath.modele.Session;


public class SettingsActivite extends OpenMathActivite
{

    private Switch switchSons;
    private Button invite ;
    private Context contexte = this ;
    private Switch switchVibration;
    public static boolean isVibration ;
    private Button modifierCompte ;


    /**
     * @author Oualid Benazzouz
     * Cette fonction permet de créer le menu déroulant présent dans l'activité SettingsActivite.
     * doc: https://developer.android.com/guide/topics/ui/menus.html
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(Session.getUser() != null || ConfigurationTest.fakeConnecter ){
            getMenuInflater().inflate(R.menu.settings,menu); }
        else {
            getMenuInflater().inflate(R.menu.menu_cnx,menu);
        }
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

        Intent intent = null;

        switch (item.getItemId()){
            case R.id.acceuil:
                intent = new Intent(SettingsActivite.this,MenuActivite.class);
                break ;

            case R.id.signalement:
                intent = new Intent(SettingsActivite.this,SignalementActivite.class);
                break ;


            case R.id.scoreCourant:
                intent = new Intent(SettingsActivite.this,ScoreActivite.class);
                break ;

            case R.id.aproposBienvenueActivite:
                intent = new Intent(SettingsActivite.this,AProposActivite.class);
                break ;


            case R.id.menufeedback:
                intent = new Intent(SettingsActivite.this,FeedbackActivite.class);
                break ;


            case R.id.deconnection:
                intent = new Intent(SettingsActivite.this,BienvenueActivite.class);
                saveCurrentScores();
                OpenMathActivite.deconnecter();
                break ;


            case R.id.boutonPrecedent:
                this.finish();
                break ;


            case R.id.seconnecter:
                intent = new Intent(SettingsActivite.this,IdentificationActivite.class);
                break ;


                /*
                if(ClientConnection.isConnection()){
                    findViewById(R.id.connexion).setVisibility(View.GONE);
                }
                else{
                    findViewById(R.id.groupsetting).setVisibility(View.GONE);
                }  */

        }
        if (intent != null)                 startActivity(intent);


        return super.onOptionsItemSelected(item);
    }

    /**
     * Cette méthode créer l'activité SettingsActivite activité à partir de son contenu XML
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activite);

        switchSons = findViewById(R.id.switchSons);
        invite = findViewById(R.id.inviterami) ;
        switchVibration = findViewById(R.id.switchVibration);
        modifierCompte = findViewById(R.id.modifierCompte) ;

        if(LecteurAudio.sonActive)
        {
            switchSons.setChecked(true);
        }
        else
        {
            switchSons.setChecked(false);
        }

        switchVibration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if(b)
                {
                    isVibration = true;
                }
                else
                {
                    isVibration = false;
                }
            }
        });

        switchSons.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if(b)
                {
                    LecteurAudio.sonActive = true ;

                    try
                    {
                        LecteurAudio.restartAudio();
                    }
                    catch(Exception e)
                    {
                        LecteurAudio.jouerMusique(contexte,R.raw.music_jeu);
                    }

                    switchSons.setChecked(true);

                }
                else
                {
                    LecteurAudio.sonActive = false ;
                    LecteurAudio.pauseAudio();
                    switchSons.setChecked(false);
                }
            }
        });


        /*author: oualid benazzouz
            Le bouton Invite sert a ouvrir une application de messagerie pour
            pouvoir envoyer un Message ou bien un mail , avec un  message
            qui decrit l'application OPENMATH .
         */
        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "heey, vous devriez essayer cette application OPENMATH , c'est incroyable \n" +
                        "ça nous aide à apprendre les maths en jouant , l'application est GRATUITE!! et elle est valable sur PlayStore ");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

            }
        });

        // le bouton modifier Compte sert a ouvrir l'activite ModificationCompte
        // si l'utilisateur veut changer son pseudo et mot de passe .
        modifierCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivite.this,ModificationActivite.class) ;
                startActivity(intent);
            }
        });

    }
}
