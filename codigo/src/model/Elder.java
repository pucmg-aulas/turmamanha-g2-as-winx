package model;

public class Elder extends CarSpace {
    private static final double DISCOUNT = 0.15;
    private double discountPrice;

    // Construtor
    public Elder(double baseValue) {
        super(baseValue);  // Chama o construtor da classe pai (CarSpace)
        this.discountPrice = calculateDiscountPrice(baseValue);
    }

    // Método para calcular o preço com desconto
    private double calculateDiscountPrice(double baseValue) {
        return baseValue * (1 - DISCOUNT);
    }

    // Getter para obter o preço com desconto
    public double getDiscountPrice() {
        return discountPrice;
    }

    // Método para obter o valor total (preço com desconto)
    public double getTotalValue() {
        return discountPrice;
    }
}
