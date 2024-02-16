package com.shell.command.exceptions;

public class SlotsNotFoundException extends RuntimeException {
    private String message;

    public SlotsNotFoundException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
