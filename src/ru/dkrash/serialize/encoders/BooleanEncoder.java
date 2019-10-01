package ru.dkrash.serialize.encoders;

import ru.dkrash.serialize.ObjectFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BooleanEncoder extends AbstractPrimitiveTypesEncoder {
    @Override
    public List returnList(Object obj) {
        boolean[] arr = (boolean[]) obj;
        List result = new ArrayList();
        for (boolean el : arr) {
            result.add(el);
        }
        return result;
    }

    @Override
    public Object returnObj(List list) {
        boolean[] result = new boolean[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = (boolean) list.get(i);
        }
        return result;
    }

    @Override
    public Object deserialize(String data) {
        return Boolean.valueOf(this.getObjectStr(data));
    }
}
