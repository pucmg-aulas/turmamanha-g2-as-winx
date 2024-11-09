package model;

public class CarSpace {

    private String spotId;
    private boolean isOccupied;
    private double baseValue;

    public CarSpace(){
        
    }
    
    public CarSpace(String spotId) {
        this.spotId = spotId;
        this.isOccupied = false; 
    }

 
    public CarSpace(double baseValue) {
        this.baseValue = baseValue;
        this.isOccupied = false;
    }

    public String getSpotId() {
        return spotId;
    }

    public void setSpotId(String spotId) {
        this.spotId = spotId;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    
    public void allocateSpot(RentalOfCarSpace rentalOfCarSpace) {
        if (!isOccupied) {
            this.isOccupied = true;
            System.out.println("Spot allocated: " + this.spotId);
            
        } else {
            System.out.println("Spot is already occupied.");
        }
    }

    public void freeSpot() {
        if (isOccupied) {
            this.isOccupied = false;
            System.out.println("Spot freed: " + this.spotId);
        } else {
            System.out.println("Spot is already available.");
        }
    }

    public double getBaseValue() {
        return baseValue;
    }


}

