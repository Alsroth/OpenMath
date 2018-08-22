package com.example.openmath.openmath.controleur;

import android.content.Intent;
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

import static android.view.View.INVISIBLE;

public class NiveauxActivite extends OpenMathActivite
{

    private TextView texteNiveau;
    private Button boutonActivite1;
    private Button boutonActivite2;
    private Button boutonActivite3;
    private Button boutonActivite4;
    private Button boutonPrecedent;
    private Button boutonMenuA ;
    private int CodeThemes;
    private String utilisateurConnecte;


    /**
     * @author Oualid Benazzouz
     * Cette fonction permet de créer le menu déroulant présent dans l'activité niveauxActivite.
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
                intent = new Intent(NiveauxActivite.this, MenuActivite.class);
                break ;


            case R.id.signalement:
                intent = new Intent(NiveauxActivite.this, SignalementActivite.class);
                break ;


            case R.id.scoreCourant:
                intent = new Intent(NiveauxActivite.this, ScoreActivite.class);
                break ;


            case R.id.settings:

                intent = new Intent(NiveauxActivite.this, SettingsActivite.class);
                break ;


            case R.id.aproposBienvenueActivite:
                intent = new Intent(NiveauxActivite.this, AProposActivite.class);
                break ;

            case R.id.menufeedback:
                intent = new Intent(NiveauxActivite.this, FeedbackActivite.class);
                break ;


            case R.id.deconnection:
                intent = new Intent(NiveauxActivite.this, BienvenueActivite.class);
                OpenMathActivite.deconnecter();
                break ;


            case R.id.boutonPrecedent:
                this.finish();
                break ;


            case R.id.seconnecter:
                intent = new Intent(NiveauxActivite.this,IdentificationActivite.class);
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

    /**
     * Cette méthode créer l'activité niveauxActivite à partir de son contenu XML
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.niveaux_activite);




        //On relie les widgets des activité au code java.
        texteNiveau = findViewById(R.id.texteNiveau);
        boutonActivite1 = findViewById(R.id.boutonActivite1);
        boutonActivite2 = findViewById(R.id.boutonActivite2);
        boutonActivite3 = findViewById(R.id.boutonActivite3);
        boutonActivite4 = findViewById(R.id.boutonActivite4);
        boutonPrecedent = findViewById(R.id.boutonPrecedent);
        boutonMenuA = findViewById(R.id.boutonMenuA) ;

        Intent intent = getIntent();
        utilisateurConnecte = intent.getStringExtra("userConnecete");

        //On récupére de MenuActivite le codetheme qui désigne les niveaux de quel thèmatique on doit afficher.
        if(!ConfigurationTest.isInstrumenteTest)
        {
            CodeThemes = (int) getIntent().getSerializableExtra("CodeThemes");
            //En fonction du code theme on affiche les niveaux associé au thème choisi.
            if (CodeThemes == 1)
            {
                afficherNiveauCalcul();
            }
            if(CodeThemes == 11)
            {
                afficherNiveauNumeration();
            }
            if(CodeThemes == 21)
            {
                afficherNiveauMesure();
            }
        }







        Intent intentRecu = getIntent();
        final String utilisateurLogin = intentRecu.getStringExtra(IntentKeys.CURRENT_USER);

        /*
        Le "CodeActivite" :
        Indique quelles questions devront être générer en fonctions du niveau choisi.
        CodeActivite 1-10 réservé pour les niveaux du thèmes calcul:
        1 - Additions et soustractions d'entiers
        CodeActivite 11-20 réservé pour les niveaux du thèmes numeration
        CodeActivite 21-30 réservé pour les niveaux du thèmes mesure
        CodeActivite 31-40 réservé pour les niveaux du thèmes géométrie
        (4 boutons seulement on été définir pour le moment mais plus seront ajouté en fonctions des besoin)
         */
        boutonActivite1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.e("NiveauxActivite","je suis dans bouton 1");
                QuestionBanque.setCodeActivite(0 + CodeThemes);
                Intent SaisieReponseActiviteIntent = new Intent(NiveauxActivite.this, LevelActivite.class);
                SaisieReponseActiviteIntent.putExtra("qc"+boutonActivite1.getText().toString(),0);
                String currentQuestionCategory = boutonActivite1.getText().toString();

