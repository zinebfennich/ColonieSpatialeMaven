package Menus;

import Affichages.RecapColonie;
import Colonie.*;
import Service.AttributionNaive;
import Service.AttributionOptimale;

import java.util.List;
import java.util.Scanner;


public class Menu2 {
    private int choix;

    private Colonie colonie; // Référence à la colonie partagée

    public Menu2(Colonie colonie) {
        this.colonie = colonie; // Réutilisation de l'instance
    }

    public void afficherMenu2(Scanner scanner1) throws Exception {
        AttributionNaive attNaive = new AttributionNaive(colonie.getlistecolons(),colonie.getRessources(),colonie);
        attNaive.affectationNaive();
        RecapColonie affichage = new RecapColonie(colonie);
        //colonie.affectationOptimisee(); // Remplace affectationNaive par attributionOptimale
        // Appel de l'attribution optimisée mta el tfol
        //List<Ressource> ressources = colonie.getRessources().keySet().stream().toList(); // Exemple pour obtenir les ressources

        // Créez une instance d'AttributionOptimale avec la colonie unique
        //AttributionOptimale attributionOpt = new AttributionOptimale(colonie, colonie.getListeColons(), colonie.getRessources());

        // Appel de la méthode d'attribution optimisée
        //int coutJaloux = attributionOpt.affectationOptimisee(ressources);
        //System.out.println("Coût de jalousie après attribution optimisée : " + coutJaloux);


        do {
            // Affiche le 2ème menu
            System.out.println("Veuillez entrer votre choix pour le deuxieme menu");
            System.out.println("1 Echanger les ressources de deux colons");
            System.out.println("2 Afficher le nombre de colons jaloux ");
            System.out.println("3 Fin ");

            /*if (scanner1.hasNextInt()) {
                choix = scanner1.nextInt();
                scanner1.nextLine(); // Consomme le saut de ligne
            } else {
                System.out.println("Erreur : Veuillez entrer un numéro valide.");
                scanner1.nextLine(); // Vide la ligne incorrecte
                continue;
            }*/
            try {
                System.out.print("Votre choix : ");
                String input = scanner1.nextLine().trim(); // Lit l'entrée utilisateur

                if (!input.matches("\\d+")) { // Vérifie si l'entrée est un entier
                    System.out.println("Erreur : Veuillez entrer un numero valide.");
                    continue;
                }

                choix = Integer.parseInt(input); // Convertit en entier
            } catch (Exception e) {
                System.out.println("Erreur inattendue : " + e.getMessage());
                break;
            }



            switch (choix) {
                case 1:
                    System.out.println(
                            "Entrez les deux colons pour lesquels vous voulez echanger les ressources (par exemple, A B) :");
                    String input = scanner1.nextLine();
                    String[] parts = input.split(" ");

                    if (parts.length >= 2) {
                        String nom1 = parts[0];
                        String nom2 = parts[1];
                        Colon colon1 = colonie.getColon(nom1);
                        Colon colon2 = colonie.getColon(nom2);

                        if (colon1 == null || colon2 == null) {
                            System.out.println("Un des colons n'existe pas");
                            break;
                        }
                        colonie.echangerRessources(colon1, colon2);
                    } else {
                        System.out.println("Erreur de lecture de colons, veuillez entrer deux caracteres.");
                    }
                    affichage.affichageaffection();
                    break;

                case 2:
                    affichage.affichageaffection();
                    break;
                case 3:
                    break;

                default:
                    System.out.println("Choix invalide, veuillez reessayer");
                    break;
            }
        } while (choix != 3);
    }
}