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
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by alsroth on 23/02/18.
 */

/**
 Framework : espresso
 La liste des documentation utilisé pour cette classe de test sera écrite ici au fur et à mesure des besoins:
 1) Sur le test des intents avec espresso :
 https://developer.android.com/training/testing/espresso/intents.html
 https://developer.android.com/reference/android/support/test/espresso/intent/rule/IntentsTestRule.html
 https://developer.android.com/reference/android/support/test/espresso/intent/matcher/IntentMatchers.html
 https://developer.android.com/reference/java/lang/Class.html //Pour le .getName()
 */

@RunWith(AndroidJUnit4.class)
public class InscriptionPart1ActiviteTest
{

    @Rule
    public IntentsTestRule<InscriptionPart1Activite> intentsTestRule = new IntentsTestRule<>(InscriptionPart1Activite.class);

    //Je test que le bouton "precedent" amène bien sur l'activité de bienvenue.
    @Test
    public void testIntentBoutonPrecedent()
    {

        onView(withId(R.id.boutonPrecedent)).perform(click());
        intended(hasComponent(BienvenueActivite.class.getName()));
    }

    //Je test que le bouton "suivant" amène bien sur l'activité de la partie 2 de l'inscription.
    @Test
    public void testIntentBoutonSuivant()
    {
        onView(withId(R.id.champLogin)).perform(typeText("12"));
        onView(withId(R.id.champMDP)).perform(typeText("12"));
        onView(withId(R.id.champMDPVerification)).perform(typeText("12"));
        onView(withId(R.id.boutonSuivant)).perform(click());
        intended(hasComponent(InscriptionPart2Activite.class.getName()));
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

    /*
    Je test que les champs login , mdp , mdpVerification affiche un toast lorsque l'utilisateur clique sur suivant
    alors que les champs n'ont pas au moins  2 caractere.
     */

    @Test
    public void testToastChamp2CaractereMinimum()
    {
        onView(withId(R.id.boutonSuivant)).perform(click());
        onView(withText("2 caractères minimum par champs")).inRoot(withDecorView(not(intentsTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void testToastChampMDPNonIdentique()
    {

        onView(withId(R.id.champLogin)).perform(typeText("12"));
        onView(withId(R.id.champMDP)).perform(typeText("12"));
        onView(withId(R.id.champMDPVerification)).perform(typeText("13"));
        onView(withId(R.id.boutonSuivant)).perform(click());
        onView(withText("Les mots de passe ne sont pas identique")).inRoot(withDecorView(not(intentsTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));

    }




}