package com.example.openmath.openmath.modele;


import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by alsroth on 13/02/18.
 */

public class QuestionBanque {

    public static QuestionBanque questionBanque;
    private static List<Question> listQuestion ;
    private static int questionSuivanteIndex;
    private static Question question;
    private static int codeActivite;
    private static int scoreCourant;
    private static int scoreMaximalDefi;
    private static int difficulteCourante;
    private static int codeDefi ;
    private static boolean modeDefi ;
    private static int compteurDefi;
    private static boolean isPremierDefi = true ;
    private static int valeurTimerDefi ;
    private static boolean modeTest = false;

    public QuestionBanque(List<Question> listQuestion) {
        this.listQuestion = listQuestion;
        Collections.shuffle(this.listQuestion);
        questionSuivanteIndex = 0;
    }


    //Affichage de la liste de questions:

    @Override
    public String toString() {
        return "QuestionBanque{}";
    }


    //getters&setters:


    public static List<Question> getListQuestion()
    {
        return listQuestion;
    }

    public static void setListQuestion(List<Question> listQuestion)
    {
        QuestionBanque.listQuestion = listQuestion;
    }

    public static int getQuestionSuivanteIndex()
    {
        return questionSuivanteIndex;
    }

    public static void setQuestionSuivanteIndex(int questionSuivanteIndex) {
        QuestionBanque.questionSuivanteIndex = questionSuivanteIndex;
    }

    public static Question getQuestion() {

        if (QuestionBanque.questionSuivanteIndex == QuestionBanque.listQuestion.size()) {
            QuestionBanque.questionSuivanteIndex = 0;
        }

        return listQuestion.get(questionSuivanteIndex);
    }

    public static void setQuestion(Question question)
    {
        QuestionBanque.question = question;
    }

    public static int getCodeActivite()
    {
        return codeActivite;
    }

    public static void setCodeActivite(int codeActivite)
    {
        QuestionBanque.codeActivite = codeActivite;
    }

    public static int getScoreMaximalDefi()
    {
        return scoreMaximalDefi;
    }

    public static void setScoreMaximalDefi(int scoreMaximalDefi)
    {
        QuestionBanque.scoreMaximalDefi = scoreMaximalDefi;
    }

    public static boolean isModeDefi()
    {
        return modeDefi;
    }

    public static void setModeDefi(boolean modeDefi)
    {
        QuestionBanque.modeDefi = modeDefi;
    }

    public static int getCompteurDefi()
    {
        return compteurDefi;
    }

    public static void setCompteurDefi(int compteurDefi)
    {
        QuestionBanque.compteurDefi = compteurDefi;
    }

    public static int getScoreCourant()
    {

        return scoreCourant;
    }

    public static void setScoreCourant(int scoreCourant)
    {
        QuestionBanque.scoreCourant = scoreCourant;
    }

    public static boolean isModeTest()
    {
        return modeTest;
    }

    public static void setModeTest(boolean modeTest)
    {
        QuestionBanque.modeTest = modeTest;
    }

    public static int getDifficulteCourante()
    {
        return difficulteCourante;
    }

    public static void setDifficulteCourante(int difficulteCourante)
    {
        QuestionBanque.difficulteCourante = difficulteCourante;
    }

    public static boolean getIsPremierDefi()
    {
        return isPremierDefi;
    }

    public static void setIsPremierDefi(boolean isPremierDefi)
    {
        QuestionBanque.isPremierDefi = isPremierDefi;
    }

    public static int getValeurTimerDefi()
    {
        return valeurTimerDefi;
    }

    public static void setValeurTimerDefi(int valeurTimerDefi)
    {
        QuestionBanque.valeurTimerDefi = valeurTimerDefi;
    }

    public static int getCodeDefi()
    {
        return codeDefi;
    }

    public static void setCodeDefi(int codeDefi)
    {
        QuestionBanque.codeDefi = codeDefi;
    }

