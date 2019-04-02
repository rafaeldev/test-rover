package br.com.getninjas.rover.manager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dell
 */
public class NasaCoordinatesReaderTest {
    
    NasaCoordinatesReader ncr;

    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ncr = new NasaCoordinatesReader();
    }
    
    /**
     * Test of readFromResource method, of class NasaCoordinatesReader.
     */
    @Test
    public void testReadFromResource() {
        assertNotNull(ncr.readFromResource());
    }

    /**
     * Test of readFromPath method, of class NasaCoordinatesReader.
     */
    @Test
    public void testReadFromPath() {
        String path = getClass().getResource("/nasa-coordinates-test.txt").getPath();

        assertNotNull(ncr.readFromPath(path));
    }
    
}
