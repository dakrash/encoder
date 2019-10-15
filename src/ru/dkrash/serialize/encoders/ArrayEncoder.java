package ru.dkrash.serialize.encoders;

import ru.dkrash.serialize.EncoderFactory;
import ru.dkrash.serialize.EncoderProxy;
import ru.dkrash.serialize.exeptions.UnknownType;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArrayEncoder extends AbstractSetEncoder {

    @Override
    public void refreshObj(String objStr) throws IllegalAccessException, InvocationTargetException, ParseException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        Collection collection = (Collection) this.obj;
        collection.add(EncoderProxy.el.deserialize(objStr));
        this.obj = collection;
    }

    @Override
    public void setObj(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        this.obj = (Collection) Class.forName("java.util.ArrayList").newInstance();
    }

    @Override
    public String serialize(Object any) throws IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchMethodException, InvocationTargetException, UnsupportedEncodingException {

        StringBuilder stringResult = new StringBuilder();
        stringResult.append("[");

        String className = String.valueOf(any.getClass());
        AbstractPrimitiveTypesEncoder encoder = EncoderFactory.getPrimitiveEncoder(className.substring(className.indexOf("[") + 1));
        if (encoder != null) {
            List arr;
            arr = encoder.returnList(any);
            for (int i = 0; i < arr.size(); i++) {
                if (i != 0) {
                    stringResult.append(", ");
                }
                stringResult.append(EncoderProxy.el.serialize(arr.get(i)));
            }
        } else {
            Object[] arr = (Object[]) any;
            for (int i = 0; i < arr.length; i++) {
                if (i != 0) {
                    stringResult.append(", ");
                }
                stringResult.append(EncoderProxy.el.serialize(arr[i]));
            }
        }

        stringResult.append("]");
        return String.valueOf(stringResult);
    }

    @Override
    public String getTypeField(String data) {
        return data.substring(data.indexOf(Encoder.TYPE_SEPARATOR) + Encoder.TYPE_SEPARATOR.length() + 1, data.indexOf(","));
    }

    @Override
    public Object deserialize(String data) throws IllegalAccessException, InstantiationException, ClassNotFoundException, ParseException, NoSuchMethodException, InvocationTargetException {
        String typeField = this.getTypeField(data);
        this.refreshResultObj(data);
        ArrayList listResult = (ArrayList) this.obj;
        Class clazz;
        Object obj;
        if (typeField.charAt(0) == 'L') {
            clazz = Class.forName(typeField.substring(1));
            obj = listResult.toArray((Object[]) Array.newInstance(clazz, listResult.size()));
        } else {
            AbstractPrimitiveTypesEncoder encoder = EncoderFactory.getPrimitiveEncoder(typeField);
            if (encoder == null) {
                throw new UnknownType("UnknownType");
            } else {
                obj = encoder.returnObj(listResult);
            }
        }
        return obj;
    }
}
