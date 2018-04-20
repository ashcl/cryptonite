package visuals.ciphers;

import utilities.ColumnarTransposition;
import utilities.GeneralUtilities;
import utilities.TranspositionCipher;
import visuals.TextBoxes;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.MalformedParametersException;

public class ColumnarTranspositionCipherWindow extends CipherDialog {

    JTextField transpositionKeyField;
    JTextArea columnOutput;

    public ColumnarTranspositionCipherWindow(Frame parent){
        super(parent, "Columnar Transposition Cipher");
    }

    @Override
    void createUI() {
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();


        transpositionKeyField = new JTextField(20);

        constraints.gridx = 0;
        constraints.gridy = 0;
        contentPane.add(new JLabel("Key: "), constraints);
        constraints.gridx = 1;
        contentPane.add(transpositionKeyField, constraints);

        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        contentPane.add(encryptButton, constraints);

        constraints.gridx = 1;
        contentPane.add(decryptButton, constraints);

        /*JPanel columnPanel = createColumnOutputPanel();

        constraints.gridy = 2;
        constraints.gridx = 0;
        constraints.gridheight = 3;

        contentPane.add(columnPanel);
*/
    }

    private JPanel createColumnOutputPanel() {
        JPanel resultPanel = new JPanel();

        columnOutput = new JTextArea();
        columnOutput.setLineWrap(true);
        columnOutput.setRows(50);
        columnOutput.setColumns(10);
        resultPanel.add(new JScrollPane(columnOutput));

        return resultPanel;
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
        updateColumnArea(decodedText);
        }catch (MalformedParametersException exc){
            JOptionPane.showMessageDialog(this, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateColumnArea(String text) {
        StringBuilder builder = new StringBuilder();
        int columns = transpositionKeyField.getText().length();
        String[] subStrings = new String[columns];

        for (int i = 0; i < columns; i++){
            subStrings[i] = GeneralUtilities.extractSubstring(text, i, columns);
        }

        String subString;
        for (int i = 0; i < text.length(); i++){
            //Special beginning case
            if(i == 1){
                subString = subStrings[i];
                builder.append(subString.charAt(0));

                try {
                    subStrings[i] = subString.substring(1);
                }catch (IndexOutOfBoundsException e){
                    //Ignore it
                }
                //Average case
            } else {
                subString = subStrings[i % columns];
                builder.append(subString.charAt(0));
                try {
                    subStrings[i] = subString.substring(1);
                }catch (IndexOutOfBoundsException e){
                    //Ignore it
                }
            }

            if (i > 1 && (i%columns) == 0){
                builder.append("\n");
            }
        }

        this.columnOutput.setText(builder.toString());
    }
}
