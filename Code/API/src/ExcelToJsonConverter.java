//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//public class ExcelToJsonConverter {
//    public static void convertCsvToJson(String csvFilePath, String jsonFilePath) {
//        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
//             FileWriter jsonFile = new FileWriter(jsonFilePath)) {
//
//            String line;
//            String[] headers = null;
//            List<String[]> dataList = new ArrayList<>();
//
//            // Read headers
//            if ((line = br.readLine()) != null) {
//                headers = line.split(",");
//            }
//
//            // Read data rows
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(",");
//                dataList.add(values);
//            }
//
//            // Convert data to JSON
//            StringBuilder jsonBuilder = new StringBuilder("[");
//            for (String[] data : dataList) {
//                jsonBuilder.append("{");
//                for (int i = 0; i < headers.length && i < data.length; i++) {
//                    jsonBuilder.append("\"").append(headers[i]).append("\":\"").append(data[i]).append("\"");
//                    if (i < headers.length - 1) {
//                        jsonBuilder.append(",");
//                    }
//                }
//                jsonBuilder.append("},");
//            }
//            jsonBuilder.deleteCharAt(jsonBuilder.length() - 1); // Remove last comma
//            jsonBuilder.append("]");
//
//            // Write JSON to file
//            jsonFile.write(jsonBuilder.toString());
//
//            System.out.println("Conversion completed successfully.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
