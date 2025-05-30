/**
 * Classe principale pour tester l'algorithme de Dijkstra.
 */
public class MainDijkstra {

    /**
     * Méthode principale.
     * @param args
     */
    public static void main(String[] args) {
        GrapheListe g = new GrapheListe();

        g.ajouterArc("A", "B", 12);
        g.ajouterArc("A", "D", 87);
        g.ajouterArc("B", "E", 11);
        g.ajouterArc("C", "A", 19);
        g.ajouterArc("D", "B", 23);
        g.ajouterArc("D", "C", 10);
        g.ajouterArc("E", "D", 43);

        Dijkstra algo = new Dijkstra();
        Valeurs resultats = algo.resoudre(g, "A");

        System.out.println(resultats);
        System.out.println(resultats.calculerChemin("D"));
    }
}
