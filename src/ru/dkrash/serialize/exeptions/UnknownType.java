package ru.dkrash.serialize.exeptions;

public class UnknownType extends RuntimeException {
    public UnknownType(String message) {
        super(message);
    }
}
