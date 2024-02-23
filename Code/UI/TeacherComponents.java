package Code.UI;

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

    private String selectedFileName = "";

    public TeacherComponents(ArrayList<String> companies, Window window) {
        this.companies = companies;
        this.window = window;
        createTeacherComponents();

        //-------------- Testzweck-------------------
        Object[][] initialData = {
                {"ITF213", "Hardel", "Marvin", "Polizei", "RWTH", "Barbor", "Feuerwehr", "Bundeswehr", "Puff"},
                {"ITF213", "Gerschau", "Jonas", "Polizei", "RWTH", "Barbor", "Feuerwehr", "Bundeswehr", "Puff"},
                {"ITF213", "Muelfarth", "Jan", "Polizei", "RWTH", "Barbor", "Feuerwehr", "Bundeswehr", "Puff"}
        };
        for (Object[] row : initialData) {
            data.add(Arrays.copyOf(row, row.length)); // Add a copy of the row to data
        }

        for (Object[] row : data) {
            tableModel.addRow(row);
        }
        //-------------- Testzweck-------------------

    }

    private void createTeacherComponents() {
        mainPanel = new JPanel(new BorderLayout());
        this.window.add(mainPanel);

        JPanel teacherPanel = new JPanel(new BorderLayout());
        mainPanel.add(teacherPanel, BorderLayout.WEST);
        mainPanel.add(getButtonsPanel(), BorderLayout.CENTER);

        String[] columnNames = {"Klasse", "Name", "Vorname", "Wahl 1", "Wahl 2", "Wahl 3", "Wahl 4", "Wahl 5", "Wahl 6"};

        this.tableModel = new DefaultTableModel(columnNames, 0);

        this.studentsTable = new JTable(tableModel);
        addComboBoxesToTable(this.studentsTable, 3, 8);
        teacherPanel.add(new JScrollPane(this.studentsTable));

        this.studentsTable.getTableHeader().setReorderingAllowed(false);

        this.tableModel.addTableModelListener(e -> {
            this.onTableDataChanged(e);
        });
    }

    private void onTableDataChanged(TableModelEvent e){
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
    private void onBackButtonClick(){
        removeAllComponents();
        window.createPanel();
    }

    private void onSaveButtonClick() throws IOException {
        this.saveJsonToFile();
    }

    private void onAddStudentsButtonClick(){
        Object[] newRowData = new Object[this.tableModel.getColumnCount()];
        this.tableModel.addRow(newRowData);
        this.studentsTable.scrollRectToVisible(this.studentsTable.getCellRect(this.tableModel.getRowCount() - 1, 0, true));
    }

    private void onRemoveStudentsButtonClick(){
        int selectedRows[] = this.studentsTable.getSelectedRows();
        if(selectedRows.length > 0){
            for(int i = selectedRows.length - 1; i >= 0; i--){
                int row = selectedRows[i];
                int modelRow = this.studentsTable.convertRowIndexToModel(row);
                this.tableModel.removeRow(modelRow);
            }
        }
    }

    private void onCreateNewListButtonClick(){
        int dialogResult = JOptionPane.showConfirmDialog(null, "Neue Liste anlegen?", "Neue Liste", JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_NO_OPTION){
        }
    }

    private void onLoadListButtonClick() throws IOException {
        this.loadTextFromFile();
    }



    private JPanel getButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new GridLayout(6, 1, 5, 10));

        buttonsPanel.add(createButton("Liste Laden", e -> {
            try {
                this.onLoadListButtonClick();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }));
        buttonsPanel.add(createButton("Neue Liste", e -> onCreateNewListButtonClick()));
        buttonsPanel.add(createButton("Schüler hinzufügen", e -> onAddStudentsButtonClick()));
        buttonsPanel.add(createButton("Schüler entfernen", e -> onRemoveStudentsButtonClick()));
        buttonsPanel.add(createButton("Speichern", e -> {
            try {
                onSaveButtonClick();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }));
        buttonsPanel.add(createButton("Zurück", e -> onBackButtonClick()));

        return buttonsPanel;
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

    private String createJSONOfData (){
        StringBuilder sb = new StringBuilder("[");

        for (Object[] row : data) {
            sb.append("{");
            sb.append("\"Klasse\": \"").append(row[0]).append("\",");
            sb.append("\"Name\": \"").append(row[1]).append("\",");
            sb.append("\"Vorname\": \"").append(row[2]).append("\",");
            for (int i = 3; i < row.length; i++) {
                sb.append("\"Wahl").append(i - 2).append("\": \"").append(row[i]).append("\",");
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
        if(result == JFileChooser.APPROVE_OPTION){
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
            studentsTable.revalidate();
            studentsTable.repaint();

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
