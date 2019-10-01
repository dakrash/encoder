package ru.dkrash.serialize.encoders;

import java.util.ArrayList;
import java.util.List;

public class ByteEncoder extends AbstractPrimitiveTypesEncoder {

    @Override
    public List returnList(Object obj) {
        byte[] arr = (byte[]) obj;
        List result = new ArrayList();
        for (byte el : arr) {
            result.add(el);
        }
        return result;
    }

    @Override
    public Object returnObj(List list) {
        byte[] result = new byte[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) list.get(i);
        }
        return result;
    }

    @Override
    public Object deserialize(String data) {
        int intData = Integer.parseInt(this.getObjectStr(data));
        return (byte) intData;
    }
}
