package ru.dkrash.serialize.encoders;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public abstract class AbstractStandardTypesEncoder implements Encoder {
    public String serialize(Object anyPrimitive) {
        return String.valueOf(anyPrimitive);
    }

    public abstract Object deserialize(String data) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ParseException;
}
