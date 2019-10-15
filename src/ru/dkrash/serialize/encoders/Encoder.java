package ru.dkrash.serialize.encoders;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public interface Encoder {
    String FIELD_SEPARATOR = "field ";
    String TYPE_SEPARATOR = "type : ";
    String VALUE_SEPARATOR = "value : ";

    public default String getTypeField(String data) {
        return data.substring(data.indexOf(Encoder.TYPE_SEPARATOR) + Encoder.TYPE_SEPARATOR.length(), data.indexOf(","));
    }

    public default String getObjectStr(String data) {
        return data.substring(data.indexOf(Encoder.VALUE_SEPARATOR) + Encoder.VALUE_SEPARATOR.length(), data.lastIndexOf("}"));
    }

    String serialize(Object any) throws ClassNotFoundException, InvocationTargetException, InstantiationException, NoSuchMethodException, IllegalAccessException, UnsupportedEncodingException;

    Object deserialize(String data) throws ClassNotFoundException, InvocationTargetException, InstantiationException, NoSuchMethodException, IllegalAccessException, ParseException;
}
