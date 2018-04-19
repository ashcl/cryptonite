package visuals.analysis;

import tools.LowFrequencyAnalysis;
import visuals.TextBoxes;

import javax.swing.*;
import java.awt.*;

public class LowFrequencyWindow extends JDialog {

    JButton analyzeButton;

    JTextField keySizeInputField;

    JLabel suggestedKeyLabel;


    public LowFrequencyWindow(Frame parent) {
        super(parent, "Low Frequency Analysis");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        createUI();
        this.setSize(200, 175);
    }

    private void createUI() {
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JPanel sampleSizePanel = createKeyLengthPanel();

        constraints.ipadx = 10;
        constraints.ipady = 10;
        constraints.gridy = 0;
        constraints.gridx = 0;
        contentPane.add(sampleSizePanel, constraints);

        constraints.gridy = 1;
        contentPane.add(createResultPanel(), constraints);

    }

    private JPanel createKeyLengthPanel() {
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        keySizeInputField = new JTextField("6");
        keySizeInputField.setEditable(true);
        keySizeInputField.setMinimumSize(new Dimension(15, 10));

        analyzeButton = new JButton("Analyze");
        analyzeButton.addActionListener(e -> analyzeCipher());

        constraints.gridy = 0;
        constraints.gridx = 0;
        inputPanel.add(new JLabel("Key length:"), constraints);

        constraints.gridx = 1;
        inputPanel.add(keySizeInputField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        inputPanel.add(analyzeButton, constraints);

        inputPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return inputPanel;
    }

    private JPanel createResultPanel() {
        JPanel resultPanel = new JPanel(new FlowLayout());
        resultPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        resultPanel.add(new JLabel("Suggested Key: "));
        suggestedKeyLabel = new JLabel();
        resultPanel.add(suggestedKeyLabel);
        return resultPanel;
    }

    public void open() {
        this.setVisible(true);
    }


    private void analyzeCipher() {
        String cipherText = TextBoxes.txtAreaCipher.getText();
        try {
            int keyLength = Integer.parseInt(keySizeInputField.getText());
            suggestedKeyLabel.setText(LowFrequencyAnalysis.suggestKey(cipherText, keyLength));
            this.pack();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Key length must be an integer!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