    /**
     * @author alexandre lallis
     * Cette fonction change le code d'activite (soit le prochain jeux qui sera généré). Afin d'enchainer
     * différents jeux pour le defi1.
     */
    public static void changementJeuDefi1()
    {
        switch(compteurDefi)
        {
            case 0: QuestionBanque.setCodeActivite(1);
            break;
            case 1: QuestionBanque.setCodeActivite(12);
                break;
            case 2: QuestionBanque.setCodeActivite(3);
                break;
            case 3: QuestionBanque.setCodeActivite(11);
                break;
        }
        QuestionBanque.compteurDefi ++ ;

    }

    /**
     * @author alexandre lallis
     * Cette fonction change le code d'activite (soit le prochain jeux qui sera généré). Afin d'enchainer
     * différents jeux pour le defi1.
     */
    public static void changementJeuDefi2()
    {
        switch(compteurDefi)
        {
            case 0: QuestionBanque.setCodeActivite(2);
                break;
            case 1: QuestionBanque.setCodeActivite(12);
                break;
            case 2: QuestionBanque.setCodeActivite(21);
                break;
            case 3: QuestionBanque.setCodeActivite(11);
                break;
        }
        QuestionBanque.compteurDefi ++ ;

    }

    /**
     * @author alexandre lallis
     * Cette fonction change le code d'activite (soit le prochain jeux qui sera généré). Afin d'enchainer
     * différents jeux pour le defi1.
     */
    public static void changementJeuDefi3()
    {
        switch(compteurDefi)
        {
            case 0: QuestionBanque.setCodeActivite(3);
                break;
            case 1: QuestionBanque.setCodeActivite(1);
                break;
            case 2: QuestionBanque.setCodeActivite(3);
                break;
            case 3: QuestionBanque.setCodeActivite(11);
                break;
        }
        QuestionBanque.compteurDefi ++ ;

    }

    /**
     * @author alexandre lallis
     * Cette fonction change le code d'activite (soit le prochain jeux qui sera généré). Afin d'enchainer
     * différents jeux pour le defi1.
     */
    public static void changementJeuDefi4()
    {
        switch(compteurDefi)
        {
            case 0: QuestionBanque.setCodeActivite(1);
                break;
            case 1: QuestionBanque.setCodeActivite(12);
                break;
            case 2: QuestionBanque.setCodeActivite(3);
                break;
            case 3: QuestionBanque.setCodeActivite(21);
                break;
        }
        QuestionBanque.compteurDefi ++ ;

    }


    /**
     * Cette méthode initialise la liste de question dans questionBanque (de la classe QuestionBanque)
     * en fonction du code d'activite. Et reset le scoreCourant.
     * Rappel :
     *  1 --> Calcul
     *  11 --> Numeration
     *  21 --> Mesure
     *  31 --> Geometrie
     * @param: rien
     * @return: void
     */
    public static void initialisationQuestionBanque(int difficulteCourante)
    {
        QuestionBanque.setScoreCourant(0);
        QuestionBanque.setDifficulteCourante(difficulteCourante);
        switch (QuestionBanque.getCodeActivite()) {
            case 1: //additions&Soustractions
                QuestionBanque.questionBanque = QuestionBanque.genererQuestionsAdditionsEtSoustractions();
                break;
            case 2: //multiplication
                QuestionBanque.questionBanque = QuestionBanque.generateQuestionsMultiplication();
                break;
            case 3: //Division
                QuestionBanque.questionBanque = QuestionBanque.generateQuestionsDivision();
                break;
            case 11: //Unité, dizaine, centaine
                QuestionBanque.questionBanque = QuestionBanque.generateUniteDizaineCentaine();
                break;
            case 12:// Les fractions
                QuestionBanque.questionBanque = QuestionBanque.generateFraction();
                break;
            case 21 : //ConversionMasses
                QuestionBanque.questionBanque = QuestionBanque.generateQuestionConversionMasses();
                break;
            case 22 : //ConversionTemps
                QuestionBanque.questionBanque = QuestionBanque.generateQuestionConversionTemps();
                break;
        }
    }


