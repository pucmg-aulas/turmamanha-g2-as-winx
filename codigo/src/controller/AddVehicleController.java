package controller;

import model.Park;
import model.Vehicle;
import model.Client;
import view.AddVehicleView;

public class AddVehicleController {
    private AddVehicleView view;
    private Park park;

    public AddVehicleController(AddVehicleView view, Park park) {
        this.view = view;
        this.park = park;

        this.view.addRegisterVehicleListener(e -> registerVehicle());
        
        updateClientTable();
    }

    private void updateClientTable() {
        view.updateClientTable(park.getClients());
    }

    private void registerVehicle() {
        String plate = view.getPlate().trim();
        String model = view.getModel().trim();
        String clientIdStr = view.getClientId().trim();

        if (plate.isEmpty() || model.isEmpty() || clientIdStr.isEmpty()) {
            view.showErrorMessage("All fields are required!");
            return;
        }

        if (!isNumeric(clientIdStr)) {
            view.showErrorMessage("Client ID must be a number!");
            return;
        }

        int clientId = Integer.parseInt(clientIdStr);
        
        Client client = park.findClientById(clientId);
        if (client == null) {
            view.showErrorMessage("Client not found!");
            return;
        }

        Vehicle newVehicle = new Vehicle(plate, model);
        client.addVehicle(newVehicle);

        view.showSuccessMessage("Vehicle registered successfully!\n" +
                              "Plate: " + plate + "\n" +
                              "Model: " + model + "\n" +
                              "Client ID: " + clientId);

        view.clearFields();
    }

    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}