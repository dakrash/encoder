package ru.dkrash.serialize.encoders;

public class StringEncoder extends AbstractStandardTypesEncoder {
    @Override
    public Object deserialize(String data) {
        return this.getObjectStr(data);
    }
}
