package Code.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Window extends JFrame{
    ArrayList<String> companies = new ArrayList<>();
    JPanel buttonsPanel;
    public void create(){
        this.addCompany("Polizei");
        this.addCompany("RWTH");
        this.addCompany("Barbor");
        this.addCompany("Feuerwehr");
        this.addCompany("Bundeswehr");
        this.addCompany("Puff");




        this.buttonsPanel = this.createButtons();
        this.add(this.buttonsPanel);

        this.setLayout(new GridBagLayout());
        this.add(this.buttonsPanel, new GridBagConstraints());

        this.setSize(300, 150);
        this.setVisible(true);
    }

    private JPanel createButtons(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        buttonPanel.add(this.createTeacherButton());
        buttonPanel.add(this.createCompanyButton());



        return buttonPanel;
    }

    private JButton createTeacherButton(){
        JButton teacherButton = new JButton("Lehrer");
        teacherButton.addActionListener(event -> {
            this.removeAllComponents();
            this.createTeacherComponents();
        });
        return teacherButton;
    }

    private JButton createCompanyButton(){
        JButton teacherButton = new JButton("Betrieb");
        teacherButton.addActionListener(event -> {
            this.removeAllComponents();
        });
        return teacherButton;
    }

    private void removeAllComponents(){
        this.remove(this.buttonsPanel);
        this.revalidate();
        this.repaint();
        this.setSize(700, 500);
    }

    private void createTeacherComponents(){
        JPanel teacherPanel = new JPanel();
        this.add(teacherPanel);

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

    private JPanel createCompanyComponents(){
        JPanel companyPanel = new JPanel();
        return companyPanel;
    }

    private void addCompany(String company){
        this.companies.add(company);
    }

    private ArrayList<String> getCompanies (){
        return this.companies;
    }
}
