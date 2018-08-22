package com.example.openmath.openmath.controleur;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class FeedbackActivite extends OpenMathActivite
{

    private TextView texteFeedback1;
    private TextView texteFeedback2;
    private EditText champNom ;
    private EditText champEmail;
    private EditText champSujet;
    private EditText champMessage ;
    private Button envoieFeedback;
    private Context context = this ;


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
                intent = new Intent(FeedbackActivite.this, MenuActivite.class);
                break ;


            case R.id.signalement:
                intent = new Intent(FeedbackActivite.this, SignalementActivite.class);
                break ;


            case R.id.scoreCourant:
                intent = new Intent(FeedbackActivite.this, ScoreActivite.class);
                break ;


            case R.id.settings:

                intent = new Intent(FeedbackActivite.this, SettingsActivite.class);
                break ;


            case R.id.aproposBienvenueActivite:
                intent = new Intent(FeedbackActivite.this, AProposActivite.class);
                break ;


            case R.id.deconnection:
                intent = new Intent(FeedbackActivite.this, BienvenueActivite.class);
                OpenMathActivite.deconnecter();
                break ;


            case R.id.boutonPrecedent:
                this.finish();
                break ;


            case R.id.seconnecter:
                intent = new Intent(FeedbackActivite.this,IdentificationActivite.class);
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


    /**
     * Author : Oualid benazzouz
     * Cette méthode créer l'activité apropros à partir de son contenu XML
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_activite);

        //On relie les widgets des activité au code java.
        texteFeedback1= findViewById(R.id.texteFeedback1) ;
        texteFeedback2 = findViewById(R.id.texteFeedback2) ;
        champNom = (EditText)findViewById(R.id.champNom) ;
        champEmail = (EditText)findViewById(R.id.champEmail) ;
        champSujet = (EditText)findViewById(R.id.champSujet) ;
        champMessage = (EditText)findViewById(R.id.champMessage) ;
        envoieFeedback = (Button)findViewById(R.id.boutonFeedback) ;

        /*author: oualid benazzouz
            Le bouton Send Report sert a ouvrir une application de messagerie pour
            pouvoir envoyer un mail au developpeurs qui contient le nom de l'utilisateur,
            l'adresse Email de l'utilisateur , le sujet et le message pour decrire le
            feedback de l'utilisateur .
            si l'utilisateur a laisser un champ Vide , le bouton Send Report est désactivé
            et le champ renvoie l'erreur "Champ Obligatoir'
         */

        envoieFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nom = champNom.getText().toString() ;
                String toS = champEmail.getText().toString() ;
                String sujet = champSujet.getText().toString() ;
                String message = champMessage.getText().toString() ;

                if (nom.equals(""))
                    champNom.setError("champ obligatoire");
                else if (toS.equals(""))
                    champEmail.setError("champ obligatoire");
                else if (sujet.equals(""))
                    champSujet.setError("champ obligatoire");
                else if (message.equals(""))
                    champMessage.setError("champ obligatoire");
                else {



                Intent email = new Intent(Intent.ACTION_SEND) ;
                //email.putExtra(Intent.EXTRA_EMAIL, toS) ;
                email.putExtra(Intent.EXTRA_EMAIL,new String[] {"oualid.benazzouz1@gmail.com"}) ;
                email.putExtra(Intent.EXTRA_SUBJECT, sujet) ;
                email.putExtra(Intent.EXTRA_TEXT,"Monsieur Oualid BENAZZOUZ , \n"+ message + "\n Cordialement "+ nom +"\n"
                + toS) ;

                email.setType("message/rfc822") ;
                try {
                    startActivity(Intent.createChooser(email, "choisissez l'application pour envoyer le courrier"));
                }catch (android.content.ActivityNotFoundException ex)
                {
                    Toast.makeText(context,"aucune application de messagerie trouvée",Toast.LENGTH_SHORT).show();
                }catch(Exception ex)
                {
                    Toast.makeText(context, "erreur inattendue" +ex.toString(), Toast.LENGTH_SHORT).show();
                }

                }
            }
        });














    }
}
