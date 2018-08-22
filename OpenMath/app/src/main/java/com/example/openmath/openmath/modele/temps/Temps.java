package com.example.openmath.openmath.modele.temps;

/**
 * Created by swabahadine on 21/03/2018.
 */

public abstract class Temps implements InterfaceTemps {

    protected double valeur;

    // Constructeur
    public Temps(double valeur) {
        super();
        this.valeur = valeur;
    }
    // getters & setters
    public double getValeur() {
        return valeur;
    }
    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    // Convertisseurs
    public Seconde convertirEnSeconde(Temps temps) {
        double v =  temps.valeurEnSeconde()*temps.valeur();
        return new Seconde(v);
    }
    public Minute convertirEnMinute(Temps temps) {
        return new Minute(temps.convertirEnSeconde(temps).valeur()/60);
    }

    public Heure convertirEnHeure(Temps temps) {
        return new Heure(temps.convertirEnMinute(temps).valeur/60);
    }

    public Jour convertirEnJour(Temps temps) {
        return new Jour(temps.convertirEnHeure(temps).valeur/24);
    }

    public Annee convertirEnAnnee(Temps temps) {
        return new Annee(temps.convertirEnJour(temps).valeur/365);
    }


    @Override
    public String toString() {
        return "" + valeur;
    }


}

