package com.example.javaapp.e_enumLesson;

public enum Country {
    JAPAN("Япония", 140_000_000, "японский"),
    GREECE("Греция", 10_000_000, "греческий"),
    ARGENTINA("Аргентина", 47_000_000, "испанский");

    private String name;
    private long peopleCountry;
    private String language;

    Country(String name, long peopleCountry, String language) {
        this.name = name;
        this.language = language;
        this.peopleCountry = peopleCountry;
    }

    public String getName() {
        return name;
    }

    public long getCount() {
        return peopleCountry;
    }

    public String getLanguage() {
        return language;
    }
}
