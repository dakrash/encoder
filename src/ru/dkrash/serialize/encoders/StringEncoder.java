package ru.dkrash.serialize.encoders;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class StringEncoder extends AbstractStandardTypesEncoder {
    @Override
    public Object deserialize(String data) {
        byte[] decodedBytes = Base64.getDecoder().decode(this.getObjectStr(data));
        return new String(decodedBytes);
    }

    @Override
    public String serialize(Object obj) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(String.valueOf(obj).getBytes());
    }
}
