package main.java.entity;

import main.java.ientity.ICar;

/**
 * representation of Car 
 * 
 * @author bhagwat
 *
 */
public class Car extends RootEntity implements ICar {
	
	/**
	 * unique for each car
	 */
	private String registrationNumber;
	
	private String color;
	
	public Car() {
		this.registrationNumber = "";
		this.color = "";
	}

	public Car(String registrationNumber, String color) {
		super();
		this.registrationNumber = registrationNumber;
		this.color = color;
	}
	
	public Car(String registrationNumber) {
		super();
		this.registrationNumber = registrationNumber;
		this.color = "";
	}

	@Override
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	@Override
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * hashcode generated based on unique registration number of car
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((registrationNumber == null) ? 0 : registrationNumber.hashCode());
		return result;
	}

	/**
	 * equals method check uniqueness of car based on registration number of car
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (registrationNumber == null) {
			if (other.registrationNumber != null)
				return false;
		} else if (!registrationNumber.equals(other.registrationNumber))
			return false;
		return true;
	}
}
