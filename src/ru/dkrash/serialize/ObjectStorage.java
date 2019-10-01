package ru.dkrash.serialize;

import java.util.HashSet;

public class ObjectStorage {
    private static HashSet<Object> objects = new HashSet<>();

    public static Boolean checkObject(Object object) {
        return ObjectStorage.objects.contains(object);
    }

    public static void addObject(Object object) {
        ObjectStorage.objects.add(object);
    }
}
