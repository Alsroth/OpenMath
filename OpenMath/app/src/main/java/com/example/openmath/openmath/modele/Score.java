package com.example.openmath.openmath.modele;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by swabahadine on 18/04/2018.
 */

public class Score {
    private List<Integer> calcul;
    private List<Integer> numeration;
    private List<Integer> mesure;
    private List<Integer> defi;




    public Score(){

    }
    public Score(List<Integer> calcul, List<Integer> numeration, List<Integer> mesure, List<Integer> defi) {
        this.calcul = calcul;
        this.numeration = numeration;
        this.mesure = mesure;
        this.defi = defi;
    }
    // Getters & Setters
    public List<Integer> getCalcul() {
        return calcul;
    }

    public void setCalcul(List<Integer> calcul) {
        this.calcul = calcul;
    }

    public List<Integer> getNumeration() {
        return numeration;
    }

    public void setNumeration(List<Integer> numeration) {
        this.numeration = numeration;
    }

    public List<Integer> getMesure() {
        return mesure;
    }

    public void setMesure(List<Integer> mesure) {
        this.mesure = mesure;
    }

    public List<Integer> getDefi() {
        return defi;
    }

    public void setDefi(List<Integer> defi) {
        this.defi = defi;
    }


    public String convertListEnString(List<Integer> liste){
        String str = "";
        for(Integer i : liste){
            str = str+i;
        }
        return str;
    }

    /**
     * @author swabahadine abdallah
     * Cette fonction sert à convertir une chaine formatée en score
     * @param chaineFormatee
     * @return score
     */
    public Score chargerScore(String chaineFormatee){
        ArrayList<Integer> scoreList = new ArrayList<>();

        ArrayList<Integer> calcul = new ArrayList<>();
        ArrayList<Integer> numeration = new ArrayList<>();
        ArrayList<Integer> mesure = new ArrayList<>();
        ArrayList<Integer> defi = new ArrayList<>();
        String [] lesScores = chaineFormatee.split("\n");

        for (String scores : lesScores){
            String [] unScore = scores.split(";");
            for (String score : unScore ){
                int scoreConverti = Integer.parseInt(score);
                scoreList.add(scoreConverti);
            }

        }
        calcul.add(scoreList.get(0));
        calcul.add(scoreList.get(1));
        calcul.add(scoreList.get(2));
        numeration.add(scoreList.get(3));
        numeration.add(scoreList.get(4));
        numeration.add(scoreList.get(5));
        mesure.add(scoreList.get(6));
        mesure.add(scoreList.get(7));
        mesure.add(scoreList.get(8));
        defi.add(scoreList.get(9));
        defi.add(scoreList.get(10));
        defi.add(scoreList.get(11));
        defi.add(scoreList.get(12));

        return new Score(calcul,numeration,mesure,defi);

    }

    /*
    FICHIER ICI





     */



    /**
     * @author swabahadine abdallah
     * Cette fonction sert mettre un bouton en mode bloqué
     * @param data,context
     * @return rien
     */
    private void writeToFile(String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * @author swabahadine abdallah
     * Cette fonction sert à lire le fichier config.txt
     * @param context
     * @return le contenu du fichier en String
     */
    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("config.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    /**
     * @author swabahadine abdallah
     *
     * @param
     * @return Utilisateur local
     */
    public Utilisateur chargerUtilisateurLocal(Context context){
        BufferedReader In = null;
        String recupScoreString;
        try {
            In = new BufferedReader(new FileReader("config.txt"));
            //si le fichier existe

        } catch (FileNotFoundException fnfe) {



        }
        recupScoreString = readFromFile(context);
        //Score score = new Score().chargerScore(recupScoreString);
        return new Utilisateur("User", new Score());
    }

    /**
     * @author swabahadine abdallah
     *
     * @param liste
     * @return chaine formatée
     */
    public String formaterList(List<Integer> liste){
        String chaine = "";
        for(int i : liste){
            chaine=chaine+i+";";
        }
        return chaine;
    }
    /**
     * @author swabahadine abdallah
     *
     * @param
     * @return score en chaine formatée
     */
    public String formaterScore(){
        String calcul = formaterList(this.calcul);
        String numeration = formaterList(this.numeration);
        String mesure = formaterList(this.mesure);
        String score = calcul+"\n"+numeration+"\n"+mesure;
        return score;
    }
    /**
     * @author swabahadine abdallah
     * Cette fonction sert à ecire les scores sous forme formatée dans un fichier
     * @param context
     * @return rien
     */
    public void pushScore(Context context){
        String score = formaterScore();
        writeToFile(score,context);
    }

}

