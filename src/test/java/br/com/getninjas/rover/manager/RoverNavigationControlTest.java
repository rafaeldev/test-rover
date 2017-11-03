package br.com.getninjas.rover.manager;

import br.com.getninjas.rover.enumeration.Guidance;
import br.com.getninjas.rover.exception.RoverInitialPositionException;
import br.com.getninjas.rover.exception.RoverOutOfPlateauException;
import br.com.getninjas.rover.model.Plateau;
import br.com.getninjas.rover.model.Rover;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Dell
 */
public class RoverNavigationControlTest {

    RoverNavigationControl rnc;

    public RoverNavigationControlTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        rnc = new RoverNavigationControl();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addRover method, of class RoverNavigationControll.
     */
    @Test
    public void testAddRover() {
        rnc.addRover(new Rover(1, 2, Guidance.N));
    }

    @Test
    public void testAddRoverException() {
        exception.expect(IllegalArgumentException.class);
        rnc.addRover(null);
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    /**
     * Test of addCommands method, of class RoverNavigationControll.
     */
    @Test
    public void testAddCommands() {
        rnc.addCommands("LMLMLMLMM");
        rnc.addCommands("MMRMMRMRRM");
    }

    @Test
    public void testAddCommandsException() {
        exception.expect(IllegalArgumentException.class);
        rnc.addCommands("XMMRMMRMRRM");
    }

    /**
     * Test of addPlateau method, of class RoverNavigationControll.
     */
    @Test
    public void testAddPlateau() {
        rnc.addPlateau(new Plateau(4, 3));
    }

    @Test
    public void testAddPlateauException() {
        exception.expect(IllegalArgumentException.class);
        rnc.addPlateau(null);
    }

    /**
     * Test of run method, of class RoverNavigationControll.
     */
    @Test
    public void testRun() {
        
        String report;
        
        report = rnc.addPlateau(new Plateau(5, 5))
                    .addRover(new Rover(1, 2, Guidance.N))
                    .addCommands("LMLMLMLMM")
                    .run();
        //testing perfect case 1
        assertEquals(report, "1 3 N");
        
        
        report = rnc.addPlateau(new Plateau(5, 5))
                    .addRover(new Rover(3, 3, Guidance.E))
                    .addCommands("MMRMMRMRRM")
                    .run();
        
        //testing perfect case 2
        assertEquals(report, "5 1 E");
    }
    
    @Test
    public void testRunExceptionPosition() {
        exception.expect(RoverInitialPositionException.class);
        
        String report;
        
        report = rnc.addPlateau(new Plateau(5, 5))
                    .addRover(new Rover(4, 8, Guidance.N)) //Axis Y bigger then plateau's axis Y limit
                    .addCommands("LMLMLMLMM")
                    .run();
    }
    
    @Test
    public void testRunExceptionMoveOutPosition() {
        exception.expect(RoverOutOfPlateauException.class);
        
        String report;
        
        report = rnc.addPlateau(new Plateau(5, 5))
                    .addRover(new Rover(1, 2, Guidance.N))
                    .addCommands("MMMMMMM") //Moving Rover until out of plateau
                    .run();
    }

}
