package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.ParkDao;

public class Park {

	private CarSpace[][] parkingSpaces;
	private int rows;
	private int columns;
	private List<Client> clients;
	private LocalDateTime[][] parkingStartTimes;
	private int minSize = 4;
	private int maxSize = 20;
	private int numberParkingSpaces;

	private RentalOfCarSpace rentalOfCarSpace;
	private ParkDao parkDao;
		private String[][] parkingVehiclePlates;
	
		public Park() {
			this(8, 8);
		}
	
		public Park(int rows, int columns) {
			this.rows = rows;
			this.columns = columns;
			this.parkingSpaces = new CarSpace[rows][columns];
			this.clients = new ArrayList<>();
			this.parkingStartTimes = new LocalDateTime[rows][columns];
			initializeParkingSpaces();
			this.rentalOfCarSpace = new RentalOfCarSpace();
			this.parkDao = new ParkDao();
		}
	
		private void initializeParkingSpaces() {
			for (int i = 0; i < rows; i++) {
				String rowLetter = String.valueOf((char)('A' + i));
				for (int j = 0; j < columns; j++) {
					parkingSpaces[i][j] = new CarSpace(rowLetter, j + 1);
				}
			}
		}
	
		public int getNumberParkingSpaces() {
			return numberParkingSpaces;
		}
	
		public void setNumberParkingSpaces(int numberParkingSpaces) {
			this.numberParkingSpaces = numberParkingSpaces;
		}
	
		public boolean occupySpot(int row, int column, int clientId, String licensePlate, int year, int month, int day,
				int hour, int minute) {
			if (parkingSpaces[row][column].isOccupied()) {
				System.out.println("Spot is already occupied!");
				return false;
			}
	
			Client client = findClientById(clientId);
			if (client == null) {
				System.out.println("Client not found.");
				return false;
			}
	
			Vehicle selectedVehicle = null;
			for (Vehicle v : client.getVehicles()) {
				if (v.getPlate().equalsIgnoreCase(licensePlate)) {
					selectedVehicle = v;
					break;
				}
			}
	
			if (selectedVehicle == null) {
				System.out.println("Vehicle not found or not registered for this client.");
				return false;
			}
	
			LocalDateTime startParkingTime = LocalDateTime.of(year, month, day, hour, minute);
			parkingStartTimes[row][column] = startParkingTime;
			parkingSpaces[row][column].allocateSpot(rentalOfCarSpace);
			System.out.println("Spot successfully occupied by vehicle " + selectedVehicle.getModel() + " (Plate: "
					+ selectedVehicle.getPlate() + ")" + " at " + startParkingTime);
			parkDao.savePark(this);
			return true;
		}
	
		public boolean freeSpot(int row, int column, int year, int month, int day, int hour, int minute) {
			LocalDateTime startParkingTime = parkingStartTimes[row][column];
			LocalDateTime endParkingTime = LocalDateTime.of(year, month, day, hour, minute);
			if (parkingSpaces[row][column].isOccupied()) {
				parkingSpaces[row][column].freeSpot();
				System.out.println("Spot successfully freed.\n" + "Total price: "
						+ rentalOfCarSpace.calculatePrice(startParkingTime, endParkingTime) + "\nTotal Time: "
						+ rentalOfCarSpace.calculateTime(startParkingTime, endParkingTime));
	
				return true;
			} else {
				System.out.println("The spot is already free!");
				return false;
			}
		}
	
		public void listCarSpacesAvailables() {
			System.out.println("Parking Entrance");
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					CarSpace spot = parkingSpaces[i][j];
					System.out.print((spot.isOccupied() ? "[X]" : "[ ]") + spot.getSpotId() + " ");
				}
				System.out.println();
			}
			System.out.println("Parking Exit");
		}
	
		public void addClient(Client client) {
			clients.add(client);
			System.out.println("Client " + client.getName() + " added successfully.");
		}
	
		public Client findClientById(int id) {
			for (Client client : clients) {
				if (client.getId() == id) {
					return client;
				}
			}
			return null;
		}
	
		public void registerVehicleForClient() {
			try (Scanner sc = new Scanner(System.in)) {
				System.out.print("Enter the client ID: ");
				int clientId = sc.nextInt();
				sc.nextLine();
	
				Client client = findClientById(clientId);
				if (client == null) {
					System.out.println("Client not found.");
					return;
				}
	
				System.out.print("Enter vehicle license plate: ");
				String licensePlate = sc.nextLine();
	
				System.out.print("Enter vehicle model: ");
				String model = sc.nextLine();
	
				Vehicle newVehicle = new Vehicle(licensePlate, model);
				client.addVehicle(newVehicle);
			}
		}
	
		public void listClientsAndVehicles() {
			System.out.println("Clients and their Vehicles:");
			for (Client client : clients) {
				System.out.println("Client ID: " + client.getId() + ", Name: " + client.getName());
				if (client.getVehicles().isEmpty()) {
					System.out.println("  No vehicles registered.");
				} else {
					System.out.println("  Vehicles:");
					for (Vehicle vehicle : client.getVehicles()) {
						System.out.println("    - " + vehicle.getPlate() + " (" + vehicle.getModel() + ")");
					}
				}
			}
		}
	
		public List<Client> getClients() {
			return new ArrayList<>(clients);
		}
	
		public CarSpace[][] getParkingSpaces() {
			return parkingSpaces;
		}
	
		public int getRows() {
			return rows;
		}
	
		public int getColumns() {
			return columns;
		}
		
		public void setRows(int rows) {
			this.rows = rows;
		}
	
		public void setColumns(int columns) {
			this.columns = columns;
		}
	
		public LocalDateTime[][] getParkingStartTimes() {
			return parkingStartTimes;
		}
	
		public void setParkingStartTimes(LocalDateTime[][] parkingStartTimes) {
			this.parkingStartTimes = parkingStartTimes;
		}
	
		public void setClients(List<Client> clients) {
			this.clients = clients;
		}
	
		public boolean isSpotOccupied(int row, int column) {
			return getParkingSpaces()[row][column].isOccupied();
		}
		
		public String[][] getParkingVehiclePlates() {
			return parkingVehiclePlates;
		}
	
		public void setParkingVehiclePlates(String[][] parkingVehiclePlates) {
			this.parkingVehiclePlates = parkingVehiclePlates;
		}
		
		public boolean verifySize(int value) {
			return value > minSize && value < maxSize;
		}

}
