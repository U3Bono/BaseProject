package com.example.baseproject.utils;

import java.lang.reflect.Field;
import java.util.*;


public class FieldUtil {

    public static Map<String, Object> getEntity(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        Map<String, Object> map = new LinkedHashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (!field.getName().equals("serialVersionUID"))
                    map.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public static void setEntity(Object obj, Map<String, Object> map) {
        for (String key : map.keySet()) {
            Field field = null;
            try {
                field = obj.getClass().getDeclaredField(key);
                field.setAccessible(true);
                Class<?> type = field.getType();
                String value = String.valueOf(map.get(key));
                if (String.valueOf(type).equals("int")) {//判断类型
                    if (value.length() > 9)//int类型最多9位
                        continue;
                    field.set(obj, Integer.parseInt(value));
                }
                if (String.class.equals(type)) {
                    field.set(obj, value);
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        }
    }

    public static List<Class<?>> entityType(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        List<Class<?>> typeList = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            typeList.add(field.getType());
        }
        return typeList;
    }

    public static Class<?> varType(Object obj, Object value) {
        if (value == null)
            return null;
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.get(obj) == value) {
                    return field.getType();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String varName(Object obj, Object value) {
        if (value == null)
            return null;
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (String.valueOf(field.get(obj)).equals(String.valueOf(value))) {
                    return field.getName();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
