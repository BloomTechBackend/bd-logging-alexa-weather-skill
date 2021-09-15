package com.amazon.ata.remotedebuggingandlogging;

/**
 * Requesting temperatures for a city that we don't have a record of.
 */
public class CityNotFoundException extends Exception {

    private static final long serialVersionUID = -2087086266997508156L;

    public CityNotFoundException(String message) {
        super(message);
    }

    public CityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
