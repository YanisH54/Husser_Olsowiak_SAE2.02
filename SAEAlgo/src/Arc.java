/**
 * Classe qui représente un arc orienté dans un graphe.
 * Un arc relie un noeud de départ à un noeud de destination avec un certain coût.
 */
public class Arc {
    private String dest;
    private double cout;

    /**
     * Crée un nouvel arc avec une destination et un coût.
     */
    public Arc(String dest, double cout){
        this.dest = dest;
        this.cout = cout;
    }

    /**
     * Donne le nom du noeud de destination.
     * @return le nom du noeud d’arrivée
     */
    public String getDestination() {
        return this.dest;
    }

    /**
     * Donne le coût de l’arc.
     * @return le coût associé à l’arc
     */
    public double getCout() {
        return cout;
    }

    /**
     * Affiche l’arc sous la forme "destination(coût)".
     * @return une chaîne représentant l’arc
     */
    public String toString(){
        return this.dest + "(" + this.cout + ")";
    }
}
