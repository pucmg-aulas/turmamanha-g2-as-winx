package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Client;
import model.Vehicle;

public class ClientDao {

    private static final String FILE_PATH = "clients.txt";

    public void saveClients(List<Client> clients) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Client client : clients) {
                writer.write(client.getId() + "," + client.getName());
                writer.newLine();
                for (Vehicle vehicle : client.getVehicles()) {
                    writer.write("V," + vehicle.getPlate() + "," + vehicle.getModel());
                    writer.newLine();
                }
                writer.write("END"); 
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Client> loadClients() {
        List<Client> clients = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            Client client = null;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                
                if (parts.length >= 2 && !parts[0].equals("END") && !parts[0].equals("V")) {
                    int clientId = Integer.parseInt(parts[0].trim());
                    String clientName = parts[1].trim();
                    client = new Client(clientId, clientName); 
                    clients.add(client);
                } 
                else if (parts.length == 3 && parts[0].equals("V") && client != null) {
                    String vehiclePlate = parts[1].trim();
                    String vehicleModel = parts[2].trim();
                    Vehicle vehicle = new Vehicle(vehiclePlate, vehicleModel); 
                    client.addVehicle(vehicle); 
                }
              
                else if (parts.length == 1 && parts[0].equals("END")) {
                    client = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clients;
    }
}