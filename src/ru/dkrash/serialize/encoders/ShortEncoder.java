package ru.dkrash.serialize.encoders;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortEncoder extends AbstractPrimitiveTypesEncoder {
    @Override
    public List returnList(Object obj) {
        short[] arr = (short[]) obj;
        List result = new ArrayList();
        for (short el : arr) {
            result.add(el);
        }
        return result;
    }

    @Override
    public Object returnObj(List list) {
        short[] result = new short[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = (short) list.get(i);
        }
        return result;
    }

    @Override
    public Object deserialize(String data) {
        return Short.valueOf(this.getObjectStr(data));
    }
}
