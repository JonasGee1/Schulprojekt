import java.util.ArrayList;

public class ApiMain {

    public static void main(String[] args) {
//        String csvFilePath = "T:\\Gruppe2\\test.csv";
//        String jsonFilePath = "T:\\Gruppe2\\JsonOutput\\test.json";
//        ExcelToJsonConverter.convertCsvToJson(csvFilePath, jsonFilePath);

        String filePath = "H:\\23-24\\GVI\\TestFormat.json";
        ArrayList<String> data = JSONReader.readJSONFile(filePath);
        // Ausgabe der Daten
        for (String value : data) {
            System.out.println(value);
        }
    }
}

