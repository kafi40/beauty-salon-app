package ru.kafi.beautysalonapiservice.exception;

public class ValueAlreadyUsedException extends RuntimeException {
    public ValueAlreadyUsedException(String message) {
        super(message);
    }
}
