package org.example.Colonie;

import org.example.ExceptionColonie.ExceptionColon;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Colonie {
    public int n;
    private List<Colon> colons;
    private Map<Ressource, Colon> ressources;

    public Colonie(int n) throws ExceptionColon {
        if (n > 26) {
            throw new ExceptionColon("Le nombre de colons ne peut pas depasser 26.");
        }
        this.n = n;
        this.colons = new ArrayList<>();
        this.ressources = new HashMap<>();
    }

    public void initialiserColons(List<String> nomsColons) {
        for (String nom : nomsColons) {
             colons.add(new Colon(nom)); }
    }
    public void initialiserRessources(List<String> nomsRessources) {
        for (String nom : nomsRessources) {
            Ressource r = new Ressource(nom);
            ressources.put(r, null); }
    }

    public int getn() {
        return n;
    }

    public List<Colon> getListeColons (){
        return colons;
    }

    public List<Ressource> getListeRessources(){
        List<Ressource> keysList = new ArrayList<>();
        for (Ressource key : ressources.keySet()) {
            keysList.add(key);
        }
        return keysList;
    }

    public void setRessources(Map<Ressource, Colon> ressources) {
        this.ressources = ressources;
    }

    public Colon getColon(String nom) {
        for (Colon x : colons) {
            if (x.getNom().equals(nom)) {
                return x;
            }
        }
        return null;
    }


    public void ajoutColon(Colon c) throws ExceptionColon {
        if (colons.size() > n) {
            throw new ExceptionColon("Le nombre de colons ne peut pas depasser le nombre donne.");
        }
        colons.add(c);
    }

    public List<Colon> getlistecolons() {
        return colons;
    }

    public Map<Ressource, Colon> getRessources() {
        return ressources;
    }



    //Cette affectation optimisee retourne la meme chose que laffectation naive
    /*public void affectationOptimisee() { 
        // Étape 1 : Trier les colons par ordre décroissant du nombre d'ennemis
        List<Colon> colonsTries = new ArrayList<>(colons);
        colonsTries.sort((a, b) -> b.getEnnemis().size() - a.getEnnemis().size());

        // Étape 2 : Affecter les ressources
        for (Colon colon : colonsTries) {
            Ressource meilleureRessource = null;
            int minPopularite = Integer.MAX_VALUE;

            for (Ressource ressource : colon.getlistepreferences()) {
                // Vérifier si la ressource est disponible
                if (ressources.get(ressource) == null) { // La ressource n'est pas encore attribuée
                    // Calculer combien d'ennemis convoitent cette ressource
                    int popularite = 0;
                    for (Colon ennemi : colon.getEnnemis()) {
                        if (ennemi.getlistepreferences().contains(ressource)) {
                            popularite++;
                        }
                    }

                    // Sélectionner la ressource la moins convoitée
                    if (popularite < minPopularite) {
                        meilleureRessource = ressource;
                        minPopularite = popularite;
                    }
                }
            }

            // Affecter la meilleure ressource trouvée au colon
            if (meilleureRessource != null) {
                colon.setRessourceAttribuee(meilleureRessource);
                ressources.put(meilleureRessource, colon);
            }
        }
    }*/
    

    public int nombreColonsJaloux() {
        int nombreJaloux = 0;

        for (Colon colon : this.colons) {
            for (Colon ennemi : colon.getEnnemis()) {
                Ressource ressourceEnnemi = ennemi.getRessourceAttribuee();

                if (colon.prefereObjet(ressourceEnnemi)) {
                    colon.setJaloux();
                    nombreJaloux++;

                }
            }
        }

        return nombreJaloux;
    }

    public Ressource getRessourceParNom(String nom) {
        for (Ressource r : ressources.keySet()) {
            if (r.getNom().equals(nom)) {
                return r;
            }
        }
        return null;
    }

    public void affichageaffection() {
        trierColonsParNom(); // Trier les colons par nom

        for (Colon colon : colons) {
            Ressource ressource = colon.getRessourceAttribuee(); // Obtenir la ressource attribuée au colon

            if (ressource != null) { // Vérifie si la ressource est attribuée
                System.out.println(colon.getNom() + " : " + ressource.getNom());
            } else {
                System.out.println(colon.getNom() + " : aucune ressource attribuee");
            }
        }
    }


    public void echangerRessources(Colon colon1, Colon colon2) {

        Ressource ressource1 = colon1.getRessourceAttribuee();
        Ressource ressource2 = colon2.getRessourceAttribuee();

        if (ressource1 != null && ressource2 != null) {

            colon1.setRessourceAttribuee(ressource2);

            colon2.setRessourceAttribuee(ressource1);

            // Met à jour le dictionnaire de ressources

            ressources.put(ressource1, colon2);

            ressources.put(ressource2, colon1);

        } else {

            System.out.println("L'un des colons n'a pas de ressource attribuee, impossible d'echanger.");

        }

    }

    public void trierColonsParNom() {
        colons.sort(Comparator.comparing(colon -> colon.getNom(), (s1, s2) -> {
            // Comparateur pour trier naturellement les noms
            Pattern pattern = Pattern.compile("\\d+");
            Matcher m1 = pattern.matcher(s1);
            Matcher m2 = pattern.matcher(s2);

            int pos1 = 0, pos2 = 0;

            while (m1.find(pos1) && m2.find(pos2)) {
                // Comparaison des parties non numériques
                int compareText = s1.substring(pos1, m1.start()).compareTo(s2.substring(pos2, m2.start()));
                if (compareText != 0) return compareText;

                // Comparaison des parties numériques
                int num1 = Integer.parseInt(m1.group());
                int num2 = Integer.parseInt(m2.group());
                if (num1 != num2) return Integer.compare(num1, num2);

                // Avance aux positions suivantes
                pos1 = m1.end();
                pos2 = m2.end();
            }
            // Comparaison des parties restantes
            return s1.substring(pos1).compareTo(s2.substring(pos2));
        }));
    }


    public boolean toutesRessourcesAttribuees() {
        for (Colon colon : colons) {
            if (colon.getRessourceAttribuee() == null) {
                return false;
            }
        }
        return true;
    }

}
