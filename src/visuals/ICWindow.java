package visuals;

import tools.IndexOfCoincidence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ICWindow extends JDialog {

    JButton iCAnalyzeButton;
    JTextArea resultTextArea;
    JTextField maxICLength;

    public ICWindow(Frame owner){
        super(owner, "Index of Coincidence");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(300, 500);
        initializeUI();
    }

    private void initializeUI() {
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());

        maxICLength = new JTextField(1);

        iCAnalyzeButton = new JButton("Solve");
        iCAnalyzeButton.addActionListener(e -> {
            try {
                this.resultTextArea.setText(IndexOfCoincidence
                        .calculateICs(TextBoxes.txtAreaCipher.getText(), Integer.parseInt(maxICLength.getText())));
            }catch (NumberFormatException nfe){
                JOptionPane.showMessageDialog(this, "Error: Maximum key length must be an integer!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        resultTextArea = new JTextArea(24, 24);

        JPanel northPanel = new JPanel(new FlowLayout());
        northPanel.add(new Label("Maximum Key Length: "));
        northPanel.add(maxICLength);

        container.add(northPanel, BorderLayout.NORTH);
        container.add(iCAnalyzeButton, BorderLayout.CENTER);
        container.add(new JScrollPane(resultTextArea), BorderLayout.SOUTH);
    }

    public void open() {
        this.setVisible(true);
    }

}
