package visuals;

import tools.FrequencyAnalysis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class FrequencyWindow extends JDialog implements ActionListener {

    private String analysisType = "one";

    ButtonGroup frequencyButtonGroup;
    JRadioButton singleRadioButton;
    JRadioButton doubleRadioButton;
    JRadioButton tripleRadioButton;

    JButton startAnalysisButton;
    JButton displayGraphButton;

    JTextArea analysisResultsTextArea;

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
        singleRadioButton = new JRadioButton("Monograms", true);
        doubleRadioButton = new JRadioButton("Digrams");
        tripleRadioButton = new JRadioButton("Trigrams");

        singleRadioButton.setActionCommand("mono");
        doubleRadioButton.setActionCommand("di");
        tripleRadioButton.setActionCommand("tri");

        singleRadioButton.addActionListener(this);
        doubleRadioButton.addActionListener(this);
        tripleRadioButton.addActionListener(this);

        frequencyButtonGroup = new ButtonGroup();
        frequencyButtonGroup.add(singleRadioButton);
        frequencyButtonGroup.add(doubleRadioButton);
        frequencyButtonGroup.add(tripleRadioButton);

        //Add analysis button
        startAnalysisButton = new JButton("Run Analysis");
        startAnalysisButton.setActionCommand("run analysis");
        startAnalysisButton.addActionListener(this);

        //Add Display Graph button
        displayGraphButton = new JButton("Display Graph");
        displayGraphButton.setActionCommand("display graph");
        displayGraphButton.addActionListener(this);

        //Add text area for analysis results
        analysisResultsTextArea = new JTextArea();
        analysisResultsTextArea.setEditable(false);
        analysisResultsTextArea.setRows(26);
        analysisResultsTextArea.setColumns(15);
        analysisResultsTextArea.setLineWrap(true);

        constraints.gridx = 0;
        constraints.gridheight = 5;
        contentPane.add(new JScrollPane(analysisResultsTextArea), constraints);

        JPanel radioPanel = new JPanel(new FlowLayout());
        radioPanel.add(singleRadioButton);
        radioPanel.add(doubleRadioButton);
        radioPanel.add(tripleRadioButton);
        constraints.gridheight = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        contentPane.add(radioPanel, constraints);

        constraints.gridy = 1;
        contentPane.add(startAnalysisButton, constraints);
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
                analysisResultsTextArea.setText(buildMonogramString(map));
                break;
            case "two":
                map = FrequencyAnalysis.findDigrams(cipherText);
                analysisResultsTextArea.setText(buildDiTrigramString(map));
                break;
            case "three":
                map = FrequencyAnalysis.findTrigrams(cipherText);
                analysisResultsTextArea.setText(buildDiTrigramString(map));
                break;
            default:
                analysisResultsTextArea.setText("Error in analysis: Improper option selected.\n" +
                        "Please click one of the radio buttons to select an analysis method.");
        }
    }

    private String buildMonogramString(HashMap<String, Integer> map) {
        StringBuilder builder = new StringBuilder();

        for (String key : map.keySet()) {
            builder.append(key + "\t" + map.get(key) + "\n");
        }

        return builder.toString();
    }

    private String buildDiTrigramString(HashMap<String, Integer> map) {
        StringBuilder builder = new StringBuilder();

        for (String key : map.keySet()) {
            if (map.get(key) > 1) {
                builder.append(key + "\t" + map.get(key) + "\n");
            }
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
            case "display graph":
                displayGraph();
            default:
                break;

        }
    }

    private void displayGraph() {

    }
}

class GraphDialog extends JDialog{
    GraphDialog(Frame parent){
        super(parent, "Frequency Graph");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
}