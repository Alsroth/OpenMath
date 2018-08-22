package com.example.openmath.openmath.modele;

import java.util.List;

/**
 * Created by alsroth on 13/02/18.
 */

public class UtilisateurBanque
{
    private List<Utilisateur> listUtilisateur;

    public UtilisateurBanque(List<Utilisateur> listUtilisateur)
    {
        this.listUtilisateur = listUtilisateur;
    }

    //Affichage de la liste des utilisateurs :

    @Override
    public String toString()
    {
        return "UtilisateurBanque{" +
                "listUtilisateur=" + listUtilisateur +
                '}';
    }

    //getters&setters :

    public List<Utilisateur> getListUtilisateur()
    {
        return listUtilisateur;
    }

    public void setListUtilisateur(List<Utilisateur> listUtilisateur)
    {
        this.listUtilisateur = listUtilisateur;
    }
}
