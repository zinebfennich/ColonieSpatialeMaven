
package org.example.Main1;
import org.example.Menus.Menu2;
import org.example.Colonie.Colonie;
import org.example.Affichages.RecapColonie;
import org.example.DataAccess.FichierColonie;
import org.example.Menus.Menu1;
import org.example.ExceptionColonie.ExceptionColon;
import org.example.Menus.Menu3;

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
            System.out.println("Chargement des données depuis le fichier : " + cheminFichier);

            try {
                colonie = FichierColonie.chargerDepuisFichier(cheminFichier, n);
                System.out.println("Colonie chargée depuis le fichier !");
            } catch (IOException e) {
                System.err.println("Erreur d'entrée/sortie : " + e.getMessage());
                scanner.close(); // Fermer le scanner avant de quitter
                return;
            } catch (ExceptionColon e) {
                System.err.println("Erreur : " + e.getMessage());
                scanner.close(); // Fermer le scanner avant de quitter
                return;
            }

            // Si la colonie est chargée, afficher le menu 3
            Menu3 menu3 = new Menu3(colonie);
            menu3.afficherMenu3(scanner);

        } else {
            // Aucune argument fourni, exécuter les menus 1 et 2
            System.out.println("Aucun fichier fourni. Construction manuelle de la colonie.");
            System.out.println("Entrez la taille de la colonie spatiale :");
            n = scanner.nextInt();
            scanner.nextLine(); // Consomme le saut de ligne après nextInt()

            if (n < 0) {
                System.out.println("La taille de la colonie ne peut pas être négative.");
                scanner.close(); // Fermer le scanner avant de quitter
                return;
            }

            Menu1 menu1 = new Menu1(n);
            menu1.afficherMenu1(scanner);

            colonie = menu1.getColonie();
            if (colonie == null) {
                System.out.println("Erreur : La colonie n'a pas été correctement initialisée.");
                scanner.close(); // Fermer le scanner avant de quitter
                return;
            }

            // Afficher le récap de la colonie avant le menu 2
            System.out.println("\n=== Récapitulatif de l'état de la colonie ===");
            RecapColonie recap = new RecapColonie(colonie);
            recap.afficherEtatColonie();

            Menu2 menu2 = new Menu2(colonie);
            menu2.afficherMenu2(scanner);
        }

        scanner.close(); // Fermer le scanner après l'exécution
    }
}
