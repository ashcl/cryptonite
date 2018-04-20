package visuals.ciphers;

import utilities.CaesarCipher;
import visuals.TextBoxes;

import javax.swing.*;
import java.awt.*;

public class CaesarCipherWindow extends CipherDialog {

    JTextField shiftLengthField;

    public CaesarCipherWindow(Frame parent){
        super(parent, "Caesar Cipher");
    }

    @Override
    void createUI() {
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        shiftLengthField = new JTextField(2);

        constraints.gridy = 0;
        constraints.gridx = 0;
        contentPane.add(new Label("Shift length: "), constraints);
        constraints.gridx = 1;
        contentPane.add(shiftLengthField, constraints);

        constraints.gridy = 1;
        constraints.gridx = 0;
        contentPane.add(encryptButton, constraints);

        constraints.gridx = 1;
        contentPane.add(decryptButton, constraints);
    }

    @Override
    void encryptText(){
        String plainText = TextBoxes.txtAreaPlain.getText();
        try {
            TextBoxes.txtAreaCipher.setText(CaesarCipher.encrypt(plainText, Integer.parseInt(shiftLengthField.getText())));
        }catch (NumberFormatException exc){
            JOptionPane.showMessageDialog(this, "Error: Shift length must be a number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    void decryptText(){
        String cipherText = TextBoxes.txtAreaCipher.getText();
        try {
            TextBoxes.txtAreaPlain.setText(CaesarCipher.decrypt(cipherText, Integer.parseInt(shiftLengthField.getText())));
        }catch (NumberFormatException exc){
            JOptionPane.showMessageDialog(this, "Error: Shift length must be a number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
