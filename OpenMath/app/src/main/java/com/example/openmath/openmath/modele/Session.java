package com.example.openmath.openmath.modele;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

import static android.content.Context.MODE_PRIVATE;
import static java.sql.Types.NULL;

/**
 * Created by KHAN on 13/04/2018.
 * Contient l'utilisateur connecté ainsi que son score
 */
public class Session {

    private static String currentUserName = null;

    /**
     * Un Map qui contient les different categories des question (Multiplication, Calcul, division etc) en clef et
     * Le score respectif obtenu par l'utilisateur connecté en mode test
     */
    private static Map<String, Integer> scores = new TreeMap<String,Integer>();
    private static String dernierQuestionCategory = null;
    private static Utilisateur user ;

    public static Utilisateur getUser() {
        return user;
    }

    public static void setUser(Utilisateur user) {
        Session.user = user;
    }

    public static void setCurrentUserName(String s) {
        currentUserName = s;
    }

    public static String getCurrentUserName() {
        return currentUserName;
    }


    /**
     * Rajouter des points dans le category de question correspondant
     * @param questionCategory
     * @param points
     */
    public static void addScore(String questionCategory, int points) {
        dernierQuestionCategory = questionCategory;
        if(scores.containsKey(questionCategory)) {
            scores.put(questionCategory, scores.get(questionCategory)+points);
        } else {
            scores.put(questionCategory, points);
        }
    }

    /**
     * Réinisialiser les scores
     */
    public static void resetScores() {
        scores = new TreeMap<String,Integer>();
    }

    public static void addScore(String questionCategory) {
        addScore(questionCategory, 1);
    }

    public static Map<String, Integer> getScores() {
        return scores;
    }

    public static String getDernierQuestionCategory() {
        return dernierQuestionCategory;
    }

    /**
     * Renvoie le meuilleur score obtenu ainsi que sa category question correspondant dans un String
     * Si il y a plusieur meuilleur score le permier sera envoyer
     */
    public static String getMeilleuresScore() {
        if(scores.isEmpty()) return "";
        String res = "";
        int max = Integer.MIN_VALUE;
        for(Map.Entry<String, Integer> me : scores.entrySet() ) {
            if(me.getValue() > max) {
                max = me.getValue();
                res = me.getKey();
            }
        }
        res += " : " + max;
        return res;
    }

    /**
     *
     * @param message
     */
    public static void saveScoresFromServer(String message) {
        try {
            JSONObject jo = new JSONObject(message);
            /*for(String cle : jo.keys()) {

            }*/
        } catch (JSONException je) {
            je.getStackTrace();
        }
    }

    /**
     * @author swabahadine abdallah
     * Cette fonction sert à stocker le score dans le shared preference Scores
     * @param context,scoreFormate
     * @return rien
     */
    public static void commitScore(Context context, float scoreFormate){
        SharedPreferences pref = context.getSharedPreferences("Scores", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        float scores = pref.getFloat("scores",NULL);
        editor.putFloat("scores",scoreFormate);
        editor.commit();

    }

    /**
     * @author swabahadine abdallah
     * Cette fonction sert à stocker le score dans le shared preference Scores
     * @param context,scoreFormate
     * @return rien
     */
    public static void pullScore(Context context){
        SharedPreferences pref = context.getSharedPreferences("Scores", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        float scores = pref.getFloat("scores",0);
        Session.setScores(scoreConverti(scores));

    }

    /**
     * @author swabahadine abdallah
     * Cette fonction sert à ecire les scores sous forme formatée dans un fichier
     * @param
     * @return rien
     */
    public static float scoreFormatee(){
        float score = 1;
        int i = 1;
        float scoreActivite;
        String [] jeuActivite = {"Addition et soustraction","Multiplication","Division","Unité Dizaine Centaine","Les fractions","Convertion","Conversion temps"};
        Map<String, Integer> scoresMap = Session.getScores();
        for(String activite : jeuActivite){
            if(scoresMap.get(activite) != null){
                scoreActivite = (float)scoresMap.get(activite);
                score+=scoreActivite*1/(10*i++);
            }
        }
        return score;

    }

    /**
     * @author swabahadine abdallah
     * Cette fonction sert à ecire les scores sous forme formatée dans un fichier
     * @param
     * @return rien
     */
    public static Map<String, Integer> scoreConverti(float score){
        float scoreCopie = score;
        int i = 1;
        String [] jeuActivite = {"Addition et soustraction","Multiplication","Division","Unité Dizaine Centaine","Les fractions","Convertion","Conversion temps"};
        Map<String, Integer> scoresMap = Session.getScores();
        for(String activite : jeuActivite){
            int sc = (int)scoreCopie;
            scoreCopie-=sc;
            scoreCopie*=10;
            sc = (int)scoreCopie;
            scoresMap.put(activite,sc);
        }
        return scoresMap;

    }

    public static void setScores(Map<String, Integer> scores) {
        Session.scores = scores;
    }



}
