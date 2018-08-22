
package com.example.openmath.openmath.modele;

        import android.widget.Switch;

        import com.example.openmath.openmath.modele.temps.Minute;
        import com.example.openmath.openmath.modele.temps.Seconde;

        import java.util.Arrays;
        import java.util.Collections;
        import java.util.List;
        import java.util.Random;

/**
 * Created by alsroth on 13/02/18.
 */

public class Question
{
    private String enonce;
    private List<String> listChoixPossibles;
    private int reponseIndex;
    //Deux types de format 1: MultiplesReponse  2: ReponseSaisie (via EditText)
    private int format ;
    private String reponseSaisie ;

    public Question(String enonce, List<String> listChoixPossibles, int reponseIndex)
    {
        this.enonce = enonce;
        this.listChoixPossibles = listChoixPossibles;
        this.reponseIndex = reponseIndex;
        this.format = 1 ;

    }

    public Question(String enonce, String[] choixPossibles, int responseIndex) {
        this(enonce, Arrays.asList(choixPossibles), responseIndex);
    }

    public Question(String enonce,String reponseSaisie)
    {
        this.enonce = enonce;
        this.reponseSaisie = reponseSaisie;
        this.format = 2 ;
    }

    //Affichage d'une enonce:

    @Override
    public String toString()
    {
        return "Question{" +
                "enonce='" + enonce + '\'' +
                ", listChoixPossibles=" + listChoixPossibles +
                ", reponseIndex=" + reponseIndex +
                '}';
    }

    // getters&setters :

    public String getEnonce()
    {
        return enonce;
    }

    public void setEnonce(String enonce)
    {
        this.enonce = enonce;
    }

    public List<String> getlistChoixPossibles()
    {
        return listChoixPossibles;
    }

    public void setlistChoixPossibles(List<String> listChoixPossibles)
    {
        this.listChoixPossibles = listChoixPossibles;
    }

    public int getReponseIndex()
    {
        return reponseIndex;
    }

    public void setReponseIndex(int reponseIndex)
    {
        this.reponseIndex = reponseIndex;
    }

    public int getFormat()
    {
        return format;
    }

    public void setFormat(int format)
    {
        this.format = format;
    }

    public String getReponseSaisie()
    {
        return reponseSaisie;
    }

    public void setReponseSaisie(String reponseSaisie)
    {
        this.reponseSaisie = reponseSaisie;
    }


    /*
    METHODE POUR FAIRE LES JEUX
     */



    /**
     * @autor swabahadine abdallah
     * @param n1,n2 nombres entier
     * @return Objet de type Question traitant l'addition
     */
    public static Question genererAddition(int n1 , int n2){

        int resultat = n1 + n2;
        /*
        Liste d'enoncee.
         */
        List<String> enonceList = Arrays.asList("Quel est la valeur de "+n1+" + "+n2+" ?","Peux tu me calculer "+n1+" + "+n2+" ?", "Aide moi à résoudre "+n1+" + "+n2);
        String enonce = enonceList.get((int)(Math.random()*3));// enonce pris au hasard dans enonceList

        List<Integer> listNumber = Arrays.asList(1,2,3,4,5);
        Collections.shuffle(listNumber); // Melange de la listNumber
        List<Integer> listDecal = Arrays.asList(0,listNumber.get(0),listNumber.get(1));
        Collections.shuffle(listDecal);
        int index  = 0;
        int decal;
        int choixTab[] = new int[3];
        for(int i = 0; i< choixTab.length ; i++){
            decal = listDecal.get(i);
            choixTab[i] = resultat + decal;
            if(decal == 0){
                index = i+1;
            }
        }
        List<String> listChoix = Arrays.asList(""+choixTab[0], ""+choixTab[1],""+choixTab[2],"");
        return new Question(enonce,listChoix,index);
    }

