package Code.Backend;

import Code.Backend.InfoMaxSchuelerList.FeldNummerUndSchueler;

import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        // Schülerliste mit den Wahlen
        List<Schueler> schueler;
        List<FeldNummerUndSchueler> maxList;
        // Eingabe des Pfades
        String filePath = "T:\\Gruppe2\\StanTest.json"; //Dateipfad und Klasse Backend\JsonReader angepasst - Muelfarth
        // Hier liest der JSONReader aus dem Pfad
        ArrayList<String> data = JSONReader.readJSONFile(filePath);

        // Erstellen der maximalen Schüleranzahlliste

        // Erstellen der Maximalen Schüleranzahlliste

        schueler = SchuelerWahlSumme.erstelleSchuelerList(data);
        InfoMaxSchuelerList liste = new InfoMaxSchuelerList(schueler);
        maxList =  liste.erstelleNeueListe();
        
        //System.out.println(liste.toString());



        List<Zeitslots> beispielZeitslotsListe = new ArrayList<>();
        beispielZeitslotsListe.add(new Zeitslots("Polizei", "", "", "", "", ""));
        beispielZeitslotsListe.add(new Zeitslots("Babor", "", "", "", "", ""));
        beispielZeitslotsListe.add(new Zeitslots("Siemens", "", "", "", "", ""));
        beispielZeitslotsListe.add(new Zeitslots("Feuerwehr", "", "", "", "", ""));
        
        ZeitslotsVerteilung zeitslotsVerteilung = new ZeitslotsVerteilung(schueler, beispielZeitslotsListe);
        zeitslotsVerteilung.verteileSchuelerAufSlots();
        
        
        
        
        


        
        



    }

}

