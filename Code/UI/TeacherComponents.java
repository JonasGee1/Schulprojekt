package Code.UI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class TeacherComponents {
    private ArrayList<String> companies;
    private Object[][] data = {
            {"ITF213", "Hardel", "Marvin", "Polizei", "RWTH", "Barbor", "Feuerwehr", "Bundeswehr"},
            {"ITF213", "Gerschau", "Jonas", "Polizei", "RWTH", "Barbor", "Feuerwehr", "Bundeswehr"},
            {"ITF213", "Muelfarth", "Jan", "Polizei", "RWTH", "Barbor", "Feuerwehr", "Bundeswehr"}
    };
    private Window window;
    private JPanel mainPanel;

    public TeacherComponents(ArrayList<String> companies, Window window) {
        this.companies = companies;
        this.window = window;
        createTeacherComponents();
    }

    private void createTeacherComponents() {
        mainPanel = new JPanel();
        window.add(mainPanel);

        JPanel teacherPanel = new JPanel();
        mainPanel.add(teacherPanel);
        mainPanel.add(getButtonsPanel());

        String[] columnNames = {"Klasse", "Name", "Vorname", "Wahl 1", "Wahl 2", "Wahl 3", "Wahl 4", "Wahl 5", "Wahl 6"};

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column >= 3;
            }
        };

        JTable studentsTable = new JTable(tableModel);
        addComboBoxesToTable(studentsTable, 3, 8);
        teacherPanel.add(new JScrollPane(studentsTable));

        studentsTable.getTableHeader().setReorderingAllowed(false);
        studentsTable.getTableHeader().setResizingAllowed(false);
    }

    private void onBackButtonClick(){
        this.removeAllComponents();
        this.window.createPanel();
    }

    private void onSaveButtonClick(){

    }

    private void onAddStudentsButtonClick(){

    }

    private void onRemoveStudentsButtonClick(){

    }

    private void onCreateNewListButtonClick(){

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
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        buttonsPanel.add(createButton("Zurück", e -> { this.onBackButtonClick(); }));
        buttonsPanel.add(createButton("Speichern", e -> { this.onSaveButtonClick(); }));
        buttonsPanel.add(createButton("Schüler hinzufügen", e -> { this.onAddStudentsButtonClick(); }));
        buttonsPanel.add(createButton("Schüler entfernen", e -> { this.onRemoveStudentsButtonClick(); }));
        buttonsPanel.add(createButton("Neue Liste", e -> { this.onCreateNewListButtonClick(); }));
        buttonsPanel.add(createButton("Liste Laden", e -> { this.onLoadListButtonClick(); }));

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
