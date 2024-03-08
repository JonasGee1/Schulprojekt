package Code.UI;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CompanyComponents {

    private ArrayList<String> companies = new ArrayList<>();
    private ArrayList<ArrayList<String>> timeIntervals = new ArrayList<>();
    private Window window;
    private JPanel mainPanel;

    private DefaultTableModel tableModel;

    public CompanyComponents(Window window) {

        this.window = window;
        this.companies = (window.getCompanies());

        // Initialisieren der Zeitintervalle für jede Firma
        for (int i = 0; i < companies.size(); i++) {
            timeIntervals.add(new ArrayList<>());
        }

        this.createCompanyComponents();
    }

    private void createCompanyComponents() {
        JPanel companyPanel = new JPanel(new BorderLayout());


        //Erstellen der Tabelle für Betriebe
        String[] columnNames = {"Unternehmen", "Zeit"};
        Object[][] data = new Object[companies.size()][2];
        for (int i = 0; i < companies.size(); i++) {
            data[i][0] = companies.get(i);
            String timeIntervalsAsString = String.join(", ", timeIntervals.get(i));
            data[i][1] = timeIntervalsAsString;
        }
        this.tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable companyTable = new JTable(tableModel);

        companyTable.getColumnModel().getColumn(1).setPreferredWidth(300);

        companyPanel.add(new JScrollPane(companyTable), BorderLayout.CENTER);

        //Panel Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));

        //Hinzufügen Button
        JButton addButton = new JButton("Betrieb anlegen");
        addButton.addActionListener(e -> {
            String companyName = JOptionPane.showInputDialog(window, "Geben Sie den Namen des Unternehmens ein:");
            if (companyName != null && !companyName.isEmpty()) {
                companies.add(companyName);
                timeIntervals.add(new ArrayList<>());
                // Neue Zeile zur Tabelle hinzufügen
                tableModel.addRow(new Object[]{companyName, timeIntervals.get(companies.size() - 1)});
            }
        });

        //Entfernen Button
        JButton deleteButton = new JButton("Betrieb löschen");
        deleteButton.addActionListener(e -> {
            int selectedRow = companyTable.getSelectedRow();
            if (selectedRow != -1) {
                companies.remove(selectedRow);
                timeIntervals.remove(selectedRow);
                // Zeile aus der Tabelle entfernen
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(window, "Bitte wählen Sie ein Unternehmen zum Löschen aus.");
            }
        });
//TODO: Richtige Zeitslots einfügen, select and deselct all button
        // Button zum Angeben der Zeit
        JButton timeButton = new JButton("Zeit angeben");
        timeButton.addActionListener(e -> {
            int selectedRow = companyTable.getSelectedRow();
            if (selectedRow != -1) {
                JPanel timePanel = new JPanel(new BorderLayout());

                JPanel checkboxesPanel = new JPanel(new GridLayout(0, 1));
                JCheckBox[] timeSlotsCheckBoxes = {
                        new JCheckBox("08:00-08:45"),
                        new JCheckBox("08:45-09:30"),
                        new JCheckBox("09:50-10:35"),
                        new JCheckBox("10:35-11:20"),
                        new JCheckBox("11:40-12:25"),
                        new JCheckBox("12:25-13:10"),
                        new JCheckBox("13:25-14:10"),
                        new JCheckBox("14:10-14:55"),
                        new JCheckBox("15:05-15:50"),
                        new JCheckBox("15:50-16:35")
                };

                for (JCheckBox checkBox : timeSlotsCheckBoxes) {
                    checkboxesPanel.add(checkBox);
                }

                timePanel.add(checkboxesPanel, BorderLayout.CENTER);

                // Button zum Anwählen aller Zeiten
                JButton selectAllButton = new JButton("Alle auswählen");
                selectAllButton.addActionListener(ev -> {
                    for (JCheckBox checkBox : timeSlotsCheckBoxes) {
                        checkBox.setSelected(true);
                    }
                });

                // Button zum Abwählen aller Zeiten
                JButton deselectAllButton = new JButton("Alle abwählen");
                deselectAllButton.addActionListener(ev -> {
                    for (JCheckBox checkBox : timeSlotsCheckBoxes) {
                        checkBox.setSelected(false);
                    }
                });

                // Hinzufügen der Buttons zum Zeitauswahl-Panel
                JPanel buttonPanelForTimeSelection = new JPanel(new FlowLayout());
                buttonPanelForTimeSelection.add(selectAllButton);
                buttonPanelForTimeSelection.add(deselectAllButton);
                timePanel.add(buttonPanelForTimeSelection, BorderLayout.SOUTH);

                int result = JOptionPane.showConfirmDialog(window, timePanel, "Zeitintervall für " + companies.get(selectedRow), JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    ArrayList<String> selectedTimeSlots = new ArrayList<>();
                    for (JCheckBox checkBox : timeSlotsCheckBoxes) {
                        if (checkBox.isSelected()) {
                            selectedTimeSlots.add(checkBox.getText());
                        }
                    }
                    timeIntervals.set(selectedRow, selectedTimeSlots);
                    tableModel.setValueAt(String.join(", ", selectedTimeSlots), selectedRow, 1);
                }
            } else {
                JOptionPane.showMessageDialog(window, "Bitte wählen Sie ein Unternehmen aus, um die Zeit anzugeben.");
            }
        });

        JButton backButton = new JButton("Zurück");
        backButton.addActionListener(e -> {
            this.removeAllComponents();
            this.window.createPanel();
        });

        // Buttons zum Button-Panel hinzufügen
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(timeButton);
        buttonPanel.add(backButton);


        // Buttons-Panel zum Company-Panel hinzufügen
        companyPanel.add(buttonPanel, BorderLayout.EAST);

        JButton exportButton = new JButton("Exportieren");
        exportButton.addActionListener(e -> {
            try {
                saveJsonToExcel(); // Aufruf der Methode zum Exportieren der Daten in eine Excel-Datei
            } catch (IOException ex) {
                ex.printStackTrace(); // Fehlerbehandlung für den Fall, dass das Exportieren fehlschlägt
            }
        });

        JButton importButton = new JButton("Importieren");
        importButton.addActionListener(e -> {
            importFromExcel(); // Aufruf der Importmethode
        });

        // Hinzufügen des Import-Buttons zum Button-Panel
        buttonPanel.add(importButton);


        // Hinzufügen des Export-Buttons zum Button-Panel
        buttonPanel.add(exportButton);

        // Company-Panel zum Hauptfenster hinzufügen
        this.mainPanel = new JPanel(new BorderLayout());
        this.mainPanel.add(companyPanel, BorderLayout.CENTER);
        window.add(this.mainPanel, BorderLayout.CENTER);
        window.revalidate();
    }

    public void saveJsonToExcel() throws IOException {
        // Erstellen eines neuen Excel-Arbeitsbuches
        Workbook workbook = new XSSFWorkbook();

        // Erstellen eines Arbeitsblatts im Arbeitsbuch
        Sheet sheet = workbook.createSheet("Unternehmen");

        // Erstellen einer Zeile für die Spaltenüberschriften
        Row headerRow = sheet.createRow(0);

        // Hinzufügen der Spaltenüberschriften
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(tableModel.getColumnName(i));
        }

        // Hinzufügen der Datenzeilen
        for (int rowIndex = 0; rowIndex < tableModel.getRowCount(); rowIndex++) {
            Row row = sheet.createRow(rowIndex + 1);
            for (int colIndex = 0; colIndex < tableModel.getColumnCount(); colIndex++) {
                Cell cell = row.createCell(colIndex);
                Object value = tableModel.getValueAt(rowIndex, colIndex);
                if (value != null) {
                    cell.setCellValue(value.toString());
                }
            }
        }

        // Speichern des Arbeitsbuches in einer Datei
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel-Dateien", "xlsx"));
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String filePath = file.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".xlsx")) {
                filePath += ".xlsx";
            }
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
            }
            JOptionPane.showConfirmDialog(null, "Erfolgreich gespeichert!", "Gespeichert", JOptionPane.DEFAULT_OPTION);
        }

        // Schließen des Arbeitsbuches
        workbook.close();
    }

    public void importFromExcel() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel-Dateien", "xlsx"));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (Workbook workbook = new XSSFWorkbook(file)) {
                Sheet sheet = workbook.getSheetAt(0); // Annahme: Daten sind im ersten Blatt

                // Löschen der vorhandenen Daten
                companies.clear();
                timeIntervals.clear();
                tableModel.setRowCount(0);

                // Lesen der Datenzeilen
                for (Row row : sheet) {
                    String companyName = row.getCell(0).getStringCellValue();
                    ArrayList<String> intervals = new ArrayList<>();
                    for (int i = 1; i < row.getLastCellNum(); i++) {
                        Cell cell = row.getCell(i);
                        if (cell != null) {
                            intervals.add(cell.getStringCellValue());
                        }
                    }
                    companies.add(companyName);
                    timeIntervals.add(intervals);
                    tableModel.addRow(new Object[]{companyName, String.join(", ", intervals)});
                }
                //TODO:Fehler richtig implementieren
                JOptionPane.showMessageDialog(null, "Daten erfolgreich importiert!", "Importieren", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException | InvalidFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Fehler beim Importieren der Daten", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void removeAllComponents() {
        window.remove(this.mainPanel);
        window.revalidate();
        window.repaint();
    }
}