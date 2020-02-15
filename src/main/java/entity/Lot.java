package main.java.entity;

import main.java.ientity.ICar;
import main.java.ientity.ILot;
/**
 * Implementation for parking lot
 * @author bhagwat
 *
 */
public class Lot extends RootEntity implements ILot {
	
	/**
	 * unique for each lot
	 */
	private Long lotNo;
	
	private Integer parkHour;
	
	private ICar parkCar;
	
	public Lot() {
		this.lotNo = 0l;
		this.parkCar = new Car();
		this.parkHour = 0;
	}
	
	public Lot(Long lotNo) {
		this.lotNo = lotNo;
		this.parkCar = new Car();
		this.parkHour = 0;
	}

	@Override
	public Long getLotNo() {
		return lotNo;
	}

	@Override
	public void setLotNo(Long lotNo) {
		this.lotNo = lotNo;
	}

	@Override
	public ICar getParkCar() {
		return parkCar;
	}

	@Override
	public void setParkCar(ICar parkCar) {
		this.parkCar = parkCar;
	}

	@Override
	public Integer getParkHour() {
		return parkHour;
	}

	@Override
	public void setParkHour(Integer parkHour) {
		this.parkHour = parkHour;
	}

	/**
	 * hashcode generated based on unique lot number
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lotNo == null) ? 0 : lotNo.hashCode());
		return result;
	}

	/**
	 * uniqueness of lot is checked on basis of lot no;
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lot other = (Lot) obj;
		if (lotNo == null) {
			if (other.lotNo != null)
				return false;
		} else if (!lotNo.equals(other.lotNo))
			return false;
		return true;
	}

	/**
	 * compare to lot based on lot no.
	 */
	@Override
	public int compareTo(ILot lot) {
		return this.lotNo.compareTo(lot.getLotNo());
	}
}
