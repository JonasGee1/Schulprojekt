package Code.UI;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class TeacherComponents {
    private ArrayList<String> companies;
    private ArrayList<Object[]> data = new ArrayList<>();
    private Window window;
    private JPanel mainPanel;
    private JTable studentsTable;
    private DefaultTableModel tableModel;
    private JButton saveButton;
    private JButton addStudentsButton;
    private JButton removeStudentsButton;
    private JButton printListButton;
    private String selectedFilePath;

    public TeacherComponents(ArrayList<String> companies, Window window, ArrayList<Object[]> data, String selectedFilePath) {
        this.companies = companies;
        this.window = window;
        this.data = data;
        this.selectedFilePath = selectedFilePath;
        this.createTeacherComponents();

        if (this.data.isEmpty()) {
            this.saveButton.setEnabled(false);
            this.addStudentsButton.setEnabled(false);
            this.printListButton.setEnabled(false);
        }
        if(this.data.size() == 1){
            this.removeStudentsButton.setEnabled(false);
        }

        if(this.selectedFilePath.equals("") || this.selectedFilePath.isEmpty()){
            this.createNewList();
            this.selectedFilePath = "newList";
            this.window.setStudentsListFilePath(this.selectedFilePath);
        }

        this.window.setLocationRelativeTo(null);
    }

    private void createTeacherComponents() {
        mainPanel = new JPanel(new BorderLayout());
        this.window.add(mainPanel);

        JPanel teacherPanel = new JPanel(new BorderLayout());
        mainPanel.add(teacherPanel, BorderLayout.WEST);
        mainPanel.add(getButtonsPanel(), BorderLayout.CENTER);

        String[] columnNames = {"Klasse", "Name", "Vorname", "Wahl 1", "Wahl 2", "Wahl 3", "Wahl 4", "Wahl 5", "Wahl 6"};

        this.tableModel = new DefaultTableModel(columnNames, 0);
        this.tableModel.setDataVector(this.data.toArray(new Object[0][]), this.getColumNames());


        this.studentsTable = new JTable(tableModel);
        addComboBoxesToTable(this.studentsTable, 3, 8);
        teacherPanel.add(new JScrollPane(this.studentsTable));

        this.studentsTable.getTableHeader().setReorderingAllowed(false);

        this.tableModel.addTableModelListener(e -> {
            this.onTableDataChanged(e);
        });

        if (!this.data.isEmpty()) {
            this.studentsTable.setRowSelectionInterval(this.data.size() - 1, this.data.size() - 1);
        } else {

        }
    }

    private void onTableDataChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        if (row >= 0 && column >= 0) {
            Object newValue = this.tableModel.getValueAt(row, column);
            if (row < data.size()) { // Update existing data if it exists
                data.get(row)[column] = newValue;
            } else { // If row doesn't exist in data, add new row
                Object[] newRow = new Object[tableModel.getColumnCount()];
                newRow[column] = newValue;
                data.add(newRow);
            }
        }
    }

    private void onBackButtonClick() {
        removeAllComponents();
        window.setStudentsList(this.data);
        window.createPanel();
        this.window.setLocationRelativeTo(null);

    }

    private void onSaveButtonClick() throws IOException {
        if (this.isListComplete()) {
            this.saveJsonToFile();
        } else {
            int value = JOptionPane.showInternalConfirmDialog(null, "Liste ist nicht Vollständig. \nTrotzdem speichern?", "Warnung", JOptionPane.YES_NO_OPTION);
            if (value == JOptionPane.YES_OPTION) {
                this.saveJsonToFile();
            }
        }


    }

    private boolean isListComplete() {
        for (Object[] dataRow : this.data) {
            for (int i = 0; i < dataRow.length; i++) {
                if (dataRow[i] == null || dataRow[i].toString().trim().isEmpty() || dataRow[i].toString().trim() == "") {
                    return false;
                }
            }
        }
        return true;
    }

    private void onAddStudentsButtonClick() {
        Object[] newRowData = new Object[this.tableModel.getColumnCount()];
        this.tableModel.addRow(newRowData);
        this.studentsTable.scrollRectToVisible(this.studentsTable.getCellRect(this.tableModel.getRowCount() - 1, 0, true));
        this.saveButton.setEnabled(true);
        this.removeStudentsButton.setEnabled(true);

        this.data.add(new Object[tableModel.getColumnCount()]);
        this.studentsTable.setRowSelectionInterval(this.data.size() - 1, this.data.size() - 1);
    }

    private void onRemoveStudentsButtonClick() {
        int selectedRows[] = this.studentsTable.getSelectedRows();

        if (selectedRows.length >= 1) {
            for (int i = selectedRows.length - 1; i >= 0; i--) {
                int row = selectedRows[i];
                int modelRow = this.studentsTable.convertRowIndexToModel(row);
                this.tableModel.removeRow(modelRow);
                this.data.remove(selectedRows[i]);
                this.studentsTable.setRowSelectionInterval(row - 1, row - 1);
            }
        }


        if (this.data.size() == 1) {
            this.removeStudentsButton.setEnabled(false);
        }
    }

    private void onCreateNewListButtonClick() {
        if(this.selectedFilePath.equals("") || this.selectedFilePath.isEmpty()){

            this.createNewList();
        } else {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Neue Liste anlegen?", "Neue Liste", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_NO_OPTION) {
                this.createNewList();
            }
        }
    }

    private void createNewList(){
        this.data = new ArrayList<>();
        tableModel.setDataVector(data.toArray(new Object[0][]), getColumNames());
        addComboBoxesToTable(this.studentsTable, 3, 8);
        studentsTable.revalidate();
        studentsTable.repaint();
        this.onAddStudentsButtonClick();
        this.studentsTable.setRowSelectionInterval(0, 0);

        this.addStudentsButton.setEnabled(true);
        this.removeStudentsButton.setEnabled(false);
        this.printListButton.setEnabled(true);
    }

    private void onLoadListButtonClick() throws IOException {
        this.loadTextFromFile();
    }


    private JPanel getButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new GridLayout(6, 1, 5, 10));

        this.addStudentsButton = createButton("Schüler hinzufügen", e -> onAddStudentsButtonClick());
        buttonsPanel.add(this.addStudentsButton);

        this.removeStudentsButton = createButton("Schüler entfernen", e -> onRemoveStudentsButtonClick());
        buttonsPanel.add(this.removeStudentsButton);

        buttonsPanel.add(createButton("Neue Liste", e -> onCreateNewListButtonClick()));
        this.saveButton = createButton("Speichern", e -> {
            try {
                onSaveButtonClick();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonsPanel.add(this.saveButton);
        buttonsPanel.add(createButton("Liste Laden", e -> {
            try {
                this.onLoadListButtonClick();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }));

        this.printListButton = createButton("Zu Excel", e -> {
            try {
                this.saveJsonToExcel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        });
        buttonsPanel.add(this.printListButton);

        buttonsPanel.add(createButton("Zurück", e -> onBackButtonClick()));

        return buttonsPanel;
    }


    public void saveJsonToExcel() throws IOException {
        // Erstellen eines neuen Excel-Arbeitsbuches
        Workbook workbook = new XSSFWorkbook();

        // Erstellen eines Arbeitsblatts im Arbeitsbuch
        Sheet sheet = workbook.createSheet("Schülerliste");

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


    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        return button;
    }

    private void addComboBoxesToTable(JTable table, int startColumn, int endColumn) {
        for (int i = startColumn; i <= endColumn; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setCellRenderer(new ComboBoxRenderer());
            column.setCellEditor(new DefaultCellEditor(new JComboBox<>(getOptions())));
        }
    }

    private String[] getOptions() {
        return companies.toArray(new String[0]);
    }

    private class ComboBoxRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            return value instanceof JComboBox ? (Component) value : this;
        }
    }

    private void removeAllComponents() {
        window.remove(mainPanel);
        window.revalidate();
        window.repaint();
    }

    private String createJSONOfData() {
        StringBuilder sb = new StringBuilder("[");

        for (Object[] row : this.data) {
            sb.append("{");
            sb.append("\"Klasse\": \"").append(row[0] == null ? "" : row[0]).append("\",");
            sb.append("\"Name\": \"").append(row[1] == null ? "" : row[1]).append("\",");
            sb.append("\"Vorname\": \"").append(row[2] == null ? "" : row[2]).append("\",");
            for (int i = 3; i < row.length; i++) {
                sb.append("\"Wahl").append(i - 2).append("\": \"").append(row[i] == null ? "" : row[i]).append("\",");
            }
            // Entferne das letzte Komma
            sb.deleteCharAt(sb.length() - 1);
            sb.append("},");
        }
        // Entferne das letzte Komma
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");

        return sb.toString();
    }

    public void saveJsonToFile() throws IOException {
        String json = this.createJSONOfData();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("JSON-Dateien", "json"));
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            PrintWriter writer = new PrintWriter(file);
            writer.print(json);
            writer.close();
            JOptionPane.showConfirmDialog(null, "Erfolgreich gespeichert!", "Gespeichert", JOptionPane.DEFAULT_OPTION);
        }
    }


    public void loadTextFromFile() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("JSON-Dateien", "json"));


        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            this.window.setStudentsListFilePath(fileChooser.getSelectedFile().getAbsolutePath());

            File file = fileChooser.getSelectedFile();

            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            String jsonText = "";
            String line;
            while ((line = br.readLine()) != null) {
                jsonText += line + "\n";
            }
            br.close();

            jsonText = jsonText.trim();

            if (!jsonText.startsWith("[") || !jsonText.endsWith("]")) {
                throw new IllegalArgumentException("Ungültiges JSON-Format: Array erwartet");
            }

            // Entferne die Array-Klammern
            jsonText = jsonText.substring(1, jsonText.length() - 1);

            // Teile den String in einzelne Objekte auf
            String[] objects = jsonText.split("},");

            // Initialisiere das Ergebnis-Array
            Object[][] newData = new Object[objects.length][];

            for (int i = 0; i < objects.length; i++) {
                // Entferne führende und trailing Leerzeichen vom Objekt
                objects[i] = objects[i].trim();

                // Entferne die Objekt-Klammern
                objects[i] = objects[i].substring(1, objects[i].length() - 1);

                // Teile das Objekt in Schlüssel-Wert-Paare auf
                String[] keyValues = objects[i].split(",");

                // Initialisiere das Objekt-Array für diesen Datensatz
                newData[i] = new Object[keyValues.length];

                for (int j = 0; j < keyValues.length; j++) {
                    // Teile Schlüssel und Wert auf
                    String[] keyValue = keyValues[j].trim().split(":");

                    // Extrahiere Schlüssel und Wert
                    String key = keyValue[0].trim().replace("\"", "");
                    String value = keyValue[1].trim().replace("\"", "");

                    // Speichere Schlüssel-Wert-Paar im Ergebnis-Array
                    newData[i][j] = value;
                }
            }
            this.data = new ArrayList<>();
            for (Object[] row : newData) {
                data.add(Arrays.copyOf(row, row.length));
            }
            tableModel.setDataVector(data.toArray(new Object[0][]), getColumNames());
            addComboBoxesToTable(this.studentsTable, 3, 8);
            this.studentsTable.revalidate();
            this.studentsTable.repaint();
            this.studentsTable.setRowSelectionInterval(this.data.size() - 1, this.data.size() - 1);

            this.saveButton.setEnabled(true);
            this.addStudentsButton.setEnabled(true);
            this.removeStudentsButton.setEnabled(true);


            JOptionPane.showConfirmDialog(null, "List geladen!", "Liste geladen", JOptionPane.DEFAULT_OPTION);
        }
    }

    private String[] getColumNames() {
        int columnCount = tableModel.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columnNames[i] = tableModel.getColumnName(i);
        }
        return columnNames;
    }

}
