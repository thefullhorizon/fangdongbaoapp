package com.hx.fdb.util;

import com.huoqiu.framework.util.LogUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Created by yanxin on 17/4/8.
 */

public class MapUtil {

    /**
     * 将一个 JavaBean 对象转化为一个  Map
     *
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的  Map 对象
     * @throws IllegalAccessException    如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    public static HashMap<String, Object> convertBean(Object bean) throws IllegalAccessException {
        Class beanClass = bean.getClass();
        HashMap<String, Object> returnMap = new HashMap();

        Field[] fieldsPublic = beanClass.getFields();
        if (fieldsPublic != null && fieldsPublic.length > 0) {
            for (int i = 0; i < fieldsPublic.length; i++) {
                Field field = fieldsPublic[i];
                Object obj = field.get(bean);
                if(obj != null && isCommonParam(field)) {
                    returnMap.put(field.getName(),obj);
                }
            }
        }

        Field[] fields = beanClass.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                if(!returnMap.containsKey(field.getName())) {
                    Object obj = field.get(bean);
                    if(obj != null && isCommonParam(field)) {
                        returnMap.put(field.getName(),obj);
                    }
                }
            }
        }
        return returnMap;
    }

    public static boolean isCommonParam(Field field) {
        if(field == null) {
            return false;
        }

        String className = field.getType().getName();
        LogUtil.e("FIELD TYPE",className);
        if(className.equalsIgnoreCase("int")
                || className.equalsIgnoreCase("double")
                || className.equalsIgnoreCase("long")
                || className.equalsIgnoreCase("float")
                || className.equalsIgnoreCase("java.lang.string")
                || className.equalsIgnoreCase("integer")) {
            return true;
        }

        return false;
    }
}