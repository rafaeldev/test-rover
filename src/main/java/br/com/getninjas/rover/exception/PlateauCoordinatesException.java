package br.com.getninjas.rover.exception;

/**
 *
 * @author Dell
 */
public class PlateauCoordinatesException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Plateau coordinates doesn't match with pattern: ";
    
    public PlateauCoordinatesException() {
    }
    
    public PlateauCoordinatesException(String message) {
        super(DEFAULT_MESSAGE + message);
    }
    
}
