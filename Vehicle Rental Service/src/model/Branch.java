package model;

import java.util.Iterator;
import java.util.List;

public class Branch {
	String branchName;
	List<Vehicle> vehicles;
	public Branch(String branchName, List<Vehicle> vehicles) {
		this.branchName = branchName;
		this.vehicles = vehicles;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public List<Vehicle> getVehicles() {
		return vehicles;
	}
	public void addVehicle(String vehicleName, int count) {
		Iterator<Vehicle> it1 = vehicles.iterator();
		Vehicle veh = null;
		while(it1.hasNext()) {
			veh = it1.next();
			if(veh.getName() == vehicleName) {
				veh.setCount(veh.getCount()+count);
				break;
			}
		}
	}
	
}
