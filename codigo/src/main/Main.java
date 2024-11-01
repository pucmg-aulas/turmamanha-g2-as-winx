package main;

import java.util.Scanner;
import java.awt.EventQueue;

import model.Client;
import model.Park;
import model.Vehicle;
import model.RecordFile;
import view.AddClientView;
import view.AddVehicleView;
import view.ParkingSpotsView;
import controller.AddClientController;
import controller.AddVehicleController;
import controller.ParkingSpotsController;


public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Park park = new Park(5, 5);

		// Adicionando clientes e veículos de teste
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
		// Seus dados de teste
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

		park.occupySpot(3, 3, 6, "123");
		park.occupySpot(0, 1, 6, "124");
		park.occupySpot(0, 0, 7, "125");
	}

	private static void runConsoleInterface(Scanner sc, Park park) {
		int option;
		int clientId = 0;

		do {
			System.out.println("WELCOME TO WINX PARKING!\nPLEASE SELECT AN OPTION");
			System.out.println(
					"0 - Leave\n1 - Register Client\n2 - Register Client Vehicle\n3 - See parking spots\n4 - Ocuppy Spot\n5 - Free Spot\n6 - List clients and vehicles\n7 - Record File ");
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

				boolean success = park.occupySpot(row, column, idClient, licensePlate);

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
				park.freeSpot(row, column);
				break;
			case 6:
				park.listClientsAndVehicles();
				break;
			case 7:
			RecordFile recordFile = new RecordFile();
                recordFile.salvaDados(park.getClients());
                System.out.println("Client data saved successfully.");
                break;
           
            default:
                System.out.println("Invalid option. Please try again.");
                break;

			
			}
		} while (option != 0);
	}

	private static void runGraphicalInterface(Park park) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				/*
				ParkingSpotsView view = new ParkingSpotsView(park.getRows(), park.getColumns());
				ParkingSpotsController controller = new ParkingSpotsController(view, park);
				*/

				AddClientView view = new AddClientView();
				AddClientController controller = new AddClientController(view, park);
				/*
				AddVehicleView view = new AddVehicleView();
				AddVehicleController controller = new AddVehicleController(view, park);
				*/
				view.setVisible(true);
			}
		});
	}
}