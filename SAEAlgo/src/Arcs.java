import java.util.ArrayList;

public class Arcs {
    private ArrayList<Arc> arcs;

    public Arcs() {
        this.arcs = new ArrayList<>();
    }

    public void ajouterArc(Arc arc) {
        this.arcs.add(arc);
    }

    public ArrayList<Arc> getArcs() {
        return this.arcs;
    }
}
