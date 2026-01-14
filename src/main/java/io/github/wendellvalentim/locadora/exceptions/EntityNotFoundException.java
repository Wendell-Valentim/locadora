package io.github.wendellvalentim.locadora.exceptions;

public class EntityNotFoundException extends  RuntimeException{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
