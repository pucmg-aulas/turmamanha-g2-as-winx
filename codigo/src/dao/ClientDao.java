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
                if (parts[0].equals("END")) {
                    clients.add(client);
                    client = null;
                } else if (parts[0].equals("V") && client != null) {
                    Vehicle vehicle = new Vehicle(parts[1], parts[2]);
                    client.getVehicles().add(vehicle);
                } else {
                    client = new Client(Integer.parseInt(parts[0]), parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clients;
    }
}