package model;

public class Vip extends CarSpace {
    private static final double INCREASE = 0.20;
    private boolean nextToGate;
    private boolean coverage;
    private double increaseValue;

    // Construtor
    public Vip(boolean nextToGate, boolean coverage, double baseValue) {
        super(baseValue);  // Chama o construtor da classe pai (CarSpace)
        this.nextToGate = nextToGate;
        this.coverage = coverage;
        this.increaseValue = calculateIncreaseValue(baseValue);
    }

    // Método para calcular o valor adicional com base no INCREASE
    private double calculateIncreaseValue(double baseValue) {
        return baseValue * INCREASE;
    }

    // Getters e Setters
    public boolean isNextToGate() {
        return nextToGate;
    }

    public void setNextToGate(boolean nextToGate) {
        this.nextToGate = nextToGate;
    }

    public boolean hasCoverage() {
        return coverage;
    }

    public void setCoverage(boolean coverage) {
        this.coverage = coverage;
    }

    public double getIncreaseValue() {
        return increaseValue;
    }

    public double getTotalValue() {
        return super.getBaseValue() + increaseValue;
    }
}
