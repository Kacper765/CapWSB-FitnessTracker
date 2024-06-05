package com.capgemini.wsb.fitnesstracker.exception.api;

public class NotFoundExceptionImpl extends NotFoundException {
    public NotFoundExceptionImpl(String message) {
        super(message);
    }
}
