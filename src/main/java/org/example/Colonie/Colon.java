package org.example.Colonie;

import java.util.*;

public class Colon {
    private String nom;
    private ArrayList<Ressource> preferences;
    private ArrayList<Colon> ennemis;
    private Ressource ressourceAttribuee;

    private boolean jaloux;

    public Colon(String nom) {
        this.nom = nom;
        this.preferences = new ArrayList<>();
        this.ennemis = new ArrayList<>();
        this.ressourceAttribuee = null;
        this.jaloux = false;
    }

    public String getNom() {
        return nom;
    }

    public void setJaloux() {
        this.jaloux = true;
    }

    public void ajoutennemi(Colon c) {
        ennemis.add(c);
    }

    public void ajoutpreference(Ressource p) {
        preferences.add(p);

    }

    public ArrayList<Ressource> getlistepreferences() {
        return preferences;
    }

    public void AfficherListePref() {
        System.out.println("preference du colon" + nom + ": " + this.preferences);
    }

    public void setRessourceAttribuee(Ressource r) {
        ressourceAttribuee = r;
    }

    public Ressource getRessourceAttribuee() {
        return ressourceAttribuee;
    }

    public ArrayList<Colon> getEnnemis() {
        return ennemis;
    }


    public boolean prefereObjet(Ressource ressource) {
        if (this.ressourceAttribuee == null || ressource == null) {
            System.out.println("Erreur : ressourceAttribuee ou ressource est null");
            return false;
        }
        if (!this.preferences.contains(ressource)) {
            System.out.println(
                    "Erreur : la ressource " + ressource + " n'est pas trouvee dans les preferences de " + nom);
            System.out.println("preference du colon" + nom + ": " + this.preferences);
            return false;
        }
        if (!this.preferences.contains(this.ressourceAttribuee)) {
            System.out.println("Erreur : la ressource attribuee " + this.ressourceAttribuee
                    + " n'est pas trouvee dans les preferences de " + nom);
            return false;
        }
        boolean result = this.preferences.indexOf(ressource) < this.preferences.indexOf(this.ressourceAttribuee);
        return result;
    }
    @Override
    public String toString() {
        return nom; // Retourne uniquement le nom du colon
    }


}
