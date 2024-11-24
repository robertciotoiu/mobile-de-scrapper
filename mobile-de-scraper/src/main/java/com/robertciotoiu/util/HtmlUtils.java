package com.robertciotoiu.util;

public class HtmlUtils {
    public static String replaceSpecialBlankLines(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll("\\u00a0", " ").trim();
    }

    public static String replaceBackslash(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll("\\u002F", "/").trim();
    }
}