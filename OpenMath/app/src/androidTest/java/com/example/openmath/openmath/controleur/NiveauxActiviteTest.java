package com.example.openmath.openmath.controleur;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.ContextCompat;

import com.example.openmath.openmath.R;
import com.example.openmath.openmath.modele.ConfigurationTest;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * Created by alsroth on 24/02/18.
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
public class NiveauxActiviteTest
{
    @Rule
    public IntentsTestRule<NiveauxActivite> intentsTestRule = new IntentsTestRule<>(NiveauxActivite.class);

    @Before
    public void setUp()
    {
        ConfigurationTest.isInstrumenteTest = true ;
    }

    //Je test que le bouton "Activite1" amène bien sur l'activité de multiplesChoix.
    @Test
    public void testIntentBoutonActivite1()
    {

        onView(withId(R.id.boutonActivite1)).perform(click());
        intended(hasComponent(LevelActivite.class.getName()));
    }

    //Je test que le bouton "Activite2" amène bien sur l'activité de multiplesChoix.
    @Test
    public void testIntentBoutonActivite2()
    {

        onView(withId(R.id.boutonActivite2)).perform(click());
        intended(hasComponent(LevelActivite.class.getName()));
    }

    //Je test que le bouton "Activite3" amène bien sur l'activité de multiplesChoix.
    @Test
    public void testIntentBoutonActivite3()
    {

        onView(withId(R.id.boutonActivite3)).perform(click());
        intended(hasComponent(LevelActivite.class.getName()));
    }

    //Je test que le bouton "Activite4" amène bien sur l'activité de multiplesChoix.
    @Test
    public void testIntentBoutonActivite4()
    {

        onView(withId(R.id.boutonActivite4)).perform(click());
        intended(hasComponent(LevelActivite.class.getName()));
    }

    //Je test que le bouton accueil amène bien vers l'activté menu.
    @Test
    public void testIntentBoutonMenuDeroulantAccueil()
    {
        ConfigurationTest.isInstrumenteTest = true ;
        ConfigurationTest.fakeConnecter = true ;
        openActionBarOverflowOrOptionsMenu(intentsTestRule.getActivity());
        onView(withText("Acceuil")).perform(click());
        intended(hasComponent(MenuActivite.class.getName()));
    }

    //Je test que le bouton settings du menu déroulant amène biens vers l'activité SettingsActivite
    @Test
    public void testIntentBoutonMenuDeroulantSettings()
    {
        ConfigurationTest.fakeConnecter = true ;
        openActionBarOverflowOrOptionsMenu(intentsTestRule.getActivity());
        onView(withText("Settings")).perform(click());
        intended(hasComponent(SettingsActivite.class.getName()));
    }


    //Je test que le bouton score du menu déroulant amène bien vers l'activité ScoreActivite.
    @Test
    public void testIntentBoutonMenuDeroulantScore()
    {
        ConfigurationTest.fakeConnecter = true ;
        openActionBarOverflowOrOptionsMenu(intentsTestRule.getActivity());
        onView(withText("Score")).perform(click()) ;
        intended(hasComponent(ScoreActivite.class.getName()));
    }

    //Je test que le bouton feedback du menu déroulant amène biens vers l'activité feedbackActivite.
    @Test
    public void testIntentBoutonMenuDeroulantFeedBack()
    {
        ConfigurationTest.fakeConnecter = true ;
        openActionBarOverflowOrOptionsMenu(intentsTestRule.getActivity());
        onView(withText("feedback")).perform(click());
        intended(hasComponent(FeedbackActivite.class.getName()));
    }

    //Je test que le bouton accueil amène bien vers l'activté AproprosActivite.
    @Test
    public void testIntentBoutonMenuDeroulantApropros()
    {
        ConfigurationTest.fakeConnecter = true ;
        openActionBarOverflowOrOptionsMenu(intentsTestRule.getActivity());
        onView(withText("A propos")).perform(click());
        intended(hasComponent(AProposActivite.class.getName()));
    }

    //Je test que le bouton Deconnexion du menu déroulant amène biens vers l'activité BienvenueActivite.
    @Test
    public void testIntentBoutonMenuDeroulantDeconnexion()
    {
        ConfigurationTest.fakeConnecter = true ;
        openActionBarOverflowOrOptionsMenu(intentsTestRule.getActivity());
        onView(withText("Déconnexion")).perform(click());
        intended(hasComponent(BienvenueActivite.class.getName()));
    }

    //Je test que le bouton Signalement du menu déroulant amène biens vers l'activité SignalementActivite.
    @Test
    public void testIntentBoutonMenuSignalement()
    {
        onView(withId(R.id.signalement)).perform(click());
        intended(hasComponent(SignalementActivite.class.getName()));
    }

    //Test du changement de l'affichage des widgets pour calcul
    @Test
    public void testAffichageCalcul()
    {
        ConfigurationTest.isTestAffichageCalcul = true ;
        onView(withId(R.id.texteNiveau)).check(matches(withText(containsString("Apprends les Calculs"))));
        onView(withId(R.id.boutonActivite1)).check(matches(withText(containsString("Additions et soustractions"))));
        onView(withId(R.id.boutonActivite2)).check(matches(withText(containsString("Multiplication"))));
        onView(withId(R.id.boutonActivite3)).check(matches(withText(containsString("Division"))));
        onView(withId(R.id.boutonActivite4)).check(matches(withText(containsString(""))));
    }

    //Test du changement de l'affichage des widgets pour Numeration
    @Test
    public void testAffichageNumeration()
    {
        ConfigurationTest.isTestAffichageNumeration = true ;
        onView(withId(R.id.texteNiveau)).check(matches(withText(containsString("Apprends la Numération"))));
        onView(withId(R.id.boutonActivite1)).check(matches(withText(containsString("Unité dizaine centaine"))));
        onView(withId(R.id.boutonActivite2)).check(matches(withText(containsString("Les fractions"))));
        onView(withId(R.id.boutonActivite3)).check(matches(withText(containsString(""))));
        onView(withId(R.id.boutonActivite4)).check(matches(withText(containsString(""))));
    }

    //Test du changement de l'affichage des widgets pour Mesure.
    @Test
    public void testAffichageMesure()
    {
        ConfigurationTest.isTestAffichageMesure = true ;
        onView(withId(R.id.texteNiveau)).check(matches(withText(containsString("Apprends les Mesures"))));
        onView(withId(R.id.boutonActivite1)).check(matches(withText(containsString("Convertion"))));
        onView(withId(R.id.boutonActivite2)).check(matches(withText(containsString("Conversion Temps"))));
        onView(withId(R.id.boutonActivite3)).check(matches(withText(containsString(""))));
        onView(withId(R.id.boutonActivite4)).check(matches(withText(containsString(""))));
    }

}