    /**
     * @autor swabahadine abdallah
     * @param n1,n2 nombres entier
     * @return Objet de type Question traitant l'addition
     */
    public static Question genererSoustraction(int n1,int n2){

        int resultat = n1 - n2;
        /*
        Liste d'enoncee.
         */
        List<String> enonceList = Arrays.asList("Quel est la valeur de "+n1+" - "+n2+" ?","Peux tu me calculer "+n1+" - "+n2+" ?", "Aide moi à résoudre "+n1+" - "+n2);
        String enonce = enonceList.get((int)(Math.random()*3));// enonce pris au hasard dans enonceList

        List<Integer> listNumber = Arrays.asList(1,2,3,4,5);
        Collections.shuffle(listNumber); // Melange de la listNumber
        List<Integer> listDecal = Arrays.asList(0,listNumber.get(0),listNumber.get(1));
        Collections.shuffle(listDecal);
        int index  = 0;
        int decal;
        int choixTab[] = new int[3];
        for(int i = 0; i< choixTab.length ; i++){
            decal = listDecal.get(i);
            choixTab[i] = resultat + decal;
            if(decal == 0){
                index = i+1;
            }
        }
        List<String> listChoix = Arrays.asList(""+choixTab[0], ""+choixTab[1],""+choixTab[2],"");
        return new Question(enonce,listChoix,index);
    }
    /**
     * @autor swabahadine abdallah
     * @param n1,n2 nombre entier
     * @return Objet de type Question traitant la division
     */
    public static Question genererDivision(int n1, int n2){

        int resultat = n1 / n2;
        int reste = n1 % n2;
        /*
        Liste d'enoncee.
         */
        List<String> enonceList = Arrays.asList("Quel est la valeur de "+n1+" / "+n2+" ?","Peux tu me calculer "+n1+" / "+n2+" ?", "Aide moi à résoudre "+n1+" / "+n2);
        String enonce = enonceList.get((int)(Math.random()*3));// enonce pris au hasard dans enonceList

        List<Integer> listNumber = Arrays.asList(1,2,3,4);
        Collections.shuffle(listNumber); // Melange de la listNumber
        List<Integer> listDecal = Arrays.asList(0,listNumber.get(0),listNumber.get(1));
        Collections.shuffle(listDecal);
        int index  = 0;
        int decal;
        int choixTab[] = new int[3];
        for(int i = 0; i< choixTab.length ; i++){
            decal = listDecal.get(i);
            choixTab[i] = resultat + decal;
            if(decal == 0){
                index = i+1;
            }
        }
        String s1,s2,s3;
        if(reste == 0){
            s1 = String.format("%d", choixTab[0]);
            s2 = String.format("%d", choixTab[1]);
            s3 = String.format("%d", choixTab[2]);
        }
        else{
            s1 = String.format("resultat = %d, reste = %d", choixTab[0], reste);
            s2 = String.format("resultat = %d, reste = %d", choixTab[1], reste);
            s3 = String.format("resultat = %d, reste = %d", choixTab[2], reste);
        }
        List<String> listChoix = Arrays.asList(s1,s2,s3,"");
        return new Question(enonce,listChoix,index);

    }

    /**
     * @autor swabahadine abdallah
     * @param difficulte nombre entier
     * @return Objet de type Question traitant la multiplication
     */
    public static Question genererMultiplication(int n1 , int n2){

        int resultat = n1 * n2;
        /*
        Liste d'enoncee.
         */
        List<String> enonceList = Arrays.asList("Quel est la valeur de "+n1+" * "+n2+" ?","Peux tu me calculer "+n1+" * "+n2+" ?", "Aide moi à résoudre "+n1+" * "+n2);
        String enonce = enonceList.get((int)(Math.random()*3));// enonce pris au hasard dans enonceList

        List<Integer> listNumber = Arrays.asList(1,2,3,4,5);
        Collections.shuffle(listNumber); // Melange de la listNumber
        List<Integer> listDecal = Arrays.asList(0,listNumber.get(0),listNumber.get(1));
        Collections.shuffle(listDecal);
        int index  = 0;
        int decal;
        int choixTab[] = new int[3];
        for(int i = 0; i< choixTab.length ; i++){
            decal = listDecal.get(i);
            choixTab[i] = resultat + decal;
            if(decal == 0){
                index = i+1;
            }
        }
        List<String> listChoix = Arrays.asList(""+choixTab[0], ""+choixTab[1],""+choixTab[2],"");
        return new Question(enonce,listChoix,index);
    }

