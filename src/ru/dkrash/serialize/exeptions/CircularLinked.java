package ru.dkrash.serialize.exeptions;

public class CircularLinked extends RuntimeException {
    public CircularLinked(String message) {
        super(message);
    }
}
