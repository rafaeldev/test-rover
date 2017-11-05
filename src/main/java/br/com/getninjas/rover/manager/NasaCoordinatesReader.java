package br.com.getninjas.rover.manager;

import br.com.getninjas.rover.enumeration.Guidance;
import br.com.getninjas.rover.exception.PlateauCoordinatesException;
import br.com.getninjas.rover.model.Coordinates;
import br.com.getninjas.rover.model.Plateau;
import br.com.getninjas.rover.model.Rover;
import br.com.getninjas.rover.util.DataPattern;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael G. Francisco
 */
public class NasaCoordinatesReader {
    
    InputStream in = getClass().getResourceAsStream("/nasa-coordinates.txt");
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    
    private static final String EMPTY = "";
    
    public Coordinates readFromResource() {
        try {
            return intrepetingCoordinates(reader);
            
        } catch (PlateauCoordinatesException 
                | NullPointerException 
                | IOException ex) {
            Logger.getLogger(NasaCoordinatesReader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Coordinates readFromPath(String path) {
        try {
            return intrepetingCoordinates(new BufferedReader(new FileReader(path)));
        } catch (PlateauCoordinatesException 
                | NullPointerException 
                | IOException ex) {
            Logger.getLogger(NasaCoordinatesReader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private Coordinates intrepetingCoordinates(BufferedReader reader) throws FileNotFoundException, IOException {
        int i = 0;
        
        Coordinates coordinates = new Coordinates();
        
        String line;
        Rover rover = null;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            
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
