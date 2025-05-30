import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
     * Crée un graphe à partir d'un fichier texte.
     * @param nomFichier le nom du fichier utilisé
     */
    public GrapheListe(String nomFichier) throws IOException {
        this.noeuds = new ArrayList<String>();
        this.adjacence = new ArrayList<Arcs>();

        BufferedReader br = new BufferedReader(new FileReader(nomFichier));
        String ligne;

        while ((ligne = br.readLine()) != null) {
            String[] tab = ligne.split("\t");
            if (tab.length == 3) {
                String depart = tab[0];
                String destination = tab[1];
                double cout = Double.parseDouble(tab[2]);
                ajouterArc(depart, destination, cout);
            }
        }
        br.close();
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
        // On modifie cet ajout pour l'adapter à l'ajout des lignes
        this.adjacence.get(i).ajouterArc(new Arc(destination, cout, "1"));
    }

    /**
     * Ajoute un arc partant d'un noeud vers un autre avec un coût donné
     * ainsi qu'une ligne de métro donnée.
     * Si les noeuds n'existent pas encore, ils sont ajoutés automatiquement.
     * @param depart le nom du noeud de départ
     * @param destination le nom du noeud d'arrivée
     * @param cout le coût de l'arc
     * @param ligne la ligne de métro
     */
    public void ajouterArc(String depart, String destination, double cout, String ligne) {
        if (!this.noeuds.contains(depart)) {
            this.noeuds.add(depart);
            this.adjacence.add(new Arcs());
        }
        if (!this.noeuds.contains(destination)) {
            this.noeuds.add(destination);
            this.adjacence.add(new Arcs());
        }

        int i = getIndice(depart);
        // On modifie cet ajout pour l'adapter à l'ajout des lignes
        this.adjacence.get(i).ajouterArc(new Arc(destination, cout, ligne));
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
