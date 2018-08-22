package com.example.openmath.openmath.controleur;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.openmath.openmath.R;
import com.example.openmath.openmath.modele.ClientConnection;
import com.example.openmath.openmath.modele.ConfigurationTest;
import com.example.openmath.openmath.modele.Session;

public class AProposActivite extends OpenMathActivite
{

    /**
     * @author Oualid Benazzouz
     * Cette fonction permet de créer le menu déroulant présent dans l'activité apropro.
     * doc: https://developer.android.com/guide/topics/ui/menus.html
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(Session.getUser()!= null || ConfigurationTest.fakeConnecter){
            getMenuInflater().inflate(R.menu.apropo,menu); }
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
                intent = new Intent(AProposActivite.this,MenuActivite.class);
                break ;


            case R.id.signalement:
                intent = new Intent(AProposActivite.this,SignalementActivite.class);
                break ;


            case R.id.scoreCourant:
                intent = new Intent(AProposActivite.this,ScoreActivite.class);
                break ;


            case R.id.settings:
                intent = new Intent(AProposActivite.this,SettingsActivite.class);
                break ;


            case R.id.menufeedback:
                intent = new Intent(AProposActivite.this,FeedbackActivite.class);
                break ;


            case R.id.deconnection:
                intent = new Intent(AProposActivite.this,BienvenueActivite.class);
                OpenMathActivite.deconnecter();
                break ;


            case R.id.boutonPrecedent:
                this.finish();
                break ;


            case R.id.seconnecter:
                intent = new Intent(AProposActivite.this,IdentificationActivite.class);
                break ;

          /*      if(ClientConnection.isConnection()){
                    findViewById(R.id.connexion).setVisibility(View.GONE);
                }
                else{
                    findViewById(R.id.groupapro).setVisibility(View.GONE);
                } */

        }
        if (intent != null)                 startActivity(intent);


        return super.onOptionsItemSelected(item);
    }

    /**
     * Cette méthode créer l'activité apropros à partir de son contenu XML
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apropos_activite);
    }
}
