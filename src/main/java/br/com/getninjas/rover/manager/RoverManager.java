package br.com.getninjas.rover.manager;

import br.com.getninjas.rover.enumeration.Axis;
import br.com.getninjas.rover.enumeration.Command;
import br.com.getninjas.rover.enumeration.Guidance;
import br.com.getninjas.rover.model.Rover;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dell
 */
public class RoverManager {

    private Rover rover;
    private String commands;
    private static final Map<Guidance, GuidanceMap> MAP = new HashMap<>(Guidance.values().length);
    
    public RoverManager(Rover rover, String commands) {
        this();
        this.rover = rover;
        this.commands = commands;
    }

    public RoverManager() {
        MAP.put(Guidance.N, new GuidanceMap(Guidance.W, Guidance.E));
        MAP.put(Guidance.E, new GuidanceMap(Guidance.N, Guidance.S));
        MAP.put(Guidance.S, new GuidanceMap(Guidance.E, Guidance.W));
        MAP.put(Guidance.W, new GuidanceMap(Guidance.S, Guidance.N));
    }

    public RoverManager setRover(Rover rover) {
        this.rover = rover;
        return this;
    }

    public RoverManager setCommands(String commands) {
        this.commands = commands;
        return this;
    }
    
    public void walk() {
        String[] split = this.commands.split("|");
        for (String s : split) {
            Command command = Command.valueOf(s);
            
            if (Command.M.equals(command)) {
                move();
            } else {
                rotate(command);
            }
        }
        
        this.rover = null;
        this.commands = null;
    }
    
    private void rotate(Command command) {
        GuidanceMap map = MAP.get(this.rover.getGuidance());
        
        if (Command.L.equals(command)) {
            this.rover.setGuidance(map.getPreviousGuidance());
        } else {
            this.rover.setGuidance(map.getNextGuidance());
        }
    }
    
    private void move() {
        //se a direcao for responsavel pelo eixo X, entao
        if (Axis.X.equals(rover.getGuidance().getAxis())) {
            this.rover.setAxisX(this.rover.getAxisX() + rover.getGuidance().getAxisValue());
        } else {
        //se a direcao for responsavel pelo eixo Y, entao
            this.rover.setAxisY(this.rover.getAxisY() + rover.getGuidance().getAxisValue());
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
