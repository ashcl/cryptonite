package tools;

import java.util.HashMap;

public class FrequencyAnalysis {

    public static void main(String[] args){
        String cipher = "abcdefghijklzmnopqrstuvwxyz";
        HashMap<String, Integer> monograms = findMonograms(cipher);
        StringBuilder builder = new StringBuilder();
        for (String key :
                monograms.keySet()) {
            builder.append(key + ": " + monograms.get(key) + "\n");
        }
        System.out.println(builder.toString());
    }

    public static HashMap<String, Integer> findMonograms(String cipher){
        HashMap<String, Integer> frequencyChart = new HashMap<>();
        cipher.replace(" ", "");
        String lowerCipher = cipher.toLowerCase();
        for (int i = 0; i < lowerCipher.length(); i++){
            String character = Character.toString(lowerCipher.charAt(i));
            if(frequencyChart.containsKey(character)){
                int frequency = frequencyChart.get(character);
                frequency++;
                frequencyChart.put(character, frequency);
            }else{
                frequencyChart.put(character, 1);
            }
        }
        return frequencyChart;
    }

    public static HashMap<String, Integer> findDigrams(String cipher){
        return null;
    }

    public static HashMap<String, Integer> findTrigrams(String cipher){
        return null;
    }

}
