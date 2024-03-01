package Code.Backend;

import java.util.ArrayList;
import java.util.List;

public class Zeitslots {

    ArrayList<String> zeitslot = new ArrayList<>();

    private String Firma;

    public String getFirma() {
        return Firma;
    }

    public void setFirma(String firma) {
        Firma = firma;
    }

    private String slotA;

    public String getSlotA() {
        return slotA;
    }

    public void setSlotA(String slotA) {
        this.slotA = slotA;
    }

    private String slotB;

    public String getSlotB() {
        return slotB;
    }

    public void setSlotB(String slotB) {
        this.slotB = slotB;
    }

    private String slotC;

    public String getSlotC() {
        return slotC;
    }

    public void setSlotC(String slotC) {
        this.slotC = slotC;
    }

    private String slotD;

    public String getSlotD() {
        return slotD;
    }

    public void setSlotD(String slotD) {
        this.slotD = slotD;
    }

    private String slotE;

    public String getSlotE() {
        return slotE;
    }

    public void setSlotE(String slotE) {
        this.slotE = slotE;
    }

    public Zeitslots(String Firma, String slotA, String slotB, String slotC, String slotD, String slotE) {
        setFirma(Firma);
        setSlotA(slotA);
        setSlotB(slotB);
        setSlotC(slotC);
        setSlotD(slotD);
        setSlotE(slotE);

    }

    public void fuegeSchuelerHinzu(Schueler schueler) {
        if (getAnzahlSchuelerInSlot(slotA) < 20) {
            slotA = updateSlot(slotA, schueler);
        } else if (getAnzahlSchuelerInSlot(slotB) < 20) {
            slotB = updateSlot(slotB, schueler);
        } else if (getAnzahlSchuelerInSlot(slotC) < 20) {
            slotC = updateSlot(slotC, schueler);
        } else if (getAnzahlSchuelerInSlot(slotD) < 20) {
            slotD = updateSlot(slotD, schueler);
        } else if (getAnzahlSchuelerInSlot(slotE) < 20) {
            slotE = updateSlot(slotE, schueler);
        }

    }

    private String updateSlot(String currentSlot, Schueler schueler) {
        if (currentSlot.isEmpty()) {
            return schueler.getnachName() + " " + schueler.getvorName();
        } else {
            return currentSlot + ", " + schueler.getnachName() + " " + schueler.getvorName();
        }
    }

    public boolean slotIstFrei() {
        return (getAnzahlSchuelerInSlot(slotA) < 20) || (getAnzahlSchuelerInSlot(slotB) < 20) ||
                (getAnzahlSchuelerInSlot(slotC) < 20) || (getAnzahlSchuelerInSlot(slotD) < 20) ||
                (getAnzahlSchuelerInSlot(slotE) < 20);
    }

    private int getAnzahlSchuelerInSlot(String slot) {
        if (slot.isEmpty()) {
            return 0;
        }
        return slot.split(",").length;
    }

    public static List<Zeitslots> erstelleZeitslotliste(ArrayList<String> stringListe) {
        List<Zeitslots> zeitslotliste = new ArrayList<>();

        for (String eintrag : stringListe) {
            String[] daten = eintrag.split(",");
            Zeitslots zeitslots = new Zeitslots(
                    daten[0], // Firma
                    daten[1], // slotA
                    daten[2], // slotB
                    daten[3], // slotC
                    daten[4], // slotD
                    daten[5]  // slotE
            );
            zeitslotliste.add(zeitslots);
        }

        return zeitslotliste;
    }

}
