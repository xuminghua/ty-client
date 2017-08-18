package com.songouhe.base.dao;

/**
 * @author zoujuan
 * @version 1.0 2007-11-9
 */
public class DaoSysException extends Exception {
    private Exception exception;

    /**
     * Creates a new ServiceLocatorException wrapping another exception, and with a detail message.
     *
     * @param message   the detail message.
     * @param exception the wrapped exception.
     */
    public DaoSysException(String message, Exception exception) {
        super(message);
        this.exception = exception;
        return;
    }

    /**
     * Creates a ServiceLocatorException with the specified detail message.
     *
     * @param message the detail message.
     */
    public DaoSysException(String message) {
        this(message, null);
        return;
    }

    /**
     * Creates a new ServiceLocatorException wrapping another exception, and with no detail message.
     *
     * @param exception the wrapped exception.
     */
    public DaoSysException(Exception exception) {
        this(null, exception);
        return;
    }

    /**
     * Gets the wrapped exception.
     *
     * @return the wrapped exception.
     */
    public Exception getException() {
        return exception;
    }

    /**
     * Retrieves (recursively) the root cause exception.
     *
     * @return the root cause exception.
     */
    public Exception getRootCause() {
        if (exception instanceof DaoSysException) {
            return ((DaoSysException) exception).getRootCause();
        }
        return exception == null ? this : exception;
    }

    public String toString() {
        if (exception instanceof DaoSysException) {
            return exception.toString();
        }
        return exception == null ? super.toString() : exception.toString();
    }

}
