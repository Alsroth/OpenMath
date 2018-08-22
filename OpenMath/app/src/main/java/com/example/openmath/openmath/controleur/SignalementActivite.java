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

public class SignalementActivite extends OpenMathActivite
{


    private TextView texteSignalement;
    private TextView texteSignalement2;
    private TextView titre ;
    private EditText champtitre ;
    private TextView Description ;
    private EditText champDescription ;
    private Button boutonSignalement ;
    private Context context = this ;

    /**
     * @author Oualid Benazzouz
     * Cette fonction permet de créer le menu déroulant présent dans l'activité SignalementActivite.
     * doc: https://developer.android.com/guide/topics/ui/menus.html
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(Session.getUser()!= null || ConfigurationTest.fakeConnecter){
            getMenuInflater().inflate(R.menu.signalement,menu); }
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
                intent = new Intent(SignalementActivite.this,MenuActivite.class);
                break;

            case R.id.scoreCourant:
                intent = new Intent(SignalementActivite.this,ScoreActivite.class);
                break;

            case R.id.settings:
                intent = new Intent(SignalementActivite.this,SettingsActivite.class);
                break;

            case R.id.aproposBienvenueActivite:
                intent = new Intent(SignalementActivite.this,AProposActivite.class);
                break;


            case R.id.menufeedback:
                intent = new Intent(SignalementActivite.this,FeedbackActivite.class);
                break ;

            case R.id.deconnection:
                intent = new Intent(SignalementActivite.this,BienvenueActivite.class);
                saveCurrentScores();
                OpenMathActivite.deconnecter();
                break ;

            case R.id.boutonPrecedent:
                this.finish();
                break ;

            case R.id.seconnecter:
                intent = new Intent(SignalementActivite.this,IdentificationActivite.class);
                break ;



             /*   if(ClientConnection.isConnection()){
                    findViewById(R.id.connexion).setVisibility(View.GONE);
                }
                else{
                    findViewById(R.id.groupsignal).setVisibility(View.GONE);
                } */

        }

        if (intent != null)                 startActivity(intent);



        return super.onOptionsItemSelected(item);
    }

    /**
     * Cette méthode créer l'activité SignalementActivite activité à partir de son contenu XML
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signalement_activite);


        champtitre = (EditText)findViewById(R.id.champtitre) ;
        champDescription = (EditText)findViewById(R.id.champDescription) ;
        boutonSignalement = (Button)findViewById(R.id.boutonSignalement) ;

        /* author: oualid benazzouz
            Le bouton Signalement sert a ouvrir une application de messagerie pour
            pouvoir envoyer un mail au developpeurs qui contient le titre  de Signalement
            et la description .
            si l'utilisateur a laisser un champ Vide , le bouton Signalement est désactivé
            et le champ renvoie l'erreur "Champ Obligatoir'
         */

        boutonSignalement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String titre = champtitre.getText().toString() ;
                String description = champDescription.getText().toString() ;

                if (titre.equals(""))
                    champtitre.setError("champ obligatoire");
                else if (description.equals(""))
                    champDescription.setError("champ obligatoire");
                else {



                    Intent email = new Intent(Intent.ACTION_SEND) ;
                    email.putExtra(Intent.EXTRA_EMAIL,new String[] {"oualid.benazzouz1@gmail.com"}) ;
                    email.putExtra(Intent.EXTRA_SUBJECT, titre) ;
                    email.putExtra(Intent.EXTRA_TEXT,"Monsieur Oualid BENAZZOUZ , \n"+ Description +"\n Cordialement \n"
                            ) ;

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
