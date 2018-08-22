package com.example.openmath.openmath.modele.temps;

/**
 * Created by swabahadine on 21/03/2018.
 */

public class Annee extends Temps {

    public Annee(double valeur) {
        super(valeur);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double valeurEnSeconde() {
        // TODO Auto-generated method stub
        return 31536000;
    }

    @Override
    public double valeur() {
        // TODO Auto-generated method stub
        return this.valeur;
    }

}
