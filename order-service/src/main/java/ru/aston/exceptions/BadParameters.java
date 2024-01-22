package ru.aston.exceptions;

public class BadParameters extends RuntimeException {
    public BadParameters(String msg) {
        super(msg);
    }
}
