package entities;

import java.util.ArrayList;

public class Client {
	private int idClient;
	private String name;
	private ArrayList<Vehicle> vehicles = new ArrayList<>();

	
	public Client(int idClient, String name) {
		this.idClient = idClient;
		this.name = name;
		this.vehicles = vehicles;
	}

	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	public void addVehicle(String plate, String model){
		Vehicle vehicle = new Vehicle(plate, model);
		vehicles.add(vehicle);
	}

	public void removeVehicle(String plate){
		for(Vehicle findVehicle : vehicles){
			if(findVehicle.getPlate() == null ? plate == null : findVehicle.getPlate().equals(plate)){
				vehicles.remove(findVehicle);
				System.out.println("Vehicle removed !");
			}
		}
		System.out.println("Vehicle not found !");
	}

	public String setAnonymous(int idClient){
		return "anonymousClient";
	}
}
