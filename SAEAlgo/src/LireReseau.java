import java.io.*;

public class LireReseau {

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