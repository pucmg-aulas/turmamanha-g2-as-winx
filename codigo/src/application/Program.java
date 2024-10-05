package application;

import entities.Client;
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

		Park ui = new Park(8, 8);
		ui.occupySpot(3, 3);
		ui.occupySpot(3, 4);
		ui.occupySpot(3, 5);
		int option;
		do { 
			System.out.println("WELCOME TO WINX PARKING!\nPLEASE SELECT AN OPTION");
			System.out.println("0 - Leave\n1 - Register Client\n2 - Register Vehicle\n3 - See parking spots\n4 - Ocuppy Spot\n5 - Free Spot");
			option = sc.nextInt();
			switch (option) {
				case 1:
					System.out.println("1 - Register new client.\n2 - Register anonymous client");
					int optionClient = sc.nextInt();
					int clientId = 0;
					switch (optionClient) {
						case 1:
							System.out.print("Enter client name: ");
							String clientName = sc.nextLine();
							Client newClient = new Client(clientId, clientName);
							System.out.println("Client registered: " + newClient.getName() + " (ID: " + newClient.getIdClient() + ")");
							clientId++;
							break;
						case 2: 
							Client anonymousClient = new Client(clientId, "Default");
							anonymousClient.setAnonymous(clientId);
							System.out.println("Anonymous client registered!");
							clientId++;
						
					}
					break;
				case 2:
					
					break;
				case 3:
					ui.listCarSpacesAvailables();
					sc.nextLine();
					break;
				case 4: 
					System.out.println("What spot do you want to ocuppy?");
					int row = sc.nextInt();
					int column = sc.nextInt();
					sc.nextLine();
					ui.occupySpot(row, column);

					break;
				case 5:
					System.out.println("What spot do you want to free?");
					row = sc.nextInt();
					column = sc.nextInt();
					sc.nextLine();
					ui.freeSpot(row, column);
					break;
				default:
					
					break;
			}
		} while (option!=0);

		//mat.printPark();
		sc.close();
	}

}
