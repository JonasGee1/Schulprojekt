package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

 /**
 * Klasse zum Erstellen einer Excel-Datei für die Raum- und Zeitplanung.
 *
 * @author NickDuecker
 */

public class PrintRaumUndZeitPlanung {
     /**
     * Methode zum Erstellen einer Excel-Datei aus einer List von Daten.
     *
     * @param dataList Die Liste der Daten, die in die Excel-Datei geschrieben werden sollen.
     */

    public static void createExcel(List<List<String>> dataList) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Raum-und Zeitplanung");

            // Spaltenüberschriften erstellen
            Row headerRow = sheet.createRow(0);
            String[] columns = {"Nr", "Unternehmen", "8:45 - 9:30 - A", "9:50 - 10:35 - B", "10:35 - 11:20 - C", "11:40 - 12:25 - D", "12:25 - 13:10 - E"};

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Daten aus der ArrayList in Excel übertragen
            int rowNum = 1;
            for (List<String> rowData : dataList) {
                Row row = sheet.createRow(rowNum++);
                int colNum = 0;
                for (String cellData : rowData) {
                    row.createCell(colNum++).setCellValue(cellData);
                }
            }

            // Automatische Spaltenanpassung
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Excel-Datei speichern
            try (FileOutputStream outputStream = new FileOutputStream("BOT3_Raum-und Zeitplanung.xlsx")) {
                workbook.write(outputStream);
                System.out.println("Excel-Datei erfolgreich erstellt.");
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Erstellen der Excel-Datei: " + e.getMessage());
        }
    }
}
