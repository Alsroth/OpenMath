package com.example.openmath.openmath.controleur;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
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

import java.sql.SQLOutput;
import java.util.Map;

public class LevelActivite extends OpenMathActivite
{

    private TextView texteNiveau ;
    private Button boutonNiveau1 ;
    private Button boutonNiveau2 ;
    private Button boutonNiveau3;
    private Button boutonNiveau4 ;
    private Button boutonPrecedent;
    private Button boutonMenuA ;

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
                intent = new Intent(LevelActivite.this, MenuActivite.class);
                break;


            case R.id.signalement:
                intent = new Intent(LevelActivite.this, SignalementActivite.class);
                break ;


            case R.id.scoreCourant:
                intent = new Intent(LevelActivite.this, ScoreActivite.class);
                break ;


            case R.id.settings:
                intent = new Intent(LevelActivite.this, SettingsActivite.class);
                break ;


            case R.id.aproposBienvenueActivite:
                intent = new Intent(LevelActivite.this, AProposActivite.class);
                break ;

            case R.id.menufeedback:
                intent = new Intent(LevelActivite.this, FeedbackActivite.class);
                break ;


            case R.id.deconnection:
                intent = new Intent(LevelActivite.this, BienvenueActivite.class);
                OpenMathActivite.deconnecter();
                break ;


            case R.id.boutonPrecedent:
                this.finish();
                break ;


