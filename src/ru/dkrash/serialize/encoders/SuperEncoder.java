package ru.dkrash.serialize.encoders;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public interface SuperEncoder {
    byte[] serialize(Object anyBean) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, UnsupportedEncodingException;

    Object deserialize(byte[] data) throws NoSuchMethodException, ParseException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException;
}
