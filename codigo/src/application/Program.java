package application;

import entities.Park;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//test calculatePrice and calculateTime
		/*
		RentalOfCarSpace r1 = new RentalOfCarSpace(null, null, null, null, 0);
		LocalDateTime startDateTime = LocalDateTime.of(2024, 9, 29, 8, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2024, 9, 29, 12, 46);

		String duration = r1.calculateTime(startDateTime, endDateTime);
		Double totalPrice = r1.calculatePrice(startDateTime, endDateTime);

		System.out.println("You stayed on the parking lot for: " + duration);
		System.out.println("The total price of the ticket is: " + totalPrice);
		 
		 */

		Park ui = new Park(0, 0, 0, 0);
		int option;
		do { 
			System.out.println("WELCOME TO WINX PARKING!\nPLEASE SELECT AN OPTION");
			System.out.println("0 - Leave\n1 - Register Client\n2 - Register Vehicle\n3 - See parking spots\n4 - Get your parking ticket");
			option = sc.nextInt();
			switch (option) {
				case 1:
					
					break;
				case 2:
					
					break;
				case 3:
					ui.listCarSpacesAvailables();
				case 4: 

					break;
				default:
					
					break;
			}
		} while (option!=0);

		//mat.printPark();
		sc.close();
	}

}
