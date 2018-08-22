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
import com.example.openmath.openmath.modele.ConfigurationTest;
import com.example.openmath.openmath.modele.QuestionBanque;
import com.example.openmath.openmath.modele.Session;

public class InformationDefiActivite extends AppCompatActivity {
    private TextView nomDefi ;
    private TextView scoreDefi ;
    private TextView texteConsigneDefi;
    private Button boutonJouer ;
    private Button boutonMenuA ;
    private Button boutonPrecedent ;

    /**
     * @author Oualid Benazzouz
     * Cette fonction permet de créer le menu déroulant présent dans l'activité LevelActivite.
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
     * Cette méthodes liste les choix atteignable depuis le menu déroulant dans l'activité FeedbackActivite.
     * doc: https://developer.android.com/guide/topics/ui/menus.html
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = null;

        switch (item.getItemId()) {
            case R.id.acceuil:
                intent = new Intent(InformationDefiActivite.this, MenuActivite.class);
                break;


            case R.id.signalement:
                intent = new Intent(InformationDefiActivite.this, SignalementActivite.class);
                break ;


            case R.id.scoreCourant:
                intent = new Intent(InformationDefiActivite.this, ScoreActivite.class);
                break ;


            case R.id.settings:
                intent = new Intent(InformationDefiActivite.this, SettingsActivite.class);
                break ;


            case R.id.aproposBienvenueActivite:
                intent = new Intent(InformationDefiActivite.this, AProposActivite.class);
            break ;


            case R.id.menufeedback:
                intent = new Intent(InformationDefiActivite.this, FeedbackActivite.class);
                break ;


            case R.id.deconnection:
                intent = new Intent(InformationDefiActivite.this, BienvenueActivite.class);
                OpenMathActivite.deconnecter();
                break ;


            case R.id.boutonPrecedent:
                this.finish();
                break ;


            case R.id.seconnecter:
                intent = new Intent(InformationDefiActivite.this,IdentificationActivite.class);
                break ;

        }
        if (intent != null)                 startActivity(intent);



        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_defi_activte);
        nomDefi = findViewById(R.id.nomDefi);
        scoreDefi = findViewById(R.id.scoreDefi);
        texteConsigneDefi = findViewById(R.id.texteconsigne);
        boutonJouer = findViewById(R.id.boutonJouer);
        boutonMenuA = findViewById(R.id.boutonMenuA) ;
        boutonPrecedent = findViewById(R.id.boutonPrecedent) ;

        ConfigurationTest.isInstrumenteTest = false ;
        int score = getPreferences(MODE_PRIVATE).getInt("score",1);

        switch (QuestionBanque.getCodeDefi())
        {
            case 1:
                changeValeurActivite("Defi 1","Joue un maximum de jeux",0);
                QuestionBanque.changementJeuDefi1();
                break;
            case 2:
                changeValeurActivite("Defi 2","Joue un maximum de jeux",0);
                QuestionBanque.changementJeuDefi2();
                break;
            case 3:
                changeValeurActivite("Defi 3","Joue un maximum de jeux",0);
                QuestionBanque.changementJeuDefi3();
                break;
            case 4:
                changeValeurActivite("Defi 4","Joue un maximum de jeux",0);
                QuestionBanque.changementJeuDefi4();
                break;

        }

        boutonJouer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                QuestionBanque.setModeDefi(true);
                QuestionBanque.initialisationQuestionBanque(2);
                // En fonction du format de la question on va lancer l'activité MultiplesChoix ou SaisieReponse.
                if(QuestionBanque.getQuestion().getFormat() == 1)
                {
                    Intent multiplesChoixActiviteIntent = new Intent(InformationDefiActivite.this, MultiplesChoixActivite.class);
                    startActivity(multiplesChoixActiviteIntent);
                }
                else
                {
                    Intent SaisieReponseActiviteIntent = new Intent(InformationDefiActivite.this, SaisieReponseActivite.class);
                    startActivity(SaisieReponseActiviteIntent);
                }
            }
        });

        boutonMenuA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InformationDefiActivite.this,MenuActivite.class) ;
                startActivity(intent);
            }
        });

        boutonPrecedent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void changeValeurActivite(String nomDefi, String consigne , int score)
    {
        this.nomDefi.setText(nomDefi);
        this.texteConsigneDefi.setText(consigne);
        this.scoreDefi.setText("Meilleur score : " + score);
    }


}
