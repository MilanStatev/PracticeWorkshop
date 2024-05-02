package com.company.oop.furniture.exceptions;

public class ElementAlreadyExistException extends RuntimeException{
    public ElementAlreadyExistException() {
    }

    public ElementAlreadyExistException(String message) {
        super(message);
    }
}
