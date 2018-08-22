package com.example.openmath.openmath.controleur;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.openmath.openmath.R;


public class InscriptionPart1Activite extends OpenMathActivite
{

    private TextView texteInscription;
    private TextView texteLogin;
    private TextView texteMDP;
    private EditText champLogin;
    private EditText champMDP ;
    private EditText champMDPVerification;
    private Button boutonPrecedent ;
    private Button boutonSuivant ;
    private Context contexte = this ;
    private boolean isTexteChampsSuffisant = false;
    private boolean isChampMDPIdentique = false ;

    /**
     * @author Oualid Benazzouz
     * Cette fonction permet de créer le menu déroulant présent dans l'activité InscriptionPart1Activite.
     * doc: https://developer.android.com/guide/topics/ui/menus.html
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bienvenue,menu);
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
        switch (item.getItemId()) {
            case R.id.aproposBienvenueActivite:
                Intent intent = new Intent(InscriptionPart1Activite.this, AProposActivite.class);
                startActivity(intent);
        }
        switch (item.getItemId()){
            case R.id.boutonPrecedent:
                this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Cette méthode créer l'activité apropros à partir de son contenu XML
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_part1_activite);

        //On relie les widgets des activité au code java.
        texteInscription = findViewById(R.id.texteInscription);
        texteLogin  = findViewById(R.id.texteLogin);
        texteMDP = findViewById(R.id.texteMDP);
        champLogin = findViewById(R.id.champLogin);
        champMDP = findViewById(R.id.champMDP);
        champMDPVerification = findViewById(R.id.champMDPVerification);
        boutonPrecedent = findViewById(R.id.boutonPrecedent);
        boutonSuivant = findViewById(R.id.boutonSuivant);


        champLogin.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(champLogin.getText().length() >= 2 && champMDP.getText().length() >= 2  && champMDPVerification.getText().length() >= 2)
                {
                    isTexteChampsSuffisant = true ;
                }
                else
                {
                    isTexteChampsSuffisant = false ;
                }

            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });

        champMDP.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(champLogin.getText().length() >= 2 && champMDP.getText().length() >= 2  && champMDPVerification.getText().length() >= 2)
                {
                    isTexteChampsSuffisant = true ;
                }
                else
                {
                    isTexteChampsSuffisant = false ;
                }

                if(champMDP.getText().toString().equals(champMDPVerification.getText().toString()) )
                {
                    isChampMDPIdentique = true ;
                }
                else
                {
                    isChampMDPIdentique = false ;
                }

            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });

        champMDPVerification.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(champLogin.getText().length() >= 2 && champMDP.getText().length() >= 2  && champMDPVerification.getText().length() >= 2)
                {
                    isTexteChampsSuffisant = true ;
                }
                else
                {
                    isTexteChampsSuffisant = false ;
                }

                if(champMDP.getText().toString().equals(champMDPVerification.getText().toString()) )
                {
                    isChampMDPIdentique = true ;
                }
                else
                {
                    isChampMDPIdentique = false ;
                }

            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });

        //On redéfini les listener des boutons pour pouvoir rediriger les utiliseurs vers une nouvelle interface.
        boutonSuivant.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if(!isTexteChampsSuffisant)
                {
                    Toast.makeText(contexte, "2 caractères minimum par champs", Toast.LENGTH_SHORT).show();
                }
                else if(!isChampMDPIdentique)
                {
                    Toast.makeText(contexte, "Les mots de passe ne sont pas identique", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent inscriptionPart2ActiviteIntent = new Intent(InscriptionPart1Activite.this,InscriptionPart2Activite.class);
                    inscriptionPart2ActiviteIntent.putExtra("username", champLogin.getText().toString());
                    inscriptionPart2ActiviteIntent.putExtra("password", champMDP.getText().toString());
                    startActivity(inscriptionPart2ActiviteIntent);
                }

            }
        });

        boutonPrecedent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent bienvenueActiviteIntent = new Intent(InscriptionPart1Activite.this,BienvenueActivite.class);
                startActivity(bienvenueActiviteIntent );
            }
        });
    }
}
