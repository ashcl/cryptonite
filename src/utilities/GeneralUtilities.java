package utilities;

import java.util.Arrays;
import java.util.HashMap;

public class GeneralUtilities {

    public static void main(String[] args){
        String key = "black";
        int[] indexArray = obtainIndexKeyArray(key, true);
        //Expected output: [1,4,0,2,3]
        System.out.println(Arrays.toString(indexArray));
    }

    /**
     * Pull out every (@param)length'th character starting at (@param)index and put it into a string
     * @param s
     * @param index
     * @param length
     * @return
     */
    public static String extractSubstring(String s, int index, int length){
        StringBuilder builder = new StringBuilder();
        for(int i = index; i < s.length(); i = i+length){
            builder.append(s.charAt(i));
        }

        return builder.toString();
    }

    public static String removeWhitespace(String s){
        return s.replaceAll("\\s+","");
    }

    public static int[] obtainIndexKeyArray(String key, boolean encoding){
        char[] keyChars = key.toCharArray();
        Arrays.sort(keyChars);
        HashMap<Character, Integer> lastIndexMap = new HashMap<>();

        int[] indexArray = new int[keyChars.length];
        for (int i = 0; i < keyChars.length; i++) {
            char keyChar = keyChars[i];
            int index = findIndex(key, keyChar, lastIndexMap);

            if (encoding){
                indexArray[i] = index;
            }else{
                indexArray[index] = i;
            }
            lastIndexMap.put(keyChar, index);
        }

        return indexArray;
    }

    private static int findIndex(String key, char keyChar, HashMap<Character, Integer> lastIndexMap) {
        if(lastIndexMap.containsKey(keyChar)) {
            return key.indexOf(keyChar, lastIndexMap.get(keyChar));
        }else{
            return key.indexOf(keyChar);
        }
    }

}
