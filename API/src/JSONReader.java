//import java.io.FileReader;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//public class JsonReader {
//
//
//
//    public static List<String> readJsonToList(String filePath) {
//        List<String> schuelerListe = new ArrayList<>();
//        try {
//            String content = new String(Files.readAllBytes(Paths.get(filePath)));
//            JSONArray jsonArray = new JSONArray(content);
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                StringBuilder schueler = new StringBuilder();
//                schueler.append("Vorname: ").append(jsonObject.getString("Vorname")).append(", ");
//                schueler.append("Nachname: ").append(jsonObject.getString("Nachname")).append(", ");
//                schueler.append("Wahl1: ").append(jsonObject.getString("Wahl1")).append(", ");
//                schueler.append("Wahl2: ").append(jsonObject.getString("Wahl2")).append(", ");
//                schueler.append("Wahl3: ").append(jsonObject.getString("Wahl3")).append(", ");
//                schueler.append("Wahl4: ").append(jsonObject.getString("Wahl4")).append(", ");
//                schueler.append("Wahl5: ").append(jsonObject.getString("Wahl5")).append(", ");
//                schueler.append("Wahl6: ").append(jsonObject.getString("Wahl6"));
//                schuelerListe.add(schueler.toString());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return schuelerListe;
//    }
//}



/*public class JSONReader {
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
}*/
