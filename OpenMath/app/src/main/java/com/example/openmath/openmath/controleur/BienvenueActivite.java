package com.example.openmath.openmath.controleur;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.openmath.openmath.R;
import com.example.openmath.openmath.modele.ConfigurationTest;
import com.example.openmath.openmath.modele.LecteurAudio;

public class BienvenueActivite extends OpenMathActivite
{


    private TextView nomApplication ;
    private Button boutonConnecter;
    private Button boutonInscrire;
    private Button boutonIgnorer;

    /**
     * @author Oualid Benazzouz
     * Cette fonction permet de créer le menu déroulant présent dans l'activité BienvenueActivite.
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
        switch (item.getItemId())
        {
            case R.id.aproposBienvenueActivite:
                Intent intent = new Intent(BienvenueActivite.this,AProposActivite.class);
                startActivity(intent);
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
        setContentView(R.layout.bienvenue_activite);

        //On relie les widgets des activité au code java.
        nomApplication = findViewById(R.id.nomApplication);
        boutonConnecter = findViewById(R.id.boutonConnecter);
        boutonInscrire = findViewById(R.id.boutonInscrire);
        boutonIgnorer = findViewById(R.id.boutonIgnorer);

        //On désactive le mode test instrumente.
        ConfigurationTest.isInstrumenteTest = false ;

        //On redéfini les listener des boutons pour pouvoir rediriger les utiliseurs vers une nouvelle interface.
        boutonConnecter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent identificationActiviteIntent = new Intent(BienvenueActivite.this, IdentificationActivite.class);
                startActivity(identificationActiviteIntent);
            }
        });

        boutonInscrire.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent inscriptionActiviteIntent = new Intent(BienvenueActivite.this, InscriptionPart1Activite.class);
                startActivity(inscriptionActiviteIntent);
            }
        });

        boutonIgnorer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent menuActiviteIntent = new Intent(BienvenueActivite.this, MenuActivite.class);
                startActivity(menuActiviteIntent);
            }
        });

        //On lance la musique principale
        lancementMusiquePrincipale();



    }

    /**
     * @author alexandre lallis
     * Cette méthode lance la musique principale de l'application et si jamais le lecteur audio est déja utilisé,
     * il stop la musique courante avant de lancer la musique principale.
     */
    private void lancementMusiquePrincipale()
    {
        if(LecteurAudio.etatLecteurAudio == true)
        {
            LecteurAudio.stopAudio();
        }
        LecteurAudio.jouerMusique(this,R.raw.music_jeu);
    }




}
