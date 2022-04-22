package util;

import java.util.Random;

public class RandomGeneration {
    private static final String DICT = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random random = new Random();

    public static String generate() {
        int length = 9;
        StringBuilder sb = new StringBuilder();

        for ( ; length > 0; --length )
            sb.append(DICT.charAt(random.nextInt(DICT.length())));

        return sb.toString();
    }
}
