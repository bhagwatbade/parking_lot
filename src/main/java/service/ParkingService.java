package main.java.service;

import java.util.Optional;
import java.util.TreeSet;

import main.java.dto.ParkingDTO;
import main.java.entity.Car;
import main.java.entity.Lot;
import main.java.idto.IParkingDTO;
import main.java.ientity.ICar;
import main.java.ientity.ILot;
import main.java.ientity.ILot.SlotCharges;
import main.java.iservice.IParkingSerive;

/**
 * Implementation of method to parking service
 * 
 * @author bhagwat
 *
 */
public class ParkingService extends RootService implements IParkingSerive {
	
	/**
	 * Hold available parking lots
	 */
	private TreeSet<ILot> availableLot;
	
	/**
	 * Hold used parking lots
	 */
	private TreeSet<ILot>  usedLot;
	
	public ParkingService() {
		this.availableLot = new TreeSet<ILot>();
		this.usedLot = new TreeSet<ILot>();
	}
	
	/**
	 * park the car into parking lot
	 * 
	 * @param parkingDTO take car information like reg. No. car color.
	 * @throws Exception
	 */
	@Override
	public void parkCar(IParkingDTO parkingDTO) throws Exception {
		ICar car = new Car(parkingDTO.getRegistrationNumber(), parkingDTO.getColor());
		ILot lot = this.getFreeLot();
		if(lot != null) {
			this.allocateParkingLot(lot, car);
		}
	}
	/**
	 * check availability of parking lot 
	 * If lot is available then provide nearest parking lot
	 */
	private ILot getFreeLot() throws Exception {
		if(this.availableLot.isEmpty()) {
			System.out.format("Sorry, parking lot is full\n");
			return null;
		}else {
			return this.availableLot.first();
		}
	}

	/**
	 * Taking off car from parking lot
	 * 
	 * @param parkingDTO take car information like reg. no., parking hours.
	 * @throws Exception
	 */
	@Override
	public void unParkCar(IParkingDTO parkingDTO) throws Exception {
		ICar car = new Car(parkingDTO.getRegistrationNumber(), parkingDTO.getColor());
		ILot parkLot = this.getParkingLot(car);
		if(parkLot != null) {
			parkLot.setParkHour(parkingDTO.getHour());
			this.freeParkingLot(parkLot);
		}else {
			System.out.format("Registration number %s not found\n", car.getRegistrationNumber());
		}
		
	}
	
	/**
	 * Calculate parking charges for lot
	 * on bases of parking hours
	 * @param lot
	 * 
	 */
	private Double calculateParkingCharges(ILot lot) throws Exception {
		Double charges = 0.0;
		if(lot.getParkHour() == 0)
			return charges;
		
		Integer parkHour = lot.getParkHour();
		
		//Calculate flat charges
		charges = SlotCharges.FLATCHARGES.getCharges();
		parkHour = parkHour - SlotCharges.FLATCHARGES.getEndTime();
		
		//Calculate per hour charges
		if(parkHour > 0) {
			charges = charges + (parkHour * SlotCharges.PERHOURCHARGES.getCharges());
		}
		return charges;
	}
	
	/**
	 * provide parking lot where car is park
	 * @param car
	 * @return
	 * @throws Exception
	 */
	private ILot getParkingLot(ICar car) throws Exception {
		Optional<ILot> findFirst = this.usedLot.stream().filter(lot -> lot.getParkCar().equals(car)).findFirst();
		if(findFirst.isPresent()) {
			return findFirst.get();
		}else {
			return null;
		}
	}
	
	/**
	 * park car in provided parking lot
	 * @param lot : provided parking lot
	 * @param car
	 * @throws Exception
	 */
	private void allocateParkingLot(ILot lot, ICar car) throws Exception {
		boolean remove = this.availableLot.remove(lot);
		if(remove) {
			lot.setParkCar(car);
			this.usedLot.add(lot);
			System.out.format("Allocated slot number: %d\n", lot.getLotNo());
		}
	}

	/**
	 * park off car from provided lot
	 * @param lot
	 * @throws Exception
	 */
	private void freeParkingLot(ILot lot) throws Exception {
		boolean remove = this.usedLot.remove(lot);
		if(remove) {
			System.out.format("Registration number %s with Slot Number %d is free with Charge %d\n", lot.getParkCar().getRegistrationNumber(), lot.getLotNo(), this.calculateParkingCharges(lot).intValue());
			lot.setParkCar(new Car());
			lot.setParkHour(0);
			this.availableLot.add(lot);
		}
	}
	
	/**
	 * Offers status of parking lot
	 */
	@Override
	public void parkingStatus() throws Exception {
		System.out.format("Slot No.  Registration No.\n");
		for(ILot lot : usedLot) {
			System.out.format("%d  %s\n", lot.getLotNo(), lot.getParkCar().getRegistrationNumber());
		}
	}

	/**
	 * Create parking lots with provided size
	 * 
	 * @param size
	 */
	@Override
	public void createParkingLot(Long size) throws Exception {
		for(Long i = 1l; i <= size; i++) {
			this.availableLot.add(new Lot(i));
		}
		System.out.format("Created parking lot with %d slots\n", size);
	}
}
