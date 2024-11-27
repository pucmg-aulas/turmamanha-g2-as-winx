package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public  class Client {
    private int id;
    private String name;
    private List<Vehicle> vehicles;
    private double baseValue;
   
    public Client(int id, String name) {
        this.id = id;
        this.name = name;
        this.vehicles = new ArrayList<>(); 
    }

    public Client(int id, String name, double baseValue) {
        this.id = id;
        this.name = name;
        this.vehicles = new ArrayList<>();
        this.baseValue = baseValue;
    }

    public Client(double baseValue) {
        this.baseValue = baseValue;
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

    public double getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(double baseValue) {
        this.baseValue = baseValue;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Client client = (Client) obj;
        return id == client.id && Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}