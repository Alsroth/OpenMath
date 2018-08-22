package com.example.openmath.openmath.modele;

/**
 * Created by alsroth on 13/02/18.
 */

public class Utilisateur
{
    private String pseudo ;
    private String motDePasse ;
    private String prenom ;
    private String nom ;
    private String dateDeNaissance ;
    private String mail ;
    private Score score ;

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Utilisateur(String pseudo, String motDePasse, String prenom, String nom, String dateDeNaissance, String mail)
    {
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
        this.prenom = prenom;
        this.nom = nom;
        this.dateDeNaissance = dateDeNaissance;
        this.mail = mail;
    }
    public Utilisateur(String pseudo, Score score)
    {
        this.pseudo = pseudo;
        this.score = score;
        this.prenom = pseudo;
        this.nom = pseudo;
    }
    public Utilisateur(){
        this.pseudo = "Utilisateur";
        this.prenom = pseudo;
        this.nom = pseudo;
    }
    //Affichage des informations d'une utilisateur

    @Override
    public String toString()
    {
        return "Utilisateur{" +
                "pseudo='" + pseudo + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", dateDeNaissance='" + dateDeNaissance + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }

    //getters&setters :

    public String getPseudo()
    {
        return pseudo;
    }

    public void setPseudo(String pseudo)
    {
        this.pseudo = pseudo;
    }

    public String getMotDePasse()
    {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse)
    {
        this.motDePasse = motDePasse;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getDateDeNaissance()
    {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(String dateDeNaissance)
    {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }
}
