package Code.UI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class TeacherComponents {
    private ArrayList<String> companies = new ArrayList<>();
    private Object[][] data = {{"ITF213", "Hardel", "Marvin", "Polizei", "RWTH", "Barbor", "Feuerwehr", "Bundeswehr"},
            {"ITF213", "Gerschau", "Jonas", "Polizei", "RWTH", "Barbor", "Feuerwehr", "Bundeswehr"},
            {"ITF213", "Muelfarth", "Jan", "Polizei", "RWTH", "Barbor", "Feuerwehr", "Bundeswehr"}};
    private Window window;
    private JPanel mainPanel;

    public TeacherComponents(ArrayList<String> companies, Window window) {
        this.companies = companies;
        this.window = window;

        this.createTeacherComponents();
    }

    private void createTeacherComponents() {
        this.mainPanel = new JPanel();
        this.window.add(mainPanel);

        JPanel teacherPanel = new JPanel();

        mainPanel.add(teacherPanel);
        mainPanel.add(getButtonsPanel());

        String[] columnNames = {"Klasse", "Name", "Vorname", "Wahl 1", "Wahl 2", "Wahl 3", "Wahl 4", "Wahl 5", "Wahl 6"};

        JTable studentsTable = new JTable(new DefaultTableModel(this.data, columnNames));
        addComboBoxesToTable(studentsTable, 3, 8);
        teacherPanel.add(new JScrollPane(studentsTable));


    }

    private JPanel getButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        buttonsPanel.add(this.createLoadListButton());
        buttonsPanel.add(this.createNewListButton());
        buttonsPanel.add(this.createAddStudentButton());
        buttonsPanel.add(this.createRemoveStudentButton());
        buttonsPanel.add(this.createSaveButton());
        buttonsPanel.add(this.createBackButton());

        return buttonsPanel;
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("Zurück");
        backButton.addActionListener(event -> {
            this.removeAllComponents();
            this.window.createPanel();
        });
        return backButton;
    }

    private JButton createSaveButton() {
        JButton saveButton = new JButton("Speichen");
        saveButton.addActionListener(event -> {

        });
        return saveButton;
    }

    private JButton createAddStudentButton() {
        JButton addStudentButton = new JButton("Schüler hinzufügen");
        addStudentButton.addActionListener(event -> {

        });
        return addStudentButton;
    }

    private JButton createRemoveStudentButton() {
        JButton removeStudentButton = new JButton("Schüler entfernen");
        removeStudentButton.addActionListener(event -> {

        });
        return removeStudentButton;
    }

    private JButton createNewListButton() {
        JButton createListButton = new JButton("Neue Liste");
        createListButton.addActionListener(event -> {

        });
        return createListButton;
    }

    private JButton createLoadListButton() {

        JButton loadListButton = new JButton("Liste Laden");
        loadListButton.addActionListener(event -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Datei auswählen");

            FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON-Dateien", "json");
            fileChooser.setFileFilter(filter);

            int userSelection = fileChooser.showOpenDialog(this.window);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File selecteFile = fileChooser.getSelectedFile();
                System.out.println("Ausgewählte Datei: " + selecteFile.getName());
            }
        });
        return loadListButton;
    }

    private void addComboBoxesToTable(JTable table, int startColumn, int endColumn) {
        for (int i = startColumn; i <= endColumn; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setCellRenderer(new ComboBoxRenderer());
            column.setCellEditor(new DefaultCellEditor(new JComboBox<String>(this.getOptions())));
        }
    }

    private String[] getOptions() {
        return this.companies.toArray(new String[0]);
    }

    private class ComboBoxRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (value instanceof JComboBox) {
                return (Component) value;
            }
            return this;
        }
    }

    private void removeAllComponents(){
        this.window.remove(this.mainPanel);
        this.window.revalidate();
        this.window.repaint();
    }
}
