import java.util.ArrayList;
import java.util.List;

/**
 * Représente un graphe orienté où chaque noeud est identifié par une chaîne de caractères.
 * Les arcs sortants de chaque noeud sont stockés dans des listes d'adjacence.
 */
public class GrapheListe implements Graphe {
    private List<String> noeuds;
    private List<Arcs> adjacence;

    /**
     * Crée un graphe vide sans noeuds ni arcs.
     */
    public GrapheListe() {
        this.noeuds = new ArrayList<>();
        this.adjacence = new ArrayList<>();
    }

    /**
     * Renvoie l'indice d'un noeud dans la liste des noeuds.
     * @param n le nom du noeud à rechercher
     * @return son indice dans la liste, ou -1 s'il n'est pas présent
     */
    public int getIndice(String n) {
        return this.noeuds.indexOf(n);
    }

    /**
     * Ajoute un arc partant d'un noeud vers un autre avec un coût donné.
     * Si les noeuds n'existent pas encore, ils sont ajoutés automatiquement.
     * @param depart le nom du noeud de départ
     * @param destination le nom du noeud d'arrivée
     * @param cout le coût de l'arc
     */
    public void ajouterArc(String depart, String destination, double cout) {
        if (!this.noeuds.contains(depart)) {
            this.noeuds.add(depart);
            this.adjacence.add(new Arcs());
        }
        if (!this.noeuds.contains(destination)) {
            this.noeuds.add(destination);
            this.adjacence.add(new Arcs());
        }

        int i = getIndice(depart);
        this.adjacence.get(i).ajouterArc(new Arc(destination, cout));
    }

    /**
     * Renvoie la liste de tous les noeuds présents dans le graphe.
     * @return une liste de noms de noeuds
     */
    public List<String> listeNoeuds() {
        return this.noeuds;
    }

    /**
     * Donne la liste des arcs sortants à partir d’un noeud donné.
     * @param n le nom du noeud dont on veut les arcs
     * @return la liste des arcs sortants depuis ce noeud
     */
    public List<Arc> suivants(String n) {
        int i = getIndice(n);
        if (i >= 0) {
            return this.adjacence.get(i).getArcs();
        }
        return new ArrayList<>();
    }

    /**
     * Affiche le graphe.
     * Chaque ligne indique les arcs partant d’un noeud.
     * @return une chaîne de caractères représentant le graphe
     */
    public String toString() {
        StringBuilder chaine = new StringBuilder();
        for (int i = 0; i < this.noeuds.size(); i++) {
            chaine.append(this.noeuds.get(i)).append(" -> ");
            chaine.append(this.adjacence.get(i).toString()).append("\n");
        }
        return chaine.toString();
    }
}
