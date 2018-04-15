package visuals.analysis;

import tools.KasiskiAnalysis;
import visuals.TextBoxes;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class KasiskiWindow extends JDialog {

    String baseLengthString = "Key lengths: ";
    private JButton analyzeButton;
    private JTextField sampleSizeInputField;
    JTextArea factorResultField;
    JLabel suggestedLength = new JLabel();

    public KasiskiWindow(Frame owner){
        super(owner, "Kasiski Analysis");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(300, 275);
        createUI();
    }

    private void createUI() {
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JPanel sampleSizePanel = createSampleSizePanel();

        JPanel factorFieldPanel = createFactorFieldPanel();

        constraints.ipadx = 10;
        constraints.ipady = 10;
        constraints.gridy = 0;
        constraints.gridx = 0;
        contentPane.add(sampleSizePanel, constraints);

        constraints.gridy = 1;
        contentPane.add(suggestedLength, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridheight = 3;
        constraints.gridwidth = 3;
        contentPane.add(factorFieldPanel, constraints);
    }

    private JPanel createFactorFieldPanel() {
        JPanel factorFieldPanel = new JPanel(new GridBagLayout());
        factorFieldPanel.setMaximumSize(new Dimension(180, 350));

        factorResultField = new JTextArea(15, 6);
        factorResultField.setMaximumSize(new Dimension(180, 200));
        factorResultField.setLineWrap(true);
        factorResultField.setColumns(6);
        factorResultField.setRows(8);

        factorFieldPanel.add(new JScrollPane(factorResultField));
        factorFieldPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Factors", TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION));
        return factorFieldPanel;
    }

    private JPanel createSampleSizePanel(){
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        sampleSizeInputField = new JTextField("4");
        sampleSizeInputField.setEditable(true);
        sampleSizeInputField.setMinimumSize(new Dimension(15, 10));

        analyzeButton = new JButton("Analyze");
        analyzeButton.addActionListener(e -> analyzeCipher());

        constraints.gridy  = 0;
        constraints.gridx = 0;
        inputPanel.add(new JLabel("Cipher Sample Size:"), constraints);

        constraints.gridx = 1;
        inputPanel.add(sampleSizeInputField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        inputPanel.add(analyzeButton, constraints);

        inputPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return inputPanel;
    }

    public void open(){
        this.setVisible(true);
    }

    private void analyzeCipher(){
        String cipherText = TextBoxes.txtAreaCipher.getText();
        try {
            int sampleSize = Integer.parseInt(sampleSizeInputField.getText());
            HashMap<Integer, Integer> factors = KasiskiAnalysis.calculateFactors(cipherText, sampleSize);
            int[] suggestedKeyLengths = KasiskiAnalysis.suggestKeyLengths(factors);
            updateKeyLengths(suggestedKeyLengths);
            fillFactorField(factors);
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Error: Sample size must be an integer!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fillFactorField(HashMap<Integer, Integer> factors) {
        StringBuilder builder = new StringBuilder();
        Set<Map.Entry<Integer, Integer>> entries = factors.entrySet();

        for (Map.Entry<Integer, Integer> entry : entries){
            builder.append(entry.getKey()).append("   ").append(entry.getValue()).append("\n");
        }

        factorResultField.setText(builder.toString());
    }

    private void updateKeyLengths(int[] keyLengths){
        StringBuilder builder = new StringBuilder(baseLengthString);

        for (int length : keyLengths){
            builder.append(length).append("/");
        }
        builder.deleteCharAt(builder.lastIndexOf("/"));

        this.suggestedLength.setText(builder.toString());
    }
}
