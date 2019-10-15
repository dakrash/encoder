package ru.dkrash.foo;

public class ClassWithConstr {
    private ClassWithConstr(Boolean bool){
        this.bool = bool;
    }
    public static ClassWithConstr el = new ClassWithConstr(true);
    private boolean bool;
}
