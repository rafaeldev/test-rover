package br.com.getninjas.rover.util;

import br.com.getninjas.rover.enumeration.Command;
import br.com.getninjas.rover.enumeration.Guidance;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 *
 * @author Rafael G. Francisco
 */
public class DataPattern {
    
    private static final StringBuilder POSSIBLE_COMMANDS = new StringBuilder(Command.values().length);
    private static final StringBuilder POSSIBLE_GUIDANCE = new StringBuilder(Guidance.values().length);
    
    public static Pattern MOVE_COMMANDS;
    public static Pattern PLATEAU_COORDINATES;
    public static Pattern ROVER_INIT_COORDINATES;
    
    static {
        Arrays.asList(Command.values()).stream().forEach(cmd -> POSSIBLE_COMMANDS.append(cmd.name()));
        MOVE_COMMANDS = Pattern.compile("[^" + POSSIBLE_COMMANDS.toString() + "]");
        
        PLATEAU_COORDINATES = Pattern.compile("\\d\\s\\d");
        
        Arrays.asList(Guidance.values()).stream().forEach(g -> POSSIBLE_GUIDANCE.append(g.name()));
        ROVER_INIT_COORDINATES = Pattern.compile("\\d\\s\\d\\s[" + POSSIBLE_GUIDANCE.toString() + "]");
    }
    
}
