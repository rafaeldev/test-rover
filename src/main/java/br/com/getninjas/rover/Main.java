package br.com.getninjas.rover;

import br.com.getninjas.rover.enumeration.Guidance;
import br.com.getninjas.rover.manager.RoverManager;
import br.com.getninjas.rover.model.Plateau;
import br.com.getninjas.rover.model.Rover;

/**
 * @author Dell
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Init the area where the rover will roll
        Plateau plateau = new Plateau(5, 5);
        Rover rover;
        String commands;
        
        RoverManager roverManager = new RoverManager();
        
        rover = new Rover(1, 2, Guidance.N, plateau);
        commands = "LMLMLMLMM";
        roverManager.setRover(rover).setCommands(commands).walk();
        printRover(rover);
        
        rover = new Rover(3, 3, Guidance.E, plateau);
        commands = "MMRMMRMRRM";
        roverManager.setRover(rover).setCommands(commands).walk();
        printRover(rover);
    }
    
    private static void printRover(Rover rover) {
        System.out.println(rover.getAxisX() + " " + rover.getAxisY() + " " + rover.getGuidance().toString());
    }
    
}
