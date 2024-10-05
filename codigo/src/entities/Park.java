package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Park {

    private int numberParkingSpaces;
    private int idPark;
    private boolean parkingSpaces[][];
    private int rows;
    private int columns;
    private List<Client> clients;
    private Scanner sc;

    public Park() {
        this.sc = new Scanner(System.in);
        this.clients = new ArrayList<>();
    }

    public Park(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.parkingSpaces = new boolean[rows][columns];
    }

    public int getNumberParkingSpaces() {
        return numberParkingSpaces;
    }

    public void setNumberParkingSpaces(int numberParkingSpaces) {
        this.numberParkingSpaces = numberParkingSpaces;
    }

    public boolean occupySpot(int row, int column, int clientId, String licensePlate) {
        if (parkingSpaces[row][column]) {
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

        LocalDateTime parkingTime = LocalDateTime.now();

        parkingSpaces[row][column] = true;
        System.out.println("Spot successfully occupied by vehicle " + selectedVehicle.getModel()
                + " (Plate: " + selectedVehicle.getPlate() + ")"
                + " at " + parkingTime);

        return true;
    }

    public boolean freeSpot(int row, int column) {
        if (parkingSpaces[row][column]) {
            parkingSpaces[row][column] = false;
            System.out.println("Spot successfully freed.");
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
                System.out.print((parkingSpaces[i][j] ? "[X]" : "[ ]") + " ");
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
