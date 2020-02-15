package main.java.iservice;

import main.java.idto.IParkingDTO;

/**
 * Offer parking services to client
 * 
 * @author bhagwat
 */
public interface IParkingSerive extends IRootService {
	
	/**
	 * Create parking lots with provided size
	 * 
	 * @param size
	 */
	public void createParkingLot(Long size) throws Exception;
	
	/**
	 * park the car into parking lot
	 * 
	 * @param parkingDTO take car information like reg. No. car color.
	 * @throws Exception
	 */
	public void parkCar(IParkingDTO parkingDTO) throws Exception;

	/**
	 * Taking off car from parking lot
	 * 
	 * @param parkingDTO take car information like reg. no., parking hours.
	 * @throws Exception
	 */
	public void unParkCar(IParkingDTO parkingDTO) throws Exception;
	
	/**
	 * Offers status of parking lot
	 */
	public void parkingStatus() throws Exception;
}
