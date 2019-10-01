package ru.dkrash.serialize.encoders;

import java.util.ArrayList;
import java.util.List;

public class LongEncoder extends AbstractPrimitiveTypesEncoder {

    @Override
    public List returnList(Object obj) {
        long[] arr = (long[]) obj;
        List result = new ArrayList();
        for (long el : arr) {
            result.add(el);
        }
        return result;
    }

    @Override
    public Object returnObj(List list) {
        long[] result = new long[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = (long) list.get(i);
        }
        return result;
    }

    @Override
    public Object deserialize(String data) {
        return Long.valueOf(this.getObjectStr(data));
    }
}
