package service.exceptions;

/**
 * Exception that is thrown if the Event could not be found in the collection.
 */
public class NoSuchEventException extends Exception {

    public NoSuchEventException(String message){
        super(message);
    }

}
