package tools;

import utilities.GeneralUtilities;

import java.util.*;

public class KasiskiAnalysis {

    public static void main(String[] args) {
        int factorTest = 100;
        Collection<Integer> factors = factor(factorTest);
        for (Integer factor : factors) {
            System.out.println(factor + ", ");
        }

        String cipher = "xumop eeomn unhgp khikr vxqcm cpkdl mxmgx ltbte mltui  \n" +
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

        int[] suggestedLenths = suggestKeyLengths(calculateFactors(cipher, 4));
        for (int i : suggestedLenths) {
            System.out.println(i + ", ");
        }
    }

    public static int[] suggestKeyLengths(HashMap<Integer, Integer> factorCounts) {

        //The highest count factor that isn't 2 is the likely key length.
        //However, suggest the second and third highest count factor as well, for safety
        int[] likelyKeyLengths = new int[3];

        Map<Integer, Integer> sortedMap = GeneralUtilities.sortByValue(factorCounts);
        sortedMap.remove(2);
        Iterator<Map.Entry<Integer, Integer>> sortedIterator = sortedMap.entrySet().iterator();
        for(int i = 0; i < 3; i++){
            likelyKeyLengths[i] = sortedIterator.next().getKey();
        }

        return likelyKeyLengths;
    }

    public static HashMap<Integer, Integer> calculateFactors(String cipherText, int sampleLength) {
        HashMap<Integer, Integer> factorCounts = new HashMap<>();
        String trimmedCipher = GeneralUtilities.removeWhitespace(cipherText);

        //Sanity check
        if(sampleLength+3 >= cipherText.length()){
            throw new IndexOutOfBoundsException("The cipher text is too small to perform Kasiski analysis!");
        }

        for (int i = 0; i < 4; i++) {

            //We need to take a section of the text of @param sampleLength
            String subString = trimmedCipher.substring(i, sampleLength);

            ArrayList<Integer> occurrences = countOccurrences(trimmedCipher, subString);

            //If there are a suitable number of occurrences (i.e. >= 3)
            if (occurrences.size() >= 3) {
                //then take the distance between them
                int[] distances = new int[occurrences.size() - 1];
                for (int j = 0; j < occurrences.size() - 1; j++) {
                    distances[j] = occurrences.get(j + 1) - occurrences.get(j);
                }

                //then factor the distances and take the count of each factor
                countFactors(factorCounts, distances);
            }

        }
        return factorCounts;
    }

    private static void countFactors(HashMap<Integer, Integer> factorCounts, int[] distances) {
        for (int distance : distances) {
            Collection<Integer> factors = factor(distance);
            for (Integer factor : factors) {
                factorCounts.merge(factor, 1, (a, b) -> a + b);
            }
        }
    }

    private static Set<Integer> factor(int distance) {
        Set<Integer> factors = new LinkedHashSet<>();
        int i = 2;
        while (i < distance / 2) {
            if (distance % i == 0) {
                factors.add(i);
                factors.add(distance / i);
            }
            i++;
        }
        return factors;
    }

    private static ArrayList<Integer> countOccurrences(String cipherText, String subString) {
        ArrayList<Integer> occurrenceIndexes = new ArrayList<>();
        occurrenceIndexes.add(0);
        //and move a "window" looking for matches to that text
        for (int i = 1; i < (cipherText.length() - subString.length()); i++) {
            //When we find a match, mark the starting index and store into the occurrences array
            if (subString.equalsIgnoreCase(cipherText.substring(i, i + subString.length()))) {
                occurrenceIndexes.add(i);
            }
        }

        return occurrenceIndexes;
    }
}