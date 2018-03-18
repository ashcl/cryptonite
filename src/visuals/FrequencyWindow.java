package visuals;

import tools.FrequencyAnalysis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class FrequencyWindow extends JDialog implements ActionListener {

    private String analysisType = "one";

    ButtonGroup btnGroupFrequency;
    JRadioButton radBtnSingle;
    JRadioButton radBtnDouble;
    JRadioButton radBtnTriple;

    JButton btnStartAnalysis;

    JTextArea txtAnalysisResults;

    public FrequencyWindow(Frame owner) {
        super(owner, "Frequency Analysis");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        createUI();

        this.setSize(500, 450);
    }

    private void createUI() {
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //Create radio buttons for analysis type
        radBtnSingle = new JRadioButton("Monograms", true);
        radBtnDouble = new JRadioButton("Digrams");
        radBtnTriple = new JRadioButton("Trigrams");

        radBtnSingle.setActionCommand("mono");
        radBtnDouble.setActionCommand("di");
        radBtnTriple.setActionCommand("tri");

        radBtnSingle.addActionListener(this);
        radBtnDouble.addActionListener(this);
        radBtnTriple.addActionListener(this);

        btnGroupFrequency = new ButtonGroup();
        btnGroupFrequency.add(radBtnSingle);
        btnGroupFrequency.add(radBtnDouble);
        btnGroupFrequency.add(radBtnTriple);

        //Add analysis button
        btnStartAnalysis = new JButton("Run Analysis");
        btnStartAnalysis.setActionCommand("run analysis");
        btnStartAnalysis.addActionListener(this);

        //Add text area for analysis results
        txtAnalysisResults = new JTextArea();
        txtAnalysisResults.setEditable(false);
        txtAnalysisResults.setRows(26);
        txtAnalysisResults.setColumns(15);
        txtAnalysisResults.setLineWrap(true);

        constraints.gridx = 0;
        constraints.gridheight = 5;
        contentPane.add(new JScrollPane(txtAnalysisResults), constraints);

        JPanel radioPanel = new JPanel(new FlowLayout());
        radioPanel.add(radBtnSingle);
        radioPanel.add(radBtnDouble);
        radioPanel.add(radBtnTriple);
        constraints.gridheight = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        contentPane.add(radioPanel, constraints);

        constraints.gridy = 1;
        contentPane.add(btnStartAnalysis, constraints);
    }

    public void open() {
        this.setVisible(true);
    }

    private void runFrequencyAnalysis() {
        String cipherText = TextBoxes.txtAreaCipher.getText();
        HashMap<String, Integer> map = null;
        switch (analysisType) {
            case "one":
                map = FrequencyAnalysis.findMonograms(cipherText);
                txtAnalysisResults.setText(buildResultStringForMonograms(map));
                break;
            case "two":
                map = FrequencyAnalysis.findDigrams(cipherText);
                break;
            case "three":
                map = FrequencyAnalysis.findTrigrams(cipherText);
                break;
            default:
                txtAnalysisResults.setText("Error in analysis: Improper option selected.\n" +
                        "Please click one of the radio buttons to select an analysis method.");
        }
    }

    private String buildResultStringForMonograms(HashMap<String, Integer> map) {
        StringBuilder builder = new StringBuilder();

        for (String key : map.keySet()) {
            builder.append(key + "    " + map.get(key) + "\n");
        }

        return builder.toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "mono":
                analysisType = "one";
                break;
            case "di":
                analysisType = "two";
                break;
            case "tri":
                analysisType = "three";
                break;
            case "run analysis":
                runFrequencyAnalysis();
                break;
            default:
                break;

        }
    }
}
