package ru.dkrash.foo;

public enum EnumWithString {
    SUNDAY ("Воскресенье"),
    MONDAY ("Понедельник"),
    TUESDAY ("Вторник"),
    WEDNESDAY ("Среда"),
    THURSDAY ("Четверг"),
    FRIDAY ("Пятница"),
    SATURDAY ("Суббота");

    private String title;

    EnumWithString(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "ru.dkrash.serialize.DayOfWeek{" +
                "title='" + title + '\'' +
                '}';
    }
}
