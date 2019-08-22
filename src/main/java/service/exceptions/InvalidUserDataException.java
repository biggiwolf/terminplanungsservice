package service.exceptions;

/**
 * Exception that is thrown if obligatory data is missing or faulty
 */
public class InvalidUserDataException extends Exception{

    public InvalidUserDataException(String message){
        super(message);
    }

}
