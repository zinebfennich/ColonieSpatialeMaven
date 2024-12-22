package Colonie;

import ExceptionColonie.ExceptionColon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColonieTest {

        private Colonie colonie;
        private Colon colon1;
        private Colon colon2;
        private Colon colon3;
        private Ressource ressource1;
        private Ressource ressource2;
        private Ressource ressource3;

        @BeforeEach
        void setUp() throws ExceptionColon {
            colonie = new Colonie(3);

            colon1 = new Colon("Colon1");
            colon2 = new Colon("Colon2");
            colon3 = new Colon("Colon3");

            ressource1 = new Ressource("Eau");
            ressource2 = new Ressource("Nourriture");
            ressource3 = new Ressource("Bois");

            colon1.ajoutpreference(ressource1);
            colon1.ajoutpreference(ressource2);

            colon2.ajoutpreference(ressource2);
            colon2.ajoutpreference(ressource3);

            colon3.ajoutpreference(ressource1);
            colon3.ajoutpreference(ressource3);

            colonie.ajoutColon(colon1);
            colonie.ajoutColon(colon2);
            colonie.ajoutColon(colon3);

            List<String> nomsRessources = Arrays.asList("Eau", "Nourriture", "Bois");
            colonie.initialiserRessources(nomsRessources);
        }

        @Test
        void testInitialisation() {
            assertEquals(3, colonie.getn());
            assertEquals(3, colonie.getListeColons().size());
            assertEquals(3, colonie.getListeRessources().size());
        }

        @Test
        void testAjoutColon() throws ExceptionColon {
            Colon colon4 = new Colon("Colon4");
            Exception exception = assertThrows(ExceptionColon.class, () -> colonie.ajoutColon(colon4));
            assertEquals("Le nombre de colons ne peut pas depasser le nombre donne.", exception.getMessage());
        }

        @Test
        void testAffectationRessources() {
            colonie.echangerRessources(colon1, colon2);
            Assertions.assertEquals(colon2.getRessourceAttribuee(), colon1.getRessourceAttribuee());
            Assertions.assertEquals(colon1.getRessourceAttribuee(), colon2.getRessourceAttribuee());
        }

        @Test
        void testNombreColonsJaloux() {
            colon1.setRessourceAttribuee(ressource1);
            colon2.setRessourceAttribuee(ressource2);
            colon3.setRessourceAttribuee(ressource3);

            int jaloux = colonie.nombreColonsJaloux();
            assertEquals(0, jaloux); // Aucun jaloux pour l'instant

            colon2.setRessourceAttribuee(ressource1); // Ressource convoitée
            jaloux = colonie.nombreColonsJaloux();
            assertTrue(jaloux > 0);
        }

        @Test
        void testToutesRessourcesAttribuees() {
            colon1.setRessourceAttribuee(ressource1);
            colon2.setRessourceAttribuee(ressource2);
            colon3.setRessourceAttribuee(ressource3);

            assertTrue(colonie.toutesRessourcesAttribuees());

            colon3.setRessourceAttribuee(null);
            assertFalse(colonie.toutesRessourcesAttribuees());
        }

        @Test
        void testTrierColonsParNom() {
            colonie.trierColonsParNom();
            List<Colon> colonsTries = colonie.getListeColons();

            Assertions.assertEquals("Colon1", colonsTries.get(0).getNom());
            Assertions.assertEquals("Colon2", colonsTries.get(1).getNom());
            Assertions.assertEquals("Colon3", colonsTries.get(2).getNom());
        }

        @Test
        void testGetColon() {
            Colon result = colonie.getColon("Colon1");
            Assertions.assertEquals(colon1, result);

            result = colonie.getColon("ColonInexistant");
            assertNull(result);
        }

        @Test
        void testGetRessourceParNom() {
            Ressource result = colonie.getRessourceParNom("Eau");
            Assertions.assertEquals(ressource1, result);

            result = colonie.getRessourceParNom("Inexistante");
            assertNull(result);
        }

        @Test
        void testAffichageAffection() {
            colon1.setRessourceAttribuee(ressource1);
            colon2.setRessourceAttribuee(ressource2);
            colon3.setRessourceAttribuee(ressource3);

            colonie.affichageaffection(); // Vérifiez la sortie manuellement dans la console
        }
    }
