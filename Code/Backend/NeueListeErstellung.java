package Code.Backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NeueListeErstellung {

    private List<Schueler> schuelerListe;

    public NeueListeErstellung(List<Schueler> schuelerListe) {
        this.schuelerListe = schuelerListe;
    }

    public void erstelleNeueListe() {
        // Liste nach Wahl1 sortieren
        schuelerListe.sort(Comparator.comparingInt(Schueler::getWahl1));
        

        // Map für die Häufigkeit der Zahlen in Wahl1 bis Wahl6 erstellen
        Map<Integer, Integer> wahlHaeufigkeitMap = new HashMap<>();
        for (Schueler schueler : schuelerListe) {
            erhoeheHaeufigkeit(wahlHaeufigkeitMap, schueler.getWahl1());
            erhoeheHaeufigkeit(wahlHaeufigkeitMap, schueler.getWahl2());
            erhoeheHaeufigkeit(wahlHaeufigkeitMap, schueler.getWahl3());
            erhoeheHaeufigkeit(wahlHaeufigkeitMap, schueler.getWahl4());
            erhoeheHaeufigkeit(wahlHaeufigkeitMap, schueler.getWahl5());
            erhoeheHaeufigkeit(wahlHaeufigkeitMap, schueler.getWahl6());
        }

        // Neue Liste erstellen
        List<FeldNummerUndSchueler> neueListe = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : wahlHaeufigkeitMap.entrySet()) {
            int feldNummer = entry.getKey();
            int anzahl = entry.getValue();

            // Schüler, die diese Nummer als Wahl1 haben
            List<Schueler> schuelerMitNummer = new ArrayList<>();
            for (Schueler schueler : schuelerListe) {
                if (schueler.getWahl1() == feldNummer) {
                    schuelerMitNummer.add(schueler);
                }
            }

            neueListe.add(new FeldNummerUndSchueler(feldNummer, anzahl, schuelerMitNummer));
        }

        // Ausgabe der neuen Liste
        System.out.println("Neue Liste:");
        for (FeldNummerUndSchueler eintrag : neueListe) {
            System.out.println(eintrag);
        }
    }
    // Summation der Anzahl
    private static void erhoeheHaeufigkeit(Map<Integer, Integer> map, int zahl) {
        map.merge(zahl, 1, Integer::sum);
        
    }

 
    public static void main(String[] args) {
        // Beispiel-Daten aus der SchuelerVerarbeitung Klasse
        List<Schueler> beispielListe = new ArrayList<>();
        beispielListe.add(new Schueler("Max Mustermann", "Klasse A", 1, 3, 2, 4, 5, 6));
        beispielListe.add(new Schueler("Anna Schmidt", "Klasse B", 1, 3, 4, 2, 7, 6));
        beispielListe.add(new Schueler("Tom Mueller", "Klasse A", 2, 3, 1, 6, 5, 4));
        beispielListe.add(new Schueler("Lisa Mayer", "Klasse C", 5, 6, 3, 1, 2, 4));
        beispielListe.add(new Schueler("David Becker", "Klasse B", 4, 1, 2, 6, 5, 3));
        beispielListe.add(new Schueler("Herbert Meyer", "Klasse B", 4, 1, 2, 6, 5, 3));

        NeueListeErstellung neueListeErstellung = new NeueListeErstellung(beispielListe);
        neueListeErstellung.erstelleNeueListe();
    }
}

class FeldNummerUndSchueler {
    private int feldNummer;
    private int anzahl;
    private List<Schueler> schuelerListe;

    public FeldNummerUndSchueler(int feldNummer, int anzahl, List<Schueler> schuelerListe) {
        this.feldNummer = feldNummer;
        this.anzahl = anzahl;
        this.schuelerListe = schuelerListe;
    }

    @Override
    public String toString() {
        return "Feldnummer: " + feldNummer + ", Anzahl: " + anzahl + ", Schüler: " + Arrays.toString(showSchuelerListe(schuelerListe));
    }


    public String[] showSchuelerListe(List<Schueler> schueler){
        schueler.sort(Comparator.comparingInt(Schueler::getWahl2).thenComparingInt(Schueler::getWahl1).reversed());
        String[] name = new String[schueler.size()];
        int i = 0;
        for(Schueler s : schueler){
        
           name[i] = s.getSchuelername();
           i++;
        }
        return name;

    }
    //test
}
