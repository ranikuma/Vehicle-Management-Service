package model;

public class Vehicle {
	String name;
	int count;
	int rentalPrice;
	public Vehicle(String name, int count, int rentalPrice) {
		this.name = name;
		this.count = count;
		this.rentalPrice = rentalPrice;
	}
	public String getName() {
		return name;
	}
	public int getCount() {
		return count;
	}
	public int getRentalPrice() {
		return rentalPrice;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setRentalPrice(int rentalPrice) {
		this.rentalPrice = rentalPrice;
	}
	
	
}
