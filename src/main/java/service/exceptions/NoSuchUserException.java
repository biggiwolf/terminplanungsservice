package service.exceptions;

/**
 * Exception that is thrown if a method depends on an User with an id that is not contained in the collection
 */
public class NoSuchUserException extends Exception{

    public NoSuchUserException(String message){
        super(message);
    }

}
