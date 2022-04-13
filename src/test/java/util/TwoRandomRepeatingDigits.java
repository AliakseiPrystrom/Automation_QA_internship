package util;

public class TwoRandomRepeatingDigits {

    public static boolean getCompareResult(int id) {
        String string = Integer.toString(id);
        char[] chr = string.toCharArray();
        String v1 = Character.toString(chr[0]);
        String v2 = Character.toString(chr[1]);
        int compareValue_1 = Integer.parseInt(v1);
        int compareValue_2 = Integer.parseInt(v2);
        return compareValue_1 == compareValue_2;
    }
}
