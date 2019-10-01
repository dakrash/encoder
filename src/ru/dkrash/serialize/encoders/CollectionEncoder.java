package ru.dkrash.serialize.encoders;

import ru.dkrash.serialize.EncoderProxy;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Collection;

public class CollectionEncoder extends AbstractSetEncoder {

    @Override
    public String serialize(Object any) throws IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        StringBuilder stringResult = new StringBuilder();
        Collection<Object> arrayList;
        arrayList = (Collection<Object>) any;
        stringResult.append("[");
        int i = 0;
        for (Object elem : arrayList) {
            if (i != 0) {
                stringResult.append(", ");
            }
            stringResult.append(EncoderProxy.el.serialize(elem));
            i++;
        }
        stringResult.append("]");
        return String.valueOf(stringResult);
    }

    @Override
    public void refreshObj(String objStr) throws IllegalAccessException, InvocationTargetException, ParseException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        Collection collection = (Collection) this.obj;
        collection.add(EncoderProxy.el.deserialize(objStr));
        this.obj = collection;
    }

    @Override
    public void setObj(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        this.obj = (Collection) Class.forName(className).newInstance();
    }


    @Override
    public Object deserialize(String data) throws IllegalAccessException, InstantiationException, ClassNotFoundException, ParseException, NoSuchMethodException, InvocationTargetException {
        this.refreshResultObj(data);
        return this.obj;
    }
}
