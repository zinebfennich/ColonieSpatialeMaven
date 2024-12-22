package org.example.Menus;

import org.example.Colonie.Colon;
import org.example.Colonie.Colonie;
import org.example.DataAccess.FichierColonie;
import org.example.Colonie.Ressource;
import org.example.ExceptionColonie.ExceptionColon;
import org.example.Service.AttributionOptimale;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu3 {
    private int choix;
    private Colonie colonie; // Référence à la colonie
    Map<Ressource, Colon> ressources;


    public Menu3(Colonie colonie) {
        this.colonie = colonie; // Réutilisation de l'instance
    }

    public void afficherMenu3(Scanner scanner1) {

        do{
            // Affiche le 3ème menu
            System.out.println("Veuillez entrer votre choix pour le deuxieme menu");
            System.out.println("1 Resolution automatique");
            System.out.println("2 Sauvegarde de la solution actuelle ");
            System.out.println("3 Fin ");

            choix = scanner1.nextInt();
            scanner1.nextLine(); // Consomme le saut de ligne

            switch (choix) {
                case 1:
                    List<Ressource> ressources = colonie.getRessources().keySet().stream().toList();
                    AttributionOptimale attributionOpt = new AttributionOptimale(colonie,colonie.getListeColons() , colonie.getRessources());
                    try{
                        attributionOpt.affectationOptimisee(colonie.getListeRessources());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("l'attribution optimale a ete effectuee");
                    colonie.affichageaffection();
                    break;
                case 2:
                    try {
                        System.out.println("\nVeuillez entrer le nom du fichier pour sauvegarder les affectations :");
                        System.out.println("Vous trouverez le fichier dans le repertoire courant ");
                        String nomFichier = scanner1.nextLine().trim();
                        FichierColonie.saveAttribution(nomFichier,colonie);
                    } catch (Exception e) {
                        System.out.println("Erreur inattendue : " + e.getMessage());
                    }
                    break;
                case 3:
                    break;

                default:
                    System.out.println("Choix invalide, veuillez reessayer");
                    break;

            }


        }while(choix != 3);

    }
}
