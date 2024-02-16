package Code.UI;

import javax.swing.*;
import java.util.ArrayList;

public class CompanyComponents {
    private ArrayList<String> companies = new ArrayList<>();
    private JFrame window;
    public CompanyComponents(JFrame window){
        this.window = window;
    }

    private JPanel createCompanyComponents(){
        JPanel companyPanel = new JPanel();
        return companyPanel;
    }
}
