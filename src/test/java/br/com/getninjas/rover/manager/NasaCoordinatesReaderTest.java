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
    
    public NasaCoordinatesReaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ncr = new NasaCoordinatesReader();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of readFromResource method, of class NasaCoordinatesReader.
     */
    @Test
    public void testReadFromResource() {
        assertNotNull(ncr.readFromResource());
    }

    //ALERT: For this test work well this file need exists
    private static final String PATH = "C:\\commands.txt";
    
    /**
     * Test of readFromPath method, of class NasaCoordinatesReader.
     */
    @Test
    public void testReadFromPath() {
        assertNotNull(ncr.readFromPath(PATH));
    }
    
}
