package Code.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class CompanyComponents {

    private ArrayList<String> companies = new ArrayList<>();
    private ArrayList<ArrayList<String>> timeIntervals = new ArrayList<>();
    private Window window;
    private JPanel mainPanel;

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
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
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
                        new JCheckBox("10:35-11:20")
                };

                for (JCheckBox checkBox : timeSlotsCheckBoxes) {
                    checkboxesPanel.add(checkBox);
                }

                timePanel.add(checkboxesPanel, BorderLayout.CENTER);

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

        // Company-Panel zum Hauptfenster hinzufügen
        this.mainPanel = new JPanel(new BorderLayout());
        this.mainPanel.add(companyPanel, BorderLayout.CENTER);
        window.add(this.mainPanel, BorderLayout.CENTER);
        window.revalidate();
    }

    private void removeAllComponents() {
        window.remove(this.mainPanel);
        window.revalidate();
        window.repaint();
    }
}