package main;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.ParkingSpotsController;
import dao.ClientDao;
import dao.ParkDao;
import dao.VehicleDao;
import model.Client;
import model.Park;
import model.Vehicle;
import view.ParkingSpotsView;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Park park = new Park(5, 5);

		initializeTestData(park);

		System.out.println("Choose interface type:");
		System.out.println("1 - Console Interface");
		System.out.println("2 - Graphical Interface");

		int interfaceChoice = sc.nextInt();

		if (interfaceChoice == 1) {
			//runConsoleInterface(sc, park);
		} else if (interfaceChoice == 2) {
			runGraphicalInterface(park);
		}

		sc.close();
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
				
				ParkingSpotsView view = new ParkingSpotsView(park.getRows(), park.getColumns());
				ParkingSpotsController controller = new ParkingSpotsController(view, park);
				
				/*
				AddClientView view = new AddClientView();
				AddClientController controller = new AddClientController(view, park);
				*/
				/*
				AddVehicleView view = new AddVehicleView();
				AddVehicleController controller = new AddVehicleController(view, park);
				*/
				view.setVisible(true);
			}
		});
	}
}