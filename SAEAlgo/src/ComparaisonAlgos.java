public class ComparaisonAlgos {
    public static void main(String[] args) {
        String[] fichiers = {
                "graphes/graphe_10.txt",
                "graphes/graphe_30.txt",
                "graphes/graphe_50.txt",
                "graphes/graphe_80.txt",
                "graphes/graphe_100.txt"
        };

        String sommetDepart = "A";
        Valeurs resultats;

        for (String fichier : fichiers) {
            System.out.println("Graphe : " + fichier);
            try {
                GrapheListe graphe = new GrapheListe(fichier);
                System.out.println("Analyse temporelle du graphe : " + fichier);
                // Analyse temporelle de l'algorithme de Dijkstra sur le fichier en cours
                Dijkstra dijkstra = new Dijkstra();
                long debutD = System.nanoTime();
                resultats = dijkstra.resoudre(graphe, sommetDepart);
                long finD = System.nanoTime();
                System.out.println("Temps Dijkstra : " + (finD - debutD) + " nanosecondes");

                // Analyse temporelle de l'algorithme de BellmanFord sur le fichier en cours
                BellmanFord bellman = new BellmanFord();
                long debutB = System.nanoTime();
                resultats = bellman.resoudre(graphe, sommetDepart);
                long finB = System.nanoTime();
                System.out.println("Temps BellmanFord : " + (finB - debutB) + " nanosecondes");

                System.out.println();

            }
            // En cas de soucis sert à repérer le fichier avec lequel on a eu un soucis
            catch (Exception e) {
                System.out.println("Erreur fichier : " + fichier);
                e.printStackTrace();
            }
        }
    }
}
