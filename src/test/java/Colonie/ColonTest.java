package Colonie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class ColonTest {

    private Colon colon;
    private Ressource ressource1;
    private Ressource ressource2;
    private Ressource ressource3;

    @BeforeEach
    void setUp() {
        colon = new Colon("TestColon");
        ressource1 = new Ressource("Eau");
        ressource2 = new Ressource("Nourriture");
        ressource3 = new Ressource("Bois");

        colon.ajoutpreference(ressource1);
        colon.ajoutpreference(ressource2);
        colon.ajoutpreference(ressource3);
    }

    @Test
    void testGetNom() {
        assertEquals("TestColon", colon.getNom());
    }

    @Test
    void testSetJaloux() {
        colon.setJaloux();
        // Aucun getter pour jaloux dans la classe actuelle, mais on peut tester indirectement avec le comportement attendu si applicable.
    }

    @Test
    void testAjoutEnnemi() {
        Colon ennemi = new Colon("Ennemi");
        colon.ajoutennemi(ennemi);

        ArrayList<Colon> ennemis = colon.getEnnemis();
        assertTrue(ennemis.contains(ennemi));
        assertEquals(1, ennemis.size());
    }

    @Test
    void testAjoutPreference() {
        ArrayList<Ressource> preferences = colon.getlistepreferences();
        assertEquals(3, preferences.size());
        assertTrue(preferences.contains(ressource1));
        assertTrue(preferences.contains(ressource2));
        assertTrue(preferences.contains(ressource3));
    }

    @Test
    void testSetRessourceAttribuee() {
        colon.setRessourceAttribuee(ressource1);
        assertEquals(ressource1, colon.getRessourceAttribuee());
    }

    @Test
    void testPrefereObjet() {
        colon.setRessourceAttribuee(ressource3);

        assertTrue(colon.prefereObjet(ressource1));
        assertTrue(colon.prefereObjet(ressource2));
        assertFalse(colon.prefereObjet(ressource3));
    }

    @Test
    void testAfficherListePref() {
        colon.AfficherListePref();
        // Aucune assertion ici, mais vous pouvez vérifier manuellement la sortie console si nécessaire.
    }

    @Test
    void testToString() {
        assertEquals("TestColon", colon.toString());
    }
}