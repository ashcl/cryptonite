package utilities;

import java.util.Scanner;

public class VigenereCipher {

    public static String encrypt(String plainText, String key){
        String trimmedText = GeneralUtilities.removeWhitespace(plainText);
        trimmedText = trimmedText.toLowerCase();

        String[] subStrings = new String[key.length()];
        for (int i = 0; i < key.length(); i++){
            subStrings[i] = GeneralUtilities.extractSubstring(trimmedText, i, key.length());
        }

        //Shift each string by an amount defined by the key
        for (int i = 0; i < key.length(); i++){
            subStrings[i] = CaesarCipher.encrypt(subStrings[i], CaesarCipher.ALPHABET.indexOf(key.charAt(i)));
        }

        StringBuilder cipherBuilder = new StringBuilder();
        buildResult(plainText, key, subStrings, cipherBuilder);

        return cipherBuilder.toString();
    }

    public static String decrypt(String cipherText, String key){
        String trimmedText = GeneralUtilities.removeWhitespace(cipherText);
        trimmedText = trimmedText.toLowerCase();

        String[] subStrings = new String[key.length()];
        for (int i = 0; i < key.length(); i++){
            subStrings[i] = GeneralUtilities.extractSubstring(trimmedText, i, key.length());
        }

        //Shift each string by an amount defined by the key
        for (int i = 0; i < key.length(); i++){
            subStrings[i] = CaesarCipher.decrypt(subStrings[i], CaesarCipher.ALPHABET.indexOf(key.charAt(i)));
        }

        StringBuilder cipherBuilder = new StringBuilder();
        buildResult(cipherText, key, subStrings, cipherBuilder);

        return cipherBuilder.toString();
    }

    private static void buildResult(String cipherText, String key, String[] subStrings, StringBuilder cipherBuilder) {
        for (int i = 0; i < cipherText.length(); i++){
            //We want to peel of the first character of each sub string every time we go through this loop.
            String subString = subStrings[i % key.length()];
            if(subString.length() != 0){
                cipherBuilder.append(subString.charAt(0));
                subStrings[i%key.length()] = subString.substring(1);
            }
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter a multi-word message with punctuation:");
        String message = in.nextLine();
        System.out.println("Enter a single word key with no punctuation:");
        String key = in.nextLine();
        String encodedMsg = encrypt(message, key);
        System.out.println("The encoded message is:");
        System.out.println(encodedMsg);
        String decodedMsg = decrypt(encodedMsg, key);
        System.out.println("The decoded message is:");
        System.out.println(decodedMsg);

    }

}