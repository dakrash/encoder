package ru.dkrash.serialize.encoders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class DateEncoder extends AbstractStandardTypesEncoder {
    public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");

    @Override
    public String serialize(Object anyPrimitive){
        this.format.setTimeZone(TimeZone.getTimeZone("GMT"));
        return this.format.format(anyPrimitive);
    }

    @Override
    public Object deserialize(String data) throws ParseException {
        this.format.setTimeZone(TimeZone.getTimeZone("GMT"));
        return this.format.parse(this.getObjectStr(data));
    }
}
