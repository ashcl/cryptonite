package utilities;

import java.util.HashMap;
import java.util.Map;

public class SubstitutionCipher {

    public static void main(String[] args){

    }

    public static String encrypt(String plaintext, HashMap<String, String> cipherMap){
        StringBuilder builder = new StringBuilder();
        String trimmedString = GeneralUtilities.removeForeignChars(plaintext);

        for (Character c : trimmedString.toCharArray()){
            String result = cipherMap.get(String.valueOf(c));
            if (result==null){
                builder.append("_");
            } else {
                builder.append(result);
            }
        }

        return builder.toString();
    }

    private static HashMap<String, String> invertHashMap(HashMap<String, String> cipherMap){
        HashMap<String, String> invertedMap = new HashMap<>();
        for (Map.Entry<String, String> entry : cipherMap.entrySet()){
            invertedMap.put(entry.getValue(), entry.getKey());
        }
        return invertedMap;
    }

    public static String decrypt(String ciphertext, HashMap<String, String> cipherMap){
        HashMap<String, String> map = invertHashMap(cipherMap);
        StringBuilder builder = new StringBuilder();
        String trimmedString = GeneralUtilities.removeForeignChars(ciphertext);

        for (Character c : trimmedString.toCharArray()){
            String result = map.get(String.valueOf(c));
            if (result==null){
                builder.append("_");
            } else {
                builder.append(result);
            }

        }

        return builder.toString();
    }

}
