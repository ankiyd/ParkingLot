package com.shell.command.exceptions;

public class InvalidSlotNumber extends RuntimeException{
    private String message;

    public InvalidSlotNumber(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
