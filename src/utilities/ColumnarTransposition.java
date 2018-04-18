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

        String transposedEncoded = "stsni ihcuc vtesx epbuc riioa niase sopnh ucrsl hfele  \n" +
                "tdoeu fnbsh niaag rooso cosio tisir wlmdf iiiae aheei  \n" +
                "brosr \n" +
                "rhmcr qniie bwteq ueamd elmut nebwc nasnd webei  \n" +
                "gqyet othup tesnv hoitu tasnr ntpie weeet ienit aouoe  \n" +
                "httdv ghchr saobu eptir eqasn tmdot issun orpis iohmd  \n" +
                "amroo ienfr ciech xbigq ynlrs ecsax afroo giest otbsh  \n" +
                "sthhe rlpfc ntcaa datup anrma odres ehqro \n" +
                "ofoeh rnsal  \n" +
                "ohoms etipo ehutp iohko hahrn pemwi ecite erlkl ecotp  \n" +
                "iamhn idoku tbutc uauyr fiian natss isoey linon nlpqt  \n" +
                "piocn teheh aitfa alsti cntlr stvta stlgf eitys hrnax  \n" +
                "totan pteee stnmt asnnr afutn eqpcl eibwr oosni tcrcd  \n" +
                "lerar oapis";

        System.out.println(decode(transposedEncoded, "edcba"));
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
            String subString = subStrings[indexArray[i%key.length()]];
            if(subString.length() != 0){
                builder.append(subString.charAt(0));
                subStrings[indexArray[i%key.length()]] = subString.substring(1);
            } else {
                i = trimmedCipher.length();
            }
        }

        return builder.toString();
    }
}
