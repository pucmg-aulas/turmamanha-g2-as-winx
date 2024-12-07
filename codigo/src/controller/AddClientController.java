package controller;

import model.Client;
import model.ClientElder;
import model.ClientPcd;
import model.ClientVip;
import model.Park;
import view.AddClientView;

public class AddClientController {
    private AddClientView view;
    private Park park;
    private int clientId;

    public AddClientController(AddClientView view, Park park) {
        this.view = view;
        this.park = park;
        this.clientId = park.getClients().size() + 1;

        this.view.addRegisterNormalListener(e -> registerNormalClient());
        this.view.addRegisterAnonymousListener(e -> registerAnonymousClient());
    }

    private void registerNormalClient() {
        String name = view.getClientName().trim();
        String clientType = view.getSelectedClientType();
        
        if (name.isEmpty()) {
            view.showErrorMessage("Please enter a client name!");
            return;
        }

        Client newClient;
        switch (clientType) {
            case "ELDER":
                newClient = new ClientElder(clientId, name);
                break;
            case "PCD":
                newClient = new ClientPcd(clientId, name);
                break;
            case "VIP":
                newClient = new ClientVip(clientId, name);
                break;
            default:
                newClient = new Client(clientId, name);
        }

        park.addClient(newClient);
        
        String successMessage = "Client registered successfully!\n" +
                              "Name: " + name + "\n" +
                              "Type: " + clientType + "\n" +
                              "ID: " + clientId;
        view.showSuccessMessage(successMessage);
        
        clientId++;
        view.clearFields();
    }

    private void registerAnonymousClient() {
        Client anonymousClient = new Client(clientId, "AnonymousClient");
        park.addClient(anonymousClient);
        
        String successMessage = "Anonymous client registered successfully!\n" +
                              "ID: " + clientId;
        view.showSuccessMessage(successMessage);
        
        clientId++;
        view.clearFields();
    }
}