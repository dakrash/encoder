package ru.dkrash.serialize.encoders;

import ru.dkrash.serialize.EncoderProxy;

import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Arrays;

public class MainEncoder implements SuperEncoder {
    @Override
    public byte[] serialize(Object anyBean) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String stringResult = EncoderProxy.el.serialize(anyBean);
        System.out.println(stringResult);
        return stringResult.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public Object deserialize(byte[] data) throws NoSuchMethodException, ParseException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        String strDeserialize = new String(data, StandardCharsets.UTF_8);
        return EncoderProxy.el.deserialize(strDeserialize);
    }
}
