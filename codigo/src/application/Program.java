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
        //ocuppying spots test
        Park ui = new Park(8, 8);
        Park park = new Park();
        Client c1 = new Client(10, "Christian");
        Client c2 = new Client(9, "Maria");
        ui.occupySpot(3, 3);
        ui.occupySpot(3, 4);
        ui.occupySpot(3, 5);

        int option;
        do {
            System.out.println("WELCOME TO WINX PARKING!\nPLEASE SELECT AN OPTION");
            System.out.println("0 - Leave\n1 - Register Client\n2 - Register Client Vehicle\n3 - See parking spots\n4 - Ocuppy Spot\n5 - Free Spot");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("1 - Register new client.\n2 - Register anonymous client");
                    int optionClient = sc.nextInt();
                    sc.nextLine();
                    int clientId = 0;
                    switch (optionClient) {
                        case 1:
                            System.out.print("Enter client name: ");
                            String clientName = sc.nextLine();
                            Client newClient = new Client(clientId, clientName);
                            park.addClient(newClient);
                            System.out.println("Client registered: " + newClient.getName() + " (ID: " + newClient.getId() + ")");
                            clientId++;
                            break;
                        case 2:
                            Client anonymousClient = new Client(clientId, "AnonymousClient");
                            park.addClient(anonymousClient);
                            System.out.println("Anonymous client registered!");
                            clientId++;
                    }
                    break;
                case 2:
                    park.registerVehicleForClient();
                    break;
                case 3:
                    ui.listCarSpacesAvailables();
                    sc.nextLine();
                    break;
                case 4:
                    System.out.println("What spot do you want to occupy?");
                    System.out.print("Enter row: ");
                    int row = sc.nextInt();
                    System.out.print("Enter column: ");
                    int column = sc.nextInt();
                    sc.nextLine(); 

                    System.out.print("Enter the client ID: ");
                    int idClient = sc.nextInt();
                    sc.nextLine(); 

                    System.out.print("Enter the vehicle license plate: ");
                    String licensePlate = sc.nextLine();

                    boolean success = ui.occupySpot(row, column, idClient, licensePlate);

                    if (success) {
                        System.out.println("Vehicle parked successfully.");
                    } else {
                        System.out.println("Failed to park the vehicle.");
                    }

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
        } while (option != 0);

        sc.close();
    }

}
