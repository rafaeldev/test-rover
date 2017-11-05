package br.com.getninjas.rover;

import br.com.getninjas.rover.enumeration.Guidance;
import br.com.getninjas.rover.manager.NasaCoordinatesReader;
import br.com.getninjas.rover.manager.RoverNavigationControl;
import br.com.getninjas.rover.model.Coordinates;
import br.com.getninjas.rover.model.Plateau;
import br.com.getninjas.rover.model.Rover;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Rafael G. Francisco
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //test();
        
        Scanner scanner = new Scanner(System.in);
        int nextInt = 0;
        
        do {
            try {
                System.out.println("Choice the file:");
                System.out.println("[1] Commands from resource file on project");
                System.out.println("[2] Set path to load file on read (with extension)");

                nextInt = scanner.nextInt();
            } catch (InputMismatchException mismatchException) {
                System.out.println("\nInvalid value! Set a number from options\n");
                scanner.nextLine();
            }
        } while (nextInt != 1 && nextInt != 2);
        
        System.out.println("\n");
        
        NasaCoordinatesReader ncr = new NasaCoordinatesReader();
        
        Coordinates coordinates;
        if (nextInt == 1) {
            coordinates = ncr.readFromResource();
        } else {
            System.out.println("Please, set the complete path to file:");
            String path = scanner.next();
            coordinates = ncr.readFromPath(path);
        }
        
        scanner.close();
        
        RoverNavigationControl rnc = new RoverNavigationControl();
        
        rnc.addPlateau(coordinates.getPlateau());
        for (Rover rover : coordinates.getRovers()) {
            System.out.println(rnc.addRover(rover).run());
        }
    }
    
    private static void test() {
        //Init the area where the rover will roll
        Plateau plateau = new Plateau(5, 5);
        Rover rover;
        String commands;
        String report;
        
        RoverNavigationControl rnc = new RoverNavigationControl();
        
        rover = new Rover(1, 2, Guidance.N);
        commands = "LMLMLMLMM";
        
        report = rnc.addPlateau(plateau)
                    .addRover(rover.setExploreCommands(commands))
                    .run();
        
        System.out.println(report);
        
        rover = new Rover(3, 3, Guidance.E);
        commands = "MMRMMRMRRM";
        
        report = rnc.addPlateau(plateau)
                    .addRover(rover.setExploreCommands(commands))
                    .run();
        
        System.out.println(report);
    }
}
