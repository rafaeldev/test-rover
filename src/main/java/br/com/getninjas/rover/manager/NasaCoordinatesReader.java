package br.com.getninjas.rover.manager;

import br.com.getninjas.rover.enumeration.Guidance;
import br.com.getninjas.rover.exception.PlateauCoordinatesException;
import br.com.getninjas.rover.model.Coordinates;
import br.com.getninjas.rover.model.Plateau;
import br.com.getninjas.rover.model.Rover;
import br.com.getninjas.rover.util.DataPattern;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael G. Francisco
 */
public class NasaCoordinatesReader {
    
    ClassLoader classLoader = getClass().getClassLoader();
    File internalCoodinates = new File(classLoader.getResource("nasa-coordinates.txt").getFile());
    
    private static final String EMPTY = "";
    
    public Coordinates readFromResource() {
        try {
            return intrepetingCoordinates(internalCoodinates);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NasaCoordinatesReader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Coordinates readFromPath(String path) {
        try {
            return intrepetingCoordinates(new File(path));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NasaCoordinatesReader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private Coordinates intrepetingCoordinates(File file) throws FileNotFoundException {
        int i = 0;
        
        Coordinates coordinates = new Coordinates();
        
        try (Scanner scanner = new Scanner(file)) {
            
            Rover rover = null;
            
            while (scanner.hasNext()) {
                String line = scanner.nextLine().trim();
                
                if (line.startsWith("#") || EMPTY.equals(line)) {
                    continue;
                }
                
                if (i == 0) {
                    if (DataPattern.PLATEAU_COORDINATES.matcher(line).find()) {
                        Plateau plateau = intrepetingPlateau(line);
                        coordinates.setPlateau(plateau);
                        i++;
                        continue;
                    } else {
                        throw new PlateauCoordinatesException(line);
                    } 
                }
                
                if (i % 2 == 0) {
                    rover.setExploreCommands(line);
                    coordinates.addRover(rover);
                } else {
                    rover = intrepetingRover(line);
                }
                
                i++;
            }
        }
        
        return coordinates;
    }
    
    private Plateau intrepetingPlateau(String str) {
        String[] arr = str.split("\\s");
        return new Plateau(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
    }
    
    private Rover intrepetingRover(String str) {
        String[] arr = str.split("\\s");
        return new Rover(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Guidance.valueOf(arr[2]));
    }
}
