import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JSONReader {
    public static void readFile(String filePath){
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
}