            case R.id.seconnecter:
                intent = new Intent(LevelActivite.this,IdentificationActivite.class);
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
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_activite);

        //On relie les widgets des activité au code java.
        texteNiveau = findViewById(R.id.texteNiveau);
        boutonNiveau1 = findViewById(R.id.boutonNiveau1);
        boutonNiveau2 = findViewById(R.id.boutonNiveau2);
        boutonNiveau3 = findViewById(R.id.boutonNiveau3);
        boutonNiveau4 = findViewById(R.id.boutonNiveau4);
        boutonPrecedent = findViewById(R.id.boutonPrecedent);
        boutonMenuA = findViewById(R.id.boutonMenuA) ;

        //On débloque multipleschoix, en prévenant que ce n'est pas un test instrumente

        final Intent intent = getIntent();


        if(!ConfigurationTest.isInstrumenteTest)
        {
            desactiverBouton(boutonNiveau2);
            desactiverBouton(boutonNiveau3);
            desactiverBouton(boutonNiveau4);

            /* @author swabahadine abdallah
             * switch pour bloquer les boutons niveaux
             * 1,2,3 :  calcul
             *      1 : Addition & soustraction
             *      2 : Multiplication
             *      3 : Division
             * 11,12 : numeration
             *
             * 21,22 : mesures
             */
            //CALCUL

            Map<String, Integer> scores = Session.getScores();
            try {
                switch (QuestionBanque.getCodeActivite()){


                    case 1 :{
                        if(scores.get("Additions et soustractions") >= 5) activerBouton(boutonNiveau2);
                        if(scores.get("Additions et soustractions") >= 10) activerBouton(boutonNiveau3);
                        if(scores.get("Additions et soustractions") >= 15) activerBouton(boutonNiveau4);
                        break;
                    }
                    case 2 :{
                        if(scores.get("Multiplication") >= 5) activerBouton(boutonNiveau2);
                        if(scores.get("Multiplication") >= 10) activerBouton(boutonNiveau3);
                        if(scores.get("Multiplication") >= 15) activerBouton(boutonNiveau4);
                        break;
                    }
                    case 3 :{
                        if(scores.get("Division") >= 5) activerBouton(boutonNiveau2);
                        if(scores.get("Division") >= 10) activerBouton(boutonNiveau3);
                        if(scores.get("Division") >= 15) activerBouton(boutonNiveau4);
                        break;
                    }
                    //NUMERATION
                    case 11 :{
                        if(scores.get("Unité dizaine centaine") >= 5) activerBouton(boutonNiveau2);
                        if(scores.get("Unité dizaine centaine") >= 10) activerBouton(boutonNiveau3);
                        if(scores.get("Unité dizaine centaine") >= 15) activerBouton(boutonNiveau4);
                        break;
                    }
                    case 12 :{
                        if(scores.get("Les fractions") >= 5) activerBouton(boutonNiveau2);
                        if(scores.get("Les fractions") >= 10) activerBouton(boutonNiveau3);
                        if(scores.get("Les fractions") >= 15) activerBouton(boutonNiveau4);
                        break;
                    }

                    //MESURES

                    case 21 : {
                        if(scores.get("Convertion") >= 5) activerBouton(boutonNiveau2);
                        if(scores.get("Convertion") >= 10) activerBouton(boutonNiveau3);
                        if(scores.get("Convertion") >= 15) activerBouton(boutonNiveau4);
                        break;
                    }
                    case 22 :{
                        if(scores.get("Conversion Temps") >= 5) activerBouton(boutonNiveau2);
                        if(scores.get("Conversion Temps") >= 10) activerBouton(boutonNiveau3);
                        if(scores.get("Conversion Temps") >= 15) activerBouton(boutonNiveau4);
                        break;
                    }

                }
            } catch(NullPointerException npe) {
                npe.printStackTrace();
                Log.e("LevelActivite", npe.getMessage());
            }


        }





        /*
        Nous generons les questions de jeu avec la difficulté adéquate puis nous lançons l'activité
        correspondant au format de la question (ex: multiplesChoix , saisieReponse ...)
         */
        boutonNiveau1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Log.e("LevelActivity","Niveau 1 clique");

                QuestionBanque.initialisationQuestionBanque(1);

                Log.e("LevelActivity","avant lancement musique jeu");
                //lancementMusiqueJeu();
                Log.e("LevelActivity","apres lancement musique jeu");

                // En fonction du format de la question on va lancer l'activité MultiplesChoix ou SaisieReponse.
                if(QuestionBanque.getQuestion().getFormat() == 1)
                {
                    Log.e("LevelActivity","debut if erer");

                    Intent multiplesChoixActiviteIntent = new Intent(LevelActivite.this, MultiplesChoixActivite.class);
                    if(intent.hasExtra(IntentKeys.CURRENT_QUESTION_CATEGORY)) {
                        Log.e("LevelActivite","intent has cqc extra");
                        String currentQuestion  = intent.getStringExtra(IntentKeys.CURRENT_QUESTION_CATEGORY);
                        Log.e("levelActivite",currentQuestion);
                        multiplesChoixActiviteIntent.putExtra(IntentKeys.CURRENT_QUESTION_CATEGORY,currentQuestion );
                        multiplesChoixActiviteIntent.putExtra("qc"+ currentQuestion,intent.getIntExtra("qc"+ currentQuestion, 0));
                    }else if(QuestionBanque.isModeDefi()) {
                        multiplesChoixActiviteIntent.putExtra(IntentKeys.CURRENT_QUESTION_CATEGORY, "Defi");
                    }else{
                        Log.e("LevelActivite", "intent does note have cqc");
                    }
                    startActivity(multiplesChoixActiviteIntent);
                }
                else
                {
                    Intent SaisieReponseActiviteIntent = new Intent(LevelActivite.this, SaisieReponseActivite.class);
                    addCurrentQuestionCategoryToIntent(intent, SaisieReponseActiviteIntent);
                    startActivity(SaisieReponseActiviteIntent);
                }
            }
        });

        /*
        Nous generons les questions de jeu avec la difficulté adéquate puis nous lançons l'activité
        correspondant au format de la question (ex: multiplesChoix , saisieReponse ...)
         */
        boutonNiveau2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionBanque.initialisationQuestionBanque(2);
                // En fonction du format de la question on va lancer l'activité MultiplesChoix ou SaisieReponse.
                if(QuestionBanque.getQuestion().getFormat() == 1)
                {
                    Intent multiplesChoixActiviteIntent = new Intent(LevelActivite.this, MultiplesChoixActivite.class);
                    addCurrentQuestionCategoryToIntent(intent, multiplesChoixActiviteIntent);
                    startActivity(multiplesChoixActiviteIntent);
                }
                else
                {
                    Intent SaisieReponseActiviteIntent = new Intent(LevelActivite.this, SaisieReponseActivite.class);
                    addCurrentQuestionCategoryToIntent(intent, SaisieReponseActiviteIntent);
                    startActivity(SaisieReponseActiviteIntent);
                }
            }
        });

        /*
        Nous generons les questions de jeu avec la difficulté adéquate puis nous lançons l'activité
        correspondant au format de la question (ex: multiplesChoix , saisieReponse ...)
         */
        boutonNiveau3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionBanque.initialisationQuestionBanque(3);
                // En fonction du format de la question on va lancer l'activité MultiplesChoix ou SaisieReponse.
                if(QuestionBanque.getQuestion().getFormat() == 1)
                {
                    Intent multiplesChoixActiviteIntent = new Intent(LevelActivite.this, MultiplesChoixActivite.class);
                    addCurrentQuestionCategoryToIntent(intent, multiplesChoixActiviteIntent);
                    startActivity(multiplesChoixActiviteIntent);
                }
                else
                {
                    Intent SaisieReponseActiviteIntent = new Intent(LevelActivite.this, SaisieReponseActivite.class);
                    addCurrentQuestionCategoryToIntent(intent, SaisieReponseActiviteIntent);
                    startActivity(SaisieReponseActiviteIntent);
                }
            }
        });

        /*
        Nous generons les questions de jeu avec la difficulté adéquate puis nous lançons l'activité
        correspondant au format de la question (ex: multiplesChoix , saisieReponse ...)
         */
        boutonNiveau4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionBanque.initialisationQuestionBanque(4);
                // En fonction du format de la question on va lancer l'activité MultiplesChoix ou SaisieReponse.
                if(QuestionBanque.getQuestion().getFormat() == 1)
                {
                    Intent multiplesChoixActiviteIntent = new Intent(LevelActivite.this, MultiplesChoixActivite.class);
                    addCurrentQuestionCategoryToIntent(intent, multiplesChoixActiviteIntent);
                    startActivity(multiplesChoixActiviteIntent);
                }
                else
                {
                    Intent SaisieReponseActiviteIntent = new Intent(LevelActivite.this, SaisieReponseActivite.class);
                    addCurrentQuestionCategoryToIntent(intent, SaisieReponseActiviteIntent);
                    startActivity(SaisieReponseActiviteIntent);
                }
            }
        });

        //Lors du click sur le bouton precedent, on revient à l'activité NiveauxActivite.
        boutonPrecedent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });


        boutonMenuA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LevelActivite.this,MenuActivite.class) ;
                startActivity(intent);
            }
        });


    }

    /**
     * Rajoute la valeur CURRENT_QUESTION_CATEGORY, à partir de intentRecu et le met dans intentEnvoye
     * @author KHAN
     * @param intentRecu
     * @param intentEnvoye
     */
    private void addCurrentQuestionCategoryToIntent(Intent intentRecu, Intent intentEnvoye) {
        if(intentRecu.hasExtra(IntentKeys.CURRENT_QUESTION_CATEGORY)) {
            Log.e("LevelActivite","intent has cqc extra");
            String currentQuestion  = intentRecu.getStringExtra(IntentKeys.CURRENT_QUESTION_CATEGORY);
            Log.e("levelActivite",currentQuestion);
            intentEnvoye.putExtra(IntentKeys.CURRENT_QUESTION_CATEGORY,currentQuestion );
            intentEnvoye.putExtra("qc"+ currentQuestion,intentEnvoye.getIntExtra("qc"+ currentQuestion, 0));
        }else if(QuestionBanque.isModeDefi()) {
            intentEnvoye.putExtra(IntentKeys.CURRENT_QUESTION_CATEGORY, "Defi");
            intentEnvoye.putExtra("qcDefi", intentEnvoye.getIntExtra("qcDefi", 0 ));
        }else{
            Log.e("LevelActivite", "intent does note have cqc");
        }
    }

    /**
     * @author swabahadine abdallah
     * Cette fonction sert mettre un bouton en mode bloqué
     * @param bouton
     * @return rien
     */
    public void desactiverBouton(Button bouton){
        bouton.setEnabled(false);
        bouton.setTextColor(Color.parseColor("#BFBCBC"));
        bouton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.boutonlevelblocked) );
    }

    /**
     * @author swabahadine abdallah
     * Cette fonction sert mettre un bouton en mode débloqué
     * @param bouton
     * @return rien
     */
    public void activerBouton(Button bouton){
        bouton.setEnabled(true);
        bouton.setTextColor(Color.parseColor("#ffffff"));
        bouton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.boutonlevel) );
    }



}
