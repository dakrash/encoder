package ru.dkrash.serialize.encoders;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class BigIntegerEncoder extends AbstractStandardTypesEncoder {
    @Override
    public Object deserialize(String data) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor ctor = Class.forName(this.getTypeField(data)).getDeclaredConstructor(String.class);
        return ctor.newInstance(this.getObjectStr(data));
    }
}
