package Code.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class Window extends JFrame {
    ArrayList<String> companies = new ArrayList<>();
    JPanel buttonsPanel;

    public void create() {
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

        // Füge einen WindowListener hinzu, um das Programm zu beenden, wenn das Fenster geschlossen wird
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Aufruf der Methode zum Beenden des Programms
                closeWindow();
            }
        });
    }

    private JPanel createButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        buttonPanel.add(this.createTeacherButton());
        buttonPanel.add(this.createCompanyButton());
        buttonPanel.add(this.createCloseButton());

        return buttonPanel;
    }

    private JButton createTeacherButton() {
        JButton teacherButton = new JButton("Lehrer");
        teacherButton.addActionListener(event -> {
            this.removeAllComponents();
            TeacherComponents teacherComponents = new TeacherComponents(this.companies, this);
        });
        return teacherButton;
    }

    private JButton createCompanyButton() {
        JButton companyButton = new JButton("Betrieb");
        companyButton.addActionListener(event -> {
            this.removeAllComponents();
            CompanyComponents companyComponents = new CompanyComponents(this);
        });
        return companyButton;
    }

    private JButton createCloseButton() {
        JButton closeButton = new JButton("Schließen");
        closeButton.addActionListener(e -> closeWindow());
        return closeButton;
    }

    private void removeAllComponents() {
        this.remove(this.buttonsPanel);
        this.revalidate();
        this.repaint();
        this.setSize(700, 500);
    }

    private void addCompany(String company) {
        this.companies.add(company);
    }

    public ArrayList<String> getCompanies() {
        return this.companies;
    }

    // Methode zum Beenden des Programms
    private void closeWindow() {
        // Beenden des Programms
        System.exit(0);
    }
}
