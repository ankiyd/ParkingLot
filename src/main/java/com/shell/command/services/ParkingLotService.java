package com.shell.command.services;

import com.shell.command.entity.CarEntity;
import com.shell.command.entity.ParkingSlot;
import com.shell.command.exceptions.InvalidSlotNumber;
import com.shell.command.exceptions.SlotsNotFoundException;

import java.util.*;

/* Parking Lot contains available parking slots and capacity of Parking Lot*/
public class ParkingLotService {

    private int capacity;
    private PriorityQueue<ParkingSlot> availableSlots;

    private Map<Integer, ParkingSlot> occupiedSlots;

    /* This is the Create Parking Lot Function */
    public ParkingLotService(int capacity) {
        this.capacity = capacity;
        this.availableSlots = new PriorityQueue<>(capacity, Comparator.comparingInt(ParkingSlot::getNumber));
        for (int i = 1; i <= capacity; i++) {
            availableSlots.offer(new ParkingSlot(i));
        }
        this.occupiedSlots = new HashMap<>();
    }

    /* This is the Park Function */
    public String park(String registrationNumber, String color) {
        if (availableSlots.isEmpty()) throw new SlotsNotFoundException("Sorry, Parking is full");
        CarEntity carEntity = new CarEntity(registrationNumber, color);
        ParkingSlot parkingSlot = availableSlots.poll();
        parkingSlot.parkCar(carEntity);
        occupiedSlots.put(parkingSlot.getNumber(), parkingSlot);
        return "Allocated slot number: " + parkingSlot.getNumber();
    }

    /* This is the Leave Function */
    public String leave(int slotNumber) {
        if (slotNumber < 1 || slotNumber > capacity)
            throw new InvalidSlotNumber("Invalid Slot number!");
        availableSlots.offer(new ParkingSlot(slotNumber));
        occupiedSlots.remove(slotNumber);
        return "Slot number " + slotNumber + " is free";
    }

    /* This is the Function to get all Occupied Parking Slots list */
    public List<ParkingSlot> getOccupiedParkingSlots() {
        List<ParkingSlot> occupiedSlotsList = new ArrayList<>();
        for (ParkingSlot slot : occupiedSlots.values()) {
            if (slot.isOccupied()) {
                occupiedSlotsList.add(slot);
            }
        }
        return occupiedSlotsList;
    }

    /* This is the Status Function */
    public String getStatusTable(List<ParkingSlot> parkingSlots) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Slot No.   Registration No   Colour\n");
        for (ParkingSlot slot : parkingSlots) {
            CarEntity car = slot.getParkedCar();
            stringBuilder.append(String.format("%-11d%-18s%s\n", slot.getNumber(),
                    car.getRegistrationNumber(), car.getColor()));
        }
        return stringBuilder.toString();
    }

    /* This is the Function to get all cars with specified color*/
    public String getAllCarsByColor(String color) {
        List<ParkingSlot> parkingSlots = getOccupiedParkingSlots();
        List<String> registrationNumbersList = new ArrayList<>();
        for (ParkingSlot slot : parkingSlots) {
            CarEntity car = slot.getParkedCar();
            if (color.equals(car.getColor())) {
                registrationNumbersList.add(car.getRegistrationNumber());
            }
        }
        if (registrationNumbersList.isEmpty()) {
            return "No cars found with such color";
        }
        StringBuilder registrationNumbers = new StringBuilder();
        for (String registrationNumber : registrationNumbersList) {
            registrationNumbers.append(registrationNumber).append(", ");
        }
        registrationNumbers.deleteCharAt(registrationNumbers.lastIndexOf(", "));
        return registrationNumbers.toString();

    }

}
