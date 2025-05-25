import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour vérifier l’algorithme de Dijkstra.
 */
public class DijkstraTest {

    /**
     * Vérifie que les distances minimales et les parents sont corrects
     * pour le graphe de l’énoncé avec départ en A.
     */
    @Test
    public void testResoudreDepuisA() {
        // Préparation des données
        GrapheListe g = new GrapheListe();
        g.ajouterArc("A", "B", 12);
        g.ajouterArc("A", "D", 87);
        g.ajouterArc("B", "E", 11);
        g.ajouterArc("C", "A", 19);
        g.ajouterArc("D", "B", 23);
        g.ajouterArc("D", "C", 10);
        g.ajouterArc("E", "D", 43);
        Dijkstra algo = new Dijkstra();

        // Méthode testée
        Valeurs resultats = algo.resoudre(g, "A");

        // Vérification des valeurs calculées
        assertEquals(0, resultats.getValeur("A"), "La valeur de A doit être 0");
        assertEquals(12, resultats.getValeur("B"), "La valeur de B doit être 12");
        assertEquals(23, resultats.getValeur("E"), "La valeur de E doit être 23");
        assertEquals(66, resultats.getValeur("D"), "La valeur de D doit être 66");
        assertEquals(76, resultats.getValeur("C"), "La valeur de C doit être 76");

        // Vérification des parents
        assertNull(resultats.getParent("A"), "Le parent de A doit être null");
        assertEquals("A", resultats.getParent("B"), "Le parent de B doit être A");
        assertEquals("B", resultats.getParent("E"), "Le parent de E doit être B");
        assertEquals("E", resultats.getParent("D"), "Le parent de D doit être E");
        assertEquals("D", resultats.getParent("C"), "Le parent de C doit être D");
    }
}
