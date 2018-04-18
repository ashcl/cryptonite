package tools;

import utilities.GeneralUtilities;

import java.util.HashMap;

public class FrequencyAnalysis {

    public static void main(String[] args){
        String cipher = "abcdefghijkylzmnopqrstuvwxylz";
        HashMap<String, Integer> monograms = findMonograms(cipher);
        StringBuilder builder = new StringBuilder();
        for (String key :
                monograms.keySet()) {
            builder.append(key + ": " + monograms.get(key) + "\n");
        }
        System.out.println(builder.toString());

        HashMap<String, Integer> digrams = findDigrams(cipher);
        builder = new StringBuilder();
        for (String key :
                digrams.keySet()) {
            builder.append(key + ": " + digrams.get(key) + "\n");
        }
        System.out.println(builder.toString());

        HashMap<String, Integer> trigrams = findTrigrams(cipher);
        builder = new StringBuilder();
        for (String key :
                trigrams.keySet()) {
            builder.append(key + ": " + trigrams.get(key) + "\n");
        }
        System.out.println(builder.toString());
    }

    public static HashMap<String, Integer> findMonograms(String cipher){
        HashMap<String, Integer> frequencyChart = initializeAlphabetMap();
        String cipherText = GeneralUtilities.removeForeignChars(cipher);
        String lowerCipher = cipherText.toLowerCase();
        for (int i = 0; i < lowerCipher.length(); i++){
            String character = Character.toString(lowerCipher.charAt(i));
            updateMap(frequencyChart, character);
        }
        return frequencyChart;
    }

    public static HashMap<String, Integer> findDigrams(String cipher){
        HashMap<String, Integer> frequencyChart = new HashMap<>();
        String cipherText = GeneralUtilities.removeForeignChars(cipher);
        String lowerCipher = cipherText.toLowerCase();
        for (int i = 0; i < lowerCipher.length()-1; i++){
            String character = lowerCipher.substring(i, i+2);
            updateMap(frequencyChart, character);
        }
        return frequencyChart;
    }

    public static HashMap<String, Integer> findTrigrams(String cipher){
        HashMap<String, Integer> frequencyChart = new HashMap<>();
        String cipherText = GeneralUtilities.removeForeignChars(cipher);
        String lowerCipher = cipherText.toLowerCase();
        for (int i = 0; i < lowerCipher.length()-2; i++){
            String character = lowerCipher.substring(i, i+3);
            updateMap(frequencyChart, character);
        }
        return frequencyChart;
    }

    private static void updateMap(HashMap<String, Integer> frequencyChart, String character) {
        if(frequencyChart.containsKey(character)){
            int frequency = frequencyChart.get(character);
            frequency++;
            frequencyChart.put(character, frequency);
        }else{
            frequencyChart.put(character, 1);
        }
    }

    private static HashMap<String, Integer> initializeAlphabetMap(){
        HashMap<String, Integer> initMap = new HashMap<>();

        for (Character c : "abcdefghijklmnopqrstuvwxyz".toCharArray()){
            initMap.put(String.valueOf(c), 0);
        }

        return initMap;
    }

}
