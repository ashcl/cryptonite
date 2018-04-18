package tools;

import utilities.CaesarCipher;
import utilities.GeneralUtilities;

public class LowFrequencyAnalysis {

    public static void main(String[] args) {
        String testString = "dstvqbhwwdhhdstjmmsqgkghfwgtxxlytrhbhmutmgyinzxnlegemfksfrpxkxwitbkmpkikvyowmgafwjrxurqeekuyuvhlwhgwjbumvlvwgpqiemnsnlxisxkgwneqcvgxfeuesgmpvimvaiurrmwrqefbtipuyfwrkdqtwggeeluspxyxmpvimvwwfzefnmvrifsxvzwgmpnrwhvenvwtutgcpxfxgjunwihwmvaxwiwtymvkmlgvezrxuenzunsqrisbfinvqxfxwdrxiyghybkeetyfkepilhfgwjhnahkrqlwqrvvimvwjrhftjrvxlvcztlmqpvunwwguykfeffrxuzqcymhevrxhvmqvywagvlqlwhnvgmmwhvpbkhktxnerqezxdmvzhowlktyeszcimnkyteewmmumieesncmljmulwowwvzfndyorpnuxwjiqkmvrqxlmpkikvyoumtetgcpxfxgjunwzgymvmpcjifnmvriwmmtysguyuxvtnmfrjnkgghybkzgeigsxkjekuy";
        String trimmedString = GeneralUtilities.removeForeignChars(testString);
        System.out.println(suggestKey(trimmedString, 6));
    }

    public static String suggestKey(String cipher, int keyLength) {
        String trimmedString = GeneralUtilities.removeForeignChars(cipher);
        trimmedString = trimmedString.toLowerCase();
        int[] keyShifts = new int[keyLength];
        for (int i = 0; i < keyLength; i++) {
            keyShifts[i] = suggestShiftLength(GeneralUtilities.extractSubstring(trimmedString, i, keyLength));
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < keyLength; i++) {
            builder.append(GeneralUtilities.ALPHABET.charAt(keyShifts[i]));
        }

        return builder.toString();
    }

    public static int suggestShiftLength(String subString) {
        String trimmedString = GeneralUtilities.removeForeignChars(subString);
        trimmedString = trimmedString.toLowerCase();

        double[] chiAnalysis = new double[26];
        for (int i = 0; i < 26; i++) {
            //Get all the chi-square analysis for all shifts of each string.
            String shiftedAmount = CaesarCipher.decrypt(trimmedString, i);
            chiAnalysis[i] = GeneralUtilities.chiSquareAnalysis(shiftedAmount);
        }

        return findLowestIndex(chiAnalysis);
    }

    private static int findLowestIndex(double[] chiAnalysis) {
        double tempValue = Double.MAX_VALUE;
        int returnIndex = 0;
        for (int i = 0; i < chiAnalysis.length; i++){
            if(chiAnalysis[i] < tempValue){
                returnIndex = i;
                tempValue = chiAnalysis[i];
            }
        }

        return returnIndex;
    }

}
