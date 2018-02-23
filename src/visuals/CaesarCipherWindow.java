package visuals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaesarCipherWindow extends JDialog  implements ActionListener {

    public CaesarCipherWindow(Frame owner){
        super(owner, "Caesar Cipher");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    public void open(){
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
