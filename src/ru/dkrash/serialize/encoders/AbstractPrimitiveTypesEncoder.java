package ru.dkrash.serialize.encoders;

import java.util.List;

public abstract class AbstractPrimitiveTypesEncoder extends AbstractStandardTypesEncoder {
    public abstract List returnList(Object obj);

    public abstract Object returnObj(List list);
}
