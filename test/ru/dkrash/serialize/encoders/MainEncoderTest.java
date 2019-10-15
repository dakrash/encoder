package ru.dkrash.serialize.encoders;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.dkrash.foo.MockObject;
import ru.dkrash.foo.ClassWithConstr;
import ru.dkrash.serialize.EncoderProxy;
import ru.dkrash.serialize.exeptions.CircularLinked;
import ru.dkrash.serialize.exeptions.ThereIsConstructor;
import ru.dkrash.serialize.exeptions.UnknownType;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MainEncoderTest {
    private MockObject elem;
    private DateEncoder dateEncoder = new DateEncoder();
    private EncoderProxy encoderProxy = new EncoderProxy();
    private String instantStr;
    private String dateStr;
    private String serializeStr;

    void setFieldsValue() {
        this.dateEncoder.format.setTimeZone(TimeZone.getTimeZone("GMT"));
        this.elem = new MockObject();
        this.elem.setStr("{type : [M, value : [{type : java.lang.Integer, value : 0}]}");
        this.elem.setArrInt(new int[]{0, 1, 2, 0, 0});
        Map<Integer, String> map = new HashMap<>();
        map.put(0, null);
        map.put(2, "2");
        this.elem.setMap(map);
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add("2");
        this.elem.setArrayList(arrayList);
        Date date = new Date();
        Instant instant = Instant.now();
        this.elem.setInstant(instant);
        this.elem.setDate(date);
        this.dateStr = this.dateEncoder.format.format(date);
        this.instantStr = String.valueOf(instant);
        this.serializeStr = this.getSerializeStr();
    }


    String getSerializeStr() {
        return "{type : ru.dkrash.foo.MockObject, value : {[{field str:{type : java.lang.String, value : " +
                Base64.getEncoder().encodeToString(String.valueOf(this.elem.getStr()).getBytes()) + "}}, {field date:{type : java.util.Date, value : " + dateStr + "}}, " +
                "{field instant:{type : java.time.Instant, value : " + instantStr + "}}, {field enumSimple:" +
                "{type : ru.dkrash.foo.EnumSimple, value : THURSDAY}}, {field bigDecimal:{type : " +
                "java.math.BigDecimal, value : 3}}, {field arrInt:{type : [I, value : [{type : java.lang.Integer, " +
                "value : 0}, {type : java.lang.Integer, value : 1}, {type : java.lang.Integer, value : 2}, " +
                "{type : java.lang.Integer, value : 0}, {type : java.lang.Integer, value : 0}]}}, {field bool:" +
                "{type : java.lang.Boolean, value : true}}, {field fl:{type : java.lang.Float, value : 1.0}}, " +
                "{field character:{type : java.lang.Character, value : t}}, {field classForField:" +
                "{type : ru.dkrash.foo.ClassForField, value : {[{field name:{type : java.lang.String, " +
                "value : ZGV2ZWxvcGVy}}]}}}, {field map:{type : java.util.HashMap, value : [{type : java.lang.Integer," +
                " value : 0} = {type : null, value : null}, {type : java.lang.Integer, value : 2} = " +
                "{type : java.lang.String, value : Mg==}]}}, {field arrayList:{type : java.util.ArrayList, " +
                "value : [{type : java.lang.String, value : Mg==}]}}, {field mockObject:{type : null, " +
                "value : null}}]}}";

    }


    @Test
    void serialize() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, UnsupportedEncodingException {
        this.setFieldsValue();
        assertEquals(encoderProxy.serialize(elem), serializeStr);
    }

    @Test
    void deserialize() throws IllegalAccessException, InvocationTargetException, ParseException, InstantiationException, NoSuchMethodException, ClassNotFoundException, UnsupportedEncodingException {
        this.setFieldsValue();
        MockObject elDeserialize = (MockObject) EncoderProxy.el.deserialize(serializeStr);
        assertEquals(this.serializeStr, encoderProxy.serialize(elDeserialize));
    }

    @Test
    void circularLinked() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, UnsupportedEncodingException {
        this.setFieldsValue();
        this.elem.setMockObject(this.elem);
        try {
            this.encoderProxy.serialize(this.elem);
            Assert.fail("Expected CircularLinked");
        } catch (CircularLinked thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void thereIsConstructor() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, UnsupportedEncodingException {
        ClassWithConstr classWithConstr = ClassWithConstr.el;
        this.setFieldsValue();
        try {
            this.encoderProxy.serialize(classWithConstr);
            Assert.fail("Expected ThereIsConstructor");
        } catch (ThereIsConstructor thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void unknownType() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, ParseException, IllegalAccessException {
        String forTest = "{type : [M, value : [{type : java.lang.Integer, value : 0}]}";
        try {
            ArrayEncoder arrayEncoder = new ArrayEncoder();
            arrayEncoder.deserialize(forTest);
            Assert.fail("Expected UnknownType");
        } catch (UnknownType thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Test
    void notCircualLinked() throws NoSuchMethodException, UnsupportedEncodingException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        try {
            this.setFieldsValue();
            MainEncoder mainEncoder = new MainEncoder();
            mainEncoder.serialize(this.elem);
            mainEncoder.serialize(this.elem);
            Assert.assertNotEquals(1, 2);
        } catch (CircularLinked thrown) {
            Assert.fail("notCircualLinked");
        }
    }
}