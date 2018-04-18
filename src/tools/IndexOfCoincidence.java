package tools;

import utilities.GeneralUtilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class IndexOfCoincidence {

    public static void main(String[] args) {
        String testString="xumop eeomn unhgp khikr vxqcm cpkdl mxmgx ltbte mltui " +
                "hqtmp kobhi eaoet sqqys umfmv xamab kjtpr bzqrj emwge " +
                "rpqke wlvkn unxrz lqrif eofhe fbxmi kubla ranzj mptyq " +
                "imtmb vurfv ryaqn glitm lcfqy rovvv yfarp morpm fbnqm " +
                "sfbld euhmt fligb kdirn kobhi ebkjt auwyq ppnqt febgq " +
                "yunia orusl bvkyi kubyg strkz fhegx iarvr avanh fbuqb " +
                "ipiae eivaz teqba zrrid ckztp lcyqd prbzq rmamt slmfp " +
                "nawii mxgsm aozte zvokz ripqv tevrk gzbir viupl rzkpa " +
                "wqqlr evrvz oitum xfebg tkfti eagfd msnkd ergxu unxfq " +
                "tfhiz myeak rbngs hrnkm tmaoy umtym ldeuh mtoye airks " +
                "mf";
        System.out.println(IndexOfCoincidence.calculateICs(testString, 8));
    }

    /**
     * Calculate index of coincidence of string (@param)s using William F. Friedman's algorithm
     * @param s
     * @return index of coincidence of given string
     */
    public static double findIC(String s){
        int N = s.length();
        double sum = 0.0;
        double total;
        HashMap<String, Integer> monogramFrequency = FrequencyAnalysis.findMonograms(s);
        Collection<Integer> values = monogramFrequency.values();
        for (Integer i : values){
            sum = sum + (i.doubleValue() * (i.doubleValue()-1));
        }

        total = sum/(N*(N-1));
        return total;
    }



    /**
     * Separate out the strings to find best IC up to (@param)length. First separate the string into (@param)length substrings
     * defined by: S(i) = Ci+C(i+length)+C(i+2*length)+...
     * Then calculate the IC's of each S1,S2,...,Slength and take the average of all of them.
     * @param s
     * @param maxLength
     * @return
     */
    public static String calculateICs(String s, int maxLength){
        StringBuilder builder = new StringBuilder();
        String plain = GeneralUtilities.removeForeignChars(s);
        //Two for loops manage each iteration the outer one controlling length of the strings
        //The inner loop extracts each substring
        for (int l = 0; l < maxLength; l++){
            //For each new length, we need a new average, we get that from the collected IC's of each substring.
            ArrayList<Double> indexesOfCoincidence = new ArrayList<>();
            for (int i = 0; i < l+1; i++){
                indexesOfCoincidence.add(findIC(GeneralUtilities.extractSubstring(plain,i,l+1)));
            }
            double average = 0.0;
            for(Double d: indexesOfCoincidence){
                average = average + d;
            }
            average = average/indexesOfCoincidence.size();
            String toAppend = String.format("%d    %f", (l+1), average);
            builder.append(toAppend + "\n");
        }

        return builder.toString();
    }
}
