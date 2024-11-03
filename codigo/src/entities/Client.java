package entities;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private int id;
    private String name;
    private List<Vehicle> vehicles;

    public Client(int id, String name) {
        this.id = id;
        this.name = name;
        this.vehicles = new ArrayList<>(); 
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        System.out.println("Vehicle with plate " + vehicle.getPlate() + " registered for client " + name);
    }

    public void showVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles registered for " + name);
        } else {
            for (Vehicle v : vehicles) {
                System.out.println("Vehicle: " + v.getModel() + " - Plate: " + v.getPlate());
            }
        }
    }
}