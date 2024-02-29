package Code.Backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InfoMaxSchuelerList {

    private List<Schueler> schuelerListe;

    public InfoMaxSchuelerList(List<Schueler> schuelerListe) {
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

    public List<Schueler> getSchueler(){
        return this.schuelerListe;
    } 

 
    public static void main(String[] args) {
        // Beispiel-Daten aus der SchuelerVerarbeitung Klasse
        List<Schueler> beispielListe = new ArrayList<Schueler>();
        beispielListe.add(new Schueler("ITF213", "Kopacz","Stan", "1", "2", "3", "4", "5", "6"));
        beispielListe.add(new Schueler("ITF213", "Duecker","Nick" ,"5", "2", "3", "4", "1", "6"));
        beispielListe.add(new Schueler("ITF213", "Hardel","Marvin" ,"5", "2", "3", "4", "1", "6"));
        InfoMaxSchuelerList liste = new InfoMaxSchuelerList(beispielListe);
        liste.erstelleNeueListe();
        
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
        
           name[i] = s.getnachName();
           i++;
        }
        return name;

    }
    // Neuer Test
}
}
