import java.io.*;

/**
 * Classe pour lire un fichier réseau et construire un graphe.
 */
public class LireReseau {
    /**
     * Lit un fichier de plan réseau et crée un graphe orienté pondéré.
     * Le fichier doit contenir une section "%% Connexions:" suivie de lignes au format :
     * depart:arrivee:temps:ligne
     *
     * @param nomFichier chemin du fichier à lire
     * @return le graphe construit
     * @throws IOException si le fichier est introuvable ou invalide
     */
    public static GrapheListe lire(String nomFichier) throws IOException {
        GrapheListe graphe = new GrapheListe();
        BufferedReader br = new BufferedReader(new FileReader(nomFichier));

        boolean lectureConnexions = false;
        String ligne;

        while ((ligne = br.readLine()) != null) {
            if (!lectureConnexions) {
                if (ligne.equals("%% Connexions:")) {
                    lectureConnexions = true;
                }
            }
            else {
                String[] parties = ligne.split(":");
                if (parties.length == 4) {
                    String depart = parties[0];
                    String arrivee = parties[1];
                    double temps = Double.parseDouble(parties[2]);
                    String ligneMetro = parties[3];

                    graphe.ajouterArc(depart, arrivee, temps, ligneMetro);
                    graphe.ajouterArc(arrivee, depart, temps, ligneMetro);
                }
            }
        }

        br.close();
        return graphe;
    }
}