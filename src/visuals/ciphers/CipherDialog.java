package visuals.ciphers;

import javax.swing.*;
import java.awt.*;

public abstract class CipherDialog extends JDialog {

    JButton encryptButton;
    JButton decryptButton;

    public CipherDialog(Frame parent, String title){
        super(parent, title);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener(e -> encryptText());

        decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener(e -> decryptText());
        createUI();
        pack();
    }

    abstract void createUI();

    abstract void encryptText();
    abstract void decryptText();

    public void open(){
        this.setVisible(true);
    }
}
