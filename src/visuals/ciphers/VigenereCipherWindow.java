package visuals.ciphers;

import utilities.VigenereCipher;
import visuals.TextBoxes;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.MalformedParametersException;

public class VigenereCipherWindow extends CipherDialog {

    JTextField vigenereKeyField;

    public VigenereCipherWindow(Frame parent){
        super(parent, "Vigenere Cipher");
    }

    @Override
    void createUI() {
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        vigenereKeyField = new JTextField(20);

        constraints.gridx = 0;
        constraints.gridy = 0;
        contentPane.add(new JLabel("Key: "), constraints);
        constraints.gridx = 1;
        contentPane.add(vigenereKeyField, constraints);

        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        contentPane.add(encryptButton, constraints);

        constraints.gridx = 1;
        contentPane.add(decryptButton, constraints);
    }

    @Override
    void encryptText() {
        try {
            String encodedText = VigenereCipher.encrypt(TextBoxes.txtAreaPlain.getText(), vigenereKeyField.getText());
            TextBoxes.txtAreaCipher.setText(encodedText);
        }catch (MalformedParametersException exc){
            JOptionPane.showMessageDialog(this, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    void decryptText() {
        try{
        String decodedText = VigenereCipher.decrypt(TextBoxes.txtAreaCipher.getText(), vigenereKeyField.getText());
        TextBoxes.txtAreaPlain.setText(decodedText);
        }catch (MalformedParametersException exc){
            JOptionPane.showMessageDialog(this, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
