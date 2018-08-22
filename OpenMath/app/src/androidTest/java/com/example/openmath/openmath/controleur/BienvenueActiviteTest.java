package com.example.openmath.openmath.controleur;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.openmath.openmath.R;
import com.example.openmath.openmath.modele.ConfigurationTest;

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

/**
 * Created by alsroth on 23/02/18.
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
public class BienvenueActiviteTest
{

    @Rule
    public IntentsTestRule<BienvenueActivite> intentsTestRule = new IntentsTestRule<>(BienvenueActivite.class);


    //Je test que le bouton "connecter" amène bien sur l'activité d'identification.
    @Test
    public void testIntentBoutonConnecter()
    {

        onView(withId(R.id.boutonConnecter)).perform(click());
        intended(hasComponent(IdentificationActivite.class.getName()));
    }

    //Je test que le bouton "Inscrire" amène bien sur l'activité d'inscription.
    @Test
    public void testIntentBoutonInscrire()
    {

        onView(withId(R.id.boutonInscrire)).perform(click());
        intended(hasComponent(InscriptionPart1Activite.class.getName()));
    }

    //Je test que le bouton "Ignorer" amène bien sur l'activité menu.
    @Test
    public void testIntentBoutonIgnorer()
    {

        onView(withId(R.id.boutonIgnorer)).perform(click());
        intended(hasComponent(MenuActivite.class.getName()));

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




}