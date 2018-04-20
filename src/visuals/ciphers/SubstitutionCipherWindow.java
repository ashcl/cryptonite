package visuals.ciphers;

import utilities.GeneralUtilities;
import utilities.SubstitutionCipher;
import visuals.TextBoxes;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class SubstitutionCipherWindow extends CipherDialog {

    HashMap<String, String> cipherMap;
    ArrayList<CipherBox> cipherBoxes;
    JTextArea outputPane;

    public SubstitutionCipherWindow(Frame parent) {
        super(parent, "Substitution Cipher");
        cipherMap = initializeCipherMap();
    }

    @Override
    void createUI() {
        initializeCipherBoxes();
        Container contentPane = this.getContentPane();

        JPanel input = createInputPanel();
        contentPane.add(input, BorderLayout.NORTH);

        JPanel outputPanel = new JPanel();
        outputPane = new JTextArea(30, 100);
        outputPane.setLineWrap(true);
        outputPanel.add(new JScrollPane(outputPane), BorderLayout.CENTER);
        outputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Output",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.CENTER));

        contentPane.add(outputPanel, BorderLayout.CENTER);
    }

    @Override
    void encryptText() {
        TextBoxes.txtAreaCipher.setText(SubstitutionCipher.encrypt(TextBoxes.txtAreaPlain.getText(), cipherMap));
    }

    @Override
    void decryptText() {
        TextBoxes.txtAreaPlain.setText(SubstitutionCipher.decrypt(TextBoxes.txtAreaCipher.getText(), cipherMap));
    }

    private void initializeCipherBoxes() {
        cipherBoxes = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            cipherBoxes.add(new CipherBox(String.valueOf(GeneralUtilities.ALPHABET.charAt(i))));
        }

        cipherBoxes.sort(Comparator.comparing(CipherBox::getKey));
    }

    private JPanel createInputPanel() {
        JPanel returnPanel = new JPanel();

        JPanel inputPanel = new JPanel(new FlowLayout());

        for (CipherBox cBox : cipherBoxes) {
            inputPanel.add(new JLabel(cBox.getKey()));
            inputPanel.add(cBox);
        }

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(encryptButton);
        buttonPanel.add(decryptButton);


        returnPanel.add(inputPanel, BorderLayout.NORTH);
        returnPanel.add(buttonPanel, BorderLayout.SOUTH);


        return returnPanel;
    }

    private static HashMap<String, String> initializeCipherMap() {
        HashMap<String, String> initMap = new HashMap<>();

        for (Character c : "abcdefghijklmnopqrstuvwxyz".toCharArray()) {
            initMap.put(String.valueOf(c), "_");
        }

        return initMap;
    }

    /***
     * This is a helper class that represents each cipher input box.
     */
    private class CipherBox extends JTextField implements DocumentListener {

        String key;

        CipherBox(String keyLetter) {
            super(1);
            this.key = keyLetter;
            this.getDocument().addDocumentListener(this);
        }

        public String getKey() {
            return key;
        }

        private boolean checkValid(String s) {
            return s.length() > 0 && s.length() < 2;
        }

        private void updateCipherMap() {
            String newLetter = this.getText();
            if (checkValid(newLetter)) {
                cipherMap.put(key, newLetter);
            } else {
                cipherMap.put(key, "_");
            }
        }

        private void updateOutputPane() {
            StringBuilder builder = new StringBuilder();
            String cipherText = SubstitutionCipher.decrypt(TextBoxes.txtAreaCipher.getText(), cipherMap);
            for (int i = 0; i < cipherText.length(); i++){
                builder.append(cipherText.charAt(i));
                if((i % 5) == 0 && i > 2){
                    builder.append(" ");
                }
            }

            outputPane.setText(builder.toString());
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            updateCipherMap();
            updateOutputPane();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateCipherMap();
            updateOutputPane();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateCipherMap();
            updateOutputPane();
        }
    }



}
