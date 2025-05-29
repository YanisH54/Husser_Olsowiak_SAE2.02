import java.io.IOException;
import java.util.List;

/**
 * Classe servant à la comparaison des deux algorithmes de chemin le plus court.
 */
public class MainMetro {

    /**
     * Méthode de lancement du main
     * @param args
     */
    public static void main(String[] args) throws IOException {
        String cheminFichier = "graphes/plan-reseau.txt";
        GrapheListe graphe = LireReseau.lire(cheminFichier);

        // Liste des 5 trajets qui seront utilisés pour les tests
        String[][] trajets = {
                {"1", "15"},    // Grande Arche de la Défense vers Châtelet
                {"7", "263"},   // Charles de Gaulle, Étoile vers la Mairie d'Issy
                {"25", "136"},  // Château de Vincennes vers La Courneuve
                {"39", "87"},   // Stalingrad vers Odéon
                {"198", "150"}  // Pont de Sèvres vers Pont-Neuf
        };

        for (String[] trajet : trajets) {
            // Initialisation du du départ et de l'arrivée
            String depart = trajet[0];
            String arrivee = trajet[1];

            // Affichage du trajet analysé
            System.out.println("Trajet de la station " + depart + " à la station " + arrivee);

            // Analyse temporelle pas l'algorithme de Dijkstra
            System.out.println("Analyse temporelle du chemin par Dijkstra: ");
            Dijkstra dijkstra = new Dijkstra();
            // Lancement de la mesure
            long debutD = System.nanoTime();
            Valeurs vD = dijkstra.resoudre2(graphe, depart);
            List<String> cheminD = vD.calculerChemin(arrivee);
            System.out.println(cheminD);
            long finD = System.nanoTime(); // Fin de la mesure
            System.out.println("Temps Dijkstra : " + (finD - debutD) + " nanosecondes");

            // Analyse temporelle pas l'algorithme de Bellman-Ford
            System.out.println("Analyse temporelle du chemin par Bellman-Ford: ");
            BellmanFord bellman = new BellmanFord();
            // Lancement de la mesure
            long debutB = System.nanoTime();
            Valeurs vB = bellman.resoudre2(graphe, depart);
            List<String> cheminB = vB.calculerChemin(arrivee);
            System.out.println(cheminB);
            long finB = System.nanoTime(); // Fin de la mesure
            System.out.println("Temps Bellman-Ford : " + (finB - debutB) + " nanosecondes");


            System.out.println();
        }
    }
}
