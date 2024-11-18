package main;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.MainMenuController;
import controller.ParkingSpotsController;
import dao.ClientDao;
import dao.ParkDao;
import dao.VehicleDao;
import model.Client;
import model.Park;
import model.Vehicle;
import view.MainMenuView;
import view.ParkingSpotsView;

public class Main {

	public static void main(String[] args) {
		Park park = new Park(5, 5);

		initializeTestData(park);

		runGraphicalInterface(park);
	}

	private static void initializeTestData(Park park) {
		
		List<Client> clients = new ArrayList<>();
		List<Vehicle> vehicles = new ArrayList<>();	
		
		
		ParkDao parkDao = new ParkDao();
		ClientDao clientDao = new ClientDao();
		VehicleDao vehicleDao = new VehicleDao();

		
		clientDao.loadClients();
		parkDao.loadPark(park);
		vehicleDao.loadVehicles();
	}

	private static void runGraphicalInterface(Park park) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				MainMenuView mainMenu = new MainMenuView(); 
				MainMenuController controller = new MainMenuController(mainMenu, park); 
				mainMenu.setVisible(true);
			}
		});
	}
}