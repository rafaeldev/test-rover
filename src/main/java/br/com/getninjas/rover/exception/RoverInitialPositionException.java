package br.com.getninjas.rover.exception;

/**
 *
 * @author Rafael G. Francisco
 */
public class RoverInitialPositionException extends IllegalArgumentException {

    private static final String DEFAULT_MESSAGE = "Rover out of plateau's area on init travel position: ";
    
    public RoverInitialPositionException() {
    }

    public RoverInitialPositionException(String message) {
        super(DEFAULT_MESSAGE + message);
    }
    
}
