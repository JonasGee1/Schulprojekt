package Code.Backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Erstellung der Schülerklasse und der Summation der Wahlen

public class Schueler {
    private String nachName;
    private String vorName;
    private String klasse;
    private int wahl1;
    private int wahl2;
    private int wahl3;
    private int wahl4;
    private int wahl5;
    private int wahl6;

    public Schueler( String klasse, String nachName, String vorName, String wahl1, String wahl2, String wahl3, String wahl4, String wahl5, String wahl6) {
        
        this.klasse = klasse;
        this.nachName = nachName;
        this.vorName = vorName;
        this.wahl1 = Integer.parseInt(wahl1);
        this.wahl2 = Integer.parseInt(wahl2);
        this.wahl3 = Integer.parseInt(wahl3);
        this.wahl4 = Integer.parseInt(wahl4);
        this.wahl5 = Integer.parseInt(wahl5);
        this.wahl6 = Integer.parseInt(wahl6);
    }

    public String getnachName() {
        return nachName;
    }
    public String getvorName(){
        return vorName;
    }

    public String getKlasse() {
        return klasse;
    }

    public int getWahl1() {
        return wahl1;
    }

    public int getWahl2() {
        return wahl2;
    }

    public int getWahl3() {
        return wahl3;
    }

    public int getWahl4() {
        return wahl4;
    }

    public int getWahl5() {
        return wahl5;
    }

    public int getWahl6() {
        return wahl6;
    }

    public String toString(){
        return "Vorname: " + getnachName();
    }
}

class SchuelerWahlSumme {
    private int wahlSumme;
    private int counter;

    public SchuelerWahlSumme(int wahlSumme, int counter) {
        this.wahlSumme = wahlSumme;
        this.counter = counter;
    }

    public int getWahlSumme() {
        return wahlSumme;
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return "Summe: " + wahlSumme + ", Anzahl: " + counter;
    }
    public static List<Schueler> erstelleSchuelerList(ArrayList<String> stringListe) {
        List<Schueler> schuelerList = new ArrayList<>();

        for (String eintrag : stringListe) {
            String[] daten = eintrag.split(",");
            Schueler schueler = new Schueler(
                    daten[0], // Klasse
                    daten[1], // Nachname
                    daten[2], // Vorname
                    daten[3], // wahl1
                    daten[4], // wahl2
                    daten[5], // wahl3
                    daten[6], // wahl4
                    daten[7], // wahl5
                    daten[8]  // wahl6
            );
            schuelerList.add(schueler);
        }

        return schuelerList;
    }
}


