import java.util.ArrayList;
import java.util.List;

public class GrapheListe implements Graphe {
    private ArrayList<String> noeuds;
    private ArrayList<Arcs> adjacence;

    public GrapheListe() {
        noeuds = new ArrayList<>();
        adjacence = new ArrayList<>();
    }

    private int getIndice(String n) {
        return noeuds.indexOf(n);
    }

    public void ajouterArc(String depart, String destination, int cout) {
        if (!noeuds.contains(depart)) {
            noeuds.add(depart);
            adjacence.add(new Arcs());
        }
        if (!noeuds.contains(destination)) {
            noeuds.add(destination);
            adjacence.add(new Arcs());
        }

        int i = getIndice(depart);
        adjacence.get(i).ajouterArc(new Arc(destination, cout));
    }

    @Override
    public List<String> listeNoeuds() {
        return noeuds;
    }

    @Override
    public List<Arc> suivants(String n) {
        int i = getIndice(n);
        if (i >= 0) {
            return adjacence.get(i).getArcs();
        }
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < noeuds.size(); i++) {
            res.append(noeuds.get(i)).append(" -> ");
            for (Arc a : adjacence.get(i).getArcs()) {
                res.append(a.toString()).append(" ");
            }
            res.append("\n");
        }
        return res.toString();
    }
}
