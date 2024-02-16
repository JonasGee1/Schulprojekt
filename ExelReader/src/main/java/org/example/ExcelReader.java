package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Diese Klasse liest Daten aus einer Excel-Datei ein und speichert sie in einer Liste.
 * Es verwendet die Apache POI-Bibliothek, um mit Excel-Dateien zu arbeiten.
 *
 * @author NickDuecker
 */
public class ExcelReader {
    /**
     * Die Hauptmethode des Programms, die die Excel-Daten einliest und ausgibt.
     *
     * @param args Die Befehlszeilenargumente (nicht verwendet).
     */
    public static void main(String[] args) {
        List<List<String>> excelData = readExcel("C:/Users/Nick/IdeaProjects/Schulprojekt/ExelReader/src/main/resources/BetriebeExcel.xlsx");
        // Excel
        for (List<String> row : excelData) {
            System.out.println(row);
        }
    }

    /**
     * Diese Methode liest eine Excel-Datei ein und gibt deren Inhalt als Liste von Zeilen zurück.
     *
     * @param filename Der Dateipfad zur Excel-Datei.
     * @return Eine Liste von Zeilen, wobei jede Zeile eine Liste von Zellenwerten ist.
     */
    public static List<List<String>> readExcel(String filename) {
        List<List<String>> excelData = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filename);
             Workbook workbook = new XSSFWorkbook(fis)) { // Hier kann auch HSSFWorkbook für .xls-Dateien verwendet werden
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Iterator<Cell> cellIterator = row.cellIterator();
                List<String> rowData = new ArrayList<>();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case STRING:
                            rowData.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            rowData.add(Integer.toString((int) cell.getNumericCellValue()));
                            break;
                        case BOOLEAN:
                            rowData.add(Boolean.toString(cell.getBooleanCellValue()));
                            break;
                        default:
                            rowData.add("");
                            break;
                    }
                }
                excelData.add(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return excelData;
    }
}
