package com.github.lol.lib.util;

import com.github.lol.lib.util.annotation.NotEmpty;
import lombok.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * reflect util
 *
 * @author: jifuwei
 * @create: 2019-07-23 11:57
 **/
public class ReflectUtil {

    /**
     * 检测@NotEmpty字段是否不为空
     *
     * @param source
     */
    public static void validateNotNullField(@NonNull Object source) {
        List<Class> clazzList = getAllClazz(source.getClass());
        clazzList.stream()
                .flatMap(c -> Arrays.stream(c.getDeclaredFields()))
                .filter(field -> field.isAnnotationPresent(NotEmpty.class))
                .forEach(field -> {
                    if (Objects.isNull(getFieldVal(field, source))) {
                        throw new RuntimeException("illegal Object check, Field: [" + field.getName() +
                                "] marked @NotEmpty");
                    }
                });
    }

    public static List<Class> getAllClazz(@NonNull Class clazz) {
        List<Class> clazzList = new ArrayList<>();
        do {
            clazzList.add(clazz);
            clazz = clazz.getSuperclass();
        } while (clazz != null && clazz != Object.class);

        return clazzList;
    }

    @SneakyThrows
    public static Object getFieldVal(@NonNull Field field, @NonNull Object source) {
        field.setAccessible(true);
        return field.get(source);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class TestParent {
        @NotEmpty
        private String id;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    static class TestObject extends TestParent {
        private String name;
        @NotEmpty
        private String age;
        private String sex;
    }

    public static void main(String[] args) {
        TestObject object1 = new TestObject("lisi", "15", "1");
        object1.setId("1");
        validateNotNullField(object1);
        TestObject object2 = new TestObject("lisi", null, "1");
        validateNotNullField(object2);
    }
}
