package com.example.openmath.openmath.controleur;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.util.*;

import com.example.openmath.openmath.modele.ClientConnection;
import com.example.openmath.openmath.modele.ConfigurationTest;
import com.example.openmath.openmath.modele.Session;

import com.example.openmath.openmath.R;

import java.util.Map;

public class ScoreActivite extends OpenMathActivite
{

    private TextView texteScoreView;
    private TextView dernierScoreView;
    private TextView meilleureScoreView;
    private Button boutonRejouer ;

    /**
     * @author Oualid Benazzouz
     * Cette fonction permet de créer le menu déroulant présent dans l'activité ScoreActivite.
     * doc: https://developer.android.com/guide/topics/ui/menus.html
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(Session.getUser()!= null || ConfigurationTest.fakeConnecter){
            getMenuInflater().inflate(R.menu.score,menu); }
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
                intent = new Intent(ScoreActivite.this,MenuActivite.class);
                break ;

            case R.id.signalement:
                intent = new Intent(ScoreActivite.this,SignalementActivite.class);
                break ;

            case R.id.settings:
                intent = new Intent(ScoreActivite.this,SettingsActivite.class);
                break ;

            case R.id.aproposBienvenueActivite:
                intent = new Intent(ScoreActivite.this,AProposActivite.class);
                break ;

            case R.id.menufeedback:
                intent = new Intent(ScoreActivite.this,FeedbackActivite.class);
                break ;

            case R.id.deconnection:
                intent = new Intent(ScoreActivite.this,BienvenueActivite.class);
                saveCurrentScores();
                OpenMathActivite.deconnecter();
                break ;

            case R.id.boutonPrecedent:
                this.finish();
                break ;

            case R.id.seconnecter:
                intent = new Intent(ScoreActivite.this,IdentificationActivite.class);
                break ;

              /*
                if(ClientConnection.isConnection()){
                    findViewById(R.id.connexion).setVisibility(View.GONE);
                }
                else{
                    findViewById(R.id.groupscore).setVisibility(View.GONE);
                }
                */

        }
        if (intent != null)                 startActivity(intent);

        return super.onOptionsItemSelected(item);
    }

    /**
     * Cette méthode créer l'activité ScoreActivite activité à partir de son contenu XML
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_activite);

        texteScoreView = (TextView)findViewById(R.id.texteScore);
        dernierScoreView = (TextView)findViewById(R.id.texteDernierscore);
        meilleureScoreView = (TextView)findViewById(R.id.texteMeilleurscore);
        boutonRejouer = (Button)findViewById(R.id.boutonRejouer);

        texteScoreView.setText("");


        Map<String, Integer> scoresMap = Session.getScores();
        StringBuffer scoreText = new StringBuffer("\n");
        Log.e("ScoreActivite", Session.getScores().toString());
        for(Map.Entry<String, Integer> sc : scoresMap.entrySet()) {
            scoreText.append(sc.getKey() + " : " + sc.getValue() + "\n");
        }
        Log.e("ScoreActivite",scoreText.toString());

        texteScoreView.setText(scoreText.toString());

        String dernierQuestionCategory = Session.getDernierQuestionCategory();
        if(dernierQuestionCategory != null) {
            dernierScoreView.setText(dernierQuestionCategory + " : " + scoresMap.get(dernierQuestionCategory) );
        } else {
            dernierScoreView.setText("Vous n'avez pas repondu à une question depuis votre connexion");
        }

        meilleureScoreView.setText(Session.getMeilleuresScore());

        //Bouton rejouer sert a se deplacer a l'activite MenuActivite
        // pour pouvoir lancer un autre jeu
        
        boutonRejouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScoreActivite.this,MenuActivite.class) ;
            }
        });

    }
}
