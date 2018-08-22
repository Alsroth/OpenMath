package com.example.openmath.openmath.controleur;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
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
import com.example.openmath.openmath.modele.Score;
import com.example.openmath.openmath.modele.Session;
import com.example.openmath.openmath.modele.Utilisateur;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.view.View.INVISIBLE;
import static java.sql.Types.NULL;

public class MenuActivite extends AppCompatActivity
{

    //On relie les widgets des activité au code java.
    private TextView phraseDeBienvenue ;
    private Button boutonMenu1 ;
    private Button boutonMenu2 ;
    private Button boutonMenu3 ;

    private Button boutonMenu4 ;
    private Button boutonMenuDefi;
    private String utilisateurConnecte;


    @Override
    protected void onResume() {
        Log.e("MenuActivite","ma resume");
        super.onResume();
    }

    /**
     * @author Oualid Benazzouz
     * Cette fonction permet de créer le menu déroulant présent dans l'activité MenuActivite.
     * doc: https://developer.android.com/guide/topics/ui/menus.html
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(Session.getUser()!= null || ConfigurationTest.fakeConnecter){
            getMenuInflater().inflate(R.menu.acceuil,menu); }
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
            case R.id.signalement:
                intent = new Intent(MenuActivite.this, SignalementActivite.class);
                break ;


            case R.id.scoreCourant:
                intent = new Intent(MenuActivite.this, ScoreActivite.class);
                break ;


            case R.id.settings:
                intent = new Intent(MenuActivite.this, SettingsActivite.class);
                break ;


            case R.id.aproposBienvenueActivite:
                intent = new Intent(MenuActivite.this, AProposActivite.class);
                break ;

            case R.id.menufeedback:
                intent = new Intent(MenuActivite.this, FeedbackActivite.class);
                break ;


            case R.id.deconnection:
                intent = new Intent(MenuActivite.this, BienvenueActivite.class);
                OpenMathActivite.deconnecter();
                break ;


            case R.id.boutonPrecedent:
                this.finish();
                break ;


            case R.id.seconnecter:
                intent = new Intent(MenuActivite.this,IdentificationActivite.class);
                break ;

                /*
                if(ClientConnection.isConnection()){
                    findViewById(R.id.connexion).setVisibility(View.GONE);
                }
                else{
                    findViewById(R.id.groupacc).setVisibility(View.GONE);
                } */
        }
        if (intent != null)                 startActivity(intent);

        return super.onOptionsItemSelected(item);
    }
    /**
     * @author swabahadine abdallah
     * Cette méthode sert à recupérer le score stocké sinon le creer si il n'existe pas
     * @param
     * @return scores
     */
    /*public float initScore(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Scores", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        float scores = pref.getFloat("scores",NULL);
        if(scores == NULL){
            editor.putFloat("scores",1);
            editor.apply();
        }
        return scores;
    }*/

    /**
     * Cette méthode créer l'activité MenuActivite à partir de son contenu XML
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //float scores = initScore();
        //Session.pullScore(getApplicationContext());
        //if(true) throw new RuntimeException("hahahah");
        Log.e("MenuActivite","Menu activite appele");
        Log.d("MenuActivite","Menu activite appele");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activite);

        //On relie les widgets des activité au code java.
        phraseDeBienvenue = findViewById(R.id.phraseDeBienvenue);
        boutonMenu1 = findViewById(R.id.boutonMenu1);
        boutonMenu2 = findViewById(R.id.boutonMenu2);
        boutonMenu3 = findViewById(R.id.boutonMenu3);
        boutonMenuDefi = findViewById(R.id.boutonMenuDefi);

        ConfigurationTest.isInstrumenteTest = false ;


        //Intent intent = getIntent();
        // utilisateurConnecte = intent.getStringExtra("userConnecte");

        //Log.d("MenuActivite",getUtilisateurConnecte());
        //On affiche le menu en fonction du développement courant de l'application (4 thématique de prévu).
        this.afficherMenu();


        /*
        On redéfini les listener des boutons pour pouvoir rediriger les utiliseurs vers les niveaux de la thèmatique choisi.
        On envoie en plus l'information "CodeThemes" associer à un nombre qui indique quel les niveaux de quel thèmatique
        on veux afficher sur l'inteface NiveauxActivite.
        Sur l'interface NiveauxActivite on récupére l'information grace à la methode getIntent().getSerializableExtra("CodeThemes");
        1 --> Calcul
        11 --> Numeration
        21 --> Mesure
        31 --> Geometrie
         */

        Intent intentRecu = getIntent();
        final String utilisateurLogin = intentRecu.getStringExtra(IntentKeys.CURRENT_USER);
        boutonMenu1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent niveauxIntent = new Intent(MenuActivite.this,NiveauxActivite.class) ;
                Log.d("MenuActivite",boutonMenu1.getText().toString());
                niveauxIntent.putExtra(IntentKeys.CURRENT_USER, utilisateurLogin);
                niveauxIntent.putExtra("CodeThemes",1);
                startActivity(niveauxIntent);
            }
        });

        boutonMenu2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent niveauxIntent = new Intent(MenuActivite.this,NiveauxActivite.class) ;
                Log.d("MenuActivite",boutonMenu2.getText().toString());

                niveauxIntent.putExtra("userConnecte",utilisateurConnecte);
                niveauxIntent.putExtra("CodeThemes",11);
                startActivity(niveauxIntent);
            }
        });

        boutonMenu3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent niveauxIntent = new Intent(MenuActivite.this,NiveauxActivite.class) ;
                Log.d("MenuActivite",boutonMenu3.getText().toString());

                niveauxIntent.putExtra("userConnecte",utilisateurConnecte);
                niveauxIntent.putExtra("CodeThemes",21);
                startActivity(niveauxIntent);
            }
        });

        boutonMenuDefi.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent niveauxIntent = new Intent(MenuActivite.this,DefiActivite.class) ;
                //Log.d("MenuActivite",boutonMenu4.getText().toString());

                niveauxIntent.putExtra("userConnecte",utilisateurConnecte);

                niveauxIntent.putExtra("CodeThemes",31);
                startActivity(niveauxIntent);
            }
        });


        Session.setUser(chargerUtilisateurLocal());

    }

    /*
FICHIER ICI





 */
    /**
     * @author swabahadine abdallah
     * Cette fonction sert à creer un fichier
     * @param FILENAME,texte
     * @return rien
     */
    private void creerFichierTxt(String FILENAME , String texte)  {

        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(texte.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author swabahadine abdallah
     * Cette fonction sert mettre un bouton en mode bloqué
     * @param data,context
     * @return rien
     */
    private void writeToFile(String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * @author swabahadine abdallah
     * Cette fonction sert à lire le fichier config.txt
     * @param context
     * @return le contenu du fichier en String
     */
    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("config.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    /**
     * @author swabahadine abdallah
     *
     * @param
     * @return Utilisateur local
     */
    public Utilisateur chargerUtilisateurLocal(){
        BufferedReader In = null;
        String recupScoreString;
        try {
            In = new BufferedReader(new FileReader("config.txt"));
            //si le fichier existe

        } catch (FileNotFoundException fnfe) {
            //si le fichier n'existe pas
            creerFichierTxt("config.txt", "0;0;0;\n0;0;0;\n0;0;0;\n0;0;0;0;");


        }
        //recupScoreString = readFromFile(getApplicationContext());
        //Score score = new Score().chargerScore(recupScoreString);
        //return new Utilisateur("User", score);
        return new Utilisateur("User", new Score());
    }


    /**
     * @autor alexandre lallis
     * Cette fonction permet de cacher et de désactiver les boutons menu qui n'ont pas encore implémenté.
     * Un bouton menu non implémenté à un texte vide.
     * //@param Cette fonctione ne prends aucun paramètre.
     * @return Ne renvoie rien
     */

    private void cacherWidget()
    {

        if (phraseDeBienvenue.getText().length() == 0)
        {
            phraseDeBienvenue.setVisibility(INVISIBLE);
        }
        if (boutonMenu1.getText().length() == 0)
        {
            boutonMenu1.setEnabled(false);
            boutonMenu1.setVisibility(INVISIBLE);
        }
        if (boutonMenu2.getText().length() == 0)
        {
            boutonMenu2.setEnabled(false);
            boutonMenu2.setVisibility(INVISIBLE);
        }
        if (boutonMenu3.getText().length() == 0)
        {
            boutonMenu3.setEnabled(false);
            boutonMenu3.setVisibility(INVISIBLE);
        }

    }

    /**
     * @autor alexandre lallis
     * Cette fonction modifie le texte de base présent sur les boutons pour afficher celui qui correspond à
     * la progression de l'application. Si un menu n'a pas encore été implémenté sont texte est changé en une chaine vide.
     * Ainsi le menu non implémenté sera désactivité et invisible grace à la methode cacherWidget().
     * //@param Cette fonctione ne prends aucun paramètre.
     * @return Ne renvoie rien
     */
    private void afficherMenu()
    {
        Log.e("MenuActivite","affichierMenu called");
        phraseDeBienvenue.setText("Bonjour! Es tu prêt à muscler ton cerveau ?!");
        String prenom = Session.getCurrentUserName();
        phraseDeBienvenue.setText("Bonjour "+ prenom);
        boutonMenu1.setText("Calcul");
        boutonMenu2.setText("Numeration");
        boutonMenu3.setText("Mesure");
        cacherWidget();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MenuActivite","ma started");
    }
}
