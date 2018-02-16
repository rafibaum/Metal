package com.rafibaum.metal.utils;

/**
 * MetalConfigurationException is an exception which should be thrown when a developer
 * using metal fails to properly configure a Metal object in such a way there is no way
 * to recover from this exception other than to change the code so their objects are
 * set up correctly.
 */
public class MetalConfigurationException extends RuntimeException {

    public MetalConfigurationException() {
        super();
    }

    public MetalConfigurationException(String s) {
        super(s);
    }

    public MetalConfigurationException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public MetalConfigurationException(Throwable throwable) {
        super(throwable);
    }

    protected MetalConfigurationException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
