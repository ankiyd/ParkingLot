package com.shell.command.entity;

public class CarEntity {
    private final String registrationNumber;
    private final String color;

    public CarEntity(String registrationNumber, String color) {
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getColor() {
        return color;
    }

}
