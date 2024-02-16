package com.shell.command.exceptions;

public class ParkingLotNotFoundException extends RuntimeException {

    private String message;

    public ParkingLotNotFoundException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
