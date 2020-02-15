package main.java.idto;

/**
 * 
 * Exchange data of park car and parking hour
 * 
 * @author bhagwat
 *
 */
public interface IParkingDTO extends IRootDTO {
	
	/**
	 * provide registration number of car
	 * @return
	 */
	public String getRegistrationNumber();
	
	/**
	 * set registration number of car
	 * 
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
	
	/**
	 * provide parking hours
	 * @return
	 */
	public Integer getHour();
	
	/**
	 * set parking hours
	 * @param hour
	 */
	public void setHour(Integer hour);

}
