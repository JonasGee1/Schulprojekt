package Code.Backend;

import java.util.ArrayList;
import java.util.List;

public class Unternehmen {

    //private List<String> unternehmensliste;

    private String firmenNr;
    private String firmenName;
    private String slotA;
    private String slotB;
    private String slotC;
    private String slotD;
    private String slotE;
    private String gruppenGroesse;

    // Konstruktor für Unternehmen
    public Unternehmen(String firmenNr, String firmenName, String slotA, String slotB, String slotC, String slotD, String slotE, String gruppenGroesse) {
        this.firmenNr = firmenNr;
        this.firmenName = firmenName;
        this.slotA = slotA;
        this.slotB = slotB;
        this.slotC = slotC;
        this.slotD = slotD;
        this.slotE = slotE;
        this.gruppenGroesse = gruppenGroesse;
    }

    // Getter-Methoden
    public String getFirmenNr() {
        return firmenNr;
    }

    public String getFirmenName() {
        return firmenName;
    }

    public String getSlotA() {
        return slotA;
    }

    public String getSlotB() {
        return slotB;
    }

    public String getSlotC() {
        return slotC;
    }

    public String getSlotD() {
        return slotD;
    }

    public String getSlotE() {
        return slotE;
    }

    public String getGruppenGroesse() {
        return gruppenGroesse;
    }


    public static List<Unternehmen> erstelleUnternehmensliste(ArrayList<String> stringListe) {
        List<Unternehmen> unternehmensliste = new ArrayList<>();

        for (String eintrag : stringListe) {
            String[] daten = eintrag.split(",");
            Unternehmen unternehmen = new Unternehmen(
                    daten[0],  // FirmenNr
                    daten[1],  // FirmenName
                    daten[2],  // slotA
                    daten[3],  // slotB
                    daten[4],  // slotC
                    daten[5],  // slotD
                    daten[6],  // slotE
                    daten[7]   // GruppenGroesse
            );
            unternehmensliste.add(unternehmen);
        }

        return unternehmensliste;
    }

}
