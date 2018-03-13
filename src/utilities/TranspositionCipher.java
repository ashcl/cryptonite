package utilities;

public class TranspositionCipher {

    public static void main(String[] args){

    }

    private static String transpose(String original, String key){
        String stringInEdit = original.replace(" ", "");
        stringInEdit = stringInEdit.toLowerCase();
        char[] originalChars = stringInEdit.toCharArray();
        char[] keyChars = key.toCharArray();



        return null;
    }

    private static void swapChars(char[] array, int firstIndex, int secondIndex){
        char temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }
}
