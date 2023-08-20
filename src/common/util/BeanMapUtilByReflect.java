package common.util;

import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class BeanMapUtilByReflect {



/**
 * 对象转Map
 * @param object
 * @return
 * @throws IllegalAccessException
 */
    public static Map beanToMap(Object object) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
        field.setAccessible(true);
        map.put(field.getName(), field.get(object));
        }
        return map;
        }

        public static Map<String, Object> JavaBean2Map(Object obj) {
            Map<String, Object> map = new LinkedHashMap<>();

            try {
                Class bean = obj.getClass();
                Field[] fields = bean.getDeclaredFields();
                for (Field field : fields) {
                    String key = field.getName();
                    Method method = bean.getDeclaredMethod("get" + upperFirstLatter(key));
                    Object value = method.invoke(obj);
                    map.put(key, value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return map;
        }
    /**
     * Map转对象
     * @param map, object
     * @return
     * @throws IllegalAccessException
     */
    public static Object Map2JavaBean(Map<String, Object> map, Object obj) {
        try {
            Class bean = obj.getClass();
            Field[] fields = bean.getDeclaredFields();
            for (Field field : fields) {
                String key = field.getName();
                Class type = field.getType();
                Method method = bean.getDeclaredMethod("set" + upperFirstLatter(key), type);
                Object value = method.invoke(obj, map.get(key));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return obj;
    }

    private static String upperFirstLatter(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

}