package Code.UI;

import  javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class CompanyComponents {
    private ArrayList<String> companies = new ArrayList<>();
    private JFrame window;
    public CompanyComponents(JFrame window){

        this.window = window;
        this.companies = ((Window)window.getCompanies());

        createCompanyComponents();
    }

    private JPanel createCompanyComponents(){
        JPanel companyPanel = new JPanel(new BorderLayout());

        //Liste erstelen für Betriebe
        JList<String> companyList = new JList<>(companies.toArray(new String[0]));
        companyPanel.add(new JScrollPane(companyList), BorderLayout.CENTER);

        //Panel Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3,1));

        //Hinzufügen Button
        JButton buttonAdd = new JButton("Betrieb anlegen");

        return companyPanel;
    }
}
