package visuals.ciphers;

import utilities.ColumnarTransposition;
import utilities.TranspositionCipher;
import visuals.TextBoxes;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.MalformedParametersException;

public class ColumnarTranspositionCipherWindow extends CipherDialog {

    JTextField transpositionKeyField;

    public ColumnarTranspositionCipherWindow(Frame parent){
        super(parent, "Columnar Transposition Cipher");
        this.setSize(175, 150);
    }

    @Override
    void createUI() {
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        transpositionKeyField = new JTextField(20);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        contentPane.add(transpositionKeyField, constraints);

        constraints.gridy = 1;
        constraints.gridwidth = 1;
        contentPane.add(encryptButton);

        constraints.gridx = 1;
        contentPane.add(decryptButton);
    }

    @Override
    void encryptText() {
        try {
            String encodedText = ColumnarTransposition.encode(TextBoxes.txtAreaPlain.getText(), transpositionKeyField.getText());
            TextBoxes.txtAreaCipher.setText(encodedText);
        }catch (MalformedParametersException exc){
            JOptionPane.showMessageDialog(this, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    void decryptText() {
        try{
        String decodedText = ColumnarTransposition.decode(TextBoxes.txtAreaCipher.getText(), transpositionKeyField.getText());
        TextBoxes.txtAreaPlain.setText(decodedText);
        }catch (MalformedParametersException exc){
            JOptionPane.showMessageDialog(this, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
