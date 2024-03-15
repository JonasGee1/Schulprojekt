package Code.Backend;

import ExelReader.src.main.java.org.example.ExcelReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    
    public static void main(String[] args) {
/*
        // Schülerliste mit den Wahlen
        List<Schueler> schueler;
        List<InfoMaxSchuelerList.FeldNummerUndSchueler> maxList;
        // Eingabe des Pfades
        String filePath = "T:\\Gruppe2\\StanTest.json";
        // Hier liest der JSONReader aus dem Pfad
        ArrayList<String> data = JSONReader.readJSONFile(filePath);

        // Erstellen der maximalen Schüleranzahlliste

        // Erstellen der Maximalen Schüleranzahlliste

        schueler = SchuelerWahlSumme.erstelleSchuelerList(data);
        InfoMaxSchuelerList liste = new InfoMaxSchuelerList(schueler);
        liste.erstelleNeueListe();

        //System.out.println(liste.toString());



        List<Zeitslots> beispielZeitslotsListe = new ArrayList<>();
        beispielZeitslotsListe.add(new Zeitslots("Polizei", "", "", "", "", ""));
        beispielZeitslotsListe.add(new Zeitslots("Babor", "", "", "", "", ""));
        beispielZeitslotsListe.add(new Zeitslots("Siemens", "", "", "", "", ""));
        beispielZeitslotsListe.add(new Zeitslots("Feuerwehr", "", "", "", "", ""));

        ZeitslotsVerteilung zeitslotsVerteilung = new ZeitslotsVerteilung(schueler, beispielZeitslotsListe);
        zeitslotsVerteilung.verteileSchuelerAufSlots();



*/




        ArrayList<String[]> veranstaltungsListe = ExcelReader.readExcel("H:/Projekt/IMPORT BOT1_Veranstaltungsliste.xlsx");
        ArrayList<String[]> wahlen = ExcelReader.readExcel("H:/Projekt/IMPORT BOT2_Wahl.xlsx");


        Veranstaltungsliste veranstaltungsliste = new Veranstaltungsliste();
        int[] teilnehmerZahl = new int[veranstaltungsListe.size()];


        for(String[] veranstaltung : veranstaltungsListe){
            Company c = new Company();
            c.setName(veranstaltung[1]);
            c.setId(Integer.parseInt(veranstaltung[0]));
            veranstaltungsliste.addCompany(c);
        }

        for(String[] wahlRow : wahlen){
            for(int i = 3; i < wahlRow.length - 1; i++){ //Wahl 6 wird erstmal ignoriert
                for(int k = 0; k < veranstaltungsliste.getCompanies().size(); k++){
                    if(veranstaltungsliste.getCompanies().get(k).getId() == Integer.parseInt(wahlRow[i])){
                        veranstaltungsliste.getCompanies().get(k).addStudent(wahlRow[1]);
                    }
                }
            }
        }

        ArrayList<String[]> raumliste = ExcelReader.readExcel("H:/Projekt/IMPORT BOT0_Raumliste.xlsx"); //Stelle: 0 = Raum; Stelle: 1 = Kapazität

        veranstaltungsliste.sortCompaniesBySize();
        veranstaltungsliste.printList();

        // ArrayList<String[]> {"id", "Name", "Raum", "Raum", "Raum", "Raum, "Raum"}

        sortRoomListByCapacity(raumliste);

        ArrayList<String[]> verteilung = new ArrayList<>();

        for(Company c : veranstaltungsliste.getCompanies()){
            String[] besetzung = new String[7];
            besetzung[0] = String.valueOf(c.getId());
            besetzung[1] = c.getName();

            for(String[] raum:raumliste){
                if(Integer.parseInt(raum[1]) > c.getCount()){
                    System.out.println(c.getName());
                }
            }




            verteilung.add(besetzung);
            //System.out.print(raumliste.get(0)[1]);
            //System.out.println(c.getName());
        }

        System.out.println("------------");


        for(String[] t : verteilung){
            for(String a : t){
                System.out.print(a + ", ");
            }
            System.out.println("");
        }

        System.out.println("------------");
    }



    public static void sortRoomListByCapacity(ArrayList<String[]> roomList) {
        // Verwenden eines Comparator, um die Raumliste nach Kapazität zu vergleichen
        Comparator<String[]> capacityComparator = new Comparator<String[]>() {
            @Override
            public int compare(String[] room1, String[] room2) {
                // Umwandlung der Kapazitäten von String in Integer und Vergleich
                int capacity1 = Integer.parseInt(room1[1]);
                int capacity2 = Integer.parseInt(room2[1]);
                return Integer.compare(capacity2, capacity1);
            }
        };

        // Sortieren der Raumliste unter Verwendung des Comparators
        Collections.sort(roomList, capacityComparator);
    }
}
