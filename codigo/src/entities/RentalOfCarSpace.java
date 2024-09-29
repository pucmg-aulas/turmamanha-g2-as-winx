package entities;

import java.time.LocalDateTime;

public class RentalOfCarSpace {
    private CarSpace carSpace;
    private LocalDateTime startRental;
    private LocalDateTime endRental;
    private Vehicle vehicle;
    private int rentalId;

    public RentalOfCarSpace(CarSpace carSpace, LocalDateTime startRental, LocalDateTime endRental, Vehicle vehicle,
            int rentalId) {
        this.carSpace = carSpace;
        this.startRental = startRental;
        this.endRental = endRental;
        this.vehicle = vehicle;
        this.rentalId = rentalId;
    }
    public CarSpace getCarSpace() {
        return carSpace;
    }
    public void setCarSpace(CarSpace carSpace) {
        this.carSpace = carSpace;
    }
    public LocalDateTime getStartRental() {
        return startRental;
    }
    public void setStartRental(LocalDateTime startRental) {
        this.startRental = startRental;
    }
    public LocalDateTime getEndRental() {
        return endRental;
    }
    public void setEndRental(LocalDateTime endRental) {
        this.endRental = endRental;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public int getRentalId() {
        return rentalId;
    }
    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    
}
