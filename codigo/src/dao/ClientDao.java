package dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import model.Client;
import model.Vehicle;

public class ClientDao {

    private static final String FILE_PATH = "codigo/clients.txt";

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
        String clientQuery = "SELECT id, name FROM clients";
        String vehicleQuery = "SELECT plate, model FROM vehicles WHERE client_id = ?";

        try (Connection connection = DB.getConnection();
             PreparedStatement clientStatement = connection.prepareStatement(clientQuery);
             PreparedStatement vehicleStatement = connection.prepareStatement(vehicleQuery)) {

            ResultSet clientResultSet = clientStatement.executeQuery();

            while (clientResultSet.next()) {
                int clientId = clientResultSet.getInt("id");
                String clientName = clientResultSet.getString("name");

                Client client = new Client(clientId, clientName);

                vehicleStatement.setInt(1, clientId);
                ResultSet vehicleResultSet = vehicleStatement.executeQuery();

                while (vehicleResultSet.next()) {
                    String vehiclePlate = vehicleResultSet.getString("plate");
                    String vehicleModel = vehicleResultSet.getString("model");

                    Vehicle vehicle = new Vehicle(vehiclePlate, vehicleModel);
                    client.addVehicle(vehicle);
                }

                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }
    
    
}