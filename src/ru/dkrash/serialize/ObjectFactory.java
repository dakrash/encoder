package ru.dkrash.serialize;

import ru.dkrash.serialize.exeptions.ThereIsConstructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ObjectFactory {
    public static Object create(String className) throws IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        Constructor[] constructors = Class.forName(className).getDeclaredConstructors();
        Constructor constructor = null;
        for (Constructor constr : constructors) {
            Class[] paramTypes = constr.getParameterTypes();
            if (paramTypes.length == 0) {
                constructor = constr;
            }
        }
        if (constructor == null) {
            throw new ThereIsConstructor("Cannot serialize object with constructor");
        }
        constructor.setAccessible(true);
        return constructor.newInstance(null);
    }
}
