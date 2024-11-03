package main;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.ParkingSpotsController;
import dao.ClientDao;
import dao.ParkDao;
import dao.VehicleDao;
import model.Client;
import model.Park;
import model.Vehicle;
import view.ParkingSpotsView;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Park park = new Park(5, 5);

		initializeTestData(park);

		System.out.println("Choose interface type:");
		System.out.println("1 - Console Interface");
		System.out.println("2 - Graphical Interface");

		int interfaceChoice = sc.nextInt();

		if (interfaceChoice == 1) {
			runConsoleInterface(sc, park);
		} else if (interfaceChoice == 2) {
			runGraphicalInterface(park);
		}

		sc.close();
	}

	private static void initializeTestData(Park park) {
		
		List<Client> clients = new ArrayList<>();
		List<Vehicle> vehicles = new ArrayList<>();	
		
		Client c1 = new Client(6, "Alice");
		Client c2 = new Client(7, "Bob");
		Client c3 = new Client(8, "Christian");
		Client c4 = new Client(9, "Maria");
		
		park.addClient(c1);
		park.addClient(c2);
		park.addClient(c3);
		park.addClient(c4);

		Vehicle onix = new Vehicle("123", "onix");
		Vehicle voyage = new Vehicle("124", "voyage");
		Vehicle corolla = new Vehicle("125", "corolla");
		Vehicle bmw = new Vehicle("000", "bmw");

		c1.addVehicle(onix);
		c1.addVehicle(voyage);
		c2.addVehicle(corolla);
		c3.addVehicle(bmw);

		park.occupySpot(3, 3, 6, "123", 2024, 11, 3, 13, 30);
		park.occupySpot(0, 1, 6, "124", 2024, 11, 3, 13, 30);
		park.occupySpot(0, 0, 7, "125", 2024, 11, 3, 13, 30);
		
		ParkDao parkDao = new ParkDao();
		ClientDao clientDao = new ClientDao();
		VehicleDao vehicleDao = new VehicleDao();
		
		clients.add(c1);
		clients.add(c2);
		clients.add(c3);
		clients.add(c4);
		
		vehicles.add(onix);
		vehicles.add(voyage);
		vehicles.add(corolla);
		vehicles.add(bmw);
		
		clientDao.saveClients(clients);
		parkDao.savePark(park);
		vehicleDao.saveAllVehicles(vehicles);
	}

	private static void runConsoleInterface(Scanner sc, Park park) {
		int option;

		do {
			System.out.println("WELCOME TO WINX PARKING!\nPLEASE SELECT AN OPTION");
			System.out.println(
					"0 - Leave\n1 - Register Client\n2 - Register Client Vehicle\n3 - See parking spots\n4 - Ocuppy Spot\n5 - Free Spot\n6 - List clients and vehicles");
			option = sc.nextInt();

			switch (option) {
			case 1:
				System.out.println("1 - Register new client.\n2 - Register anonymous client");
				int optionClient = sc.nextInt();
				sc.nextLine();
				int clientId1 = 0;
				switch (optionClient) {
				case 1:
					System.out.print("Enter client name: ");
					String clientName = sc.nextLine();
					Client newClient = new Client(clientId1, clientName);
					park.addClient(newClient);
					System.out
							.println("Client registered: " + newClient.getName() + " (ID: " + newClient.getId() + ")");
					clientId1++;
					break;
				case 2:
					Client anonymousClient = new Client(clientId1, "AnonymousClient");
					park.addClient(anonymousClient);
					System.out.println("Anonymous client registered!");
					clientId1++;
				}
				break;
			case 2:
				park.registerVehicleForClient();
				break;
			case 3:
				park.listCarSpacesAvailables();
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

			    System.out.print("Enter the year: ");
			    int year = sc.nextInt();
			    System.out.print("Enter the month: ");
			    int month = sc.nextInt();
			    System.out.print("Enter the day: ");
			    int day = sc.nextInt();
			    
			    System.out.print("Enter the hour: ");
			    int hours = sc.nextInt();
			    System.out.print("Enter the minutes: ");
			    int minutes = sc.nextInt();
			    sc.nextLine();

			    boolean success = park.occupySpot(row, column, idClient, licensePlate, year, month, day, hours, minutes);

			    if (success) {
			        System.out.println("Vehicle parked successfully.");
			    } else {
			        System.out.println("Failed to park the vehicle.");
			    }

			    break;

			case 5:
			    System.out.println("What spot do you want to free?");
			    System.out.print("Enter row: ");
			    row = sc.nextInt();
			    System.out.print("Enter column: ");
			    column = sc.nextInt();
			    sc.nextLine();
			    System.out.print("Enter the year: ");
			    int yearF = sc.nextInt();
			    System.out.print("Enter the month: ");
			    int monthF = sc.nextInt();
			    System.out.print("Enter the day: ");
			    int dayF = sc.nextInt();
			    
			    System.out.print("Enter the hour: ");
			    int hoursF = sc.nextInt();
			    System.out.print("Enter the minutes: ");
			    int minutesF = sc.nextInt();
			    sc.nextLine();

			    boolean successF = park.freeSpot(row, column, yearF, monthF, dayF, hoursF, minutesF);

			    if (successF) {
			        System.out.println("Vehicle parked successfully.");
			    } else {
			        System.out.println("Failed to park the vehicle.");
			    }
			    
			    park.freeSpot(row, column, yearF, monthF, dayF, hoursF, minutesF);
			    System.out.println("Spot freed successfully.");
			    break;
			case 6:
				park.listClientsAndVehicles();
				break;
			default:

				break;
			}
		} while (option != 0);
	}

	private static void runGraphicalInterface(Park park) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				ParkingSpotsView view = new ParkingSpotsView(park.getRows(), park.getColumns());
				ParkingSpotsController controller = new ParkingSpotsController(view, park);
				
				/*
				AddClientView view = new AddClientView();
				AddClientController controller = new AddClientController(view, park);
				*/
				/*
				AddVehicleView view = new AddVehicleView();
				AddVehicleController controller = new AddVehicleController(view, park);
				*/
				view.setVisible(true);
			}
		});
	}
}