package com.github.lol.lib.util;

/**
 * validate util
 *
 * @author: jifuwei
 * @create: 2019-07-17 14:48
 **/
public class ValidUtil {

    private static final String DEFAULT_NOT_EMPTY_CHAR_SEQUENCE_EX_MESSAGE =
            "The validated character sequence is empty";
    private static final String DEFAULT_IS_TRUE_EX_MESSAGE = "The validated expression is false";

    public static void isTrue(final boolean expression, final String message,
                              final Object... values) {
        if (!expression) {
            throw new IllegalArgumentException(String.format(message, values));
        }
    }

    public static void isTrue(final boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException(DEFAULT_IS_TRUE_EX_MESSAGE);
        }
    }

    public static <T extends CharSequence> T notEmpty(final T chars) {
        return notEmpty(chars, DEFAULT_NOT_EMPTY_CHAR_SEQUENCE_EX_MESSAGE);
    }

    public static <T extends CharSequence> T notEmpty(final T chars, final String message,
                                                      final Object... values) {
        if (chars == null) {
            throw new NullPointerException(String.format(message, values));
        }
        if (chars.length() == 0) {
            throw new IllegalArgumentException(String.format(message, values));
        }
        return chars;
    }
}
