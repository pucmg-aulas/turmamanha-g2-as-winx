package dao;

import model.Vehicle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
	private static final String FILE_NAME = "codigo/vehicles.txt";

	public void saveAllVehicles(List<Vehicle> vehicles) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
			for (Vehicle vehicle : vehicles) {
				writer.write(vehicle.getPlate() + "," + vehicle.getModel());
				writer.newLine();
			}
			System.out.println("All vehicles saved.");
		} catch (IOException e) {
			System.err.println("Error saving vehicles: " + e.getMessage());
		}
	}

	public void saveVehicle(Vehicle vehicle) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {

			writer.write(vehicle.getPlate() + "," + vehicle.getModel());
			writer.newLine();
			System.out.println("Vehicle saved: " + vehicle.getPlate());
		} catch (IOException e) {
			System.err.println("Error saving vehicle: " + e.getMessage());
		}
	}

	public List<Vehicle> loadVehicles() {
		List<Vehicle> vehicles = new ArrayList<>();
		System.out.println("Attempting to load vehicles from: " + FILE_NAME);
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
			String line;

			while ((line = reader.readLine()) != null) {
				if (!line.isEmpty()) {
					String[] vehicleData = line.split(",");
					String plate = vehicleData[0];
					String model = vehicleData[1];
					vehicles.add(new Vehicle(plate, model));
				}
			}
			System.out.println("Successfully loaded " + vehicles.size() + " vehicles");
		} catch (IOException e) {
			System.err.println("Error loading vehicles: " + e.getMessage());
			e.printStackTrace();
		}
		return vehicles;
	}
}
