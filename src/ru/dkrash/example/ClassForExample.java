package ru.dkrash.example;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

public class ClassForExample {
    public ClassForExample() {
        for (int i = 0; i < this.arrBool.length - 2; i++) {
            this.arrBool[i] = true;
            this.arrInt[i] = i;
        }
        this.map.put(0, null);
        this.map.put(2, "2");
        this.arrayList.add("2");
        this.hashSet.add("1");
        this.hashSet.add("2");
        this.date = new Date();
        this.instant = Instant.now();
    }

    public Date date;
    public Instant instant;
    public EnumSimple enumSimple = EnumSimple.THURSDAY;
    public EnumWithString enumWithString = EnumWithString.MONDAY;
    private BigDecimal bigDecimal = new BigDecimal(3);
    public int[] arrInt = new int[5];
    public Boolean[] arrBool = new Boolean[5];
    public Boolean bool = true;
    private float one = 1;
    private int two = 2;
    Character character = 't';
    public ClassForField classForField = new ClassForField();
    public String str = "Funny";
    Map<Integer, String> map = new HashMap<>();
    ArrayList<Object> arrayList = new ArrayList<>();
    HashSet<String> hashSet = new HashSet<>();
    public ClassForExample classForExample;
}
