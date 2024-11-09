package model;

public class ClientElder extends Client {
    private static final double DISCOUNT = 0.15;
    private double discountPrice;

    public ClientElder(int id, String name) {
        super(id, name);
    }

    public ClientElder(double baseValue) {
        super(baseValue);
        this.discountPrice = calculateDiscountPrice(baseValue);
    }

    private double calculateDiscountPrice(double baseValue) {
        return baseValue * (1 - DISCOUNT);
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public double getTotalValue() {
        return discountPrice;
    }
    
}
