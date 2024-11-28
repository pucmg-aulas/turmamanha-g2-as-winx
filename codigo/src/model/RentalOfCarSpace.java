package model;

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

	public RentalOfCarSpace() {
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

	public String calculateTime(LocalDateTime startRental, LocalDateTime endRental) {
		Duration duration = Duration.between(startRental, endRental);
		Long hours = duration.toHours();
		Long minutes = duration.toMinutes() % 60;
		Long seconds = duration.toSeconds() % 60;
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

	public Double calculatePrice(LocalDateTime startRental, LocalDateTime endRental) {
		Double fractionPrice = 4.0; // Base price per 15-minute fraction
		Double totalPrice = 0.0;

		Duration duration = Duration.between(startRental, endRental);
		Long totalMinutes = duration.toMinutes();
		
		// Calculate number of complete 15-minute fractions
		Long fractions = (totalMinutes + 14) / 15; // Round up to next fraction
		
		// Calculate base price (R$4 per fraction)
		totalPrice = fractionPrice * fractions;

		// Apply spot type modifiers
		if (this.carSpace instanceof Vip) {
			totalPrice *= (1 + ((Vip) this.carSpace).getIncrease());
		} else if (this.carSpace instanceof Elder) {
			totalPrice *= (1 - ((Elder) this.carSpace).getDiscount());
		} else if (this.carSpace instanceof Pcd) {
			totalPrice *= (1 - ((Pcd) this.carSpace).getDiscount());
		}

		// Apply maximum price cap
		if (totalPrice > 50.0) {
			totalPrice = 50.0;
		}
		
		return totalPrice;
	}

}
