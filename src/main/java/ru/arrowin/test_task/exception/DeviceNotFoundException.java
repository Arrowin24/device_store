package ru.arrowin.test_task.exception;

public class DeviceNotFoundException extends NullPointerException {
    public DeviceNotFoundException() {
        super("The database does not contain such a line of devices. Please add a new device before adding models to it.");
    }
}
