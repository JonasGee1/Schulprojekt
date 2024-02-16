package Code.UI;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class TeacherComponents {
    private ArrayList<String> companies;
    private ArrayList<Object[]> data = new ArrayList<>();
    private Window window;
    private JPanel mainPanel;
    private JTable studentsTable;
    private DefaultTableModel tableModel;

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

    private void printData(){
        System.out.println("Data:");
        for(Object[] row : data){
            for(Object cell : row){
                System.out.print(cell + " ");
            }
            System.out.println();
        }
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

    private void onSaveButtonClick(){
        printData();
    }

    private void onAddStudentsButtonClick(){
        Object[] newRowData = new Object[this.tableModel.getColumnCount()];
        this.tableModel.addRow(newRowData);
        this.studentsTable.scrollRectToVisible(this.studentsTable.getCellRect(this.tableModel.getRowCount() - 1, 0, true));
    }

    private void onRemoveStudentsButtonClick(){
        int selectedRow = this.studentsTable.getSelectedRow();
        if(selectedRow != -1){
            int modelRow = this.studentsTable.convertRowIndexToModel(selectedRow);
            this.tableModel.removeRow(modelRow);
        }
    }

    private void onCreateNewListButtonClick(){
        int dialogResult = JOptionPane.showConfirmDialog(null, "Neue Liste anlegen?", "Neue Liste", JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_NO_OPTION){
        }
    }

    private void onLoadListButtonClick(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Datei auswählen");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON-Dateien", "json");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showOpenDialog(window);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Ausgewählte Datei: " + selectedFile.getName());
        }
    }

    private JPanel getButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new GridLayout(6, 1, 5, 10));

        buttonsPanel.add(createButton("Liste Laden", e -> onLoadListButtonClick()));
        buttonsPanel.add(createButton("Neue Liste", e -> onCreateNewListButtonClick()));
        buttonsPanel.add(createButton("Schüler hinzufügen", e -> onAddStudentsButtonClick()));
        buttonsPanel.add(createButton("Schüler entfernen", e -> onRemoveStudentsButtonClick()));
        buttonsPanel.add(createButton("Speichern", e -> onSaveButtonClick()));
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
}
