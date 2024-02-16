package Code.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class TeacherComponents {
    private ArrayList<String> companies = new ArrayList<>();
    private JFrame window;

    public TeacherComponents(ArrayList<String> companies, JFrame window){
        this.companies = companies;
        this.window = window;

        this.createTeacherComponents();
    }

    private void createTeacherComponents(){
        JPanel teacherPanel = new JPanel();
        this.window.add(teacherPanel);

        String[] columnNames = {"Klasse", "Name", "Vorname", "Wahl 1", "Wahl 2", "Wahl 3", "Wahl 4", "Wahl 5", "Wahl 6"};
        Object[][] data = {
                {"ITF213", "Hardel", "Marvin", "Polizei", "RWTH", "Barbor", "Feuerwehr", "Bundeswehr"},
                {"ITF213", "Gerschau", "Jonas", "Polizei", "RWTH", "Barbor", "Feuerwehr", "Bundeswehr"},
                {"ITF213", "Muelfarth", "Jan", "Polizei", "RWTH", "Barbor", "Feuerwehr", "Bundeswehr"}
        };

        JTable studentsTable = new JTable(new DefaultTableModel(data, columnNames));
        addComboBoxesToTable(studentsTable, 3, 8);
        teacherPanel.add(new JScrollPane(studentsTable));


    }

    private void addComboBoxesToTable(JTable table, int startColumn, int endColumn){
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

}
