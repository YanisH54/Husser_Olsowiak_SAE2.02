import java.util.ArrayList;
import java.util.List;

public class GrapheListe implements Graphe {
    private ArrayList<String> noeuds;
    private ArrayList<Arcs> adjacence;

    public GrapheListe() {
        this.noeuds = new ArrayList<>();
        this.adjacence = new ArrayList<>();
    }

    private int getIndice(String n) {
        return this.noeuds.indexOf(n);
    }

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

    public List<String> listeNoeuds() {
        return this.noeuds;
    }

    public List<Arc> suivants(String n) {
        int i = getIndice(n);
        if (i >= 0) {
            return this.adjacence.get(i).getArcs();
        }
        return new ArrayList<>();
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.noeuds.size(); i++) {
            res.append(this.noeuds.get(i)).append(" -> ");
            for (Arc a : this.adjacence.get(i).getArcs()) {
                res.append(a.toString()).append(" ");
            }
            res.append("\n");
        }
        return res.toString();
    }
}
