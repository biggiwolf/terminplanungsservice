package service.exceptions;

/**
 * Exception that is thrown if event data is invalid. No title, no participant, no startTime or no endTime.
 */
public class InvalidEventDataException extends Exception {

    public InvalidEventDataException(String message){
        super(message);
    }

}