    /**
     * @return Objet de type QuestionBanque avec objet Questions traitant les additions et sosutractions.
     * @autor Alexandre Lallis
     * Cette méthode gènere une liste de questions soit questionBanque  pour le jeu additions et sosutractions.
     */
    public static QuestionBanque genererQuestionsAdditionsEtSoustractions()
    {
        int difficulte = QuestionBanque.getDifficulteCourante();
        //Pour appeler les fct qui generer les questions.
        Question question = new Question("", "0");
        List<Question> listChoix = new ArrayList<Question>();
        for (int i = 0; i < 3; i++) {
            int n1 = (int)(Math.random()*5 + (difficulte-1)*5); // génere le premier nombre : 5 <= n1 <= nMax+5
            int n2 = (int)(Math.random()*5*difficulte +1); // gérere le second nombre: 5 <= n2 <= nMax+5
            listChoix.add(question.genererAddition(n1,n2));
            int nMax = difficulte*5;
            int nMin = difficulte*2;
            n2 = (int)(Math.random()*(nMax-nMin)+nMin); // génere le premier nombre : nMin <= n1 <= nMax
            n1 = n2 + (int)(Math.random()*nMin+1); // gérere le second nombre: n2 < n1
            listChoix.add(question.genererSoustraction(n1,n2));

        }

        return new QuestionBanque(listChoix);

    }

    /**
     * @return Objet de type QuestionBanque avec objet Questions traitant les division.
     * @autor swabahadine abdallah
     * Cette méthode gènere une liste de questions soit questionBanque  pour le jeu division.
     */
    public static QuestionBanque generateQuestionsDivision()
    {
        int difficulte = QuestionBanque.getDifficulteCourante();
        //Pour appeler les fct qui generer les questions.
        Question question = new Question("", "0");
        List<Question> listChoix = new ArrayList<Question>();
        for (int i = 0; i < 6; i++) {
            List<Integer> listNumbre = Arrays.asList(1,2,3,4,5,6,7,8,9);
            Collections.shuffle(listNumbre);
            int nMax = (difficulte+1)*listNumbre.get(0);
            int nMin = listNumbre.get(0);
            int n1 = (int)(Math.random()*(nMax)+nMin); // génere le premier nombre : nMin <= n1 <= nMax
            int n2 = (int)(Math.random()*nMin+1); // gérere le second nombre: n2 < n1
            listChoix.add(question.genererDivision(n1,n2));
        }

        return new QuestionBanque(listChoix);

    }

    /**
     * @return Objet de type QuestionBanque avec objet Questions traitant les multiplication.
     * @autor swabahadine abdallah
     * Cette méthode gènere une liste de questions soit questionBanque  pour le jeu multiplication
     */
    public static QuestionBanque generateQuestionsMultiplication()
    {
        int difficulte = QuestionBanque.getDifficulteCourante();
        //Pour appeler les fct qui generer les questions.
        Question question = new Question("", "0");
        List<Question> listChoix = new ArrayList<Question>();
        for (int i = 0; i < 6; i++) {
            int nMax = difficulte*5;
            int n1 = (int)(Math.random()*nMax+1); // génere le premier nombre : 5 <= n1 <= nMax+5
            int n2 = (int)(Math.random()*nMax+1); // gérere le second nombre: 5 <= n2 <= nMax+5
            listChoix.add(question.genererMultiplication(n1 , n2));
        }

        return new QuestionBanque(listChoix);

    }


    /**
     * @return Objet de type QuestionBanque avec objet Questions traitant les unite,dizine,centaine
     * @autor swabahadine abdallah
     * Cette méthode gènere une liste de questions soit questionBanque  pour le jeu uniteDizaineCentaine
     */
    public static QuestionBanque generateUniteDizaineCentaine()
    {
        int difficulte = QuestionBanque.getDifficulteCourante();
        //Pour appeler les fct qui generer les questions.
        Question question = new Question("", "0");
        List<Question> listChoix = new ArrayList<Question>();
        for (int i = 0; i < 6; i++) {
            listChoix.add(question.genererUniteDizaineCentaine(difficulte));
        }


        return new QuestionBanque(listChoix);

    }

