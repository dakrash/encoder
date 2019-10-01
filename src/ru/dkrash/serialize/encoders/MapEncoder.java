package ru.dkrash.serialize.encoders;

import ru.dkrash.serialize.EncoderProxy;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Map;

public class MapEncoder extends AbstractSetEncoder {
    @Override
    public String serialize(Object any) throws IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        StringBuilder stringResult = new StringBuilder();
        Map<Object, Object> map;
        map = (Map<Object, Object>) any;
        stringResult.append("[");
        int i = 0;
        for (final Map.Entry<Object, Object> entry : map.entrySet()) {
            if (i != 0) {
                stringResult.append(", ");
            }
            stringResult.append(EncoderProxy.el.serialize(entry.getKey()));
            stringResult.append(" = ");
            stringResult.append(EncoderProxy.el.serialize(entry.getValue()));
            i++;
        }
        stringResult.append("]");
        return String.valueOf(stringResult);
    }

    @Override
    public void refreshObj(String objStr) throws IllegalAccessException, InvocationTargetException, ParseException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        Map arrayMap = (Map) this.obj;
        arrayMap.put(EncoderProxy.el.deserialize(objStr.substring(0, objStr.indexOf(" ="))), EncoderProxy.el.deserialize(objStr.substring(objStr.indexOf("= "))));
        this.obj = arrayMap;
    }

    @Override
    public void setObj(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        this.obj = (Map) Class.forName(className).newInstance();
    }

    @Override
    public Object deserialize(String data) throws IllegalAccessException, InvocationTargetException, ParseException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        this.refreshResultObj(data);
        return this.obj;
    }
}
