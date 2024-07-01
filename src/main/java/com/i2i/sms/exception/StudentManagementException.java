package com.i2i.sms.exception;

/**
 * <p>
 * StudentManagementException class is used to catch and throw custom exception
 * In StudentManagementSystem with a String message and Throwable.
 * </p>
 */
public class StudentManagementException extends Exception {
    /**
     * <p>
     * Constructs a new StudentManagementException with the specified detail message
     * and cause.
     *</p>
     * @param message the detail message.
     * @param e the cause of the exception.
     */
    public StudentManagementException(String message, Throwable e) {
        super(message, e);
    }
}
