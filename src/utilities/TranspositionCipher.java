package utilities;

import java.lang.reflect.MalformedParametersException;
import java.util.ArrayList;
import java.util.Scanner;

import static utilities.GeneralUtilities.extractStringBlock;

public class TranspositionCipher {

    public static void main(String[] args){
        Scanner sysIn = new Scanner(System.in);
        String toEncode = sysIn.nextLine();
        String key = sysIn.nextLine();
        String encoded = encode(toEncode, key);
        System.out.println(encoded);
        System.out.println(decode(encoded, key));
    }

    public static String encode(String s, String key) throws MalformedParametersException{
        if(key.equalsIgnoreCase("") || key.length() <= 0){
            throw new MalformedParametersException("Key must contain text of some sort.");
        }
        int[] keyIndex = GeneralUtilities.obtainIndexKeyArray(key, true);
        String plainText = GeneralUtilities.removeForeignChars(s);
        //Split cipher text into blocks of key.length()
        ArrayList<String> subStrings = new ArrayList<>();
        for (int i = 0; i < plainText.length(); i= i+key.length()){
            subStrings.add(extractStringBlock(plainText, key.length()+1, i));
        }
        StringBuilder builder = new StringBuilder();
        for(String block: subStrings) {
            //Transpose each block according to the keyIndex
            //Add transformed block to the builder
            builder.append(transpose(block, keyIndex));
        }
        return builder.toString();
    }

    public static String decode(String s, String key) throws MalformedParametersException{
        if(key.equalsIgnoreCase("") || key.length() <= 0){
            throw new MalformedParametersException("Key must contain text of some sort.");
        }
        int[] keyIndex = GeneralUtilities.obtainIndexKeyArray(key, false);
        String cipherText = GeneralUtilities.removeForeignChars(s);
        //Split cipher text into blocks of key.length()
        ArrayList<String> subStrings = new ArrayList<>();
        for (int i = 0; i < cipherText.length(); i= i+key.length()){
            subStrings.add(extractStringBlock(cipherText, key.length(), i));
        }
        StringBuilder builder = new StringBuilder();
        for(String block: subStrings) {
            //Transpose each block according to the keyIndex
            //Add transformed block to the builder
            builder.append(transpose(block, keyIndex));
        }
        return builder.toString();
    }

    private static String transpose(String block, int[] keyIndex) {
        StringBuilder transposeBuilder = new StringBuilder();
        for (int i: keyIndex) {
            try {
                transposeBuilder.append(String.valueOf(block.charAt(i)));
            } catch (StringIndexOutOfBoundsException exc) {
                //Ignore the error for now
            }
        }
        return transposeBuilder.toString();
    }
}
