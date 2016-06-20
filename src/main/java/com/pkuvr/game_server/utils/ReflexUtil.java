package com.pkuvr.game_server.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射工具类
 *
 * @author EvilHades
 */
public class ReflexUtil {

    /**
     * 执行方法
     *
     * @param obj
     * @param method
     * @param parameterTypes
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static void invokeMethod(Object obj, String methodName, boolean parameter)
            throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {

        Class<? extends Object> clazz = obj.getClass();
        Method method = clazz.getMethod(methodName, boolean.class);
        method.invoke(obj, parameter);
    }

}
