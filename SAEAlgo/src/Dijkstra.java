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

    /**
     * Applique l'algorithme de Dijkstra sur le graphe g
     * en partant du noeud 'depart' et applique désormais un malus au changement de ligne.
     *
     * @param g le graphe sur lequel on travaille
     * @param depart le nom du noeud de depart
     * @return un objet Valeurs contenant les distances minimales et les parents
     */
    public Valeurs resoudre2(Graphe g, String depart) {
        Valeurs v = new Valeurs();
        List<String> Q = new ArrayList<>();
        // On stock les lignes
        List<String> lignes = new ArrayList<>();

        for (String noeud : g.listeNoeuds()) {
            v.setValeur(noeud, Double.MAX_VALUE);
            v.setParent(noeud, null);
            Q.add(noeud);
            lignes.add(null); // On ne connait aucune ligne au départ
        }

        v.setValeur(depart, 0);

        while (!Q.isEmpty()) {
            // On cherche le noeud avec la plus petite valeur dans Q
            String u = Q.get(0);
            for (String s : Q) {
                if (v.getValeur(s) < v.getValeur(u)) {
                    u = s;
                }
            }

            // On prend l'indice de U
            int indiceU = Q.indexOf(u);

            // Stock la ligne pour venir à U
            String lignePrecedente = lignes.get(indiceU);

            // on retire u de Q et sa position dans lignes
            Q.remove(u);
            lignes.remove(indiceU);

            // Analyse des arcs sortants de U
            for (Arc arc : g.suivants(u)) {
                String voisin = arc.getDestination();

                // Si voisin encore dans Q
                if (Q.contains(voisin)) {
                    int indiceVoisin = Q.indexOf(voisin);
                    // On stocke la ligne qui va permettre d'aller vers voisin
                    String ligneActuelle = arc.getLigne();
                    double d = v.getValeur(u) + arc.getCout();
                    // On vérifie s'il y a changement de ligne ou pas
                    if (lignePrecedente != null && !lignePrecedente.equals(ligneActuelle)) {
                        d += 10; // S'il y a changement on ajoute le malus
                    }

                    if (d < v.getValeur(voisin)) {
                        v.setValeur(voisin, d);
                        v.setParent(voisin, u);
                        lignes.set(indiceVoisin, ligneActuelle);
                    }
                }
            }
        }
        return v;
    }
}
