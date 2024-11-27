package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Park;
import model.CarSpace;
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

		String yearStr = view.getYear();
		String monthStr = view.getMonth();
		String dayStr = view.getDay();
		String hourStr = view.getHour();
		String minuteStr = view.getMinute();

		if (!isNumeric(yearStr) || !isNumeric(monthStr) || !isNumeric(dayStr) || !isNumeric(hourStr)
				|| !isNumeric(minuteStr)) {
			view.showErrorMessage("Please fill out Year, Month, Day, Hour, and Minute with valid numbers.");
			return;
		}

		int year = Integer.parseInt(yearStr);
		int month = Integer.parseInt(monthStr);
		int day = Integer.parseInt(dayStr);
		int hour = Integer.parseInt(hourStr);
		int minute = Integer.parseInt(minuteStr);

		boolean success = park.freeSpot(row, col, year, month, day, hour, minute);
		if (success) {
			updateParkingView();
			view.showSuccessMessage("Spot successfully freed.");
			view.clearFields();
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