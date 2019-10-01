package ru.dkrash.serialize.exeptions;

public class ThereIsConstructor extends RuntimeException {
    public ThereIsConstructor(String message) {
        super(message);
    }
}
