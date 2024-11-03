package entities;

import java.time.Duration;
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

    public String calculateTime(LocalDateTime startRental, LocalDateTime endRental){
        Duration duration = Duration.between(startRental, endRental);
        Long hours = duration.toHours();
        Long minutes = duration.toMinutes() % 60;
        Long seconds = duration.toSeconds() % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public Double calculatePrice(LocalDateTime startRental, LocalDateTime endRental){
        Double fraction = 4.0;
        Double fractionPrice = 0.0;
        Double hourPrice;
        Double totalPrice;
        
        Duration duration = Duration.between(startRental, endRental);
        Long minutes = duration.toMinutes();
        Long hours = duration.toHours();

        if(minutes > 15){
            if(minutes/15 > 3){
                fractionPrice = fraction * 4;
            }else if(minutes/15 > 2){
                fractionPrice = fraction * 3;
            }else if(minutes/15 > 1){
                fractionPrice = fraction * 2;
            }else{
                fractionPrice = fraction;
            }
        }
        
        hourPrice = hours * fraction *4;
        totalPrice = hourPrice + fractionPrice;

        if(totalPrice>50){
            totalPrice = 50.0;
        }
        return totalPrice;
    }
    
}
