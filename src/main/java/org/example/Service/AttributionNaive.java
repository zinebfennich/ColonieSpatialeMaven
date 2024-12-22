package org.example.Service;
import org.example.Colonie.*;


import java.util.*;

public class AttributionNaive {
    private List<Colon> colons;
    private Map<Ressource, Colon> ressources;
    private Colonie colonie;

    public AttributionNaive(List<Colon> colons, Map<Ressource, Colon> ressources, Colonie colonie) {
        this.colons = colons;
        this.ressources = ressources;
        this.colonie= colonie;
    }

    // solution naive
    public void affectationNaive() {
        for (Colon c : colons) {
            for (Ressource p : c.getlistepreferences()) {
                Ressource ressource = colonie.getRessourceParNom(p.getNom());
                if (ressource != null && ressources.get(ressource) == null) {
                    c.setRessourceAttribuee(ressource);
                    ressources.put(ressource, c);
                    break;
                }
            }
        }
    }
}
