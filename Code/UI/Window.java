package Code.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Window extends JFrame{
    ArrayList<String> companies = new ArrayList<>(); //FIXME: Später löschen. Ist nur zu Testzwecken, weil später die Daten im Backend liegen werden
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
            TeacherComponents teacherComponents = new TeacherComponents(this.companies, this);
        });
        return teacherButton;
    }

    private JButton createCompanyButton(){
        JButton teacherButton = new JButton("Betrieb");
        teacherButton.addActionListener(event -> {
            this.removeAllComponents();
            CompanyComponents companyComponents = new CompanyComponents(this);
        });
        return teacherButton;
    }

    private void removeAllComponents(){
        this.remove(this.buttonsPanel);
        this.revalidate();
        this.repaint();
        this.setSize(700, 500);
    }

    private void addCompany(String company){
        this.companies.add(company);
    }

    public ArrayList<String> getCompanies (){
        return this.companies;
    }
}
