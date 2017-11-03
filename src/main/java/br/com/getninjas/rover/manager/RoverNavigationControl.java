package br.com.getninjas.rover.manager;

import br.com.getninjas.rover.enumeration.Axis;
import br.com.getninjas.rover.enumeration.Command;
import br.com.getninjas.rover.enumeration.Guidance;
import br.com.getninjas.rover.exception.RoverInitialPositionException;
import br.com.getninjas.rover.exception.RoverOutOfPlateauException;
import br.com.getninjas.rover.model.Plateau;
import br.com.getninjas.rover.model.Rover;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Rafael G. Francisco
 */
public class RoverNavigationControl {

    private Rover rover;
    private String commands;
    private Plateau plateau;
    
    private static final Map<Guidance, GuidanceMap> MAP = new HashMap<>(Guidance.values().length);
    
    private static final StringBuilder POSSIBLE_COMMANDS = new StringBuilder(Command.values().length);
    private static Pattern PATTERN;
    
    public RoverNavigationControl() {
        //Mapping sibling Guidances for each one
        MAP.put(Guidance.N, new GuidanceMap(Guidance.W, Guidance.E));
        MAP.put(Guidance.E, new GuidanceMap(Guidance.N, Guidance.S));
        MAP.put(Guidance.S, new GuidanceMap(Guidance.E, Guidance.W));
        MAP.put(Guidance.W, new GuidanceMap(Guidance.S, Guidance.N));
        
        Arrays.asList(Command.values()).stream().forEach(cmd -> POSSIBLE_COMMANDS.append(cmd.name()));
        
        PATTERN = Pattern.compile("[^" + POSSIBLE_COMMANDS.toString() + "]");
    }

    public RoverNavigationControl addRover(Rover rover) {
        if (rover == null) {
            throw new IllegalArgumentException("Rover can't be null!");
        }
        
        this.rover = rover;
        return this;
    }

    private static final String EMPTY = "";
    
    public RoverNavigationControl addCommands(String commands) {
        if (commands == null || EMPTY.equals(commands)) {
            throw new IllegalArgumentException("Commands is invalid!");
        }
        
        Matcher matcher = PATTERN.matcher(commands);
        if (matcher.find()) {
            throw new IllegalArgumentException(String.format("The command \"%s\" is invalid!", matcher.group()));
        }
        
        this.commands = commands;
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
        String[] split = this.commands.split("|");
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
