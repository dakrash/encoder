package ru.dkrash.foo;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

public class MockObject implements java.io.Serializable {
    public MockObject() {
    }

    private String str;
    private Date date;
    private Instant instant;
    private EnumSimple enumSimple;
    private BigDecimal bigDecimal;
    private int[] arrInt;
    private Boolean bool;
    private float fl;
    private Character character;
    private ClassForField classForField = new ClassForField();
    private Map<Integer, String> map = new HashMap<>();
    private ArrayList<Object> arrayList = new ArrayList<>();
    private MockObject mockObject;
    public void setStr(String str){
        this.str = str;
    }
    public String getStr(){
        return this.str;
    }
    public void setDate(Date date){
        this.date = date;
    }
    public Date getDate(){
        return this.date;
    }
    public void setInstant(Instant instant){
        this.instant = instant;
    }
    public Instant getInstant(){
        return this.instant;
    }
    public void setEnumSimple(EnumSimple enumSimple){
        this.enumSimple = enumSimple;
    }
    public EnumSimple getEnumSimple(){
        return this.enumSimple;
    }
    public void setBigDecimal(BigDecimal bigDecimal){
        this.bigDecimal = bigDecimal;
    }
    public BigDecimal getBigDecimal(){
        return this.bigDecimal;
    }
    public void setArrInt(int[] arrInt){
        this.arrInt = arrInt;
    }
    public int[] getArrInt(){
        return this.arrInt;
    }
    public void setBoolean(Boolean bool){
        this.bool = bool;
    }
    public Boolean isBoolean(){
        return this.bool;
    }
    public void setFloat(float fl){
        this.fl = fl;
    }
    public float getFloat(){
        return this.fl;
    }
    public void setCharacter(Character character){
        this.character = character;
    }
    public Character getCharacter(){
        return this.character;
    }
    public void setClassForField(ClassForField classForField){
        this.classForField = classForField;
    }
    public ClassForField getClassForField(){
        return this.classForField;
    }
    public void setMap(Map<Integer, String> map){
        this.map = map;
    }
    public Map<Integer, String> getMap(){
        return this.map;
    }
    public void setArrayList(ArrayList<Object> arrayList){
        this.arrayList = arrayList;
    }
    public ArrayList<Object> getArrayList(){
        return this.arrayList;
    }
    public void setMockObject(MockObject mockObject){
        this.mockObject = mockObject;
    }
    public MockObject getMockObject(){
        return this.mockObject;
    }
}
