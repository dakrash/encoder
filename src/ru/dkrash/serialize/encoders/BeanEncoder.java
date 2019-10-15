package ru.dkrash.serialize.encoders;

import ru.dkrash.serialize.EncoderProxy;
import ru.dkrash.serialize.ObjectFactory;
import ru.dkrash.serialize.ObjectStorage;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.*;
import java.text.ParseException;

public class BeanEncoder implements Encoder {
    @Override
    public String serialize(Object any) throws IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchMethodException, InvocationTargetException, UnsupportedEncodingException {
        ObjectStorage.addObject(any);
        StringBuilder stringResult = new StringBuilder("{[");
        Field[] fields = any.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            stringResult.append("{field ");
            Field field = fields[i];
            field.setAccessible(true);
            String name = field.getName();
            Object value = field.get(any);
            stringResult.append(name);
            stringResult.append(":");
            stringResult.append(EncoderProxy.el.serialize(value));
            stringResult.append("}");
            if (i < fields.length - 1) {
                stringResult.append(", ");
            }
        }
        stringResult.append("]}");
        return String.valueOf(stringResult);
    }

    @Override
    public Object deserialize(String data) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, ParseException, InvocationTargetException {
        String className = this.getTypeField(data);

        Object obj = ObjectFactory.create(className);
        data = data.substring(data.indexOf("[") + 1, data.lastIndexOf("]"));
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            String nameField = "{" + Encoder.FIELD_SEPARATOR + name + ":";
            String substr = data;
            if (data.indexOf(nameField) != 0) {
                substr = data.substring(data.indexOf(nameField) - 1);
                if (substr.charAt(0) == '[') {
                    while (substr.charAt(0) == '[') {
                        substr = substr.substring(2);
                        substr = substr.substring(substr.indexOf(nameField) - 2);
                    }
                    substr = substr.substring(1);
                }
            }
            substr = substr.substring(nameField.length() + 1);
            String objectStr;
            if (substr.contains("}, {" + Encoder.FIELD_SEPARATOR)) {
                objectStr = substr.substring(0, substr.indexOf("}, {" + Encoder.FIELD_SEPARATOR));
            } else {
                objectStr = substr.substring(0, substr.lastIndexOf("}"));
            }
            Object objForAdd;
            objForAdd = EncoderProxy.el.deserialize(objectStr);
            field.set(obj, objForAdd);
        }
        return obj;
    }
}
