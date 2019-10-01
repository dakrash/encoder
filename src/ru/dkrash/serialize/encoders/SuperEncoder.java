package ru.dkrash.serialize.encoders;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public interface SuperEncoder {
    byte[] serialize(Object anyBean) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

    Object deserialize(byte[] data) throws NoSuchMethodException, ParseException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException;
}
