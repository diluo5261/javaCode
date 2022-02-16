package com.dilo.exception;

//当姓名有异常时，抛出的异常
public class NameException extends MyUserException{
    public NameException() {
        super();
    }

    public NameException(String message) {
        super(message);
    }
}
