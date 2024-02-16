package Code.UI;

import  javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class CompanyComponents {

    //TODO: Grid passend skalieren(Speziell für Zeitslots)
    private ArrayList<String> companies = new ArrayList<>();
    private JFrame window;
    public CompanyComponents(Window window){

        this.window = window;
        this.companies = (window.getCompanies());

        createCompanyComponents();
    }

    private JPanel createCompanyComponents() {
        JPanel companyPanel = new JPanel(new BorderLayout());

        //Liste erstelen für Betriebe
        JList<String> companyList = new JList<>(companies.toArray(new String[0]));
        companyPanel.add(new JScrollPane(companyList), BorderLayout.CENTER);

        //Panel Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));

        //Hinzufügen Button
        JButton addButton = new JButton("Betrieb anlegen");
        addButton.addActionListener(e -> {
            String companyName = JOptionPane.showInputDialog(window, "Geben Sie den Namen des Unternehmens ein:");
            if (companyName != null && !companyName.isEmpty()) {
                companies.add(companyName);
                companyList.setListData(companies.toArray(new String[0])); // Liste aktualisieren
            }
        });

        //Entfernen Button
        JButton deleteButton = new JButton("Betrieb löschen");
        deleteButton.addActionListener(e -> {
            if (!companyList.isSelectionEmpty()) {
                int selectedIndex = companyList.getSelectedIndex();
                companies.remove(selectedIndex);
                companyList.setListData(companies.toArray(new String[0])); // Liste aktualisieren
            } else {
                JOptionPane.showMessageDialog(window, "Bitte wählen Sie ein Unternehmen zum Löschen aus.");
            }
        });

        //TODO: Zeitangabe implementieren, momentan nur "Mock"

        // Button zum Angeben der Zeit
        JButton timeButton = new JButton("Zeit angeben");
        timeButton.addActionListener(e -> {
            // Hier implementieren Sie die Logik zum Angeben der Zeit für das ausgewählte Unternehmen
            if (!companyList.isSelectionEmpty()) {
                String selectedCompany = companyList.getSelectedValue();
                // Hier können Sie den Dialog oder die Eingabeaufforderung implementieren, um die Zeit anzugeben
                JOptionPane.showMessageDialog(window, "Zeit für " + selectedCompany + " angeben.");
            } else {
                JOptionPane.showMessageDialog(window, "Bitte wählen Sie ein Unternehmen aus, um die Zeit anzugeben.");
            }
        });

        // Buttons zum Button-Panel hinzufügen
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(timeButton);


        // Buttons-Panel zum Company-Panel hinzufügen
        companyPanel.add(buttonPanel, BorderLayout.EAST);

        // Company-Panel zum Hauptfenster hinzufügen
        window.add(companyPanel, BorderLayout.CENTER);
        window.revalidate();

        return companyPanel;
    }}