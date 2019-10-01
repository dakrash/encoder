package ru.dkrash.serialize.encoders;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.dkrash.example.ClassForExample;
import ru.dkrash.example.ClassWithConstr;
import ru.dkrash.serialize.EncoderProxy;
import ru.dkrash.serialize.exeptions.CircularLinked;
import ru.dkrash.serialize.exeptions.ThereIsConstructor;
import ru.dkrash.serialize.exeptions.UnknownType;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

class MainEncoderTest {
    ClassForExample elem;
    DateEncoder dateEncoder = new DateEncoder();
    EncoderProxy encoderProxy = new EncoderProxy();
    String instantStr;
    String dateStr;
    String serializeStr;
    Date date;
    Instant instant;

    void setFieldsValue() {
        this.dateEncoder.format.setTimeZone(TimeZone.getTimeZone("GMT"));
        this.elem = new ClassForExample();
        this.date = new Date();
        this.instant = Instant.now();
        this.elem.instant = this.instant;
        this.elem.date = this.date;
        this.dateStr = this.dateEncoder.format.format(this.date);
        this.instantStr = String.valueOf(this.instant);
        this.serializeStr = this.getSerializeStr();
    }


    String getSerializeStr() {
        return "{type : ru.dkrash.example.ClassForExample, value : {[{field date:{type : java.util.Date, value : " + dateStr + "}}" +
                ", {field instant:{type : java.time.Instant, value : " + instantStr + "}}" +
                ", {field enumSimple:{type : ru.dkrash.example.EnumSimple, value : THURSDAY}}, {field enumWithString:{type : ru.dkrash.example.EnumWithString, value : ru.dkrash.serialize.DayOfWeek{title='Понедельник'}}}, {field bigDecimal:{type : java.math.BigDecimal, value : 3}}, {field arrInt:{type : [I, value : [{type : java.lang.Integer, value : 0}, {type : java.lang.Integer, value : 1}, {type : java.lang.Integer, value : 2}, {type : java.lang.Integer, value : 0}, {type : java.lang.Integer, value : 0}]}}, {field arrBool:{type : [Ljava.lang.Boolean, value : [{type : java.lang.Boolean, value : true}, {type : java.lang.Boolean, value : true}, {type : java.lang.Boolean, value : true}, {type : null, value : null}, {type : null, value : null}]}}, {field bool:{type : java.lang.Boolean, value : true}}, {field one:{type : java.lang.Float, value : 1.0}}, {field two:{type : java.lang.Integer, value : 2}}, {field character:{type : java.lang.Character, value : t}}, {field classForField:{type : ru.dkrash.example.ClassForField, value : {[{field name:{type : java.lang.String, value : developer}}]}}}, {field str:{type : java.lang.String, value : Funny}}, {field map:{type : java.util.HashMap, value : [{type : java.lang.Integer, value : 0} = {type : null, value : null}, {type : java.lang.Integer, value : 2} = {type : java.lang.String, value : 2}]}}, {field arrayList:{type : java.util.ArrayList, value : [{type : java.lang.String, value : 2}]}}, {field hashSet:{type : java.util.HashSet, value : [{type : java.lang.String, value : 1}, {type : java.lang.String, value : 2}]}}, {field classForExample:{type : null, value : null}}]}}";
    }


    @Test
    void serialize() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        this.setFieldsValue();
        assertEquals(encoderProxy.serialize(elem), serializeStr);
    }

    @Test
    void deserialize() throws IllegalAccessException, InvocationTargetException, ParseException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        this.setFieldsValue();
        ClassForExample elDeserialize = (ClassForExample) EncoderProxy.el.deserialize(serializeStr);
        assertEquals(this.serializeStr, encoderProxy.serialize(elDeserialize));
    }

    @Test
    void circularLinked() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        this.setFieldsValue();
        this.elem.classForExample = this.elem;
        try {
            this.encoderProxy.serialize(this.elem);
            Assert.fail("Expected CircularLinked");
        } catch (CircularLinked thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }
    }

    @Test
    void thereIsConstructor() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ClassWithConstr classWithConstr = ClassWithConstr.el;
        this.setFieldsValue();
        try {
            this.encoderProxy.serialize(classWithConstr);
            Assert.fail("Expected ThereIsConstructor");
        } catch (ThereIsConstructor thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }
    }
//
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
}