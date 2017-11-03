package br.com.getninjas.rover.exception;

/**
 *
 * @author Rafael G. Francisco
 */
public class RoverOutOfPlateauException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Rover out of plateau's area: ";
    
    public RoverOutOfPlateauException() {
    }

    public RoverOutOfPlateauException(String message) {
        super(DEFAULT_MESSAGE + message);
    }
    
}
