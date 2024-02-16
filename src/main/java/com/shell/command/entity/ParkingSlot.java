package com.shell.command.entity;


public class ParkingSlot {
    private int number;
    private boolean occupied;
    private CarEntity parkedCar;

    public ParkingSlot(int number) {
        this.number = number;
        this.occupied = false;
        this.parkedCar = null;
    }

    public int getNumber() {
        return number;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void parkCar(CarEntity car) {
        this.parkedCar = car;
        this.occupied = true;
    }

    public CarEntity getParkedCar() {
        return parkedCar;
    }
}
