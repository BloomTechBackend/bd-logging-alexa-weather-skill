package com.amazon.ata.remotedebuggingandlogging;

/**
 * The temperature service is currently unavailable for some reason, try again shortly.
 */
public class TemperatureServiceUnavailableException extends Exception {

    private static final long serialVersionUID = 5748406473812151456L;

    public TemperatureServiceUnavailableException(String message) {
        super(message);
    }

    public TemperatureServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
