package model;

import java.util.ArrayList;

public class ClientVip extends Client{
   
    private static final double DISCOUNT = 0.30;
    private double discountPrice;

    public ClientVip(double baseValue) {
        super(baseValue);
        this.discountPrice = calculateDiscountPrice(baseValue);
    }

     public ClientVip(int id, String name) {
        super(id, name);
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
