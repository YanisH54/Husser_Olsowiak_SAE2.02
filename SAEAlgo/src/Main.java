/**
 * Classe de principale
 */
public class Main {

    /**
     * Méthode principale.
     * @param args
     */
    public static void main(String[] args) {
        // Graphe fournis dans le sujet commun à tous les mains.
        GrapheListe g = new GrapheListe();

        g.ajouterArc("A", "B", 12);
        g.ajouterArc("A", "D", 87);
        g.ajouterArc("B", "E", 11);
        g.ajouterArc("C", "A", 19);
        g.ajouterArc("D", "B", 23);
        g.ajouterArc("D", "C", 10);
        g.ajouterArc("E", "D", 43);

        // Main réalisé pour la section 2
        // System.out.println(g);

        // Main réalisé pour la section 3
        BellmanFord algo = new BellmanFord();
        Valeurs resultats = algo.resoudre(g, "A");

        System.out.println(resultats);
        System.out.println(resultats.calculerChemin("D"));
    }
}
