package com.example.openmath.openmath.controleur;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.openmath.openmath.R;
import com.example.openmath.openmath.modele.ConfigurationTest;

import org.hamcrest.core.IsNot;
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
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

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
 2) Espresso cheat sheet :
 https://developer.android.com/training/testing/espresso/cheat-sheet.html
 */
@RunWith(AndroidJUnit4.class)
public class InscriptionPart2ActiviteTest
{
    @Rule
    public IntentsTestRule<InscriptionPart2Activite> intentsTestRule = new IntentsTestRule<>(InscriptionPart2Activite.class);


    //Je test que le bouton "inscription" amène bien sur l'activité menu.

    @Test
    public void testIntentBoutonInscription()
    {

        onView(withId(R.id.champNom)).perform(typeText("123"));
        onView(withId(R.id.champPrenom)).perform(typeText("123"));
        onView(withId(R.id.champDateDeNaissance)).perform(typeText("1234567891"));
        onView(withId(R.id.champMail)).perform(typeText("1234567891"));
        onView(withId(R.id.boutonInscrire)).perform(click());
        intended(hasComponent(MenuActivite.class.getName()));
    }

    //Je test le toast "3 caractères minimum par champs et la date de naissance au format dd/mm/xxxx" lorsque les champs ne respecte pas les conditions
    @Test
    public void testToastChampCaractereMinimum()
    {
        onView(withId(R.id.boutonInscrire)).perform(click());
        onView(withText("3 caractères minimum par champs et la date de naissance au format dd/mm/xxxx")).inRoot(withDecorView(IsNot.not(intentsTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }



    //Je test que le bouton accueil amène bien vers l'activté AproprosActivite.
    @Test
    public void testIntentBoutonMenuDeroulantApropros()
    {
        openActionBarOverflowOrOptionsMenu(intentsTestRule.getActivity());
        ConfigurationTest.fakeConnecter = true ;
        onView(withText("A propos")).perform(click());
        intended(hasComponent(AProposActivite.class.getName()));
    }

}