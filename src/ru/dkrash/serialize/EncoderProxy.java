package ru.dkrash.serialize;

import ru.dkrash.serialize.exeptions.CircularLinked;
import ru.dkrash.serialize.encoders.Encoder;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public class EncoderProxy implements Encoder {

    public EncoderProxy() {
    }

    public static EncoderProxy el = new EncoderProxy();

    public String serialize(Object value) throws ClassNotFoundException, InvocationTargetException, InstantiationException, NoSuchMethodException, IllegalAccessException, UnsupportedEncodingException {
        StringBuilder stringResult = new StringBuilder();
        stringResult.append("{");
        String objectClass;
        if (value == null) {
            objectClass = "null";
        } else {
            objectClass = String.valueOf(value.getClass());
            final String CLASS_SEPARATOR = "class ";
            if (objectClass.lastIndexOf(";") != -1) {
                objectClass = objectClass.substring(CLASS_SEPARATOR.length(), objectClass.lastIndexOf(";"));
            } else {
                objectClass = objectClass.substring(CLASS_SEPARATOR.length());
            }
        }
        stringResult.append(Encoder.TYPE_SEPARATOR).append(objectClass);
        stringResult.append(", ");
        stringResult.append(Encoder.VALUE_SEPARATOR);
        if (value == null) {
            stringResult.append("null");
        } else {
            Encoder encoder;
            if (!ObjectStorage.checkObject(value)) {
                encoder = EncoderFactory.getEncoder(objectClass);
            } else {
                throw new CircularLinked("Circular Linked");
            }
            stringResult.append(encoder.serialize(value));
        }
        stringResult.append("}");
        return String.valueOf(stringResult);
    }


    public Object deserialize(String objectStr) throws ClassNotFoundException, InvocationTargetException, InstantiationException, NoSuchMethodException, IllegalAccessException, ParseException {
        String typeField = this.getTypeField(objectStr);
        if (typeField.equals("null")) {
            return null;
        } else {
            Encoder encoder = EncoderFactory.getEncoder(typeField);
            return encoder.deserialize(objectStr);
        }
    }
}
