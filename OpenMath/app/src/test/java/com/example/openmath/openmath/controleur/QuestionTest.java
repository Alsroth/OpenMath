package com.example.openmath.openmath.controleur;

import com.example.openmath.openmath.modele.Question;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @autor swabahadine abdallah
 *
 */
public class QuestionTest
{
    /**
     * @autor swabahadine abdallah
     * Test pour l'addition de 9 + 9 resultat attendu 18
     */
    @Test
    public void genererAdditionTest(){
        Question question = new Question("", "0");
        question = question.genererAddition(9,9);
        String reponse = question.getlistChoixPossibles().get(question.getReponseIndex()-1);
        assertEquals(reponse,"18");
    }
    /**
     * @autor swabahadine abdallah
     * Test la soustraction de 90 - 50 resultat attendu 40
     */
    @Test
    public void genererSoustractionTest(){
        Question question = new Question("", "0");
        question = question.genererSoustraction(90,50);
        String reponse = question.getlistChoixPossibles().get(question.getReponseIndex()-1);
        assertEquals(reponse,"40");
    }

    /**
     * @autor swabahadine abdallah
     * Test la division de 17/4 resultat attendu "resultat = 4, reste = 1"
     */
    @Test
    public void genererDivisionTest(){
        Question question = new Question("", "0");
        question = question.genererDivision(17,4);
        String reponse = question.getlistChoixPossibles().get(question.getReponseIndex()-1);
        assertEquals(reponse,"resultat = 4, reste = 1");
    }

    /**
     * @autor swabahadine abdallah
     * Test la multiplication 99 * 99 = 9801
     */
    @Test
    public void genererMultiplicationTest(){
        Question question = new Question("", "0");
        question = question.genererMultiplication(99,99);
        String reponse = question.getlistChoixPossibles().get(question.getReponseIndex()-1);
        assertEquals(reponse,"9801");
    }

    /**
     * @autor swabahadine abdallah
     * Test la simplification des fraction
     * test denominateur de la fraction simplifiée 4/32 —> 8
     */
    @Test
    public void fractionTest(){
        Question question = new Question("", "0");
        question = question.fraction(4,32,2);
        String reponse = question.getReponseSaisie();
        assertEquals(reponse,"8");
    }

    /**
     * @autor swabahadine abdallah
     * Test la simplification des fraction
     * test 4/32 —> 1/8
     */
    @Test
    public void simplifierFractionTest(){
        Question question = new Question("", "0");
        question = question.simplifierFraction(4,32);
        String reponse = question.getReponseSaisie();
        assertEquals(reponse,"1/8");
    }

    /**
     * @autor swabahadine abdallah
     * Test la simplification des fraction
     * test addition fraction : 1/2  + 3/4 = 5/4
     */
    @Test
    public void additionFractionTest(){
        Question question = new Question("", "0");
        question = question.additionFraction(1,3,2,4);
        String reponse = question.getReponseSaisie();
        assertEquals(reponse,"5/4");
    }

    /**
     * @autor swabahadine abdallah
     * Test la simplification des fraction
     * test addition fraction : 1/2 : 3/4 = 2/3
     */
    @Test
    public void diviserFractionTest(){
        Question question = new Question("", "0");
        question = question.diviserFraction(1,3,2,4);
        String reponse = question.getReponseSaisie();
        assertEquals(reponse,"2/3");
    }

    /**
     * @autor swabahadine abdallah
     * Test la simplification des fraction
     * test addition fraction : 1/2  * 3/4 = 3/8
     */
    @Test
    public void multiplierFractionTest(){
        Question question = new Question("", "0");
        question = question.multiplierFraction(1,3,2,4);
        String reponse = question.getReponseSaisie();
        assertEquals(reponse,"3/8");
    }

    /**
     * @autor swabahadine abdallah
     * Test la conversion des masses
     * On teste 123 kg en g —> 123000
     */
    @Test
    public void conversionMassesTest(){
        Question question = new Question("", "0");
        question = question.conversionMasses(123 , 0);
        String reponse = question.getlistChoixPossibles().get(question.getReponseIndex()-1);
        assertEquals(reponse,"123000");
    }


    /**
     * @autor swabahadine abdallah
     * Test la simplification des fraction
     * test addition fraction : 1/2  * 3/4 = 3/8
     */
    @Test
    public void convertirMinutesEnSecondesTest(){
        Question question = new Question("", "0");
        question = question.convertirMinutesEnSecondes(10);
        String reponse = question.getReponseSaisie();
        assertEquals(reponse,"600");
    }

    /**
     * @autor swabahadine abdallah
     * Test la simplification des fraction
     * test addition fraction : 1/2  * 3/4 = 3/8
     */
    @Test
    public void convertirSecondeEnMinutesTest(){
        Question question = new Question("", "0");
        question = question.convertirSecondeEnMinutes(300);
        String reponse = question.getReponseSaisie();
        assertEquals(reponse,"5");
    }


    /**
     * @autor swabahadine abdallah
     * Test la simplification des fraction
     * test addition fraction : 1/2  * 3/4 = 3/8
     */
    @Test
    public void convertirEnSecondes(){
        Question question = new Question("", "0");
        question = question.convertirEnSecondes(1,30,0);
        String reponse = question.getReponseSaisie();
        assertEquals(reponse,"90");
    }


    /**
     * @autor swabahadine abdallah
     * Test la simplification des fraction
     * test addition fraction : 1/2  * 3/4 = 3/8
     */
    @Test
    public void convertirEnMinutes(){
        Question question = new Question("", "0");
        question = question.convertirEnMinutes(1,30,1);
        String reponse = question.getReponseSaisie();
        assertEquals(reponse,"30");
    }


    /**
     * @autor swabahadine abdallah
     * Test la simplification des fraction
     * test addition fraction : 1/2  * 3/4 = 3/8
     */
    @Test
    public void convertirEnMinutes2(){
        Question question = new Question("", "0");
        question = question.convertirEnMinutes2(3,1,0);
        String reponse = question.getReponseSaisie();
        assertEquals(reponse,"105");
    }


    /**
     * @autor swabahadine abdallah
     * Test la simplification des fraction
     * test addition fraction : 1/2  * 3/4 = 3/8
     */
    @Test
    public void lesQuartHeures(){
        Question question = new Question("", "0");
        question = question.lesQuartHeures(2);
        String reponse = question.getReponseSaisie();
        assertEquals(reponse,"30");
    }

}
