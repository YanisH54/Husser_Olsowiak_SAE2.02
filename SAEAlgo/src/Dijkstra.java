import java.util.ArrayList;
import java.util.List;

/**
 * Classe appliquant l'algorithme de Dijkstra sur un graphe g.
 */
public class Dijkstra {

    /*
    Entrées :
    G un graphe orienté avec une pondération positive des arcs
    (coût ou poids)
    A un sommet (départ) de G

    Début
    Q <- {} // utilisation d'une liste de noeuds à traiter
    Pour chaque sommet v de G faire
        v.valeur <- Infini
        v.parent <- Indéfini
        Q <- Q U {v} // ajouter le sommet v à la liste Q
    Fin Pour

    A.valeur <- 0

    Tant que Q est un ensemble non vide faire
        u <- un sommet de Q telle que u.valeur est minimal
        // enlever le sommet u de la liste Q
        Q <- Q \ {u}

        Pour chaque sommet v de Q tel que l'arc (u,v) existe faire
            d <- u.valeur + poids(u,v)
            Si d < v.valeur
                // le chemin est plus interessant
                Alors v.valeur <- d
                      v.parent <- u
            Fin Si
        Fin Pour
    Fin Tant que
    Fin
    */

    /**
     * Applique l'algorithme de Dijkstra sur le graphe g
     * en partant du noeud 'depart'.
     *
     * @param g le graphe sur lequel on travaille
     * @param depart le nom du noeud de depart
     * @return un objet Valeurs contenant les distances minimales et les parents
     */
    public Valeurs resoudre(Graphe g, String depart) {
        Valeurs v = new Valeurs();
        List<String> Q = new ArrayList<>();

        for (String noeud : g.listeNoeuds()) {
            v.setValeur(noeud, Double.MAX_VALUE);
            v.setParent(noeud, null);
            Q.add(noeud);
        }

        v.setValeur(depart, 0);

        while (!Q.isEmpty()) {
            String u = Q.get(0);
            for (String s : Q) {
                if (v.getValeur(s) < v.getValeur(u)) {
                    u = s;
                }
            }

            Q.remove(u);

            for (Arc arc : g.suivants(u)) {
                String voisin = arc.getDestination();
                // On vérifie que le voisin n'a pas déjà été retiré de Q
                if (Q.contains(voisin)) {
                    double d = v.getValeur(u) + arc.getCout();
                    if (d < v.getValeur(voisin)) {
                        v.setValeur(voisin, d);
                        v.setParent(voisin, u);
                    }
                }
            }
        }
        return v;
    }
}
