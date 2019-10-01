package ru.dkrash.serialize.encoders;

import java.text.ParseException;
import java.time.Instant;

public class InstantEncoder extends AbstractStandardTypesEncoder {
    @Override
    public Object deserialize(String data) throws ParseException {
        return Instant.parse(this.getObjectStr(data));
    }
}
