package ru.dkrash.example;

import ru.dkrash.serialize.encoders.MainEncoder;

public class Main {
    public static void main(String[] args) {
        ClassForExample classForExample = new ClassForExample();
        MainEncoder superBeanEncoder = new MainEncoder();
        byte[] bytes;
        try {
            bytes = superBeanEncoder.serialize(classForExample);
            ClassForExample par = (ClassForExample) superBeanEncoder.deserialize(bytes);
            superBeanEncoder.serialize(par);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
