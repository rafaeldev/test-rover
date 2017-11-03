package br.com.getninjas.rover;

import br.com.getninjas.rover.enumeration.Guidance;
import br.com.getninjas.rover.manager.RoverNavigationControl;
import br.com.getninjas.rover.model.Plateau;
import br.com.getninjas.rover.model.Rover;

/**
 * @author Rafael G. Francisco
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
        String report;
        
        RoverNavigationControl rnc = new RoverNavigationControl();
        
        rover = new Rover(1, 2, Guidance.N);
        commands = "LMLMLMLMM";
        
        report = rnc.addPlateau(plateau)
                    .addRover(rover)
                    .addCommands(commands)
                    .run();
        
        System.out.println(report);
        
        rover = new Rover(3, 3, Guidance.E);
        commands = "MMRMMRMRRM";
        
        report = rnc.addPlateau(plateau)
                    .addRover(rover)
                    .addCommands(commands)
                    .run();
        
        System.out.println(report);
        
    }
    
}
