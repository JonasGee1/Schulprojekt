package Code.Backend;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JSONReader {
    public static ArrayList<String> readJSONFile(String filePath) {
        ArrayList<String> dataList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim().replaceAll("[\\[\\],]", "");
                String[] keyValuePairs = line.split(",");
                for (String pair : keyValuePairs) {
                    // Teile das Paar an den Doppelpunkten
                    String[] pairParts = pair.split(":");
                    // Stelle sicher, dass das Key-Value-Paar korrekt ist
                    if (pairParts.length == 2) {
                        // Füge den Wert zur Liste hinzu (ohne Anführungszeichen)
                        dataList.add(pairParts[1].trim().replaceAll("\"", ""));
                    } else {
                        System.err.println("Ungültiges Key-Value-Paar: " + pair);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }

}


/*public class JSONReader {
    public static void readFile(String filePath) {
        // Dateipfad zur JSON-Datei

        // ArrayList, um die Daten zu speichern
        ArrayList<String> dataList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Die JSON-Datei zeilenweise lesen
            while ((line = br.readLine()) != null) {
                // Jede Zeile zur ArrayList hinzufügen
                dataList.add(line);
            }

            // ArrayList-Inhalt ausgeben
            for (String data : dataList) {
                System.out.println(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}*/
