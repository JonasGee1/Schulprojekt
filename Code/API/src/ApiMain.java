import java.util.ArrayList;

public class ApiMain {

    public static void main(String[] args) {
//        String csvFilePath = "T:\\Gruppe2\\test.csv";
//        String jsonFilePath = "T:\\Gruppe2\\JsonOutput\\test.json";
//        ExcelToJsonConverter.convertCsvToJson(csvFilePath, jsonFilePath);

        String filePath = "T:\\Gruppe2\\StanTest.json";
        ArrayList<String> data;
        data = JSONReader.readJSONFile(filePath);
        // Ausgabe der Daten
        for (String value : data) {
            System.out.println(value);
        }
    }
}

