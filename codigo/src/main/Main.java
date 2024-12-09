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
	    Park park = new Park(5, 5);
	    runGraphicalInterface(park);

	    Connection conn = null;
	    Statement st = null;
	    try {
	        conn = DB.getConnection();
	        if (conn == null) {
	            throw new DbException("Database connection failed");
	        }

	        conn.setAutoCommit(true); 

	        ParkDao parkDao = new ParkDao();
	        ClientDao clientDao = new ClientDao();
	        VehicleDao vehicleDao = new VehicleDao();

	        List<Client> clients = clientDao.loadClients();
	        List<Vehicle> vehicles = vehicleDao.loadVehicles();
	        
	        parkDao.loadPark(park);

	        RevenueTracker revenueTracker = new RevenueTracker(park);
	        park.setRevenueTracker(revenueTracker);

	        System.out.println("Test data initialized:");
	        System.out.println("Clients loaded: " + clients.size());
	        System.out.println("Vehicles loaded: " + vehicles.size());

	    } catch (SQLException | DbException e) {
	        System.err.println("Database error: " + e.getMessage());
	        e.printStackTrace();  

	        if (e instanceof SQLException) {
	            SQLException sqlEx = (SQLException) e;
	            System.err.println("SQL State: " + sqlEx.getSQLState());
	            System.err.println("Error Code: " + sqlEx.getErrorCode());
	        }
	    } finally {
	        try {
	            if (st != null) DB.closeStatement(st);
	            if (conn != null) DB.closeConnection();
	        } catch (Exception e) {
	            System.err.println("Error closing database resources: " + e.getMessage());
	        }
	    }
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