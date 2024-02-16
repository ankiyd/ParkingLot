package com.shell.command.controller;

import com.shell.command.entity.ParkingSlot;
import com.shell.command.exceptions.ParkingLotNotFoundException;
import com.shell.command.services.ParkingLotService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;
import java.util.Objects;

@ShellComponent
public class ParkingLotController {

    private ParkingLotService parkingLotService;

    @ShellMethod(key = "create_parking_lot")
    public String createParkingLot(
            @ShellOption Integer lotSize
    ) {
        parkingLotService = new ParkingLotService(lotSize);
        return "Created a parking lot with " + lotSize + " slots";
    }

    @ShellMethod(key = "park")
    public String parkCar(
            @ShellOption String registrationNumber, @ShellOption String color
    ) {
        try {
            if (Objects.isNull(parkingLotService)) {
                throw new ParkingLotNotFoundException("Parking Lot is not created!");
            }
            return parkingLotService.park(registrationNumber, color);
        } catch (RuntimeException runtimeException) {
            return runtimeException.getMessage();
        }

    }

    @ShellMethod(key = "leave")
    public String leave(
            @ShellOption Integer slotNumber
    ) {
        try {
            if (Objects.isNull(parkingLotService)) {
                throw new ParkingLotNotFoundException("Parking Lot is not created!");
            }
            return parkingLotService.leave(slotNumber);
        } catch (RuntimeException runtimeException) {
            return runtimeException.getMessage();
        }
    }

    @ShellMethod(key = "status")
    public String getStatus() {
        try {
            if (Objects.isNull(parkingLotService)) {
                throw new ParkingLotNotFoundException("Parking Lot is not created!");
            }
            List<ParkingSlot> occupiedSlots = parkingLotService.getOccupiedParkingSlots();
            return parkingLotService.getStatusTable(occupiedSlots);
        } catch (RuntimeException runtimeException) {
            return runtimeException.getMessage();
        }
    }

    @ShellMethod(key = "registration_numbers_for_cars_with_colour")
    public String getRegistrationNumbersByColor(@ShellOption String color) {
        try {
            if (Objects.isNull(parkingLotService)) {
                throw new ParkingLotNotFoundException("Parking Lot is not created!");
            }
            return parkingLotService.getAllCarsByColor(color);
        } catch (RuntimeException runtimeException) {
            return runtimeException.getMessage();
        }
    }
}
