package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import model.Client;
import model.Park;
import model.Vehicle;
import model.CarSpace;

public class ParkDao {

    private static final String FILE_NAME = "parkdao.txt";

    public void savePark(Park park) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write("Rows: " + park.getRows() + ", Columns: " + park.getColumns() + "\n");

            for (Client client : park.getClients()) {
                writer.write("Client: " + client.getName() + " (ID: " + client.getId() + ")\n");

                List<String> writtenPlates = new ArrayList<>();

                for (int i = 0; i < park.getRows(); i++) {
                    for (int j = 0; j < park.getColumns(); j++) {
                        CarSpace spot = park.getParkingSpaces()[i][j];
                        if (spot.isOccupied()) {
                            LocalDateTime startTime = park.getParkingStartTimes()[i][j];

                            if (startTime != null) {
                                for (Vehicle vehicle : client.getVehicles()) {
                                    if (!writtenPlates.contains(vehicle.getPlate())) {
                                        String formattedDate = startTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                                        writer.write("  Vehicle: " + vehicle.getPlate() + " (Model: " + vehicle.getModel() +
                                                ") - Spot: " + spot.getSpotId() + " - Occupied on: " + formattedDate + "\n");

                                        writtenPlates.add(vehicle.getPlate());
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("Parking data saved successfully to " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void loadPark(Park park) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            Client currentClient = null;
            Vehicle currentVehicle = null;
            List<Vehicle> vehicles = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Client:")) {
                    String clientInfo = line.substring(8); 
                    String[] parts = clientInfo.split(" \\(ID: "); 
                    String clientName = parts[0];
                    int clientId = Integer.parseInt(parts[1].replace(")", "")); 

                    currentClient = park.findClientById(clientId);
                    if (currentClient == null) {
                        currentClient = new Client(clientId, clientName);
                        park.addClient(currentClient);
                    }
                } else if (line.startsWith("  Vehicle:")) {
                    
                    if (currentClient == null) {
                        System.out.println("Vehicle found before a client: " + line);
                        continue; 
                    }

                    String vehicleInfo = line.substring(11);
                    String[] vehicleParts = vehicleInfo.split(" \\(Model: |\\) - Spot: | - Occupied on: ");
                    String plate = vehicleParts[0];
                    String model = vehicleParts[1];
                    String[] positionParts = vehicleParts[2].split(", ");
                    
                    currentVehicle = new Vehicle(plate, model);
                    currentClient.addVehicle(currentVehicle);
                    
                    int row = Integer.parseInt(positionParts[0]);
                    int column = Integer.parseInt(positionParts[1]);
                    String dateTimeString = vehicleParts[3]; 

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    LocalDateTime startTime = LocalDateTime.parse(dateTimeString, formatter);
                    if (!park.occupySpot(row, column, currentClient.getId(), plate, startTime.getYear(),
                            startTime.getMonthValue(), startTime.getDayOfMonth(),
                            startTime.getHour(), startTime.getMinute())) {
                        System.out.println("Failed to occupy the spot for vehicle " + plate);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing date and time: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}
