package Code.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class CompanyComponents {

    //TODO: Grid passend skalieren(Spalte Blockierte Zeitslots)
    //TODO: Nur hh:mm zeitformate akzeptieren implementieren
    private ArrayList<String> companies = new ArrayList<>();
    private Window window;
    private JPanel mainPanel;

    public CompanyComponents(Window window) {

        this.window = window;
        this.companies = (window.getCompanies());

        this.createCompanyComponents();
    }

    private void createCompanyComponents() {
        JPanel companyPanel = new JPanel(new BorderLayout());

        //Erstelen Tabelle für Betriebe
        String[] columnNames = {"Unternehmen", "Zeit"};
        Object[][] data = new Object[companies.size()][2];
        for (int i = 0; i < companies.size(); i++) {
            data[i][0] = companies.get(i);
            data[i][1] = "";
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable companyTable = new JTable(tableModel);

        companyTable.getColumnModel().getColumn(1).setPreferredWidth(100);

        companyPanel.add(new JScrollPane(companyTable), BorderLayout.CENTER);

        //Panel Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));

        //Hinzufügen Button
        JButton addButton = new JButton("Betrieb anlegen");
        addButton.addActionListener(e -> {
            String companyName = JOptionPane.showInputDialog(window, "Geben Sie den Namen des Unternehmens ein:");
            if (companyName != null && !companyName.isEmpty()) {
                companies.add(companyName);
                // Neue Zeile zur Tabelle hinzufügen
                tableModel.addRow(new Object[]{companyName, ""});
            }
        });

        //Entfernen Button
        JButton deleteButton = new JButton("Betrieb löschen");
        deleteButton.addActionListener(e -> {
            int selectedRow = companyTable.getSelectedRow();
            if (selectedRow != -1) {
                companies.remove(selectedRow);
                // Zeile aus der Tabelle entfernen
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(window, "Bitte wählen Sie ein Unternehmen zum Löschen aus.");
            }
        });

        // Button zum Angeben der Zeit
        JButton timeButton = new JButton("Zeit angeben");
        timeButton.addActionListener(e -> {
            int selectedRow = companyTable.getSelectedRow();
            if (selectedRow != -1) {
                String time = JOptionPane.showInputDialog(window, "Geben Sie die Zeit für das Unternehmen ein:");
                if (time != null) {
                    // Zeit in der Tabelle aktualisieren
                    tableModel.setValueAt(time, selectedRow, 1);
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