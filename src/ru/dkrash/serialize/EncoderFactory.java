package ru.dkrash.serialize;

import ru.dkrash.serialize.encoders.*;
import ru.dkrash.serialize.exeptions.UnknownType;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class EncoderFactory {

    private EncoderFactory() {
    }

    public static Encoder getEncoder(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Encoder encoder = (Encoder) EncoderFactory.getStandardTypes(className);
        if (encoder == null) {
            if (className.contains("[")) {
                encoder = new ArrayEncoder();
            } else {
                Class<Object> clazz = (Class<Object>) Class.forName(className);
                Object[] enumConstants = clazz.getEnumConstants();
                if (enumConstants != null) {
                    encoder = new EnumEncoder();
                } else {
                    Object obj = ObjectFactory.create(className);
                    if (obj instanceof Collection) {
                        encoder = new CollectionEncoder();
                    } else if (obj instanceof Map) {
                        encoder = new MapEncoder();
                    } else {
                        encoder = new BeanEncoder();
                    }
                }
            }
        }
        return encoder;
    }

    private static AbstractStandardTypesEncoder getStandardTypes(String className) {
        AbstractStandardTypesEncoder encoder;
        switch (className) {
            case "java.lang.Integer":
                encoder = new IntegerEncoder();
                break;
            case "java.lang.Byte":
                encoder = new ByteEncoder();
                break;
            case "java.lang.Boolean":
                encoder = new BooleanEncoder();
                break;
            case "java.lang.Double":
                encoder = new DoubleEncoder();
                break;
            case "java.lang.Float":
                encoder = new FloatEncoder();
                break;
            case "java.lang.Long":
                encoder = new LongEncoder();
                break;
            case "java.lang.Short":
                encoder = new ShortEncoder();
                break;
            case "java.math.BigInteger":
                encoder = new BigIntegerEncoder();
                break;
            case "java.math.BigDecimal":
                encoder = new BigDecimalEncoder();
                break;
            case "java.time.Instant":
                encoder = new InstantEncoder();
                break;
            case "java.util.Date":
                encoder = new DateEncoder();
                break;
            case "java.lang.String":
                encoder = new StringEncoder();
                break;
            case "java.lang.Character":
                encoder = new CharacterEncoder();
                break;
            default:
                encoder = null;
                break;
        }
        return encoder;
    }

    public static AbstractPrimitiveTypesEncoder getPrimitiveEncoder(String className) {
        AbstractPrimitiveTypesEncoder encoder;
        switch (className) {
            case "I":
                encoder = new IntegerEncoder();
                break;
            case "B":
                encoder = new ByteEncoder();
                break;
            case "Z":
                encoder = new BooleanEncoder();
                break;
            case "D":
                encoder = new DoubleEncoder();
                break;
            case "F":
                encoder = new FloatEncoder();
                break;
            case "J":
                encoder = new LongEncoder();
                break;
            case "S":
                encoder = new ShortEncoder();
                break;
            case "C":
                encoder = new CharacterEncoder();
                break;
            default:
                encoder = null;
        }
        return encoder;
    }
}
