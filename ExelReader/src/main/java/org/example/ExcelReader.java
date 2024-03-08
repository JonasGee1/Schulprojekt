package ExelReader.src.main.java.org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

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
     * Diese Methode liest eine Excel-Datei ein und gibt deren Inhalt als Array von String-Arrays zurück.
     *
     * @param filename Der Dateipfad zur Excel-Datei.
     * @return Ein Array von String-Arrays, wobei jedes String-Array eine Zeile in der Excel-Datei darstellt.
     */
    public static ArrayList<String[]> readExcel(String filename) {
        ArrayList<String[]> excelData = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filename);
             Workbook workbook = new XSSFWorkbook(fis)) { // Hier kann auch HSSFWorkbook für .xls-Dateien verwendet werden
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) { // Überprüfe, ob mindestens eine Zeile vorhanden ist
                rowIterator.next(); // Springe zur nächsten Zeile (überspringe die erste Zeile)
            }
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                String[] rowData = new String[row.getLastCellNum()];
                int i = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case STRING:
                            rowData[i++] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            rowData[i++] = Integer.toString((int) cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            rowData[i++] = Boolean.toString(cell.getBooleanCellValue());
                            break;
                        default:
                            rowData[i++] = "";
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

