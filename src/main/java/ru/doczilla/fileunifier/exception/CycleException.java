package ru.doczilla.fileunifier.exception;

public class CycleException extends RuntimeException {
    public CycleException(String message) {
        super(message);
    }
}
