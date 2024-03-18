package Code.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class LabeledButton extends JPanel {
    private String buttonText;
    private String label;
    private JButton clearButton;
    private File file;
    private JButton button;
    private ActionListener callBack;

    public LabeledButton(String buttonText, String label, File file, ActionListener callBack){
        this.buttonText = buttonText;
        this.label = label;
        this.file = file;
        this.callBack = callBack;

        this.button = new JButton(buttonText);
        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadExcelFileWithFileChooser();
            }
        });

        JLabel labelComponent = new JLabel(label);

        this.clearButton = new JButton("X");
        this.clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        this.clearButton.setEnabled(false);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.button, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        this.add(labelComponent, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        this.add(this.clearButton, gbc);
    }

    public void clearFields() {
        this.label = "";
        this.file = null;
        ((JLabel) this.getComponent(1)).setText("");
        this.clearButton.setEnabled(false);
    }

    public void loadExcelFileWithFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Excel-Datei auswählen");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory() || file.getName().toLowerCase().endsWith(".xls") || file.getName().toLowerCase().endsWith(".xlsx");
            }

            @Override
            public String getDescription() {
                return "Excel-Dateien (*.xls, *.xlsx)";
            }
        });

        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            this.file = selectedFile;
            this.label = selectedFile.getAbsolutePath();
            ((JLabel) this.getComponent(1)).setText(this.label);
            this.clearButton.setEnabled(true);

            this.callBack.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "fileSelected"));
        }
    }
    public File getFile() {
        return this.file;
    }

    public JButton getButton(){
        return this.button;
    }
}
