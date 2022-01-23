package service;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import model.Branch;
import model.Vehicle;

class VehicleTimmerDecrResourceTask extends TimerTask{
	private Vehicle vehicle;
	public VehicleTimmerDecrResourceTask(Vehicle vehicle) {
		// TODO Auto-generated constructor stub
		this.vehicle = vehicle;	
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		vehicle.setCount(vehicle.getCount()-1);
		System.out.println("Count has been reduced");
		
	}
		
}

class VehicleTimmerIncrResourceTask extends TimerTask{
	private Vehicle vehicle;
	private Branch branch;
	public VehicleTimmerIncrResourceTask(Branch branch, Vehicle vehicle) {
		// TODO Auto-generated constructor stub
		this.vehicle = vehicle;	
		this.branch = branch;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		branch.addVehicle(vehicle.getName(), 1);
		System.out.println("Count has been incremented back");
	}
		
}

public class VehicleRentalService {
	List<Branch> branches;

	public VehicleRentalService(List<Branch> branches) {
		this.branches = branches;
		for(Branch it : this.branches) {
			System.out.println("------------"+it.getBranchName()+"-------------");
			System.out.println(it.getVehicles().size());
			for(Vehicle it1 : it.getVehicles()) {
				System.out.println(it1.getName());
			}
		}
	}
	
	public void addBranch(Branch branch) {
		branches.add(branch);
		String name = branch.getBranchName();
		System.out.println("-------new Branch Vehicles: ---------------");
		for(Vehicle veh : branch.getVehicles()) {
			System.out.println(veh.getName());
		}
	}
	
	//This function adds vehicle to some unique branch.
	public void addVehicle(String branchName, String vehicleName, int count) {
		System.out.println("-------------------Adding a new Vehicle-----------------");
		for(Branch branch : branches) {
			if(branch.getBranchName() == branchName) {
				branch.addVehicle(vehicleName, count);
				break;
			}
		}
	}
	
	//This function returns the booked from vehicle
	public void rentVehicle(String vehicle, String fromTime, String toTime) {
		int lowPrice=9999;
		Branch curBranch=null;
		Vehicle curVehicle=null;
		Instant start = Instant.now();
		//your code
		
		//Prebook For time slot
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=null;
		try {
			date = dateFormatter.parse(fromTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date date1=null;
		try {
			date1 = dateFormatter.parse(toTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long differenceInTime = date1.getTime() - date.getTime();
		System.out.println("Time = "+differenceInTime);
		if(((differenceInTime/1000) % 3600) != 0) {
			System.out.println("Please Enter in a slot of hour");
			return;
		}
		
		
		for(Branch branch: branches) {
			for(Vehicle vehIt: branch.getVehicles()) {
				if(vehIt.getName() == vehicle)
					if((lowPrice > vehIt.getRentalPrice()) && (vehIt.getCount() != 0)) {
						lowPrice = vehIt.getRentalPrice();
						curBranch = branch;
						curVehicle = vehIt;
					}
			}
		}
		

	    //Now create the time and schedule it
	    Timer timer = new Timer();

	    //Use this if you want to execute it once
	    timer.schedule(new VehicleTimmerDecrResourceTask(curVehicle), date);
	    
	    

	    //Now create the time and schedule it
//	    Timer timer1 = new Timer();

	    //Use this if you want to execute it once
	    timer.schedule(new VehicleTimmerIncrResourceTask(curBranch, curVehicle), date1);
	}
	
	//This function returns 
	public void getAvailableVehicles(String branchName){
		System.out.println("Available vehicles for "+branchName+" branch");
		for(Branch branch: branches) {
			if(branch.getBranchName() == branchName)
				for(Vehicle vehIt: branch.getVehicles()) {
					System.out.println(vehIt.getName());
				}
		}
	}
	
	//This function prints the system View
	public void printSystemView() {
		for(Branch branch: branches) {
			System.out.println("For Branch "+branch.getBranchName()+" Available vehicles are: ");
				for(Vehicle vehIt: branch.getVehicles()) {
					System.out.println(vehIt.getCount() +" "+vehIt.getName()+" "+"Available for "+vehIt.getRentalPrice() + " Price");
				}
		}
	}
	
}
