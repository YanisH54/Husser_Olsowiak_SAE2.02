import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui représente une liste d'arcs.
 */
public class Arcs {
    private List<Arc> arcs;

    /**
     * Crée un objet Arcs avec une liste vide.
     */
    public Arcs() {
        this.arcs = new ArrayList<>();
    }

    /**
     * Ajoute un arc à la liste.
     * @param arc l'arc à ajouter
     */
    public void ajouterArc(Arc arc) {
        this.arcs.add(arc);
    }

    /**
     * Retourne la liste des arcs.
     * @return la liste des arcs ajoutés
     */
    public List<Arc> getArcs() {
        return this.arcs;
    }

    /**
     * Affiche tous les arcs de la liste.
     * Chaque arc est séparé par un espace.
     * @return une chaîne avec tous les arcs de la liste
     */
    public String toString() {
        StringBuilder chaine = new StringBuilder();
        for (Arc a : arcs) {
            chaine.append(a.toString()).append(" ");
        }
        return chaine.toString();
    }
}
