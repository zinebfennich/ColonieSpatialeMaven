package Affichages;

import Colonie.Colon;
import Colonie.Colonie;
import Colonie.Ressource;

import java.util.List;

public class RecapColonie {

    private Colonie colonie;

    public RecapColonie(Colonie colonie) {
        this.colonie = colonie;
    }

    public void afficherEtatColonie() {
        System.out.println("\n=== Recapitulatif de l'etat de la colonie ===");
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


    public void affichageaffection() {
        // Affichage du nombre de colons jaloux
        System.out.println("\n*** Voici le nombre de colons jaloux : ***");
        System.out.println("   " + colonie.nombreColonsJaloux());

        // Affichage de l'affectation des ressources
        System.out.println("\n*** Voici l'affectation des ressources ***");
        System.out.println("Colon                Ressource attribuee");
        System.out.println("------------------------------------------");

        // Trier les colons par nom et afficher les affectations
        colonie.trierColonsParNom();

        for (Colon colon : colonie.getListeColons()) {
            Ressource ressource = colon.getRessourceAttribuee(); // Obtenir la ressource attribuée

            if (ressource != null) {
                System.out.printf("%-20s %s%n", colon.getNom(), ressource.getNom());
            } else {
                System.out.printf("%-20s %s%n", colon.getNom(), "aucune ressource attribuée");
            }
        }
    }

}