import java.util.List;
/**
 * Classe appliquant l'algorithme du point fixe (Bellman-Ford) sur un graphe g.
 */
public class BellmanFord {

    /**
     * Applique l'algorithme du point fixe (Bellman-Ford) sur le graphe g
     * en partant du noeud 'depart'.
     *
     * @param g le graphe sur lequel on travaille
     * @param depart le nom du noeud de depart
     * @return un objet Valeurs contenant les distances minimales et les parents
     */
    public Valeurs resoudre(Graphe g, String depart) {
        Valeurs v = new Valeurs();

        // Initialisation de tous les noeuds à +infini
        for (String n : g.listeNoeuds()) {
            v.setValeur(n, Double.MAX_VALUE);
        }
        v.setValeur(depart, 0); // le noeud de depart a une valeur nulle

        boolean changement = true;

        // Algorithme du point fixe
        while (changement) {
            changement = false;
            // Pour chaque noeud on prend la valeur du noeud
            for (String x : g.listeNoeuds()) {
                double valeurX = v.getValeur(x);
                // Pour chaque arc partant de ce noeud
                for (Arc arc : g.suivants(x)) {
                    // On prend son successeur
                    String y = arc.getDestination();
                    double cout = arc.getCout();
                    double nouvelleValeur = valeurX + cout;
                    // Si le nouveau chemin est plus court, il devient le nouveau chemin.
                    if (nouvelleValeur < v.getValeur(y)) {
                        v.setValeur(y, nouvelleValeur);
                        v.setParent(y, x);
                        changement = true;
                    }
                }
            }
        }

        return v;
    }
}
