/**
 * Classe appliquant l'algorithme du point fixe (Bellman-Ford) sur un graphe g.
 */
public class BellmanFord {

    // Algorithme utilisé
    /*
    fonction pointFixe(Graphe g, Noeud depart):
    debut
        liste <- g.listeNoeuds()
        taille <- liste.size()

        pour i de 0 à taille - 1 faire
            X <- liste[i]
            L(X) <- +infini
        fpour

        L(depart) <- 0

        changement <- vrai
        tant que changement = vrai faire
            changement <- faux
            pour i de 0 à taille - 1 faire
                X <- liste[i]
                arcs <- g.suivants(X)
                pour j de 0 à arcs.size() - 1 faire
                    arc <- arcs[j]
                    Y <- arc.getDestination()
                    cout <- arc.getCout()
                    si L(X) + cout < L(Y) alors
                        L(Y) <- L(X) + cout
                        parent(Y) <- X
                        changement <- vrai
                    fsi
                fpour
            fpour
        ftant
    fin
    */

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

    /**
     * Applique l'algorithme du point fixe (Bellman-Ford) sur le graphe g
     * en partant du noeud 'depart' et applique désormais un malus au changement de ligne.
     *
     * @param g le graphe sur lequel on travaille
     * @param depart le nom du noeud de depart
     * @return un objet Valeurs contenant les distances minimales et les parents
     */
    public Valeurs resoudre2(Graphe g, String depart) {
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
