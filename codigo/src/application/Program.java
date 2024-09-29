package application;

import java.time.Duration;
import java.time.LocalDateTime;


import entities.RentalOfCarSpace;
public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		//test calculatePrice and calculateTime
		RentalOfCarSpace r1 = new RentalOfCarSpace(null, null, null, null, 0);
		LocalDateTime startDateTime = LocalDateTime.of(2024, 9, 29, 8, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2024, 9, 29, 12, 46);

		String duration = r1.calculateTime(startDateTime, endDateTime);
		Double totalPrice = r1.calculatePrice(startDateTime, endDateTime);

		System.out.println("You stayed on the parking lot for: " + duration);
		System.out.println("The total price of the ticket is: " + totalPrice);
	}

}