    /**
     * @return Objet de type QuestionBanque avec objet Questions traitant les Fraction
     * @autor swabahadine abdallah
     * Cette méthode gènere une liste de questions soit questionBanque  pour le jeu Fraction
     */
    public static QuestionBanque generateFraction()
    {
        int difficulte = QuestionBanque.getDifficulteCourante();
        //Pour appeler les fct qui generer les questions.
        Question question = new Question("", "0");
        List<Question> listChoix = new ArrayList<Question>();
        switch (difficulte){
            case 1:

                for (int i = 0; i < 3; i++) {
                    int n = 1+ (int)(Math.random()*5);
                    int numerateur = n*(int)(Math.random()*7);
                    int denominateur = n*(int)(Math.random()*6 +1);
                    int r = (int)(Math.random()*2);
                    listChoix.add(question.fraction(numerateur,denominateur,r));
                }
                for (int i = 0; i < 3; i++) {
                    List<Integer> nombrePremiers = Arrays.asList(2,3,5,7);
                    Collections.shuffle(nombrePremiers);
                    int n = 1+ (int)(Math.random()*10);
                    int n1 = nombrePremiers.get(0);
                    int n2 = (nombrePremiers.get(1));
                    int numerateur = n*n1;
                    int denominateur = n*n2;
                    listChoix.add(question.simplifierFraction(numerateur,denominateur));
                }
                break;

            case 2:
                for (int i = 0; i < 3; i++) {
                    int numerateurF1 = (int)(Math.random()*4)+1;
                    int denominateurF1 = (int)(Math.random()*4)+2;
                    int numerateurF2 = (int)(Math.random()*4)+1;
                    int denominateurF2 = (int)(Math.random()*4+1);
                    listChoix.add(question.multiplierFraction(numerateurF1,numerateurF2,denominateurF1,denominateurF2));
                }
                for (int i = 0; i < 3; i++) {
                    int numerateurF1 = (int)(Math.random()*4)+1;
                    int denominateurF1 = (int)(Math.random()*4)+2;
                    int numerateurF2 = (int)(Math.random()*4)+1;
                    int denominateurF2 = (int)(Math.random()*4+1);
                    listChoix.add(question.diviserFraction(numerateurF1,numerateurF2,denominateurF1,denominateurF2));
                }
                break;
            case 3:
                for (int i = 0; i < 6; i++) {
                    int numerateurF1 = (int)(Math.random()*9)+1;
                    int denominateurF1 = (int)(Math.random()*8)+2;
                    int numerateurF2 = (int)(Math.random()*9)+1;
                    int denominateurF2 = (int)(Math.random()*3+1) *denominateurF1;
                    listChoix.add(question.additionFraction(numerateurF1,numerateurF2,denominateurF1,denominateurF2));
                }
                break;
            case 4:
                for (int i = 0; i < 6; i++) {
                    int numerateurF1 = (int)(Math.random()*9)+1;
                    int denominateurF1 = (int)(Math.random()*8)+2;
                    int numerateurF2 = (int)(Math.random()*9)+1;
                    int denominateurF2 = (int)(Math.random()*3+1) *denominateurF1;
                    listChoix.add(question.soustractionFraction(numerateurF1,numerateurF2,denominateurF1,denominateurF2));
                }
                break;
        }



        return new QuestionBanque(listChoix);

    }

    /**
     * @autor oualid benazzouz
     * Cette méthode gènere une liste de questions soit questionBanque  pour le jeu conversionMasses
     * @return Objet de type QuestionBanque avec objet Questions traitant la Conversion des Masses
     */
    public static QuestionBanque generateQuestionConversionMasses()
    {
        int difficulte = QuestionBanque.getDifficulteCourante();
        //Pour appeler les fct qui generer les questions.
        Question question = new Question("","0");
        List<Question> listChoix = new ArrayList<Question>();
        for(int i = 0; i < 6 ; i++){
            Random rand = new Random() ;
            int nombre = rand.nextInt(500*difficulte) ;
            rand = new Random() ;
            int en = rand.nextInt(4) ;
            listChoix.add(question.conversionMasses(nombre,en));
        }

        return new QuestionBanque(listChoix);

    }

