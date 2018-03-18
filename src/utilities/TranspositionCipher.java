package utilities;

import java.util.ArrayList;

public class TranspositionCipher {

    public static void main(String[] args){
        String toEncode = "black";
        String key = "black";
        String encoded = encode(toEncode, key);
        System.out.println(encoded);
        System.out.println(decode(encoded, key));
    }

    public static String encode(String s, String key){
        int[] keyIndex = GeneralUtilities.obtainIndexKeyArray(key, true);
        String plainText = GeneralUtilities.removeWhitespace(s);
        //Split cipher text into blocks of key.length()
        ArrayList<String> subStrings = new ArrayList<>();
        for (int i = 0; i < plainText.length(); i= i+key.length()){
            subStrings.add(extractStringBlock(plainText, key.length(), i));
        }
        StringBuilder builder = new StringBuilder();
        for(String block: subStrings) {
            //Transpose each block according to the keyIndex
            //Add transformed block to the builder
            builder.append(transpose(block, keyIndex));
        }
        return builder.toString();
    }

    public static String decode(String s, String key){
        int[] keyIndex = GeneralUtilities.obtainIndexKeyArray(key, false);
        String cipherText = GeneralUtilities.removeWhitespace(s);
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

    private static String extractStringBlock(String victim, int blockSize, int beginningIndex){
        if((beginningIndex + blockSize) > victim.length()){
            return victim.substring(beginningIndex);
        }
        return victim.substring(beginningIndex, beginningIndex + blockSize);
    }

    private static String transpose(String block, int[] keyIndex) {
        StringBuilder transposeBuilder = new StringBuilder();
        for (int i: keyIndex){
            transposeBuilder.append(String.valueOf(block.charAt(i)));
        }
        return transposeBuilder.toString();
    }
}
