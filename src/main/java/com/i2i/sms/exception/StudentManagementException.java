package com.i2i.sms.exception;

public class StudentManagementException extends Exception {

    public StudentManagementException(String message) {
        super(message);
    }

    public StudentManagementException(String message, Throwable e) {
        super(message, e);
    }
}
