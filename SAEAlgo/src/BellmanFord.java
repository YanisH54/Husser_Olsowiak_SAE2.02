import java.util.ArrayList;
import java.util.List;

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
        List<String> lignes = new ArrayList<>();


        // Initialisation de tous les noeuds à +infini
        for (String n : g.listeNoeuds()) {
            v.setValeur(n, Double.MAX_VALUE);
            lignes.add(null);
        }
        v.setValeur(depart, 0); // le noeud de depart a une valeur nulle

        boolean changement = true;

        // Algorithme du point fixe
        while (changement) {
            changement = false;

            // Pour chaque noeud on prend la valeur du noeud
            for (int i = 0; i < g.listeNoeuds().size(); i++) {
                String x = g.listeNoeuds().get(i);
                double valeurX = v.getValeur(x);
                String ligneX = lignes.get(i); // ligne utilisée pour arriver à x

                // Pour chaque arc partant de ce noeud
                for (Arc arc : g.suivants(x)) {
                    // On prend son successeur
                    String y = arc.getDestination();
                    double cout = arc.getCout();
                    String ligneArc = arc.getLigne();
                    // Ajout du malus si la ligne change
                    if (ligneX != null && !ligneX.equals(ligneArc)) {
                        cout += 10;
                    }
                    double nouvelleValeur = valeurX + cout;
                    // Si le nouveau chemin est plus court, il devient le nouveau chemin.
                    if (nouvelleValeur < v.getValeur(y)) {
                        v.setValeur(y, nouvelleValeur);
                        v.setParent(y, x);
                        changement = true;

                        // Mise à jour de la ligne pour y
                        int indiceY = g.listeNoeuds().indexOf(y);
                        lignes.set(indiceY, ligneArc);
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
        List<String> lignes = new ArrayList<>();
        List<String> noeuds = g.listeNoeuds();

        // Initialisation de tous les noeuds à +infini
        for (int i = 0; i < noeuds.size(); i++) {
            v.setValeur(noeuds.get(i), Double.MAX_VALUE);
            lignes.add(null); // aucune ligne connue au départ
        }
        v.setValeur(depart, 0); // le noeud de depart a une valeur nulle

        boolean changement = true;

        // Algorithme du point fixe
        while (changement) {
            changement = false;

            // Pour chaque noeud on prend la valeur du noeud
            for (int i = 0; i < noeuds.size(); i++) {
                String x = noeuds.get(i);
                double valeurX = v.getValeur(x);
                String ligneX = lignes.get(i); // ligne utilisée pour arriver à x

                // Pour chaque arc partant de ce noeud
                for (Arc arc : g.suivants(x)) {
                    // On prend son successeur
                    String y = arc.getDestination();
                    double cout = arc.getCout();
                    String ligneArc = arc.getLigne();

                    // Ajout du malus si la ligne change
                    if (ligneX != null && !ligneX.equals(ligneArc)) {
                        cout += 10;
                    }

                    double nouvelleValeur = valeurX + cout;

                    // Si le nouveau chemin est plus court, il devient le nouveau chemin.
                    if (nouvelleValeur < v.getValeur(y)) {
                        v.setValeur(y, nouvelleValeur);
                        v.setParent(y, x);
                        changement = true;

                        // Mise à jour de la ligne pour y
                        int indiceY = noeuds.indexOf(y);
                        lignes.set(indiceY, ligneArc);
                    }
                }
            }
        }

        return v;
    }

}