    /**
     * @autor swabahadine abdallah
     * Cette méthode gènere une liste de questions soit questionBanque  pour le jeu conversionTemps
     * @return Objet de type QuestionBanque avec objet Questions traitant la Conversion du temps
     */
    public static QuestionBanque generateQuestionConversionTemps()
    {
        //Pour appeler les fct qui generer les questions.
        int difficulte = QuestionBanque.getDifficulteCourante();
        Question question = new Question("","0");
        List<Question> listChoix = new ArrayList<Question>();
        switch (difficulte){
            case 1:
                for(int i = 0; i < 3 ; i++)
                {
                    int min = (int)(Math.random()*5 + 1);
                    listChoix.add(question.convertirMinutesEnSecondes(min));
                    int sec = (int)(Math.random()*5 + 1);
                    sec *= 60;
                    listChoix.add(question.convertirSecondeEnMinutes(sec));
                }
                break;
            case 2:
                listChoix.add(question.lesQuartHeures(1));
                listChoix.add(question.lesQuartHeures(2));
                listChoix.add(question.lesQuartHeures(3));
                for (int i = 0 ; i <3 ; i++){
                    int min = (int)(Math.random()*60 + 1);
                    int heure = (int)(Math.random()*2 + 1);
                    int choix = (int)(Math.random()*2);
                    listChoix.add(question.convertirEnMinutes(heure,min,choix));
                }
                break;
            case 3:
                for(int i = 0; i < 6 ; i++)
                {
                    int sec = (int)(Math.random()*60 + 1);
                    int min = (int)(Math.random()*2 + 1);
                    int choix = (int)(Math.random()*2);
                    listChoix.add(question.convertirEnSecondes(min,sec,choix));
                }
                break;
            default:
                for(int i = 0; i < 2 ; i++)
                {
                    int heure = (int)(Math.random()*2 + 1);
                    int choix = (int)(Math.random()*2);
                    for (int j = 0; j < 3; j++){
                        listChoix.add(question.convertirEnMinutes2(j,heure,choix));
                    }



                }
                break;

        }
        Collections.shuffle(listChoix);


        return new QuestionBanque(listChoix);
    }

    /**
     * @autor swabahadine abdallah
     * Cette methode qui return vrai si score est superieur au score du jeu indique en parametre
     * @param : score,jeu
     * @return : rien
     */
    private static boolean plusGrand(int score, String jeu){
        int scoreJeu = 0;
        if(Session.getScores().get(jeu) == null){
            Session.getScores().put(jeu,0);
        }
        scoreJeu = Session.getScores().get(jeu);
        return score > scoreJeu;
    }
    /**
     * @autor swabahadine abdallah
     * Cette methode est appeler en fin de jeu (mode test uniquement), et enregitre le score dans user de session
     * @param : rien
     * @return : rien
     *
     * @Deprecated score enregistre dans Session.scores et dans le serveur pour chaque reponse correcte dans le mode test
     */
    @Deprecated
    public static void attribuerScore() {
        int leScore = QuestionBanque.getScoreCourant();


        switch (QuestionBanque.getCodeActivite()) {
            /*
             * 1,2,3 :  calcul
             *      1 : Addition & soustraction
             *      2 : Multiplication
             *      3 : Division
             * 11,12 : numeration
             *
             * 21,22 : mesures
             */
            //CALCUL
            case 1: {
                if(plusGrand(leScore,"Additions et soustractions"))
                Session.getScores().put("Additions et soustractions", leScore);
                break;
            }
            case 2: {
                if(plusGrand(leScore,"Multiplication"))
                Session.getScores().put("Multiplication", leScore);
                break;
            }
            case 3: {
                if(plusGrand(leScore,"Division"))
                Session.getScores().put("Division", leScore);
                break;
            }
            //NUMERATION
            case 11: {
                if(plusGrand(leScore,"Unité dizaine centaine"))
                Session.getScores().put("Unité dizaine centaine", leScore);
                break;
            }
            case 12: {
                if(plusGrand(leScore,"Les fractions"))
                Session.getScores().put("Les fractions", leScore);
                break;
            }


            //MESURES


            case 21: {
                if(plusGrand(leScore,"Convertion"))
                Session.getScores().put("Convertion", leScore);
                break;
            }
            case 22: {
                if(plusGrand(leScore,"Conversion Temps"))
                Session.getScores().put("Conversion Temps", leScore);
                break;
            }


        }




    }



}

