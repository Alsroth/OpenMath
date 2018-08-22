package com.example.openmath.openmath.controleur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.openmath.openmath.R;
import com.example.openmath.openmath.modele.ClientConnection;
import com.example.openmath.openmath.modele.ConfigurationTest;
import com.example.openmath.openmath.modele.QuestionBanque;
import com.example.openmath.openmath.modele.Session;

public class DefiActivite extends OpenMathActivite  {

    Button boutonDefi1;
    Button boutonDefi2;
    Button boutonDefi3;
    Button boutonDefi4;
    private TextView texteNiveau ;
    private Button boutonPrecedent;
    private Button boutonMenuA ;


    /**
     * @author Oualid Benazzouz
     * Cette fonction permet de créer le menu déroulant présent dans l'activité FeedbackActivite.
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

        switch (item.getItemId()) {
            case R.id.acceuil:
                intent = new Intent(DefiActivite.this, MenuActivite.class);
                break ;


            case R.id.signalement:
                intent = new Intent(DefiActivite.this, SignalementActivite.class);
                break ;


            case R.id.scoreCourant:
                intent = new Intent(DefiActivite.this, ScoreActivite.class);
                break ;


            case R.id.settings:

                intent = new Intent(DefiActivite.this, SettingsActivite.class);
                break ;


            case R.id.aproposBienvenueActivite:
                intent = new Intent(DefiActivite.this, AProposActivite.class);
                break ;


            case R.id.menufeedback:
                intent = new Intent(DefiActivite.this, FeedbackActivite.class);
                break ;


            case R.id.deconnection:
                intent = new Intent(DefiActivite.this, BienvenueActivite.class);
                OpenMathActivite.deconnecter();
                break ;


            case R.id.boutonPrecedent:
                this.finish();
                break ;


            case R.id.seconnecter:
                intent = new Intent(DefiActivite.this, IdentificationActivite.class);
                break ;

            /*    if(ClientConnection.isConnection()){
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
        setContentView(R.layout.level_activite);

        boutonDefi1 = findViewById(R.id.boutonNiveau1);

        boutonDefi2 = findViewById(R.id.boutonNiveau2);

        boutonDefi3 = findViewById(R.id.boutonNiveau3);

        boutonDefi4 = findViewById(R.id.boutonNiveau4);

        texteNiveau = findViewById(R.id.texteNiveaux);

        boutonPrecedent = findViewById(R.id.boutonPrecedent) ;

        boutonMenuA = findViewById(R.id.boutonMenuA) ;

        boutonDefi1.setText("Defi 1");
        boutonDefi2.setText("Defi 2");
        boutonDefi3.setText("Defi 3");
        boutonDefi4.setText("Defi 4");
        texteNiveau.setText("Jusqu'où peux tu aller?");

        boutonDefi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentInformationDefi = new Intent(DefiActivite.this,InformationDefiActivite.class);
                QuestionBanque.setCodeDefi(1);
                startActivity(intentInformationDefi);

            }
        });

        boutonDefi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentInformationDefi = new Intent(DefiActivite.this,InformationDefiActivite.class);
                QuestionBanque.setCodeDefi(2);
                startActivity(intentInformationDefi);

            }
        });

        boutonDefi3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentInformationDefi = new Intent(DefiActivite.this,InformationDefiActivite.class);
                QuestionBanque.setCodeDefi(3);
                startActivity(intentInformationDefi);

            }
        });

        boutonDefi4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentInformationDefi = new Intent(DefiActivite.this,InformationDefiActivite.class);
                QuestionBanque.setCodeDefi(4);
                startActivity(intentInformationDefi);
            }
        });

        boutonPrecedent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        boutonMenuA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DefiActivite.this,MenuActivite.class) ;
                startActivity(intent);
            }
        });

    }
}
