package visuals;

import utilities.CaesarCipher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaesarCipherWindow extends JDialog  implements ActionListener {

    JButton solveButton;
    JTextArea resultTextArea;

    public CaesarCipherWindow(Frame owner){
        super(owner, "Caesar Cipher");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(300, 500);
        initializeUI();
    }

    private void initializeUI() {
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());
        solveButton = new JButton("Solve");
        solveButton.addActionListener(this);

        resultTextArea = new JTextArea(24, 24);

        container.add(solveButton, BorderLayout.NORTH);
        container.add(new JScrollPane(resultTextArea), BorderLayout.CENTER);
    }

    public void open(){
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object actionSource = e.getSource();
        if (actionSource == solveButton){
            solveCipher();
        }
    }

    private void solveCipher(){
        String cipherText = TextBoxes.txtAreaCipher.getText();
        String cipherSnippet;
        try {
            cipherSnippet = cipherText.substring(0, 10);
        }catch (Exception e){
            //The string isn't big enough for a batch of 10 characters
            cipherSnippet = cipherText;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            builder.append(i).append("\t").
                    append(CaesarCipher.decrypt(cipherSnippet, i))
                    .append("...").append("\n");
        }

        resultTextArea.setText(builder.toString());
    }
}