    /**
     * @autor swabahadine abdallah
     * Cette méthode gènere des questions qui serviront à demander à l'utilisateur où se trouve dans
     * un nombre l'unité , la dizaine ou la centaine.
     * @return Objet de type Question traitant les unite,dizine,centaine
     */
    public static Question genererUniteDizaineCentaine(int difficulte){
        List<Integer> listChiffre = Arrays.asList(1,2,3,4,5,6,7,8,9);
        Collections.shuffle(listChiffre);
        String enonce = "";
        int index =0;
        List<String> listChoix = null;


        /*
         **************************NIVEAU 1*******************************
         */
        if(difficulte == 1){
            List<Integer> listChiffreNombre = Arrays.asList(listChiffre.get(0),listChiffre.get(1),listChiffre.get(2));
            Collections.shuffle(listChiffreNombre);
            int nombre = listChiffre.get(0) + 10*listChiffre.get(1)+100*listChiffre.get(2);
            int valeur = (int)(Math.random()*3); // unite ou dizaine ou centaine
            int chiffre = listChiffre.get(valeur);
            String valeurString;

            switch (valeur)
            {
                case 0:
                    valeurString = "unité";
                    break;
                case 1:
                    valeurString = "dizaine";
                    break;
                case 2:
                    valeurString = "centaine";
                    break;
                default:
                    valeurString  = "noValue";
                    break;
            }

        /*
        Liste d'enoncee.
         */
            //List<String> enonceList = Arrays.asList("Quel est la valeur de "+n1+" * "+n2+" ?","Peux tu me calculer "+n1+" * "+n2+" ?", "Aide moi à résoudre "+n1+" * "+n2);
            //String enonce = enonceList.get((int)(Math.random()*3));// enonce pris au hasard dans enonceList

            enonce = "Dans "+nombre+" quel est le chiffre des "+ valeurString;

            index  = valeur + 1;
            int decal;
            int choixTab[] = new int[3];
            for(int i = 0; i< choixTab.length ; i++){
                choixTab[i] = listChiffreNombre.get(i);
                if(listChiffreNombre.get(i) == chiffre){
                    index = i+1;
                }
            }
            listChoix = Arrays.asList(""+choixTab[0], ""+choixTab[1],""+choixTab[2],"");

        }

        /*
         **************************NIVEAU 2*******************************
         */
        else if(difficulte == 2){
            List<Integer> listChiffreNombre = Arrays.asList(listChiffre.get(0),listChiffre.get(1),listChiffre.get(2),listChiffre.get(3));
            Collections.shuffle(listChiffreNombre);
            int nombre = listChiffre.get(0) + 10*listChiffre.get(1)+100*listChiffre.get(2) + 1000*listChiffre.get(3);
            int valeur = (int)(Math.random()*4); // unite ou dizaine ou centaine
            int chiffre = listChiffre.get(valeur);
            String valeurString;

            switch (valeur)
            {
                case 0:
                    valeurString = "unité";
                    break;
                case 1:
                    valeurString = "dizaine";
                    break;
                case 2:
                    valeurString = "centaine";
                    break;
                case 3:
                    valeurString = "unité de mille";
                    break;
                default:
                    valeurString  = "noValue";
                    break;
            }

        /*
        Liste d'enoncee.
         */
            //List<String> enonceList = Arrays.asList("Quel est la valeur de "+n1+" * "+n2+" ?","Peux tu me calculer "+n1+" * "+n2+" ?", "Aide moi à résoudre "+n1+" * "+n2);
            //String enonce = enonceList.get((int)(Math.random()*3));// enonce pris au hasard dans enonceList

            enonce = "Dans "+nombre/100+" quel est le chiffre des "+ valeurString;

            index  = valeur + 1;
            int decal;
            int choixTab[] = new int[4];
            for(int i = 0; i< choixTab.length ; i++){
                choixTab[i] = listChiffreNombre.get(i);
                if(listChiffreNombre.get(i) == chiffre){
                    index = i+1;
                }
            }
            listChoix = Arrays.asList(""+choixTab[0], ""+choixTab[1],""+choixTab[2],""+choixTab[3]);

        }

        /*
         **************************NIVEAU 3*******************************
         */
        if(difficulte == 3){
            List<Integer> listChiffreNombre = Arrays.asList(listChiffre.get(0),listChiffre.get(1),listChiffre.get(2));
            Collections.shuffle(listChiffreNombre);
            int nombre = listChiffre.get(0) + 10*listChiffre.get(1)+100*listChiffre.get(2);
            int valeur = (int)(Math.random()*3); // unite ou dizaine ou centaine
            int chiffre = listChiffre.get(valeur);
            String valeurString;

            switch (valeur)
            {
                case 0:
                    valeurString = "centième";
                    break;
                case 1:
                    valeurString = "dixième";
                    break;
                case 2:
                    valeurString = "unité";
                    break;
                default:
                    valeurString  = "noValue";
                    break;
            }

        /*
        Liste d'enoncee.
         */
            //List<String> enonceList = Arrays.asList("Quel est la valeur de "+n1+" * "+n2+" ?","Peux tu me calculer "+n1+" * "+n2+" ?", "Aide moi à résoudre "+n1+" * "+n2);
            //String enonce = enonceList.get((int)(Math.random()*3));// enonce pris au hasard dans enonceList

            enonce = "Dans "+(double)nombre/100+" quel est le chiffre des "+ valeurString;

            index  = valeur + 1;
            int decal;
            int choixTab[] = new int[3];
            for(int i = 0; i< choixTab.length ; i++){
                choixTab[i] = listChiffreNombre.get(i);
                if(listChiffreNombre.get(i) == chiffre){
                    index = i+1;
                }
            }
            listChoix = Arrays.asList(""+choixTab[0], ""+choixTab[1],""+choixTab[2],"");

        }

        /*
         **************************NIVEAU 4*******************************
         */
        else if(difficulte == 4){
            List<Integer> listChiffreNombre = Arrays.asList(listChiffre.get(0),listChiffre.get(1),listChiffre.get(2),listChiffre.get(3));
            Collections.shuffle(listChiffreNombre);
            int nombre = listChiffre.get(0) + 10*listChiffre.get(1)+100*listChiffre.get(2) + 1000*listChiffre.get(3);
            int valeur = (int)(Math.random()*4); // unite ou dizaine ou centaine
            int chiffre = listChiffre.get(valeur);
            String valeurString;

            switch (valeur)
            {
                case 0:
                    valeurString = "centième";
                    break;
                case 1:
                    valeurString = "dixième";
                    break;
                case 2:
                    valeurString = "unité";
                    break;
                case 3:
                    valeurString = "dizaine";
                    break;
                default:
                    valeurString  = "noValue";
                    break;
            }

        /*
        Liste d'enoncee.
         */
            //List<String> enonceList = Arrays.asList("Quel est la valeur de "+n1+" * "+n2+" ?","Peux tu me calculer "+n1+" * "+n2+" ?", "Aide moi à résoudre "+n1+" * "+n2);
            //String enonce = enonceList.get((int)(Math.random()*3));// enonce pris au hasard dans enonceList

            enonce = "Dans "+(double)nombre/100+" quel est le chiffre des "+ valeurString;

            index  = valeur + 1;
            int decal;
            int choixTab[] = new int[4];
            for(int i = 0; i< choixTab.length ; i++){
                choixTab[i] = listChiffreNombre.get(i);
                if(listChiffreNombre.get(i) == chiffre){
                    index = i+1;
                }
            }
            listChoix = Arrays.asList(""+choixTab[0], ""+choixTab[1],""+choixTab[2],""+choixTab[3]);

        }


        return new Question(enonce,listChoix,index);

    }
    /**
     * @autor swabahadine abdallah
     * Cette méthode gènere des questions qui serviront à demander à l'utilisateur d'écrire le numérateur ou le dénominateur d'une fraction sous la forme simplifiée
     * @return Objet de type Question traitant les fraction partie simplification
     */
    public static Question fraction(int numerateur , int denominateur,int r){
        Fraction mafraction = new Fraction(numerateur,denominateur);
        String reponse;
        String enonce;
        if(r == 0){
            enonce = "Simplifie et écris le numérateur de la fraction " + mafraction;
            reponse = mafraction.reduce().getNumerator()+"";
        }
        else {
            enonce = "Simplifie et écris le dénominateur de la fraction " + mafraction;
            reponse = mafraction.reduce().getDenominator()+"";
        }

        return new Question(enonce,reponse);

    }
    /**
     * @autor swabahadine abdallah
     * Cette méthode gènere des questions qui serviront à demander à l'utilisateur d'écrire la fraction sous la forme simplifiée
     * @return Objet de type Question traitant les fraction partie simplification
     */
    public static Question simplifierFraction(int numerateur,int denominateur){

        Fraction mafraction = new Fraction(numerateur,denominateur);
        String reponse = mafraction.reduce().toString();
        String enonce = "Simplifie " + mafraction.toString();
        return new Question(enonce,reponse);
    }

