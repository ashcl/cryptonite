package utilities;

import java.util.Scanner;


class Vigenere {

    public static String encrypt(String text, final String key)
    {
        String res = "";
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z')
                continue;
            res += (char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
            j = ++j % key.length();
        }
        return res;
    }

    public static String decrypt(String text, final String key)
    {
        String res = "";
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z')
                continue;
            res += (char) ((c - key.charAt(j) + 26) % 26 + 'A');
            j = ++j % key.length();
        }
        return res;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter a multi-word message with punctuation:");
        String message = in.nextLine();
        System.out.println("Enter a single word key with no punctuation:");
        String key = in.nextLine();
        String encodedMsg = encrypt(message, key);
        System.out.println("The encoded message is:");
        System.out.println(encodedMsg);
        String decodedMsg = decrypt(encodedMsg, key);
        System.out.println("The decoded message is:");
        System.out.println(decodedMsg);

    }

}