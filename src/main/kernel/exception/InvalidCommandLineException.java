package main.kernel.exception;

public class InvalidCommandLineException extends Exception {
    public InvalidCommandLineException(String message) {
        super(message);
    }
}