    /**
     * @autor swabahadine abdallah
     * Cette méthode gènere des questions pour additionner 2 fraction
     * @return Objet de type Question traitant les fraction partie addition
     */
    public static Question additionFraction( int numerateurF1,int numerateurF2,int denominateurF1, int denominateurF2){


        Fraction f1 = new Fraction(numerateurF1,denominateurF1);
        Fraction f2 = new Fraction(numerateurF2,denominateurF2);
        Fraction resultat = f1.reduce().add(f2.reduce());
        resultat = resultat.reduce();
        String reponse = resultat.toString();
        String enonce =   f1 +" + "+ f2+" = ?";
        return new Question(enonce,reponse);
    }

    /**
     * @autor swabahadine abdallah
     * Cette méthode gènere des questions pour soustraire 2 fraction
     * @return Objet de type Question traitant les fraction partie addition
     */
    public static Question soustractionFraction( int numerateurF1,int numerateurF2,int denominateurF1, int denominateurF2){

        Fraction f1 = new Fraction(numerateurF1,denominateurF1);
        Fraction f2 = new Fraction(numerateurF2,denominateurF2);
        Fraction resultat = f1.reduce().subtract(f2.reduce());
        resultat = resultat.reduce();
        String reponse = resultat.toString();
        String enonce =   f1 +" - "+ f2+" = ?";
        return new Question(enonce,reponse);
    }

