package utilities;

public class ColumnarTransposition {

    public static void main(String[] args){
        String test = "Thisisateststringman";
        String encoded = encode(test, 5);
        System.out.println(encoded);
        String decoded = decode(encoded, 5);
        System.out.println(decoded);
    }

    public static String encode(String s, int colNum){
        //Since all a columnar transposition is is taking a substring of every nth character, we can reuse some old code.
        StringBuilder builder = new StringBuilder();
        s = s.replace(" ", "");
        for(int i = 0; i < colNum; i++){
            builder.append(GeneralUtilities.extractSubstring(s, i, colNum));
        }

        return builder.toString();
    }

    public static String decode(String s, int colNum){
        //Take each substring of length s.length()/colNum and read off each character from each substring in order.
        StringBuilder builder = new StringBuilder();
        String[] subStrings = new String[colNum];
        int interval = (s.length()/colNum);
        for (int i = 0; i < colNum; i++){
            subStrings[i] = s.substring(i*interval, (i*interval)+interval);
        }

        for (int i = 0; i < s.length(); i++){
            //We want to peel of the first character of each sub string every time we go through this loop.
            String subString = subStrings[i % colNum];
            builder.append(subString.charAt(0));
            subStrings[i%colNum] = subString.substring(1);
        }

        return builder.toString();
    }

    public static String encode(String s, String key){
        return null;
    }

    public static String decode(String s, String key){
        return null;
    }
}
