package ru.dkrash.serialize.encoders;

import java.util.ArrayList;
import java.util.List;

public class DoubleEncoder extends AbstractPrimitiveTypesEncoder {

    @Override
    public List returnList(Object obj) {
        double[] arr = (double[]) obj;
        List result = new ArrayList();
        for (double el : arr) {
            result.add(el);
        }
        return result;
    }

    @Override
    public Object returnObj(List list) {
        double[] result = new double[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = (double) list.get(i);
        }
        return result;
    }

    @Override
    public Object deserialize(String data) {
        return Double.valueOf(this.getObjectStr(data));
    }
}
