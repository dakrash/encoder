package ru.dkrash.serialize.encoders;

import java.util.ArrayList;
import java.util.List;

public class FloatEncoder extends AbstractPrimitiveTypesEncoder {

    @Override
    public List returnList(Object obj) {
        float[] arr = (float []) obj;
        List result = new ArrayList();
        for (float el : arr) {
            result.add(el);
        }
        return result;
    }

    @Override
    public Object returnObj(List list) {
        float[] result = new float[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = (float) list.get(i);
        }
        return result;
    }

    @Override
    public Object deserialize(String data) {
        return Float.valueOf(this.getObjectStr(data));
    }
}
