package com.github.lol.lib.util.annotation;

import java.lang.annotation.*;

/**
 * not empty
 *
 * @author: jifuwei
 * @create: 2019-07-23 14:29
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotEmpty {
}
