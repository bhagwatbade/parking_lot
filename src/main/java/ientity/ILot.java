package main.java.ientity;

import main.java.idto.IRootDTO;

/**
 * Offers parking lot information
 * 
 * @author bhagwat
 *
 */
public interface ILot extends IRootDTO, Comparable<ILot> {
	
	/**
	 * Changeable parking charges
	 * currently we have flat and per hour charges for park car 
	 * @author bhagwat
	 *
	 */
	public enum SlotCharges{
		
		FLATCHARGES(0, 2, 10.0),PERHOURCHARGES(2, Integer.MAX_VALUE, 10.0);
		
		/**
		 * Starting time of slot
		 */
		private Integer startTime;
		
		/**
		 * End time of slot
		 */
		private Integer endTime;
		
		/**
		 * Charges of slot
		 */
		private Double charges;
		
		private SlotCharges(int startTime, int endTime, Double charges) {
			this.startTime = startTime;
			this.endTime = endTime;
			this.charges = charges;
		}

		public Integer getStartTime() {
			return startTime;
		}

		public Integer getEndTime() {
			return endTime;
		}

		public Double getCharges() {
			return charges;
		}
	}
	
	public Long getLotNo();
	public void setLotNo(Long slotNo);
	
	public ICar getParkCar();
	public void setParkCar(ICar parkCar);
	
	public Integer getParkHour();
	public void setParkHour(Integer parkHour);

}
