package utilities;

/**
 * Created by bradenbuchanan on 2/15/18.
 */
import java.util.Scanner;

import static utilities.GeneralUtilities.ALPHABET;

public class CaesarCipher
{


    public static String encrypt(String plainText, int shiftKey)
    {
        String trimmedText = plainText.toLowerCase();
        trimmedText = GeneralUtilities.removeForeignChars(trimmedText);

        String cipherText = "";
        for (int i = 0; i < trimmedText.length(); i++)
        {
            int charPosition = ALPHABET.indexOf(trimmedText.charAt(i));
            int keyVal = (shiftKey + charPosition) % 26;
            char replaceVal = ALPHABET.charAt(keyVal);
            cipherText += replaceVal;
        }
        return cipherText;
    }

    public static String decrypt(String cipherText, int shiftKey)
    {
        String trimmedText = cipherText.toLowerCase();
        trimmedText = GeneralUtilities.removeForeignChars(trimmedText);

        String plainText = "";
        for (int i = 0; i < trimmedText.length(); i++)
        {
            int charPosition = ALPHABET.indexOf(trimmedText.charAt(i));
            int keyVal = (charPosition - shiftKey) % 26;
            if (keyVal < 0)
            {
                keyVal = ALPHABET.length() + keyVal;
            }
            char replaceVal = ALPHABET.charAt(keyVal);
            plainText += replaceVal;
        }
        return plainText;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the String for Encryption: ");
        String message;
        message = sc.next();
        System.out.println(encrypt(message, 3));
        System.out.println(decrypt(encrypt(message, 3), 3));
        sc.close();
    }
}