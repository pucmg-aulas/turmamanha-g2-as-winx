package main;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.MainMenuController;
import controller.ParkingSpotsController;
import dao.ClientDao;
import dao.ParkDao;
import dao.VehicleDao;
import db.DB;
import db.DbException;
import model.Client;
import model.Park;
import model.Vehicle;
import model.RevenueTracker;
import view.MainMenuView;
import view.ParkingSpotsView;

public class Main {

	public static void main(String[] args) {
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		Park park = new Park(5, 5);

		initializeTestData(park);

		runGraphicalInterface(park);
		
		Connection conn = null;
		Statement st = null;
		try {
			conn = DB.getConnection();
	
			conn.setAutoCommit(false);

			st = conn.createStatement();
			System.out.println("Banco de Dados conectado! ");
			
		}
		catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
			} 
			catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
			}
		} 
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}

	private static void initializeTestData(Park park) {
		List<Client> clients = new ArrayList<>();
		List<Vehicle> vehicles = new ArrayList<>();	
		
		ParkDao parkDao = new ParkDao();
		ClientDao clientDao = new ClientDao();
		VehicleDao vehicleDao = new VehicleDao();

		// Carregar clientes e adicioná-los ao park
		clients = clientDao.loadClients();
		for (Client client : clients) {
			park.addClient(client);
		}

		// Carregar veículos
		vehicles = vehicleDao.loadVehicles();
		
		// Carregar configuração do estacionamento
		parkDao.loadPark(park);
		
		// Initialize RevenueTracker with park reference
		RevenueTracker revenueTracker = new RevenueTracker(park);
		park.setRevenueTracker(revenueTracker);
		
		System.out.println("Test data initialized:");
		System.out.println("Clients loaded: " + clients.size());
		System.out.println("Vehicles loaded: " + vehicles.size());
	}

	private static void runGraphicalInterface(Park park) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				MainMenuView mainMenu = new MainMenuView(); 
				@SuppressWarnings("unused")
				MainMenuController controller = new MainMenuController(mainMenu, park); 
				mainMenu.setVisible(true);
			}
		});
	}
}