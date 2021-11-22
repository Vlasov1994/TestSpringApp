package com.example.testspringapp.util;

import java.lang.reflect.ParameterizedType;

public abstract class Util {

    public static Class<?> getGenericParameterClass(Class<?> actualClass, int parameterIndex) {
        return (Class<?>) ((ParameterizedType) actualClass.getGenericSuperclass()).getActualTypeArguments()[parameterIndex];
    }
}
