package Code.Backend;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ZeitslotsVerteilung {

    private List<Schueler> schuelerListe;
    private List<Zeitslots> zeitslotsListe;

    public ZeitslotsVerteilung(List<Schueler> schuelerListe, List<Zeitslots> zeitslotsListe) {
        this.schuelerListe = schuelerListe;
        this.zeitslotsListe = zeitslotsListe;
    }

   
    public void verteileSchuelerAufSlots() {
        // Sortiere die Schüler nach Wahl1 und dann nach Wahl2 absteigend
        schuelerListe.sort(Comparator.comparingInt(Schueler::getWahl2).thenComparingInt(Schueler::getWahl1).reversed());

        for (Schueler schueler : schuelerListe) {
            // Suche nach einem freien Slot für den Schüler
            boolean slotGefunden = false;
            for (Zeitslots zeitslots : zeitslotsListe) {
                if (zeitslots.getFirma().equals(String.valueOf(schueler.getWahl1())) && zeitslots.slotIstFrei()) {
                    zeitslots.fuegeSchuelerHinzu(schueler);
                    slotGefunden = true;
                    break;
                }
            }

            // Wenn kein passender Slot gefunden wurde, suche nach dem nächsten freien Slot
            if (!slotGefunden) {
                for (Zeitslots zeitslots : zeitslotsListe) {
                    if (zeitslots.slotIstFrei()) {
                        zeitslots.fuegeSchuelerHinzu(schueler);
                        break;
                    }
                }
            }
        }

        // Ausgabe der belegten Slots
        System.out.println("Verteilte Schüler auf Slots:");
        for (Zeitslots zeitslots : zeitslotsListe) {
            System.out.println("Firma: " + zeitslots.getFirma());
            System.out.println("SlotA: " + zeitslots.getSlotA());
            System.out.println("SlotB: " + zeitslots.getSlotB());
            System.out.println("SlotC: " + zeitslots.getSlotC());
            System.out.println("SlotD: " + zeitslots.getSlotD());
            System.out.println("SlotE: " + zeitslots.getSlotE());
            System.out.println();
        }

        

    }
    

    public static void main(String[] args) {
        // Beispiel-Daten aus den vorherigen Klassen
        List<Schueler> beispielSchuelerListe = new ArrayList<>();
        beispielSchuelerListe.add(new Schueler("Max Mustermann", "Klasse A", 1, 3, 2, 4, 5, 6));
        beispielSchuelerListe.add(new Schueler("Anna Schmidt", "Klasse B", 1, 3, 4, 2, 7, 6));
        beispielSchuelerListe.add(new Schueler("Tom Mueller", "Klasse A", 2, 3, 1, 6, 5, 4));
        beispielSchuelerListe.add(new Schueler("Lisa Mayer", "Klasse C", 5, 6, 3, 1, 2, 4));
        beispielSchuelerListe.add(new Schueler("David Becker", "Klasse B", 4, 1, 2, 6, 5, 3));
        beispielSchuelerListe.add(new Schueler("Herbert Meyer", "Klasse B", 4, 1, 2, 6, 5, 3));
        
        // Erstelle der Zeitslotsverteilung und Firmen
        List<Zeitslots> beispielZeitslotsListe = new ArrayList<>();
        beispielZeitslotsListe.add(new Zeitslots("1", "", "", "", "", ""));
        beispielZeitslotsListe.add(new Zeitslots("2", "", "", "", "", ""));
        beispielZeitslotsListe.add(new Zeitslots("3", "", "", "", "", ""));
        beispielZeitslotsListe.add(new Zeitslots("4", "", "", "", "", ""));
        beispielZeitslotsListe.add(new Zeitslots("5", "", "", "", "", ""));

        // Übergabe Schülerliste und Zeitslotliste und verteilung der Schüler auf die Slots
        ZeitslotsVerteilung zeitslotsVerteilung = new ZeitslotsVerteilung(beispielSchuelerListe, beispielZeitslotsListe);
        zeitslotsVerteilung.verteileSchuelerAufSlots();
    }
}