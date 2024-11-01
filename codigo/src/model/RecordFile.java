package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RecordFile { 

    public void salvaDados(List<Client> listaClientes) {
        try (BufferedWriter saida = new BufferedWriter(new FileWriter("RecordFile.txt"))) {
            for (Client client : listaClientes) {
                // Salvando o nome do cliente
                saida.write("Nome: " + client.getName());
                saida.newLine();
                saida.write("ID: " + client.getId());
                saida.newLine();

                // Salvando os veículos do cliente
                List<Vehicle> vehicles = client.getVehicles();
                if (vehicles != null && !vehicles.isEmpty()) {
                    for (Vehicle vehicle : vehicles) {
                        saida.write("Veículo: " + vehicle.toString()); // Assuming toString() provides vehicle details
                        saida.newLine();
                    }
                } else {
                    saida.write("Nenhum veículo registrado.");
                    saida.newLine();
                }

                // Removing the RentalOfCarSpace information
                // If you want to keep this section but just without RentalOfCarSpace, it can be left empty.
                saida.write("Nenhum aluguel ativo.");
                saida.newLine();

                saida.newLine(); // Add an extra line for better readability between clients
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }
}
