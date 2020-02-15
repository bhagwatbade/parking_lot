package main.java.dto;

import main.java.entity.Car;
import main.java.idto.IParkingDTO;
import main.java.ientity.ICar;
/**
 * 
 * @author bhagwat
 *
 */
public class ParkingDTO extends RootDTO implements IParkingDTO {
	
	private ICar car;
	
	private Integer hour;
	
	public ParkingDTO() {
		this.car = new Car();
		this.hour = 0;
	}
	
	public ParkingDTO(String registrationNumber, String color) {
		this.car = new Car(registrationNumber, color);
		this.hour = 0;
	}
	
	public ParkingDTO(String registrationNumber, Integer hour) {
		this.car = new Car(registrationNumber);
		this.hour = hour;
	}

	@Override
	public String getRegistrationNumber() {
		return this.car.getRegistrationNumber();
	}

	@Override
	public void setRegistrationNumber(String registrationNumber) {
		this.car.setRegistrationNumber(registrationNumber);
	}

	@Override
	public String getColor() {
		return this.car.getColor();
	}

	@Override
	public void setColor(String color) {
		this.car.setColor(color);
	}

	@Override
	public Integer getHour() {
		return hour;
	}

	@Override
	public void setHour(Integer hour) {
		this.hour = hour;
	}
}