                Log.e("NiveauxActivite", currentQuestionCategory);
                SaisieReponseActiviteIntent.putExtra(IntentKeys.CURRENT_QUESTION_CATEGORY, currentQuestionCategory);
                SaisieReponseActiviteIntent.putExtra("qc"+currentQuestionCategory, 0);
                SaisieReponseActiviteIntent.putExtra(IntentKeys.CURRENT_USER, utilisateurLogin);
                startActivity(SaisieReponseActiviteIntent);

            }
        });

        boutonActivite2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.e("NiveauxActivite","je suis dans bouton 2");
                QuestionBanque.setCodeActivite(1 + CodeThemes);
                Intent SaisieReponseActiviteIntent = new Intent(NiveauxActivite.this, LevelActivite.class);
                String currentQuestionCategory = boutonActivite2.getText().toString();

                Log.d("NiveauxActivite", currentQuestionCategory);
                SaisieReponseActiviteIntent.putExtra(IntentKeys.CURRENT_QUESTION_CATEGORY,currentQuestionCategory);
                SaisieReponseActiviteIntent.putExtra("qc"+currentQuestionCategory,0);

                startActivity(SaisieReponseActiviteIntent);
            }
        });

        boutonActivite3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.e("NiveauxActivite","je suis dans bouton 3");
                QuestionBanque.setCodeActivite(2 + CodeThemes);
                Intent SaisieReponseActiviteIntent = new Intent(NiveauxActivite.this, LevelActivite.class);
                String currentQuestionCategory = boutonActivite3.getText().toString();
                Log.d("NiveauxActivite", currentQuestionCategory);
                SaisieReponseActiviteIntent.putExtra(IntentKeys.CURRENT_QUESTION_CATEGORY,currentQuestionCategory);
                SaisieReponseActiviteIntent.putExtra("qc"+currentQuestionCategory,0);
                startActivity(SaisieReponseActiviteIntent);
            }
        });

        boutonActivite4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.e("NiveauxActivite","je suis dans bouton 4");
                QuestionBanque.setCodeActivite(3 + CodeThemes);
                Intent SaisieReponseActiviteIntent = new Intent(NiveauxActivite.this, LevelActivite.class);
                String currentQuestionCategory = boutonActivite4.getText().toString();
                Log.d("NiveauxActivite", currentQuestionCategory);
                SaisieReponseActiviteIntent.putExtra(IntentKeys.CURRENT_QUESTION_CATEGORY,currentQuestionCategory);
                SaisieReponseActiviteIntent.putExtra("qc"+currentQuestionCategory,0);
                startActivity(SaisieReponseActiviteIntent);
            }
        });

        boutonPrecedent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.e("NiveauxActivite","je suis dans bouton precedent");
                Intent menuActiviteIntent = new Intent(NiveauxActivite.this, MenuActivite.class);
                startActivity(menuActiviteIntent);
                finish();

            }
        });

        boutonMenuA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NiveauxActivite.this,MenuActivite.class) ;
                startActivity(intent);
            }
        });


    }




    /**
     * @autor alexandre lallis
     * Cette fonction permet de cacher et de désactiver les boutons des activité qui n'ont pas encore implémenté.
     * Un bouton d'activité non implémenté à un texte vide.
     * //@param Cette fonctione ne prends aucun paramètre.
     * @return Ne renvoie rien
     */
    private void cacherWidget()
    {

        if (texteNiveau.getText().length() == 0)
        {
            texteNiveau.setVisibility(INVISIBLE);
        }
        if (boutonActivite1.getText().length() == 0)
        {
            boutonActivite1.setEnabled(false);
            boutonActivite1.setVisibility(INVISIBLE);
        }
        if (boutonActivite2.getText().length() == 0)
        {
            boutonActivite2.setEnabled(false);
            boutonActivite2.setVisibility(INVISIBLE);
        }
        if (boutonActivite3.getText().length() == 0)
        {
            boutonActivite3.setEnabled(false);
            boutonActivite3.setVisibility(INVISIBLE);
        }
        if (boutonActivite4.getText().length() == 0)
        {
            boutonActivite4.setEnabled(false);
            boutonActivite4.setVisibility(INVISIBLE);
        }

    }

    /**
     * @autor alexandre lallis
     * Cette fonction modifie le texte de base présent sur les boutons pour afficher celui qui correspond à
     * la progression de l'application. Si un niveau n'a pas encore été implémenté son texte est changé en une chaine vide.
     * Ainsi le niveau non implémenté sera désactivité et invisible grace à la methode cacherWidget().
     * Il est aussi necessaire d'avoir une fonction d'affichage pour chaque thèmes existant
     * puisqu'on réutilise l'interface pour l'affichage des niveaux de chacun de ses thèmes.
     * //@param Cette fonctione ne prends aucun paramètre.
     * @return Ne renvoie rien
    */
    private void afficherNiveauCalcul()
    {
        texteNiveau.setText("Apprends les Calculs");
        boutonActivite1.setText("Additions et soustractions");
        boutonActivite2.setText("Multiplication");
        boutonActivite3.setText("Division");
        boutonActivite4.setText("");

        boutonActivite1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.boutonactivitecalcul) );
        boutonActivite2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.boutonactivitecalcul) );
        boutonActivite3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.boutonactivitecalcul) );

        cacherWidget();
    }

    /**
     * @autor alexandre lallis
     * Cette fonction modifie le texte de base présent sur les boutons pour afficher celui qui correspond à
     * la progression de l'application. Si un niveau n'a pas encore été implémenté son texte est changé en une chaine vide.
     * Ainsi le niveau non implémenté sera désactivité et invisible grace à la methode cacherWidget().
     * Il est aussi necessaire d'avoir une fonction d'affichage pour chaque thèmes existant
     * puisqu'on réutilise l'interface pour l'affichage des niveaux de chacun de ses thèmes.
     * //@param Cette fonctione ne prends aucun paramètre.
     * @return Ne renvoie rien
     */
    private void afficherNiveauNumeration()
    {
        texteNiveau.setText("Apprends la Numération");
        boutonActivite1.setText("Unité dizaine centaine");
        boutonActivite2.setText("Les fractions");
        boutonActivite3.setText("");
        boutonActivite4.setText("");

        boutonActivite1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.boutonactivitenumeration) );
        boutonActivite2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.boutonactivitenumeration) );
        boutonActivite3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.boutonactivitenumeration) );
        cacherWidget();
    }

    private void afficherNiveauMesure()
    {
        texteNiveau.setText("Apprends les Mesures");
        boutonActivite1.setText("Convertion");
        boutonActivite2.setText("Conversion Temps");
        boutonActivite3.setText("");
        boutonActivite4.setText("");

        boutonActivite1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.boutonactivitemesure) );
        boutonActivite2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.boutonactivitemesure) );
        boutonActivite3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.boutonactivitemesure) );
        cacherWidget();
    }



}
