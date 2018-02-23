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
        freqWindow.open();
        ccWindow = new CaesarCipherWindow(this);
    }

    private void setUpGui(){
        JLabel lblHello = new JLabel("Hello World! Let's get cracking!");
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 2;

        contentPane.add(lblHello);
    }

    private JPanel createToolButtonPanel(){
        JPanel pnlToolButtons = new JPanel();
        pnlToolButtons.setLayout(new GridLayout());
        btnAnalysis = new JButton("Analysis Tools");
        btnCipher = new JButton("Cipher solvers");

        btnAnalysis.addActionListener(this);
        btnCipher.addActionListener(this);

        return pnlToolButtons;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
