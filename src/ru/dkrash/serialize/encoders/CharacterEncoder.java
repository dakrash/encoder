package ru.dkrash.serialize.encoders;

import java.util.ArrayList;
import java.util.List;

public class CharacterEncoder extends AbstractPrimitiveTypesEncoder {

    @Override
    public List returnList(Object obj) {
        char[] arr = (char[]) obj;
        List result = new ArrayList();
        for (char el : arr) {
            result.add(el);
        }
        return result;
    }

    @Override
    public Object returnObj(List list) {
        char[] result = new char[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = (char) list.get(i);
        }
        return result;
    }

    @Override
    public Character deserialize(String data) {
        return (this.getObjectStr(data).charAt(0));
    }
}
