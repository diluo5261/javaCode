package com.dilo.exception;
//当年龄有异常时，抛出的异常
public class AgeException extends MyUserException{
    public AgeException() {
        super();
    }

    public AgeException(String message) {
        super(message);
    }
}