    /**
     * @autor swabahadine abdallah
     * Cette méthode gènere des questions pour multiplier 2 fraction
     * @return Objet de type Question traitant les fraction partie addition
     */
    public static Question multiplierFraction(int numerateurF1,int numerateurF2,int denominateurF1, int denominateurF2){


        Fraction f1 = new Fraction(numerateurF1,denominateurF1);
        Fraction f2 = new Fraction(numerateurF2,denominateurF2);
        Fraction resultat = f1.reduce().multiply(f2.reduce());
        resultat = resultat.reduce();
        String reponse = resultat.toString();
        String enonce =   f1 +" * "+ f2+" = ?";
        return new Question(enonce,reponse);
    }

    /**
     * @autor swabahadine abdallah
     * Cette méthode gènere des questions pour diviser 2 fractions
     * @return Objet de type Question traitant les fraction partie division
     */
    public static Question diviserFraction(int numerateurF1,int numerateurF2,int denominateurF1, int denominateurF2){


        Fraction f1 = new Fraction(numerateurF1,denominateurF1);
        Fraction f2 = new Fraction(numerateurF2,denominateurF2);
        Fraction resultat = f1.reduce().divide(f2.reduce());
        resultat = resultat.reduce();
        String reponse = resultat.toString();
        String enonce =   f1 +" : "+ f2+" = ?";
        return new Question(enonce,reponse);
    }


    /**
     * @autor oualid benazzouz
     * Cette méthode gènere des questions pour la conversion masse
     * @return Objet de type Question
     */
    public static Question conversionMasses(int nombre , int en){

        String enoncephrase = "" ;
        int reponse = 0 ;
        //double nombre = (double)(((int)((Math.random()*10000))) )/ 1000;

        String enonce = "Combien fait "+nombre +" kg en g ?" ;
        String enonce2 = "Yo tu peux me donner "+nombre +" dg en cg ??" ;
        String enonce3 = "Peux tu m'aider a resoudre "+nombre + " kg en cg" ;
        String enonce4 = "Peux tu me calculer "+nombre +" dg en g" ;
        switch(en)
        {
            case 0: enoncephrase = enonce ;
                reponse = (int)(nombre * 1000) ;
                break;
            case 1: enoncephrase = enonce2 ;
                reponse = (int)(nombre * 10) ;
                break ;
            case 2: enoncephrase = enonce3 ;
                reponse = (int)(nombre * 100) ;
                break ;
            case 3: enoncephrase = enonce4 ;
                reponse = (int)(nombre*100) ;
                break ;
        }
        int result = reponse ;
        int index = 0 ;
        List<Integer> resultats = Arrays.asList(result,result/10,result/100,result*10);
        Collections.shuffle(resultats);
        for(int i=0; i < resultats.size() ; i++){
            if(resultats.get(i) == result){
                index=i+1 ;  // recuperer l'indice de resultat
            }
        }
        List<String> listChoix = Arrays.asList(""+resultats.get(0), ""+resultats.get(1),""+resultats.get(2),""+resultats.get(3));
        return new Question(enoncephrase,listChoix,index) ;

    }

