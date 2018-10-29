package com.cristik.code.util;

import com.cristik.code.convert.Style;

/**
 * @author cristik
 */
public class StringUtil {

    private static final int START = 'A';

    private static final int END = 'Z';


    public static String convertByStyle(String str, Style style) {
        switch (style) {
            case camel:
                return toCamelCase(str);
            case capitalizeCamel:
                return toCapitalizeCamelCase(str);
            case underline:
                return toUnderScoreCase(str);
            case uppercase:
                return str.toUpperCase();
            case lowercase:
                return str.toLowerCase();
            case underlineUppercase:
                return toUnderScoreCase(str).toUpperCase();
            case firstcapitalize:
                return toFirstLowerCase(str);
            default:
                return str;
        }
    }

    private static String toFirstLowerCase(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        } else {
            char[] cs = str.toCharArray();
            if (cs[0] >= START && cs[0] <= END) {
                cs[0] += 32;
            }
            return String.valueOf(cs);
        }
    }

    /**
     * 驼峰首字母小写
     */
    public static String toCamelCase(String str) {
        StringBuilder builder = new StringBuilder();
        if (str == null || str.isEmpty()) {
            return "";
        } else if (!str.contains("_")) {
            return str.toLowerCase();
        }
        String camels[] = str.split("_");
        for (String camel : camels) {
            if (camel.isEmpty()) {
                continue;
            }
            if (builder.length() == 0) {
                builder.append(camel.toLowerCase());
            } else {
                builder.append(camel.substring(0, 1).toUpperCase());
                builder.append(camel.substring(1).toLowerCase());
            }
        }
        return builder.toString();
    }

    /**
     * 驼峰首字母大写
     */
    public static String toCapitalizeCamelCase(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        str = toCamelCase(str);
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 驼峰转下划线
     */
    public static String toUnderScoreCase(String s) {
        final int size;
        final char[] chars;
        final StringBuilder sb = new StringBuilder(
                (size = (chars = s.toCharArray()).length) * 3 / 2 + 1);
        char c;
        for (int i = 0; i < size; i++) {
            c = chars[i];
            if (isUppercaseAlpha(c)) {
                sb.append('_').append(toLowerAscii(c));
            } else {
                sb.append(toUpperAscii(c));
            }
        }
        return sb.charAt(0) == '_' ? sb.substring(1) : sb.toString();
    }

    public static boolean isUppercaseAlpha(char c) {
        return (c >= 'A') && (c <= 'Z');
    }

    public static boolean isLowercaseAlpha(char c) {
        return (c >= 'a') && (c <= 'z');
    }

    public static char toUpperAscii(char c) {
        if (isUppercaseAlpha(c)) {
            c -= (char) 0x20;
        }
        return c;
    }

    public static char toLowerAscii(char c) {
        if (isUppercaseAlpha(c)) {
            c += (char) 0x20;
        }
        return c;
    }
}
