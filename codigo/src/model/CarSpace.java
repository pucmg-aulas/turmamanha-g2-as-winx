package model;

public class CarSpace {

    private String row;
    private int number;
    private boolean isOccupied;
    private double baseValue;

    public CarSpace(){
        
    }
    
    public CarSpace(String row, int number) {
        this.row = row.toUpperCase();
        this.number = number;
        this.isOccupied = false;
    }

    public CarSpace(String row, int number, double baseValue) {
        this.row = row.toUpperCase();
        this.number = number;
        this.baseValue = baseValue;
        this.isOccupied = false;
    }

    public CarSpace(double baseValue) {
        this.baseValue = baseValue;
    }

    public String getSpotId() {
        return String.format("%s%02d", row, number);
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row.toUpperCase();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
            System.out.println("Spot allocated: " + getSpotId());
            
        } else {
            System.out.println("Spot is already occupied.");
        }
    }

    public void freeSpot() {
        if (isOccupied) {
            this.isOccupied = false;
            System.out.println("Spot freed: " + getSpotId());
        } else {
            System.out.println("Spot is already available.");
        }
    }

    public double getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(double baseValue) {
        this.baseValue = baseValue;
    }

}

