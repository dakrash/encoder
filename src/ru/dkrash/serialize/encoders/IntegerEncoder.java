package ru.dkrash.serialize.encoders;

import java.util.ArrayList;
import java.util.List;

public class IntegerEncoder extends AbstractPrimitiveTypesEncoder {

    @Override
    public List returnList(Object obj) {
        int[] arr = (int[]) obj;
        List result = new ArrayList();
        for (int el : arr) {
            result.add(el);
        }
        return result;
    }

    @Override
    public Object returnObj(List list) {
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = (int) list.get(i);
        }
        return result;
    }

    @Override
    public Object deserialize(String data) {
        return Integer.valueOf(this.getObjectStr(data));
    }
}
