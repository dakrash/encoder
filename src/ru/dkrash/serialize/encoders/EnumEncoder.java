package ru.dkrash.serialize.encoders;

public class EnumEncoder extends AbstractStandardTypesEncoder {
    @Override
    public Object deserialize(String data) throws ClassNotFoundException {
        String typeField = this.getTypeField(data);
        String objectStr = this.getObjectStr(data);
        Object[] enumConstants = Class.forName(typeField).getEnumConstants();
        for (Object enumConstant : enumConstants) {
            if (objectStr.equals(String.valueOf(enumConstant))) {
                return enumConstant;
            }
        }
        return null;
    }
}
