package ExelReader.src.main.java.org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PrintLaufzettel {
/**
    public static void main(String[] args) {
        ArrayList<String[]> dataList = new ArrayList<>();
        // Beispiel Array
        dataList.add(new String[]{"ASS221", "Doe", "Jane", "103", "Finanzamt", "104", "Finanzamt2", "102", "Aachener Sparkasse", "102", "Justizvollzugsanstalt", "107", "Debeka"});
        dataList.add(new String[]{"ASS241", "Mustermann", "Max", "103", "Finanzamt", "104", "Finanzamt2", "102", "Aachener Sparkasse", "102", "Justizvollzugsanstalt", "107", "Debeka"});
        dataList.add(new String[]{"ASS231", "Schmitz", "Kevin", "103", "Finanzamt", "104", "Finanzamt2", "102", "Aachener Sparkasse", "102", "Justizvollzugsanstalt", "107", "Debeka"});
        dataList.add(new String[]{"ASS231", "Mustermann", "Max", "103", "Finanzamt", "104", "Finanzamt2", "102", "Aachener Sparkasse", "102", "Justizvollzugsanstalt", "107", "Debeka"});

        writeDataToExcel(dataList, "output.xlsx");
    }
*/
    public static void writeDataToExcel(ArrayList<String[]> dataList, String fileName) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Veranstaltungen");

            int rowIndex = 0; // Zeilenindex in der Excel-Tabelle
            for (String[] studentData : dataList) {
                // Titelzeile
                Row titleRow = sheet.createRow(rowIndex++);
                titleRow.createCell(0).setCellValue(studentData[0]); // Raum
                titleRow.createCell(1).setCellValue(studentData[1] + ", " + studentData[2]); // Nachname, Vorname

                // Überschriftzeile
                Row headerRow = sheet.createRow(rowIndex++);
                headerRow.createCell(0).setCellValue("Zeit");
                headerRow.createCell(1).setCellValue("Raum");
                headerRow.createCell(2).setCellValue("Veranstaltung");

                String[] times = {"08:45-9:30", "9:50-10:35", "10:35-11:20", "11:40-12:25", "12:25-13:10"};

                // Daten einfügen
                for (int i = 0; i < studentData.length - 1; i += 2) {
                    if (i / 2 < times.length) {
                        Row row = sheet.createRow(rowIndex++);
                        row.createCell(0).setCellValue(times[i / 2]); // Zeit
                        row.createCell(1).setCellValue(studentData[i + 3]); // Raum
                        row.createCell(2).setCellValue(studentData[i + 4]); // Veranstaltung
                    }
                }
                rowIndex++; // Leere Zeile einfügen
            }

            // Auto-size columns
            for (int i = 0; i < 3; i++) {
                sheet.autoSizeColumn(i);
            }

            // Excel-Datei speichern
            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                workbook.write(fileOut);
                System.out.println("Excel-Datei wurde erfolgreich erstellt: " + fileName);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
