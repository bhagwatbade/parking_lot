package main.java.ientity;

/**
 * Offers car information (Represent real word car with java)
 * 
 * @author bhagwat
 *
 */
public interface ICar extends IRootEntity {
	
	/**
	 * provide car registration number
	 * @return
	 */
	public String getRegistrationNumber();
	
	/**
	 * set car registration number
	 * @param registrationNumber
	 */
	public void setRegistrationNumber(String registrationNumber);
	
	/**
	 * provide color of car
	 * @return
	 */
	public String getColor();
	
	/**
	 * set color of car
	 * @param color
	 */
	public void setColor(String color);

}
