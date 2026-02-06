package com.example.colorpicker.model;

public class Color {
    private final String name;
    private final int number;
    private final String hex;

    public Color(String name, int number, String hex){
        this.name = name;
        this.number =number;
        this.hex = hex;
    }

    public String getHex() {
        return hex;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }
}
