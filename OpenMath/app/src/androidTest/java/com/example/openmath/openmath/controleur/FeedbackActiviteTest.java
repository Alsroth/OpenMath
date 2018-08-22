package com.example.openmath.openmath.controleur;


import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.openmath.openmath.R;
import com.example.openmath.openmath.modele.ConfigurationTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;


/**
 *
 *
 * Author : Oualid BENAZZOUZ

 *
 *
 */

/*
       Framework : espresso
       La liste des documentation utilisé pour cette classe de test sera écrite ici au fur et à mesure des besoins:
       1) Pour la "@Rule" "ActivityTestRule"
        https://developer.android.com/reference/android/support/test/rule/ActivityTestRule.html
       2) Espresso cheat sheet :
       https://developer.android.com/training/testing/espresso/cheat-sheet.html

     */

@RunWith(AndroidJUnit4.class)
public class FeedbackActiviteTest
{



    @Rule
    public IntentsTestRule<FeedbackActivite> intentsTestRule = new IntentsTestRule<>(FeedbackActivite.class);

    /**
     Je test que le bouton de feedback soit bien désactive si les conditions ne sont pas remplie.
     Conditions :
     Le bouton de feedback est désactivé tant que les champs nom et email et message
       ne font pas au moins les valeurs minimum :
        - 3 pour le champ nom
        - 10 pour le champ email
        - 7 pour le champ message.
     */




 /*   @Test
    public void testBoutonFeedbackDesactiveEtActive()
    {

        //Par défaut le bouton doit être désactiver
        onView(withId(R.id.boutonFeedback)).check(matches(not(isEnabled())));

        //Le champ Nom fait plus de 3 caractères mais les autres champs de ne respectent pas les conditions.
        onView(withId(R.id.champNom)).perform(typeText("123"));
        onView(withId(R.id.boutonFeedback)).check(matches(not(isEnabled())));

        //Le champ Nom et champEmail respectent les conditions mais le message.
        onView(withId(R.id.champEmail)).perform(typeText("1234567891"));
        onView(withId(R.id.boutonFeedback)).check(matches(not(isEnabled())));

        //Le champMessage et champ Email respectent les conditions mais plus le champ Nom.
        onView(withId(R.id.champMessage)).perform(typeText("1234567"));
        onView(withId(R.id.champNom)).perform(clearText());
        onView(withId(R.id.boutonFeedback)).check(matches(not(isEnabled())));

        // Les 3 champs respectent les conditions.
        onView(withId(R.id.champNom)).perform(typeText("123"));
        onView(withId(R.id.boutonFeedback)).check(matches(isEnabled()));

    }  */

    //Je test que le bouton accueil amène bien vers l'activté menu.
    @Test
    public void testIntentBoutonMenuDeroulantAccueil()
    {
<<<<<<< HEAD
        ConfigurationTest.fakeConnecter = true ;
        onView(withId(R.id.acceuil)).perform(click());
=======
        SettingsActivite.fakeConnecter = true ;

        openActionBarOverflowOrOptionsMenu(intentsTestRule.getActivity());

        onView(withText("Acceuil")).perform(click());

>>>>>>> 25175889b61e8da0ddbdfd37bd3dbe4b148489f1
        intended(hasComponent(MenuActivite.class.getName()));
    }


    //Je test que le bouton score du menu déroulant amène bien vers l'activité ScoreActivite.
    @Test
    public void testIntentBoutonMenuDeroulantScore()
    {
<<<<<<< HEAD
        ConfigurationTest.fakeConnecter = true ;
        onView(withId(R.id.scoreCourant)).perform(click());
=======
        SettingsActivite.fakeConnecter = true ;

        openActionBarOverflowOrOptionsMenu(intentsTestRule.getActivity());

        onView(withText("Score")).perform(click());

>>>>>>> 25175889b61e8da0ddbdfd37bd3dbe4b148489f1
        intended(hasComponent(ScoreActivite.class.getName()));
    }

    //Je test que le bouton settings du menu déroulant amène biens vers l'activité SettingsActivite.
    @Test
    public void testIntentBoutonMenuDeroulantSettings()
    {
<<<<<<< HEAD
        ConfigurationTest.fakeConnecter = true ;
        onView(withId(R.id.settings)).perform(click());
=======
        SettingsActivite.fakeConnecter = true ;

        openActionBarOverflowOrOptionsMenu(intentsTestRule.getActivity());

        onView(withText("Settings")).perform(click());

>>>>>>> 25175889b61e8da0ddbdfd37bd3dbe4b148489f1
        intended(hasComponent(SettingsActivite.class.getName()));
    }

    //Je test que le bouton accueil amène bien vers l'activté AproprosActivite.
    @Test
    public void testIntentBoutonMenuDeroulantApropros()
    {
<<<<<<< HEAD

        ConfigurationTest.fakeConnecter = true ;
        onView(withId(R.id.aproposBienvenueActivite)).perform(click());
=======
        SettingsActivite.fakeConnecter = true ;

        openActionBarOverflowOrOptionsMenu(intentsTestRule.getActivity());

        onView(withText("A propos")).perform(click());

>>>>>>> 25175889b61e8da0ddbdfd37bd3dbe4b148489f1
        intended(hasComponent(AProposActivite.class.getName()));
    }


    //Je test que le bouton Deconnexion du menu déroulant amène biens vers l'activité BienvenueActivite.
    @Test
    public void testIntentBoutonMenuDeroulantDeconnexion()
    {
<<<<<<< HEAD
        ConfigurationTest.fakeConnecter = true ;
        onView(withId(R.id.deconnection)).perform(click());
=======
        SettingsActivite.fakeConnecter = true ;

        openActionBarOverflowOrOptionsMenu(intentsTestRule.getActivity());

        onView(withText("Déconnexion")).perform(click());

>>>>>>> 25175889b61e8da0ddbdfd37bd3dbe4b148489f1
        intended(hasComponent(BienvenueActivite.class.getName()));
    }

    //Je test que le bouton Signalement du menu déroulant amène biens vers l'activité SignalementActivite.
    @Test
    public void testIntentBoutonMenuDeroulantSignalement()
    {
<<<<<<< HEAD
        ConfigurationTest.fakeConnecter = true ;
=======
        SettingsActivite.fakeConnecter = true ;

>>>>>>> 25175889b61e8da0ddbdfd37bd3dbe4b148489f1
        onView(withId(R.id.signalement)).perform(click());

        intended(hasComponent(SignalementActivite.class.getName()));
    }




}