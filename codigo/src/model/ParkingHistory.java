package model;

import java.time.LocalDateTime;

public class ParkingHistory {
    private int clientId;
    private String spotId;
    private String vehiclePlate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double price;

    public ParkingHistory(int clientId, String spotId, String vehiclePlate, 
                         LocalDateTime startTime, LocalDateTime endTime, double price) {
        this.clientId = clientId;
        this.spotId = spotId;
        this.vehiclePlate = vehiclePlate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    // Getters
    public int getClientId() { return clientId; }
    public String getSpotId() { return spotId; }
    public String getVehiclePlate() { return vehiclePlate; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public double getPrice() { return price; }
}