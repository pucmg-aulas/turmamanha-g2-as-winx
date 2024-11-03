package dao;

import model.Park;
import model.Vehicle;
import model.Client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParkDao {

    private static final String FILE_NAME = "parkdao.txt";

    public void savePark(Park park) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(String.valueOf(park.getClients().size()));
            writer.newLine();
            
            for (Client client : park.getClients()) {
                writer.write(client.getId() + "," + client.getName());
                writer.newLine();
                for (Vehicle vehicle : client.getVehicles()) {
                    writer.write(vehicle.getPlate() + "," + vehicle.getModel());
                    writer.newLine();
                }
                writer.newLine();
            }
            System.out.println("Clients saved to " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error saving clients: " + e.getMessage());
        }
    }

    public Park loadPark() {
        Park park = new Park();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            int numberOfClients = Integer.parseInt(reader.readLine());

            for (int i = 0; i < numberOfClients; i++) {
                line = reader.readLine();
                String[] clientData = line.split(",");
                int clientId = Integer.parseInt(clientData[0]);
                String clientName = clientData[1];
                Client client = new Client(clientId, clientName);
                
                while ((line = reader.readLine()) != null && !line.isEmpty()) {
                    String[] vehicleData = line.split(",");
                    String plate = vehicleData[0];
                    String model = vehicleData[1];
                    client.addVehicle(new Vehicle(plate, model));
                }
                
                park.addClient(client);
            }
            System.out.println("Clients loaded from " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error loading clients: " + e.getMessage());
        }
        return park;
    }
}
