package Code.Backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Erstellung der Schülerklasse und der Summation der Wahlen

public class Schueler {
    private String schuelername;
    private String klasse;
    private int wahl1;
    private int wahl2;
    private int wahl3;
    private int wahl4;
    private int wahl5;
    private int wahl6;

    public Schueler(String schuelername, String klasse, int wahl1, int wahl2, int wahl3, int wahl4, int wahl5, int wahl6) {
        this.schuelername = schuelername;
        this.klasse = klasse;
        this.wahl1 = wahl1;
        this.wahl2 = wahl2;
        this.wahl3 = wahl3;
        this.wahl4 = wahl4;
        this.wahl5 = wahl5;
        this.wahl6 = wahl6;
    }

    public String getSchuelername() {
        return schuelername;
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
}


