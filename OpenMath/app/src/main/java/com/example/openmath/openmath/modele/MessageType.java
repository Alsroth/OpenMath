package com.example.openmath.openmath.modele;

/**
 * Created by KHAN on 22/04/2018.
 * Spécifier les quatre type de messages envoyer vers le serveur
 */

public enum MessageType {
    LOGIN("LOGIN"), INSCRIPTION("INSCRIPTION"), SAVE_SCORE("SAVE_SCORE"), GET_SCORE("GET_SCORE");

    private String value;

    private MessageType(String v) {
        this.value = v;
    }

    public String getValue(){
        return value;
    }

}


