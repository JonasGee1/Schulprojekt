package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Klasse zum Erstellen einer Excel-Datei für eine Kursliste.
 *
 * @author [Dein Name]
 */
public class PrintKursliste {
/**
    public static void main(String[] args) {
        // Beispielarrayliste erstellen (Nur als Platzhalter)
        List<List<String>> dataList = new ArrayList<>();
        dataList.add(Arrays.asList("Zentis", "209", "A", "Max Mustermann, Lisa Schmidt, Kevin, John-Paul"));
        dataList.add(Arrays.asList("Zentis", "209", "B", "Hans Müller, Müller Hans"));
        dataList.add(Arrays.asList("Zentis", "209", "C", "Anna Meier, Peter Schmitt"));
        createExcel(dataList);
    }
*/

    /**
     * Methode zum Erstellen einer Excel-Datei aus einer Liste von Daten.
     *
     * @param dataList Die Liste der Daten, die in die Excel-Datei geschrieben werden sollen.
     */
    public static void createExcel(List<List<String>> dataList) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Kursliste");

            // Spaltenüberschriften erstellen
            Row headerRow = sheet.createRow(0);
            String[] columns = {"Firma", "Raum", "Zeit", "Schüler", "Anwesend"};

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Daten aus der ArrayList in Excel übertragen
            int rowNum = 1;
            String previousCompanyName = null;
            boolean companyWritten = false; // Variable, um zu überprüfen, ob die Firmeninformation bereits geschrieben wurde
            for (List<String> rowData : dataList) {
                String companyName = rowData.get(0); // Firma aus der aktuellen Zeile holen
                String students = rowData.get(3); // Die Schülerliste aus der aktuellen Zeile holen
                String[] studentNames = students.split(",\\s*"); // Die Namen der Schüler trennen

                // Firmeninformation nur einmal ausgeben
                Row row = sheet.createRow(rowNum++);
                if (!companyName.equals(previousCompanyName)) {
                    row.createCell(0).setCellValue(companyName); // Firma
                    previousCompanyName = companyName;
                    companyWritten = true;
                } else {
                    companyWritten = false;
                }

                // Für jeden Schüler eine neue Zeile in der Excel-Tabelle erstellen
                for (String studentName : studentNames) {
                    if (!companyWritten) {
                        row = sheet.createRow(rowNum++);
                    }
                    row.createCell(1).setCellValue(rowData.get(1)); // Raum
                    row.createCell(2).setCellValue(rowData.get(2)); // Zeit
                    row.createCell(3).setCellValue(studentName.trim()); // Name des Schülers
                    row.createCell(4); // Leer für Unterschrift
                    companyWritten = false;
                }

            }

            // Automatische Spaltenanpassung
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Excel-Datei speichern
            try (FileOutputStream outputStream = new FileOutputStream("Kursliste.xlsx")) {
                workbook.write(outputStream);
                System.out.println("Excel-Datei erfolgreich erstellt.");
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Erstellen der Excel-Datei: " + e.getMessage());
        }
    }
}
