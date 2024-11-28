package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;

import model.Park;
import model.CarSpace;
import model.Client;
import model.Vehicle;
import view.ParkingSpotsView;

public class ParkingSpotsController {
	private ParkingSpotsView view;
	private Park park;

	private void spotClicked(int row, int col) {
	    CarSpace spot = park.getParkingSpaces()[row][col];
	    if (spot.isOccupied()) {
	        view.setSelectedSpot(row, col);
	        view.getFreeButton().setEnabled(true);    
	        view.getOccupyButton().setEnabled(false); 
	    } else {
	        view.setSelectedSpot(row, col);
	        view.getFreeButton().setEnabled(false);   
	        view.getOccupyButton().setEnabled(true); 
	    }
	}

	
	public ParkingSpotsController(ParkingSpotsView view, Park park) {
		this.view = view;
		this.park = park;

		for (int i = 0; i < park.getRows(); i++) {
			for (int j = 0; j < park.getColumns(); j++) {
				final int row = i;
				final int col = j;
				view.addSpotClickListener(i, j, new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						spotClicked(row, col);
					}
				});
			}
		}

		view.addOccupyButtonListener(e -> occupySelectedSpot());
		view.addFreeButtonListener(e -> freeSelectedSpot());

		updateParkingView();
		view.updateClientTable(park.getClients());
	}

	private void occupySelectedSpot() {
		String clientIdStr = view.getClientId();
		String licensePlate = view.getLicensePlate();
		String yearStr = view.getYear();
		String monthStr = view.getMonth();
		String dayStr = view.getDay();
		String hours = view.getHour();
		String minutes = view.getMinute();

		int row = view.getSelectedRow();
		int col = view.getSelectedColumn();

		if (clientIdStr.isEmpty() || licensePlate.isEmpty() || yearStr.isEmpty() || monthStr.isEmpty()
				|| dayStr.isEmpty()) {
			view.showErrorMessage("Please fill all fields!");
			return;
		}

		if (!isNumeric(clientIdStr)) {
			view.showErrorMessage("Client ID must be a number!");
			return;
		}

		int clientId = Integer.parseInt(clientIdStr);

		if (!isNumeric(yearStr) || !isNumeric(monthStr) || !isNumeric(dayStr) || !isNumeric(hours)
				|| !isNumeric(minutes)) {
			view.showErrorMessage("Year, Month, Day, Hour, and Minute must be numbers!");
			return;
		}

		int clientYear = Integer.parseInt(yearStr);
		int clientMonth = Integer.parseInt(monthStr);
		int clientDay = Integer.parseInt(dayStr);
		int clientHour = Integer.parseInt(hours);
		int clientMinute = Integer.parseInt(minutes);

		boolean success = park.occupySpot(row, col, clientId, licensePlate, clientYear, clientMonth, clientDay,
				clientHour, clientMinute);
		if (success) {
			updateParkingView();
			view.updateClientTable(park.getClients());
			view.clearFields();
			view.showSuccessMessage("Spot successfully occupied!");
		}
	}

	private void freeSelectedSpot() {
		int row = view.getSelectedRow();
		int col = view.getSelectedColumn();

		if (row == -1 || col == -1) {
			view.showErrorMessage("Please select an occupied spot to free.");
			return;
		}

		if (!park.isSpotOccupied(row, col)) {
			view.showErrorMessage("This spot is already free!");
			return;
		}

		String yearStr = view.getYear();
		String monthStr = view.getMonth();
		String dayStr = view.getDay();
		String hourStr = view.getHour();
		String minuteStr = view.getMinute();

		// Validate input
		if (!isValidDateTime(yearStr, monthStr, dayStr, hourStr, minuteStr)) {
			view.showErrorMessage("Please enter valid date and time values.");
			return;
		}

		int year = Integer.parseInt(yearStr);
		int month = Integer.parseInt(monthStr);
		int day = Integer.parseInt(dayStr);
		int hour = Integer.parseInt(hourStr);
		int minute = Integer.parseInt(minuteStr);

		// Get parking details before freeing the spot
		String spotId = park.getParkingSpaces()[row][col].getSpotId();
		String vehiclePlate = park.getParkingVehiclePlates()[row][col];
		
		// Find client and vehicle information
		Client parkingClient = null;
		Vehicle parkedVehicle = null;
		for (Client client : park.getClients()) {
			for (Vehicle vehicle : client.getVehicles()) {
				if (vehicle.getPlate().equals(vehiclePlate)) {
					parkingClient = client;
					parkedVehicle = vehicle;
					break;
				}
			}
			if (parkingClient != null) break;
		}

		// Calculate duration and price
		LocalDateTime startTime = park.getParkingStartTimes()[row][col];
		LocalDateTime endTime = LocalDateTime.of(year, month, day, hour, minute);
		String duration = park.getRentalOfCarSpace().calculateTime(startTime, endTime);

		// Importante: Primeiro configure o CarSpace no RentalOfCarSpace
		park.getRentalOfCarSpace().setCarSpace(park.getParkingSpaces()[row][col]);

		// Agora calcule o preço - isso aplicará o aumento VIP se necessário
		double price = park.getRentalOfCarSpace().calculatePrice(startTime, endTime);

		// Free the spot
		if (park.freeSpot(row, col, year, month, day, hour, minute)) {
			// Show receipt com o preço já calculado corretamente
			String clientName = parkingClient != null ? parkingClient.getName() : "Unknown";
			String clientId = parkingClient != null ? String.valueOf(parkingClient.getId()) : "N/A";
			String vehicleInfo = parkedVehicle != null ? 
				parkedVehicle.getModel() + " (Plate: " + parkedVehicle.getPlate() + ")" : 
				"Unknown Vehicle";

			view.showParkingReceipt(clientName, clientId, spotId, vehicleInfo, duration, price);
			
			// Update the UI
			updateParkingView();
			view.clearFields();
		}
	}

	private boolean isValidDateTime(String year, String month, String day, String hour, String minute) {
		try {
			int y = Integer.parseInt(year);
			int m = Integer.parseInt(month);
			int d = Integer.parseInt(day);
			int h = Integer.parseInt(hour);
			int min = Integer.parseInt(minute);

			return y >= 2000 && y <= 2100 && 
				   m >= 1 && m <= 12 &&
				   d >= 1 && d <= 31 &&
				   h >= 0 && h <= 23 &&
				   min >= 0 && min <= 59;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private void updateParkingView() {
		CarSpace[][] parkingSpaces = park.getParkingSpaces();
		view.updateParkingSpots(parkingSpaces);
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