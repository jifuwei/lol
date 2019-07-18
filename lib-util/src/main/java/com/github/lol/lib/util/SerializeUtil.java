package com.github.lol.lib.util;

import lombok.NonNull;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Serialize util
 *
 * @author: jifuwei
 * @create: 2019-07-17 16:58
 **/
public class SerializeUtil {

    public final static String EQUAL = "=";
    public final static String AMPERSAND = "&";

    /**
     * map to object
     *
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    @SneakyThrows
    public static <T> T mapToObject(@NonNull Map map, @NonNull Class<T> beanClass) {
        if (map.isEmpty()) {
            return null;
        }

        T obj = beanClass.newInstance();
        // get all super class
        Class clazz = obj.getClass();
        List<Class> clazzList = getAllClazz(clazz);

        for (Class aClass : clazzList) {
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }

                field.setAccessible(true);
                field.set(obj, map.get(field.getName()));
            }
        }

        return obj;
    }

    /**
     * object to map & remove val = null
     *
     * @param source
     * @param keyClass
     * @param valClass
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> Map<T, U> objectToMapNullRemove(@NonNull Object source,
                                                         @NonNull Class<T> keyClass, @NonNull Class<U> valClass) {
        Map target = objectToMap(source);
        if (Objects.isNull(target)) {
            return null;
        }

        removeNullKey(target);
        removeNullValue(target);

        return target;
    }

    /**
     * object to map
     *
     * @param source
     * @return
     * @throws Exception
     */
    @SneakyThrows
    public static Map<String, Object> objectToMap(@NonNull Object source) {
        // get all super class
        Class clazz = source.getClass();
        List<Class> clazzList = getAllClazz(clazz);

        Map<String, Object> map = new HashMap<>();

        for (Class aClass : clazzList) {
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field field : declaredFields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                map.put(field.getName(), field.get(source));
            }
        }

        return map;
    }

    /**
     * convert map to k=v&k=v seq order by key's ASCII num ASC
     *
     * @param sourceMap
     * @return
     */
    public static String map2KVStr(@NonNull Map<String, String> sourceMap, Set<String> removeKeySet) {
        if (sourceMap.isEmpty()) {
            return null;
        }

        return new TreeMap<>(sourceMap).entrySet()
                .stream()
                .filter(e -> Objects.isNull(removeKeySet) || !removeKeySet.contains(e.getKey()))
                .map(e -> e.getKey() + EQUAL + e.getValue())
                .collect(Collectors.joining(AMPERSAND));
    }

    private static List<Class> getAllClazz(@NonNull Class clazz) {
        List<Class> clazzList = new ArrayList<>();
        do {
            clazzList.add(clazz);
            clazz = clazz.getSuperclass();
        } while (clazz != null && clazz != Object.class);

        return clazzList;
    }

    private static void removeNullKey(@NonNull Map map) {
        Set set = map.keySet();
        for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            Object obj = iterator.next();
            remove(obj, iterator);
        }
    }

    public static void removeNullValue(@NonNull Map map) {
        Set set = map.keySet();
        for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            Object obj = iterator.next();
            Object value = map.get(obj);
            remove(value, iterator);
        }
    }

    private static void remove(Object obj, @NonNull Iterator iterator) {
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.trim().isEmpty()) {
                iterator.remove();
            }
        } else if (obj instanceof Collection) {
            Collection col = (Collection) obj;
            if (col.isEmpty()) {
                iterator.remove();
            }

        } else if (obj instanceof Map) {
            Map temp = (Map) obj;
            if (temp.isEmpty()) {
                iterator.remove();
            }

        } else if (obj instanceof Object[]) {
            Object[] array = (Object[]) obj;
            if (array.length <= 0) {
                iterator.remove();
            }
        } else {
            if (obj == null) {
                iterator.remove();
            }
        }
    }
}
