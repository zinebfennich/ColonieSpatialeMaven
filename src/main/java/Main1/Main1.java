package Main1;

import Menus.Menu2;
import Colonie.Colonie;
import Affichages.RecapColonie;
import DataAccess.FichierColonie;
import Menus.Menu1;
import ExceptionColonie.ExceptionColon;
import Menus.Menu3;

import java.io.IOException;
import java.util.Scanner;

public class Main1 {
    public static int n = 0; // Déclaration statique pour utilisation globale

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in); // Création du scanner une seule fois
        Colonie colonie = null;


        // Vérifier si un fichier est passé en argument
        if (args.length > 0) {
            String cheminFichier = args[0];
            System.out.println("Chargement des donnees depuis le fichier : " + cheminFichier);

            try {
                colonie = FichierColonie.chargerDepuisFichier(cheminFichier, n);
                System.out.println("Colonie chargee depuis le fichier !");
            } catch (IOException e) {
                System.err.println("Erreur d'entree/sortie : " + e.getMessage());
                scanner.close(); // Fermer le scanner avant de quitter
                return;
            } catch (ExceptionColon e) {
                System.err.println("Erreur : " + e.getMessage());
                scanner.close(); // Fermer le scanner avant de quitter
                return;
            }
            RecapColonie affichage1 = new RecapColonie(colonie);
            affichage1.afficherEtatColonie();

            // Si la colonie est chargée, afficher le menu 3
            Menu3 menu3 = new Menu3(colonie);
            menu3.afficherMenu3(scanner);

        } else {
            // Aucune argument fourni, exécuter les menus 1 et 2
            System.out.println("Aucun fichier fourni. Construction manuelle de la colonie.");
            // Demande de la taille de la colonie jusqu'à ce qu'une valeur valide soit fournie
            boolean tailleValide = false;
            while (!tailleValide) {
                System.out.println("Entrez la taille de la colonie spatiale (doit être un entier positif) :");
                try {
                    n = Integer.parseInt(scanner.nextLine());
                    if (n > 0) {
                        tailleValide = true; // La taille est valide
                    } else {
                        System.out.println("Erreur : La taille de la colonie doit être un entier positif. Veuillez réessayer.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erreur : Veuillez entrer un nombre entier valide.");
                }

            }

            Menu1 menu1 = new Menu1(n);
            menu1.afficherMenu1(scanner);

            colonie = menu1.getColonie();
            if (colonie == null) {
                System.out.println("Erreur : La colonie n'a pas ete correctement initialisee.");
                scanner.close(); // Fermer le scanner avant de quitter
                return;
            }

            // Afficher le récap de la colonie avant le menu 2
            RecapColonie affichage2 = new RecapColonie(colonie);
            affichage2.afficherEtatColonie();

            Menu2 menu2 = new Menu2(colonie);
            menu2.afficherMenu2(scanner);
        }

        scanner.close(); // Fermer le scanner après l'exécution
    }
}