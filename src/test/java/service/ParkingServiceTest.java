package test.java.service;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.dto.ParkingDTO;
import main.java.idto.IParkingDTO;
import main.java.iservice.IParkingSerive;
import main.java.service.ParkingService;

/**
 * Unit test cases for parking application
 * @author bhagwat
 *
 */
public class ParkingServiceTest {
	
	/**
	 * parking service
	 */
	private IParkingSerive parkingSerive = new ParkingService();
	
	/**
	 * To read console
	 */
	private PrintStream sysOut;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    @Before
    public void before() throws Throwable {
        sysOut = System.out;
        System.setOut(new PrintStream(outContent));
    }
 
    @After
    public void after() {
        System.setOut(sysOut);
    }
 
    public String asString() {
        return outContent.toString();
    }
    
    /**
     * Check parking lot creation
     * @throws Exception
     */
	@Test
	public void createParkingLotTest() throws Exception {
		IParkingSerive parkingSerive = new ParkingService();
		parkingSerive.createParkingLot(6l);
		assertEquals(outContent.toString(), String.format("Created parking lot with %d slots\n", 6l));
	}
	
	/**
	 * Check car parking
	 * @throws Exception
	 */
	@Test
	public void parkCarTest() throws Exception {
		parkingSerive.createParkingLot(1l);
		outContent.reset();
		IParkingDTO parkingDTO = new ParkingDTO("KA-01-HH-1234", "RED");
		parkingSerive.parkCar(parkingDTO);
		assertEquals(outContent.toString(), String.format("Allocated slot number: %d\n", 1));
	}
	
	/**
	 * Check parking off car with flat charge
	 * @throws Exception
	 */
	@Test
	public void unparkCarFlatChargesTest() throws Exception {
		parkingSerive.createParkingLot(1l);
		IParkingDTO parkingDTO = new ParkingDTO("KA-01-HH-1234", "RED");
		parkingSerive.parkCar(parkingDTO);
		outContent.reset();
		
		IParkingDTO unParkingDTO = new ParkingDTO("KA-01-HH-1234", 2);
		parkingSerive.unParkCar(unParkingDTO);
		assertEquals(outContent.toString(), String.format("Registration number %s with Slot Number %d is free with Charge %d\n","KA-01-HH-1234", 1, 10));
	}
	
	
	/**
	 * Check parking off car with per hour charge
	 * @throws Exception
	 */
	@Test
	public void unparkCarPerHourChargesTest() throws Exception {
		parkingSerive.createParkingLot(1l);
		IParkingDTO parkingDTO = new ParkingDTO("KA-01-HH-1234", "RED");
		parkingSerive.parkCar(parkingDTO);
		outContent.reset();
		
		IParkingDTO unParkingDTO = new ParkingDTO("KA-01-HH-1234", 4);
		parkingSerive.unParkCar(unParkingDTO);
		assertEquals(outContent.toString(), String.format("Registration number %s with Slot Number %d is free with Charge %d\n","KA-01-HH-1234", 1, 30));
	}
	
	/**
	 * Check for parking full
	 * @throws Exception
	 */
	@Test
	public void parkingFullTest() throws Exception {
		parkingSerive.createParkingLot(1l);
		IParkingDTO parkingDTO1 = new ParkingDTO("KA-01-HH-1234", "RED");
		parkingSerive.parkCar(parkingDTO1);
		
		outContent.reset();
		
		IParkingDTO parkingDTO2 = new ParkingDTO("KA-01-HH-9999", "RED");
		parkingSerive.parkCar(parkingDTO2);
		
		assertEquals(outContent.toString(), String.format("Sorry, parking lot is full\n"));
	}
	
	/**
	 * Check for car not available in parking
	 * @throws Exception
	 */
	@Test
	public void carNotFoundTest() throws Exception {
		parkingSerive.createParkingLot(1l);
		IParkingDTO parkingDTO = new ParkingDTO("KA-01-HH-1234", "RED");
		parkingSerive.parkCar(parkingDTO);
		
		outContent.reset();
		
		IParkingDTO parkingDTO2 = new ParkingDTO("KA-01-HH-9999", 3);
		parkingSerive.unParkCar(parkingDTO2);
		
		assertEquals(outContent.toString(), String.format("Registration number %s not found\n", "KA-01-HH-9999"));
	}
	
	/**
	 * Check for parking status
	 * @throws Exception
	 */
	@Test
	public void parkingStatusTest() throws Exception {
		parkingSerive.createParkingLot(2l);
		IParkingDTO parkingDTO = new ParkingDTO("KA-01-HH-1234", "RED");
		parkingSerive.parkCar(parkingDTO);
		IParkingDTO parkingDTO2 = new ParkingDTO("KA-01-HH-9999", "RED");
		parkingSerive.parkCar(parkingDTO2);
		
		outContent.reset();
		
		parkingSerive.parkingStatus();
		assertEquals(outContent.toString(), String.format("Slot No.  Registration No.\n1  KA-01-HH-1234\n2  KA-01-HH-9999\n"));
	}
}
