package utilities;

public class ColumnarTransposition {

    public static void main(String[] args){
        String testEncoded = "tpioc ntehe haitf aalst icntl rstvt astlg feity shrna " +
                "xtota nptee estnm tasnn rafut neqpc leibw roosn itcrc " +
                "dlera roapi sroof oehrn saloh omset ipoeh utpio hkoha " +
                "hrnpe mwiec iteer lklec otpia mhnid okutb utcua uyrfi " +
                "ianna tssis oeyli nonnl pqasn tmdot issun orpis iohmd " +
                "amroo ienfr ciech xbigq ynlrs ecsax afroo giest otbsh " +
                "sthhe rlpfc ntcaa datup anrma odres ehqni iebwt equea " +
                "mdelm utneb wcnas ndweb eigqy etoth uptes nvhoi tutas " +
                "nrntp iewee etien itaou oehtt dvghc hrsao buept ireqs " +
                "tsnii hcucv tesxe pbucr iioan iases opnhu crslh felet " +
                "doeuf nbshn iaagr oosoc osiot isirw lmdfi iiaea heeib " +
                "rosrr hmcrq";
        System.out.println(decode(testEncoded, 5));

        String test = "This is a test string man";
        String encoded = encode(test, 5);
        System.out.println(encoded);
        String decoded = decode(encoded, 5);
        System.out.println(decoded);
    }

    public static String encode(String s, int colNum){
        //Since all a columnar transposition is is taking a substring of every nth character, we can reuse some old code.
        StringBuilder builder = new StringBuilder();
        String plaintext = GeneralUtilities.removeWhitespace(s);
        for(int i = 0; i < colNum; i++){
            builder.append(GeneralUtilities.extractSubstring(plaintext, i, colNum));
        }

        return builder.toString();
    }

    public static String decode(String s, int colNum){
        //Take each substring of length s.length()/colNum and read off each character from each substring in order.
        StringBuilder builder = new StringBuilder();
        String cipher = GeneralUtilities.removeWhitespace(s);
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
        s = s.replace(" ", "");
        for(int i = 0; i < key.length(); i++){
            builder.append(GeneralUtilities.extractSubstring(s, i, key.length()));
        }

        return builder.toString();
    }

    public static String decode(String s, String key){
        //Take each substring of length s.length()/colNum and read off each character from each substring in order.
        StringBuilder builder = new StringBuilder();
        s = s.replace(" ", "");
        String[] subStrings = new String[key.length()];
        int interval = (s.length()/key.length());
        //Split the string into several substrings representing the columns
        for (int i = 0; i < key.length(); i++){
            subStrings[i] = s.substring(i*interval, (i*interval)+interval);
        }

        for (int i = 0; i < s.length(); i++){
            //We want to peel of the first character of each sub string every time we go through this loop.
            String subString = subStrings[i % key.length()];
            if(subString.length() != 0){
                builder.append(subString.charAt(0));
                subStrings[i%key.length()] = subString.substring(1);
            } else {
                i = s.length();
            }
        }

        return builder.toString();
    }
}
