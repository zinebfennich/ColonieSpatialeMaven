package org.example.Affichages;

import org.example.Colonie.Colon;
import org.example.Colonie.Colonie;
import org.example.Colonie.Ressource;

import java.util.List;

public class RecapColonie {

    private Colonie colonie;

    public RecapColonie(Colonie colonie) {
        this.colonie = colonie;
    }

    public void afficherEtatColonie() {
        System.out.println("=== Etat de la Colonie ===");
        List<Colon> colons = colonie.getlistecolons();

        for (Colon colon : colons) {
            System.out.println("Colon : " + colon.getNom());

            // Afficher les préférences du colon
            System.out.print("  Preferences : ");
            List<Ressource> preferences = colon.getlistepreferences();
            if (preferences.isEmpty()) {
                System.out.println("Aucune preference definie.");
            } else {
                for (Ressource ressource : preferences) {
                    System.out.print(ressource.getNom() + " ");
                }
                System.out.println();
            }

            // Afficher les ennemis du colon
            System.out.print("  Ennemis : ");
            List<Colon> ennemis = colon.getEnnemis();
            if (ennemis.isEmpty()) {
                System.out.println("Aucun ennemi defini.");
            } else {
                for (Colon ennemi : ennemis) {
                    System.out.print(ennemi.getNom() + " ");
                }
                System.out.println();
            }

            // Afficher la ressource attribuee au colon
            Ressource ressourceAttribuee = colon.getRessourceAttribuee();
            System.out.println("  Ressource attribuee : " +
                    (ressourceAttribuee != null ? ressourceAttribuee.getNom() : "Aucune"));
            System.out.println();
        }

        System.out.println("=== Fin de l'etat de la colonie ===");
    }
}