package controller;

import model.Client;
import model.Park;
import view.AddClientView;

public class AddClientController {
    private AddClientView view;
    private Park park;
    private int clientId;

    public AddClientController(AddClientView view, Park park) {
        this.view = view;
        this.park = park;
        this.clientId = 1;

        this.view.addRegisterNormalListener(e -> registerNormalClient());
        this.view.addRegisterAnonymousListener(e -> registerAnonymousClient());
    }

    private void registerNormalClient() {
        String name = view.getClientName().trim();
        
        if (name.isEmpty()) {
            view.showErrorMessage("Please enter a client name!");
            return;
        }

        Client newClient = new Client(clientId, name);
        park.addClient(newClient);
        
        String successMessage = "Client registered successfully!\n" +
                              "Name: " + name + "\n" +
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