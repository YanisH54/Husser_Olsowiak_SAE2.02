import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Classe de tests pour voir si GrapheListe fonctionne correctement.
 */
public class GrapheListeTest {

    /**
     * Vérifie que la méthode getIndice retourne bien le bon indice.
     */
    @Test
    public void testGetIndice() {
        // Préparation des données
        GrapheListe g = new GrapheListe();
        g.ajouterArc("A", "B", 12);
        g.ajouterArc("A", "C", 7);

        // Méthode testée
        int i = g.getIndice("C");
        // Vérification
        assertEquals(2,i, "L'indice du noeud C devrait être 3");
    }

    /**
     * Vérifie qu'un arc est bien ajouté, même si les noeuds n'existaient pas avant.
     */
    @Test
    public void testAjouterArcPointsAbsents() {
        //Préparation des données
        GrapheListe g = new GrapheListe();
        g.ajouterArc("A", "B", 5);
        List<String> noeuds = g.listeNoeuds();

        // Vérifications
        assertTrue(noeuds.contains("A"), "Le noeud A doit exister");
        assertTrue(noeuds.contains("B"), "Le noeud B doit exister");
        assertEquals(2, noeuds.size(), "La liste des noeuds doit contenir 2 éléments");
    }

    /**
     * Vérifie que listeNoeuds retourne tous les noeuds du graphe.
     */
    @Test
    public void testListeNoeuds() {
        // Préparation des données
        GrapheListe g = new GrapheListe();
        g.ajouterArc("A", "B", 1);
        g.ajouterArc("C", "D", 2);

        // Méthode testée
        List<String> noeuds = g.listeNoeuds();

        // Vérifications
        assertEquals(4, noeuds.size());
        assertTrue(noeuds.contains("A"), "Le noeud A doit être dans la liste");
        assertTrue(noeuds.contains("B"), "Le noeud B doit être dans la liste");
        assertTrue(noeuds.contains("C"), "Le noeud C doit être dans la liste");
        assertTrue(noeuds.contains("D"), "Le noeud D doit être dans la liste");
    }

    /**
     * Vérifie que la méthode suivants retourne bien les arcs sortants d’un noeud donné.
     */
    @Test
    public void testSuivants() {
        // Préparation des données
        GrapheListe g = new GrapheListe();
        g.ajouterArc("A", "B", 12);
        g.ajouterArc("A", "C", 7);

        // Méthode testée
        List<Arc> arcs = g.suivants("A");

        // Vérifications
        assertEquals(2, arcs.size(), "Le point A doit avoir 2 noeuds dans la liste des suivants");
        assertEquals("B", arcs.get(0).getDestination(), "Le premier point suivant doit être B");
        assertEquals(12, arcs.get(0).getCout(),"Le cout pour aller au point B doit être de 12");
        assertEquals("C", arcs.get(1).getDestination(), "Le second point suivant doit être C");
        assertEquals(7, arcs.get(1).getCout(), "Le cout pour aller au point C doit être de 12");
    }

    /**
     * Vérifie que toString retourne la bonne représentation du graphe.
     */
    @Test
    public void testToString() {
        // Préparation des données
        GrapheListe g = new GrapheListe();
        g.ajouterArc("A", "B", 12);
        g.ajouterArc("A", "D", 87);
        g.ajouterArc("B", "E", 11);
        g.ajouterArc("C", "A", 19);
        g.ajouterArc("D", "B", 23);
        g.ajouterArc("D", "C", 10);
        g.ajouterArc("E", "D", 43);

        String attendu =
                "A -> B(12.0) D(87.0) \n" +
                        "B -> E(11.0) \n" +
                        "D -> B(23.0) C(10.0) \n" +
                        "E -> D(43.0) \n" +
                        "C -> A(19.0) \n";

        // Vérification
        assertEquals(attendu, g.toString(), "Le résultat attendu n'est pas identique à celui reçu par la méthode toString");
    }
}