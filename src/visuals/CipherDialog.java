package visuals;

import javax.swing.*;
import java.awt.*;

public abstract class CipherDialog extends JDialog {

    public CipherDialog(Frame parent, String title){
        super(parent, title);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        createUI();
        pack();
    }

    abstract void createUI();

    abstract void encryptText();
    abstract void decryptText();
}
