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
        constraints.weightx = 0.75;
//        constraints.anchor = GridBagConstraints.LINE_START;
        createToolButtonPanel();
        contentPane.add(pnlToolButtons, constraints);

        constraints.gridx = 1;
        constraints.weightx = .25;
//        constraints.anchor = GridBagConstraints.LINE_END;
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
        pnlText.setLayout(new BorderLayout());

        TextBoxes.txtAreaPlain = new JTextArea(15, 30);
        TextBoxes.txtAreaCipher = new JTextArea(15, 30);

        TextBoxes.txtAreaPlain.setLineWrap(true);
        TextBoxes.txtAreaCipher.setLineWrap(true);
        
        pnlText.add(new Label("Plain text"), BorderLayout.NORTH);
        pnlText.add(new JScrollPane(TextBoxes.txtAreaPlain), BorderLayout.NORTH);
        pnlText.add(new Label("Cipher text"), BorderLayout.SOUTH);
        pnlText.add(new JScrollPane(TextBoxes.txtAreaCipher), BorderLayout.SOUTH);

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
