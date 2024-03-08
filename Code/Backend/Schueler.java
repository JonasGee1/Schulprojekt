package Code.Backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

//Erstellung der Schülerklasse und der Summation der Wahlen

public class Schueler {
    private String nachName;
    private String vorName;
    private String klasse;
    private String wahl1;
    private String wahl2;
    private String wahl3;
    private String wahl4;
    private String wahl5;
    private String wahl6;

    public Schueler( String klasse, String nachName, String vorName, String wahl1, String wahl2, String wahl3, String wahl4, String wahl5, String wahl6) {
        
        this.klasse = klasse;
        this.nachName = nachName;
        this.vorName = vorName;
        this.wahl1 =wahl1;
        this.wahl2 =wahl2;
        this.wahl3 =wahl3;
        this.wahl4 =wahl4;
        this.wahl5 =wahl5;
        this.wahl6 =wahl6;
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

    public String getWahl1() {
        return wahl1;
    }

    public String getWahl2() {
        return wahl2;
    }

    public String getWahl3() {
        return wahl3;
    }

    public String getWahl4() {
        return wahl4;
    }

    public String getWahl5() {
        return wahl5;
    }

    public String getWahl6() {
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
        String[] daten = new String[9];
        int i = 0;
        for (String eintrag : stringListe) {

            String[] temp = eintrag.split(",");
            daten[i] = temp[0];
            i++;
            
            /* for(int i = 0; i < 9; i++){
                String str[] = eintrag.split(",");
                System.out.println(str);
                daten[i] = str[0];
            } */
        if(i == 9){
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
            i = 0;
                }

        }
        return schuelerList;
    }

    
}


