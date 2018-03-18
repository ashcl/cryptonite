import visuals.CaesarCipherWindow;
import visuals.FrequencyWindow;
import visuals.TextBoxes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener{

    //Child Windows
    FrequencyWindow freqWindow;
    CaesarCipherWindow ccWindow;

    //UI elements
    JPanel pnlToolButtons;
    JPanel pnlText;

    JButton btnAnalysis;
    JButton btnCipher;

    public static void main(String[] args){
        new Main();
    }

    public Main(){
        this.setTitle("Cryptonite");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.createChildWindows();
        this.setUpGui();

        this.setSize(500, 600);
        this.setVisible(true);

    }

    private void createChildWindows() {
        freqWindow = new FrequencyWindow(this);
        ccWindow = new CaesarCipherWindow(this);
    }

    private void setUpGui(){
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.weightx = constraints.weighty = 1;
        createToolButtonPanel();
        contentPane.add(pnlToolButtons, constraints);

        constraints.gridy = 1;
        constraints.gridheight = 2;
        createTextAreaPanel();
        contentPane.add(pnlText, constraints);
    }

    private void createToolButtonPanel(){
        pnlToolButtons = new JPanel();
        pnlToolButtons.setLayout(new FlowLayout());
        btnAnalysis = new JButton("Analysis Tools");
        btnCipher = new JButton("Cipher solvers");

        btnAnalysis.addActionListener(this);
        btnCipher.addActionListener(this);

        pnlToolButtons.add(btnAnalysis);
        pnlToolButtons.add(btnCipher);
    }

    private void createTextAreaPanel(){
        pnlText = new JPanel();
        pnlText.setLayout(new BorderLayout());

        TextBoxes.txtAreaPlain = new JTextArea(15, 30);
        TextBoxes.txtAreaCipher = new JTextArea(15, 30);

        TextBoxes.txtAreaPlain.setLineWrap(true);
        TextBoxes.txtAreaCipher.setLineWrap(true);

        JPanel plainPanel = new JPanel();
        plainPanel.setLayout(new BorderLayout());

        JPanel cipherPanel = new JPanel();
        cipherPanel.setLayout(new BorderLayout());

        plainPanel.add(new Label("Plain text"), BorderLayout.NORTH);
        plainPanel.add(new JScrollPane(TextBoxes.txtAreaPlain), BorderLayout.SOUTH);

        cipherPanel.add(new Label("Cipher text"), BorderLayout.NORTH);
        cipherPanel.add(new JScrollPane(TextBoxes.txtAreaCipher), BorderLayout.SOUTH);

        pnlText.add(plainPanel, BorderLayout.NORTH);
        pnlText.add(cipherPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnAnalysis){
            freqWindow.open();
        }
        if(e.getSource() == btnCipher){
            ccWindow.open();
        }
    }
}
