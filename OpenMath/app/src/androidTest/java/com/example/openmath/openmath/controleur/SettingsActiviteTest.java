package com.example.openmath.openmath.controleur;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.openmath.openmath.R;
import com.example.openmath.openmath.modele.ConfigurationTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;


/**
 * author : OUALID BENAZZOUZ
 */

/**
 Framework : espresso
 La liste des documentation utilisé  pour cette classe de test  sera écrite ici au fur et à mesure des besoins:
 1) Sur le test des intents avec espresso :
 https://developer.android.com/training/testing/espresso/intents.html
 https://developer.android.com/reference/android/support/test/espresso/intent/rule/IntentsTestRule.html
 https://developer.android.com/reference/android/support/test/espresso/intent/matcher/IntentMatchers.html
 https://developer.android.com/reference/java/lang/Class.html //Pour le .getName()
 */

@RunWith(AndroidJUnit4.class)
public class SettingsActiviteTest
{

    @Rule
    public IntentsTestRule<SettingsActivite> intentsTestRule = new IntentsTestRule<>(SettingsActivite.class);



    //Je test que le bouton "modification" amène bien sur l'activité ModificationActivite.
    @Test
    public void testIntentBoutonModificationCompte()
    {
        onView(withId(R.id.modifierCompte)).perform(click());
        intended(hasComponent(ModificationActivite.class.getName()));
    }

    //Je test que le bouton accueil amène bien vers l'activté menu.
    @Test
    public void testIntentBoutonMenuDeroulantAccueil()
    {
        ConfigurationTest.fakeConnecter = true ;
        openActionBarOverflowOrOptionsMenu(intentsTestRule.getActivity());
        onView(withText("Acceuil")).perform(click());
        intended(hasComponent(MenuActivite.class.getName()));
    }


    //Je test que le bouton score du menu déroulant amène bien vers l'activité ScoreActivite.
    @Test
    public void testIntentBoutonMenuDeroulantScore()
    {
        ConfigurationTest.fakeConnecter = true ;
        openActionBarOverflowOrOptionsMenu(intentsTestRule.getActivity());
        onView(withText("Score")).perform(click());
        intended(hasComponent(ScoreActivite.class.getName()));
    }


    //Je test que le bouton A propos amène bien vers l'activté AproprosActivite.
    @Test
    public void testIntentBoutonMenuDeroulantApropros()
    {
        ConfigurationTest.fakeConnecter = true ;
        openActionBarOverflowOrOptionsMenu(intentsTestRule.getActivity());
        onView(withText("A propos")).perform(click());
        intended(hasComponent(AProposActivite.class.getName()));
    }


    //Je test que le bouton deconnection du menu déroulant amène biens vers l'activité BienvenueActivite.
    @Test
    public void testIntentBoutonMenuDeroulantDeconnexion()
    {
        ConfigurationTest.fakeConnecter = true ;
        openActionBarOverflowOrOptionsMenu(intentsTestRule.getActivity());
        onView(withText("Déconnexion")).perform(click());
        intended(hasComponent(BienvenueActivite.class.getName()));
    }

    //Je test que le bouton feedback du menu déroulant amène biens vers l'activité FeedbackActivite.
    @Test
    public void testIntentBoutonMenuDeroulantFeedback()
    {
        ConfigurationTest.fakeConnecter = true ;
        openActionBarOverflowOrOptionsMenu(intentsTestRule.getActivity());

        onView(withText("feedback")).perform(click());
        intended(hasComponent(FeedbackActivite.class.getName()));
    }

    //Je test que le bouton "Signalement" amène bien sur l'activité SignalementActivite.
    @Test
    public void testIntentBoutonMenuDeroulantSignalement()
    {
        ConfigurationTest.fakeConnecter = true ;
        onView(withId(R.id.signalement)).perform(click());

        intended(hasComponent(SignalementActivite.class.getName()));
    }

}