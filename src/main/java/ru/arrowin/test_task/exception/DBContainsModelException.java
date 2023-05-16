package ru.arrowin.test_task.exception;

public class DBContainsModelException extends IllegalArgumentException {

    public DBContainsModelException() {
        super("The database contains a model with this serial number. Please make sure you have not entered this " +
                      "model into the database before.");
    }
}
