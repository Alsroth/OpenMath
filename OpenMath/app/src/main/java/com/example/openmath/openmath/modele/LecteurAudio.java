package com.example.openmath.openmath.modele;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.example.openmath.openmath.R;
import com.example.openmath.openmath.controleur.SettingsActivite;

/**
 * Created by alsroth on 04/03/18.
 */

public class LecteurAudio
{
    public static MediaPlayer musique;
    public static MediaPlayer bruitage;
    private static SoundPool sound;
    public static boolean sonActive ;
    public static boolean etatLecteurAudio =false;

    /**
     * @author alexandre lallis
     * Cette méthode utilise l'objet MediaPLayer d'android qui permet de jouer un son.
     * @param c
     * @param id
     */
    public static void jouerMusique(Context c, int id)
    {

        if(sonActive)
        {
            musique = MediaPlayer.create(c,id);
            sound = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
            if(!musique.isPlaying())
            {
                etatLecteurAudio = true;
                musique.start();
                musique.setLooping(true);
            }
        }

    }

    /**
     * @author alexandre lallis
     * Cette méthode utilise l'objet MediaPLayer d'android qui permet de jouer un son.
     * @param c
     * @param id
     */
    public static void jouerBruitage(Context c, int id)
    {
        if(sonActive)
        {
            bruitage = MediaPlayer.create(c,id);
            sound = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);

            if(!bruitage.isPlaying())
            {
                etatLecteurAudio = true;
                bruitage.start();
            }
        }

    }


    /**
     * @author alexandre lallis
     * Cette méthode arrete le MediaPlayer.s
     */
    public static void stopAudio()
    {
        etatLecteurAudio =false;
        musique.stop();
    }

    public static void pauseAudio()
    {
        musique.pause();
    }

    public static void restartAudio()
    {
            musique.start();
    }


}

