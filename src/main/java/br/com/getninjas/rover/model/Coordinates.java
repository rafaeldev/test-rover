package br.com.getninjas.rover.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rafael G. Francisco
 */
public class Coordinates {
    
    private Plateau plateau;
    
    private final List<Rover> rovers;

    public Coordinates() {
        this.rovers = new ArrayList<>();
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }
    
    public boolean addRover(Rover rover) {
        return this.rovers.add(rover);
    }

    public List<Rover> getRovers() {
        return rovers;
    }
    
}
