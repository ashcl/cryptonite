package utilities;

public class ColumnarTransposition {

    public static void main(String[] args){

        String test = "This is a test string man";
        String encoded = encode(test, "abcde");
        System.out.println(encoded);
        String decoded = decode(encoded, "abcde");
        System.out.println(decoded);
    }

    public static String encode(String s, int colNum){
        //Since all a columnar transposition is is taking a substring of every nth character, we can reuse some old code.
        StringBuilder builder = new StringBuilder();
        String plaintext = GeneralUtilities.removeForeignChars(s);
        for(int i = 0; i < colNum; i++){
            builder.append(GeneralUtilities.extractSubstring(plaintext, i, colNum));
        }

        return builder.toString();
    }

    public static String decode(String s, int colNum){
        //Take each substring of length s.length()/colNum and read off each character from each substring in order.
        StringBuilder builder = new StringBuilder();
        String cipher = GeneralUtilities.removeForeignChars(s);
        String[] subStrings = new String[colNum];
        int interval = (cipher.length()/colNum);
        //Split the string into several substrings representing the columns
        for (int i = 0; i < colNum; i++){
            subStrings[i] = cipher.substring(i*interval, (i*interval)+interval);
        }

        for (int i = 0; i < cipher.length(); i++){
            //We want to peel of the first character of each sub string every time we go through this loop.
            String subString = subStrings[i % colNum];
            if(subString.length() != 0){
                builder.append(subString.charAt(0));
                subStrings[i%colNum] = subString.substring(1);
            }
        }

        return builder.toString();
    }

    public static String encode(String s, String key){
        //Since all a columnar transposition is is taking a substring of every nth character, we can reuse some old code.
        StringBuilder builder = new StringBuilder();
        String trimmedPlain = GeneralUtilities.removeForeignChars(s);

        String[] subStrings = new String[key.length()];

        for (int i = 0; i < key.length(); i++) {
            subStrings[i] = GeneralUtilities.extractSubstring(trimmedPlain, i, key.length());
        }

        int[] indexArray = GeneralUtilities.obtainIndexKeyArray(key, true);

        for(int i = 0; i < key.length(); i++){
            //Add each string into the cipher in key order
            builder.append(subStrings[indexArray[i]]);
        }

        return builder.toString();
    }

    public static String decode(String s, String key){
        //Take each substring of length s.length()/colNum and read off each character from each substring in order.
        StringBuilder builder = new StringBuilder();
        String trimmedCipher = GeneralUtilities.removeForeignChars(s);

        String[] subStrings = new String[key.length()];
        int interval = (trimmedCipher.length()/key.length());
        //Split the string into several substrings representing the columns
        for (int i = 0; i < key.length(); i++){
            subStrings[i] = trimmedCipher.substring(i*interval, (i*interval)+interval);
        }

        int[] indexArray = GeneralUtilities.obtainIndexKeyArray(key, false);

        for (int i = 0; i < trimmedCipher.length(); i++){
            //We want to peel of the first character of each sub string every time we go through this loop.
            String subString;
            if (i == 1){
                 subString = subStrings[indexArray[i]];
            }else{
                subString = subStrings[indexArray[i%key.length()]];
            }

            if(subString.length() != 0){
                builder.append(subString.charAt(0));
                try {
                    subStrings[indexArray[i % key.length()]] = subString.substring(1);
                }catch (IndexOutOfBoundsException e){
                    //Ignore it
                }
            } else {
                i = trimmedCipher.length();
            }
        }

        return builder.toString();
    }
}
