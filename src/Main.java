import visuals.*;
import visuals.analysis.*;
import visuals.ciphers.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener{

    //Child Windows
    FrequencyWindow freqWindow;
    ShiftAnalysisWindow shiftAnalysisWindow;
    KasiskiWindow kasiskiWindow;
    ICWindow iCWindow;
    LowFrequencyWindow lowFrequencyWindow;

    CaesarCipherWindow caesarCipherWindow;
    SubstitutionCipherWindow substitutionCipherWindow;
    VigenereCipherWindow vigenereCipherWindow;
    TranspositionCipherWindow transpositionCipherWindow;
    ColumnarTranspositionCipherWindow columnarTranspositionCipherWindow;
    //UI elements
    JPanel pnlToolButtons;
    JPanel pnlText;

    JButton btnAnalysis;
    JButton btnFrequency;
    JButton btnIC;
    JButton btnKasiski;
    JButton btnLowFreq;

    JButton btnCaesar;
    JButton btnSubstitution;
    JButton btnVigenere;
    JButton btnTransposition;
    JButton btnColumnar;

    JButton btnCipher;
    JButton btnShift;

    public static void main(String[] args){
        new Main();
    }

    public Main(){
        this.setTitle("Cryptonite");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.createChildWindows();
        this.setUpGui();

        this.pack();
        this.setVisible(true);

    }

    private void createChildWindows() {
        freqWindow = new FrequencyWindow(this);
        shiftAnalysisWindow = new ShiftAnalysisWindow(this);
        kasiskiWindow = new KasiskiWindow(this);
        iCWindow = new ICWindow(this);
        lowFrequencyWindow = new LowFrequencyWindow(this);

        caesarCipherWindow = new CaesarCipherWindow(this);
        substitutionCipherWindow = new SubstitutionCipherWindow(this);
        vigenereCipherWindow = new VigenereCipherWindow(this);
        transpositionCipherWindow = new TranspositionCipherWindow(this);
        columnarTranspositionCipherWindow = new ColumnarTranspositionCipherWindow(this);
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

        plainPanel.add(new JScrollPane(TextBoxes.txtAreaPlain), BorderLayout.SOUTH);
        Border plainTextBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Plain Text");
        ((TitledBorder) plainTextBorder).setTitleJustification(TitledBorder.CENTER);
        plainPanel.setBorder(plainTextBorder);

        cipherPanel.add(new JScrollPane(TextBoxes.txtAreaCipher), BorderLayout.SOUTH);
        Border cipherTextBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Cipher Text");
        ((TitledBorder) cipherTextBorder).setTitleJustification(TitledBorder.CENTER);
        cipherPanel.setBorder(cipherTextBorder);

        pnlText.add(plainPanel, BorderLayout.NORTH);
        pnlText.add(cipherPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnAnalysis){
            JDialog analysisDialog = createAnalysisLinkDialog();
            analysisDialog.setVisible(true);
        }
        if(e.getSource() == btnCipher){
            JDialog cipherDialog = createCipherLinkDialog();
            cipherDialog.setVisible(true);
        }
    }

    private JDialog createAnalysisLinkDialog() {
        JDialog dialog = new JDialog(this, "Analysis Tools");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        Container contentPane = dialog.getContentPane();
        contentPane.setLayout(new FlowLayout());
        dialog.setSize(250, 250);

        btnFrequency = new JButton("Frequency Analysis");
        btnIC = new JButton("Index of Coincidence");
        btnKasiski = new JButton("Kasiski Analysis");
        btnShift = new JButton("Shift Analysis");
        btnLowFreq = new JButton("Low Frequency");

        btnFrequency.addActionListener(e -> freqWindow.open());
        btnIC.addActionListener(e -> iCWindow.open());
        btnKasiski.addActionListener(e -> kasiskiWindow.open());
        btnShift.addActionListener(e -> shiftAnalysisWindow.open());
        btnLowFreq.addActionListener(e -> lowFrequencyWindow.open());


        contentPane.add(btnFrequency);
        contentPane.add(btnShift);
        contentPane.add(btnKasiski);
        contentPane.add(btnIC);
        contentPane.add(btnLowFreq);
        return dialog;
    }

    private JDialog createCipherLinkDialog(){
        JDialog dialog = new JDialog(this, "Ciphers");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        Container contentPane = dialog.getContentPane();
        contentPane.setLayout(new FlowLayout());
        dialog.setSize(250, 250);

        btnCaesar = new JButton("Caesar Cipher");
        btnCaesar.addActionListener(e -> caesarCipherWindow.open());

        btnVigenere = new JButton("Vigenere Cipher");
        btnVigenere.addActionListener(e -> vigenereCipherWindow.open());

        btnTransposition = new JButton("Transposition Cipher");
        btnTransposition.addActionListener(e -> transpositionCipherWindow.open());

        btnColumnar = new JButton("Columnar Transposition");
        btnColumnar.addActionListener(e -> columnarTranspositionCipherWindow.open());

        btnSubstitution = new JButton("Substitution Window");
        btnSubstitution.addActionListener(e -> substitutionCipherWindow.open());

        contentPane.add(btnCaesar);
        contentPane.add(btnSubstitution);
        contentPane.add(btnVigenere);
        contentPane.add(btnTransposition);
        contentPane.add(btnColumnar);
        return dialog;
    }
}
