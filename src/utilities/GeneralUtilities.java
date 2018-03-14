package utilities;

public class GeneralUtilities {

    /**
     * Pull out every (@param)length'th character starting at (@param)index and put it into a string
     * @param s
     * @param index
     * @param length
     * @return
     */
    public static String extractSubstring(String s, int index, int length){
        StringBuilder builder = new StringBuilder();
        for(int i = index; i < s.length(); i = i+length){
            builder.append(s.charAt(i));
        }

        return builder.toString();
    }

}
