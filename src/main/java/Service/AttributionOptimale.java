package Service;
import Colonie.*;

import java.util.*;


public class AttributionOptimale {
    private List<Colon> colons;
    private Map<Ressource, Colon> mapRessources;
    private Colonie colonies; // Une seule colonie

    public AttributionOptimale( Colonie colonies,List<Colon> colons, Map<Ressource, Colon> ressources) {
        this.colons = colons;
        this.mapRessources = ressources;
        this.colonies = colonies; // Version mtaa el tfol

    }

   /* public int affectationOptimisee() {
        // Étape 1 : Trier les colons par ordre décroissant du nombre d'ennemis
        List<Colon> colonsTries = new ArrayList<>(colons);
        colonsTries.sort((a, b) -> b.getEnnemis().size() - a.getEnnemis().size());

        // Étape 2 : Affecter les ressources
        for (Colon colon : colonsTries) {
            Ressource meilleureRessource = null;
            int minJalousie = Integer.MAX_VALUE;

            for (Ressource ressource : colon.getlistepreferences()) {
                if (ressources.get(ressource) == null) { // La ressource n'est pas encore attribuée
                    int jalousie = calculerJalousie(colon, ressource);
                    if (jalousie < minJalousie) {
                        minJalousie = jalousie;
                        meilleureRessource = ressource;
                    }
                }
            }

            // Affecter la meilleure ressource trouvée au colon
            if (meilleureRessource != null) {
                colon.setRessourceAttribuee(meilleureRessource);
                ressources.put(meilleureRessource, colon);
            }
        }
    // Étape 3 : Optimisation locale par échanges
    boolean amelioration;
    do {
        amelioration = false;
        for (int i = 0; i < colonsTries.size(); i++) {
            for (int j = i + 1; j < colonsTries.size(); j++) {
                Colon colon1 = colonsTries.get(i);
                Colon colon2 = colonsTries.get(j);
                // Tester l'échange
                Ressource ressource1 = colon1.getRessourceAttribuee();
                Ressource ressource2 = colon2.getRessourceAttribuee();

                colon1.setRessourceAttribuee(ressource2);
                colon2.setRessourceAttribuee(ressource1);

                int nouveauCout = calculerTotalJalousie(colonsTries);
                if (nouveauCout < calculerTotalJalousie(colonsTries)) {
                    amelioration = true; // Amélioration trouvée
                } else {
                    // Annuler l'échange s'il n'y a pas d'amélioration
                    colon1.setRessourceAttribuee(ressource1);
                    colon2.setRessourceAttribuee(ressource2);
                }
            }
        }
    } while (amelioration);

    return calculerTotalJalousie(colonsTries);
}


private int calculerJalousie (Colon colon, Ressource ressource) {
    int jalousie = 0;
    for (Colon ennemi : colon.getEnnemis()) {
        if (ennemi.getlistepreferences().contains(ressource) && !ennemi.getRessourceAttribuee().equals(ressource)) {
            jalousie++;
        }
    }
    return jalousie;
}

private int calculerTotalJalousie(List<Colon> colons) {
    int totalJalousie = 0;
    for (Colon colon : colons) {
        totalJalousie += calculerJalousie(colon, colon.getRessourceAttribuee());
    }
    return totalJalousie;
}


    public void afficherAffectations() {
        for (Map.Entry<Ressource, Colon> entry : ressources.entrySet()) {
            if (entry.getValue() != null) {
                System.out.println(entry.getValue().getNom() + " -> " + entry.getKey().getNom());
            }
        }
    }*/

    //algo mta el tfol
    public int affectationOptimisee ( List<Ressource> ressources) throws Exception {
        //  Colonie colonie = colonies.get(colonieN);

        // Vérification initiale
        if (colonies.getListeColons().size() != ressources.size()) {
            throw new Exception("Nombre de colons different du nombre de ressources");
        }

        // 1. Combinaison des deux algos Effectuer l'affectation naïve pour letat initial
        //colonies.affectationNaive(); // Appel de la méthode d'affectation naïve
        AttributionNaive attNaive = new AttributionNaive(colons,mapRessources,colonies);
        attNaive.affectationNaive();

        // 1. Créer une copie du graphe des relations et des préférences pour manipulation
        List<Colon> colonsTries = new ArrayList<>(colonies.getListeColons());

        // 2. Trier les colons par ordre décroissant de contraintes (nombre de relations détestables)
        colonsTries.sort((c1, c2) -> c2.getEnnemis().size() - c1.getEnnemis().size());

        int meilleurCout = Integer.MAX_VALUE;
        Map<Colon, Ressource> meilleureAffectation = new HashMap<>();

        // 3. Phase 1 : Essayer plusieurs affectations initiales
        for (int essai = 0; essai < 10; essai++) {
            // Réinitialiser les ressources
            List<Ressource> ressourcesTemp = new ArrayList<>(ressources);


            // Affecter les ressources en priorité aux colons ayant le plus de contraintes
            for (Colon colon : colonsTries) {
                Ressource meilleureRessource = null;
                int minConflits = Integer.MAX_VALUE;

                // Pour chaque ressource disponible
                for (Ressource ressourceCandidate : ressourcesTemp) {
                    colon.setRessourceAttribuee(ressourceCandidate);

                    // Calculer les conflits potentiels
                    int conflits = 0;
                    for (Colon voisin : colon.getEnnemis()) {
                        if (voisin.getRessourceAttribuee() != null &&
                                colon.getlistepreferences().indexOf(voisin.getRessourceAttribuee()) < colon.getlistepreferences().indexOf(ressourceCandidate)) {
                            conflits++;
                        }
                    }

                    if (conflits < minConflits) {
                        minConflits = conflits;
                        meilleureRessource = ressourceCandidate;
                    }
                }

                // Affecter la meilleure ressource trouvée
                if (meilleureRessource != null) {
                    colon.setRessourceAttribuee(meilleureRessource);
                    ressourcesTemp.remove(meilleureRessource);
                }
            }

            // 4. Phase 2 : Optimisation locale par échanges
            boolean amelioration;
            do {
                amelioration = false;
                int coutActuel = colonies.nombreColonsJaloux();

                // Essayer tous les échanges possibles entre paires de colons
                for (int i = 0; i < colonsTries.size(); i++) {
                    for (int j = i + 1; j < colonsTries.size(); j++) {
                        Colon colon1 = colonsTries.get(i);
                        Colon colon2 = colonsTries.get(j);

                        // Tester l'échange
                        colonies.echangerRessources(colon1, colon2);
                        int nouveauCout = colonies.nombreColonsJaloux();

                        if (nouveauCout < coutActuel) {
                            coutActuel = nouveauCout;
                            amelioration = true;
                        } else {
                            // Annuler l'échange s'il n'y a pas d'amélioration
                            colonies.echangerRessources(colon1, colon2);
                        }
                    }
                }
            } while (amelioration);

            // Mettre à jour la meilleure solution si nécessaire
            int coutFinal = colonies.nombreColonsJaloux();
            if (coutFinal < meilleurCout) {
                meilleurCout = coutFinal;
                meilleureAffectation.clear();
                for (Colon c : colonsTries) {
                    meilleureAffectation.put(c, c.getRessourceAttribuee());
                }
            }
        }

        // 5. Restaurer la meilleure affectation trouvée
        for (Map.Entry<Colon, Ressource> entry : meilleureAffectation.entrySet()) {
            entry.getKey().setRessourceAttribuee(entry.getValue());
        }

        return meilleurCout;


    }






}