package com.github.lol.lib.util;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Serialize util
 *
 * @author: jifuwei
 * @create: 2019-07-17 16:58
 **/
public class SerializeUtil {

    /**
     * map to object
     *
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    @SneakyThrows
    public static <T> T mapToObject(Map map, Class<T> beanClass) {
        if (Objects.isNull(map) || map.isEmpty()) {
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

    public static <T, U> Map<T, U> objectToMapNullRemove(Object source, Class<T> keyClass, Class<U> valClass) {
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
    public static Map<String, Object> objectToMap(Object source) {
        if (Objects.isNull(source)) {
            return null;
        }

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

    private static List<Class> getAllClazz(Class clazz) {
        List<Class> clazzList = new ArrayList<>();
        do {
            clazzList.add(clazz);
            clazz = clazz.getSuperclass();
        } while (clazz != null && clazz != Object.class);

        return clazzList;
    }

    private static void removeNullKey(Map map) {
        Set set = map.keySet();
        for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            Object obj = iterator.next();
            remove(obj, iterator);
        }
    }

    public static void removeNullValue(Map map) {
        Set set = map.keySet();
        for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            Object obj = iterator.next();
            Object value = map.get(obj);
            remove(value, iterator);
        }
    }

    private static void remove(Object obj, Iterator iterator) {
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
