package by.bntu.fitr.povt.model;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator {
    private static final String string= "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
    private static SecureRandom random = new SecureRandom();

    public static String generate(int len)
    {
        StringBuilder stringBuilder = new StringBuilder();
        int lenSt = string.length()-1;
        for(int i=0;i<len;i++)
        {


            stringBuilder.append(string.charAt(random.nextInt(lenSt)));
        }
        return stringBuilder.toString();
    }
}
