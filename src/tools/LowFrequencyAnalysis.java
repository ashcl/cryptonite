package tools;

import utilities.CaesarCipher;
import utilities.GeneralUtilities;

public class LowFrequencyAnalysis {

    public static void main(String[] args) {
        String testString = "xumop eeomn unhgp khikr vxqcm cpkdl mxmgx ltbte mltui  \n" +
                "hqtmp kobhi eaoet sqqys umfmv xamab kjtpr bzqrj emwge  \n" +
                "rpqke wlvkn unxrz lqrif eofhe fbxmi kubla ranzj mptyq  \n" +
                "imtmb vurfv ryaqn glitm lcfqy rovvv yfarp morpm fbnqm  \n" +
                "sfbld euhmt fligb kdirn kobhi ebkjt auwyq ppnqt febgq  \n" +
                "yunia orusl bvkyi kubyg strkz fhegx iarvr avanh fbuqb  \n" +
                "ipiae eivaz teqba zrrid ckztp lcyqd prbzq rmamt slmfp  \n" +
                "nawii mxgsm aozte zvokz ripqv tevrk gzbir viupl rzkpa  \n" +
                "wqqlr evrvz oitum xfebg tkfti eagfd msnkd ergxu unxfq  \n" +
                "tfhiz myeak rbngs hrnkm tmaoy umtym ldeuh mtoye airks  \n" +
                "mf ";

//        System.out.println(suggestKey(testString, 6));

        String trimmedString = GeneralUtilities.removeWhitespace(testString);
//        int i = suggestShiftLength(GeneralUtilities.extractSubstring(trimmedString, 0, 6));
//        System.out.println(i);
//        System.out.println(GeneralUtilities.ALPHABET.charAt(i));
        System.out.println(suggestKey(trimmedString, 6));
    }

    public static String suggestKey(String cipher, int keyLength) {
        String trimmedString = GeneralUtilities.removeWhitespace(cipher);
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
        String trimmedString = GeneralUtilities.removeWhitespace(subString);
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
