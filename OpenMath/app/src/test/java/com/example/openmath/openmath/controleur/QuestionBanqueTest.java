package com.example.openmath.openmath.controleur;


import com.example.openmath.openmath.modele.*;

import org.junit.*;

import java.util.*;

/**
 * Created by KHAN on 21/03/2018.
 */

public class QuestionBanqueTest {

    private Question q1 = new Question("Combien de voyelles", new String[]{"4","5","6"}, 2);
    private Question q2 = new Question("Capital de la France", new String[]{"Nice","Paris","Marseilles"}, 1);
    private Question q3 = new Question("Aire d'un cercle de rayon 7", new String[]{"7","77","154"}, 2);
    private QuestionBanque qb1;

    @Before
    public void setup() {
        qb1 = new QuestionBanque(Arrays.asList(new Question[]{q1,q2, q3}));
    }

    @Test
    public void getListQuestionTest(){
        List<Question> ql = qb1.getListQuestion();
        Assert.assertTrue(ql.contains(q1));
        Assert.assertTrue(ql.contains(q2));
        Assert.assertTrue(ql.contains(q3));
    }

    @Test
    public void difficulteCouranteTest() {
        Assert.assertEquals(0, qb1.getDifficulteCourante());
        qb1.setDifficulteCourante(1);
        Assert.assertEquals(1, QuestionBanque.questionBanque.getDifficulteCourante());
    }

    @Test
    public void scoreCourantTest() {
        QuestionBanque.setScoreCourant(3);
        Assert.assertEquals(3, QuestionBanque.getScoreCourant());
        QuestionBanque.setScoreCourant(9);
        Assert.assertEquals(9, QuestionBanque.getScoreCourant());
    }
}