    /**
     * @autor swabahadine abdallah
     * Cette méthode gènere des questions convertir les minutes en secondes
     * @return Objet de type Question
     */
    public static Question convertirMinutesEnSecondes(int min){
        Minute minute = new Minute(min);
        String enonce = "Converti en seconde "+ min + " minutes :";
        Seconde seconde = new Minute(min).convertirEnSeconde(minute);
        String reponse = "" + (int)seconde.getValeur();
        return new Question(enonce,reponse);
    }

    /**
     * @autor swabahadine abdallah
     * Cette méthode gènere des questions convertir les secondes en minutes
     * @return Objet de type Question
     */
    public static Question convertirSecondeEnMinutes(int sec){
        Seconde seconce = new Seconde(sec);
        String enonce = "Convertis en minutes : "+ sec + " secondes :";
        Minute minute = seconce.convertirEnMinute(seconce);
        String reponse = "" + (int)minute.getValeur();
        return new Question(enonce,reponse);
    }

    /**
     * @autor swabahadine abdallah
     * Cette méthode gènere des questions convertir en seconces
     * @return Objet de type Question
     */
    public static Question convertirEnSecondes(int min , int sec , int choix){


        int secReponse;
        String operation;
        if(choix == 0){
            secReponse = sec + min*60;
            operation = "et ";
        }
        else {
            secReponse = min*60 - sec;
            operation = "moins ";
        }
        String enonce = "Convertis en secondes : "+ min + " minutes " + operation+sec+" secondes";
        String reponse = "" + secReponse;
        return new Question(enonce,reponse);
    }

    /**
     * @autor swabahadine abdallah
     * Cette méthode gènere des questions convertir en minutes
     * @return Objet de type Question
     */
    public static Question convertirEnMinutes(int heure, int min , int choix){
        int minReponse;
        String operation;
        if(choix == 0){
            minReponse = min + heure*60;
            operation = "et ";
        }
        else {
            minReponse = heure*60 - min;
            operation = "moins ";
        }
        String enonce = "Convertis en minutes : "+ heure + " heure " + operation+min+" minutes";
        String reponse = "" + minReponse;
        return new Question(enonce,reponse);
    }

    /**
     * @autor swabahadine abdallah
     * Cette méthode gènere des questions convertir en minutes
     * @return Objet de type Question
     */
    public static Question convertirEnMinutes2(int quartHeure , int heure , int choix){
        int minQ = quartHeure*15;
        int minReponse;
        String operation;
        if(choix == 0){
            minReponse = minQ + heure*60;
            operation = "plus ";
        }
        else {
            minReponse = heure*60 - minQ;
            operation = "moins ";
        }
        String enonce = "Convertis en minutes : "+ heure + " heure " + operation+quartHeure+" quart d'heure";
        String reponse = "" + minReponse;
        return new Question(enonce,reponse);
    }


    /**
     * @autor swabahadine abdallah
     * Cette méthode gènere des questions convertir en minutes
     * @return Objet de type Question
     */
    public static Question lesQuartHeures(int quartHeure){
        int minQ = quartHeure*15;
        String enonce ="";
        switch(quartHeure){
            case 2:
                enonce = "Convertis en minutes : 1 demi-heure";

                break;
            default:
                enonce = "Convertis en minutes : "+quartHeure+" quart d'heure";
                break;
        }

        String reponse = "" + minQ;
        return new Question(enonce,reponse);
    }





}
