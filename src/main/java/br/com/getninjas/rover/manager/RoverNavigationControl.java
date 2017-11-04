package br.com.getninjas.rover.manager;

import br.com.getninjas.rover.enumeration.Axis;
import br.com.getninjas.rover.enumeration.Command;
import br.com.getninjas.rover.enumeration.Guidance;
import br.com.getninjas.rover.exception.RoverInitialPositionException;
import br.com.getninjas.rover.exception.RoverOutOfPlateauException;
import br.com.getninjas.rover.model.Plateau;
import br.com.getninjas.rover.model.Rover;
import br.com.getninjas.rover.util.DataPattern;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

/**
 *
 * @author Rafael G. Francisco
 */
public class RoverNavigationControl {

    private Rover rover;
//    private String commands;
    private Plateau plateau;
    
    private static final Map<Guidance, GuidanceMap> MAP = new HashMap<>(Guidance.values().length);
    
    public RoverNavigationControl() {
        //Mapping sibling Guidances for each one
        MAP.put(Guidance.N, new GuidanceMap(Guidance.W, Guidance.E));
        MAP.put(Guidance.E, new GuidanceMap(Guidance.N, Guidance.S));
        MAP.put(Guidance.S, new GuidanceMap(Guidance.E, Guidance.W));
        MAP.put(Guidance.W, new GuidanceMap(Guidance.S, Guidance.N));
    }

    private static final String EMPTY = "";
    
    public RoverNavigationControl addRover(Rover rover) {
        if (rover == null) {
            throw new IllegalArgumentException("Rover can't be null!");
        }
        
        String commands = rover.getExploreCommands();
        
        if (commands == null || EMPTY.equals(commands)) {
            throw new IllegalArgumentException("Commands is invalid! (null or empty)");
        }
        
        Matcher matcher = DataPattern.MOVE_COMMANDS.matcher(commands);
        if (matcher.find()) {
            throw new IllegalArgumentException(String.format("The command \"%s\" is invalid!", matcher.group()));
        }
        
        this.rover = rover;
        return this;
    }

    public RoverNavigationControl addPlateau(Plateau plateau) {
        if (plateau == null) {
            throw new IllegalArgumentException("Plateau can't be null!");
        }
        
        this.plateau = plateau;
        return this;
    }
    
    public String run() {
        //verify initial position of Rover at Plateau
        if (this.rover.getAxisX() > this.plateau.getBase() || this.rover.getAxisX() < 0) {
            throw new RoverInitialPositionException("Axis X is wrong!");
        } 
        
        if (this.rover.getAxisY() > this.plateau.getHeight()|| this.rover.getAxisY() < 0) {
            throw new RoverInitialPositionException("Axis Y is wrong!");
        }
        
        //Spliting by all chars
        String[] split = this.rover.getExploreCommands().split("|");
        for (String s : split) {
            //For each letter from array, parse to a Command
            Command command = Command.valueOf(s);
            
            if (Command.M.equals(command)) {
                move();
            } else {
                rotate(command);
            }
        }
        
        //return last position after the commands
        return String.format("%d %d %s", rover.getAxisX(), rover.getAxisY(), rover.getGuidance().toString());
    }
    
    private void rotate(Command command) {
        GuidanceMap map = MAP.get(this.rover.getGuidance());
        
        if (Command.L.equals(command)) {
            this.rover.setGuidance(map.getPreviousGuidance());
        } else {
            this.rover.setGuidance(map.getNextGuidance());
        }
    }
    
    private void move() throws RoverOutOfPlateauException {
        int step;
        
        if (Axis.X.equals(rover.getGuidance().getAxis())) {
            step = this.rover.getAxisX() + rover.getGuidance().getAxisValue();
            
            if (step > this.plateau.getHeight() || step < 0) {
                throw new RoverOutOfPlateauException("Step out to axis X = " + step);
            }
            
            this.rover.setAxisX(step);
        } else {
            step = this.rover.getAxisY() + rover.getGuidance().getAxisValue();
            
            if (step > this.plateau.getBase()|| step < 0) {
                throw new RoverOutOfPlateauException("Step out to axis Y = " + step);
            }
            
            this.rover.setAxisY(step);
        }
    }
    
    private class GuidanceMap {
        private final Guidance previousGuidance;
        private final Guidance nextGuidance;

        public GuidanceMap(Guidance previousGuidance, Guidance nextGuidance) {
            this.previousGuidance = previousGuidance;
            this.nextGuidance = nextGuidance;
        }

        public Guidance getPreviousGuidance() {
            return previousGuidance;
        }

        public Guidance getNextGuidance() {
            return nextGuidance;
        }
    }
}
