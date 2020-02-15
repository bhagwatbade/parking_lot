package main.java.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import main.java.dto.ParkingDTO;
import main.java.idto.IParkingDTO;
import main.java.iservice.IParkingSerive;
import main.java.service.ParkingService;
/**
 * main entry point of parking application
 * @author bhagwat
 *
 */
public class ParkingLot {
	/**
	 * keyword for create parking lot
	 */
	public static final String CPL = "create_parking_lot";
	
	/**
	 * keyword for park
	 */
	public static final String PARK = "park";
	
	/**
	 * keyword for leave car from parking
	 */
	public static final String LEAVE = "leave";
	
	/**
	 * keyword for parking status
	 */
	public static final String STATUS = "status";
	
	/**
	 * value separator
	 */
	public static final String SEPARATOR = " ";

	/**
	 * Read input file and manage parking lots accordingly
	 * @param args : file name
	 */
	public static void main(String[] args) {
		try {
			if(args == null)
				return;
			//check user provide proper file
			String fileName;
			try {
				fileName = args[0];
			} catch (Exception e) {
				throw new Exception("Please provide input file");
			}
			
			
			File file = new File(fileName);
			
			try (BufferedReader br = new BufferedReader(new FileReader(file))){
				String line = br.readLine();
				IParkingSerive parkingSerive = new ParkingService();
				
				while(line != null){
					//create parking lot
					if(line.contains(CPL)){
						String[] values = line.split(SEPARATOR);
						if(values != null && values.length == 2){
							Long size = Long.parseLong(values[1]);
							parkingSerive.createParkingLot(size);
						}else{
							throw new Exception("Invalid input");
						}
					}
					//park car
					else if(line.contains(PARK)){
						String[] values = line.split(SEPARATOR);
						if(values != null && values.length == 2){
							IParkingDTO parkingDTO = new ParkingDTO(values[1], "DefaultColor");
							parkingSerive.parkCar(parkingDTO);
						}else{
							throw new Exception("Invalid input");
						}
					}
					//Leave car from lot 
					else if(line.contains(LEAVE)){
						String[] values = line.split(SEPARATOR);
						if(values != null && values.length == 3){
							Integer hour = Integer.parseInt(values[2]);
							IParkingDTO parkingDTO = new ParkingDTO(values[1], hour);
							parkingSerive.unParkCar(parkingDTO);
						}else{
							throw new Exception("Invalid input");
						}
					}
					//status of parking lot
					else if(STATUS.equals(line.trim())){
						parkingSerive.parkingStatus();
					}
					
					line = br.readLine();
				}
			} catch(FileNotFoundException e){
				throw new Exception("Please provide correct physical file path");
			}
		} 
		 // All Exception handle by this common catch and provide information to client
		catch (Throwable e) {
			System.out.println(e.getMessage());
		}
	}

}
