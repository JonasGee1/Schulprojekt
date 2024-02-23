package Code.Backend;

import java.util.ArrayList;

public class Unternehmen {
    private int firmenNr;
    private String firmenName;
    private String slotA;
    private String slotB;
    private String slotC;
    private String slotD;
    private String slotE;
    private int gruppenGroesse;

    // Konstruktor
    public Unternehmen(int firmenNr, String firmenName, String slotA, String slotB, String slotC, String slotD, String slotE, int gruppenGroesse) {
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
    public int getFirmenNr() {
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

    public int getGruppenGroesse() {
        return gruppenGroesse;
    }
}
