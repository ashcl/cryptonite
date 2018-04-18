package utilities;

import tools.FrequencyAnalysis;

import java.util.*;

public class GeneralUtilities {

    public static double[] ENGLISH_FREQUENCIES = {0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.02228, 0.02015, 0.06094, 0.06966, 0.00153, 0.00772,
            0.04025, 0.02406, 0.06749, 0.07507, 0.01929, 0.00095, 0.05987, 0.06327, 0.09056, 0.02758, 0.00978,
            0.02360, 0.00150, 0.01974, 0.00074};

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        String key = "black";
        int[] indexArray = obtainIndexKeyArray(key, true);
        //Expected output: [1,4,0,2,3]
        System.out.println(Arrays.toString(indexArray));

        String chiTest = "Defend the east wall of the castle";
        System.out.println(chiSquareAnalysis(chiTest));
    }

    /**
     * Pull out every (@param)length'th character starting at (@param)index and put it into a string
     *
     * @param s
     * @param index
     * @param length
     * @return
     */
    public static String extractSubstring(String s, int index, int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = index; i < s.length(); i = i + length) {
            builder.append(s.charAt(i));
        }

        return builder.toString();
    }

    public static String removeForeignChars(String s) {
        String returnVal = s.replaceAll("\\s+", "");
        returnVal = returnVal.replaceAll("([^\\p{L}\\p{N}])", "");
        return returnVal;
    }

    public static double chiSquareAnalysis(String subString) {
        String trimmedSubString = subString.toLowerCase();
        trimmedSubString = removeForeignChars(trimmedSubString);
        int strLength = trimmedSubString.length();
        HashMap<String, Integer> frequencies = FrequencyAnalysis.findMonograms(trimmedSubString);
        double sum = 0.0;
        for (String key : frequencies.keySet()) {
            double expectedFreq = strLength * ENGLISH_FREQUENCIES[ALPHABET.indexOf(key)];
            sum += (Math.pow((frequencies.get(key) + expectedFreq), 2) / expectedFreq);
        }

        return sum;
    }

    public static int[] obtainIndexKeyArray(String key, boolean encoding) {
        char[] keyChars = key.toCharArray();
        Arrays.sort(keyChars);
        HashMap<Character, Integer> lastIndexMap = new HashMap<>();

        int[] indexArray = new int[keyChars.length];
        for (int i = 0; i < keyChars.length; i++) {
            char keyChar = keyChars[i];
            int index = findIndex(key, keyChar, lastIndexMap);

            if (encoding) {
                indexArray[i] = index;
            } else {
                indexArray[index] = i;
            }
            lastIndexMap.put(keyChar, index);
        }

        return indexArray;
    }

    private static int findIndex(String key, char keyChar, Map<Character, Integer> lastIndexMap) {
        if (lastIndexMap.containsKey(keyChar)) {
            return key.indexOf(keyChar, (lastIndexMap.get(keyChar) + 1));
        } else {
            return key.indexOf(keyChar);
        }
    }

    public static String extractStringBlock(String victim, int blockSize, int beginningIndex) {
        if ((beginningIndex + blockSize) > victim.length()) {
            return victim.substring(beginningIndex);
        }
        return victim.substring(beginningIndex, beginningIndex + blockSize);
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
