package Code.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

public class Window extends JFrame {
    private ArrayList<String> companies = new ArrayList<>();
    private ArrayList<Object[]> studentsList = new ArrayList<>();
    private JPanel buttonsPanel;
    private File FileRaumListe;
    private File FileWahlen;
    private File FileVeranstaltungen;


    public void create() {
        this.addCompany("Polizei");
        this.addCompany("RWTH");
        this.addCompany("Barbor");
        this.addCompany("Feuerwehr");
        this.addCompany("Bundeswehr");
        this.addCompany("Simens");

        this.createPanel();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setStudentsList(ArrayList<Object[]> studentsList) {
        this.studentsList = studentsList;
    }

    public void createPanel(){
        this.buttonsPanel = this.createButtons();
        this.add(this.buttonsPanel);

        this.setLayout(new GridBagLayout());
        this.add(this.buttonsPanel, new GridBagConstraints());

        this.setSize(600, 300);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });
    }

    private void setFileRaumListe(File file){
        this.FileRaumListe = file;
    }

    private void setFileWahlen(File file){
        this.FileWahlen = file;
    }

    private void setFileVeranstaltungen(File file){
        this.FileVeranstaltungen = file;
    }



    private JPanel createButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        ActionListener raumListeCallback = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFileRaumListe(((LabeledButton) e.getSource()).getFile());
            }
        };

        ActionListener wahlenListeCallback = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFileWahlen(((LabeledButton) e.getSource()).getFile());
            }
        };

        ActionListener veranstaltungenListeCallback = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFileVeranstaltungen(((LabeledButton) e.getSource()).getFile());
            }
        };

        buttonPanel.add(createLabeledButton("Raumliste importieren", "", this.FileRaumListe, 1, raumListeCallback));
        buttonPanel.add(createLabeledButton("Wahlen importieren", "", this.FileWahlen, 2, wahlenListeCallback));
        buttonPanel.add(createLabeledButton("Veranstaltungen importieren", "", this.FileVeranstaltungen, 3, veranstaltungenListeCallback));

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel createButtonsPanel = new JPanel();
        createButtonsPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.add(createButtonsPanel);

        createButtonsPanel.add(createButton("Laufzettel erstellen", e -> System.out.println(this.FileRaumListe.getAbsolutePath() + "\n" + this.FileVeranstaltungen.getAbsolutePath() + "\n" + this.FileWahlen.getAbsolutePath())));
        createButtonsPanel.add(createButton("Anwesenheitsliste erstellen", e -> System.out.println(this.FileRaumListe.getAbsolutePath() + "\n" + this.FileVeranstaltungen.getAbsolutePath() + "\n" + this.FileWahlen.getAbsolutePath())));
        createButtonsPanel.add(createButton("Raum und Zeitplaung erstellen", e -> System.out.println(this.FileRaumListe.getAbsolutePath() + "\n" + this.FileVeranstaltungen.getAbsolutePath() + "\n" + this.FileWahlen.getAbsolutePath())));


        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel closeButtonPanel = new JPanel();
        closeButtonPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.add(closeButtonPanel);

        closeButtonPanel.add(this.createCloseButton());

        return buttonPanel;
    }

    private LabeledButton createLabeledButton(String buttonText, String label, File file, int index, ActionListener callBack) {
        LabeledButton labeledButton = new LabeledButton(buttonText, label, file, callBack);
        labeledButton.getButton().addActionListener(e -> {
            File selectedFile = labeledButton.getFile();
            if (index == 1) {
                this.FileRaumListe = selectedFile;
            } else if (index == 2) {
                this.FileWahlen = selectedFile;
            } else if (index == 3) {
                this.FileVeranstaltungen = selectedFile;
            }
        });
        return labeledButton;
    }

    private JButton createButton(String text, ActionListener actionListener) {

        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height));
        return button;
    }

    private JButton createCloseButton() {
        JButton closeButton = new JButton("Beenden");
        closeButton.addActionListener(e -> closeWindow());
        return closeButton;
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
