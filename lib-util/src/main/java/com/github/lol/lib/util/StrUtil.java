package com.github.lol.lib.util;

/**
 * string util
 *
 * @author: jifuwei
 * @create: 2019-07-17 14:41
 **/
public class StrUtil {

    /**
     * copy from commons-lang3
     * just don't want dependency too much third party lib
     *
     * @param cs
     * @return
     */
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
}
