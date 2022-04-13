package util;

import java.util.Random;

public class GenerationDataForTest {

    private static final String DICT = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random random = new Random();

    public static String generateNameLoginPassMail() {
        int length = 10;
        StringBuilder sb = new StringBuilder();

        for (; length > 0; --length)
            sb.append(DICT.charAt(random.nextInt(DICT.length())));
        return sb.toString();
    }

    public static int generateRandomIntValue() {
        Random random = new Random();
        return random.nextInt(3);
    }

    public static boolean checkValue(){
        return generateRandomIntValue()%2==0;
    }


}
