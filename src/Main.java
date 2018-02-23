import visuals.CaesarCipherWindow;
import visuals.FrequencyWindow;

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

    JTextArea txtAreaCipher;
    JTextArea txtAreaPlain;

    JButton btnAnalysis;
    JButton btnCipher;

    public static void main(String[] args){
        new Main();
    }

    public Main(){
        this.setTitle("Cryptonite");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.createChildWindows();
        this.setUpGui();

        this.setSize(800, 600);
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

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 2;
        constraints.weightx = 0.25;
        constraints.anchor = GridBagConstraints.LINE_START;
        createToolButtonPanel();
        contentPane.add(pnlToolButtons, constraints);

        constraints.gridx = 1;
        constraints.weightx = .75;
        constraints.anchor = GridBagConstraints.LINE_END;
        createTextAreaPanel();
        contentPane.add(pnlText, constraints);
    }

    private void createToolButtonPanel(){
        pnlToolButtons = new JPanel();
        pnlToolButtons.setLayout(new GridLayout(0,1));
        btnAnalysis = new JButton("Analysis Tools");
        btnCipher = new JButton("Cipher solvers");

        btnAnalysis.addActionListener(this);
        btnCipher.addActionListener(this);

        pnlToolButtons.add(btnAnalysis);
        pnlToolButtons.add(new JLabel(""));
        pnlToolButtons.add(btnCipher);
    }

    private void createTextAreaPanel(){
        pnlText = new JPanel();
        pnlText.setLayout(new GridLayout(0,1));

        txtAreaPlain = new JTextArea(15, 30);
        txtAreaCipher = new JTextArea(15, 30);

        txtAreaPlain.setLineWrap(true);
        txtAreaCipher.setLineWrap(true);
        
        pnlText.add(new Label("Plain text"));
        pnlText.add(new JScrollPane(txtAreaPlain));
        pnlText.add(new Label("Cipher text"));
        pnlText.add(new JScrollPane(txtAreaCipher));

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
