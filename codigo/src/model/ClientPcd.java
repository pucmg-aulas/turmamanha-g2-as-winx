package model;

public class ClientPcd extends Client {
    private static final double DISCOUNT = 0.50;
    private double discountPrice;

    public ClientPcd(int id, String name) {
        super(id, name);
    }

    public ClientPcd(double baseValue) {
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
