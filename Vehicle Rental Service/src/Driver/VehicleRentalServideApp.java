package Driver;

import java.util.ArrayList;
import java.util.List;

import model.Branch;
import model.Vehicle;
import service.VehicleRentalService;

public class VehicleRentalServideApp {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		vehicles.add(new Vehicle("suv", 1, 10));
		vehicles.add(new Vehicle("sedan", 2, 10));
		vehicles.add(new Vehicle("bikes", 2, 20));
		
		List<Vehicle> vehicles1 = new ArrayList<Vehicle>();
		vehicles1.add(new Vehicle("suv", 1, 10));
		vehicles1.add(new Vehicle("sedan", 2, 10));
		vehicles1.add(new Vehicle("bikes", 2, 20));
		
		List<Branch> branches = new ArrayList<Branch>();
		branches.add(new Branch("miyapur", vehicles));
		branches.add(new Branch("radhanagar",vehicles1));
		
		VehicleRentalService service = new VehicleRentalService(branches);
		
		//Adding a new branch with available vehicles 
		List<Vehicle> vehicles2 = new ArrayList<Vehicle>();
		vehicles2.add(new Vehicle("suv", 4, 10));
		vehicles2.add(new Vehicle("sedan", 5, 20));
		vehicles2.add(new Vehicle("bikes", 3, 30));
		Branch newBranch = new Branch("chandichock", vehicles2);
		service.addBranch(newBranch);
		
		//Adding a vehicle at particular branch
		service.addVehicle("miyapur", "suv", 2);
		
		//Booking a vehicle for some hourly time
		service.rentVehicle("suv", "2022-01-10 21:17:00", "2022-01-10 22:17:00");
		
		//get available vehicles
		service.getAvailableVehicles("miyapur");
		
		
		//Print SystemView
		service.printSystemView();
	}

}
