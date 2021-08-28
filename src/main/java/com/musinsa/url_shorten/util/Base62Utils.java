package com.musinsa.url_shorten.util;

public class Base62Utils {
    private Base62Utils() { }

    private static final char[] BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();

    public static String encoding(int value) {
        final StringBuilder stringBuilder = new StringBuilder();
        do {
            int i = value % 62;
            stringBuilder.append(BASE62[i]);
            value /= 62;
        } while (value > 0);
        return stringBuilder.toString();
    }

    public static int decoding(String value) {
        int result = 0;
        int power = 1;
        for (int i = 0; i < value.length(); i++) {
            int digit = new String(BASE62).indexOf(value.charAt(i));
            result += digit * power;
            power *= 62;
        }
        return result;
    }
}
