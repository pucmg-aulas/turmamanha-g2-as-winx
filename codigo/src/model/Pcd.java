package model;

public class Pcd extends CarSpace {
    private static final double DISCOUNT = 0.13;
    private boolean extraSpace;
    private double discountedPrice;

    // Construtor
    public Pcd(boolean extraSpace, double baseValue) {
        super(baseValue);  // Chama o construtor da classe pai (CarSpace)
        this.extraSpace = extraSpace;
        this.discountedPrice = calculateDiscountedPrice(baseValue);
    }

    // Método para calcular o preço com desconto
    private double calculateDiscountedPrice(double baseValue) {
        return baseValue * (1 - DISCOUNT);
    }

    // Getters e Setters
    public boolean hasExtraSpace() {
        return extraSpace;
    }

    public void setExtraSpace(boolean extraSpace) {
        this.extraSpace = extraSpace;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public double getTotalValue() {
        return discountedPrice;
    }

    public double getDiscount() {
        return DISCOUNT;
    }
}
