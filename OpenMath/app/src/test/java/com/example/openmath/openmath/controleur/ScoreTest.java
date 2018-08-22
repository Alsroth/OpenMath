package com.example.openmath.openmath.controleur;
import com.example.openmath.openmath.modele.Question;
import com.example.openmath.openmath.modele.Score;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*
Creé par Swabahadine Abdallah
 */
public class ScoreTest
{
    @Test
    public void convertListEnString(){
        List<Integer> liste = Arrays.asList(1,2,3,4,5,6);
        Score score = new Score();
        String str = score.convertListEnString(liste);
        assertEquals(str,"123456");
    }

    @Test
    public void chargerChaineFormatee(){
        Score score = new Score().chargerScore("1;2;3;\n4;5;6;\n7;8;9;\n10;11;12;13;");
        List<Integer> listeCalcul = score.getCalcul();
        List<Integer> listeNumeration = score.getNumeration();
        List<Integer> listeMesure = score.getMesure();
        List<Integer> listeDefi = score.getDefi();
        assertEquals(score.convertListEnString(listeCalcul),"123");
        assertEquals(score.convertListEnString(listeNumeration),"456");
        assertEquals(score.convertListEnString(listeMesure),"789");
        assertEquals(score.convertListEnString(listeDefi),"10111213");
    }



}
