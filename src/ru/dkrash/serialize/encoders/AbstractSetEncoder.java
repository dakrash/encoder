package ru.dkrash.serialize.encoders;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public abstract class AbstractSetEncoder implements Encoder {
    public Object obj;

    public abstract void refreshObj(String objStr) throws IllegalAccessException, InvocationTargetException, ParseException, InstantiationException, NoSuchMethodException, ClassNotFoundException;

    public abstract void setObj(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException;

    void refreshResultObj(String data) throws IllegalAccessException, ParseException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        String typeField = this.getTypeField(data);
        String objectString = this.getObjectStr(data);
        this.setObj(typeField);
        while (objectString.contains("}")) {
            String component;
            if (objectString.contains("}, {" + Encoder.TYPE_SEPARATOR)) {
                component = objectString.substring(objectString.indexOf("{"), objectString.indexOf(", {" + Encoder.TYPE_SEPARATOR));
            } else {
                component = objectString.substring(data.indexOf("{"), objectString.lastIndexOf("]"));
            }
            this.refreshObj(component);
            objectString = objectString.substring(objectString.indexOf(component) + component.length() + 1);
        }
    }

    @Override
    public abstract String serialize(Object any) throws IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchMethodException, InvocationTargetException, UnsupportedEncodingException;

    @Override
    public abstract Object deserialize(String data) throws IllegalAccessException, InvocationTargetException, ParseException, InstantiationException, NoSuchMethodException, ClassNotFoundException;
}
